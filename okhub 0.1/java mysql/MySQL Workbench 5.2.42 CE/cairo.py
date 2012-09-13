# This file was automatically generated by SWIG (http://www.swig.org).
# Version 1.3.31
#
# Don't modify this file, modify the SWIG interface instead.
# This file is compatible with both classic and new-style classes.

import _cairo
import new
new_instancemethod = new.instancemethod
try:
    _swig_property = property
except NameError:
    pass # Python < 2.2 doesn't have 'property'.
def _swig_setattr_nondynamic(self,class_type,name,value,static=1):
    if (name == "thisown"): return self.this.own(value)
    if (name == "this"):
        if type(value).__name__ == 'PySwigObject':
            self.__dict__[name] = value
            return
    method = class_type.__swig_setmethods__.get(name,None)
    if method: return method(self,value)
    if (not static) or hasattr(self,name):
        self.__dict__[name] = value
    else:
        raise AttributeError("You cannot add attributes to %s" % self)

def _swig_setattr(self,class_type,name,value):
    return _swig_setattr_nondynamic(self,class_type,name,value,0)

def _swig_getattr(self,class_type,name):
    if (name == "thisown"): return self.this.own()
    method = class_type.__swig_getmethods__.get(name,None)
    if method: return method(self)
    raise AttributeError,name

def _swig_repr(self):
    try: strthis = "proxy of " + self.this.__repr__()
    except: strthis = ""
    return "<%s.%s; %s >" % (self.__class__.__module__, self.__class__.__name__, strthis,)

import types
try:
    _object = types.ObjectType
    _newclass = 1
except AttributeError:
    class _object : pass
    _newclass = 0
del types


