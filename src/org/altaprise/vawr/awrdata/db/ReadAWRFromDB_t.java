/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
