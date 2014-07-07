package org.altaprise.vawr.awrdata.file;


import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRRecord;


public class ReadAWRMinerFile {

    private BufferedReader _fileReader;
    
    public ReadAWRMinerFile() {
        
    }

    public void parse(String fileName) throws Exception {
        try {
            _fileReader = new BufferedReader(new FileReader(fileName));

            readData();
        } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
                throw new Exception("File Not Found: "+fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void parseMemData(String fileName) throws Exception {
        try {
            _fileReader = new BufferedReader(new FileReader(fileName));

            readMemoryData();
        } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
                throw new Exception("File Not Found: "+fileName);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void readData() throws Exception {
        String rec = "";
        try {
            //Priming read
            rec = _fileReader.readLine();
            int recCount = 0;
            boolean mainMetricsFound = false;

            while (rec != null && !mainMetricsFound) {
                if (rec.equals("~~BEGIN-MAIN-METRICS~~")) {
                    mainMetricsFound = true;
                    rec = _fileReader.readLine();
                } else
                    //Read the next record
                    rec = _fileReader.readLine();
            }
            boolean endMainMetricsFound = false;
            while (rec != null && mainMetricsFound && !endMainMetricsFound) {
                rec = rec.trim();

                //Skip comment lines.
                if (rec.length() > 0) {
                    recCount++;
                    //System.out.println(rec);


                    if (recCount == 1) {
                        //Parse the headers
                        AWRData.getInstance().parseHeaders(rec);
                    } else if (recCount == 2) {
                        //Skip the row in the file that contains the dashes.
                        //Do nothing
                    } else {
                        if (rec.equals("~~END-MAIN-METRICS~~")) {
                            endMainMetricsFound = true;
                        } else {
                            //Parse the data rows
                            AWRData.getInstance().parseDataRecord(rec);
                        }
                    }

                }
                //Read the next record
                rec = _fileReader.readLine();
            }

            _fileReader.close();

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" +
                    e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void readMemoryData() throws Exception {
        String rec = "";
        DBRecSet memRecSet = new DBRecSet();

        try {
            //Priming read
            rec = _fileReader.readLine();
            int recCount = 0;
            boolean mainMetricsFound = false;

            while (rec != null && !mainMetricsFound) {
                if (rec.equals("~~BEGIN-MEMORY~~")) {
                    mainMetricsFound = true;
                    rec = _fileReader.readLine();
                } else
                    //Read the next record
                    rec = _fileReader.readLine();
            }
            boolean endMemoryMetricsFound = false;
            while (rec != null && mainMetricsFound && !endMemoryMetricsFound) {
                rec = rec.trim();

                //Skip comment lines.
                if (rec.length() > 0) {
                    recCount++;
                    //System.out.println(rec);


                    if (recCount == 1) {
                        //Skip the headers
                        //Do Nothing
                    } else if (recCount == 2) {
                        //Skip the row in the file that contains the dashes.
                        //Do nothing
                    } else {
                        if (rec.equals("~~END-MEMORY~~")) {
                            endMemoryMetricsFound = true;
                        } else {
                            //Parse the data rows
                            DBRec memDBRec = this.createMemoryDataDBRec(rec);
                            memRecSet.addRec(memDBRec);
                        }
                    }
                }
                //Read the next record
                rec = _fileReader.readLine();

            }

            _fileReader.close();
            
            AWRData.getInstance().parseMemoryDataRecords(memRecSet);

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" +
                    e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

/*
    SELECT snap_id, " +
            " instance_number, " +
            " MAX (DECODE (stat_name, \'SGA\', stat_value, NULL)) \"SGA\", " +
            " MAX (DECODE (stat_name, \'PGA\', stat_value, NULL)) \"PGA\", " +
            " MAX (DECODE (stat_name, 'SGA', stat_value, NULL)) + MAX (DECODE (stat_name, 'PGA', stat_value, " +
            " NULL)) \"SGA_PGA_TOT\" " +
*/
    private DBRec createMemoryDataDBRec(String rec) {
        
        int tokCnt = 0;
        DBRec memDBRec = new DBRec();

        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                tokCnt++;
                DBAttributes dbAttribs = null;
                switch (tokCnt) {
                            case 1:  tokCnt = 1;
                                dbAttribs = new DBAttributes("SNAP_ID", tok);
                                break;
                            case 2:  tokCnt = 2;
                                dbAttribs = new DBAttributes("INSTANCE_NUMBER", tok);
                                break;
                            case 3:  tokCnt = 3;
                                dbAttribs = new DBAttributes("SGA", tok);
                                break;
                            case 4:  tokCnt = 4;
                                dbAttribs = new DBAttributes("PGA", tok);
                                break;
                            case 5:  tokCnt = 5;
                                dbAttribs = new DBAttributes("SGA_PGA_TOT", tok);
                                break;
                        }                                 
                memDBRec.addAttrib(dbAttribs);
            }
        }
        return memDBRec;
    }

    public void dumpData() {
        AWRData.getInstance().dumpData();
    }

}
