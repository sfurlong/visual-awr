package org.altaprise.vawr.awrdata.file;


import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.OSWRecord;
import org.altaprise.vawr.charts.IOStatAvgCPUTimeSeriesChart;


public class ReadIOStatFile {

    private BufferedReader _fileReader;
    private String _osFlavor = null;
    private String _osVersion = null;
    private String _hostName = null;
    private String _fileDateS = null;

    public ReadIOStatFile() {

    }

    public static void main(String[] args) {
        try {
            ReadIOStatFile duFile = new ReadIOStatFile();
            duFile.parse("D:\\_NATO\\Excite-20-20\\capacity-tools\\OSW-Tool\\exa3db01.osc.us.oracle.com_iostat_14.01.20.1100.dat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(String fileName) throws Exception {
        try {
            
            _fileReader = new BufferedReader(new FileReader(fileName));
            readMainMetrics();
            new IOStatAvgCPUTimeSeriesChart("");
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            throw new Exception("File Not Found: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            _fileReader.close();
        }
    }

    private void readMainMetrics() throws Exception {
        String rec = "";
        String timeS = null;


        try {
            //Priming read
            rec = _fileReader.readLine();

            //Read the 2nd line that contains the machine name and date
            rec = _fileReader.readLine();
            this.parseHostNameAndDate(rec);


            int recCount = 0;
            boolean mainMetricsFound = false;

            while (rec != null && !mainMetricsFound) {
                if (rec.startsWith("Time:")) {
                    timeS = parseTime(rec);
                } else if (rec.startsWith("avg-cpu:")) {
                    DBRec avgCPURec = parseAvgCPU(_fileDateS, timeS);
                    OSWRecord.getInstance().addAvgCPUData(avgCPURec);
                } else if (rec.startsWith("Device:")) {
                    parseDevice(rec);
                } else {
                    //rec = _fileReader.readLine();
                }

                //Read the next record
                rec = _fileReader.readLine();
            }

            OSWRecord.getInstance().dump();

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    //Linux 2.6.32-400.21.1.el5uek (exa3db01.osc.us.oracle.com)       01/20/14
    private void parseHostNameAndDate(String rec) {

        int tokCnt = 0;


        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            DBAttributes dbAttribs = new DBAttributes();
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                tokCnt++;
                switch (tokCnt) {
                case 1:
                    tokCnt = 1;
                    _osFlavor = tok;
                    break;
                case 2:
                    tokCnt = 2;
                    _osVersion = tok;
                    break;
                case 3:
                    tokCnt = 3;
                    _hostName = tok;
                    break;
                case 4:
                    tokCnt = 4;
                    _fileDateS = tok;
                    break;
                }
            }
        }
    }

    private String parseTime(String rec) {
        //mainMetricsFound = true;
        String t = rec.substring(5).trim();
        System.out.println("Found Time: " + t);
        return t;
    }

    //Device:         rrqm/s   wrqm/s   r/s   w/s   rsec/s   wsec/s avgrq-sz avgqu-sz   await  svctm  %util
    private DBRec parseAvgCPU(String date, String time) throws Exception {
        //Read the next record
        String rec = _fileReader.readLine();
        DBRec dbRec = new DBRec();
        DBAttributes dateAttribs = new DBAttributes("DATE", date);
        DBAttributes timeAttribs = new DBAttributes("TIME", time);

        dbRec.addAttrib(dateAttribs);
        dbRec.addAttrib(timeAttribs);

        int tokCnt = 0;

        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                tokCnt++;
                DBAttributes dbAttribs = null;
                switch (tokCnt) {
                case 1:
                    tokCnt = 1;
                    dbAttribs = new DBAttributes("%user", tok);
                    break;
                case 2:
                    tokCnt = 2;
                    dbAttribs = new DBAttributes("%nice", tok);
                    break;
                case 3:
                    tokCnt = 2;
                    dbAttribs = new DBAttributes("%system", tok);
                    break;
                case 4:
                    tokCnt = 3;
                    dbAttribs = new DBAttributes("iowait", tok);
                    break;
                case 5:
                    tokCnt = 4;
                    dbAttribs = new DBAttributes("%steal", tok);
                    break;
                case 6:
                    tokCnt = 5;
                    dbAttribs = new DBAttributes("%idle", tok);
                    break;
                }
                dbRec.addAttrib(dbAttribs);
            }
        }
        return dbRec;
    }

    private void parseDevice(String rec) throws Exception {
        System.out.println(rec);

        //Read the next record
        String deviceRec = _fileReader.readLine();
        while (deviceRec != null && deviceRec.trim().length() > 0) {
            //System.out.println(deviceRec);
            deviceRec = _fileReader.readLine();
        }
    }
}
