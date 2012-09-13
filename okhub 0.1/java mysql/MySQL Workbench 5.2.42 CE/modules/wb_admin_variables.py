# Copyright (c) 2007, 2012, Oracle and/or its affiliates. All rights reserved.
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

from wb_admin_utils import not_running_warning_label, weakcb

from mforms import newBox, newTreeNodeView, newButton, newTabView, newTextEntry
import mforms

import wb_admin_variable_list
from wb_admin_variable_list import Option

class VariablesViewer(mforms.Box):
    def __init__(self, ctrl_be, variables, command):
        mforms.Box.__init__(self, False)
        self.set_managed()
        self.set_release_on_add()

        self.suspend_layout()

        self.command = command
        self.ctrl_be = ctrl_be

        box = newBox(True)
        box.set_spacing(12)
        self.add(box, True, True)
        self.tree = newTreeNodeView(mforms.TreeFlatList)
        self.tree.set_size(180, -1)

        sidebox = newBox(False)

        box.add(sidebox, False, True)

        self.searchEntry = newTextEntry(mforms.SearchEntry)

        sidebox.set_spacing(12)
        sidebox.add(self.searchEntry, False, True)
        sidebox.add(self.tree, True, True)

        self.searchEntry.add_changed_callback(self.filterOutput)

        self.tree.add_column(mforms.StringColumnType, "", 160, False)
        self.tree.end_columns()
        self.tree.add_changed_callback(weakcb(self, "refresh"))

        self.values = newTreeNodeView(mforms.TreeFlatList)
        box.add(self.values, True, True)

        self.values.add_column(mforms.StringColumnType, "Name", 200, False)
        self.values.add_column(mforms.StringColumnType, "Value", 120, False)
        self.values.add_column(mforms.StringColumnType, "Description", 1000, False)
        self.values.end_columns()
        self.values.set_allow_sorting(True)

        box = newBox(True)
        box.set_spacing(8)
        copy_all_button = newButton()
        copy_all_button.set_text('Copy Global Status and Variables to Clipboard')
        copy_all_button.add_clicked_callback(self.copy_status_to_clipboard)
        box.add(copy_all_button, False, False)
        copy_shown_button = newButton()
        copy_shown_button.set_text('Copy Shown Variables to Clipboard')
        copy_shown_button.add_clicked_callback(self.copy_visible_vars_to_clipboard)
        box.add(copy_shown_button, False, False)
        button = newButton()
        box.add_end(button, False, True)
        button.set_text("Refresh")
        box.set_padding(12)

        button.add_clicked_callback(weakcb(self, "refresh"))

        self.add(box, False, True)

        self.groups = {}
        self.descriptions = {}
        def analyze(tree, level, d, groupd, version, vars, existing_vars):
            groups = {}
            variables = []
            for group_name, group_variables in vars.iteritems():
                vars_added_to_the_group = []
                groups[group_name] = vars_added_to_the_group
                for v in group_variables:
                    if v.var in existing_vars:
                        name = v.var
                        variables.append(name)
                        vars_added_to_the_group.append(v.var)
                        d[name] = v.description
                        existing_vars.remove(name)
                if vars_added_to_the_group:
                    row = tree.add_node()
                    row.set_string(0, group_name)
                    row.set_tag(group_name)
            return groups, variables

        row = self.tree.add_node()
        row.set_string(0, "All")
        row = self.tree.add_node()
        row.set_string(0, "Search Results")
        self.resume_layout()


        variables_in_server = []
        result = self.ctrl_be.exec_query(self.command)
        if result is not None:
            while result.nextRow():
                name = result.stringByName("Variable_name")
                variables_in_server.append(name)

        allgroups, allvars = analyze(self.tree, 0, self.descriptions, self.groups, self.ctrl_be.get_server_version(), variables, variables_in_server)
        if variables_in_server:
            allvars += variables_in_server
            group_name = "Other"
            allgroups[group_name] = variables_in_server
            row = self.tree.add_node()
            row.set_string(0, group_name)
            row.set_tag(group_name)

        self.groups = allgroups
        self.known_variables = allvars


    def refresh(self):
        if not self.ctrl_be.is_sql_connected():
            return

        row = self.tree.get_selected_node()
        if not row:
            self.values.clear()
            return

        self.values.freeze_refresh()
        self.values.clear()

        if self.tree.row_for_node(row) == 0:
            filter = None
            search = None
        elif self.tree.row_for_node(row) == 1:
            filter = None
            search = self.searchEntry.get_string_value()
        else:
            filter = None
            search = None
            tag = row.get_tag()
            if tag:
                filter = self.groups.get(tag)

        result = self.ctrl_be.exec_query(self.command)

        if result is not None:
            while result.nextRow():
                name = result.stringByName("Variable_name")
                if name not in self.known_variables:
                    print name
                    #print "%s is an unknown variable"%name

                if filter is not None and name not in filter:
                    continue

                if search is not None and search.lower() not in name.lower():
                    continue

                value = result.stringByName("Value")
                r = self.values.add_node()
                r.set_string(0, name)
                r.set_string(1, value)
                r.set_string(2, self.descriptions.get(name, ""))

        self.values.thaw_refresh()

    def filterOutput(self):
        self.tree.select_node(self.tree.node_at_row(1))
        self.refresh()

    def copy_status_to_clipboard(self):
        if not self.ctrl_be.is_sql_connected():
            mforms.Utilities.show_error('Connection error',
                                        'Cannot query the server for variables',
                                        'OK', '', '')
            return

        global_status = []
        result = self.ctrl_be.exec_query('SHOW GLOBAL STATUS')
        if result:
            while result.nextRow():
                var_name  = result.stringByName('Variable_name')
                var_value = result.stringByName('Value')
                global_status.append( (var_name, var_value) )

        global_variables = []
        result = self.ctrl_be.exec_query('SHOW GLOBAL VARIABLES')
        if result:
            while result.nextRow():
                var_name  = result.stringByName('Variable_name')
                var_value = result.stringByName('Value')
                global_variables.append( (var_name, var_value) )

        max_length = max( len(name) for name, val in global_status + global_variables ) + 5
        status = 'GLOBAL STATUS:\n'
        status += '\n'.join( [var_name.ljust(max_length, '.') + ' ' + var_value for var_name, var_value in global_status] )
        status += '\n\nGLOBAL VARIABLES:\n'
        status += '\n'.join( [var_name.ljust(max_length, '.') + ' ' + var_value for var_name, var_value in global_variables] )
        mforms.Utilities.set_clipboard_text(status)

    def copy_visible_vars_to_clipboard(self):
        nrows = self.values.count()
        visible_variables = []
        for idx in range(nrows):
            node = self.values.root_node().get_child(idx)
            visible_variables.append((node.get_string(0), node.get_string(1)))
        max_length = max( len(name) for name, val in visible_variables ) + 5
        status = '\n'.join( [var_name.ljust(max_length, '.') + ' ' + var_value for var_name, var_value in visible_variables] )
        mforms.Utilities.set_clipboard_text(status)



