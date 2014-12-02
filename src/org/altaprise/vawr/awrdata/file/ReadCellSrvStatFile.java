package org.altaprise.vawr.awrdata.file;


import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.OSWMetrics;
import org.altaprise.vawr.charts.TopStatTimeSeriesChart;


public class ReadCellSrvStatFile {

    private BufferedReader _fileReader;
    private String _platformType = "";
    private String _topFileType = "";


    public ReadCellSrvStatFile() {

    }

    public static void main(String[] args) {
        try {
            ReadCellSrvStatFile duFile = new ReadCellSrvStatFile();
            //duFile.parse("C:/Git/visual-awr/testing/oswatcher/osc2cn01-d1_top_14.09.10.0400.dat");
            duFile.parse("C:/Git/visual-awr/testing/oswatcher/CellSrvStat/2014_09_10_04_09_58_CellSrvStatExaWatcher_osc2es01.osc.us.oracle.com.dat");
            OSWData.getInstance().dump();
            //new TopStatTimeSeriesChart("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(String fileName) throws Exception {
        try {

            _fileReader = new BufferedReader(new FileReader(fileName));
            readMainMetrics();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            throw new Exception("File Not Found: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (_fileReader != null) {
                _fileReader.close();
            }
        }
    }

    private void readMainMetrics() throws Exception {
        String rec = "";


        try {
            //Priming read
            rec = _fileReader.readLine();

            int recCount = 0;
            DBRec dbRec = null;
            boolean firstRead = true;
            while (rec != null) {

                if (rec.startsWith("===Current Time===")) {
                    if (!firstRead) {
                        OSWData.getInstance().addCellSrvStatRec(dbRec);
                    }
                    //Create the record structure
                    dbRec = new DBRec();
                    //Parse the datetime and add the attributes to the dbRec
                    this.parseDateTime(rec, dbRec);

                } else {
                    if (rec.length() > 60) {
                        String metric = rec.substring(0, 60).trim();
                        if (OSWMetrics.getInstance().oswMetricExists(metric)) {
                            firstRead = false;
                            String metricValS = rec.substring(53, 66).trim();
                            String metricName = rec.substring(0, 52).trim();
                            dbRec.addAttrib(new DBAttributes(metricName, metricValS));
                        }
                    }
                }

                //Read the next record
                rec = _fileReader.readLine();
            }
            OSWData.getInstance().addCellSrvStatRec(dbRec);
        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    //===Current Time===                                      Wed Sep 10 04:09:58 2014
    private void parseDateTime(String rec, DBRec dbRec) {

        if (rec.length() > 0) {

            String recDateTimeS = rec.substring(60, rec.length()).trim();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd hh:mm:ss yyyy");
                Date recDateTime = dateFormat.parse(recDateTimeS);

                dbRec.addAttrib(new DBAttributes("DATE", recDateTime));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
