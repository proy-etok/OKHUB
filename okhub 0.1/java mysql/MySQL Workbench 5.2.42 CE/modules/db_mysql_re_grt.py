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

from wb import DefineModule
import grt

from workbench.db_utils import MySQLConnection, escape_sql_string, escape_sql_identifier

from workbench.exceptions import NotConnectedError

ModuleInfo = DefineModule(name= "DbMySQLRE", author= "Oracle Corp.", version="1.0")

######################### Non exposed functions and variables #################

def check_interruption():
    if grt.query_status():
        raise grt.UserInterrupt()


_connections = {}

def get_connection(connection_object):
    if connection_object.__id__ in _connections:
        return _connections[connection_object.__id__]
    else:
        raise NotConnectedError("No open connection to %s" % connection_object.hostIdentifier)

def execute_query(connection_object, query):
    return get_connection(connection_object).executeQuery(query)

###############################################################################

@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.STRING)
def connect(connection, password):
    try:
        con = get_connection(connection)
        try:
            con.ping()
        except Exception:
            grt.send_info("Reconnecting to %s..." % connection.hostIdentifier)
            con.disconnect()
            con.connect()
            grt.send_info("Connection restablished")
    except NotConnectedError:
        con = MySQLConnection(connection, password = password)
        grt.send_info("Connecting to %s..." % connection.hostIdentifier)
        con.connect()
        grt.send_info("Connected")
        _connections[connection.__id__] = con
    return 1


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection)
def disconnect(connection):
    if _connections.has_key(connection.__id__):
        _connections[connection.__id__].disconnect()
        del _connections[connection.__id__]
    return 0


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection)
def isConnected(connection):
    return 1 if _connections.has_key(connection.__id__) else 0

@ModuleInfo.export(grt.STRING)
def getTargetDBMSName():
    return 'Mysql'

@ModuleInfo.export(grt.classes.GrtVersion, grt.classes.db_mgmt_Connection)
def getServerVersion(connection):
    """Returns a GrtVersion instance containing information about the server version."""
    result = execute_query(connection, "SHOW VARIABLES LIKE 'version'")
    if result and result.nextRow():
        import re
        p = re.match("([0-9]*)\.([0-9]*)\.([0-9]*)", result.stringByIndex(2))
        if p and p.groups():
            version = grt.classes.GrtVersion()
            ver_parts = [int(n) for n in p.groups()] + [0]*4
            version.majorNumber, version.minorNumber, version.releaseNumber, version.buildNumber = ver_parts[:4]
            return version
    return None


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection)
def getCatalogNames(connection):
    """Returns a list of the available catalogs.
    """
    return ["def"]

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING)
def getSchemaNames(connection, catalog):
    """Returns a list of schemas for the given connection object."""

    names = []
    result = execute_query(connection, "SHOW DATABASES")
    if grt.query_status():
        raise grt.UserInterrupt()
    while result and result.nextRow():
        names.append(result.stringByIndex(1))

    return names


def getAllTableNames(connection, catalog_name, schema_name):
    names = []
    result = execute_query(connection, "SHOW FULL TABLES FROM `%s`" % escape_sql_identifier(schema_name))
    while result and result.nextRow():
        names.append(result.stringByIndex(1))
    return names

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getTableNames(connection, catalog_name, schema_name):
    names = []
    result = execute_query(connection, "SHOW FULL TABLES FROM `%s` WHERE Table_type <> 'VIEW'" % escape_sql_identifier(schema_name))
    while result and result.nextRow():
        names.append(result.stringByIndex(1))
    return names

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getViewNames(connection, catalog_name, schema_name):
    names = []
    result = execute_query(connection, "SHOW FULL TABLES FROM `%s` WHERE Table_type = 'VIEW'" % escape_sql_identifier(schema_name))
    while result and result.nextRow():
        names.append(result.stringByIndex(1))
    return names

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getTriggerNames(connection, catalog_name, schema_name):
    names = []
    result = execute_query(connection, "SHOW TRIGGERS FROM `%s`" % escape_sql_identifier(schema_name))
    while result and result.nextRow():
        names.append(result.stringByIndex(1))
    return names

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getProcedureNames(connection, catalog_name, schema_name):
    names = []
    result = execute_query(connection, "SHOW PROCEDURE STATUS WHERE Db='%s'" % escape_sql_string(schema_name))
    while result and result.nextRow():
        names.append(result.stringByName("Name"))
    return names

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getFunctionNames(connection, catalog_name, schema_name):
    names = []
    result = execute_query(connection, "SHOW FUNCTION STATUS WHERE Db='%s'" % escape_sql_string(schema_name))
    while result and result.nextRow():
        names.append(result.stringByName("Name"))
    return names


#########  Reverse Engineering functions #########


