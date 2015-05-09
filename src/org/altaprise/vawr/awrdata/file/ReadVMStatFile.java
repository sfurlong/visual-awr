package org.altaprise.vawr.awrdata.file;


import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.charts.VMStatTimeSeriesChart;
import org.altaprise.vawr.utils.SessionMetaData;


public class ReadVMStatFile {

    private BufferedReader _fileReader;
    private String _baseDateTimeS = null;
    private int _intervalSeconds = 0;
    private Date _cumulativeDateTime = null;
    ArrayList<String> _headers = new ArrayList<String>();

    public ReadVMStatFile() {

    }

    public static void main(String[] args) {
        try {
            ReadVMStatFile duFile = new ReadVMStatFile();
            duFile.parse("D:\\_NATO\\Excite-20-20\\capacity-tools\\OSW-Tool\\exa3cel04.osc.us.oracle.com_vmstat_14.01.20.1100.dat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(String fileName) throws Exception {
        try {

            _fileReader = new BufferedReader(new FileReader(fileName));
            readMainMetrics();
            OSWData.getInstance().dump();
            new VMStatTimeSeriesChart("", "");
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

            int recCount = 0;

            while (rec != null) {
                if (rec.startsWith("zzz")) {
                    this.parseDateTime(rec);

                } else if (rec.startsWith("procs")) {
                    //Skip the previous header and Read the next
                    rec = _fileReader.readLine();
                    this.parseHeaders(rec);

                } else {
                    DBRec dbRec = this.parseDataRec(rec);
                    this.addCumulativeTimeInterval();
                    dbRec.addAttrib(new DBAttributes("DATE", _cumulativeDateTime));
                    OSWData.getInstance().addVmStatRec(dbRec);

                }

                //Read the next record
                rec = _fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void addCumulativeTimeInterval() {
        //Add 5 seconds
        Calendar cal = Calendar.getInstance();
        cal.setTime(_cumulativeDateTime);
        cal.add(Calendar.SECOND, _intervalSeconds);
        _cumulativeDateTime = cal.getTime();
    }
    
    //zzz ***Mon Jan 20 11:01:56 PST 2014 Sample interval 5 seconds
    private void parseDateTime(String rec) {

        if (rec.length() > 0) {

            _baseDateTimeS = rec.substring(11, 35);
            String intervalSecondsS = rec.substring(52, 53);

            try {
                _intervalSeconds = Integer.parseInt(intervalSecondsS);
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd hh:mm:ss zzz yyyy");
                _cumulativeDateTime = dateFormat.parse(_baseDateTimeS);
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
    private void parseHeaders(String rec) {
        int tokCnt = 0;

        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                tokCnt++;
                _headers.add(tok);
            }
        }
    }

    // r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
    private DBRec parseDataRec(String rec) {
        int tokIdx = 0;

        DBRec dbRec = new DBRec();
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);

            while (st.hasMoreTokens()) {

                String tok = st.nextToken();
                DBAttributes dbAttribs = new DBAttributes(_headers.get(tokIdx), tok);
                dbRec.addAttrib(dbAttribs);
                tokIdx++;

            }
        }
        return dbRec;
    }
}
