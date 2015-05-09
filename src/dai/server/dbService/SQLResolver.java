
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description


package dai.server.dbService;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Vector;

import dai.shared.businessObjs.BusinessObject;
import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import org.altaprise.vawr.utils.SessionMetaData;

import dai.shared.cmnSvcs.daiFormatUtil;

import java.sql.Timestamp;

import org.altaprise.vawr.utils.Logger;

public class SQLResolver {
    Logger _logger;
    SessionMetaData _sessionMeta;

    public SQLResolver() {
        _sessionMeta = SessionMetaData.getInstance();
        _logger = Logger.getInstance();
    }

    //Returns a vector of vectors

    public Vector getDynamicSQLResults(dbconnect dbConn,
                                       String sqlStmt) throws Exception {
        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);
        int i = 0;
        Vector vectOfCols = null;
        Vector vectOfRows = null;

        //Not all dynamic execs will return a result set.  For example
        //inserts, deletes, updates will not return result sets.
        if (rs != null) {
            vectOfRows = new Vector();
            try {
                while (rs.next()) {
                    //Add the result set to the next collection
                    vectOfCols = createResultVector(rs);
                    //Add the collection to the vector of collections
                    vectOfRows.addElement(vectOfCols);

                    if (i > _sessionMeta.getMaxDBSelectROws()) {
                        String msg =
                            "Attempt to retrieve too many rows from DB.\n";
                        _logger.logError(msg);
                        break; //quit looping
                    }

                    i++;
                }
            } catch (Exception e) {
                //Log: dialog, system.out, disk
                String msg = "Error querying by Expression.\n" +
                    e.getLocalizedMessage();
                _logger.logError(msg);
                throw new Exception(msg);
            }
        }

