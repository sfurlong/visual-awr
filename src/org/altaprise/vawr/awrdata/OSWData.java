package org.altaprise.vawr.awrdata;

import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;

import dai.shared.businessObjs.DBRecSet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OSWData {

    private ArrayList<DBRec> _topStatRecs = new ArrayList<DBRec>();
    private ArrayList<DBRec> _cellSrvStatRecs = new ArrayList<DBRec>();
    private ArrayList<DBRec> _ioStatRecs = new ArrayList<DBRec>();
    private ArrayList<DBRec> _vmStatRecs = new ArrayList<DBRec>();
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
        //System.out.println("r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st");
        for (int i = 0; i < _topStatRecs.size(); i++) {
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
        for (int i = 0; i < _cellSrvStatRecs.size(); i++) {
            DBRec dbRec = _cellSrvStatRecs.get(i);
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
        for (int i = 0; i < _ioStatRecs.size(); i++) {
            DBRec dbRec = _ioStatRecs.get(i);
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
        for (int i = 0; i < _vmStatRecs.size(); i++) {
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

    public void clearData() {
        _topStatRecs.clear();
        _cellSrvStatRecs.clear();
        _ioStatRecs.clear();
        _vmStatRecs.clear();
        _platformType = "";
    }

    public void addTopStatRec(DBRec topStatRec) {
        _topStatRecs.add(topStatRec);
    }

    public void addCellSrvStatRec(DBRec cellSrvStatRec) {
        _cellSrvStatRecs.add(cellSrvStatRec);
    }

    public void addIoStatRec(DBRec ioStatRec) {
        _ioStatRecs.add(ioStatRec);
    }

    public void addVmStatRec(DBRec vmStatRec) {
        _vmStatRecs.add(vmStatRec);
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

    public ArrayList<DBRec> getCellSrvStatRecs() {
        return _cellSrvStatRecs;
    }

    public ArrayList<DBRec> getIoStatRecs() {
        return _ioStatRecs;
    }

    public ArrayList<DBRec> getVmStatRecs() {
        return _vmStatRecs;
    }

    public void exportTopStatData(String outFileName) throws Exception  {
        exportOswStatData(_topStatRecs, outFileName);
    }

    public void exportIoStatData(String outFileName) throws Exception {
        exportOswStatData(_ioStatRecs, outFileName);
    }
    
    public void exportCellSrvStatData(String outFileName) throws Exception {
        exportOswStatData(_cellSrvStatRecs, outFileName);
    }

    public void exportVmStatData(String outFileName) throws Exception {
        exportOswStatData(_vmStatRecs, outFileName);
    }

    private void exportOswStatData(ArrayList<DBRec> dbRecList, String outFileName) throws Exception {
        Writer writer = null;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFileName);
            OutputStreamWriter streamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
            writer = new BufferedWriter(streamWriter);
            DBRec dbRec = null;
            
            //Write out header fields
            dbRec = dbRecList.get(0);
            String outRec = "";
            for (int j = 0; j < dbRec.size(); j++) {
                outRec += dbRec.getAttrib(j).getName() + ",";
            }
            //Add the record seperator
            outRec += System.getProperty("line.separator");
            //Write the line to disk
            writer.write(outRec);


            //Write out the data records
            for (int i = 0; i < dbRecList.size(); i++) {

                dbRec = dbRecList.get(i);
                outRec = "";
                for (int j = 0; j < dbRec.size(); j++) {
                    DBAttributes dbAttribs = dbRec.getAttrib(j);
                    if (dbAttribs.getName().equals("DATE")) {
                        outRec += dbRec.getAttrib(j).getObjValue() + ",";
                    } else {
                        outRec += dbRec.getAttrib(j).getValue() + ",";
                    }
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
}
