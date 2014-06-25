package org.altaprise.vawr.awrdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class AWRMetrics {

    private static AWRMetrics theInstance = null;
    private static LinkedHashMap _awrMetrics = new LinkedHashMap();
    
    private AWRMetrics() {
        //don't allow anyone to instantiate.
        init();
    }
    
    public static AWRMetrics getInstance() {
       if(theInstance == null) {
          theInstance = new AWRMetrics();
       }
       return theInstance;
    }

    public static ArrayList<String> getMetricNames() {
        ArrayList<String> ret = new ArrayList<String>();
        for (Object key : _awrMetrics.keySet()) {
            ret.add((String)key);
        }
        return ret;
    }
    
    /*
    Metric Description	AWR Field
    'Host CPU Utilization (%)'	os_cpu
    'Host CPU Utilization (%)'	os_cpu_max
    'Host CPU Utilization (%)'	os_cpu_sd
    'Database Wait Time Ratio'	db_wait_ratio
    'Database CPU Time Ratio'	db_cpu_ratio
    'CPU Usage Per Sec'	cpu_per_s
    'CPU Usage Per Sec'	cpu_per_s_sd
    'Host CPU Usage Per Sec'	h_cpu_per_s
    'Host CPU Usage Per Sec'	h_cpu_per_s_sd
    'Average Active Sessions'	aas
    'Average Active Sessions'	aas_sd
    'Average Active Sessions'	aas_max
    'Database Time Per Sec'	db_time
    'Database Time Per Sec'	db_time_sd
    'SQL Service Response Time'	sql_res_t_cs
    'Background Time Per Sec'	bkgd_t_per_s
    'Logons Per Sec'	logons_s
    'Current Logons Count'	logons_total
    'Executions Per Sec'	exec_s
    'Hard Parse Count Per Sec'	hard_p_s
    'Logical Reads Per Sec'	l_reads_s
    'User Commits Per Sec'	commits_s
    'Physical Read Total Bytes Per Sec'	read_mb_s
    'Physical Read Total Bytes Per Sec'	read_mb_s_max
    'Physical Read Total IO Requests Per Sec'	read_iops
    'Physical Read Total IO Requests Per Sec'	read_iops_max
    'Physical Reads Per Sec'	read_bks
    'Physical Reads Direct Per Sec'	read_bks_direct
    'Physical Write Total Bytes Per Sec'	write_mb_s
    'Physical Write Total Bytes Per Sec'	write_mb_s_max
    'Physical Write Total IO Requests Per Sec'	write_iops
    'Physical Write Total IO Requests Per Sec'	write_iops_max
    'Physical Writes Per Sec'	write_bks
    'Physical Writes Direct Per Sec'	write_bks_direct
    'Redo Generated Per Sec'	redo_mb_s
    'DB Block Gets Per Sec'	db_block_gets_s
    'DB Block Changes Per Sec'	db_block_changes_s
    'GC CR Block Received Per Second'	gc_cr_rec_s
    'GC Current Block Received Per Second'	gc_cu_rec_s
    'Global Cache Average CR Get Time'	gc_cr_get_cs
    'Global Cache Average Current Get Time'	gc_cu_get_cs
    'Global Cache Blocks Corrupted'	gc_bk_corrupted
    'Global Cache Blocks Lost'	gc_bk_lost
    'Active Parallel Sessions'	px_sess
    'Active Serial Sessions'	se_sess
    'Average Synchronous Single-Block Read Latency'	s_blk_r_lat
    'Cell Physical IO Interconnect Bytes'	cell_io_int_mb
    'Cell Physical IO Interconnect Bytes'	cell_io_int_mb_max
    */
    private void init() {
        //os_cpu os_cpu_max db_wait_ratio db_cpu_ratio        aas    aas_max sql_res_t_cs bkgd_t_per_s
        //logons_s logons_total     exec_s   hard_p_s  l_reads_s  commits_s  read_mb_s read_mb_s_max
        //read_iops read_iops_max write_mb_s write_mb_s_max write_iops write_iops_max  redo_mb_s
        //db_block_gets_s db_block_changes_s
        //Metric Name, Metric Desc, Chart Raange Desc, Chart Title, Is Chartable
        _awrMetrics.put("OS_CPU", new AWRMetric("OS_CPU", "Host CPU Utilization (%)", "% Utilized",  "Host CPU Utilization %", true));
        _awrMetrics.put("OS_CPU_MAX", new AWRMetric("OS_CPU_MAX", "Host CPU Utilization (%)", "% Utilized", "Host MAX CPU Utilization %", true));
        _awrMetrics.put("OS_CPU_SD", new AWRMetric("OS_CPU_SD", "Host CPU Utilization (%)", "% Utilized", "Host Standard Deviation CPU Utilization %", true));
        _awrMetrics.put("DB_WAIT_RATIO", new AWRMetric("DB_WAIT_RATIO", "Database Wait Time Ratio", "Ratio", "DB Wait Time Ratio", true));
        _awrMetrics.put("DB_CPU_RATIO", new AWRMetric("DB_CPU_RATIO", "Database CPU Time Ratio", "Ratio", "DB CPU Time Ratio", true));
        _awrMetrics.put("CPU_PER_S", new AWRMetric("CPU_PER_S", "CPU Usage Per Sec", "Ratio", "CPU Usage Per Second", true));
        _awrMetrics.put("H_CPU_PER_S_SD", new AWRMetric("H_CPU_PER_S_SD", "CPU Usage Per Sec", "Ratio", "CPU Usage Per Second", true));
        _awrMetrics.put("AAS", new AWRMetric("AAS", "Average Active Sessions", "Active Sessions", "Average Active Sessions", true));
        _awrMetrics.put("AAS_SD", new AWRMetric("AAS_SD", "Average Active Sessions", "AAS SD", "Active Sessions Standard Deviation", true));
        _awrMetrics.put("AAS_MAX", new AWRMetric("AAS_MAX", "Average Active Sessions", "AAS Max", "Max Average Active Sessions", true));
        _awrMetrics.put("DB_TIME", new AWRMetric("DB_TIME", "Database Time Per Sec", "DB Time", "Database Time Per Second", true));
        _awrMetrics.put("DB_TIME_SD", new AWRMetric("DB_TIME_SD", "Database Time Standard Deviation", "DB Time", "Database Time Standard Deviation", true));
        _awrMetrics.put("SQL_RES_T_CS", new AWRMetric("SQL_RES_T_CS", "SQL Service Response Time", "", "SQL Service Response Time", true));
        _awrMetrics.put("BKGD_T_PER_S", new AWRMetric("BKGD_T_PER_S", "Background Time Per Sec", "", "Background Time Per Sec", true));
        _awrMetrics.put("LOGONS_S", new AWRMetric("LOGONS_S", "Logons Per Sec", "Logons", "Logons Per Sec", true));
        _awrMetrics.put("LOGONS_TOTAL", new AWRMetric("LOGONS_TOTAL", "Current Logons Count", "Logons", "Current Logons Count", true));
        _awrMetrics.put("EXEC_S", new AWRMetric("EXEC_S", "Executions Per Sec", "Exec", "Executions Per Sec", true));
        _awrMetrics.put("HARD_P_S", new AWRMetric("HARD_P_S", "", "Hard_P", "HARD_P/s", true));
        _awrMetrics.put("L_READS_S", new AWRMetric("L_READS_S", "Logical Reads Per Sec", "Reads", "Logical Reads Per Sec", true));
        _awrMetrics.put("COMMITS_S", new AWRMetric("COMMITS_S", "User Commits Per Sec", "Commits", "User Commits Per Sec", true));
        _awrMetrics.put("READ_MB_S", new AWRMetric("READ_MB_S", "Physical Read Total Bytes Per Sec", "MB", "Physical Read Total Bytes Per Sec", true));
        _awrMetrics.put("READ_MB_S_MAX", new AWRMetric("READ_MB_S_MAX", "Physical Read Total Bytes Per Sec", "MB", "Physical Read Total Bytes Per Sec MAX", true));
        _awrMetrics.put("READ_IOPS", new AWRMetric("READ_IOPS", "Physical Read Total IO Requests Per Sec", "IOPs", "Physical Read Total IO Requests Per Sec", true));
        _awrMetrics.put("READ_IOPS_MAX", new AWRMetric("READ_IOPS_MAX", "Physical Read Total IO Requests Per Sec", "IOPs", "Physical Read Total IO Requests Per Sec MAX", true));
        _awrMetrics.put("READ_BKS", new AWRMetric("READ_BKS", "Physical Reads Per Sec", "Reads", "Physical Reads Per Sec", true));
        _awrMetrics.put("READ_BKS_DIRECT", new AWRMetric("READ_BKS_DIRECT", "Physical Reads Direct Per Sec", "Reads", "Physical Reads Direct Per Sec", true));
        _awrMetrics.put("WRITE_MB_S", new AWRMetric("WRITE_MB_S", "Physical Write Total Bytes Per Sec", "MB", "Write MB/s", true));
        _awrMetrics.put("WRITE_MB_S_MAX", new AWRMetric("WRITE_MB_S_MAX", "Physical Write Total Bytes Per Sec", "MB", "Max Write MB/s", true));
        _awrMetrics.put("WRITE_IOPS", new AWRMetric("WRITE_IOPS", "Physical Write Total IO Requests Per Sec", "IOPs", "Physical Write Total IO Requests Per Sec", true));
        _awrMetrics.put("WRITE_IOPS_MAX", new AWRMetric("WRITE_IOPS_MAX", "Physical Write Total IO Requests Per Sec", "IOPs", "Physical Write Total IO Requests Per Sec MAX", true));
        _awrMetrics.put("WRITE_BKS", new AWRMetric("WRITE_BKS", "Physical Writes Per Sec", "Writes", "Physical Writes Per Sec", true));
        _awrMetrics.put("WRITE_BKS_DIRECT", new AWRMetric("WRITE_BKS_DIRECT", "Physical Writes Direct Per Sec", "Writes", "Physical Writes Direct Per Sec", true));
        _awrMetrics.put("REDO_MB_S", new AWRMetric("REDO_MB_S", "Redo Generated Per Sec", "MB", "REDO MB/s", true));
        _awrMetrics.put("DB_BLOCK_GETS_S", new AWRMetric("DB_BLOCK_GETS_S", "DB Block Gets Per Sec", "", "DB Block Gets Per Sec", true));
        _awrMetrics.put("DB_BLOCK_CHANGES_S", new AWRMetric("DB_BLOCK_CHANGES_S", "DB Block Changes Per Sec", "", "DB Block Changes Per Sec", true));
        _awrMetrics.put("DB_CR_REC_S", new AWRMetric("DB_CR_REC_S", "GC CR Block Received Per Second", "", "GC CR Block Received Per Second", true));
        _awrMetrics.put("GC_CU_REC_S", new AWRMetric("GC_CU_REC_S", "GC Current Block Received Per Second", "", "GC Current Block Received Per Second", true));
        _awrMetrics.put("GC_CR_GET_CS", new AWRMetric("GC_CR_GET_CS", "Global Cache Average CR Get Time", "", "Global Cache Average CR Get Time", true));
        _awrMetrics.put("GC_BK_CORRUPTED", new AWRMetric("GC_BK_CORRUPTED", "Global Cache Blocks Corrupted", "", "Global Cache Blocks Corrupted", true));
        _awrMetrics.put("GC_BK_LOST", new AWRMetric("GC_BK_LOST", "Global Cache Blocks Lost", "", "Global Cache Blocks Lost", true));
        _awrMetrics.put("PX_SESS", new AWRMetric("PX_SESS", "Active Parallel Sessions", "", "Active Parallel Sessions", true));
        _awrMetrics.put("SE_SESS", new AWRMetric("SE_SESS", "Active Serial Sessions", "", "Active Serial Sessions", true));
        _awrMetrics.put("S_BLK_R_LAT", new AWRMetric("S_BLK_R_LAT", "Average Synchronous Single-Block Read Latency", "", "Average Synchronous Single-Block Read Latency", true));
        _awrMetrics.put("CELL_IO_INT_MB", new AWRMetric("CELL_IO_INT_MB", "Cell Physical IO Interconnect Bytes", "", "Cell Physical IO Interconnect Bytes", true));
        _awrMetrics.put("CELL_IO_INT_MB_MAX", new AWRMetric("CELL_IO_INT_MB_MAX", "Cell Physical IO Interconnect Bytes", "", "Cell Physical IO Interconnect Bytes MAX", true));
    }
    
    public String getMetricDescription(String metricName) {
        return ((AWRMetric)_awrMetrics.get(metricName)).metricDescription;
    }
    
    public String getMetricRangeDescription(String metricName) {
        return ((AWRMetric)_awrMetrics.get(metricName)).metricChartRangeDescription;
    }

    public String getMetricChartTitle(String metricName) {
        return ((AWRMetric)_awrMetrics.get(metricName)).metricChartTitle;
    }
    
    public class AWRMetric {
        
        public AWRMetric(String name, String desc, String chartRangeDesc, String chartTitle, boolean chartable) {
            metricName = name;
            metricDescription = desc;
            metricChartRangeDescription = chartRangeDesc;
            metricChartTitle = chartTitle;
            isChartable = chartable;
        }
        
        String metricName;
        String metricDescription;
        String metricChartRangeDescription;
        String metricChartTitle;
        boolean isChartable = false;
    }
}
