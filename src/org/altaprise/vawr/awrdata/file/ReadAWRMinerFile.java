package org.altaprise.vawr.awrdata.file;


import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRRecord;


public class ReadAWRMinerFile {

    private BufferedReader _fileReader;
    
    AWRData _awrData = new AWRData();


    public ReadAWRMinerFile() {
        
    }
    public AWRData getAWRData() {
        return _awrData;    
    }
    
    public void parse(String fileName) {
        try {
            _fileReader = new BufferedReader(new FileReader(fileName));

            readData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readData() {
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
                        parseHeaders(rec);
                    } else if (recCount == 2) {
                        //Skip the row in the file that contains the dashes.
                        //Do nothing
                    } else {
                        if (rec.equals("~~END-MAIN-METRICS~~")) {
                            endMainMetricsFound = true;
                        } else {
                            //Parse the data rows
                            AWRRecord dataRec = parseDataRecord(rec);
                            _awrData.add(dataRec);
                        }
                    }

                }
                //Read the next record
                rec = _fileReader.readLine();

            }


            _fileReader.close();

            //dumpData();

        } catch (Exception e) {
            System.out.println("PropertyFileReader::readData\n" +
                    e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


    public void dumpData() {

        for (int i = 0; i < _awrData.getHeaderCount(); i++) {
            System.out.print(_awrData.getHeaderName(i) + ", ");
        }
        System.out.println();

        for (int i = 0; i < _awrData.size(); i++) {

            for (int j = 0; j < _awrData.get(i).getSize(); j++) {
                System.out.print(_awrData.get(i).getVal((String)_awrData.getHeaderName(j)) + ", ");
            }
            System.out.println();
        }

    }

    private void parseHeaders(String rec) {
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                _awrData.addHeaderName(token.toUpperCase());

                //Check to see if this is the header.  If so, add another header field for time.
                if (token.equals("end") || token.equals("END")) {
                    _awrData.addHeaderName("TIME");
                }
            }
        }
    }

    private AWRRecord parseDataRecord(String rec) {
        ArrayList<String> dataRecord = new ArrayList<String>();
        AWRRecord awrRec = new AWRRecord();

        int tokenCnt = 0;
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec);
            while (st.hasMoreTokens()) {
                String tok = st.nextToken();
                String headerName = (String)_awrData.getHeaderName(tokenCnt);
                awrRec.putVal(headerName.toUpperCase(), tok);
                //dataRecord.add(tok);
                tokenCnt++;
            }
        }
        return awrRec;
    }
}
