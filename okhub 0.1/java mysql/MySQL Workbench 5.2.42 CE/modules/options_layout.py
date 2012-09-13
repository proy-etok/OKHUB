layout = [ ( 'General',
    [ ('Networking', ['enable-named-pipe', 'port', 'skip-networking']),
      ('Directories', ['datadir', 'basedir', 'tmpdir']),
      ('Memory usage', ['sort_buffer_size']),
      ( 'General',
        [ 'init-file',
          'character-set-filesystem',
          'debug-sync-timeout',
          'console',
          'verbose',
          'standalone',
          'collation-server',
          'partition',
          'default-storage-engine',
          'plugin-load',
          'lower_case_file_system',
          'character-set-client-handshake',
          'character-sets-dir',
          'default-table-type',
          'port-open-timeout',
          'old',
          'plugin_dir',
          'character-set-server',
          'skip-character-set-client-handshake'])]),
  ( 'Advanced',
    [ ( 'Localization',
        [ 'default-collation',
          'default-time-zone',
          'language',
          'default-character-set']),
      ( 'General',
        [ 'open-files-limit',
          'sync_frm',
          'old-alter-table',
          'skip-external-locking',
          'gdb',
          'flush',
          'memlock',
          'tc-heuristic-recover',
          'default_week_format',
          'skip-safemalloc',
          'max_sp_recursion_depth',
          'sysdate-is-now',
          'old-style-user-limits',
          'flush_time',
          'use-symbolic-links',
          'updatable_views_with_limit',
          'skip-event-scheduler',
          'low-priority-updates',
          'max_error_count',
          'max_prepared_stmt_count',
          'enable-pstack',
          'skip-stack-trace']),
      ( 'Thread specific settings',
        [ 'slow_launch_time',
          'thread_concurrency',
          'thread_handling',
          'skip-thread-priority',
          'thread_pool_size',
          'thread_stack',
          'one-thread',
          'thread_cache_size']),
      ( 'Insert delayed settings',
        [ 'delayed_insert_timeout',
          'delayed_queue_size',
          'delayed_insert_limit',
          'max_delayed_threads']),
      ( 'Various',
        [ 'tmp_table_size',
          'lower_case_table_names',
          'transaction-isolation',
          'temp-pool',
          'max_length_for_sort_data',
          'symbolic-links',
          'query_prealloc_size',
          'max_binlog_stmt_cache_size',
          'big-tables',
          'max_write_lock_count',
          'bind-address',
          'skip-merge',
          'max_join_size',
          'group_concat_max_len',
          'max_seeks_for_key',
          'delay-key-write',
          'back_log',
          'binlog_cache_size',
          'sql-mode',
          'skip-new',
          'skip-partition',
          'key_cache_block_size',
          'core-file',
          'skip-sync-bdb-logs',
          'merge',
          'preload_buffer_size',
          'transaction_alloc_block_size',
          'join_buffer_size',
          'binlog_stmt_cache_size',
          'table_cache',
          'read_rnd_buffer_size',
          'max_heap_table_size',
          'skip-bdb',
          'chroot',
          'key_cache_division_limit',
          'max_binlog_cache_size',
          'skip-symlink',
          'new',
          'max_sort_length',
          'skip-isam',
          'ansi',
          'read_buffer_size',
          'user',
          'range_alloc_block_size',
          'pid-file',
          'query_alloc_block_size',
          'bulk_insert_buffer_size',
          'max_tmp_tables',
          'transaction_prealloc_size',
          'binlog_direct_non_transactional_updates'])]),
  ( 'MyISAM',
    [ ( 'General',
        [ 'concurrent_insert',
          'keep_files_on_create',
          'key_buffer_size',
          'skip-locking',
          'key_cache_age_threshold',
          'external-locking']),
      ( 'Fulltext search',
        [ 'ft_min_word_len',
          'ft_boolean_syntax',
          'ft_max_word_len',
          'ft_stopword_file',
          'ft_query_expansion_limit']),
      ( 'Advanced Settings',
        [ 'skip-concurrent-insert',
          'myisam-recover-options',
          'myisam_sort_buffer_size',
          'myisam_max_extra_sort_file_size',
          'myisam_use_mmap',
          'myisam_stats_method',
          'myisam-recover',
          'myisam-block-size',
          'myisam_max_sort_file_size',
          'myisam_data_pointer_size',
          'myisam_repair_threads',
          'myisam_mmap_size'])]),
  ( 'Performance',
    [ ( 'General',
        ['safe-mode', 'table_open_cache', 'table_definition_cache']),
      ( 'Query cache',
        [ 'query_cache_size',
          'query_cache_wlock_invalidate',
          'query_cache_min_res_unit',
          'replicate-same-server-id',
          'query_cache_limit',
          'query_cache_type']),
      ( 'Optimize',
        [ 'optimizer_switch',
          'optimizer_prune_level',
          'optimizer_search_depth']),
      ( 'Performance Schema',
        [ 'performance_schema_max_thread_instances',
          'performance_schema_max_file_classes',
          'performance_schema',
          'performance_schema_setup_objects_size',
          'performance_schema_max_file_instances',
          'performance_schema_max_file_handles',
          'performance_schema_setup_actors_size',
          'performance_schema_events_waits_history_size',
          'performance_schema_max_table_handles',
          'performance_schema_max_thread_classes',
          'performance_schema_max_mutex_instances',
          'performance_schema_max_rwlock_classes',
          'performance_schema_max_table_instances',
          'performance_schema_events_waits_history_long_size',
          'performance_schema_max_rwlock_instances',
          'performance_schema_max_cond_instances',
          'performance_schema_max_cond_classes',
          'performance_schema_max_mutex_classes'])]),
  ( 'Log Files',
    [ ( 'Activate Logging',
        [ 'log-isam',
          'log',
          'log-error',
          'log-slow-queries',
          'general-log',
          'log-bin']),
      ( 'Binlog Options',
        [ 'max-binlog-dump-events',
          'binlog-do-db',
          'binlog-format',
          'max_binlog_size',
          'log-bin-index',
          'binlog-row-event-max-size',
          'sync_binlog',
          'log-bin-trust-routine-creators',
          'binlog-ignore-db',
          'log-bin-trust-function-creators',
          'sporadic-binlog-dump-fail']),
      ( 'Slow query log options',
        [ 'log-slow-admin-statements',
          'log-slow-slave-statements',
          'slow_query_log_file',
          'long_query_time',
          'slow-query-log']),
      ( 'Advanced log options',
        [ 'log-short-format',
          'general_log_file',
          'log-tc',
          'min-examined-row-limit',
          'log-warnings',
          'log-tc-size',
          'log-queries-not-using-indexes',
          'expire_logs_days',
          'log-output'])]),
  ( 'Security',
    [ ( 'Security',
        [ 'local-infile',
          'skip-grant-tables',
          'des-key-file',
          'read_only',
          'secure-auth',
          'safemalloc-mem-limit',
          'safe-user-create',
          'secure-file-priv',
          'old-passwords',
          'safe-show-database',
          'allow-suspicious-udfs',
          'skip-show-database']),
      ( 'SSL',
        [ 'ssl-cipher',
          'ssl',
          'ssl-verify-server-cert',
          'ssl-capath',
          'ssl-ca',
          'skip-ssl',
          'ssl-cert',
          'ssl-key'])]),
  ( 'InnoDB',
    [ ( 'General',
        [ 'innodb_replication_delay',
          'innodb_checksums',
          'innodb_use_legacy_cardinality_algorithm',
          'innodb_autoextend_increment',
          'innodb_stats_on_metadata',
          'innodb_stats_sample_pages',
          'innodb_autoinc_lock_mode',
          'innodb_old_blocks_time',
          'innodb_use_sys_malloc',
          'innodb_table_locks',
          'innodb_spin_wait_delay',
          'ignore-builtin-innodb',
          'innodb_thread_sleep_delay',
          'innodb-status-file',
          'innodb_max_purge_lag',
          'innodb_file_format_check',
          'innodb_file_format',
          'innodb_strict_mode',
          'autocommit',
          'innodb_old_blocks_pct',
          'timed_mutexes',
          'innodb_adaptive_hash_index',
          'innodb_read_io_threads',
          'innodb_doublewrite',
          'innodb_locks_unsafe_for_binlog',
          'innodb_adaptive_flushing',
          'innodb_sync_spin_loops',
          'innodb_read_ahead_threshold',
          'innodb_change_buffering',
          'innodb_concurrency_tickets',
          'innodb_support_xa',
          'innodb_io_capacity',
          'innodb_commit_concurrency',
          'innodb_rollback_on_timeout',
          'innodb_write_io_threads']),
      ('Activate InnoDB', ['innodb']),
      ( 'Memory',
        [ 'innodb_buffer_pool_awe_mem_mb',
          'innodb_buffer_pool_size',
          'innodb_additional_mem_pool_size',
          'innodb_buffer_pool_instances']),
      ( 'Datafiles',
        [ 'innodb_file_per_table',
          'innodb_data_file_path',
          'innodb_data_home_dir']),
      ( 'Logfiles',
        [ 'innodb_log_buffer_size',
          'innodb_log_arch_dir',
          'innodb_log_archive',
          'innodb_mirrored_log_groups',
          'innodb_log_file_size',
          'innodb_flush_method',
          'innodb_log_files_in_group',
          'innodb_log_group_home_dir',
          'innodb_flush_log_at_trx_commit',
          'innodb-safe-binlog']),
      ( 'Various',
        [ 'innodb_fast_shutdown',
          'innodb_force_recovery',
          'innodb_use_native_aio',
          'innodb_thread_concurrency',
          'innodb_max_merged_io',
          'innodb_extra_dirty_writes',
          'innodb_lock_wait_timeout',
          'innodb_file_format_max',
          'innodb_purge_batch_size',
          'innodb_purge_threads',
          'innodb_file_io_threads',
          'innodb_max_dirty_pages_pct',
          'innodb_open_files',
          'innodb_thread_concurrency_timer_based'])]),
  ( 'NDB',
    [ ( 'General',
        [ 'ndb-connectstring',
          'ndb_report_thresh_binlog_mem_usage',
          'ndb-nodeid',
          'ndb_index_stat_enable',
          'ndb-mgmd-host',
          'skip-ndbcluster',
          'ndb-cluster-connection-pool',
          'ndb-wait-connected',
          'ndb_index_stat_update_freq',
          'ndb_cache_check_time',
          'ndb_log_empty_epochs',
          'ndb_extra_logging',
          'ndb_optimized_node_selection',
          'ndb_index_stat_cache_entries',
          'ndb_use_transactions',
          'ndb_force_send',
          'ndbcluster',
          'ndb-wait-setup',
          'ndb-batch-size',
          'ndb-log-update-as-write',
          'ndb_log_updated_only',
          'ndb_report_thresh_binlog_epoch_slip',
          'ndb_autoincrement_prefetch_sz'])]),
  ('Transactions', [('Misc', ['completion_type'])]),
  ( 'Networking',
    [ ('General', ['socket']),
      ('Data / Memory size', ['max_allowed_packet', 'net_buffer_length']),
      ( 'Timeout Settings',
        [ 'net_read_timeout',
          'wait_timeout',
          'net_write_timeout',
          'connect_timeout',
          'interactive_timeout']),
      ( 'Advanced',
        [ 'net_retry_count',
          'max_connections',
          'max_user_connections',
          'max_connect_errors']),
      ('Naming', ['skip-name-resolve', 'skip-host-cache'])]),
  ( 'Replication',
    [ ('General', ['init-rpl-role', 'server-id']),
      ( 'Master',
        [ 'show-slave-auth-info',
          'auto_increment_increment',
          'auto_increment_offset',
          'master-bind']),
      ( 'General slave',
        [ 'slave-net-timeout',
          'disconnect-slave-event-count',
          'abort-slave-event-count',
          'slave-skip-errors',
          'slave_allow_batching',
          'skip-slave-start',
          'slave_transaction_retries',
          'log-slave-updates',
          'slave-load-tmpdir',
          'slave_compressed_protocol']),
      ( 'Slave replication objects',
        [ 'replicate-ignore-db',
          'replicate-wild-do-table',
          'replicate-ignore-table',
          'replicate-do-db',
          'replicate-do-table',
          'replicate-rewrite-db',
          'replicate-wild-ignore-table']),
      ( 'Slave Identification',
        ['report-port', 'report-user', 'report-host', 'report-password']),
      ( 'Relay Log',
        [ 'max_relay_log_size',
          'relay-log-index',
          'relay_log_info_file',
          'relay_log_purge',
          'relay_log_space_limit',
          'relay-log']),
      ( 'Slave default connection values',
        [ 'master-ssl-capath',
          'master-ssl-ca',
          'master-ssl-cert',
          'master-port',
          'master-ssl-cipher',
          'master-host',
          'master-retry-count',
          'master-connect-retry',
          'master-user',
          'master-ssl-key',
          'master-ssl',
          'master-password',
          'master-info-file'])]),
  ( 'Misc',
    [ ( 'Unsorted',
        [ 'exit-info',
          'sync_relay_log',
          'mutex-deadlock-detector',
          'enable-locking',
          'backup_elevation',
          'sync_master_info',
          'restore_precheck',
          'multi_range_count',
          'large-pages',
          'sync_relay_log_info',
          'restore_elevation',
          'backup_progress_log_file',
          'init_connect',
          'sql-bin-update-same',
          'log-long-format',
          'backup_progress_log',
          'backup_history_log',
          'debug',
          'log-backup-output',
          'lc-messages',
          'server-id-bits',
          'div_precision_increment',
          'engine-condition-pushdown',
          'restore_disables_events',
          'relay_log_recovery',
          'old-protocol',
          'join_cache_level',
          'init_slave',
          'table_lock_wait_timeout',
          'sync-bdb-logs',
          'lc-messages-dir',
          'backup_history_log_file',
          'warnings',
          'secure-backup-file-priv',
          'lock_wait_timeout',
          'slave_type_conversions',
          'backupdir',
          'bootstrap',
          'time_zone',
          'event-scheduler',
          'record_buffer',
          'mysql-backup',
          'optimizer_join_cache_level',
          'set-variable'])])]