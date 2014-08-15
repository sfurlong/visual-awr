package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.StringTokenizer;

import org.altaprise.vawr.utils.PropertyFile;

public class AWRData {


    private List _headerTokens = new ArrayList();
    private LinkedHashMap<String, AWRRecord> _dataRecords = new LinkedHashMap<String, AWRRecord>();
    private LinkedHashMap<String, AWRRecord> _activeSessionRecords = new LinkedHashMap<String, AWRRecord>();
    private HashMap<String, TopWaitEventsRecord> _topWaitEventsMap = new HashMap<String, TopWaitEventsRecord>();

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
    }


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
    }

    public void parseDataRecords(DBRecSet recSet) {

        for (int i = 0; i < recSet.getSize(); i++) {
            String rec = "";
            String racInstNum = "";
            String snapId = "";
            DBRec dbRec = recSet.getRec(i);
            AWRRecord awrRec = new AWRRecord();
            for (int j = 0; j < dbRec.size(); j++) {
                String dbFieldVal = dbRec.getAttrib(j).getValue();
                String dbFieldName = dbRec.getAttrib(j).getName();

                this.addHeaderName(dbFieldName);

                //Check to see if this is the header.  If so, add another header field for time.
                if (dbFieldName.toUpperCase().equals("END")) {
                    //The END field value has the format: "14/06/28 21:55"
                    //we need to break it up into two fields END and TIME
                    this.addHeaderName("TIME");
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
            awrRec.addAvgAcviteSessData(new AvgActiveSessRecord(waitClass, avgSess));
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
            this.addHeaderName("SGA");
            awrRec.putVal("SGA", sga);
            this.addHeaderName("PGA");
            awrRec.putVal("PGA", pga);
            this.addHeaderName("SGA_PGA_TOT");
            awrRec.putVal("SGA_PGA_TOT", memTot);
        }
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

    public ArrayList<TopWaitEventsRecord> getTopWaitEventsRecordArray() {
        return new ArrayList<TopWaitEventsRecord>(this._topWaitEventsMap.values());
    }

    public AWRRecord parseDataRecord(String rec) {
        AWRRecord awrRec = new AWRRecord();
        String racInstNum = "";
        String snapId = "";

        int tokenCnt = 0;
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                String headerName = (String) getHeaderName(tokenCnt);
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
        } else if (_headerTokens.contains(metric.toUpperCase())) {
            ret = true;
        } 
        return ret;
    }

    public int getNumRACInstances() {
        int numRacInst = 0;
        for (Map.Entry<String, AWRRecord> entry : _dataRecords.entrySet()) {
            AWRRecord awrRec = entry.getValue();
            if (awrRec.getInst() > numRacInst) {
                numRacInst = awrRec.getInst();
            }
        }
        return numRacInst;
    }
}
