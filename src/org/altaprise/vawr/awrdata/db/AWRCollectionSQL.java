package org.altaprise.vawr.awrdata.db;

public class AWRCollectionSQL {


    public static String getDbIdSQL() {
        String dbIdSQL =
            " SELECT  dbid, db_name FROM dba_hist_database_instance ";
        return dbIdSQL;
    }

    public static String getMainAWRMetricsSQL(long dbID, long snapIdMin,
                                              long snapIdMax) {
        String sql =
            "select snap_id \"snap\",num_interval \"dur_m\", end_time \"end\",inst \"inst\", " +
            " max(decode(metric_name,'Host CPU Utilization (%)',                                  average,null)) \"os_cpu\", " +
            " max(decode(metric_name,'Host CPU Utilization (%)',                                    maxval,null)) \"os_cpu_max\", " +
            " max(decode(metric_name,'Host CPU Utilization (%)',                                    STANDARD_DEVIATION,null)) \"os_cpu_sd\", " +
            " max(decode(metric_name,'Database Wait Time Ratio',                   round(average,1),null)) \"db_wait_ratio\", " +
            " max(decode(metric_name,'Database CPU Time Ratio',                   round(average,1),null)) \"db_cpu_ratio\", " +
            " max(decode(metric_name,'CPU Usage Per Sec',                   round(average/100,3),null)) \"cpu_per_s\", " +
            " max(decode(metric_name,'CPU Usage Per Sec',                   round(STANDARD_DEVIATION/100,3),null)) \"cpu_per_s_sd\", " +
            " max(decode(metric_name,'Host CPU Usage Per Sec',                   round(average/100,3),null)) \"h_cpu_per_s\", " +
            " max(decode(metric_name,'Host CPU Usage Per Sec',                   round(STANDARD_DEVIATION/100,3),null)) \"h_cpu_per_s_sd\", " +
            " max(decode(metric_name,'Average Active Sessions',                   average,null)) \"aas\", " +
            " max(decode(metric_name,'Average Active Sessions',                   STANDARD_DEVIATION,null)) \"aas_sd\", " +
            " max(decode(metric_name,'Average Active Sessions',                   maxval,null)) \"aas_max\", " +
            " max(decode(metric_name,'Database Time Per Sec',                                 average,null)) \"db_time\", " +
            " max(decode(metric_name,'Database Time Per Sec',                                 STANDARD_DEVIATION,null)) \"db_time_sd\", " +
            " max(decode(metric_name,'SQL Service Response Time',                   average,null)) \"sql_res_t_cs\", " +
            " max(decode(metric_name,'Background Time Per Sec',                   average,null)) \"bkgd_t_per_s\", " +
            " max(decode(metric_name,'Logons Per Sec',                            average,null)) \"logons_s\", " +
            " max(decode(metric_name,'Current Logons Count',                      average,null)) \"logons_total\", " +
            " max(decode(metric_name,'Executions Per Sec',                        average,null)) \"exec_s\", " +
            " max(decode(metric_name,'Hard Parse Count Per Sec',                  average,null)) \"hard_p_s\", " +
            " max(decode(metric_name,'Logical Reads Per Sec',                     average,null)) \"l_reads_s\", " +
            " max(decode(metric_name,'User Commits Per Sec',                      average,null)) \"commits_s\", " +
            " max(decode(metric_name,'Physical Read Total Bytes Per Sec',         round((average)/1024/1024,1),null)) \"read_mb_s\", " +
            " max(decode(metric_name,'Physical Read Total Bytes Per Sec',         round((maxval)/1024/1024,1),null)) \"read_mb_s_max\", " +
            " max(decode(metric_name,'Physical Read Total IO Requests Per Sec',   average,null)) \"read_iops\", " +
            " max(decode(metric_name,'Physical Read Total IO Requests Per Sec',   maxval,null)) \"read_iops_max\", " +
            " max(decode(metric_name,'Physical Reads Per Sec',                        average,null)) \"read_bks\", " +
            " max(decode(metric_name,'Physical Reads Direct Per Sec',                         average,null)) \"read_bks_direct\", " +
            " max(decode(metric_name,'Physical Write Total Bytes Per Sec',        round((average)/1024/1024,1),null)) \"write_mb_s\", " +
            " max(decode(metric_name,'Physical Write Total Bytes Per Sec',        round((maxval)/1024/1024,1),null)) \"write_mb_s_max\", " +
            " max(decode(metric_name,'Physical Write Total IO Requests Per Sec',  average,null)) \"write_iops\", " +
            " max(decode(metric_name,'Physical Write Total IO Requests Per Sec',  maxval,null)) \"write_iops_max\", " +
            " max(decode(metric_name,'Physical Writes Per Sec',                       average,null)) \"write_bks\", " +
            " max(decode(metric_name,'Physical Writes Direct Per Sec',                        average,null)) \"write_bks_direct\", " +
            " max(decode(metric_name,'Redo Generated Per Sec',                    round((average)/1024/1024,1),null)) \"redo_mb_s\", " +
            " max(decode(metric_name,'DB Block Gets Per Sec',                     average,null)) \"db_block_gets_s\", " +
            " max(decode(metric_name,'DB Block Changes Per Sec',                   average,null)) \"db_block_changes_s\", " +
            " max(decode(metric_name,'GC CR Block Received Per Second',            average,null)) \"gc_cr_rec_s\", " +
            " max(decode(metric_name,'GC Current Block Received Per Second',       average,null)) \"gc_cu_rec_s\", " +
            " max(decode(metric_name,'Global Cache Average CR Get Time',           average,null)) \"gc_cr_get_cs\", " +
            " max(decode(metric_name,'Global Cache Average Current Get Time',      average,null)) \"gc_cu_get_cs\", " +
            " max(decode(metric_name,'Global Cache Blocks Corrupted',              average,null)) \"gc_bk_corrupted\", " +
            " max(decode(metric_name,'Global Cache Blocks Lost',                   average,null)) \"gc_bk_lost\", " +
            " max(decode(metric_name,'Active Parallel Sessions',                   average,null)) \"px_sess\", " +
            " max(decode(metric_name,'Active Serial Sessions',                     average,null)) \"se_sess\", " +
            " max(decode(metric_name,'Average Synchronous Single-Block Read Latency', average,null)) \"s_blk_r_lat\", " +
            " max(decode(metric_name,'Cell Physical IO Interconnect Bytes',         round((average)/1024/1024,1),null)) \"cell_io_int_mb\", " +
            " max(decode(metric_name,'Cell Physical IO Interconnect Bytes',         round((maxval)/1024/1024,1),null)) \"cell_io_int_mb_max\" " +
            "   from( " +
            "   select  snap_id,num_interval,to_char(end_time,'YY/MM/DD HH24:MI') end_time,instance_number inst,metric_name,round(average,1) average, " +
            "   round(maxval,1) maxval,round(standard_deviation,1) standard_deviation " +
            "  from dba_hist_sysmetric_summary " + " where dbid = " + dbID +
            "  and snap_id between " + snapIdMin + "  and " + snapIdMax +
            "   and metric_name in ('Host CPU Utilization (%)','CPU Usage Per Sec','Host CPU Usage Per Sec','Average Active Sessions','Database Time Per Sec', " +
            "  'Executions Per Sec','Hard Parse Count Per Sec','Logical Reads Per Sec','Logons Per Sec', " +
            "  'Physical Read Total Bytes Per Sec','Physical Read Total IO Requests Per Sec','Physical Reads Per Sec','Physical Write Total Bytes Per Sec', " +
            "  'Redo Generated Per Sec','User Commits Per Sec','Current Logons Count','DB Block Gets Per Sec','DB Block Changes Per Sec', " +
            "  'Database Wait Time Ratio','Database CPU Time Ratio','SQL Service Response Time','Background Time Per Sec', " +
            "  'Physical Write Total IO Requests Per Sec','Physical Writes Per Sec','Physical Writes Direct Per Sec','Physical Writes Direct Lobs Per Sec', " +
            "  'Physical Reads Direct Per Sec','Physical Reads Direct Lobs Per Sec', " +
            "  'GC CR Block Received Per Second','GC Current Block Received Per Second','Global Cache Average CR Get Time','Global Cache Average Current Get Time', " +
            "  'Global Cache Blocks Corrupted','Global Cache Blocks Lost', " +
            "  'Active Parallel Sessions','Active Serial Sessions','Average Synchronous Single-Block Read Latency','Cell Physical IO Interconnect Bytes' " +
            "     ) " + "  ) " +
            "  group by snap_id,num_interval, end_time,inst " +
            "  order by snap_id, end_time,inst ";

        return sql;
    }

    public static String getMinSnapIdSQL(long dbId, long numDays) {
        String minSnapIdSQL =
            " SELECT min(snap_id) - 1 snap_min1 " + " FROM dba_hist_snapshot " +
            " WHERE dbid = " + dbId + " and begin_interval_time > ( " +
            " SELECT max(begin_interval_time) - " + numDays +
            " FROM dba_hist_snapshot  " + " where dbid = " + dbId + " )";

        return minSnapIdSQL;
    }


    public static String getMaxSnapIdSQL(long dbId) {
        String maxSnapIdSQL =
            " SELECT max(snap_id) snap_max1 " + " FROM dba_hist_snapshot " +
            " WHERE dbid = " + dbId;

        return maxSnapIdSQL;
    }

    public static String getAllSnapIdsAndTimesSQL(long dbId) {
        String allSnaps =
            "SELECT snap_id, startup_time, begin_interval_time, end_interval_time " +
            " FROM dba_hist_snapshot " + " WHERE dbid = " + dbId;

        return allSnaps;
    }
}
