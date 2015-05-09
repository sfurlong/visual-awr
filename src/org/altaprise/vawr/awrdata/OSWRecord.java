package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBRec;

import dai.shared.businessObjs.DBRecSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OSWRecord {
    
    private HashMap _metricKeyVal = new HashMap();
    private ArrayList<DBRec> _avgCPUData = new ArrayList<DBRec>();
    
    private static OSWRecord _theInstance = null;

    private OSWRecord() {
    }

    public static OSWRecord getInstance() {
        if (_theInstance == null) {
            _theInstance = new OSWRecord();
        }
        return _theInstance;
    }
    
    
    public void dump() {
        System.out.println("date, time, %user, %nice, %system, %iowait , %steal, %idle");
        for (int i=0; i< _avgCPUData.size(); i++) {
            DBRec dbRec = _avgCPUData.get(i);
            for (int j = 0; j < dbRec.size(); j++) {
                System.out.print(dbRec.getAttrib(j).getValue() + ",");
            }
            System.out.println();
        }
    }
    
    public void addAvgCPUData(DBRec avgCPURec) {
        _avgCPUData.add(avgCPURec);
    }
    
    public ArrayList<DBRec> getAvgCPUData() {
        return _avgCPUData;
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
    
    
    public Date getAvgCPUDateTime() {
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
    
}
