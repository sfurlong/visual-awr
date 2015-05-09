
//Title:        Business Artifacts
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:


package dai.shared.businessObjs;

import java.io.Serializable;
import java.util.Vector;

public class DBRecSet implements Serializable
{

    private Vector _dbRecSet = new Vector();
    private String _relatedTable = "";

    public DBRecSet ()
    {
        //Nothing to do
    }

    public int getSize()
    {
        return _dbRecSet.size();
    }

    public void setRelatedTableName(String tabName)
    {
        _relatedTable = tabName;
    }

    public String getRelatedTableName()
    {
        return _relatedTable;
    }

    public void addRec(DBRec dbRec)
    {
        _dbRecSet.addElement(dbRec);
    }
    public void addRecSet(DBRecSet dbRecSet)
    {
        int DBRecSetSize = dbRecSet.getSize();
        for(int i=0; i < DBRecSetSize; i++)  {
          _dbRecSet.addElement(dbRecSet.getRec(i));
        }
    }
    public DBRec getRec(int index)
    {
        return (DBRec)_dbRecSet.elementAt(index);
    }
}
