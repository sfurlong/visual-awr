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
