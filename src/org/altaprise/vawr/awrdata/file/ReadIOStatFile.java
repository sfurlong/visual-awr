package org.altaprise.vawr.awrdata.file;


import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.OSWRecord;
import org.altaprise.vawr.charts.IOStatTimeSeriesChart;


public class ReadIOStatFile {

    private BufferedReader _fileReader;
    private Date _fileStartDateTime = null;
    private String _osFlavor = null;
    private String _osVersion = null;
    private String _hostName = null;
    private String _fileDateS = null;
    private boolean _isExadataStorage = false;
    private LinkedHashMap<String, DBRec> _deviceMap = new LinkedHashMap<String, DBRec>();

    public ReadIOStatFile() {

    }

    public static void main(String[] args) {
        try {
            ReadIOStatFile duFile = new ReadIOStatFile();
            //duFile.parse("C:\\Git\\visual-awr\\testing\\OSWatcher\\IOStat-Linux\\du1.dat");
            duFile.parse("C:\\Git\\visual-awr\\testing\\OSWatcher\\IOStat-Linux\\2014_09_10_07_13_02_IostatExaWatcher_osc2es01.osc.us.oracle.com.dat", false);
            OSWData.getInstance().dump();
            //new IOStatAvgCPUTimeSeriesChart("");
            new IOStatTimeSeriesChart("FLASH", "Cell IOPS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBRec testFile(String fileName, Date startDateFilter, Date endDateFilter, boolean doFilter) throws Exception {
        try {
            String rec = "";
            String filteredFileList = null;
            _fileReader = new BufferedReader(new FileReader(fileName));
            DBRec fileRecSet = null;

            try {
                //Priming read
                rec = _fileReader.readLine();
                String dateTimeS = null;
                boolean timeDateFound = false;
                Date lastDateTimeRead = null;
                Date firstDateTimeRead = null;

                while (rec != null) {
                    if (rec.startsWith("zzz ***") || rec.startsWith("zzz <") || rec.startsWith ("Time:")) {
                        try {
                            Date dateTimeD = this.parseDateTime(rec);

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
                }
                
                return fileRecSet;

            } catch (Exception e) {
                System.out.println("IOSTAT File Read Error\n" + e.getLocalizedMessage());
                e.printStackTrace();
                throw e;
            }

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

    public void parse(String fileName, boolean isExadataStorage) throws Exception {
        try {
            _isExadataStorage = isExadataStorage;
            _fileReader = new BufferedReader(new FileReader(fileName));
            readMainMetrics();
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
        Date currentRecDateTime = null;


        try {
            //Priming read
            rec = _fileReader.readLine();


            while (rec != null) {
                if (rec.startsWith("Linux")) {
                    this.parseHostNameAndDate(rec);

                } else if (rec.startsWith("zzz ***") || rec.startsWith("zzz <") || rec.startsWith ("Time:")) {
                    //Parse the datetime and add the attributes to the dbRec
                    currentRecDateTime = parseDateTime(rec);

                } else if (rec.startsWith("avg-cpu:")) {

                    DBRec avgCPURec = parseAvgCPU(currentRecDateTime);
                    OSWRecord.getInstance().addAvgCPUData(avgCPURec);

                //sd is scsi device hd is IDE hard disk
                } else if (rec.startsWith("sd") || rec.startsWith("hd")) {
                    
                    parseDevice(rec, currentRecDateTime);
                }

                //Read the next record
                rec = _fileReader.readLine();
            }


            for (Map.Entry<String, DBRec> entry : _deviceMap.entrySet()) {
                DBRec mapVal = entry.getValue();
                OSWData.getInstance().addIoStatRec(mapVal);
            }

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
                    _osFlavor = tok;
                    break;
                case 2:
                    _osVersion = tok;
                    break;
                case 3:
                    _hostName = tok;
                    break;
                case 4:
                    _fileDateS = tok;
                    break;
                }
            }
        }
    }

    private Date parseDateTime(String rec) {
        String dateTimeS = null;
        Date ret = null;
        if (rec.startsWith("zzz ***")) {
            //zzz ***Sun Aug 10 20:43:48 CEST 2014
            dateTimeS = rec.substring(7, 36);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
                ret = dateFormat.parse(dateTimeS);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else if (rec.startsWith("zzz <")) {
            //zzz <09/10/2014 07:13:02>
            dateTimeS = rec.substring(6, 24);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                ret = dateFormat.parse(dateTimeS);
                _fileStartDateTime = ret;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (rec.startsWith("Time:")) {
            //Time: 07:13:02 AM
            String hourS = rec.substring(6, 8);
            String minS = rec.substring(9, 11);
            String secS = rec.substring(12, 14);
            String am_pmS = rec.substring(15, 17);
            Calendar cal = Calendar.getInstance();
            cal.setTime(this._fileStartDateTime);
            cal.set(Calendar.HOUR, Integer.parseInt(hourS));
            cal.set(Calendar.MINUTE, Integer.parseInt(minS));
            cal.set(Calendar.SECOND, Integer.parseInt(secS));
            if (am_pmS.equals("AM")) {
                cal.set(Calendar.AM_PM, Calendar.AM);
            } else if (am_pmS.equals("PM")) {
                cal.set(Calendar.AM_PM, Calendar.PM);
            } else {
                System.out.println("warning not AM/PM found in file.");
            }
            ret = cal.getTime();
        }
        return ret;
    }

    //Device:         rrqm/s   wrqm/s   r/s   w/s   rsec/s   wsec/s avgrq-sz avgqu-sz   await  svctm  %util
    private DBRec parseAvgCPU(Date dateTime) throws Exception {
        //Read the next record
        String rec = _fileReader.readLine();
        DBRec dbRec = new DBRec();
        DBAttributes dateAttribs = new DBAttributes("DATE", dateTime);

        dbRec.addAttrib(dateAttribs);

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

    /*
    Device:         rrqm/s   wrqm/s   r/s   w/s   rsec/s   wsec/s avgrq-sz avgqu-sz   await  svctm  %util
    sda               0.00    16.96 32.39 26.20   163.84   265.60     7.33     0.03    0.51   0.14   0.85
    sda1              0.00     0.00  0.00  0.00     0.41     0.00    92.97     0.00    7.09   0.37   0.00
    sda2              0.00     0.00  0.00  0.00     0.00     0.00     1.65     0.00    0.69   0.69   0.00
    sda3              0.00     0.00 31.35 20.84    44.70    87.02     2.52     0.01    0.21   0.14   0.74
    sda4              0.00     0.00  0.00  0.00     0.00     0.00     1.62     0.00    0.92   0.92   0.00
    sda5              0.00    15.75  0.28  5.11    35.18   166.88    37.50     0.01    1.35   0.12   0.06
    sda6              0.00     0.00  0.28  0.00    35.15     0.00   127.45     0.00   17.64   0.65   0.02
    sda7              0.00     1.15  0.08  0.15    10.55    10.44    88.66     0.00    6.36   0.31   0.01
    sda8              0.00     0.00  0.08  0.00    10.55     0.00   126.19     0.00   17.42   0.66   0.01
    sda9              0.00     0.00  0.06  0.00     7.03     0.00   125.30     0.00   17.92   0.67   0.00
    sda10             0.00     0.00  0.02  0.00     2.40     0.00   120.40     0.00   16.12   0.65   0.00
    sda11             0.00     0.06  0.15  0.09    17.80     1.25    77.01     0.00   10.80   0.57   0.01
    sdb               0.00    16.96  1.07  6.32   121.73   222.19    46.54     0.01    1.95   0.20   0.15
    sdb1              0.00     0.00  0.00  0.00     0.41     0.00    95.08     0.00    2.81   0.22   0.00
    sdb2              0.00     0.00  0.00  0.00     0.00     0.00     1.65     0.00    0.73   0.72   0.00
    sdb3              0.00     0.00  0.03  0.96     2.58    43.61    46.39     0.00    1.04   0.34   0.03
    sdb4              0.00     0.00  0.00  0.00     0.00     0.00     1.62     0.00    0.82   0.82   0.00
    sdb5              0.00    15.75  0.28  5.11    35.17   166.88    37.50     0.01    1.04   0.13   0.07
    sdb6              0.00     0.00  0.28  0.00    35.15     0.00   127.48     0.00   12.20   0.57   0.02
    sdb7              0.00     1.15  0.08  0.15    10.56    10.44    88.72     0.00    4.40   0.29   0.01
    sdb8              0.00     0.00  0.08  0.00    10.55     0.00   126.28     0.00   12.25   0.57   0.00
    sdb9              0.00     0.00  0.06  0.00     7.03     0.00   125.51     0.00   11.28   0.56   0.00
    sdb10             0.00     0.00  0.02  0.00     2.40     0.00   120.95     0.00   11.92   0.58   0.00
    sdb11             0.00     0.06  0.15  0.09    17.79     1.25    77.02     0.00    5.93   0.46   0.01
    sdc               0.00     0.00  0.03  0.20     2.32    19.02    90.96     0.00    2.58   0.63   0.01
    sdd               0.00     0.00  1.98  0.86    64.42    39.01    36.52     0.00    0.43   0.25   0.07
    sde               0.00     0.00  0.11  0.63     4.29    31.81    48.85     0.00    0.96   0.32   0.02
    sdf               0.00     0.00  0.31  0.49    11.05    27.45    48.05     0.00    0.92   0.29   0.02
    sdg               0.00     0.00  1.20  0.48    39.56    27.74    40.08     0.00    0.54   0.22   0.04
    sdh               0.00     0.00  0.05  0.57     2.92    30.07    53.29     0.00    1.08   0.31   0.02
    sdi               0.00     0.00  0.70  1.01    22.68    43.61    38.82     0.00    0.54   0.27   0.05
    sdj               0.00     0.00  0.67  1.57    22.93    62.74    38.17     0.00    0.47   0.25   0.06
    sdk               0.00     0.00  0.07  2.05     3.48    78.01    38.38     0.00    0.56   0.31   0.07
    sdl               0.00     0.00  0.07  1.23     2.93    51.19    41.82     0.00    0.72   0.30   0.04
    sdm               0.01     0.00  0.00  0.00     0.03     0.02    28.97     0.00   39.69   1.60   0.00
    sdm1              0.01     0.00  0.00  0.00     0.03     0.02    29.10     0.00   41.86   1.62   0.00
    md1               0.00     0.00  0.00  0.00     0.00     0.00     8.00     0.00    0.00   0.00   0.00
    md11              0.00     0.00  0.03  0.12     0.44     0.98     9.17     0.00    0.00   0.00   0.00
    md2               0.00     0.00  0.00  0.00     0.00     0.00     8.00     0.00    0.00   0.00   0.00
    md8               0.00     0.00  0.00  0.00     0.00     0.00     7.99     0.00    0.00   0.00   0.00
    md7               0.00     0.00  0.00  1.26     0.02    10.09     8.02     0.00    0.00   0.00   0.00
    md6               0.00     0.00  0.00  0.00     0.00     0.00     7.98     0.00    0.00   0.00   0.00
    md5               0.00     0.00  0.00 20.17     0.05   161.40     8.00     0.00    0.00   0.00   0.00
    md4               0.00     0.00  0.00  0.00     0.00     0.00    57.90     0.00    0.00   0.00   0.00
    sdo               0.00     0.00  0.19  0.22     8.53     6.12    35.89     0.00    0.94   0.09   0.00
    sdn               0.00     0.00  0.13  0.14     6.62     3.58    38.13     0.00    2.22   0.08   0.00
    sdq               0.00     0.00  0.13  0.14     6.68     3.54    37.80     0.00    1.81   0.07   0.00
    sdp               0.00     0.00  0.13  0.14     6.67     3.37    37.66     0.00    1.81   0.07   0.00
    sdu               0.00     0.00  0.18  0.14     8.19     3.38    36.91     0.00    1.74   0.08   0.00
    sds               0.00     0.00  0.13  0.14     6.71     3.57    37.40     0.00    1.46   0.07   0.00
    sdr               0.00     0.00  0.13  0.14     6.82     3.47    37.83     0.00    2.29   0.08   0.00
    sdt               0.00     0.00  0.15  0.13     7.13     3.31    37.76     0.00    3.29   0.08   0.00
    sdw               0.00     0.00  0.13  0.13     6.67     3.39    38.49     0.00    1.73   0.07   0.00
    sdv               0.00     0.00  0.75  0.14    26.73     3.61    34.00     0.00    0.55   0.09   0.01
    sdx               0.00     0.00  0.62  0.32    22.42     9.42    33.80     0.00    0.66   0.09   0.01
    sdy               0.00     0.00  0.13  0.14     6.78     3.58    38.49     0.00    2.85   0.08   0.00
    sdab              0.00     0.00  0.84  0.29    29.23     8.46    33.35     0.00    0.41   0.09   0.01
    sdac              0.00     0.00  0.27  0.32    11.15     9.27    34.54     0.00    1.03   0.08   0.00
    sdz               0.00     0.00  0.14  0.13     6.91     3.34    37.73     0.00    1.88   0.08   0.00
    sdaa              0.00     0.00  0.14  0.14     6.86     3.44    37.29     0.00    1.77   0.07   0.00
        */
    private void parseDevice(String rec, Date dateTime) throws Exception {

        int tokCnt = 0;
        boolean isFlash = false;

        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            String deviceName = null;
            String deviceType = null;

            DBRec ioStatRec = null;

            //Device:         rrqm/s   wrqm/s   r/s   w/s   rsec/s   wsec/s avgrq-sz avgqu-sz   await  svctm  %util
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                tokCnt++;
                DBAttributes dbAttribs = null;
                switch (tokCnt) {
                case 1:
                    deviceName = tok;

                    deviceType = getDeviceType(deviceName);

                    if (_deviceMap.containsKey(_hostName + deviceType + dateTime.getTime())) {
                        ioStatRec = _deviceMap.get(_hostName + deviceType + dateTime.getTime());
                    } else {
                        //Create a DBRec
                        ioStatRec = new DBRec();
                        ioStatRec.addAttrib(new DBAttributes("HOST_NAME", _hostName));
                        ioStatRec.addAttrib(new DBAttributes("DEVICE_NAME", deviceName));
                        ioStatRec.addAttrib(new DBAttributes("DEVICE_TYPE", deviceType));
                        ioStatRec.addAttrib(new DBAttributes("DATE", dateTime));

                        //Add to the device map
                        _deviceMap.put(_hostName + deviceType + dateTime.getTime(), ioStatRec);
                    }

                    break;
                case 2:
                    sumDeviceMetric("rrqm/s", tok, ioStatRec);
                    break;
                case 3:
                    sumDeviceMetric("wrqm/s", tok, ioStatRec);
                    break;
                case 4:
                    sumDeviceMetric("r/s", tok, ioStatRec);
                    break;
                case 5:
                    sumDeviceMetric("w/s", tok, ioStatRec);
                    break;
                case 6:
                    sumDeviceMetric("rsec/s", tok, ioStatRec);
                    break;
                case 7:
                    sumDeviceMetric("wsec/s", tok, ioStatRec);
                    break;
                case 8:
                    sumDeviceMetric("avgrq/sz", tok, ioStatRec);
                    break;
                case 9:
                    sumDeviceMetric("avgqu/sz", tok, ioStatRec);
                    break;
                case 10:
                    sumDeviceMetric("await", tok, ioStatRec);
                    break;
                case 11:
                    sumDeviceMetric("svctm", tok, ioStatRec);
                    break;
                case 12:
                    sumDeviceMetric("%util", tok, ioStatRec);
                    break;
                }
            }
        }
    }

    private void sumDeviceMetric(String metricName, String metricValP, DBRec ioStatRec) {
        DBAttributes dbAttrib = ioStatRec.getAttrib(metricName);
        if (dbAttrib == null) {
            ioStatRec.addAttrib(new DBAttributes(metricName, metricValP));
        } else {
            String metricValS = dbAttrib.getValue();
            double sumVal = Double.parseDouble(metricValS) + Double.parseDouble(metricValP);
            dbAttrib.setValue(Double.toString(sumVal));
        }
    }

    private String getDeviceType(String deviceName) {
        String ret = null;
        if (!_isExadataStorage) {
            return "DISK";
        }
        if (deviceName.matches("sda[1-9]?[0-9]?|sdb[1-9]?[0-9]?|sd[c,d,e,f,g,h,i,j,k,l][1-9]?[0-9]?")) {
            //Its a disk
            ret = "DISK";
        } else if (deviceName.matches("sdaa[1-9]?[0-9]?|sdab[1-9]?[0-9]?|sdac[1-9]?[0-9]?|sd[n,o,p,q,r,s,t,u,v,w,x,y,z][1-9]?[0-9]?")) {
            //Its Flash
            ret = "FLASH";
        } else {
            //USB, Tape, and other block divices
            ret = "UNKNOWN";
        }
        return ret;
    }
}
