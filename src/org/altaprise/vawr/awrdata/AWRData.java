package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AWRData {

    
    private List _headerTokens = new ArrayList();
    private BufferedReader _fileReader;
    private ArrayList<AWRRecord> _dataRecords = new ArrayList<AWRRecord>();

    public void dumpData() {

        for (int i = 0; i < getHeaderCount(); i++) {
            System.out.print(getHeaderName(i) + ", ");
        }
        System.out.println();

        for (int i = 0; i < _dataRecords.size(); i++) {

            for (int j = 0; j < _dataRecords.get(i).getSize(); j++) {
                String headerName = getHeaderName(j);
                AWRRecord rec = _dataRecords.get(i);
                String val = rec.getVal(headerName);
                System.out.print(val + ", ");
            }
            System.out.println();
        }

    }
    
    public void parseDataRecord(DBRecSet recSet) {
        
        for (int i = 0; i < 1; i++) {
            String rec = "";
            DBRec dbRec = recSet.getRec(i);
            AWRRecord awrRec = new AWRRecord();
            for (int j = 0; j < dbRec.size(); j++) {
                String dbFieldVal = dbRec.getAttrib(j).getValue();
                String dbFieldName = dbRec.getAttrib(j).getName();
                awrRec.putVal(dbFieldName, dbFieldVal);
            }
            _dataRecords.add(awrRec);
        }
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
    
    public AWRRecord parseDataRecord(String rec) {
        ArrayList<String> dataRecord = new ArrayList<String>();
        AWRRecord awrRec = new AWRRecord();

        int tokenCnt = 0;
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                String headerName = (String)getHeaderName(tokenCnt);
                awrRec.putVal(headerName.toUpperCase(), tok);
                //dataRecord.add(tok);
                tokenCnt++;
            }
        }
        _dataRecords.add(awrRec);
        return awrRec;
    }
    
    public AWRRecord getAWRRecord(int recNum) {
        return _dataRecords.get(recNum);
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
        return (String)_headerTokens.get(i);
    }
    
    public ArrayList<AWRRecord> getAWRData() {
        return _dataRecords;
    }
    
    public boolean awrMetricExists(String metric) {
        return _headerTokens.contains(metric.toUpperCase());
    }

    public int getNumRACInstances() {
        int numRacInst = 0;
        for (int i=0; i<_dataRecords.size(); i++){
            if (_dataRecords.get(i).getInst() > numRacInst) {
                numRacInst = _dataRecords.get(i).getInst();
            }
        }
        return numRacInst;
    }
}
