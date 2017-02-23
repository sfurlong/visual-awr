package org.altaprise.vawr.awrdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AWRMetrics {

    private static AWRMetrics theInstance = null;
    private static LinkedHashMap _awrMetrics = new LinkedHashMap();
    private static LinkedHashMap _awrOracleNamesToAWRMinerNamesMap = new LinkedHashMap();
    
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

    public static ArrayList<String> getOracleMetricNames() {
        ArrayList<String> ret = new ArrayList<String>();
        for (Object key : _awrOracleNamesToAWRMinerNamesMap.keySet()) {
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
    
    public static String getAWRMinerMetricName(String oracleMetricName) {
        return (String)_awrOracleNamesToAWRMinerNamesMap.get(oracleMetricName);
    }


    public static boolean awrMetricExists(String metricName) {
        boolean ret = false;
        ret = _awrMetrics.containsKey(metricName);
        return ret;
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
    
    public void setMetricIsKey(String metricName) {
        AWRMetric metric = (AWRMetric)_awrMetrics.get(metricName);
        metric.setIsKeyMetric(true);
    }

    public boolean getMetricIsKey(String metricName) {
        AWRMetric metric = (AWRMetric)_awrMetrics.get(metricName);
        return metric.isKeyMetric();
    }
    
    private void init() {
        //Metric Name, Metric Desc, Chart Raange Desc, Chart Title, Is Chartable
        AWRMetric tempAWRMetric;
        tempAWRMetric = new AWRMetric("OS_CPU", "Host CPU Utilization (%)", "% Busy/(Idle+Busy)", "2057", "Average");
        tempAWRMetric.setAWRMetricDescription("Average Host CPU Utilization %.  Average of \"Host CPU Utilization (%)\" metrics over each interval.");
        _awrMetrics.put("SUMMARY", new AWRMetric("SUMMARY", "Summary Report for Snapshot Range", "", ""));
        _awrMetrics.put("OS_CPU", tempAWRMetric);
        _awrMetrics.put("OS_CPU_MAX", new AWRMetric("OS_CPU_MAX", "Host CPU Utilization (%)", "% Busy/(Idle+Busy)", "2057", "MAX"));
        _awrMetrics.put("OS_CPU_SD", new AWRMetric("OS_CPU_SD", "Host CPU Utilization (%)", "% Busy/(Idle+Busy)", "2057", "SD"));
        _awrMetrics.put("DB_WAIT_RATIO", new AWRMetric("DB_WAIT_RATIO", "Database Wait Time Ratio", "% Wait/DB_Time", "2107"));
        _awrMetrics.put("DB_CPU_RATIO", new AWRMetric("DB_CPU_RATIO", "Database CPU Time Ratio", "% Cpu/DB_Time", "2108"));
        _awrMetrics.put("CPU_PER_S", new AWRMetric("CPU_PER_S", "CPU Usage Per Sec", "CentiSeconds Per Second", "2075"));
        _awrMetrics.put("H_CPU_PER_S", new AWRMetric("H_CPU_PER_S", "Host CPU Usage Per Sec", "CentiSeconds Per Second", "2155"));
        _awrMetrics.put("AAS", new AWRMetric("AAS", "Average Active Sessions", "Active Sessions", "2147"));
        _awrMetrics.put("AAS_SD", new AWRMetric("AAS_SD", "Average Active Sessions", "Active Sessions",  "2147", "SD"));
        _awrMetrics.put("AAS_MAX", new AWRMetric("AAS_MAX", "Average Active Sessions", "Active Sessions",  "2147", "MAX"));
        _awrMetrics.put("DB_TIME", new AWRMetric("DB_TIME", "Database Time Per Sec", "CentiSeconds Per Second", "2123"));
        _awrMetrics.put("DB_TIME_SD", new AWRMetric("DB_TIME_SD", "Database Time Per Sec", "CentiSeconds Per Second", "2123", "SD"));
        _awrMetrics.put("SQL_RES_T_CS", new AWRMetric("SQL_RES_T_CS", "SQL Service Response Time", "CentiSeconds Per Call", "2106"));
        _awrMetrics.put("BKGD_T_PER_S", new AWRMetric("BKGD_T_PER_S", "Background Time Per Sec", "Active Sessions\n", "2154"));
        _awrMetrics.put("LOGONS_S", new AWRMetric("LOGONS_S", "Logons Per Sec", "Logons Per Second", "2018"));
        _awrMetrics.put("LOGONS_TOTAL", new AWRMetric("LOGONS_TOTAL", "Current Logons Count", "Logons", "2103"));
        _awrMetrics.put("EXEC_S", new AWRMetric("EXEC_S", "Executions Per Sec", "Executes Per Second", "2121"));
        _awrMetrics.put("HARD_P_S", new AWRMetric("HARD_P_S", "Hard Parse Count Per Sec", "Parses Per Second", "2046"));
        _awrMetrics.put("L_READS_S", new AWRMetric("L_READS_S", "Logical Reads Per Sec", "Reads Per Second", "2030"));
        _awrMetrics.put("COMMITS_S", new AWRMetric("COMMITS_S", "User Commits Per Sec", "Commits Per Second", "2022"));
        _awrMetrics.put("READ_MB_S", new AWRMetric("READ_MB_S", "Physical Read Total Bytes Per Sec", "Bytes Per Second", "2093"));
        _awrMetrics.put("READ_MB_S_MAX", new AWRMetric("READ_MB_S_MAX", "Physical Read Total Bytes Per Sec", "Bytes Per Second", "2093", "MAX"));
        _awrMetrics.put("READ_IOPS", new AWRMetric("READ_IOPS", "Physical Read Total IO Requests Per Sec", "Requests Per Second", "2092"));
        _awrMetrics.put("READ_IOPS_MAX", new AWRMetric("READ_IOPS_MAX", "Physical Read Total IO Requests Per Sec",  "Requests Per Second", "2092", "MAX"));
        _awrMetrics.put("READ_BKS", new AWRMetric("READ_BKS", "Physical Reads Per Sec", "Reads Per Second", "2004"));
        _awrMetrics.put("READ_BKS_DIRECT", new AWRMetric("READ_BKS_DIRECT", "Physical Reads Direct Per Sec", "Reads Per Second", "2008"));
        _awrMetrics.put("WRITE_MB_S", new AWRMetric("WRITE_MB_S", "Physical Write Total Bytes Per Sec", "Bytes Per Second", "2124"));
        _awrMetrics.put("WRITE_MB_S_MAX", new AWRMetric("WRITE_MB_S_MAX", "Physical Write Total Bytes Per Sec", "Bytes Per Second", "2124", "MAX"));
        _awrMetrics.put("WRITE_IOPS", new AWRMetric("WRITE_IOPS", "Physical Write Total IO Requests Per Sec", "Requests Per Second", "2100"));
        _awrMetrics.put("WRITE_IOPS_MAX", new AWRMetric("WRITE_IOPS_MAX", "Physical Write Total IO Requests Per Sec", "Requests Per Second", "2100", "MAX"));
        _awrMetrics.put("WRITE_BKS", new AWRMetric("WRITE_BKS", "Physical Writes Per Sec", "Writes Per Second", "2006"));
        _awrMetrics.put("WRITE_BKS_DIRECT", new AWRMetric("WRITE_BKS_DIRECT", "Physical Writes Direct Per Sec", "Writes Per Second", "2010"));
        _awrMetrics.put("REDO_MB_S", new AWRMetric("REDO_MB_S", "Redo Generated Per Sec", "Bytes Per Second", "2016"));
        _awrMetrics.put("DB_BLOCK_GETS_S", new AWRMetric("DB_BLOCK_GETS_S", "DB Block Gets Per Sec", "Blocks Per Second", "2067"));
        _awrMetrics.put("DB_BLOCK_CHANGES_S", new AWRMetric("DB_BLOCK_CHANGES_S", "DB Block Changes Per Sec", "Blocks Per Second", "2071"));
        _awrMetrics.put("DB_CR_REC_S", new AWRMetric("DB_CR_REC_S", "GC CR Block Received Per Second", "Blocks Per Second", "2094"));
        _awrMetrics.put("GC_CU_REC_S", new AWRMetric("GC_CU_REC_S", "GC Current Block Received Per Second", "Blocks Per Second", "2096"));
        _awrMetrics.put("GC_CR_GET_CS", new AWRMetric("GC_CR_GET_CS", "Global Cache Average CR Get Time", "CentiSeconds Per Get", "2098"));
        _awrMetrics.put("GC_CU_GET_CS", new AWRMetric("GC_CU_GET_CS", "Global Cache Average Current Get Time", "CentiSeconds Per Get", "2099"));
        _awrMetrics.put("GC_BK_CORRUPTED", new AWRMetric("GC_BK_CORRUPTED", "Global Cache Blocks Corrupted", "Blocks", "2101"));
        _awrMetrics.put("GC_BK_LOST", new AWRMetric("GC_BK_LOST", "Global Cache Blocks Lost", "Blocks", "2102"));
        _awrMetrics.put("PX_SESS", new AWRMetric("PX_SESS", "Active Parallel Sessions", "Sessions", "2149"));
        _awrMetrics.put("SE_SESS", new AWRMetric("SE_SESS", "Active Serial Sessions", "Sessions", "2148"));
        _awrMetrics.put("S_BLK_R_LAT", new AWRMetric("S_BLK_R_LAT", "Average Synchronous Single-Block Read Latency", "Milliseconds", "2144"));
        _awrMetrics.put("CELL_IO_INT_MB", new AWRMetric("CELL_IO_INT_MB", "Cell Physical IO Interconnect Bytes", "Bytes", "2156"));
        _awrMetrics.put("CELL_IO_INT_MB_MAX", new AWRMetric("CELL_IO_INT_MB_MAX", "Cell Physical IO Interconnect Bytes", "Bytes", "2156", "MAX"));
        _awrMetrics.put("SGA_PGA_TOT", new AWRMetric("SGA_PGA_TOT", "Memory Utilization", "GB", ""));
        _awrMetrics.put("AVG_ACTIVE_SESS_WAITS", new AWRMetric("AVG_ACTIVE_SESS_WAITS", "Avg Active Session By Wait Type", "", ""));
        _awrMetrics.put("TOP_N_TIMED_EVENTS", new AWRMetric("TOP_N_TIMED_EVENTS", "Top N Timed Events for Snapshot Range", "", ""));
        _awrMetrics.put("SIZE_GB", new AWRMetric("SIZE_GB", "DB Size On Disk for Snapshot Range", "", ""));
        
        //Create the a map of AWRMiner Names to Oracle AWR Names
        for (Object key : _awrMetrics.keySet()) {
            AWRMetric metric = (AWRMetric)_awrMetrics.get(key);
            this._awrOracleNamesToAWRMinerNamesMap.put(metric.getAwrUniqueOracleMetricName(), key);
        }
        
    }
    
}
