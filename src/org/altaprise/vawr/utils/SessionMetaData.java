//Title:        Business Artifacts
//Version:
//Copyright:    Copyright (c) 1998-2000
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:
//  This class maintains data related to a users session.
//  It has been implemented as a Singleton pattern.
//  NOTE:  This class should not be instantiated directly.
//          New instances should be initialized to null and
//          the getInstance() method should be used to get a
//          handle to this class.

/*
Map map = new HashMap();
        map.put("1",new Integer(1));
        map.put("2",new Integer(2));
        map.put("3",new Integer(3));
        FileOutputStream fos = new FileOutputStream("map.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.close();

        FileInputStream fis = new FileInputStream("map.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map anotherMap = (Map) ois.readObject();
        ois.close();

        System.out.println(anotherMap);
*/
package org.altaprise.vawr.utils;

import java.io.FileInputStream;

import java.util.Properties;

public class SessionMetaData {
    //Get our static instance data.

    public static SessionMetaData getInstance() {
        if (_metaData == null) {
            _metaData = new SessionMetaData();
        }
        return _metaData;
    }

    public String getLocality() {
        return _locality;
    }


    public String getcsPlumbingType() {
        return _csPlumbingType;
    }

    public String getUserId() {
        return "system";
    }

    public String getDaiHome() {
        return System.getProperty("APP_HOME");
    }

    public boolean debugOn() {
        String debugS = System.getProperty("DEBUG");
        boolean debugOn = false;
        if (debugS != null) debugOn = true; 
        return debugOn;
    }

    public String getDaiLibHome() {
        return System.getProperty("APP_HOME") + "/lib/";
    }

    public String getDaiRptHome() {
        return System.getProperty("APP_HOME") + "/rpt/";
    }

    public String getImagesHome() {
        return System.getProperty("APP_HOME") + "/images/";
    }

    public String getHostname() {
        return _csHostName;
    }

    public String getJDBCDriverName() {
        return _dbDriverName;
    }

    public String getClientServerSecurity() {
        return "system";
    }

    /**
     * Returns the Date Format that is used by the database engine
     * for database sql opperations.
     * @return
     */
    public String getDBDateFormat() {
        if (getDatabaseType().equals("firebird") ||
            getDatabaseType().equals("interbase")) {
            return "MM/dd/yy";
        } else if (getDatabaseType().equals("hsqldb")) {
            return "yyyy-MM-dd";
        } else {
            return null;
        }

    }

    public String getDatabaseType() {
        if (_dbDriverName.indexOf("firebird") >= 0) {
            return "firebird";
        } else if (_dbDriverName.indexOf("interbase") >= 0) {
            return "interbase";
        } else if (_dbDriverName.indexOf("hsqldb") >= 0) {
            return "hsqldb";
        } else if (_dbDriverName.indexOf("oracle") >= 0) {
            return "oracle";
        } else {
            return null;
        }
    }

    public String getServerLogin() {
        return _serverLogin;
    }

    public String getServerPasswd() {
        return _serverPasswd;
    }

    public String getServerDBURL() {
        return _serverDBURL;
    }

    public int getTraceLevel() {
        return _traceLevel;
    }

    public int getMaxDBSelectROws() {
        return _maxDBSelectRows;
    }

    public void setTransCallLevel(int level) {
        transCallLevel = level;
    }

    public int getTransCallLevel() {
        return transCallLevel;
    }

    public String getWebRptsHost() {
        return _webRptsHost;
    }

    public String getVersionInfo() {
        return _version;
    }

    public String getBuildInfo() {
        return _build;
    }

    public String getPatchNum() {
        return _patchNum;
    }

    public void printAllMetaData() {
/*
        System.out.println(this.getBuildInfo());
        System.out.println(this.getClientServerSecurity());
        System.out.println(this.getDBDateFormat());
        System.out.println(this.getDaiHome());
        System.out.println(this.getDaiLibHome());
        System.out.println(this.getDaiRptHome());
        System.out.println(this.getDatabaseType());
        System.out.println(this.getHostname());
        System.out.println(this.getImagesHome());
        System.out.println(this.getJDBCDriverName());
        System.out.println(this.getLocality());
        System.out.println(this.getMaxDBSelectROws());
        System.out.println(this.getPatchNum());
        System.out.println(this.getServerDBURL());
        System.out.println(this.getServerLogin());
        System.out.println(this.getServerPasswd());
        System.out.println(this.getTraceLevel());
        System.out.println(this.getTransCallLevel());
        System.out.println(this.getUserId());
        System.out.println(this.getVersionInfo());
        System.out.println(this.getWebRptsHost());
        System.out.println(this.getcsPlumbingType());
*/
    }
    /////////////////  Private Methods/Data //////////////////////////

    //Private Constructor.  Enforces Singleton.

    private SessionMetaData() {
        _serverLogin = "system";
        _serverPasswd = "oracle";
    }


    //Declaration of the Private Static metaData.
    //Since it's static, only one copy will exist of the instance data
    //a'la the Singleton pattern.
    private static SessionMetaData _metaData = new SessionMetaData();
    private String _csPlumbingType;
    private String _csHostName;
    private String _webRptsHost;
    private String _dbDriverName;
    private String _serverLogin;
    private String _serverPasswd;
    private String _serverDBURL;
    private int _traceLevel;
    private int _maxDBSelectRows = 999999;
    private int transCallLevel = 0;
    private String _locality = "SUPER";
    private String _build = null;
    private String _version = null;
    private String _patchNum = null;
    
    public static void main(String[] args) {
        SessionMetaData data = SessionMetaData.getInstance();
        System.out.println(data.debugOn());
        System.out.println(data.getDaiHome());
    }
}
