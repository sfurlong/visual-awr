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

    public static ArrayList<AWRMetric> getMetricDetails() {
        ArrayList<AWRMetric> ret = new ArrayList<AWRMetric>();
        for (Object key : _awrMetrics.keySet()) {
            ret.add((AWRMetric)_awrMetrics.get(key));
        }
        return ret;
    }
    
    
    private void init() {
        //os_cpu os_cpu_max db_wait_ratio db_cpu_ratio        aas    aas_max sql_res_t_cs bkgd_t_per_s
        //logons_s logons_total     exec_s   hard_p_s  l_reads_s  commits_s  read_mb_s read_mb_s_max
        //read_iops read_iops_max write_mb_s write_mb_s_max write_iops write_iops_max  redo_mb_s
        //db_block_gets_s db_block_changes_s
        //Metric Name, Metric Desc, Chart Raange Desc, Chart Title, Is Chartable
        _awrMetrics.put("OS_CPU", new AWRMetric("OS_CPU", "Host CPU Utilization (%)", "% Busy/(Idle+Busy)", "2057", "", "Host CPU Utilization %", true));
        _awrMetrics.put("OS_CPU_MAX", new AWRMetric("OS_CPU_MAX", "Host CPU Utilization (%)", "% Busy/(Idle+Busy)", "2057", "MAX"));
        _awrMetrics.put("OS_CPU_SD", new AWRMetric("OS_CPU_SD", "Host CPU Utilization (%)", "% Busy/(Idle+Busy)", "2057", "SD"));
        _awrMetrics.put("DB_WAIT_RATIO", new AWRMetric("DB_WAIT_RATIO", "Database Wait Time Ratio", "% Wait/DB_Time", "2107", "DB Wait Time Ratio"));
        _awrMetrics.put("DB_CPU_RATIO", new AWRMetric("DB_CPU_RATIO", "Database CPU Time Ratio", "% Cpu/DB_Time", "2108", "DB CPU Time Ratio"));
        _awrMetrics.put("CPU_PER_S", new AWRMetric("CPU_PER_S", "CPU Usage Per Sec", "CentiSeconds Per Second", "2075", "CPU Usage Per Second"));
        _awrMetrics.put("H_CPU_PER_S", new AWRMetric("H_CPU_PER_S", "Host CPU Usage Per Sec", "CentiSeconds Per Second", "2155", "CPU Usage Per Second"));
        _awrMetrics.put("AAS", new AWRMetric("AAS", "Average Active Sessions", "Active Sessions", "2147", "Average Active Sessions"));
        _awrMetrics.put("AAS_SD", new AWRMetric("AAS_SD", "Average Active Sessions", "Active Sessions",  "2147", "SD"));
        _awrMetrics.put("AAS_MAX", new AWRMetric("AAS_MAX", "Average Active Sessions", "Active Sessions",  "2147", "MAX"));
        _awrMetrics.put("DB_TIME", new AWRMetric("DB_TIME", "Database Time Per Sec", "CentiSeconds Per Second", "2123", ""));
        _awrMetrics.put("DB_TIME_SD", new AWRMetric("DB_TIME_SD", "Database Time Per Sec", "CentiSeconds Per Second", "2123", "SD"));
        _awrMetrics.put("SQL_RES_T_CS", new AWRMetric("SQL_RES_T_CS", "SQL Service Response Time", "CentiSeconds Per Call\n", "2106"));
        _awrMetrics.put("BKGD_T_PER_S", new AWRMetric("BKGD_T_PER_S", "Background Time Per Sec", "Active Sessions\n", "2154"));
        _awrMetrics.put("LOGONS_S", new AWRMetric("LOGONS_S", "Logons Per Sec", "Logons Per Second", "2018"));
        _awrMetrics.put("LOGONS_TOTAL", new AWRMetric("LOGONS_TOTAL", "Current Logons Count", "Logons", "2103"));
        _awrMetrics.put("EXEC_S", new AWRMetric("EXEC_S", "Executions Per Sec", "Executes Per Second", "2121"));
        _awrMetrics.put("HARD_P_S", new AWRMetric("HARD_P_S", "Hard Parse Count Per Sec\n", "Parses Per Second", "2046"));
        _awrMetrics.put("L_READS_S", new AWRMetric("L_READS_S", "Logical Reads Per Sec", "Reads Per Second", "2030"));
        _awrMetrics.put("COMMITS_S", new AWRMetric("COMMITS_S", "User Commits Per Sec", "Commits Per Second", "2022"));
        _awrMetrics.put("READ_MB_S", new AWRMetric("READ_MB_S", "Physical Read Total Bytes Per Sec", "Bytes Per Second", "2093"));
        _awrMetrics.put("READ_MB_S_MAX", new AWRMetric("READ_MB_S_MAX", "Physical Read Total Bytes Per Sec", "Bytes Per Second", "2093", "MAX"));
        _awrMetrics.put("READ_IOPS", new AWRMetric("READ_IOPS", "Physical Read Total IO Requests Per Sec", "Requests Per Second", "2092"));
        _awrMetrics.put("READ_IOPS_MAX", new AWRMetric("READ_IOPS_MAX", "Physical Read Total IO Requests Per Sec",  "Requests Per Second", "2092", "MAX"));
        _awrMetrics.put("READ_BKS", new AWRMetric("READ_BKS", "Physical Reads Per Sec", "Reads Per Second", "2004"));
        _awrMetrics.put("READ_BKS_DIRECT", new AWRMetric("READ_BKS_DIRECT", "Physical Reads Direct Per Sec", "Reads Per Second", "2008"));
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
        return ((AWRMetric)_awrMetrics.get(metricName)).getAWROracleMetricName();
    }
    
    public String getMetricRangeDescription(String metricName) {
        return ((AWRMetric)_awrMetrics.get(metricName)).getAWROracleMetricUnit();
    }

    public String getMetricChartTitle(String metricName) {
        return ((AWRMetric)_awrMetrics.get(metricName)).getMetricChartTitle();
    }
  
}
