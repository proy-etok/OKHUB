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

import random

from wb import DefineModule
from workbench import db_driver
from workbench.utils import find_object_with_name
from workbench.exceptions import NotConnectedError

import grt
import SQLEXT as constant


ModuleInfo = DefineModule(name= "DbGenericRE", author= "Oracle Corp.", version="1.0")

######################### Non exposed functions and variables #################

def check_interruption():
    if grt.query_status():
        raise grt.UserInterrupt()

_connections = {}

def get_connection(connection_object):
    if connection_object.__id__ in _connections:
        return _connections[connection_object.__id__]["connection"]
    else:
        raise NotConnectedError("No open connection to %s" % connection_object.hostIdentifier)

# Note: try to avoid executing SQL code within this module
def execute_query(connection_object, query, *args, **kwargs):
    """Retrieves a connection and executes the given query returning a cursor to iterate over results.

    The remaining positional and keyword arguments are passed with the query to the execute function
    """
    return get_connection(connection_object).cursor().execute(query, *args, **kwargs)

###############################################################################

@ModuleInfo.export(grt.STRING, grt.STRING)
def quoteIdentifier(name):
    return '"%s"' % name.replace('"', '\"')

@ModuleInfo.export(grt.STRING, grt.classes.GrtNamedObject)
def fullyQualifiedObjectName(obj):
    owner = obj.owner
    if owner and isinstance(owner, grt.classes.db_Schema):
        if owner.owner and isinstance(owner.owner, grt.classes.db_Catalog):
            return quoteIdentifier(owner.owner.name)+"."+quoteIdentifier(owner.name)+"."+quoteIdentifier(obj.name)
    elif owner and isinstance(owner, grt.classes.db_Catalog):
        return quoteIdentifier(owner.name)+"."+quoteIdentifier(obj.name)
    return quoteIdentifier(obj.name)

#########  Connection related functions #########

@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.STRING)
def connect(connection, password):
    '''Establishes a connection to the server and stores the connection object in the connections pool.

    It first looks for a connection with the given connection parameters in the connections pool to
    reuse existent connections. If such connection is found it queries the server to ensure that the
    connection is alive and reestablishes it if is dead. If no suitable connection is found in the
    connections pool, a new one is created and stored in the pool.

    Parameters:
    ===========
        connection:  an object of the class db_mgmt_Connection storing the parameters
                     for the connection.
        password:    a string with the password to use for the connection.
    '''
    try:
        con = get_connection(connection)
        try:
            if not con.cursor().execute('SELECT 1'):
                raise Exception("connection error")
        except Exception, exc:
            grt.send_info("Connection to %s apparently lost, reconnecting..." % connection.hostIdentifier)
            raise NotConnectedError("Connection error")
    except NotConnectedError, exc:
        grt.send_info("Connecting to %s..." % connection.hostIdentifier)
        con = db_driver.connect(connection, password)
        if not con:
            grt.send_error('Connection failed', str(exc))
            raise
        grt.send_info("Connected")
        _connections[connection.__id__] = {"connection": con}
    return 1


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection)
def disconnect(connection):
    if connection.__id__ in _connections:
        del _connections[connection.__id__]  # pyodbc cursors are automatically closed when deleted
    return 0


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection)
def isConnected(connection):
    return 1 if connection.__id__ in _connections else 0


#########  Exploratory functions (these only return useful info without reverse engineering) #########

@ModuleInfo.export(grt.STRING)
def getTargetDBMSName():
    return 'Generic'

@ModuleInfo.export(grt.classes.GrtVersion, grt.classes.db_mgmt_Connection)
def getServerVersion(connection):
    """Returns a GrtVersion instance containing information about the server version."""
    
    # Note: Not implemented. This returns a predefined default server version for compatibility sake.
    version = grt.classes.GrtVersion()
    version.majorNumber, version.minorNumber, version.releaseNumber, version.buildNumber = 1, 0, 0, 0
    return version

