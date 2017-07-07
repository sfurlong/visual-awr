package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class AWRData {


    private List _headerTokens = new ArrayList();
    private LinkedHashMap<String, AWRRecord> _dataRecords = new LinkedHashMap<String, AWRRecord>();
    private LinkedHashMap<String, AWRRecord> _activeSessionRecords = new LinkedHashMap<String, AWRRecord>();
    private LinkedHashMap<String, AWRRecord> _sizeOnDiskRecords = new LinkedHashMap<String, AWRRecord>();
    private LinkedHashMap<String, AWRRecord> _sysstatRecords = new LinkedHashMap<String, AWRRecord>();
    private HashMap<String, TopWaitEventsRecord> _topWaitEventsMap = new HashMap<String, TopWaitEventsRecord>();
    private HashMap<String, DBRec> _platformInfo = new HashMap<String, DBRec>();
    private int _numRACInstances = 0;

    private static AWRData _theInstance = null;

    private AWRData() {
    }

    public static AWRData getInstance() {
        if (_theInstance == null) {
            _theInstance = new AWRData();
        }
        return _theInstance;
    }

    public void clearAWRData() {
        _headerTokens.clear();
        _dataRecords.clear();
        _activeSessionRecords.clear();
        _topWaitEventsMap.clear();
        _sizeOnDiskRecords.clear();
        _platformInfo.clear();
        _sysstatRecords.clear();
        _numRACInstances = 0;
    }


    @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
    public void dumpData() {

        System.out.println("main metric headers");
        for (int i = 0; i < getHeaderCount(); i++) {
            System.out.print(getHeaderName(i) + ", ");
        }
        System.out.println();

        System.out.println("main metrics");
        for (Map.Entry<String, AWRRecord> entry : _dataRecords.entrySet()) {
            AWRRecord awrRec = entry.getValue();
            for (int j = 0; j < awrRec.getSize(); j++) {
                String headerName = getHeaderName(j);
                String val = awrRec.getVal(headerName);
                System.out.print(val + ", ");
            }
            System.out.println();
        }
        System.out.println("Avg Active Session Data");
        for (Map.Entry<String, AWRRecord> entry : _activeSessionRecords.entrySet()) {
            AWRRecord awrRec = entry.getValue();
            ArrayList<AvgActiveSessRecord> avgActiveSessData = awrRec.getAvgActiveSessData();
            for (int j = 0; j < avgActiveSessData.size(); j++) {
                String waitClass = avgActiveSessData.get(j).getWAIT_CLASS();
                String avgSess = avgActiveSessData.get(j).getAVG_SESS();
                System.out.println("snapId/waitClass/avgSess: " + awrRec.getSnapId() + "/" + waitClass + "/" + avgSess);
            }
        }
        System.out.println("Top Wait Events");
        System.out.println("WAIT_CLASS, EVENT_NAME, CUMM_EVENT_WAIT_TIME, CUMM_TOT_ALL_ENVENT_WAIT_TIMES, pct");
        for (Map.Entry<String, TopWaitEventsRecord> entry : _topWaitEventsMap.entrySet()) {
            TopWaitEventsRecord awrRec = entry.getValue();
            System.out.print(awrRec.getWaitClass() + ", ");
            System.out.print(awrRec.getEventName() + ", ");
            System.out.print(awrRec.getCummulativeWaitTime() + ", ");
            System.out.print(awrRec.getCummTotalAllWaitEventsTimeSec() + ", ");
            System.out.print(awrRec.getCummDBWaitTimePct());
            System.out.println();
        }
        System.out.println("Size On Disk Data");
        for (Map.Entry<String, AWRRecord> entry : _sizeOnDiskRecords.entrySet()) {
            AWRRecord awrRec = entry.getValue();
            for (int j = 0; j < awrRec.getSize(); j++) {
                String snapId = awrRec.getSnapId();
                String val = awrRec.getVal("SIZE_GB");
                System.out.println("snapId/val: " + snapId + "/" + val);
            }
        }
    }

    //Parse records read from the DB
    public void parseDataRecords(DBRecSet recSet) {

        for (int i = 0; i < recSet.getSize(); i++) {
            String racInstNum = "";
            String snapId = "";
            DBRec dbRec = recSet.getRec(i);
            AWRRecord awrRec = new AWRRecord();
            for (int j = 0; j < dbRec.size(); j++) {
                String dbFieldVal = dbRec.getAttrib(j).getValue();
                String dbFieldName = dbRec.getAttrib(j).getName();

                //Only Add header vals if this is the first record
                if (i == 0) {
                    this.addHeaderName(dbFieldName);
                }

                //Check to see if this is the header.  If so, add another header field for time.
                //The END field value has the format: "14/06/28 21:55"
                //we need to break it up into two fields END and TIME
                if (dbFieldName.toUpperCase().equals("END")) {
                    //Only Add header vals if this is the first record
                    if (i == 0) {
                        this.addHeaderName("TIME");
                    }
                    String endDateS = dbFieldVal.substring(0, 8);
                    String endTimeS = dbFieldVal.substring(9, 14);
                    awrRec.putVal("END", endDateS);
                    awrRec.putVal("TIME", endTimeS);
                } else {
                    awrRec.putVal(dbFieldName, dbFieldVal);
                }

                //Build the Key field
                if (dbFieldName.equals("SNAP"))
                    snapId = dbFieldVal;
                if (dbFieldName.equals("INST"))
                    racInstNum = dbFieldVal;
            }
            _dataRecords.put(snapId + "-" + racInstNum, awrRec);
            _activeSessionRecords.put(snapId, awrRec);
        }
    }

    /*
    TOP N WAIT EVENTS
    SNAP_ID WAIT_CLASS           EVENT_NAME                                                       PCTDBT TOTAL_TIME_S
    ---------- -------------------- ------------------------------------------------------------ ---------- ------------
       6673 DB CPU               DB CPU                                                            79.37   1162077955
       6673 User I/O             cell multiblock physical read                                      3.83     56095200
    */
    @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
    public void parseTopWaitEventsRecords(DBRecSet recSet) {
        for (int i = 0; i < recSet.getSize(); i++) {
            String waitClass = recSet.getRec(i).getAttribVal("WAIT_CLASS");
            String eventName = recSet.getRec(i).getAttribVal("EVENT_NAME");
            String totalTimeS = recSet.getRec(i).getAttribVal("TOTAL_TIME_S");
            double totalTimeD = Double.parseDouble(totalTimeS);

            TopWaitEventsRecord topWaitEventsRec = _topWaitEventsMap.get(eventName);
            if (topWaitEventsRec == null) {
                topWaitEventsRec = new TopWaitEventsRecord();
                topWaitEventsRec.setWaitClass(waitClass);
                topWaitEventsRec.setEventName(eventName);
                topWaitEventsRec.setCummulativeWaitTime(totalTimeD);
                double cummTotalAllWaitEventsTimeSec = topWaitEventsRec.getCummTotalAllWaitEventsTimeSec();
                cummTotalAllWaitEventsTimeSec += totalTimeD;
                topWaitEventsRec.setCummTotalAllWaitEventsTimeSec(cummTotalAllWaitEventsTimeSec);
                _topWaitEventsMap.put(eventName, topWaitEventsRec);
            } else {
                double cummWaitTime = topWaitEventsRec.getCummulativeWaitTime();
                @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
                double cummTotalAllWaitEventsTimeSec = topWaitEventsRec.getCummTotalAllWaitEventsTimeSec();
                cummTotalAllWaitEventsTimeSec += totalTimeD;
                cummWaitTime = cummWaitTime + totalTimeD;
                topWaitEventsRec.setCummulativeWaitTime(cummWaitTime);
                topWaitEventsRec.setCummTotalAllWaitEventsTimeSec(cummTotalAllWaitEventsTimeSec);
            }
            //Calculate the percentages
            for (Map.Entry<String, TopWaitEventsRecord> entry : _topWaitEventsMap.entrySet()) {
                TopWaitEventsRecord waitEventRec = entry.getValue();
                double cummEventWaitTime = waitEventRec.getCummulativeWaitTime();
                double cummTotalAllWaitTime = waitEventRec.getCummTotalAllWaitEventsTimeSec();
                double pct = (cummEventWaitTime / cummTotalAllWaitTime) * 100;
                waitEventRec.setCummDBWaitTimePct(pct);
            }

        }
    }

    /*
    SNAP_ID    SIZE_GB
    ---------- ----------
      23615    8885.98
    */
    public void parseSizeOnDiskRecords(DBRecSet recSet) {
        for (int i = 0; i < recSet.getSize(); i++) {

            DBRec dbRec = recSet.getRec(i);
            String snapId = dbRec.getAttribVal("SNAP_ID");
            String sizeOnDisk = dbRec.getAttribVal("SIZE_GB");
            //AWRRecord awrRec = this._dataRecords.get(snapId + "-1");
            AWRRecord storageRec = new AWRRecord();
            storageRec.putVal("SNAP_ID", snapId);
            storageRec.putVal("SIZE_GB", sizeOnDisk);
            _sizeOnDiskRecords.put(snapId, storageRec);
        }
    }

    /*
       SNAP_ID cell_flash_hits  read_iops    read_mb read_mb_opt cell_int_mb cell_int_ss_mb ehcc_con_dmls
    ---------- --------------- ---------- ---------- ----------- ----------- -------------- -------------
         35840           673.5      815.6      395.5       307.1       142.1            6.2         123.4
         35841           521.5      625.9      347.2       241.6       153.3            6.2          21.1
         35842          1604.8     1761.1      427.2       322.1       191.1            6.1         358.5
    */
    public void parseSysstatRecords(DBRecSet recSet) {
        for (int i = 0; i < recSet.getSize(); i++) {

            DBRec dbRec = recSet.getRec(i);
            String snapId = dbRec.getAttribVal("SNAP_ID");
            String cellFlashHits = dbRec.getAttribVal("CELL_FLASH_HITS");
            String readIOPS = dbRec.getAttribVal("READ_IOPS");
            String readMB = dbRec.getAttribVal("READ_MB");
            AWRRecord sysstatRec = new AWRRecord();
            sysstatRec.putVal("SNAP_ID", snapId);
            sysstatRec.putVal("CELL_FLASH_HITS", cellFlashHits);
            sysstatRec.putVal("READ_IOPS", readIOPS);
            sysstatRec.putVal("READ_MB", readMB);
            _sysstatRecords.put(snapId, sysstatRec);
        }
    }

    public AWRRecord getAWRRecordByKey(String snapId, String racInstNum) {
        AWRRecord awrRec = _dataRecords.get(snapId + "-" + racInstNum);
        return awrRec;
    }

    public LinkedHashSet<String> getUniqueSnapshotIds() {
        LinkedHashSet<String> snapIdSet = new LinkedHashSet<String>();

        for (Map.Entry<String, AWRRecord> entry : _dataRecords.entrySet()) {
            AWRRecord awrRec = entry.getValue();
            String snapId = awrRec.getSnapId();
            snapIdSet.add(snapId);
        }

        return snapIdSet;
    }

    public void parseAvgActiveSessionDataRecords(DBRecSet recSet) {
        /*
    SNAP_ID WAIT_CLASS             AVG_SESS
    ---------- -------------------- ----------
        777 Administrative                0
        777 Application                   0
    */
        for (int i = 0; i < recSet.getSize(); i++) {

            DBRec dbRec = recSet.getRec(i);
            String snapId = dbRec.getAttribVal("SNAP_ID");
            String waitClass = dbRec.getAttribVal("WAIT_CLASS");
            String avgSess = dbRec.getAttribVal("AVG_SESS");

            AWRRecord awrRec = _activeSessionRecords.get(snapId);
            if (awrRec != null) {
                awrRec.addAvgAcviteSessData(new AvgActiveSessRecord(waitClass, avgSess));
            }
        }
    }

    public void parseMemoryDataRecords(DBRecSet recSet) {
        /*
        snap_id, " +
                " instance_number, " +
                " MAX (DECODE (stat_name, \'SGA\', stat_value, NULL)) \"SGA\", " +
                " MAX (DECODE (stat_name, \'PGA\', stat_value, NULL)) \"PGA\", " +
                " MAX (DECODE (stat_name, 'SGA', stat_value, NULL)) + MAX (DECODE (stat_name, 'PGA', stat_value, " +
                " NULL)) \"TOTAL\" " +
*/
        for (int i = 0; i < recSet.getSize(); i++) {

            DBRec dbRec = recSet.getRec(i);
            String snapId = dbRec.getAttribVal("SNAP_ID");
            String racInstNum = dbRec.getAttribVal("INSTANCE_NUMBER");
            String sga = dbRec.getAttribVal("SGA");
            String pga = dbRec.getAttribVal("PGA");
            String memTot = dbRec.getAttribVal("SGA_PGA_TOT");
            
            AWRRecord awrRec = _dataRecords.get(snapId + "-" + racInstNum);
            //Check for Null.  Some files don't have all the snapshot IDs for memory records.
            if (awrRec != null) {
                //Only add the headers for the first record
                if (i == 0) {
                    this.addHeaderName("SGA");
                    this.addHeaderName("PGA");
                    this.addHeaderName("SGA_PGA_TOT");
                }
                awrRec.putVal("SGA", sga);
                awrRec.putVal("PGA", pga);
                awrRec.putVal("SGA_PGA_TOT", memTot);
            }
        }
    }

    public void parsePlatformInfoRecords(DBRecSet recSet) {
        
        for (int i = 0; recSet != null && i < recSet.getSize(); i++) {
            DBRec dbRec = recSet.getRec(i);
            String name = dbRec.getAttrib(0).getName();

            _platformInfo.put(name, dbRec);
        }
    }

    public DBRecSet getPlatformInfoRecs() {
        DBRecSet ret = new DBRecSet();
        Iterator it = this._platformInfo.entrySet().iterator();
        while (it.hasNext()) {
            ret.addRec((DBRec)it.next());
        }
        return ret;
    }

    public String getPlatformInfoHTML() {
        String ret = "<table>\n";
        for (Map.Entry<String, DBRec> entry : this._platformInfo.entrySet()) {
            DBRec dbRec = entry.getValue();
            ret += "<tr>\n";
            ret += "<td>\n";
            ret += dbRec.getAttrib(0).getName();
            ret += "</td>\n";
            ret += "<td>\n";
            ret += dbRec.getAttrib(0).getValue();
            ret += "</td>\n";
            ret += "</tr>\n";
       }
       ret += "</table>\n";        return ret;
    }

    public String getChartHeaderHTML() {

        String ret = "<table border=\"1\" font size=\"1\">";
        ret += "<tr>";
        ret += "<td>";
        ret += "<b>Chart Date</b>";
        ret += "</td>";
        ret += "<td>";
        ret += Calendar.getInstance().getTime().toString();
        ret += "</td>";
        ret += "</tr>";

        for (Map.Entry<String, DBRec> entry : this._platformInfo.entrySet()) {
            DBRec dbRec = entry.getValue();
            String name = dbRec.getAttrib(0).getName();

            switch (name) {
            case "DB_NAME":
                ret += "<tr><td><b>Database Name</b></td><td>" + dbRec.getAttribVal("DB_NAME") + "</td></tr>";
                break;
            case "INSTANCES":
                ret += "<tr><td><b>Instance(host) Count</b></td><td>" + dbRec.getAttribVal("INSTANCES") + "</td></tr>";
                break;
            case "HOSTS":
                ret += "<tr><td><b>Hosts</b></td><td>" + dbRec.getAttribVal("HOSTS") + "</td></tr>";
                break;
            case "NUM_CPUS":
                ret += "<tr><td><b>CPUs(threads)/host</b></td><td>" + dbRec.getAttribVal("NUM_CPUS") + "</td></tr>";
                break;
            case "NUM_CPU_CORES":
                ret += "<tr><td><b>CPU Cores/host</b></td><td>" + dbRec.getAttribVal("NUM_CPU_CORES") + "</td></tr>";
                break;
            case "NUM_CPU_SOCKETS":
                ret +=
                    "<tr><td><b>CPU Sockets/host</b></td><td>" + dbRec.getAttribVal("NUM_CPU_SOCKETS") + "</td></tr>";
                break;
            case "PHYSICAL_MEMORY_GB":
                ret +=
                    "<tr><td><b>Physical Memory/host(GB)</b></td><td>" + dbRec.getAttribVal("PHYSICAL_MEMORY_GB") +
                    "</td></tr>";
                break;
            default:
//                ret += "<tr><td><b>" + name + "</b></td><td>" + val + "</td></tr>";
                break;

            }
        }
        ret += "</table>";

        return ret;
    }

    public String getAWRDataTextString() {
        String ret = "";
        for (int i = 0; i < getHeaderCount(); i++) {
            ret += this.getHeaderName(i) + " ";
        }
        ret += "\n";

        for (Map.Entry<String, AWRRecord> entry : _dataRecords.entrySet()) {
            AWRRecord awrRec = entry.getValue();
            for (int j = 0; j < awrRec.getSize(); j++) {
                String headerName = getHeaderName(j);
                String val = awrRec.getVal(headerName);
                ret += val + " ";
            }
            ret += "\n";
        }
        return ret;
    }

    //Called when Reading from File
    public void parseHeaders(String rec) {
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                addHeaderName(token);

                //Check to see if this is the header.  If so, add another header field for time.
                if (token.equals("end") || token.equals("END")) {
                    addHeaderName("TIME");
                }
            }
        }
    }

    public ArrayList<AWRRecord> getAWRRecordArray() {
        return new ArrayList<AWRRecord>(_dataRecords.values());
    }

    public ArrayList<AWRRecord> getAvgActiveSessRecordArray() {
        return new ArrayList<AWRRecord>(_activeSessionRecords.values());
    }

    public ArrayList<AWRRecord> getSizeOnDiskRecordArray() {
        return new ArrayList<AWRRecord>(_sizeOnDiskRecords.values());
    }

    public ArrayList<TopWaitEventsRecord> getTopWaitEventsRecordArray() {
        return new ArrayList<TopWaitEventsRecord>(this._topWaitEventsMap.values());
    }

    public ArrayList<AWRRecord> getSysstatRecordArray() {
        return new ArrayList<AWRRecord>(this._sysstatRecords.values());
    }

    //Parse records read from AWR Files.
    public AWRRecord parseDataRecord(String rec) {
        AWRRecord awrRec = new AWRRecord();
        String racInstNum = "";
        String snapId = "";

        int tokenCnt = 0;
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                String headerName = getHeaderName(tokenCnt);
                awrRec.putVal(headerName.toUpperCase(), tok);
                if (headerName.equals("SNAP"))
                    snapId = tok;
                if (headerName.equals("INST"))
                    racInstNum = tok;
                tokenCnt++;
            }
        }
        _dataRecords.put(snapId + "-" + racInstNum, awrRec);
        _activeSessionRecords.put(snapId, awrRec);

        return awrRec;
    }

    public long getAWRDataRecordCount() {
        return _dataRecords.size();
    }

    private void addHeaderName(String name) {
        _headerTokens.add(name.toUpperCase());
    }

    private int getHeaderCount() {
        return _headerTokens.size();
    }

    private String getHeaderName(int i) {
        return (String) _headerTokens.get(i);
    }


    public boolean awrMetricExists(String metric) {
        boolean ret = false;

        if (metric.equals("AVG_ACTIVE_SESS_WAITS") && this._activeSessionRecords.size() > 0) {
            ret = true;
        } else if (metric.equals("TOP_N_TIMED_EVENTS") && this._topWaitEventsMap.size() > 0) {
            ret = true;
        } else if (metric.equals("SIZE_GB") && this._sizeOnDiskRecords.size() > 0) {
            ret = true;
        } else if (_headerTokens.contains(metric.toUpperCase())) {
            ret = true;
        }
        return ret;
    }

    public int getNumRACInstances() {
        if (this._numRACInstances == 0) {
            int numRacInst = 0;
            for (Map.Entry<String, AWRRecord> entry : _dataRecords.entrySet()) {
                AWRRecord awrRec = entry.getValue();
                if (awrRec.getInst() > numRacInst) {
                    numRacInst = awrRec.getInst();
                }
            }
            this._numRACInstances = numRacInst;
        }
        return this._numRACInstances;
    }

    public double getMetricSumForSnapshotId(String snapId, String awrMetricName) {
        double metricSum = 0;
        String metricValS = "";
        for (int i=1; i<=this._numRACInstances; i++) {
            AWRRecord awrRec = this.getAWRRecordByKey(snapId, Integer.toString(i));   
            if (awrRec != null) {
                metricValS = awrRec.getVal(awrMetricName);
                if (metricValS != null && metricValS.trim().length() > 0) {
                    metricSum = metricSum + Double.parseDouble(metricValS);
                }
            }
        }
        return metricSum;
    }

    public double getMetricMaxForSnapshotId(String snapId, String awrMetricName) {
        double metricMax = 0;
        String metricValS = "";
        for (int i=1; i<=this._numRACInstances; i++) {
            AWRRecord awrRec = this.getAWRRecordByKey(snapId, Integer.toString(i));
            if (awrRec != null) {
                metricValS = awrRec.getVal(awrMetricName);
                if (metricValS != null && metricValS.trim().length() > 0) {
                    double metricVal = Double.parseDouble(metricValS);
                    if (metricVal > metricMax) {
                        metricMax = metricVal;
                    }
                }
            }
        }
        return metricMax;
    }
    
    public void exportAWRData(String outFileName) throws Exception {
        Writer writer = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFileName);
            OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            writer = new BufferedWriter(streamWriter);

            //Write out header fields
            String outRec = "";
            for (int i = 0; i < getHeaderCount(); i++) {
                outRec += getHeaderName(i) + ",";
            }
            //Add the record seperator
            outRec += System.getProperty("line.separator");
            //Write the line to disk
            writer.write(outRec);


            //Write out the data records
            for (Map.Entry<String, AWRRecord> entry : _dataRecords.entrySet()) {
                outRec = "";
                AWRRecord awrRec = entry.getValue();
                for (int j = 0; j < awrRec.getSize(); j++) {
                    String headerName = getHeaderName(j);
                    String val = awrRec.getVal(headerName);
                    outRec += val + ", ";
                }
                //Add the record seperator
                outRec += System.getProperty("line.separator");

                //Write the line to disk
                writer.write(outRec);
            }


        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPlatformInfoAttribute(String infoName) {
        String ret = "";
        DBRec dbRec = this._platformInfo.get(infoName);
        if (dbRec != null) ret = dbRec.getAttrib(0).getValue();
        
        return ret;
    }
}
