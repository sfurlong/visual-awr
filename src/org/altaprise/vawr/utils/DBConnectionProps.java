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
package org.altaprise.vawr.utils;

import java.io.Serializable;

public class DBConnectionProps implements Serializable {

    private static final long serialVersionUID = 1L;

    String connectionName = null;
    String connectionURL = "jdbc:oracle:thin:@"; //@192.168.1.7:1521:orcl"
    String dbDriverName = "oracle.jdbc.driver.OracleDriver";
    String hostName = null;
    String port = "1521";
    String SID = null;
    String serviceName = null;
    boolean useSID = true;
    String uId = null;
    String pwd = null;
    String userRole = null;

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public void setDbDriverName(String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    public String getDbDriverName() {
        return dbDriverName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getSID() {
        return SID;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setUseSID(boolean useSID) {
        this.useSID = useSID;
    }

    public boolean isUseSID() {
        return useSID;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getUId() {
        return uId;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }
}
