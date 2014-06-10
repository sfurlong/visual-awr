package org.altaprise.vawr.awrdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AWRRecord {
    
    HashMap _metricKeyVal = new HashMap();
    //snap      dur_m end                  inst     os_cpu os_cpu_max db_wait_ratio db_cpu_ratio   
    //public String snap = null;
    //public String dur_m = null;
    //public String end = null;
    //public String time = null;
    //public String inst = null;
    //public String os_cpu = null;
    //public String os_cpu_max = null;
    
    public void putVal(String key, String val) {
        _metricKeyVal.put(key, val);
    }
    
    public String getVal (String key) {
        return (String)_metricKeyVal.get(key);
    }
    
    public int getSize() {
        return _metricKeyVal.size();
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
        //System.out.println(theEnd.substring(3,5));
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
    
    public class AWRRec {
        
    }
    
}