CAIRO_VERSION_STRING = _cairo.CAIRO_VERSION_STRING
cairo_version = _cairo.cairo_version
cairo_version_string = _cairo.cairo_version_string
CAIRO_STATUS_SUCCESS = _cairo.CAIRO_STATUS_SUCCESS
CAIRO_STATUS_NO_MEMORY = _cairo.CAIRO_STATUS_NO_MEMORY
CAIRO_STATUS_INVALID_RESTORE = _cairo.CAIRO_STATUS_INVALID_RESTORE
CAIRO_STATUS_INVALID_POP_GROUP = _cairo.CAIRO_STATUS_INVALID_POP_GROUP
CAIRO_STATUS_NO_CURRENT_POINT = _cairo.CAIRO_STATUS_NO_CURRENT_POINT
CAIRO_STATUS_INVALID_MATRIX = _cairo.CAIRO_STATUS_INVALID_MATRIX
CAIRO_STATUS_INVALID_STATUS = _cairo.CAIRO_STATUS_INVALID_STATUS
CAIRO_STATUS_NULL_POINTER = _cairo.CAIRO_STATUS_NULL_POINTER
CAIRO_STATUS_INVALID_STRING = _cairo.CAIRO_STATUS_INVALID_STRING
CAIRO_STATUS_INVALID_PATH_DATA = _cairo.CAIRO_STATUS_INVALID_PATH_DATA
CAIRO_STATUS_READ_ERROR = _cairo.CAIRO_STATUS_READ_ERROR
CAIRO_STATUS_WRITE_ERROR = _cairo.CAIRO_STATUS_WRITE_ERROR
CAIRO_STATUS_SURFACE_FINISHED = _cairo.CAIRO_STATUS_SURFACE_FINISHED
CAIRO_STATUS_SURFACE_TYPE_MISMATCH = _cairo.CAIRO_STATUS_SURFACE_TYPE_MISMATCH
CAIRO_STATUS_PATTERN_TYPE_MISMATCH = _cairo.CAIRO_STATUS_PATTERN_TYPE_MISMATCH
CAIRO_STATUS_INVALID_CONTENT = _cairo.CAIRO_STATUS_INVALID_CONTENT
CAIRO_STATUS_INVALID_FORMAT = _cairo.CAIRO_STATUS_INVALID_FORMAT
CAIRO_STATUS_INVALID_VISUAL = _cairo.CAIRO_STATUS_INVALID_VISUAL
CAIRO_STATUS_FILE_NOT_FOUND = _cairo.CAIRO_STATUS_FILE_NOT_FOUND
CAIRO_STATUS_INVALID_DASH = _cairo.CAIRO_STATUS_INVALID_DASH
CAIRO_STATUS_INVALID_DSC_COMMENT = _cairo.CAIRO_STATUS_INVALID_DSC_COMMENT
CAIRO_STATUS_INVALID_INDEX = _cairo.CAIRO_STATUS_INVALID_INDEX
CAIRO_STATUS_CLIP_NOT_REPRESENTABLE = _cairo.CAIRO_STATUS_CLIP_NOT_REPRESENTABLE
CAIRO_STATUS_TEMP_FILE_ERROR = _cairo.CAIRO_STATUS_TEMP_FILE_ERROR
CAIRO_STATUS_INVALID_STRIDE = _cairo.CAIRO_STATUS_INVALID_STRIDE
CAIRO_STATUS_FONT_TYPE_MISMATCH = _cairo.CAIRO_STATUS_FONT_TYPE_MISMATCH
CAIRO_STATUS_USER_FONT_IMMUTABLE = _cairo.CAIRO_STATUS_USER_FONT_IMMUTABLE
CAIRO_STATUS_USER_FONT_ERROR = _cairo.CAIRO_STATUS_USER_FONT_ERROR
CAIRO_STATUS_NEGATIVE_COUNT = _cairo.CAIRO_STATUS_NEGATIVE_COUNT
CAIRO_STATUS_INVALID_CLUSTERS = _cairo.CAIRO_STATUS_INVALID_CLUSTERS
CAIRO_STATUS_INVALID_SLANT = _cairo.CAIRO_STATUS_INVALID_SLANT
CAIRO_STATUS_INVALID_WEIGHT = _cairo.CAIRO_STATUS_INVALID_WEIGHT
CAIRO_CONTENT_COLOR = _cairo.CAIRO_CONTENT_COLOR
CAIRO_CONTENT_ALPHA = _cairo.CAIRO_CONTENT_ALPHA
CAIRO_CONTENT_COLOR_ALPHA = _cairo.CAIRO_CONTENT_COLOR_ALPHA
cairo_create = _cairo.cairo_create
cairo_reference = _cairo.cairo_reference
cairo_destroy = _cairo.cairo_destroy
cairo_get_reference_count = _cairo.cairo_get_reference_count
cairo_save = _cairo.cairo_save
cairo_restore = _cairo.cairo_restore
cairo_push_group = _cairo.cairo_push_group
cairo_push_group_with_content = _cairo.cairo_push_group_with_content
cairo_pop_group = _cairo.cairo_pop_group
cairo_pop_group_to_source = _cairo.cairo_pop_group_to_source
CAIRO_OPERATOR_CLEAR = _cairo.CAIRO_OPERATOR_CLEAR
CAIRO_OPERATOR_SOURCE = _cairo.CAIRO_OPERATOR_SOURCE
CAIRO_OPERATOR_OVER = _cairo.CAIRO_OPERATOR_OVER
CAIRO_OPERATOR_IN = _cairo.CAIRO_OPERATOR_IN
CAIRO_OPERATOR_OUT = _cairo.CAIRO_OPERATOR_OUT
CAIRO_OPERATOR_ATOP = _cairo.CAIRO_OPERATOR_ATOP
CAIRO_OPERATOR_DEST = _cairo.CAIRO_OPERATOR_DEST
CAIRO_OPERATOR_DEST_OVER = _cairo.CAIRO_OPERATOR_DEST_OVER
CAIRO_OPERATOR_DEST_IN = _cairo.CAIRO_OPERATOR_DEST_IN
CAIRO_OPERATOR_DEST_OUT = _cairo.CAIRO_OPERATOR_DEST_OUT
CAIRO_OPERATOR_DEST_ATOP = _cairo.CAIRO_OPERATOR_DEST_ATOP
CAIRO_OPERATOR_XOR = _cairo.CAIRO_OPERATOR_XOR
CAIRO_OPERATOR_ADD = _cairo.CAIRO_OPERATOR_ADD
CAIRO_OPERATOR_SATURATE = _cairo.CAIRO_OPERATOR_SATURATE
cairo_set_operator = _cairo.cairo_set_operator
cairo_set_source = _cairo.cairo_set_source
cairo_set_source_rgb = _cairo.cairo_set_source_rgb
cairo_set_source_rgba = _cairo.cairo_set_source_rgba
cairo_set_source_surface = _cairo.cairo_set_source_surface
cairo_set_tolerance = _cairo.cairo_set_tolerance
CAIRO_ANTIALIAS_DEFAULT = _cairo.CAIRO_ANTIALIAS_DEFAULT
CAIRO_ANTIALIAS_NONE = _cairo.CAIRO_ANTIALIAS_NONE
CAIRO_ANTIALIAS_GRAY = _cairo.CAIRO_ANTIALIAS_GRAY
CAIRO_ANTIALIAS_SUBPIXEL = _cairo.CAIRO_ANTIALIAS_SUBPIXEL
cairo_set_antialias = _cairo.cairo_set_antialias
CAIRO_FILL_RULE_WINDING = _cairo.CAIRO_FILL_RULE_WINDING
CAIRO_FILL_RULE_EVEN_ODD = _cairo.CAIRO_FILL_RULE_EVEN_ODD
cairo_set_fill_rule = _cairo.cairo_set_fill_rule
cairo_set_line_width = _cairo.cairo_set_line_width
CAIRO_LINE_CAP_BUTT = _cairo.CAIRO_LINE_CAP_BUTT
CAIRO_LINE_CAP_ROUND = _cairo.CAIRO_LINE_CAP_ROUND
CAIRO_LINE_CAP_SQUARE = _cairo.CAIRO_LINE_CAP_SQUARE
cairo_set_line_cap = _cairo.cairo_set_line_cap
CAIRO_LINE_JOIN_MITER = _cairo.CAIRO_LINE_JOIN_MITER
CAIRO_LINE_JOIN_ROUND = _cairo.CAIRO_LINE_JOIN_ROUND
CAIRO_LINE_JOIN_BEVEL = _cairo.CAIRO_LINE_JOIN_BEVEL
cairo_set_line_join = _cairo.cairo_set_line_join
cairo_set_dash = _cairo.cairo_set_dash
cairo_set_miter_limit = _cairo.cairo_set_miter_limit
cairo_translate = _cairo.cairo_translate
cairo_scale = _cairo.cairo_scale
cairo_rotate = _cairo.cairo_rotate
cairo_transform = _cairo.cairo_transform
cairo_set_matrix = _cairo.cairo_set_matrix
cairo_identity_matrix = _cairo.cairo_identity_matrix
cairo_user_to_device = _cairo.cairo_user_to_device
cairo_user_to_device_distance = _cairo.cairo_user_to_device_distance
cairo_device_to_user = _cairo.cairo_device_to_user
cairo_device_to_user_distance = _cairo.cairo_device_to_user_distance
cairo_new_path = _cairo.cairo_new_path
cairo_move_to = _cairo.cairo_move_to
cairo_new_sub_path = _cairo.cairo_new_sub_path
cairo_line_to = _cairo.cairo_line_to
cairo_curve_to = _cairo.cairo_curve_to
cairo_arc = _cairo.cairo_arc
cairo_arc_negative = _cairo.cairo_arc_negative
cairo_rel_move_to = _cairo.cairo_rel_move_to
cairo_rel_line_to = _cairo.cairo_rel_line_to
cairo_rel_curve_to = _cairo.cairo_rel_curve_to
cairo_rectangle = _cairo.cairo_rectangle
cairo_close_path = _cairo.cairo_close_path
cairo_path_extents = _cairo.cairo_path_extents
cairo_paint = _cairo.cairo_paint
cairo_paint_with_alpha = _cairo.cairo_paint_with_alpha
cairo_mask = _cairo.cairo_mask
cairo_mask_surface = _cairo.cairo_mask_surface
cairo_stroke = _cairo.cairo_stroke
cairo_stroke_preserve = _cairo.cairo_stroke_preserve
cairo_fill = _cairo.cairo_fill
cairo_fill_preserve = _cairo.cairo_fill_preserve
cairo_copy_page = _cairo.cairo_copy_page
cairo_show_page = _cairo.cairo_show_page
cairo_in_stroke = _cairo.cairo_in_stroke
cairo_in_fill = _cairo.cairo_in_fill
cairo_stroke_extents = _cairo.cairo_stroke_extents
cairo_fill_extents = _cairo.cairo_fill_extents
cairo_reset_clip = _cairo.cairo_reset_clip
cairo_clip = _cairo.cairo_clip
cairo_clip_preserve = _cairo.cairo_clip_preserve
cairo_clip_extents = _cairo.cairo_clip_extents
class cairo_glyph_t(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, cairo_glyph_t, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, cairo_glyph_t, name)
    __repr__ = _swig_repr
    __swig_setmethods__["index"] = _cairo.cairo_glyph_t_index_set
    __swig_getmethods__["index"] = _cairo.cairo_glyph_t_index_get
    if _newclass:index = _swig_property(_cairo.cairo_glyph_t_index_get, _cairo.cairo_glyph_t_index_set)
    __swig_setmethods__["x"] = _cairo.cairo_glyph_t_x_set
    __swig_getmethods__["x"] = _cairo.cairo_glyph_t_x_get
    if _newclass:x = _swig_property(_cairo.cairo_glyph_t_x_get, _cairo.cairo_glyph_t_x_set)
    __swig_setmethods__["y"] = _cairo.cairo_glyph_t_y_set
    __swig_getmethods__["y"] = _cairo.cairo_glyph_t_y_get
    if _newclass:y = _swig_property(_cairo.cairo_glyph_t_y_get, _cairo.cairo_glyph_t_y_set)
    def __init__(self, *args): 
        this = _cairo.new_cairo_glyph_t(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete_cairo_glyph_t
    __del__ = lambda self : None;
cairo_glyph_t_swigregister = _cairo.cairo_glyph_t_swigregister
cairo_glyph_t_swigregister(cairo_glyph_t)

cairo_glyph_allocate = _cairo.cairo_glyph_allocate
cairo_glyph_free = _cairo.cairo_glyph_free
class cairo_text_cluster_t(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, cairo_text_cluster_t, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, cairo_text_cluster_t, name)
    __repr__ = _swig_repr
    __swig_setmethods__["num_bytes"] = _cairo.cairo_text_cluster_t_num_bytes_set
    __swig_getmethods__["num_bytes"] = _cairo.cairo_text_cluster_t_num_bytes_get
    if _newclass:num_bytes = _swig_property(_cairo.cairo_text_cluster_t_num_bytes_get, _cairo.cairo_text_cluster_t_num_bytes_set)
    __swig_setmethods__["num_glyphs"] = _cairo.cairo_text_cluster_t_num_glyphs_set
    __swig_getmethods__["num_glyphs"] = _cairo.cairo_text_cluster_t_num_glyphs_get
    if _newclass:num_glyphs = _swig_property(_cairo.cairo_text_cluster_t_num_glyphs_get, _cairo.cairo_text_cluster_t_num_glyphs_set)
    def __init__(self, *args): 
        this = _cairo.new_cairo_text_cluster_t(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete_cairo_text_cluster_t
    __del__ = lambda self : None;
cairo_text_cluster_t_swigregister = _cairo.cairo_text_cluster_t_swigregister
cairo_text_cluster_t_swigregister(cairo_text_cluster_t)

cairo_text_cluster_allocate = _cairo.cairo_text_cluster_allocate
cairo_text_cluster_free = _cairo.cairo_text_cluster_free
CAIRO_TEXT_CLUSTER_FLAG_BACKWARD = _cairo.CAIRO_TEXT_CLUSTER_FLAG_BACKWARD
class cairo_text_extents_t(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, cairo_text_extents_t, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, cairo_text_extents_t, name)
    __repr__ = _swig_repr
    __swig_setmethods__["x_bearing"] = _cairo.cairo_text_extents_t_x_bearing_set
    __swig_getmethods__["x_bearing"] = _cairo.cairo_text_extents_t_x_bearing_get
    if _newclass:x_bearing = _swig_property(_cairo.cairo_text_extents_t_x_bearing_get, _cairo.cairo_text_extents_t_x_bearing_set)
    __swig_setmethods__["y_bearing"] = _cairo.cairo_text_extents_t_y_bearing_set
    __swig_getmethods__["y_bearing"] = _cairo.cairo_text_extents_t_y_bearing_get
    if _newclass:y_bearing = _swig_property(_cairo.cairo_text_extents_t_y_bearing_get, _cairo.cairo_text_extents_t_y_bearing_set)
    __swig_setmethods__["width"] = _cairo.cairo_text_extents_t_width_set
    __swig_getmethods__["width"] = _cairo.cairo_text_extents_t_width_get
    if _newclass:width = _swig_property(_cairo.cairo_text_extents_t_width_get, _cairo.cairo_text_extents_t_width_set)
    __swig_setmethods__["height"] = _cairo.cairo_text_extents_t_height_set
    __swig_getmethods__["height"] = _cairo.cairo_text_extents_t_height_get
    if _newclass:height = _swig_property(_cairo.cairo_text_extents_t_height_get, _cairo.cairo_text_extents_t_height_set)
    __swig_setmethods__["x_advance"] = _cairo.cairo_text_extents_t_x_advance_set
    __swig_getmethods__["x_advance"] = _cairo.cairo_text_extents_t_x_advance_get
    if _newclass:x_advance = _swig_property(_cairo.cairo_text_extents_t_x_advance_get, _cairo.cairo_text_extents_t_x_advance_set)
    __swig_setmethods__["y_advance"] = _cairo.cairo_text_extents_t_y_advance_set
    __swig_getmethods__["y_advance"] = _cairo.cairo_text_extents_t_y_advance_get
    if _newclass:y_advance = _swig_property(_cairo.cairo_text_extents_t_y_advance_get, _cairo.cairo_text_extents_t_y_advance_set)
    def __init__(self, *args): 
        this = _cairo.new_cairo_text_extents_t(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete_cairo_text_extents_t
    __del__ = lambda self : None;
cairo_text_extents_t_swigregister = _cairo.cairo_text_extents_t_swigregister
cairo_text_extents_t_swigregister(cairo_text_extents_t)

class cairo_font_extents_t(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, cairo_font_extents_t, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, cairo_font_extents_t, name)
    __repr__ = _swig_repr
    __swig_setmethods__["ascent"] = _cairo.cairo_font_extents_t_ascent_set
    __swig_getmethods__["ascent"] = _cairo.cairo_font_extents_t_ascent_get
    if _newclass:ascent = _swig_property(_cairo.cairo_font_extents_t_ascent_get, _cairo.cairo_font_extents_t_ascent_set)
    __swig_setmethods__["descent"] = _cairo.cairo_font_extents_t_descent_set
    __swig_getmethods__["descent"] = _cairo.cairo_font_extents_t_descent_get
    if _newclass:descent = _swig_property(_cairo.cairo_font_extents_t_descent_get, _cairo.cairo_font_extents_t_descent_set)
    __swig_setmethods__["height"] = _cairo.cairo_font_extents_t_height_set
    __swig_getmethods__["height"] = _cairo.cairo_font_extents_t_height_get
    if _newclass:height = _swig_property(_cairo.cairo_font_extents_t_height_get, _cairo.cairo_font_extents_t_height_set)
    __swig_setmethods__["max_x_advance"] = _cairo.cairo_font_extents_t_max_x_advance_set
    __swig_getmethods__["max_x_advance"] = _cairo.cairo_font_extents_t_max_x_advance_get
    if _newclass:max_x_advance = _swig_property(_cairo.cairo_font_extents_t_max_x_advance_get, _cairo.cairo_font_extents_t_max_x_advance_set)
    __swig_setmethods__["max_y_advance"] = _cairo.cairo_font_extents_t_max_y_advance_set
    __swig_getmethods__["max_y_advance"] = _cairo.cairo_font_extents_t_max_y_advance_get
    if _newclass:max_y_advance = _swig_property(_cairo.cairo_font_extents_t_max_y_advance_get, _cairo.cairo_font_extents_t_max_y_advance_set)
    def __init__(self, *args): 
        this = _cairo.new_cairo_font_extents_t(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete_cairo_font_extents_t
    __del__ = lambda self : None;
cairo_font_extents_t_swigregister = _cairo.cairo_font_extents_t_swigregister
cairo_font_extents_t_swigregister(cairo_font_extents_t)

CAIRO_FONT_SLANT_NORMAL = _cairo.CAIRO_FONT_SLANT_NORMAL
CAIRO_FONT_SLANT_ITALIC = _cairo.CAIRO_FONT_SLANT_ITALIC
CAIRO_FONT_SLANT_OBLIQUE = _cairo.CAIRO_FONT_SLANT_OBLIQUE
CAIRO_FONT_WEIGHT_NORMAL = _cairo.CAIRO_FONT_WEIGHT_NORMAL
CAIRO_FONT_WEIGHT_BOLD = _cairo.CAIRO_FONT_WEIGHT_BOLD
CAIRO_SUBPIXEL_ORDER_DEFAULT = _cairo.CAIRO_SUBPIXEL_ORDER_DEFAULT
CAIRO_SUBPIXEL_ORDER_RGB = _cairo.CAIRO_SUBPIXEL_ORDER_RGB
CAIRO_SUBPIXEL_ORDER_BGR = _cairo.CAIRO_SUBPIXEL_ORDER_BGR
CAIRO_SUBPIXEL_ORDER_VRGB = _cairo.CAIRO_SUBPIXEL_ORDER_VRGB
CAIRO_SUBPIXEL_ORDER_VBGR = _cairo.CAIRO_SUBPIXEL_ORDER_VBGR
CAIRO_HINT_STYLE_DEFAULT = _cairo.CAIRO_HINT_STYLE_DEFAULT
CAIRO_HINT_STYLE_NONE = _cairo.CAIRO_HINT_STYLE_NONE
CAIRO_HINT_STYLE_SLIGHT = _cairo.CAIRO_HINT_STYLE_SLIGHT
CAIRO_HINT_STYLE_MEDIUM = _cairo.CAIRO_HINT_STYLE_MEDIUM
CAIRO_HINT_STYLE_FULL = _cairo.CAIRO_HINT_STYLE_FULL
CAIRO_HINT_METRICS_DEFAULT = _cairo.CAIRO_HINT_METRICS_DEFAULT
CAIRO_HINT_METRICS_OFF = _cairo.CAIRO_HINT_METRICS_OFF
CAIRO_HINT_METRICS_ON = _cairo.CAIRO_HINT_METRICS_ON
cairo_font_options_create = _cairo.cairo_font_options_create
cairo_font_options_copy = _cairo.cairo_font_options_copy
cairo_font_options_destroy = _cairo.cairo_font_options_destroy
cairo_font_options_status = _cairo.cairo_font_options_status
cairo_font_options_merge = _cairo.cairo_font_options_merge
cairo_font_options_equal = _cairo.cairo_font_options_equal
cairo_font_options_hash = _cairo.cairo_font_options_hash
cairo_font_options_set_antialias = _cairo.cairo_font_options_set_antialias
cairo_font_options_get_antialias = _cairo.cairo_font_options_get_antialias
cairo_font_options_set_subpixel_order = _cairo.cairo_font_options_set_subpixel_order
cairo_font_options_get_subpixel_order = _cairo.cairo_font_options_get_subpixel_order
cairo_font_options_set_hint_style = _cairo.cairo_font_options_set_hint_style
cairo_font_options_get_hint_style = _cairo.cairo_font_options_get_hint_style
cairo_font_options_set_hint_metrics = _cairo.cairo_font_options_set_hint_metrics
cairo_font_options_get_hint_metrics = _cairo.cairo_font_options_get_hint_metrics
cairo_select_font_face = _cairo.cairo_select_font_face
cairo_set_font_size = _cairo.cairo_set_font_size
cairo_set_font_matrix = _cairo.cairo_set_font_matrix
cairo_get_font_matrix = _cairo.cairo_get_font_matrix
cairo_set_font_options = _cairo.cairo_set_font_options
cairo_get_font_options = _cairo.cairo_get_font_options
cairo_set_font_face = _cairo.cairo_set_font_face
cairo_get_font_face = _cairo.cairo_get_font_face
cairo_set_scaled_font = _cairo.cairo_set_scaled_font
cairo_get_scaled_font = _cairo.cairo_get_scaled_font
cairo_show_text = _cairo.cairo_show_text
cairo_show_glyphs = _cairo.cairo_show_glyphs
cairo_show_text_glyphs = _cairo.cairo_show_text_glyphs
cairo_text_path = _cairo.cairo_text_path
cairo_glyph_path = _cairo.cairo_glyph_path
cairo_text_extents = _cairo.cairo_text_extents
cairo_glyph_extents = _cairo.cairo_glyph_extents
cairo_font_extents = _cairo.cairo_font_extents
cairo_font_face_reference = _cairo.cairo_font_face_reference
cairo_font_face_destroy = _cairo.cairo_font_face_destroy
cairo_font_face_get_reference_count = _cairo.cairo_font_face_get_reference_count
cairo_font_face_status = _cairo.cairo_font_face_status
CAIRO_FONT_TYPE_TOY = _cairo.CAIRO_FONT_TYPE_TOY
CAIRO_FONT_TYPE_FT = _cairo.CAIRO_FONT_TYPE_FT
CAIRO_FONT_TYPE_WIN32 = _cairo.CAIRO_FONT_TYPE_WIN32
CAIRO_FONT_TYPE_QUARTZ = _cairo.CAIRO_FONT_TYPE_QUARTZ
CAIRO_FONT_TYPE_USER = _cairo.CAIRO_FONT_TYPE_USER
cairo_font_face_get_type = _cairo.cairo_font_face_get_type
cairo_scaled_font_create = _cairo.cairo_scaled_font_create
cairo_scaled_font_reference = _cairo.cairo_scaled_font_reference
cairo_scaled_font_destroy = _cairo.cairo_scaled_font_destroy
cairo_scaled_font_get_reference_count = _cairo.cairo_scaled_font_get_reference_count
cairo_scaled_font_status = _cairo.cairo_scaled_font_status
cairo_scaled_font_get_type = _cairo.cairo_scaled_font_get_type
cairo_scaled_font_extents = _cairo.cairo_scaled_font_extents
cairo_scaled_font_text_extents = _cairo.cairo_scaled_font_text_extents
cairo_scaled_font_glyph_extents = _cairo.cairo_scaled_font_glyph_extents
cairo_scaled_font_text_to_glyphs = _cairo.cairo_scaled_font_text_to_glyphs
cairo_scaled_font_get_font_face = _cairo.cairo_scaled_font_get_font_face
cairo_scaled_font_get_font_matrix = _cairo.cairo_scaled_font_get_font_matrix
cairo_scaled_font_get_ctm = _cairo.cairo_scaled_font_get_ctm
cairo_scaled_font_get_scale_matrix = _cairo.cairo_scaled_font_get_scale_matrix
cairo_scaled_font_get_font_options = _cairo.cairo_scaled_font_get_font_options
cairo_toy_font_face_create = _cairo.cairo_toy_font_face_create
cairo_toy_font_face_get_family = _cairo.cairo_toy_font_face_get_family
cairo_toy_font_face_get_slant = _cairo.cairo_toy_font_face_get_slant
cairo_toy_font_face_get_weight = _cairo.cairo_toy_font_face_get_weight
cairo_get_operator = _cairo.cairo_get_operator
cairo_get_source = _cairo.cairo_get_source
cairo_get_tolerance = _cairo.cairo_get_tolerance
cairo_get_antialias = _cairo.cairo_get_antialias
cairo_has_current_point = _cairo.cairo_has_current_point
cairo_get_current_point = _cairo.cairo_get_current_point
cairo_get_fill_rule = _cairo.cairo_get_fill_rule
cairo_get_line_width = _cairo.cairo_get_line_width
cairo_get_line_cap = _cairo.cairo_get_line_cap
cairo_get_line_join = _cairo.cairo_get_line_join
cairo_get_miter_limit = _cairo.cairo_get_miter_limit
cairo_get_dash_count = _cairo.cairo_get_dash_count
cairo_get_dash = _cairo.cairo_get_dash
cairo_get_matrix = _cairo.cairo_get_matrix
cairo_get_target = _cairo.cairo_get_target
cairo_get_group_target = _cairo.cairo_get_group_target
CAIRO_PATH_MOVE_TO = _cairo.CAIRO_PATH_MOVE_TO
CAIRO_PATH_LINE_TO = _cairo.CAIRO_PATH_LINE_TO
CAIRO_PATH_CURVE_TO = _cairo.CAIRO_PATH_CURVE_TO
CAIRO_PATH_CLOSE_PATH = _cairo.CAIRO_PATH_CLOSE_PATH
class _cairo_path_data_t(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, _cairo_path_data_t, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, _cairo_path_data_t, name)
    __repr__ = _swig_repr
    __swig_getmethods__["point"] = _cairo._cairo_path_data_t_point_get
    if _newclass:point = _swig_property(_cairo._cairo_path_data_t_point_get)
    __swig_getmethods__["header"] = _cairo._cairo_path_data_t_header_get
    if _newclass:header = _swig_property(_cairo._cairo_path_data_t_header_get)
    def __init__(self, *args): 
        this = _cairo.new__cairo_path_data_t(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete__cairo_path_data_t
    __del__ = lambda self : None;
_cairo_path_data_t_swigregister = _cairo._cairo_path_data_t_swigregister
_cairo_path_data_t_swigregister(_cairo_path_data_t)

class _cairo_path_data_t_point(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, _cairo_path_data_t_point, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, _cairo_path_data_t_point, name)
    __repr__ = _swig_repr
    __swig_setmethods__["x"] = _cairo._cairo_path_data_t_point_x_set
    __swig_getmethods__["x"] = _cairo._cairo_path_data_t_point_x_get
    if _newclass:x = _swig_property(_cairo._cairo_path_data_t_point_x_get, _cairo._cairo_path_data_t_point_x_set)
    __swig_setmethods__["y"] = _cairo._cairo_path_data_t_point_y_set
    __swig_getmethods__["y"] = _cairo._cairo_path_data_t_point_y_get
    if _newclass:y = _swig_property(_cairo._cairo_path_data_t_point_y_get, _cairo._cairo_path_data_t_point_y_set)
    def __init__(self, *args): 
        this = _cairo.new__cairo_path_data_t_point(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete__cairo_path_data_t_point
    __del__ = lambda self : None;
_cairo_path_data_t_point_swigregister = _cairo._cairo_path_data_t_point_swigregister
_cairo_path_data_t_point_swigregister(_cairo_path_data_t_point)

class _cairo_path_data_t_header(_object):
    __swig_setmethods__ = {}
    __setattr__ = lambda self, name, value: _swig_setattr(self, _cairo_path_data_t_header, name, value)
    __swig_getmethods__ = {}
    __getattr__ = lambda self, name: _swig_getattr(self, _cairo_path_data_t_header, name)
    __repr__ = _swig_repr
    __swig_setmethods__["type"] = _cairo._cairo_path_data_t_header_type_set
    __swig_getmethods__["type"] = _cairo._cairo_path_data_t_header_type_get
    if _newclass:type = _swig_property(_cairo._cairo_path_data_t_header_type_get, _cairo._cairo_path_data_t_header_type_set)
    __swig_setmethods__["length"] = _cairo._cairo_path_data_t_header_length_set
    __swig_getmethods__["length"] = _cairo._cairo_path_data_t_header_length_get
    if _newclass:length = _swig_property(_cairo._cairo_path_data_t_header_length_get, _cairo._cairo_path_data_t_header_length_set)
    def __init__(self, *args): 
        this = _cairo.new__cairo_path_data_t_header(*args)
        try: self.this.append(this)
        except: self.this = this
    __swig_destroy__ = _cairo.delete__cairo_path_data_t_header
    __del__ = lambda self : None;
_cairo_path_data_t_header_swigregister = _cairo._cairo_path_data_t_header_swigregister
_cairo_path_data_t_header_swigregister(_cairo_path_data_t_header)

cairo_copy_path = _cairo.cairo_copy_path
cairo_copy_path_flat = _cairo.cairo_copy_path_flat
cairo_append_path = _cairo.cairo_append_path
cairo_path_destroy = _cairo.cairo_path_destroy
cairo_status = _cairo.cairo_status
cairo_status_to_string = _cairo.cairo_status_to_string
cairo_surface_create_similar = _cairo.cairo_surface_create_similar
cairo_surface_reference = _cairo.cairo_surface_reference
cairo_surface_finish = _cairo.cairo_surface_finish
cairo_surface_destroy = _cairo.cairo_surface_destroy
cairo_surface_get_reference_count = _cairo.cairo_surface_get_reference_count
cairo_surface_status = _cairo.cairo_surface_status
CAIRO_SURFACE_TYPE_IMAGE = _cairo.CAIRO_SURFACE_TYPE_IMAGE
CAIRO_SURFACE_TYPE_PDF = _cairo.CAIRO_SURFACE_TYPE_PDF
CAIRO_SURFACE_TYPE_PS = _cairo.CAIRO_SURFACE_TYPE_PS
CAIRO_SURFACE_TYPE_XLIB = _cairo.CAIRO_SURFACE_TYPE_XLIB
CAIRO_SURFACE_TYPE_XCB = _cairo.CAIRO_SURFACE_TYPE_XCB
CAIRO_SURFACE_TYPE_GLITZ = _cairo.CAIRO_SURFACE_TYPE_GLITZ
CAIRO_SURFACE_TYPE_QUARTZ = _cairo.CAIRO_SURFACE_TYPE_QUARTZ
CAIRO_SURFACE_TYPE_WIN32 = _cairo.CAIRO_SURFACE_TYPE_WIN32
CAIRO_SURFACE_TYPE_BEOS = _cairo.CAIRO_SURFACE_TYPE_BEOS
CAIRO_SURFACE_TYPE_DIRECTFB = _cairo.CAIRO_SURFACE_TYPE_DIRECTFB
CAIRO_SURFACE_TYPE_SVG = _cairo.CAIRO_SURFACE_TYPE_SVG
CAIRO_SURFACE_TYPE_OS2 = _cairo.CAIRO_SURFACE_TYPE_OS2
CAIRO_SURFACE_TYPE_WIN32_PRINTING = _cairo.CAIRO_SURFACE_TYPE_WIN32_PRINTING
CAIRO_SURFACE_TYPE_QUARTZ_IMAGE = _cairo.CAIRO_SURFACE_TYPE_QUARTZ_IMAGE
cairo_surface_get_type = _cairo.cairo_surface_get_type
cairo_surface_get_content = _cairo.cairo_surface_get_content
cairo_surface_write_to_png = _cairo.cairo_surface_write_to_png
CAIRO_MIME_TYPE_JPEG = _cairo.CAIRO_MIME_TYPE_JPEG
CAIRO_MIME_TYPE_PNG = _cairo.CAIRO_MIME_TYPE_PNG
CAIRO_MIME_TYPE_JP2 = _cairo.CAIRO_MIME_TYPE_JP2
cairo_surface_get_font_options = _cairo.cairo_surface_get_font_options
cairo_surface_flush = _cairo.cairo_surface_flush
cairo_surface_mark_dirty = _cairo.cairo_surface_mark_dirty
cairo_surface_mark_dirty_rectangle = _cairo.cairo_surface_mark_dirty_rectangle
cairo_surface_set_device_offset = _cairo.cairo_surface_set_device_offset
cairo_surface_get_device_offset = _cairo.cairo_surface_get_device_offset
cairo_surface_set_fallback_resolution = _cairo.cairo_surface_set_fallback_resolution
cairo_surface_get_fallback_resolution = _cairo.cairo_surface_get_fallback_resolution
cairo_surface_copy_page = _cairo.cairo_surface_copy_page
cairo_surface_show_page = _cairo.cairo_surface_show_page
cairo_surface_has_show_text_glyphs = _cairo.cairo_surface_has_show_text_glyphs
CAIRO_FORMAT_ARGB32 = _cairo.CAIRO_FORMAT_ARGB32
CAIRO_FORMAT_RGB24 = _cairo.CAIRO_FORMAT_RGB24
CAIRO_FORMAT_A8 = _cairo.CAIRO_FORMAT_A8
CAIRO_FORMAT_A1 = _cairo.CAIRO_FORMAT_A1
cairo_image_surface_create = _cairo.cairo_image_surface_create
cairo_format_stride_for_width = _cairo.cairo_format_stride_for_width
cairo_image_surface_get_format = _cairo.cairo_image_surface_get_format
cairo_image_surface_get_width = _cairo.cairo_image_surface_get_width
cairo_image_surface_get_height = _cairo.cairo_image_surface_get_height
cairo_image_surface_get_stride = _cairo.cairo_image_surface_get_stride
cairo_image_surface_create_from_png = _cairo.cairo_image_surface_create_from_png
cairo_pattern_create_rgb = _cairo.cairo_pattern_create_rgb
cairo_pattern_create_rgba = _cairo.cairo_pattern_create_rgba
cairo_pattern_create_for_surface = _cairo.cairo_pattern_create_for_surface
cairo_pattern_create_linear = _cairo.cairo_pattern_create_linear
cairo_pattern_create_radial = _cairo.cairo_pattern_create_radial
cairo_pattern_reference = _cairo.cairo_pattern_reference
cairo_pattern_destroy = _cairo.cairo_pattern_destroy
cairo_pattern_get_reference_count = _cairo.cairo_pattern_get_reference_count
cairo_pattern_status = _cairo.cairo_pattern_status
CAIRO_PATTERN_TYPE_SOLID = _cairo.CAIRO_PATTERN_TYPE_SOLID
CAIRO_PATTERN_TYPE_SURFACE = _cairo.CAIRO_PATTERN_TYPE_SURFACE
CAIRO_PATTERN_TYPE_LINEAR = _cairo.CAIRO_PATTERN_TYPE_LINEAR
CAIRO_PATTERN_TYPE_RADIAL = _cairo.CAIRO_PATTERN_TYPE_RADIAL
cairo_pattern_get_type = _cairo.cairo_pattern_get_type
cairo_pattern_add_color_stop_rgb = _cairo.cairo_pattern_add_color_stop_rgb
cairo_pattern_add_color_stop_rgba = _cairo.cairo_pattern_add_color_stop_rgba
cairo_pattern_set_matrix = _cairo.cairo_pattern_set_matrix
cairo_pattern_get_matrix = _cairo.cairo_pattern_get_matrix
CAIRO_EXTEND_NONE = _cairo.CAIRO_EXTEND_NONE
CAIRO_EXTEND_REPEAT = _cairo.CAIRO_EXTEND_REPEAT
CAIRO_EXTEND_REFLECT = _cairo.CAIRO_EXTEND_REFLECT
CAIRO_EXTEND_PAD = _cairo.CAIRO_EXTEND_PAD
cairo_pattern_set_extend = _cairo.cairo_pattern_set_extend
cairo_pattern_get_extend = _cairo.cairo_pattern_get_extend
CAIRO_FILTER_FAST = _cairo.CAIRO_FILTER_FAST
CAIRO_FILTER_GOOD = _cairo.CAIRO_FILTER_GOOD
CAIRO_FILTER_BEST = _cairo.CAIRO_FILTER_BEST
CAIRO_FILTER_NEAREST = _cairo.CAIRO_FILTER_NEAREST
CAIRO_FILTER_BILINEAR = _cairo.CAIRO_FILTER_BILINEAR
CAIRO_FILTER_GAUSSIAN = _cairo.CAIRO_FILTER_GAUSSIAN
cairo_pattern_set_filter = _cairo.cairo_pattern_set_filter
cairo_pattern_get_filter = _cairo.cairo_pattern_get_filter
cairo_pattern_get_rgba = _cairo.cairo_pattern_get_rgba
cairo_pattern_get_surface = _cairo.cairo_pattern_get_surface
cairo_pattern_get_color_stop_rgba = _cairo.cairo_pattern_get_color_stop_rgba
cairo_pattern_get_color_stop_count = _cairo.cairo_pattern_get_color_stop_count
cairo_pattern_get_linear_points = _cairo.cairo_pattern_get_linear_points
cairo_pattern_get_radial_circles = _cairo.cairo_pattern_get_radial_circles
cairo_matrix_init = _cairo.cairo_matrix_init
cairo_matrix_init_identity = _cairo.cairo_matrix_init_identity
cairo_matrix_init_translate = _cairo.cairo_matrix_init_translate
cairo_matrix_init_scale = _cairo.cairo_matrix_init_scale
cairo_matrix_init_rotate = _cairo.cairo_matrix_init_rotate
cairo_matrix_translate = _cairo.cairo_matrix_translate
cairo_matrix_scale = _cairo.cairo_matrix_scale
cairo_matrix_rotate = _cairo.cairo_matrix_rotate
cairo_matrix_invert = _cairo.cairo_matrix_invert
cairo_matrix_multiply = _cairo.cairo_matrix_multiply
cairo_matrix_transform_distance = _cairo.cairo_matrix_transform_distance
cairo_matrix_transform_point = _cairo.cairo_matrix_transform_point
cairo_debug_reset_static_data = _cairo.cairo_debug_reset_static_data


