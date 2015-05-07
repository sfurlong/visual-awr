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

//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Client Server Security.  This object is used to pass permissions
//              information to remote services.  It is used by the remote services
//              to authenticate the user and verify his/her permisions for that
//              service.


package dai.shared.cmnSvcs;

import java.io.Serializable;

public class csSecurity implements Serializable
{
    public csSecurity(){};
    
    public csSecurity(String uid, String encryptedPwd) {
        _uid = uid;
        _encryptedPwd = encryptedPwd;
    }

    public void setUid(String uid) {
        _uid = uid;
    }

    public void setAndEncryptPwd(String pwd) {
        _encryptedPwd = pwd;
    }

    public void setEncryptedPwd(String pwd) {
        _encryptedPwd = pwd;
    }

    public String getEncryptedPwd() {
        return _encryptedPwd;
    }

    public String getUid() {
        return _uid;
    }
    
    private String _encryptedPwd;
    private String _uid;
}
