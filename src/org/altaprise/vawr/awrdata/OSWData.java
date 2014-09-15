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

public class OSWData {
    
    private ArrayList<DBRec> _topStatRecs = new ArrayList<DBRec>();
    private static OSWData _theInstance = null;
    private String _platformType = "";

    private OSWData() {
    }

    public static OSWData getInstance() {
        if (_theInstance == null) {
            _theInstance = new OSWData();
        }
        return _theInstance;
    }
    
    
    public void dump() {
        System.out.println("r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st");
        for (int i=0; i< _topStatRecs.size(); i++) {
            DBRec dbRec = _topStatRecs.get(i);
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

    public void clearData() {
        _topStatRecs.clear();
        _platformType = "";
    }
    
    public void addTopStatRec(DBRec topStatRec) {
        _topStatRecs.add(topStatRec);
    }

    public void setPlatformType(String type) {
        _platformType = type;
    }
    
    public String getPlatformType() {
        return _platformType;
    }

    public ArrayList<DBRec> getTopStatRecs() {
        return _topStatRecs;
    }
}
