package org.altaprise.vawr.awrdata.db;

import dai.server.dbService.dbconnect;
import dai.server.dbService.SQLResolver;

import dai.shared.businessObjs.DBRecSet;

import org.altaprise.vawr.utils.SessionMetaData;

public class ReadAWRFromDB_t {
    SQLResolver _sqlResolver = new SQLResolver();
    
    public ReadAWRFromDB_t() {
        super();
        
        SessionMetaData sessionMeta = SessionMetaData.getInstance();
        String dbDriverName = sessionMeta.getJDBCDriverName();
        String dbURL = sessionMeta.getServerDBURL();
        String dbUID = sessionMeta.getServerLogin();
        String dbPwd = sessionMeta.getServerPasswd();

        dbconnect dbConn = dbconnect.getInstance();

        //(String url, String driver, String uid, String pwd)
        try {
            dbConn.connectToDB(dbURL,
                               dbDriverName, dbUID,
                               dbPwd);

            DBRecSet dbIds = _sqlResolver.executeDynamicSQL(dbConn, AWRCollectionSQL.getDbIdSQL());
            
            dbConn.executeSQL("SELECT  dbid, db_name FROM dba_hist_database_instance");

            String mainMetricsSQL = AWRCollectionSQL.getMainAWRMetricsSQL(1, 10, 20);
            System.out.println(mainMetricsSQL);
            dbConn.executeSQL(mainMetricsSQL);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ReadAWRFromDB_t();
    }
}