@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection)
def getCatalogNames(connection):
    """Returns a list of the available catalogs.

    [NOTE] This will in fact return the name of the database we are connected to.
    """
    return list(set(row[0] for row in get_connection(connection).cursor().tables())) 


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING)
def getSchemaNames(connection, catalog_name):
    """Returns a list of schemata for the given connection object."""

    return list(set(row[1] for row in get_connection(connection).cursor().tables(catalog=catalog_name)))


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getTableNames(connection, catalog_name, schema_name):
    return list(set(row.table_name for row in 
                get_connection(connection).cursor().tables(catalog=catalog_name, schema=schema_name) if row.table_type=='TABLE'))


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getViewNames(connection, catalog_name, schema_name):
    return list(set(row.table_name for row in 
                get_connection(connection).cursor().tables(catalog=catalog_name, schema=schema_name) if row.table_type=='VIEW'))


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getTriggerNames(connection, catalog_name, schema_name):
    return []


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getProcedureNames(connection, catalog_name, schema_name):
    return list(set(row.procedure_name for row in 
                get_connection(connection).cursor().procedures(catalog=catalog_name, schema=schema_name) ))


@ModuleInfo.export(grt.LIST, grt.classes.db_mgmt_Connection, grt.STRING, grt.STRING)
def getFunctionNames(connection, catalog_name, schema_name):
    return []


#########  Reverse Engineering functions #########

