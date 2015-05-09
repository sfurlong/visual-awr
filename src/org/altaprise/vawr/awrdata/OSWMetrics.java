package org.altaprise.vawr.awrdata;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OSWMetrics {

    private static OSWMetrics theInstance = null;
    private static LinkedHashMap _cellSrvMetrics = new LinkedHashMap();
    
    private OSWMetrics() {
        //don't allow anyone to instantiate.
        init();
    }
    
    public static OSWMetrics getInstance() {
       if(theInstance == null) {
          theInstance = new OSWMetrics();
       }
       return theInstance;
    }

    public static ArrayList<String> getMetricNames() {
        ArrayList<String> ret = new ArrayList<String>();
        for (Object key : _cellSrvMetrics.keySet()) {
            ret.add((String)key);
        }
        return ret;
    }

    public static boolean oswMetricExists(String metricName) {
        boolean ret = false;
        ret = _cellSrvMetrics.containsKey(metricName);
        return ret;
    }

    public String getMetricDescription(String metricName) {
        return ((AWRMetric)_cellSrvMetrics.get(metricName)).getAWROracleMetricName();
    }
    
    public String getMetricRangeDescription(String metricName) {
        return ((AWRMetric)_cellSrvMetrics.get(metricName)).getAWROracleMetricUnit();
    }

    public String getMetricChartTitle(String metricName) {
        return ((AWRMetric)_cellSrvMetrics.get(metricName)).getMetricChartTitle();
    }
    
    public void setMetricIsKey(String metricName) {
        AWRMetric metric = (AWRMetric)_cellSrvMetrics.get(metricName);
        metric.setIsKeyMetric(true);
    }

    public boolean getMetricIsKey(String metricName) {
        AWRMetric metric = (AWRMetric)_cellSrvMetrics.get(metricName);
        return metric.isKeyMetric();
    }
    
    private void init() {
        //Metric Name, Metric Desc, Chart Raange Desc, Chart Title, Is Chartable
        _cellSrvMetrics.put("Number of hard disk block IO read requests", "");
        _cellSrvMetrics.put("Number of hard disk block IO write requests", "");
        _cellSrvMetrics.put("Number of flash disk block IO read requests", "");
        _cellSrvMetrics.put("Number of flash disk block IO write requests", "");
        _cellSrvMetrics.put("Hard disk block IO reads (KB)", "");
        _cellSrvMetrics.put("Hard disk block IO writes (KB)", "");
        _cellSrvMetrics.put("Flash disk block IO reads (KB)", "");
        _cellSrvMetrics.put("Flash disk block IO writes (KB)", "");
        _cellSrvMetrics.put("Number of disk IO errors", "");
        //_cellSrvMetrics.put("Number of latency threshold warnings during job", "");
        //_cellSrvMetrics.put("Number of latency threshold warnings by checker", "");
        //_cellSrvMetrics.put("Number of latency threshold warnings for smart IO", "");
        //_cellSrvMetrics.put("Number of latency threshold warnings for redo log writes", "");
        _cellSrvMetrics.put("Current read block IO to be issued (KB)", "");
        _cellSrvMetrics.put("Total read block IO to be issued (KB)", "");
        _cellSrvMetrics.put("Current write block IO to be issued (KB)", "");
        _cellSrvMetrics.put("Total write block IO to be issued (KB)", "");
        _cellSrvMetrics.put("Current read blocks in IO (KB)", "");
        _cellSrvMetrics.put("Total read block IO issued (KB)", "");
        _cellSrvMetrics.put("Current write blocks in IO (KB)", "");
        _cellSrvMetrics.put("Total write block IO issued (KB)", "");
        _cellSrvMetrics.put("Current read block IO in network send (KB)", "");
        _cellSrvMetrics.put("Total read block IO in network send (KB)", "");
        _cellSrvMetrics.put("Current write block IO in network send (KB)", "");
        _cellSrvMetrics.put("Total write block IO in network send (KB)", "");
        _cellSrvMetrics.put("Current block IO being populated in flash (KB)", "");
        _cellSrvMetrics.put("Total block IO KB populated in flash (KB)", "");
        //_cellSrvMetrics.put("== Memory related stats ==", "");
        /*
        _cellSrvMetrics.put("SGA heap used - kgh statistics (KB)", "");
        _cellSrvMetrics.put("SGA heap free - cellsrv statistics (KB)", "");
        _cellSrvMetrics.put("OS memory allocated to SGA (KB)", "");
        _cellSrvMetrics.put("SGA heap used - cellsrv statistics - KB", "");
        _cellSrvMetrics.put("OS memory allocated to PGA (KB)", "");
        _cellSrvMetrics.put("PGA heap used - cellsrv statistics (KB)", "");
        _cellSrvMetrics.put("OS memory allocated to cellsrv (KB)", "");
        _cellSrvMetrics.put("Number of allocation failures in 512 bytes pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 2KB pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 4KB pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 8KB pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 16KB pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 32KB pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 64KB pool", "");
        _cellSrvMetrics.put("Number of allocation failures in 1MB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 512 bytes pool", "");
        _cellSrvMetrics.put("Allocation hwm in 2KB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 4KB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 8KB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 16KB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 32KB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 64KB pool", "");
        _cellSrvMetrics.put("Allocation hwm in 1MB pool", "");
        _cellSrvMetrics.put("Number of low memory threshold failures", "");
        _cellSrvMetrics.put("Number of no memory threshold failures", "");
        _cellSrvMetrics.put("Dynamic buffer allocation requests", "");
        _cellSrvMetrics.put("Dynamic buffer allocation failures", "");
        _cellSrvMetrics.put("Dynamic buffer allocation failures due to low mem", "");
        _cellSrvMetrics.put("Dynamic buffer allocated size (KB)", "");
        _cellSrvMetrics.put("Dynamic buffer allocation hwm (KB)", "");
        */
        //_cellSrvMetrics.put("== Execution related stats ==", "");
        /*
        _cellSrvMetrics.put("Incarnation number", "");
        _cellSrvMetrics.put("Number of module version failures", "");
        _cellSrvMetrics.put("Number of threads working", "");
        _cellSrvMetrics.put("Number of threads waiting for network", "");
        _cellSrvMetrics.put("Number of threads waiting for resource", "");
        _cellSrvMetrics.put("Number of threads waiting for a mutex", "");
        */
        //_cellSrvMetrics.put("== Network related stats ==", "");
        _cellSrvMetrics.put("Total bytes received from the network", "");
        _cellSrvMetrics.put("Total bytes transmitted to the network", "");
        _cellSrvMetrics.put("Total bytes retransmitted to the network", "");
        /*
        _cellSrvMetrics.put("Number of active sendports", "");
        _cellSrvMetrics.put("Hwm of active sendports", "");
        _cellSrvMetrics.put("Number of active remote open infos", "");
        _cellSrvMetrics.put("HWM of remote open infos", "");
        */
        //_cellSrvMetrics.put("== SmartIO related stats ==", "");
        _cellSrvMetrics.put("Number of active smart IO sessions", "");
        _cellSrvMetrics.put("High water mark of smart IO sessions", "");
        _cellSrvMetrics.put("Number of completed smart IO sessions", "");
        _cellSrvMetrics.put("Smart IO offload efficiency (percentage)", "");
        _cellSrvMetrics.put("Size of IO avoided due to storage index (KB)", "");
        _cellSrvMetrics.put("Current smart IO to be issued (KB)", "");
        _cellSrvMetrics.put("Total smart IO to be issued (KB)", "");
        _cellSrvMetrics.put("Current smart IO in IO (KB)", "");
        _cellSrvMetrics.put("Total smart IO in IO (KB)", "");
        _cellSrvMetrics.put("Current smart IO being cached in flash (KB)", "");
        _cellSrvMetrics.put("Total smart IO being cached in flash (KB)", "");
        _cellSrvMetrics.put("Current smart IO with IO completed (KB)", "");
        _cellSrvMetrics.put("Total smart IO with IO completed (KB)", "");
        _cellSrvMetrics.put("Current smart IO being filtered (KB)", "");
        _cellSrvMetrics.put("Total smart IO being filtered (KB)", "");
        _cellSrvMetrics.put("Current smart IO filtering completed (KB)", "");
        _cellSrvMetrics.put("Total smart IO filtering completed (KB)", "");
        _cellSrvMetrics.put("Current smart IO filtered size (KB)", "");
        _cellSrvMetrics.put("Total smart IO filtered (KB)", "");
        _cellSrvMetrics.put("Total cpu passthru output IO size (KB)", "");
        _cellSrvMetrics.put("Total passthru output IO size (KB)", "");
        _cellSrvMetrics.put("Current smart IO with results in send (KB)", "");
        _cellSrvMetrics.put("Total smart IO with results in send (KB)", "");
        _cellSrvMetrics.put("Current smart IO filtered in send (KB)", "");
        _cellSrvMetrics.put("Total smart IO filtered in send (KB)", "");
        _cellSrvMetrics.put("Total smart IO read from flash (KB)", "");
        _cellSrvMetrics.put("Total smart IO initiated flash population (KB)", "");
        _cellSrvMetrics.put("Total smart IO read from hard disk (KB)", "");
        _cellSrvMetrics.put("Total smart IO writes (fcre) to hard disk (KB)", "");
        _cellSrvMetrics.put("Number of smart IO requests < 512KB", "");
        _cellSrvMetrics.put("Number of smart IO requests >= 512KB and < 1MB", "");
        _cellSrvMetrics.put("Number of smart IO requests >= 1MB and < 2MB", "");
        _cellSrvMetrics.put("Number of smart IO requests >= 2MB and < 4MB", "");
        _cellSrvMetrics.put("Number of smart IO requests >= 4MB and < 8MB", "");
        _cellSrvMetrics.put("Number of smart IO requests >= 8MB", "");
        _cellSrvMetrics.put("Number of times smart IO buffer reserve failures", "");
        _cellSrvMetrics.put("Number of times smart IO request misses", "");
        //_cellSrvMetrics.put("Number of times IO for smart IO not allowed to be issued", "");
        /*
        _cellSrvMetrics.put("Number of times smart IO prefetch limit was reached", "");
        _cellSrvMetrics.put("Number of times smart scan used unoptimized mode", "");
        _cellSrvMetrics.put("Number of times smart fcre used unoptimized mode", "");
        _cellSrvMetrics.put("Number of times smart backup used unoptimized mode", "");
        */
        //_cellSrvMetrics.put("== FlashCache related stats ==", "");
        /*
        _cellSrvMetrics.put("Number of read hits", "");
        _cellSrvMetrics.put("Read on flashcache hit(KB)", "");
        _cellSrvMetrics.put("Number of keep read hits", "");
        _cellSrvMetrics.put("Read on flashcache keep hit(KB)", "");
        _cellSrvMetrics.put("Number of read misses", "");
        _cellSrvMetrics.put("Total IO size for read miss(KB)", "");
        _cellSrvMetrics.put("Number of keep read misses", "");
        _cellSrvMetrics.put("Total IO size for keep read miss(KB)", "");
        _cellSrvMetrics.put("Number of no cache reads", "");
        _cellSrvMetrics.put("Total size for nocache read(KB)", "");
        _cellSrvMetrics.put("Number of keep no cache reads", "");
        _cellSrvMetrics.put("Number of partial reads", "");
        _cellSrvMetrics.put("Total size for partial reads(KB)", "");
        _cellSrvMetrics.put("Number of optimized partial reads", "");
        _cellSrvMetrics.put("Number of keep partial reads", "");
        _cellSrvMetrics.put("Number of cache writes", "");
        _cellSrvMetrics.put("Total size for cache writes(KB)", "");
        _cellSrvMetrics.put("Number of partial cache writes", "");
        _cellSrvMetrics.put("Number of redirty", "");
        _cellSrvMetrics.put("Number of keep cache writes", "");
        _cellSrvMetrics.put("Total size for keep cache writes(KB)", "");
        _cellSrvMetrics.put("Number of partial keep cache writes", "");
        _cellSrvMetrics.put("Number of keep redirty", "");
        _cellSrvMetrics.put("Number of nocache writes", "");
        _cellSrvMetrics.put("Total size for nocache writes(KB)", "");
        _cellSrvMetrics.put("Number of partial nocache writes", "");
        _cellSrvMetrics.put("Number of keep nocache writes", "");
        _cellSrvMetrics.put("Number of populates", "");
        _cellSrvMetrics.put("Total size for populate writes(KB)", "");
        _cellSrvMetrics.put("Number of trims", "");
        _cellSrvMetrics.put("Flash Physical Usage", "");
        _cellSrvMetrics.put("Number of disk writer writes", "");
        _cellSrvMetrics.put("Total size for disk writer writes(KB)", "");
        _cellSrvMetrics.put("Number of disk writer coalesced writes", "");
        _cellSrvMetrics.put("Total size for disk writer coalesced writes(KB)", "");
        _cellSrvMetrics.put("Number of large read rejections", "");
        _cellSrvMetrics.put("Total size for rejected large read IOs(KB)", "");
        */
        //_cellSrvMetrics.put("Number of scan bytes attempted to read from cache(KB)", "");
        _cellSrvMetrics.put("Number of SCAN bytes read from cache(KB)", "");
        _cellSrvMetrics.put("Number of partial cache hits for SCAN IO", "");
        //_cellSrvMetrics.put("Number of no cache hit and no victim hdrs for SCAN IO", "");
        //_cellSrvMetrics.put("Number of failed victim searches for SCAN IO", "");
        //_cellSrvMetrics.put("Number of cache lines returned due to partial SCAN IO", "");
        //_cellSrvMetrics.put("Number of cache lines evicted due to partial SCAN IO", "");
        //_cellSrvMetrics.put("Number of cache lines initialized by sympathy caching", "");
        _cellSrvMetrics.put("Number of DW replacements during SCAN IO", "");
        _cellSrvMetrics.put("Number of OLTP replacements during SCAN IO", "");
        _cellSrvMetrics.put("Number of SELF replacements during SCAN IO", "");
        _cellSrvMetrics.put("Number of zero hit replacements during SCAN IO", "");
        //_cellSrvMetrics.put("Number of free cache line replacements during SCAN IO", "");
        //_cellSrvMetrics.put("Number of times touch count cooling occurred", "");
        //_cellSrvMetrics.put("Number of IOs failed to populate FC due to throttling", "");
        /*
        _cellSrvMetrics.put("Cachesize(KB)", "");
        _cellSrvMetrics.put("Keepsize(KB)", "");
        _cellSrvMetrics.put("OLTPsize(KB)", "");
        _cellSrvMetrics.put("Number of cache headers with valid data", "");
        _cellSrvMetrics.put("Number of attempts to get a cacheline from lru", "");
        */
        //_cellSrvMetrics.put("Number of failed attempts to get a cacheline from lru", "");
        /*
        _cellSrvMetrics.put("Number of flash cache IO errors", "");
        _cellSrvMetrics.put("Size of eviction from flash cache (KB)", "");
        _cellSrvMetrics.put("Number of outstanding large flash IOs", "");
        */
        //_cellSrvMetrics.put("== FFI related stats ==", "");
        //_cellSrvMetrics.put("number of reads straddling consecutive FFI regions", "");
        //_cellSrvMetrics.put("number of writes straddling consecutive FFI regions", "");
        //_cellSrvMetrics.put("number of writes which must block all other writes in 4MB region", "");
        //_cellSrvMetrics.put("number of FFIWriteJob waited for pin", "");
        //_cellSrvMetrics.put("number of FFIRemoveJob waited for pin", "");
        //_cellSrvMetrics.put("number of FFIFlushJob waited for pin", "");
        //_cellSrvMetrics.put("number of FFIJob waited for pin", "");
        //_cellSrvMetrics.put("number of regions initialized by FFI", "");
        //_cellSrvMetrics.put("number of regions initialized by FFI, but later flushed to disk", "");

    }
    
}
