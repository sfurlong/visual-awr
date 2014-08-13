package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;

import dai.shared.businessObjs.DBRecSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class VMStatData {
    
    private HashMap _metricKeyVal = new HashMap();
    private ArrayList<AvgActiveSessRecord> _avgActiveSess = new ArrayList<AvgActiveSessRecord>();
    private ArrayList<DBRec> _vmStatRecs = new ArrayList<DBRec>();
    
    private static VMStatData _theInstance = null;

    private VMStatData() {
    }

    public static VMStatData getInstance() {
        if (_theInstance == null) {
            _theInstance = new VMStatData();
        }
        return _theInstance;
    }
    
    
    public void dump() {
        System.out.println("r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st");
        for (int i=0; i< _vmStatRecs.size(); i++) {
            DBRec dbRec = _vmStatRecs.get(i);
            for (int j = 0; j < dbRec.size(); j++) {
                DBAttributes dbAttribs = dbRec.getAttrib(j);
                if (dbAttribs.getName().equals("DATE")) {
                    System.out.print(dbRec.getAttrib(j).getObjValue() + ",");
                } else {                    
                    System.out.print(dbRec.getAttrib(j).getValue() + ",");
                }
            }
            System.out.println();
        }
    }
    
    public void addVMStatRec(DBRec vmStatRec) {
        _vmStatRecs.add(vmStatRec);
    }
    
    public ArrayList<DBRec> getVMStatRecs() {
        return _vmStatRecs;
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
