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

    public DBRec testFile(String fileName, Date startDateFilter, Date endDateFilter, boolean doFilter) throws Exception {
        try {
            String rec = "";
            _fileReader = new BufferedReader(new FileReader(fileName));
            DBRec fileRecSet = null;

            try {
                //Priming read
                rec = _fileReader.readLine();
                Date lastDateTimeRead = null;
                Date firstDateTimeRead = null;
                boolean isValidFileFormat = false;

                while (rec != null) {
                    if (rec.startsWith("===Current Time===")) {
                        try {
                            Date dateTimeD = this.parseDateTime(rec);

                            //All we are doing right now is validating that we can find 
                            //a record that starts with "zzz ***", "zzz <" or "Time:" and the time format validates
                            isValidFileFormat = true;

                            if (doFilter) {
                                if (dateTimeD.after(startDateFilter) && dateTimeD.before(endDateFilter)) {
                                    if (firstDateTimeRead == null) {
                                        firstDateTimeRead = dateTimeD;
                                    }
                                    lastDateTimeRead = dateTimeD;
                                }
                            } else {
                                if (firstDateTimeRead == null) {
                                    firstDateTimeRead = dateTimeD;
                                }
                                lastDateTimeRead = dateTimeD;
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }

                    //Read the next record
                    rec = _fileReader.readLine();
                }

                if (firstDateTimeRead != null) {
                    fileRecSet = new DBRec();
                    fileRecSet.addAttrib(new DBAttributes("FILE_START_DATETIME", firstDateTimeRead.toString()));
                    fileRecSet.addAttrib(new DBAttributes("FILE_END_DATETIME", lastDateTimeRead.toString()));
                } else {
                        //Let's make sure we found at least one valid record regarless of the filter
                        if (!isValidFileFormat) {
                            throw new Exception ("Did not find any record descriptors of type: \"zzz ***\", \"zzz <\", or \"Time:\"");
                        }
                }
                
                return fileRecSet;

            } catch (Exception e) {
                System.out.println("IOSTAT File Read Error\n" + e.getLocalizedMessage());
                throw e;
            }

        } catch (FileNotFoundException fnfe) {
            throw new Exception("File Not Found: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            _fileReader.close();
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
                    Date recDateTime = this.parseDateTime(rec);
                    dbRec.addAttrib(new DBAttributes("DATE", recDateTime));

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
    private Date parseDateTime(String rec) {
        Date recDateTime = null;
        if (rec.length() > 0) {

            String recDateTimeS = rec.substring(60, rec.length()).trim();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd hh:mm:ss yyyy");
                recDateTime = dateFormat.parse(recDateTimeS);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return recDateTime;
    }

}
