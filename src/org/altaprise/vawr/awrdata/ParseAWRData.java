package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ParseAWRData {

    
    AWRData _awrData = new AWRData();


    public ParseAWRData() {
        
    }
    public AWRData getAWRData() {
        return _awrData;    
    }
    

    public void dumpData() {

        for (int i = 0; i < _awrData.getHeaderCount(); i++) {
            System.out.print(_awrData.getHeaderName(i) + ", ");
        }
        System.out.println();

        for (int i = 0; i < _awrData.size(); i++) {

            for (int j = 0; j < _awrData.get(i).getSize(); j++) {
                System.out.print(_awrData.get(i).getVal((String)_awrData.getHeaderName(j)) + ", ");
            }
            System.out.println();
        }

    }
    
    public void parseHeaders(DBRecSet recSet) {
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
            _awrData.add(awrRec);
        }
    }
    
    public void parseHeaders(String rec) {
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                _awrData.addHeaderName(token);

                //Check to see if this is the header.  If so, add another header field for time.
                if (token.equals("end") || token.equals("END")) {
                    _awrData.addHeaderName("TIME");
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
                String headerName = (String)_awrData.getHeaderName(tokenCnt);
                awrRec.putVal(headerName.toUpperCase(), tok);
                //dataRecord.add(tok);
                tokenCnt++;
            }
        }
        _awrData.add(awrRec);
        return awrRec;
    }

}