        return vectOfRows;
    }

    public DBRecSet executeDynamicSQL(dbconnect dbConn,
                                      String sqlStmt) throws Exception {
    
        if (SessionMetaData.getInstance().debugOn()) {
            _logger.trace(1, null, null, sqlStmt);
        }
    
        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);
        int i = 0;
        DBRecSet attribSetCollection = null;

        //Not all dynamic execs will return a result set.  For example
        //inserts, deletes, updates will not return result sets.
        if (rs != null) {
            attribSetCollection = new DBRecSet();
            try {
                while (rs.next()) {
                    //Create a new Collection for this result set record.
                    DBRec attribSet = new DBRec();
                    //Add the result set to the next collection
                    attribSet = createAttribSet(rs);
                    //Add the collection to the vector of collections
                    attribSetCollection.addRec(attribSet);

                    if (i > _sessionMeta.getMaxDBSelectROws()) {
                        String msg =
                            "Attempt to retrieve too many rows from DB.\n";
                        _logger.logError(msg);
                        break; //quit looping
                    }

                    i++;
                }
            } catch (Exception e) {
                //Log: dialog, system.out, disk
                String msg = "Error querying by Expression.\n" +
                    e.getLocalizedMessage();
                _logger.logError(msg);
                throw new Exception(msg);
            }
        }

        return attribSetCollection;
    }

    //Insert

    public int insert(dbconnect dbConn, BusinessObject obj) throws Exception {
        String sqlStmt;

        sqlStmt =
                "insert into " + obj.getTableName() + " ( " + getColNames(obj) +
                " ) " + " values " + " ( " + getColValues(obj) + " ) ";

        dbConn.executeSQL(sqlStmt);

        return 0;
    }

    //Update

    public int update(dbconnect dbConn, BusinessObject obj,
                      String exp) throws Exception {
        String sqlStmt;

        sqlStmt = " update " + obj.getTableName() + " set " + getSets(obj);

        if (exp != null && exp.length() != 0) {
            sqlStmt = sqlStmt + " where " + exp;
        }

        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);

        return 0;
    }

    //Delete

    public int delete(dbconnect dbConn, BusinessObject obj,
                      String exp) throws Exception {
        String sqlStmt;

        sqlStmt = " delete from " + obj.getTableName();

        if (exp != null && exp.length() != 0) {
            sqlStmt = sqlStmt + " where " + exp;
        }

        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);

        return 0;
    }

    //Get MAX Detail ID

    public long getMaxDetailId(dbconnect dbConn, BusinessObject obj,
                               String exp) throws Exception {
        String sqlStmt = "Select max(detail_id) from " + obj.getTableName();
        Long maxDetailId = new Long(0);


        if (exp != null && exp.length() != 0) {
            sqlStmt = sqlStmt + " where " + exp;
        }

        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);

        try {
            while (rs.next()) {
                //Get the string and trim it too.
                String sId = rs.getString(1);
                if (sId != null) {
                    maxDetailId = new Long(sId);
                }
            }
        } catch (Exception e) {
            //Log: dialog, system.out, disk
            String msg = "Error getting Max Detail ID from DB.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }

        return maxDetailId.longValue();
    }

    //Get All IDs

    public Vector getAllIds(dbconnect dbConn, String tableName,
                            String exp) throws Exception {
        String sqlStmt = "Select distinct id from " + tableName;
        Vector idVector = new Vector();
        int i = 0;

        if (exp != null && exp.length() != 0) {
            sqlStmt = sqlStmt + " where " + exp;
        }

        //Always sort the Ids.
        sqlStmt = sqlStmt + " order by id ";

        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);

        try {
            while (rs.next()) {
                //Get the string and trim it too.
                idVector.addElement((rs.getString(1)).trim());

                if (i > _sessionMeta.getMaxDBSelectROws()) {
                    //Log: dialog, system.out, disk
                    String msg =
                        "Attempt to retrieve too many rows from DB.\n";
                    _logger.logError(msg);
                    break;
                }

                i++;
            }
        } catch (Exception e) {
            //Log: dialog, system.out, disk
            String msg = "Error getting All IDs from DB.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }

        return idVector;
    }


    /**
     * Returns a vector of BusinessObjects that satisfy the query dirived from
     * the provided input parameters.
     * @param obj Is a BusinessObject that is used to get the fields used for the
     * query.  No values need to be populated in the BO.
     * @param exp Where clause expression without the "where".  For example:
     * "id = '12' and locality='SUPER'"
     * @return a Vector of BusinessObjects returned from the query.
     * @throws Exception
     */
    public Vector queryByExpression(dbconnect dbConn, BusinessObject obj,
                                    String exp) throws Exception {
        String sqlStmt =
            "Select " + getColNames(obj) + " from " + obj.getTableName();
        Vector objVector = new Vector();
        int i = 0;

        if (exp != null && exp.length() != 0) {
            sqlStmt = sqlStmt + " where " + exp;
        }

        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);

        try {
            while (rs.next()) {
                BusinessObject tempObj;
                tempObj = obj.getNewInstance();

                //Set the new values back to the BusinessObject.
                tempObj.setAttribList(populateObject(tempObj.getAttribList(),
                                                     rs));
                objVector.addElement(tempObj);

                if (i > _sessionMeta.getMaxDBSelectROws()) {
                    //Log: dialog, system.out, disk
                    String msg =
                        "Attempt to retrieve too many rows from DB.\n";
                    _logger.logError(msg);
                    throw new Exception(msg);
                }

                i++;
            }
        } catch (Exception e) {
            //Log: dialog, system.out, disk
            String msg = "Error querying by Expression.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }

        return objVector;
    }

    //Query By Expression.  Only get select attributes.
    //This should be used for things like popups, when we don't need
    //the entire business object.  Much less data passed back and forth
    //from the client to server. Thus much faster.

    public Vector queryByExpression(dbconnect dbConn, String tabName,
                                    DBAttributes[] attribs,
                                    String exp) throws Exception {
        String sqlStmt = "Select " + attribs[0].getName() + " from " + tabName;
        Vector attribVector = new Vector();
        int i = 0;

        if (exp != null && exp.length() != 0) {
            sqlStmt = sqlStmt + " where " + exp;
        }

        //Execute the SQL
        ResultSet rs = dbConn.executeSQL(sqlStmt);

        try {
            while (rs.next()) {
                //Set the new values back to the BusinessObject.
                DBAttributes[] newAttribs = populateObject(attribs, rs);
                attribVector.addElement(newAttribs);

                if (i > _sessionMeta.getMaxDBSelectROws()) {
                    //Log: dialog, system.out, disk
                    String msg =
                        "Attempt to retrieve too many rows from DB.\n";
                    _logger.logError(msg);
                    throw new Exception(msg);
                }

                i++;
            }
        } catch (Exception e) {
            //Log: dialog, system.out, disk
            String msg = "Error querying by Expression.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }

        return attribVector;
    }

    public String[] getTables(dbconnect dbConn) throws Exception {
        try {
            return dbConn.getTables();
        } catch (Exception e) {
            //Log: dialog, system.out, disk
            String msg = "Error querying DB Table Names.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }
    }

    public DBAttributes[] getColumns(dbconnect dbConn,
                                     String tableName) throws Exception {
        try {
            return dbConn.getColumns(tableName);
        } catch (Exception e) {
            //Log: dialog, system.out, disk
            String msg = "Error querying DB Table Column Names.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }
    }

    //!!NOTE: DATABASE SPECIFIC CODE IN HERE!!

    public int getNewSequenceNum(dbconnect dbConn,
                                 int seqId) throws Exception {
        int seqNum = 0;
        try {
            //qNum =DBSequenceFactory.getSequenceGenerator().getNewSequenceNum(dbConn, seqId);
        } catch (Exception e) {
            _logger.logError(e.getLocalizedMessage());
            throw new Exception(e.getLocalizedMessage());
        }
        return seqNum;
    }

    //!!NOTE: DATABASE SPECIFIC CODE IN HERE!!

    public void setSequenceValue(dbconnect dbConn, int seqId,
                                 int seqVal) throws Exception {
        try {
            //DBSequenceFactory.getSequenceGenerator().setSequenceValue(dbConn,seqId, seqVal);
        } catch (Exception e) {
            _logger.logError(e.getLocalizedMessage());
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public void beginTrans(dbconnect dbConn) throws Exception {
        if (_sessionMeta.getTransCallLevel() == 0) {
            //This seems to be necessary for Interbase. I.e. we
            //need to do a commit before the select before we
            //can see another sessions commited transactions.
            //Again, this seems to be an interbase pecuriarity.
            try {
                dbConn.commit();
            } catch (Exception e) {
                throw new Exception(e.getLocalizedMessage());
            }
        }
        _sessionMeta.setTransCallLevel(_sessionMeta.getTransCallLevel() + 1);
    }

    public void endTrans(dbconnect dbConn) throws Exception {
        _sessionMeta.setTransCallLevel(_sessionMeta.getTransCallLevel() - 1);

        try {
            if (_sessionMeta.getTransCallLevel() == 0) {
                dbConn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getLocalizedMessage());
        }
    }

    public void rollback(dbconnect dbConn) {
        try {
            dbConn.rollback();
            _sessionMeta.setTransCallLevel(0);
            System.out.println("Rollback called");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////// Private Methods ////////////////////////////////

    // Populate Object //

    private DBAttributes[] populateObject(DBAttributes[] attribs,
                                          ResultSet rs) throws Exception {
        String stringVal;

        for (int i = 0; i < attribs.length; i++) {
            //A little explanation here..Starting with the inner most parens.
            //Get the name of the attribute first.
            //Find the location, in the result set, of this column name
            //Using the location, get the value from the result set
            //Finally save the result in the corresponding attribute list.
            try {
                stringVal = rs.getString(rs.findColumn(attribs[i].getName()));
                if (stringVal != null) {
                    attribs[i].setValue(stringVal.trim());

                    //Check for dates and format accordingly
                    if (attribs[i].getType().equals("DATE")) {
                        Calendar cal = Calendar.getInstance();
                        String sDate = attribs[i].getValue();
                        sDate = sDate.substring(0, 10);
                        java.sql.Date dDate = java.sql.Date.valueOf(sDate);
                        cal.setTime(dDate);
                        //Pain in the ASS work around for Java Date Bug *#%)#$.
                        int goodMonth = cal.get(Calendar.MONTH) + 1;
                        attribs[i].setValue(goodMonth + "/" +
                                            cal.get(Calendar.DAY_OF_MONTH) +
                                            "/" + cal.get(Calendar.YEAR));
                    }
                } else {
                    attribs[i].setValue(null);
                }

            } catch (Exception e) {
                //Log: dialog, system.out, disk
                String msg = "Error populating resultset object.\n" +
                    e.getLocalizedMessage();
                _logger.logError(msg);
                throw new Exception(msg);
            }
        }

        return attribs;
    }

    private DBRec createAttribSet(ResultSet rs) throws Exception {
        String stringVal;
        DBRec attribSet = new DBRec();
        int columnCount = 0;
        try {
            columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1; i < columnCount + 1; i++) {
                //A little explanation here..Starting with the inner most parens.
                //Get the name of the attribute first.
                //Find the location, in the result set, of this column name
                //Using the location, get the value from the result set
                //Finally save the result in the corresponding attribute list.
                DBAttributes attribs = new DBAttributes();
                //Add the table name to the field name.
                if (rs.getMetaData().getTableName(i).length() <= 0) {
                    attribs.setName(rs.getMetaData().getColumnName(i).toUpperCase());
                } else {
                    attribs.setName(rs.getMetaData().getTableName(i).toUpperCase() + "." +
                                    rs.getMetaData().getColumnName(i).toUpperCase());
                }
                attribs.setType(rs.getMetaData().getColumnTypeName(i));
                stringVal = rs.getString(i);
                if (stringVal != null) {
                    attribs.setValue(stringVal.trim());

                    //Check for dates and format accordingly
                    if (attribs.getType().equals("DATE")) {
                        Calendar cal = Calendar.getInstance();
                        String sDate = attribs.getValue();
                        sDate = sDate.substring(0, 10);
                        java.sql.Date dDate = java.sql.Date.valueOf(sDate);
                        cal.setTime(dDate);
                        //Pain in the ASS work around for Java Date Bug *#%)#$.
                        int goodMonth = cal.get(Calendar.MONTH) + 1;
                        attribs.setValue(goodMonth + "/" +
                                         cal.get(Calendar.DAY_OF_MONTH) + "/" +
                                         cal.get(Calendar.YEAR));
                    } else if (attribs.getType().equals("TIMESTAMP")) {
                        SimpleDateFormat daiDateTimeFormat =
                            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Calendar cal = Calendar.getInstance();
                        String sDate = attribs.getValue();
                        daiDateTimeFormat.parse(sDate);
                        cal = daiDateTimeFormat.getCalendar();
                        attribs.setValue(daiDateTimeFormat.format(cal.getTime()));
                    }
                } else {
                    attribs.setValue(null);
                }

                attribSet.addAttrib(attribs);
            }

        } catch (Exception e) {
            String msg = "Error populating resultset object.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }

        return attribSet;
    }

    private Vector createResultVector(ResultSet rs) throws Exception {
        String stringVal;
        String valType;
        Vector rsVector = new Vector();
        int columnCount = 0;

        try {
            columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1; i < columnCount + 1; i++) {
                valType = rs.getMetaData().getColumnTypeName(i);
                stringVal = rs.getString(i);
                if (stringVal != null) {
                    stringVal = stringVal.trim();

                    //Check for dates and format accordingly
                    if (valType.equals("DATE") ||
                        valType.equals("TIMESTAMP")) {
                        Calendar cal = Calendar.getInstance();
                        String sDate = stringVal;
                        sDate = sDate.substring(0, 10);
                        java.sql.Date dDate = java.sql.Date.valueOf(sDate);
                        cal.setTime(dDate);
                        //Pain in the ASS work around for Java Date Bug *#%)#$.
                        int goodMonth = cal.get(Calendar.MONTH) + 1;
                        stringVal =
                                goodMonth + "/" + cal.get(Calendar.DAY_OF_MONTH) +
                                "/" + cal.get(Calendar.YEAR);
                    }
                }

                rsVector.addElement(stringVal);
            }

        } catch (Exception e) {
            String msg = "Error populating resultset object.\n" +
                e.getLocalizedMessage();
            _logger.logError(msg);
            throw new Exception(msg);
        }

        return rsVector;
    }

    //This method is used to extract the column names a specified
    //Business Object.  These column names will be formated for use in an
    //SQL Insert or Select Statement.

    private String getColNames(BusinessObject obj) {
        String names = "";
        String tableName = obj.getTableName();

        DBAttributes attribs[] = obj.getAttribList();

        for (int i = 0; i < attribs.length; i++) {
            names = names + tableName + "." + attribs[i].getName();

            if (i != (attribs.length - 1)) {
                names = names + ",";
            }
        }

        return names;
    }

    //This method is used to extract the data values from a specified
    //Business Object.  These values will be formated for use in an
    //SQL Insert Statement.

    private String getColValues(BusinessObject obj) {
        String Values = "";

        DBAttributes attribs[] = obj.getAttribList();

        for (int i = 0; i < attribs.length; i++) {
            String tmpVal = null;
            if (attribs[i].getValue() == null) {
                tmpVal = attribs[i].getValue();
            } else {
                tmpVal = cleanData(attribs[i].getValue());
                if (attribs[i].getNeedsQuotes()) {
                    //!!This code below will be used for additional database support.
                    //Convert to proper date insert format.
                    if (attribs[i].getType().equals("DATE")) {
                        tmpVal = daiFormatUtil.cnvtToDBDateFormat(tmpVal);
                    }
                    tmpVal = "'" + tmpVal + "'";
                }
            }
            Values = Values + tmpVal;

            if (i != (attribs.length - 1)) {
                Values = Values + ",";
            }
        }

        return Values;
    }

    //This method is used for additional database support.

    private String getDBFormatedDate(String inDate) {
        String ret = null;
        SimpleDateFormat inDF = new SimpleDateFormat("MM/dd/yy");
        SimpleDateFormat outDF = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date indDate = inDF.parse(inDate);
            ret = outDF.format(indDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    //This methos is used to get the expressions used
    //in SQL update statements for a particular Business object.
    //For example: id = 'TEST'...

    private String getSets(BusinessObject obj) {
        String sets = "";
        String setVal = "";

        DBAttributes attribs[] = obj.getAttribList();

        for (int i = 0; i < attribs.length; i++) {
            sets = sets + attribs[i].getName() + " = ";

            String tmpVal = null;

            if (attribs[i].getValue() == null) {
                tmpVal = attribs[i].getValue();
            } else {
                tmpVal = cleanData(attribs[i].getValue());
                if (attribs[i].getNeedsQuotes()) {
                    //Convert to proper date insert format.
                    if (attribs[i].getType().equals("DATE")) {
                        tmpVal = daiFormatUtil.cnvtToDBDateFormat(tmpVal);
                    }
                    tmpVal = "'" + tmpVal + "'";
                }
            }

            sets = sets + tmpVal;

            if (i != (attribs.length - 1)) {
                sets = sets + ",";
            }
        }

        return sets;
    }

    //This method will remove any characters that may cause problems
    //for an SQL statement.  For example appostrophies.  This method
    //works against data that will be used in insert and update statements.

    private String cleanData(String data) {
        StringBuffer buf = new StringBuffer(data);

        //Check for appostrophies
        int quotePos = data.indexOf("'");
        while (quotePos > 0) {
            buf.insert(quotePos, "'");
            quotePos = data.indexOf("'", quotePos + 2);
        }
        return buf.toString();
    }
}