@ModuleInfo.export(grt.classes.db_Catalog, grt.classes.db_mgmt_Connection, grt.STRING, (grt.LIST, grt.STRING), grt.DICT)
def reverseEngineer(connection, catalog_name, schemata_list, context):
    grt.send_progress(0, "Reverse engineering catalog information")
    check_interruption()
    catalog = reverseEngineerCatalog(connection, catalog_name)

    # calculate total workload 1st
    grt.send_progress(0.1, 'Preparing...')
    table_count_per_schema = {}
    view_count_per_schema = {}
    routine_count_per_schema = {}
    trigger_count_per_schema = {}
    total_count_per_schema = {}

    get_tables = context.get("reverseEngineerTables", True)
    get_triggers = context.get("reverseEngineerTriggers", True)
    get_views = context.get("reverseEngineerViews", True)
    get_routines = context.get("reverseEngineerRoutines", True)

    # 10% of the progress is for preparation
    total = 1e-10  # total should not be zero to avoid DivisionByZero exceptions
    i = 0.0
    accumulated_progress = 0.1
    for schema_name in schemata_list:
        check_interruption()
        table_count_per_schema[schema_name] = len(getTableNames(connection, catalog_name, schema_name)) if get_tables else 0
        view_count_per_schema[schema_name] = len(getViewNames(connection, catalog_name, schema_name)) if get_views else 0
        check_interruption()
        routine_count_per_schema[schema_name] = len(getProcedureNames(connection, catalog_name, schema_name)) + len(getFunctionNames(connection, catalog_name, schema_name)) if get_routines else 0
        trigger_count_per_schema[schema_name] = len(getTriggerNames(connection, catalog_name, schema_name)) if get_triggers else 0

        total_count_per_schema[schema_name] = (table_count_per_schema[schema_name] + view_count_per_schema[schema_name] +
                                               routine_count_per_schema[schema_name] + trigger_count_per_schema[schema_name] + 1e-10)
        total += total_count_per_schema[schema_name]

        grt.send_progress(accumulated_progress + 0.1 * (i / (len(schemata_list) + 1e-10) ), "Gathered stats for %s" % schema_name)
        i += 1.0

    # Now take 60% in the first pass of reverse engineering:
    accumulated_progress = 0.2
    for schema_name in schemata_list:
        schema_progress_share = 0.6 * (total_count_per_schema.get(schema_name, 0.0) / total)
        schema = find_object_with_name(catalog.schemata, schema_name) 

        if schema:
            # Reverse engineer tables:
            step_progress_share = schema_progress_share * (table_count_per_schema[schema_name] / (total_count_per_schema[schema_name] + 1e-10))
            if get_tables:
                check_interruption()
                grt.send_info('Reverse engineering tables from %s' % schema_name)
                grt.begin_progress_step(accumulated_progress, accumulated_progress + step_progress_share)
                reverseEngineerTables(connection, schema)
                grt.end_progress_step()
    
            accumulated_progress += step_progress_share
            grt.send_progress(accumulated_progress, 'First pass of table reverse engineering for schema %s completed!' % schema_name)
    
            # Reverse engineer views:
            step_progress_share = schema_progress_share * (view_count_per_schema[schema_name] / (total_count_per_schema[schema_name] + 1e-10))
            if get_views:
                check_interruption()
                grt.send_info('Reverse engineering views from %s' % schema_name)
                grt.begin_progress_step(accumulated_progress, accumulated_progress + step_progress_share)
                reverseEngineerViews(connection, schema)
                grt.end_progress_step()
    
            accumulated_progress += step_progress_share
            grt.send_progress(accumulated_progress, 'Reverse engineering of views for schema %s completed!' % schema_name)
    
            # Reverse engineer routines:
            step_progress_share = schema_progress_share * (routine_count_per_schema[schema_name] / (total_count_per_schema[schema_name] + 1e-10))
            if get_routines:
                check_interruption()
                grt.send_info('Reverse engineering routines from %s' % schema_name)
                grt.begin_progress_step(accumulated_progress, accumulated_progress + step_progress_share)
                grt.begin_progress_step(0.0, 0.5)
                reverseEngineerProcedures(connection, schema)
                check_interruption()
                grt.end_progress_step()
                grt.begin_progress_step(0.5, 1.0)
                reverseEngineerFunctions(connection, schema)
                grt.end_progress_step()
                grt.end_progress_step()
    
            accumulated_progress += step_progress_share
            grt.send_progress(accumulated_progress, 'Reverse engineering of routines for schema %s completed!' % schema_name)
    
            # Reverse engineer triggers:
            step_progress_share = schema_progress_share * (trigger_count_per_schema[schema_name] / (total_count_per_schema[schema_name] + 1e-10))
            if get_triggers:
                check_interruption()
                grt.send_info('Reverse engineering triggers from %s' % schema_name)
                grt.begin_progress_step(accumulated_progress, accumulated_progress + step_progress_share)
                reverseEngineerTriggers(connection, schema)
                grt.end_progress_step()
    
            accumulated_progress = 0.8
            grt.send_progress(accumulated_progress, 'Reverse engineering of triggers for schema %s completed!' % schema_name)
        else:  # No schema with the given name was found
            grt.send_warning('The schema %s was not found in the catalog %s. Skipping it.' % (schema_name, catalog_name) )
            
    # Now the second pass for reverse engineering tables:
    if get_tables:
        total_tables = sum(table_count_per_schema[schema.name] for schema in catalog.schemata)
        for schema in catalog.schemata:
            check_interruption()
            step_progress_share = 0.2 * (table_count_per_schema[schema.name] / (total_tables + 1e-10))
            grt.send_info('Reverse engineering foreign keys for tables in schema %s' % schema.name)
            grt.begin_progress_step(accumulated_progress, accumulated_progress + step_progress_share)
            reverseEngineerTables(connection, schema)
            grt.end_progress_step()
    
            accumulated_progress += step_progress_share
            grt.send_progress(accumulated_progress, 'Second pass of table reverse engineering for schema %s completed!' % schema_name)
        

    grt.send_progress(1.0, 'Reverse engineering completed!')
    return catalog


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Catalog)
def reverseEngineerUserDatatypes(connection, catalog):
    catalog.simpleDatatypes.remove_all()
    for type_row in get_connection(connection).cursor().getTypeInfo(catalog=catalog.name):  # FIXME: there are duplicated names in this resultset
        simple_datatype = grt.classes.db_SimpleDatatype()
        simple_datatype.name = type_row[0]
        simple_datatype.characterMaximumLength = simple_datatype.characterOctetLength = simple_datatype.numericPrecision = simple_datatype.dateTimePrecision = type_row[2] if isinstance(type_row[2], int) else -1
        if isinstance(type_row[17], int):
            simple_datatype.numericPrecisionRadix = type_row[17]
        if isinstance(type_row[14], int):
            simple_datatype.numericScale = type_row[14]
        parameter_format_type_mapping = { 0: 0, # none
                                          1: 2, # [(n)]
                                          2: 6, # [(m[, n])]
                                        }
        if type_row[5] is not None:  # parameter format
            simple_datatype.parameterFormatType = parameter_format_type_mapping.get(len(type_row[5].split(',')), 0)
        else:
            simple_datatype.parameterFormatType = 0

        simple_datatype.needsQuotes = type_row[3] in ["N'", "'"]
        
        catalog.simpleDatatypes.append(simple_datatype)
        