@ModuleInfo.export(grt.classes.db_Catalog, grt.classes.db_mgmt_Connection, grt.STRING, (grt.LIST, grt.STRING), grt.DICT)
def reverseEngineer(connection, catalog_name, schemata_list, context):
    catalog = grt.classes.db_mysql_Catalog()
    catalog.name = catalog_name
    catalog.simpleDatatypes.remove_all()
    catalog.simpleDatatypes.extend(connection.driver.owner.simpleDatatypes)
    
    table_names_per_schema = {}
    routine_names_per_schema = {}
    trigger_names_per_schema = {}
    
    def filter_warnings(mtype, text, detail):
        # filter out parser warnings about stub creation/reuse from the message stream, since
        # they're harmless
        if mtype == "WARNING" and (" stub " in text or "Stub " in text):
            grt.send_info(text)
            return True
        return False
    
    get_tables = context.get("reverseEngineerTables", True)
    get_triggers = context.get("reverseEngineerTriggers", True)
    get_views = context.get("reverseEngineerViews", True)
    get_routines = context.get("reverseEngineerRoutines", True)
    
    # calculate total workload 1st
    
    # 10% of the progress is for preparation
    
    grt.send_progress(0, "Preparing...")
    total = 0
    i = 0.0
    for schema_name in schemata_list:
        check_interruption()
        if get_tables and get_views:
            table_names = getAllTableNames(connection, catalog_name, schema_name)
        elif get_tables:
            table_names = getTableNames(connection, catalog_name, schema_name)
        elif get_views:
            table_names = getViewNames(connection, catalog_name, schema_name)
        else:
            table_name = []
        total += len(table_names)
        table_names_per_schema[schema_name] = table_names
        check_interruption()
        if get_routines:
            procedure_names = getProcedureNames(connection, catalog_name, schema_name)
            check_interruption()
            function_names = getFunctionNames(connection, catalog_name, schema_name)
            check_interruption()
            total += len(procedure_names)
            total += len(function_names)
            routine_names_per_schema[schema_name] = procedure_names, function_names
        else:
            routine_names_per_schema[schema_name] = [], []
        if get_triggers:
            trigger_names = getTriggerNames(connection, catalog_name, schema_name)
            total += len(trigger_names)
        else:
            trigger_names = []
        trigger_names_per_schema[schema_name] = trigger_names
        
        grt.send_progress(0.1 * (i/len(schemata_list)), "Preparing...")
        i += 1.0

    def wrap_sql(sql, schema):
        return "USE `%s`;\n%s"%(escape_sql_identifier(schema), sql)

    def wrap_routine_sql(sql):
        return "DELIMITER $$\n"+sql

    i = 0.0
    for schema_name in schemata_list:
        schema = grt.classes.db_mysql_Schema()
        schema.owner = catalog
        schema.name = schema_name
        catalog.schemata.append(schema)

        if get_tables or get_views:
            grt.send_info("Reverse engineering tables from %s" % schema_name)
            for table_name in table_names_per_schema[schema_name]:
                check_interruption()
                grt.send_progress(0.1 + 0.9 * (i / total), "Retrieving table %s.%s..." % (schema_name, table_name))
                result = execute_query(connection, "SHOW CREATE TABLE `%s`.`%s`" % (escape_sql_identifier(schema_name), escape_sql_identifier(table_name)))
                i += 0.5
                grt.send_progress(0.1 + 0.9 * (i / total), "Reverse engineering %s.%s..." % (schema_name, table_name))
                if result and result.nextRow():
                    sql = result.stringByIndex(2)
                    grt.push_message_handler(filter_warnings)
                    grt.begin_progress_step(0.1 + 0.9 * (i / total), 0.1 + 0.9 * ((i+0.5) / total))
                    grt.modules.MysqlSqlFacade.parseSqlScriptString(catalog, wrap_sql(sql, schema_name))
                    grt.end_progress_step()
                    grt.pop_message_handler()
                    i += 0.5
                else:
                    raise Exception("Could not fetch table information for %s.%s" % (schema_name, table_name))

        if get_triggers:
            grt.send_info("Reverse engineering triggers from %s" % schema_name)
            for trigger_name in trigger_names_per_schema[schema_name]:
                check_interruption()
                grt.send_progress(0.1 + 0.9 * (i / total), "Retrieving trigger %s.%s..." % (schema_name, trigger_name))
                result = execute_query(connection, "SHOW CREATE TRIGGER `%s`.`%s`" % (escape_sql_identifier(schema_name), escape_sql_identifier(trigger_name)))
                i += 0.5
                grt.send_progress(0.1 + 0.9 * (i / total), "Reverse engineering %s.%s..." % (schema_name, trigger_name))
                if result and result.nextRow():
                    sql = result.stringByName("SQL Original Statement")
                    grt.begin_progress_step(0.1 + 0.9 * (i / total), 0.1 + 0.9 * ((i+0.5) / total))
                    grt.modules.MysqlSqlFacade.parseSqlScriptString(catalog, wrap_sql(wrap_routine_sql(sql), schema_name))
                    grt.end_progress_step()
                    i += 0.5
                else:
                    raise Exception("Could not fetch trigger information for %s.%s" % (schema_name, trigger_name))
        
        if get_routines:
            grt.send_info("Reverse engineering stored procedures from %s" % schema_name)
            procedure_names, function_names = routine_names_per_schema[schema_name]
            for name in procedure_names:
                check_interruption()
                grt.send_progress(0.1 + 0.9 * (i / total), "Retrieving stored procedure %s.%s..." % (schema_name, name))
                result = execute_query(connection, "SHOW CREATE PROCEDURE `%s`.`%s`" % (escape_sql_identifier(schema_name), escape_sql_identifier(name)))
                i += 0.5
                grt.send_progress(0.1 + 0.9 * (i / total), "Reverse engineering %s.%s..." % (schema_name, name))
                if result and result.nextRow():
                    sql = result.stringByName("Create Procedure")
                    grt.begin_progress_step(0.1 + 0.9 * (i / total), 0.1 + 0.9 * ((i+0.5) / total))
                    grt.modules.MysqlSqlFacade.parseSqlScriptString(catalog, wrap_sql(wrap_routine_sql(sql), schema_name))
                    grt.end_progress_step()
                    i += 0.5
                else:
                    raise Exception("Could not fetch procedure information for %s.%s" % (schema_name, name))

            grt.send_info("Reverse engineering functions from %s" % schema_name)
            for name in function_names:
                check_interruption()
                grt.send_progress(0.1 + 0.9 * (i / total), "Retrieving function %s.%s..." % (schema_name, name))
                result = execute_query(connection, "SHOW CREATE FUNCTION `%s`.`%s`" % (escape_sql_identifier(schema_name), escape_sql_identifier(name)))
                i += 0.5
                grt.send_progress(0.1 + 0.9 * (i / total), "Reverse engineering %s.%s..." % (schema_name, name))
                if result and result.nextRow():
                    sql = result.stringByName("Create Function")
                    grt.begin_progress_step(0.1 + 0.9 * (i / total), 0.1 + 0.9 * ((i+0.5) / total))
                    grt.modules.MysqlSqlFacade.parseSqlScriptString(catalog, wrap_sql(wrap_routine_sql(sql), schema_name))
                    grt.end_progress_step()
                    i += 0.5
                else:
                    raise Exception("Could not fetch function information for %s.%s" % (schema_name, name))

    grt.send_progress(1.0, "Reverse engineered %i objects" % total)
    
    # check for any stub tables left
    empty_schemas = []
    for schema in catalog.schemata:
        for table in reversed(schema.tables):
            if table.isStub:
                grt.send_warning("Table %s was referenced from another table, but was not reverse engineered" % table.name)
                schema.tables.remove(table)
        if not schema.tables and not schema.views and not schema.routines:
            empty_schemas.append(schema)
    for schema in empty_schemas:
        catalog.schemata.remove(schema)

    return catalog


