# Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License as
# published by the Free Software Foundation; version 2 of the
# License.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
# 02110-1301  USA

import os
import sys
import subprocess
import Queue
import grt
import re
from threading import Thread

from workbench.db_driver import get_connection_parameters
from workbench.utils import replace_string_parameters, parameters_from_dsn, dsn_parameters_to_connection_parameters


class HelperExited(Exception):
    def __init__(self, what, returncode):
        Exception.__init__(self, what)
        self.returncode = returncode


def mysql_conn_string(conn):
    param = conn.parameterValues
    if conn.driver.name == "MysqlNative":
        return "%(userName)s@%(hostName)s:%(port)s" % param
    elif conn.driver.name == "MysqlNativeSocket":
        return "%(userName)s@::%(socket)s" % param
    else:
        raise Exception("Connection method type %s is not supported for migration" % conn.driver.name)

def odbc_conn_string(conn, strip_password = False):
    conn_params = dict(conn.parameterValues)
    conn_params.update(get_connection_parameters(conn))

    connection_string_template = conn.driver.connectionStringTemplate or 'DRIVER={%driver%};SERVER=%host%;PORT=%port%;DATABASE={%database%};UID=%username%;PWD={%password%}'
    connstring = replace_string_parameters(connection_string_template, conn_params)
    if strip_password:
        connstring = re.sub("(PWD={[^;]}*|PWD=[^;]*)", "", connstring).rstrip(";")
    return connstring


class TableCopyWorker(Thread):
    def __init__(self, owner, args, result_queue):
        Thread.__init__(self)
        self._owner = owner
        self.result_queue = result_queue
        self._process_args = args
        grt.log_debug3("Migration", "Spawning copy worker task: %s" % args)
        self._owner.send_info(" ".join(args))
        if sys.platform == "win32":
            # shell=True causes a created window to be hidden by default, this prevents a popup to be shown
            # on the migration wizard
            self.process = subprocess.Popen(args, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, 
                            universal_newlines=True, shell=True)
        else:
            self.process = subprocess.Popen(args, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, 
                            universal_newlines=True, close_fds=True)

    def feed_input(self, text):
        if self.process.poll() is None:
            if type(text) is unicode:
                text = text.encode("utf-8")
            self.process.stdin.write(text)
            self.process.stdin.flush()
        else:
            raise HelperExited("Table copy task finished unexpectedly", self.process.returncode)


    def run(self):
        try:
            while self.process.poll() is None:
                if self._owner.query_cancel_status():
                    raise grt.UserInterrupt("Canceled by user")
                line = self.process.stdout.readline()
                if line is not None:
                    type, _, msg = line.strip().partition(":")
                    if type in ("PROGRESS", "ERROR", "BEGIN", "END"):
                        self.result_queue.put((type, msg))
                    else:
                        self.result_queue.put(("LOG", msg))
            self.result_queue.put(("done", "Worker exited with status %s" % self.process.returncode))
        except grt.UserInterrupt, e:
            self._owner.send_info("Copy task interrupted by user, terminating %s..." % self.name)
            self.terminate()
            self.result_queue.put(("interrupted", None))
        except Exception, e:
            import traceback
            traceback.print_exc()
            self.result_queue.put(("done", str(e)))

    def terminate(self):
        if self.process.poll() is None:
            o, e = self.process.communicate()
            if o:
                last_progress = None
                for l in o.split("\n"):
                    if l.startswith("PROGRESS:"):
                        last_progress = l
                    else:
                        self._owner.send_info(l)
                if last_progress:
                    self.result_queue.put(l)
            if e:
                self._owner.send_info(e)
            
            # The process should be killed ONLY if it has not yet finished
            try:
              if hasattr(self.process, "terminate"):
                self.process.terminate()
              else:
                import signal
                os.kill(self.process.pid, signal.SIGTERM)
            except OSError, e:
              if e.errno == 3:
                pass
              else:
                # can't kill process
                self._owner.send_error("Unable to kill worker task %s: %s" % (self.process.id, e))

        self.process.wait()