@ModuleInfo.export(grt.classes.db_Catalog, grt.classes.db_mgmt_Connection, grt.STRING)
def reverseEngineerCatalog(connection, catalog_name):
    catalog = grt.classes.db_Catalog()
    catalog.name = catalog_name
    
    reverseEngineerUserDatatypes(connection, catalog)

    schemata_names = getSchemaNames(connection, catalog_name) or ['']
    catalog.schemata.remove_all()
    for schema_name in schemata_names:
        schema = grt.classes.db_Schema()
        schema.name = schema_name
        schema.owner = catalog
        catalog.schemata.append(schema)
    return catalog

@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Schema)
def reverseEngineerTables(connection, schema):
    # Since there are some reverse engineering stages that requires all table names and table columns
    # in the database to be set, these should be done after a first pass that rev engs their requirements
    progress_flags = _connections[connection.__id__].setdefault('_rev_eng_progress_flags', [])
    is_first_pass = not ('%s_tables_first_pass' % schema.name) in progress_flags

    if is_first_pass:
        catalog = schema.owner
        schema.tables.remove_all()
        table_names = getTableNames(connection, catalog.name, schema.name)
        total = len(table_names) + 1e-10
        i = 0.0
        for table_name in table_names:
            grt.send_progress(i / total, 'Retrieving table %s.%s...' % (schema.name, table_name))
            table = grt.classes.db_Table()
            table.name = table_name
            schema.tables.append(table)
            table.owner = schema
    
            reverseEngineerTableColumns(connection, table)
            reverseEngineerTablePKAndIndices(connection, table)
    
            i += 1.0
        progress_flags.append('%s_tables_first_pass' % schema.name)
    else:  # Second pass
        i = 0.0
        total = len(schema.tables) + 1e-10
        for table in schema.tables:
            reverseEngineerTableFKs(connection, table)
            grt.send_progress(i / total, 'Reverse engineering of foreign keys in table %s.%s completed' % (schema.name, table.name))
            i += 1.0

    return 0

