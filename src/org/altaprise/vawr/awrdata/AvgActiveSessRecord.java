package org.altaprise.vawr.awrdata;

public class AvgActiveSessRecord {

    String _WAIT_CLASS = "";
    String _AVG_SESS = "";

    public AvgActiveSessRecord(String waitClass, String avgSess) {
        _WAIT_CLASS = waitClass;
        _AVG_SESS = avgSess;
    }
    
    public void setWAIT_CLASS(String waitClass) {
        this._WAIT_CLASS = waitClass;
    }

    public String getWAIT_CLASS() {
        return _WAIT_CLASS;
    }

    public void setAVG_SESS(String avgSess) {
        this._AVG_SESS = avgSess;
    }

    public String getAVG_SESS() {
        return _AVG_SESS;
    }
    
}
