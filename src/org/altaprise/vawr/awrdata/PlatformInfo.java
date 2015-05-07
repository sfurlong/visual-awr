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
package org.altaprise.vawr.awrdata;

public class PlatformInfo {
/*    
    STAT_NAME                                                    STAT_VALUE
    ------------------------------------------------------------ ------------------------------------------------------------
    NUM_CPUS                                                     24
    NUM_CPU_CORES                                                12
    NUM_CPU_SOCKETS                                              2
CPU_COUNT                                                   48
!CPU_CORE_COUNT                                              24
!CPU_SOCKET_COUNT                                            4
    PHYSICAL_MEMORY_GB                                           94.47
    PLATFORM_NAME                                                Linux_x86_64-bit
    VERSION                                                      11.2.0.3.0
    DB_NAME                                                      EDWPRD
    INSTANCES                                                    4
    HOSTS                                                        x01pdb01,x01pdb02,x01pdb03,x01pdb04
    AWR_MINER_VER                                                3.0.7
    ~~END-OS-INFORMATION~~    
*/
    public PlatformInfo() {
        super();
    }

    private String numCPUs = null;

    public void setNumCPUs(String numCPUs) {
        this.numCPUs = numCPUs;
    }

    public String getNumCPUs() {
        return numCPUs;
    }

    public void setNumCPUCores(String numCPUCores) {
        this.numCPUCores = numCPUCores;
    }

    public String getNumCPUCores() {
        return numCPUCores;
    }

    public void setNumCPUSockets(String numCPUSockets) {
        this.numCPUSockets = numCPUSockets;
    }

    public String getNumCPUSockets() {
        return numCPUSockets;
    }

    public void setTotCPUCount(String totCPUCount) {
        this.totCPUCount = totCPUCount;
    }

    public String getTotCPUCount() {
        return totCPUCount;
    }

    public void setTotCPUCoreCnt(String totCPUCoreCnt) {
        this.totCPUCoreCnt = totCPUCoreCnt;
    }

    public String getTotCPUCoreCnt() {
        return totCPUCoreCnt;
    }

    public void setTotCPUSocketCnt(String totCPUSocketCnt) {
        this.totCPUSocketCnt = totCPUSocketCnt;
    }

    public String getTotCPUSocketCnt() {
        return totCPUSocketCnt;
    }

    public void setPhysicalMemGB(String physicalMemGB) {
        this.physicalMemGB = physicalMemGB;
    }

    public String getPhysicalMemGB() {
        return physicalMemGB;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setDbVersion(String dbVersion) {
        this.dbVersion = dbVersion;
    }

    public String getDbVersion() {
        return dbVersion;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    public String getDbId() {
        return dbId;
    }

    public void setNumInstances(String numInstances) {
        this.numInstances = numInstances;
    }

    public String getNumInstances() {
        return numInstances;
    }

    public void setHostNames(String hostNames) {
        this.hostNames = hostNames;
    }

    public String getHostNames() {
        return hostNames;
    }

    public void setAwrMinerVersion(String awrMinerVersion) {
        this.awrMinerVersion = awrMinerVersion;
    }

    public String getAwrMinerVersion() {
        return awrMinerVersion;
    }
    private String numCPUCores = null;
    private String numCPUSockets = null;
    private String totCPUCount = null;
    private String totCPUCoreCnt = null;
    private String totCPUSocketCnt = null;
    private String physicalMemGB = null;
    private String platformName = null;
    private String dbVersion = null;
    private String dbName = null;
    private String dbId = null;
    private String numInstances = null;
    private String hostNames = null;
    private String awrMinerVersion = null;
    
}
