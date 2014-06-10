package org.altaprise.vawr.awrdata;

import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AWRData {

    private List _headerTokens = new ArrayList();
    private BufferedReader _fileReader;
    private ArrayList<AWRRecord> _dataRecords = new ArrayList<AWRRecord>();

    public AWRData() {
    }
    
    public void addHeaderName(String name) {
        _headerTokens.add(name);    
    }

    public int getHeaderCount() {
        return _headerTokens.size();    
    }
    
    public String getHeaderName(int i) {
        return (String)_headerTokens.get(i);
    }
    
    public void add(AWRRecord rec) {
        _dataRecords.add(rec);    
    }
    
    public int size() {
        return _dataRecords.size();
    }
    
    public AWRRecord get(int i) {
        return _dataRecords.get(i);
    }
    
    public ArrayList<AWRRecord> getAWRData() {
        return _dataRecords;
    }
    
    public boolean awrMetricExists(String metric) {
        return _headerTokens.contains(metric.toUpperCase());
    }

    public int getNumRACInstances() {
        int numRacInst = 0;
        for (int i=0; i<_dataRecords.size(); i++){
            if (_dataRecords.get(i).getInst() > numRacInst) {
                numRacInst = _dataRecords.get(i).getInst();
            }
        }
        return numRacInst;
    }
}