@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Table)
def reverseEngineerTableColumns(connection, table):
    schema = table.owner
    catalog = schema.owner

    simple_datatypes_list = [ datatype.name.upper() for datatype in catalog.simpleDatatypes ]
    user_datatypes_list   = [ datatype.name.upper() for datatype in catalog.userDatatypes ]

    odbc_datatypes = dict( (dtype.data_type, dtype.type_name) for dtype in get_connection(connection).cursor().getTypeInfo() )

    table_columns = get_connection(connection).cursor().columns(catalog=catalog.name, schema=schema.name, table=table.name)
    for column_info in table_columns:
        column = grt.classes.db_Column()
        column.name = column_info[3]  # column_name
        column.isNotNull = column_info[17] == 'YES'  # is_nullable
        column.length = column_info[6]  # column_size
        column.scale = column_info[8]  # decimal_digits
        column.precision = column_info[6]  # column_size

        datatype = None
        try:
            type_name = odbc_datatypes[column_info[4]].upper()  # data_type
            datatype = simple_datatypes_list.index(type_name)
        except (KeyError, ValueError):
            try:
                user_datatype = catalog.userDatatypes[user_datatypes_list.index(type_name)]
            except (ValueError, TypeError, NameError):
                user_datatype = None
                datatype = simple_datatypes_list.index('VARCHAR')
                column.length = 255
                msg = 'Column datatype "%s" for column "%s" in table "%s.%s" reverse engineered as VARCHAR(255)' % (type_name, column.name, schema.name, table.name)
                grt.send_warning('Generic reverseEngineerTableColumns ' + msg)
            else:
                datatype = None
                column.userType = user_datatype

        if isinstance(datatype, int):
            column.simpleType = catalog.simpleDatatypes[datatype]

        table.addColumn(column)

    return 0


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Table)
def reverseEngineerTablePKAndIndices(connection, table):
    """Reverse engineers the primary key(s) for the given table."""

    schema = table.owner
    catalog = schema.owner


    if len(table.columns) == 0:  # Table must have columns reverse engineered before we can rev eng its primary key(s)
        grt.send_error('Migration: reverseEngineerTablePKAndIndices: Reverse engineer of table %s was attempted but the table has no columns attribute' % table.name)
        return 1
    
    # Find the index name associated with the PK:
    pk_index_row = get_connection(connection).cursor().primaryKeys(catalog=catalog.name, schema=schema.name, table=table.name).fetchone()
    pk_index_name = pk_index_row.pk_name if pk_index_row else ''

    indices_dict = {}  # Map the indices names to their respective columns:
    for row in get_connection(connection).cursor().statistics(catalog=catalog.name, schema=schema.name, table=table.name):
        if row.type == constant.SQL_TABLE_STAT:  # this entry is not an index
            continue
        indices_dict.setdefault(row.index_name, []).append(row)
    
    for index_name, row_list in indices_dict.iteritems():
        index = grt.classes.db_Index()
        index.name = index_name
        index.isPrimary = 1 if index_name == pk_index_name else 0
        index.unique = not row_list[0].non_unique
        index.indexType = 'UNIQUE' if index.unique else 'INDEX'
#        index.hasFilter = False  # TODO: Find out if there's a way to determine this

        # Get the columns for the index:
        for row in sorted(row_list, key=lambda elem: elem[7]):  # Sorted by ordinal_position
            column = find_object_with_name(table.columns, row.column_name)
            if column:
                index_column = grt.classes.db_IndexColumn()
                index_column.name = index_name + '.' + row.column_name
                index_column.referencedColumn = column
                index.columns.append(index_column)
        table.addIndex(index)

        if index.isPrimary:
            table.primaryKey = index

    return 0