class WbAdminVariables(mforms.Box):
    ui_created = False
    def __init__(self, ctrl_be, server_profile, main_view):
        mforms.Box.__init__(self, False)
        main_view.ui_profile.apply_style(self, 'page')
        self.set_managed()
        self.set_release_on_add()
        self.ctrl_be = ctrl_be
        self.main_view = main_view
        self.main_view.add_content_page(self, "MANAGEMENT", "Status and System Variables", "admin_status_vars_win")


    def create_ui(self):
        self.warning = not_running_warning_label()
        self.add(self.warning, False, True)

        self.tab = newTabView(False)
        self.add(self.tab, True, True)

        self.status = VariablesViewer(self.ctrl_be, wb_admin_variable_list.vars_list['status'], "SHOW GLOBAL STATUS")
        self.status.set_padding(6)
        self.tab.add_page(self.status, "Status Variables")

        self.server = VariablesViewer(self.ctrl_be, wb_admin_variable_list.vars_list['system'], "SHOW GLOBAL VARIABLES")
        self.server.set_padding(6)
        self.tab.add_page(self.server, "System Variables")

    def page_activated(self):
        self.main_view.set_content_label(" Status and System Variables")
        if not self.ui_created:
            self.create_ui()
            self.ui_created = True

        if self.ctrl_be.is_sql_connected():
            self.warning.show(False)
            self.tab.show(True)
        else:
            self.warning.show(True)
            self.tab.show(False)

        self.status.refresh()
        self.server.refresh()