class DataMigrator(object):
    copytable_path = "wbcopytables"
    
    def __init__(self, message_target, options, srcconnobj, srcpassword, tgtconnobj, tgtpassword):
        assert hasattr(message_target, "send_info") and hasattr(message_target, "send_error") and hasattr(message_target, "send_progress")
        self._owner = message_target
        self._options = options
        self._src_conn_object = srcconnobj
        self._src_password = srcpassword
        self._tgt_conn_object = tgtconnobj
        self._tgt_password = tgtpassword

        # Container for the tasks...
        self._tasks = []
        self._processes = []
        self._error = None

    def count_table_rows(self, working_set):
        table_list = []
        for task in working_set.values():
            table_list += ["--table", task["source_schema"], task["source_table"]]
        stdout = ""

        if self._src_conn_object.driver.owner.name == "Mysql":
            args = ["--mysql-source=%s" % mysql_conn_string(self._src_conn_object)]
        else:
            args = ["--odbc-source=%s" % odbc_conn_string(self._src_conn_object, True)]
        argv = [self.copytable_path, "--count-only", "--passwords-from-stdin"] + args + table_list
        self._owner.send_info(" ".join(argv))
        
        if sys.platform == "win32":
            # shell=True causes a created window to be hidden by default, this prevents a popup to be shown
            # on the migration wizard
            out = subprocess.Popen(argv, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
        else:
            out = subprocess.Popen(argv, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            
        passwords= self._src_password+"\n"
        while out.poll() is None:
            o, e = out.communicate(passwords)
            passwords = None
            if o:
                stdout += o
            if e:
                for l in e.split("\n"):
                    self._owner.send_info(l)
            total = 0
        if out.returncode == 0:
            for schema, table, count in [l.split(":")[1:] for l in stdout.split("\n") if l.startswith("ROW_COUNT")]:
                count = int(count.strip())
                total += count
                working_set[schema+"."+table]["row_count"] = count
            return total
        else:
            raise Exception("Error getting row count from source tables")

    def migrate_data(self, num_processes, working_set):
        table_list = []
        
        for task in working_set.values():
            table_list += ["--table", task["source_schema"], task["source_table"], task["target_schema"], task["target_table"]]
            if task.get("select_expression", None):
                table_list.append(task["select_expression"])
            else:
                table_list.append("*")

        stdout = ""
        
        if len(working_set) < num_processes:
            num_processes = len(working_set)

        args = self.helper_basic_arglist()
        args += ["--progress", "--passwords-from-stdin"]

        if self._options.get("TruncateTargetTables", False):
            args.append("--truncate-target")
        if self._options.get("DebugTableCopy", False):
            args.append("--log-level=debug3")

        args.append("--thread-count=" + str(num_processes));

        argv = [self.copytable_path] + args + table_list

        self._working_set = working_set
        self._result_queue = Queue.Queue(len(working_set))

        worker = TableCopyWorker(self._owner, argv, self._result_queue)
        worker.feed_input(self._src_password+"\t"+self._tgt_password+"\n")
        worker.start()
        results = self.process_until_done()
        worker.terminate()
        return results


    def helper_basic_arglist(self):
        if self._src_conn_object.driver.owner.name == "Mysql":
            args = ["--mysql-source=%s" % mysql_conn_string(self._src_conn_object)]
        else:
            args = ["--odbc-source=%s" % odbc_conn_string(self._src_conn_object, True)]
        args += [
          "--target=%s" % mysql_conn_string(self._tgt_conn_object),
        ]
        # for FreeTDS
        if self._src_conn_object.parameterValues.get("ODBCDriverUsesUTF8", False):
            args.append("--force-utf8-for-source")
        return args


    def process_until_done(self):
        total_row_count = 0
        for table in self._working_set.values():
            total_row_count += table["row_count"]

        progress_row_count = {}

        self.interrupted = False

        active_job_names = set()
        done = False
        
        while True:
            if done:
                # flush pending messages
                try:
                    msgtype, message = self._result_queue.get_nowait()
                except Queue.Empty:
                    break
            else:
                msgtype, message = self._result_queue.get()

            if msgtype == "BEGIN":
                target_table = message.split(":")[0]
                active_job_names.add(target_table)
                self._owner.send_info(message)
            elif msgtype == "END":
                target_table = message.split(":")[0]
                if target_table in active_job_names:
                    active_job_names.remove(target_table)
                self._owner.send_info(message)
                progress_row_count[target_table] = (True, progress_row_count.get(target_table, (False, 0))[1])
                
            elif msgtype == "ERROR":
                target_table = message.split(":")[0]
                if target_table in active_job_names:
                    active_job_names.remove(target_table)
                self._owner.send_error(message)
                
                self._owner.add_log_entry(2, target_table, message)
                
            elif msgtype == "PROGRESS":
                target_table, current, total = message.split(":")
                progress_row_count[target_table] = (False, int(current))
                self._owner.send_progress(float(sum([x[1] for x in progress_row_count.values()])) / total_row_count, "Copying %s" % ", ".join(active_job_names))
            elif msgtype == "log":
                self._owner.send_info(message)
            elif msgtype == "done":
                done = True
                self._owner.send_info("Copy helper has finished")
            elif msgtype == "interrupted":
                done = True
                self.interrupted = True
                self._owner.send_info("Copy helper was aborted by user")

        return progress_row_count

