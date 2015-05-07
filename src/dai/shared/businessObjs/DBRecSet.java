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