#@# don't export, remove reundant params
@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Table)
def reverseEngineerTableFKs(connection, table):
    """Reverse engineers the foreign keys for the given table."""

    def get_action(value):
        if value is None:
            return ''
        elif value == constant.SQL_CASCADE:
            return 'CASCADE'
        elif value == constant.SQL_RESTRICT:
            return 'RESTRICT'
        elif value == constant.SQL_SET_NULL:
            return 'SET NULL'
        elif value == constant.SQL_NO_ACTION:
            return 'NO ACTION'
        elif value == constant.SQL_SET_DEFAULT:
            return 'SET DEFAULT'
        else:
            return ''
        
    def process_fk(catalog, table, fk_name, fk_rows):
        foreign_key = grt.classes.db_ForeignKey()
        foreign_key.name = fk_name
        foreign_key.owner = table
        foreign_key.deleteRule = get_action(fk_rows[0].delete_rule)
        foreign_key.updateRule = get_action(fk_rows[0].update_rule)
        foreign_key.modelOnly = 0
        
        # Find the referenced table:
        referenced_schema = find_object_with_name(catalog.schemata, fk_rows[0].pktable_schem) if fk_rows[0].pktable_schem else schema
        foreign_key.referencedTable = find_object_with_name(referenced_schema.tables, fk_rows[0].pktable_name) if fk_rows[0].pktable_name else table
        
        for fk_row in fk_rows:
            column = find_object_with_name(table.columns, fk_row.fkcolumn_name)
            if not column:
                grt.send_error('Migration: reverseEngineerTableFKs: Column "%s" not found in table "%s"' % (fk_row.fkcolumn_name, table.name) )
                continue

            ref_column = find_object_with_name(foreign_key.referencedTable.columns, fk_row.pkcolumn_name)
            if not ref_column:
                grt.send_error('Migration: reverseEngineerTableFKs: Column "%s" not found in table "%s"' % (fk_row.pkcolumn_name, foreign_key.referencedTable.name) )
                continue
            
            foreign_key.columns.append(column)
            foreign_key.referencedColumns.append(ref_column)
        table.foreignKeys.append(foreign_key)
            

    if len(table.columns) == 0:
        grt.send_error('Migration: reverseEngineerTableFKs: Reverse engineer of table %s was attempted but the table has no columns attribute' % table.name)
        return 1    # Table must have columns reverse engineered before we can rev eng its indices

    catalog = table.owner.owner
    schema = table.owner
    table.foreignKeys.remove_all()
    fk_dict = {}  # Map the foreign key names to their respective columns:
    for row in get_connection(connection).cursor().foreignKeys(foreignSchema=schema.name, foreignTable=table.name):
        fk_dict.setdefault(row.fk_name, []).append(row)

    for fk_name, fk_columns in fk_dict.iteritems():
        if not fk_name:  # If there are unnamed fks we might have several fks merged, need to separate them
            # Partition the list based on key_seq so that if the key_seq list is, for instance, [1, 2, 3, 1, 2, 1]
            # we can have [ [1, 2, 3], [1, 2], [1] ]
            indices = [idx for idx, item in enumerate(fk_columns) if item.key_seq == 1]
            slices = [fk_columns[i:j] for i, j in zip(indices, indices+[None])]
            random_names = ['FK_generated_%06d' % id for id in random.sample(range(1000000), len(slices))] # Random names for each fk
            for slice, random_name in zip(slices, random_names):
                if slice:
                    process_fk(catalog, table, random_name, slice)
        else:
            process_fk(catalog, table, fk_name, fk_columns)
    return 0


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Schema)
def reverseEngineerViews(connection, schema):
    for view_name in getViewNames(connection, schema.owner.name, schema.name):
        grt.send_warning('Migration: reverseEngineerViews: Cannot reverse engineer view "%s"' % view_name)
    return 0


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Schema)
def reverseEngineerProcedures(connection, schema):
    # Unfortunately it seems that there's no way to get the SQL definition of a store procedure/function with ODBC
    for procedure_name in getProcedureNames(connection, schema.owner.name, schema.name):
        grt.send_warning('Migration: reverseEngineerProcedures: Cannot reverse engineer procedure "%s"' % procedure_name)
    return 0

@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Schema)
def reverseEngineerTriggers(connection, schema):
    # Unfortunately it seems that there's no way to get the SQL definition of a trigger with ODBC
    for trigger_name in getTriggerNames(connection, schema.owner.name, schema.name):
        grt.send_warning('Migration: reverseEngineerTriggers: Cannot reverse engineer trigger "%s"' % trigger_name)
    return 0


@ModuleInfo.export(grt.INT, grt.classes.db_mgmt_Connection, grt.classes.db_Schema)
def reverseEngineerFunctions(connection, schema):
    # Unfortunately it seems that there's no way to get the SQL definition of a store procedure/function with ODBC
    return 0


@ModuleInfo.export(grt.classes.db_mgmt_Rdbms)
def initializeDBMSInfo():
    rdbms = grt.unserialize(ModuleInfo.moduleDataDirectory + '/generic_rdbms_info.xml')
    grt.root.wb.rdbmsMgmt.rdbms.append(rdbms)
    return rdbms


@ModuleInfo.export((grt.LIST, grt.STRING))
def getDataSourceNames():
    result = grt.List(grt.STRING)
    import pyodbc
    sources = pyodbc.dataSources()
    for key, value in sources.items():
        result.append("%s|%s (%s)" % (key, key, value))
    return result
    
