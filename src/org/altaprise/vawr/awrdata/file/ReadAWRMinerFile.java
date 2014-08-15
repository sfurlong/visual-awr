package org.altaprise.vawr.awrdata.file;


import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.AWRData;


public class ReadAWRMinerFile {

    private BufferedReader _fileReader;

    public ReadAWRMinerFile() {

    }

    public void parse(String fileName) throws Exception {
        try {
            _fileReader = new BufferedReader(new FileReader(fileName));
            readMachineInfo();
            readMainMetrics();
            readAverageActiveSessions();
            readIOWaitMetrics();
            readTopTimedEvents();
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

    public void parseMemData(String fileName) throws Exception {
        try {
            _fileReader = new BufferedReader(new FileReader(fileName));

            readMemoryData();
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

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /*
       SNAP_ID WAIT_CLASS             AVG_SESS
    ---------- -------------------- ----------
           777 Administrative                0
           777 Application                   0
           777 Cluster                       0
           777 Commit                        0
           777 Concurrency                   0
           777 Configuration                 0
           777 DB CPU                      .21
    */
    private void readAverageActiveSessions() throws Exception {
        String rec = "";
        DBRecSet avgActiveSessionRecSet = new DBRecSet();
        try {
            //Priming read
            rec = _fileReader.readLine();
            int recCount = 0;
            boolean sectionStartFound = false;

            while (rec != null && !sectionStartFound) {
                if (rec.equals("~~BEGIN-AVERAGE-ACTIVE-SESSIONS~~")) {
                    sectionStartFound = true;
                    rec = _fileReader.readLine();
                } else
                    //Read the next record
                    rec = _fileReader.readLine();
            }
            boolean sectionEndFound = false;
            while (rec != null && sectionStartFound && !sectionEndFound) {

                //Skip comment lines.
                if (rec.trim().length() > 0) {
                    recCount++;

                    if (recCount == 1) {
                        //Parse the headers
                        AWRData.getInstance().parseHeaders(rec);
                    } else if (recCount == 2) {
                        //Skip the row in the file that contains the dashes.
                        //Do nothing


                    } else {
                        if (rec.equals("~~END-AVERAGE-ACTIVE-SESSIONS~~")) {
                            sectionEndFound = true;
                        } else {
                            //Parse the data rows
                            DBRec avgActiveSessionDBRec = this.createAvgActiveSessionDBRec(rec);
                            avgActiveSessionRecSet.addRec(avgActiveSessionDBRec);
                        }
                    }

                }
                //Read the next record
                rec = _fileReader.readLine();
            }

            AWRData.getInstance().parseAvgActiveSessionDataRecords(avgActiveSessionRecSet);

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /*
    SNAP_ID WAIT_CLASS           EVENT_NAME                            WAIT_TIME_MILLI WAIT_COUNT
    ---------- -------------------- ------------------------------------- --------------- ----------
        777 User I/O             cell list of blocks physical read                   1         22
        777 User I/O             cell list of blocks physical read                   2          1
        777 User I/O             cell list of blocks physical read                   8          1
    */
    private void readIOWaitMetrics() throws Exception {
        String rec = "";
        try {
            //Priming read
            rec = _fileReader.readLine();
            int recCount = 0;
            boolean sectionStartFound = false;

            while (rec != null && !sectionStartFound) {
                if (rec.equals("~~BEGIN-IO-WAIT-HISTOGRAM~~")) {
                    sectionStartFound = true;
                    rec = _fileReader.readLine();
                } else
                    //Read the next record
                    rec = _fileReader.readLine();
            }
            boolean sectionEndFound = false;
            while (rec != null && sectionStartFound && !sectionEndFound) {
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
                        if (rec.equals("~~END-IO-WAIT-HISTOGRAM~~")) {
                            sectionEndFound = true;
                        } else {
                            //Parse the data rows
                            //AWRData.getInstance().parseDataRecord(rec);
                            //System.out.println(rec);
                        }
                    }

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

    /*
    TOP N WAIT EVENTS
    SNAP_ID WAIT_CLASS           EVENT_NAME                                                       PCTDBT TOTAL_TIME_S
    ---------- -------------------- ------------------------------------------------------------ ---------- ------------
       6673 DB CPU               DB CPU                                                            79.37   1162077955
       6673 User I/O             cell multiblock physical read                                      3.83     56095200
    */
    private void readTopTimedEvents() throws Exception {
        String rec = "";
        try {
            //Priming read
            rec = _fileReader.readLine();
            int recCount = 0;
            boolean sectionStartFound = false;
            DBRecSet topTimedEventsRecSet = new DBRecSet();

            while (rec != null && !sectionStartFound) {
                if (rec.equals("~~BEGIN-TOP-N-TIMED-EVENTS~~")) {
                    sectionStartFound = true;
                    //Read the following blank line
                    rec = _fileReader.readLine();
                } else
                    //Read the next record
                    rec = _fileReader.readLine();
            }
            boolean sectionEndFound = false;
            while (rec != null && sectionStartFound && !sectionEndFound) {

                //Skip comment lines.
                if (rec.trim().length() > 0) {
                    recCount++;
                    //System.out.println(rec);

                    if (recCount == 1) {
                        //Parse the headers
                        AWRData.getInstance().parseHeaders(rec);
                    } else if (recCount == 2) {
                        //Skip the row in the file that contains the dashes.
                        //Do nothing


                    } else {
                        if (rec.equals("~~END-TOP-N-TIMED-EVENTS~~")) {
                            sectionEndFound = true;
                        } else {
                            //Parse the data rows
                            DBRec topTimedEventsDBRec = this.createTopTimedEventsDBRec(rec);
                            topTimedEventsRecSet.addRec(topTimedEventsDBRec);
                        }
                    }
                }
                //Read the next record
                rec = _fileReader.readLine();
            }

            AWRData.getInstance().parseTopWaitEventsRecords(topTimedEventsRecSet);

        } catch (Exception e) {
            System.out.println("readTopTimedEvents\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /*
    ~~BEGIN-OS-INFORMATION~~
    STAT_NAME                                                    STAT_VALUE
    ------------------------------------------------------------ ------------------------------------------------------------
    NUM_CPUS                                                     24
    NUM_CPU_CORES                                                12
    NUM_CPU_SOCKETS                                              2
    PHYSICAL_MEMORY_GB                                           94.47
    PLATFORM_NAME                                                Linux_x86_64-bit
    VERSION                                                      11.2.0.3.0
    DB_NAME                                                      EDWPRD
    INSTANCES                                                    4
    HOSTS                                                        x01pdb01,x01pdb02,x01pdb03,x01pdb04
    AWR_MINER_VER                                                3.0.7
    ~~END-OS-INFORMATION~~
    */
    private void readMachineInfo() throws Exception {
        String rec = "";
        try {
            //Priming read
            rec = _fileReader.readLine();
            int recCount = 0;
            boolean startOfSectionFound = false;

            while (rec != null && !startOfSectionFound) {
                if (rec.equals("~~BEGIN-OS-INFORMATION~~")) {
                    startOfSectionFound = true;
                    rec = _fileReader.readLine();
                } else
                    //Read the next record
                    rec = _fileReader.readLine();
            }
            boolean endOfSectionFound = false;
            while (rec != null && startOfSectionFound && !endOfSectionFound) {
                rec = rec.trim();

                //Skip comment lines.
                if (rec.length() > 0) {
                    recCount++;
                    //System.out.println(rec);


                    if (recCount == 1) {
                        //Skip the headers


                    } else if (recCount == 2) {
                        //Skip the row in the file that contains the dashes.
                        //Do nothing


                    } else {
                        if (rec.equals("~~END-OS-INFORMATION~~")) {
                            endOfSectionFound = true;
                        } else {
                            //Parse the data rows
                            System.out.println(rec);
                            createMachineInfoDBRec(rec);
                        }
                    }

                }
                //Read the next record
                rec = _fileReader.readLine();
            }

            //AWRData.getInstance().parseMemoryDataRecords(memRecSet);


        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
            e.printStackTrace();
            throw e;
        }
    }

    /*
       SNAP_ID INSTANCE_NUMBER        SGA        PGA      TOTAL
    ---------- --------------- ---------- ---------- ----------
           776               1        201        1.4      202.4
           776               2        201        1.6      202.6
   */
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

            AWRData.getInstance().parseMemoryDataRecords(memRecSet);

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" + e.getLocalizedMessage());
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
                case 1:
                    tokCnt = 1;
                    dbAttribs = new DBAttributes("SNAP_ID", tok);
                    break;
                case 2:
                    tokCnt = 2;
                    dbAttribs = new DBAttributes("INSTANCE_NUMBER", tok);
                    break;
                case 3:
                    tokCnt = 3;
                    dbAttribs = new DBAttributes("SGA", tok);
                    break;
                case 4:
                    tokCnt = 4;
                    dbAttribs = new DBAttributes("PGA", tok);
                    break;
                case 5:
                    tokCnt = 5;
                    dbAttribs = new DBAttributes("SGA_PGA_TOT", tok);
                    break;
                }
                memDBRec.addAttrib(dbAttribs);
            }
        }
        return memDBRec;
    }

    /*
       SNAP_ID WAIT_CLASS             AVG_SESS
    ---------- -------------------- ----------
           777 Administrative                0
           777 Application                   0
           777 Cluster                       0
           777 Commit                        0
           777 Concurrency                   0
           777 Configuration                 0
           777 DB CPU                      .21
    */
    private DBRec createAvgActiveSessionDBRec(String rec) {

        int tokCnt = 0;
        DBRec avgActiveSessDBRec = new DBRec();

        if (rec.length() > 0) {

            String snapId = rec.substring(0, 11).trim();
            DBAttributes snapIdAttribs = new DBAttributes("SNAP_ID", snapId);
            avgActiveSessDBRec.addAttrib(snapIdAttribs);

            String waitClass = rec.substring(11, 31).trim();
            DBAttributes waitClassAttribs = new DBAttributes("WAIT_CLASS", waitClass);
            avgActiveSessDBRec.addAttrib(waitClassAttribs);

            String avgSess = rec.substring(31, rec.length()).trim();
            DBAttributes avgSessAttribs = new DBAttributes("AVG_SESS", avgSess);
            avgActiveSessDBRec.addAttrib(avgSessAttribs);

        }
        return avgActiveSessDBRec;
    }

    private DBRec createMachineInfoDBRec(String rec) {

        int tokCnt = 0;
        DBRec machineInfoDBRec = new DBRec();

        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            DBAttributes dbAttribs = new DBAttributes();
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                tokCnt++;
                switch (tokCnt) {
                case 1:
                    tokCnt = 1;
                    dbAttribs.setName(tok);
                    break;
                case 2:
                    tokCnt = 2;
                    dbAttribs.setValue(tok);
                    break;
                }
                machineInfoDBRec.addAttrib(dbAttribs);
            }
        }
        return machineInfoDBRec;
    }

    /*
    SNAP_ID WAIT_CLASS           EVENT_NAME                                                       PCTDBT TOTAL_TIME_S
    ---------- -------------------- ------------------------------------------------------------ ---------- ------------
       6673 DB CPU               DB CPU                                                            79.37   1162077955
       6673 User I/O             cell multiblock physical read                                      3.83     56095200
    */
    private DBRec createTopTimedEventsDBRec(String rec) {

        DBRec dbRec = new DBRec();
        DBAttributes attribs = null;
        String subString = null;


        if (rec.length() > 0) {

            subString = rec.substring(0, 10).trim();
            attribs = new DBAttributes("SNAP_ID", subString);
            dbRec.addAttrib(attribs);

            subString = rec.substring(11, 31).trim();
            attribs = new DBAttributes("WAIT_CLASS", subString);
            dbRec.addAttrib(attribs);

            subString = rec.substring(32, 92).trim();
            attribs = new DBAttributes("EVENT_NAME", subString);
            dbRec.addAttrib(attribs);

            subString = rec.substring(93, 103).trim();
            attribs = new DBAttributes("PCTDBT", subString);
            dbRec.addAttrib(attribs);

            subString = rec.substring(104, 116).trim();
            attribs = new DBAttributes("TOTAL_TIME_S", subString);
            dbRec.addAttrib(attribs);

        }
        return dbRec;
    }

}
