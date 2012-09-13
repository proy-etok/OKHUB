from wb import DefineModule, wbinputs
import grt
import mforms

from cairo_utils import Context
from explain_renderer import render_json_data, render_json_data_to_file, decode_json
from performance_charting import TreePieChart, event_waits_summary_by_thread_by_event_name_to_tree

ModuleInfo = DefineModule(name= "SQLIDEQueryAnalysis", author= "Oracle Corp.", version= "1.0")


class JSONTreeViewer(mforms.TreeNodeView):
    def __init__(self):
        mforms.TreeNodeView.__init__(self, mforms.TreeAltRowColors|mforms.TreeShowColumnLines|mforms.TreeShowRowLines)
        self.add_column(mforms.StringColumnType, "Key", 200)
        self.add_column(mforms.StringColumnType, "Value", 300)
        self.end_columns()

    def display_data(self, data):
        def add_nodes(node, create_node, data):
            if type(data) is dict:
                if node:
                    node.set_string(1, "<dict>")
                for key, value in data.items():
                    ch = create_node()
                    ch.set_string(0, key)
                    add_nodes(ch, ch.add_child, value)
            elif type(data) is list:
                if node:
                    node.set_string(1, "<list>")
                for i, value in enumerate(data):
                    ch = create_node()
                    ch.set_string(0, str(i))
                    add_nodes(ch, ch.add_child, value)
            else:
                if not node:
                    node = create_node()
                if type(data) is bool:
                    node.set_string(1, "true" if data else "false")
                else:
                    node.set_string(1, str(data))
    
        data = decode_json(data)
        self.clear()
        add_nodes(None, self.add_node, data)

class RenderBox(mforms.PyDrawBox):
    def __init__(self):
        mforms.PyDrawBox.__init__(self)
        
        self.set_instance(self)
        self.repaint_callback = None
        self.size = None
    
    def repaint(self, cr, x, y, w, h):
        c = Context(cr)
        w, h = self.repaint_callback(c)
        if self.size != (w, h):
            self.set_size(w, h)
            self.size = (w, h)


@ModuleInfo.plugin("wb.sqlide.visual_explain", caption="Visual Explain", input=[wbinputs.currentQueryEditor()])
@ModuleInfo.export(grt.INT, grt.classes.db_query_QueryEditor)
def visualExplain(editor):
    version = editor.owner.serverVersion
    if version.majorNumber < 5 or version.minorNumber < 6:
        # explain format=json only supported in 5.6+
        mforms.Utilities.show_message("Visual Explain", "Visual Explain is supported in MySQL servers 5.6 or newer, but the one you are connected to is %s.%s.%s." % (version.majorNumber, version.minorNumber, version.releaseNumber), "OK", "", "")
    else:
        statement = editor.currentStatement
        if statement:
            rset = editor.owner.executeScript("EXPLAIN FORMAT=JSON %s" % statement)
            if rset:
                json = rset[0].stringFieldValue(0)

                view = mforms.newAppView(True, "QueryEditorExplain", False)
                #tree = JSONTreeViewer()
                #tree.display_data(json)
                #view.add(tree, False, True)
                #tree.set_size(400, -1)
                scroll = mforms.newScrollPanel(mforms.ScrollPanelNoFlags)
                scroll.set_visible_scrollers(True, True)
                
                #bgpattern = mforms.App.get().get_resource_path("background_stripes_light.png")
                bgpattern = None
                if False:
                    rbox = RenderBox()
                    rbox.set_size(200, 200)
                    rbox.repaint_callback = lambda cr: render_json_data(cr, json, bgpattern)
                    scroll.add(rbox)
                else:
                    path = mforms.App.get().get_user_data_folder()
                    w, h = render_json_data_to_file(json, bgpattern, path+"/explain.png")
                    img = mforms.newImageBox()
                    img.set_image(path+"/explain.png")
                    img.set_size(w, h)
                    scroll.add(img)
                
                view.add(scroll, True, True)
                dock = mforms.fromgrt(editor.resultDockingPoint)
                dock.dock_view(view, "", 0)
                dock.set_view_title(view, "Explain")

                rset[0].reset_references()
                
    return 0


@ModuleInfo.plugin("wb.sqlide.wait_summary", caption="P_S / Wait Summary", input=[wbinputs.currentQueryEditor()])
@ModuleInfo.export(grt.INT, grt.classes.db_query_QueryEditor)
def waitSummary(editor):
    statement = editor.currentStatement
    if statement:
        before_rows = []
        after_rows = []

        rset = editor.owner.executeScript("select * from performance_schema.events_waits_summary_by_thread_by_event_name as e join performance_schema.threads as t on e.thread_id=t.thread_id where t.processlist_id=connection_id()")
        if rset:
            while rset[0].nextRow():
                row = []
                for i in range(7):
                    if i == 1:
                        row.append(rset[0].stringFieldValue(i))
                    else:
                        row.append(rset[0].intFieldValue(i))
                before_rows.append(row)

        editor.owner.executeScriptAndOutputToGrid(statement)

        rset = editor.owner.executeScript("select * from performance_schema.events_waits_summary_by_thread_by_event_name as e join performance_schema.threads as t on e.thread_id=t.thread_id where t.processlist_id=connection_id()")
        if rset:
            while rset[0].nextRow():
                row = []
                for i in range(7):
                    if i == 1:
                        row.append(rset[0].stringFieldValue(i))
                    else:
                        row.append(rset[0].intFieldValue(i))
                after_rows.append(row)
        tree = event_waits_summary_by_thread_by_event_name_to_tree(before_rows, after_rows)
        print tree
        import cairo
        import cairo_utils
        surf = cairo_utils.ImageSurface(cairo.CAIRO_FORMAT_ARGB32, 800, 800)
        c = cairo_utils.Context(surf)
        c.set_source_rgb(0,0,0)
        c.paint()
        chart = TreePieChart(tree, c)
        chart.plot()
        surf.write_to_png("/tmp/explain.png")

        view = mforms.newAppView(True, "QueryEditorView", False)
        scroll = mforms.newScrollPanel(mforms.ScrollPanelNoFlags)
        scroll.set_visible_scrollers(True, True)
        image = mforms.newImageBox()
        image.set_size(800, 800)
        scroll.add(image)
        image.set_image("/tmp/explain.png")
        view.add(scroll, True, True)
        dock = mforms.fromgrt(editor.resultDockingPoint)
        dock.dock_view(view, "", 0)
        dock.set_view_title(view, "Explain")
            
    return 0

