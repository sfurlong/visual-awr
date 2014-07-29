package org.altaprise.vawr.awrdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AWRRecord {
    
    private HashMap _metricKeyVal = new HashMap();
    private ArrayList<AvgActiveSessRecord> _avgActiveSess = new ArrayList<AvgActiveSessRecord>();
    
    public void addAvgAcviteSessData(AvgActiveSessRecord sessData) {
        _avgActiveSess.add(sessData);
    }
    
    public ArrayList<AvgActiveSessRecord> getAvgActiveSessData() {
        return _avgActiveSess;
    }
    
    public void putVal(String key, String val) {
        _metricKeyVal.put(key, val);
    }
    
    public String getVal (String key) {
        return (String)_metricKeyVal.get(key);
    }
    
    public int getSize() {
        return _metricKeyVal.size();
    }
    
    public String getSnapId() {
        String snapIdKeyName = "SNAP";
        return (String)_metricKeyVal.get(snapIdKeyName.toUpperCase());    
    }


    public int getInst() {
        String instKeyName = "inst";
        return Integer.parseInt((String)_metricKeyVal.get(instKeyName.toUpperCase()));    
    }
    
    public Date getSnapShotDateTime() {
        //12/02/24 00:59
        String theEnd = this.getVal("END");
        String theTime = this.getVal("TIME");
        Calendar cal = Calendar.getInstance();
        int yy = Integer.parseInt(theEnd.substring(0, 2)) + 2000;
        int mm = Integer.parseInt(theEnd.substring(3, 5)) - 1;
        int dd = Integer.parseInt(theEnd.substring(6, 8));
        int hh = Integer.parseInt(theTime.substring(0, 2));
        int min = Integer.parseInt(theTime.substring(3, 5));
        cal.set(yy, mm, dd, hh, min);
        Date date = cal.getTime();
            
        return date;
    }
    
    public AWRRecord() {
        super();
    }
}