@ModuleInfo.export(grt.classes.db_mysql_Table, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def reverseEngineerTable(connection, schema_name, table_name):
    catalog = grt.classes.db_mysql_Catalog()
    schema = grt.classes.db_mysql_Schema()
    schema.owner = catalog
    schema.name = schema_name
    catalog.schemata.append(schema)

    result = execute_query(connection, "SHOW CREATE TABLE `%s`.`%s`" % (escape_sql_identifier(schema_name), escape_sql_identifier(table_name)))
    if result and result.nextRow():
        sql = result.stringByIndex(2)
        grt.modules.MysqlSqlFacade.parseSqlScriptString(catalog, ("USE `%s`;\n" % escape_sql_identifier(schema_name))+sql)
    else:
        raise Exception("Could not fetch table information for %s" % table_name)

    table = [t for t in catalog.schemata[0].tables if t.name == table_name]
    if table:
        return table[0]
    return None


@ModuleInfo.export(grt.classes.db_mysql_Table, grt.classes.db_mgmt_Connection, grt.classes.db_mysql_Catalog, grt.STRING, grt.STRING)
def reverseEngineerTableToCatalog(connection, catalog, schema_name, table_name):
    schema = None
    for s in catalog.schemata:
        if s.name == schema_name:
            schema = s
            break
    if not schema:
        schema = grt.classes.db_mysql_Schema()
        schema.owner = catalog
        schema.name = schema_name
        catalog.schemata.append(schema)

    result = execute_query(connection, "SHOW CREATE TABLE `%s`.`%s`" % (escape_sql_identifier(schema_name), escape_sql_identifier(table_name)))
    if result and result.nextRow():
        sql = result.stringByIndex(2)
        grt.modules.MysqlSqlFacade.parseSqlScriptString(catalog, ("USE `%s`;\n" % escape_sql_identifier(schema_name))+sql)
    else:
        raise Exception("Could not fetch table information for %s" % table_name)

    table = [t for t in schema.tables if t.name == table_name]
    if table:
        return table[0]
    return None
