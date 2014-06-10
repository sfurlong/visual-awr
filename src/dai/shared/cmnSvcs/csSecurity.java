
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
