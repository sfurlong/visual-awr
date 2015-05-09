
//Title:        Business Artifacts
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:


package dai.shared.businessObjs;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DBRec implements Serializable
{

    private HashMap _dbAttribMap = new HashMap();
    private ArrayList _dbAttribList = new ArrayList();

    public DBRec ()
    {
        //Nothing to do
    }

    public void addAttrib(DBAttributes attrib)
    {
        _dbAttribMap.put(attrib.getName().toUpperCase(), attrib);
        _dbAttribList.add(attrib.getName().toUpperCase());
    }

    public DBAttributes getAttrib(int attribIndex)
    {
        String attribName = (String)_dbAttribList.get(attribIndex);
        return (DBAttributes)_dbAttribMap.get(attribName);
    }
    
    public int size() {
        int i = _dbAttribList.size();
        return i;
    }
    
    public DBAttributes getAttrib(String attribName)
    {
        return (DBAttributes)_dbAttribMap.get(attribName.toUpperCase());
    }

    public String getAttribVal(String attribName)
    {
        DBAttributes attrib = (DBAttributes)_dbAttribMap.get(attribName.toUpperCase());
        String ret;
        if (attrib == null) {
            ret = null;
        } else {
            ret = attrib.getValue();
            if (ret != null && ret.trim().length() == 0) {
                ret = null;
            }
        }
        return ret;
    }

    public void debug() {
        Set keySet = _dbAttribMap.keySet();
        Iterator iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = (String)iter.next();
            System.out.println(key + "/" + getAttribVal(key));

        }
    }
}
