package org.altaprise.test.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Types;

import dai.server.dbService.dbconnect;


import java.sql.Date;
import java.sql.Statement;

import java.util.Calendar;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;

public class TestPLSQL {

    public TestPLSQL() {
    }

    private Connection getConnection() {
        Connection c = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            c = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.56.21:1521/flavia.mlg.oracle.com", "system",
                                            "oracle");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return c;
        }
    }

    public void doInsert(int empNo) {

        Connection c = getConnection();

        java.util.Date hireDate = Calendar.getInstance().getTime();
        java.sql.Date hireDateSQL = new java.sql.Date(hireDate.getTime());
        String sqlStmt = " insert into SCOTT.EMP (empno, ename, job, mgr, " +
            //" hiredate, " +
            " sal, comm, DEPTNO) values " + " ( " + empNo + ", 'Emp Name', 'Tech', 2, " +
            //"'" + hireDateSQL + "' , " +
            " 1000, 100, 10)";
        try {

            Statement dbStmt = c.createStatement();

            dbStmt.execute(sqlStmt);
            System.out.println("Inserted EmpNo: " + empNo);
            
            //c.commit();
            c.close();             
            

        } catch (Exception e) {
            String msg = "Error executing the current statement.\n" + e.getLocalizedMessage() + "\n" + sqlStmt;
            System.out.println(msg);
            e.printStackTrace();
        } finally {
        }
    }

    public void doDelete(int empNo) {
        Connection c = getConnection();
        String sqlStmt = " delete from SCOTT.EMP where empno = " + empNo;
        try {

            Statement dbStmt = c.createStatement();

            dbStmt.execute(sqlStmt);
            System.out.println("Deleted EmpNo: " + empNo);

            //c.commit();
            c.close();             

        } catch (Exception e) {
            String msg = "Error executing the current statement.\n" + e.getLocalizedMessage() + "\n" + sqlStmt;
            System.out.println(msg);
            e.printStackTrace();
        }
    }

    public void doQuery() {
        Connection c = getConnection();
        String sqlStmt = " select * from SCOTT.EMP ";;
        try {

            Statement dbStmt = c.createStatement();

            dbStmt.execute(sqlStmt);
            
            int fetchSize  = dbStmt.getResultSet().getFetchSize();
            System.out.println("retrieved: " + fetchSize);

            //c.commit();
            c.close();             

        } catch (Exception e) {
            String msg = "Error executing the current statement.\n" + e.getLocalizedMessage() + "\n" + sqlStmt;
            System.out.println(msg);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {


        TestPLSQL _main = new TestPLSQL();
        _main.doInsert(1);
        
        _main.doQuery();
        Thread.currentThread().sleep(10000);
        
        _main.doDelete(1);

        //dbconnect.getInstance().connectToDB("jdbc:oracle:thin:@//192.168.56.21:1521/flavia.mlg.oracle.com",
        //                                    "Oracle JDBC driver", "System", "oracle");

        // Warning: this is a simple example program : In a long running application,
        // error handlers MUST clean up connections statements and result sets.

        /*
        String du = AWRCollectionSQL.getAvgActiveSessionsSQL(1111, 1111, 1111);
        System.out.println(du);

        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        final Connection c =
            DriverManager.getConnection("jdbc:oracle:thin:@192.168.56.21:1521:+ASM1", "sys as SYSDBA", "oracle");
        String plsql =
            "" + " declare " + " TYPE RET_T is VARRAY(100) OF VARCHAR2(10); " + "    p_id varchar2(20) := null; " +
            "    l_rc sys_refcursor;" + " begin " + "    p_id := ?; " + "    ? := 'input parameter was = ' || p_id;" +
            "    open l_rc for " + "        select 1 id, 'hello' name from dual " + "        union " +
            "        select 2, 'peter' from dual; " + "    ? := l_rc;" + " end;";


        String plsql2 =
            "" + " DECLARE   \n" + " TYPE RET_T is VARRAY(100) OF VARCHAR2(10); \n" + "   storageRecs RET_T;   \n" +
            "   l_rc sys_refcursor; \n" + " begin   " + " storageRecs := RET_T(); \n" +
            "    storageRecs.EXTEND(1); \n" + "    storageRecs.EXTEND(1); \n" + "    storageRecs.EXTEND(1); \n" +
            "    storageRecs.EXTEND(1); \n" + "    storageRecs.EXTEND(1); \n" + "    storageRecs.EXTEND(1); \n" +
            "    storageRecs.EXTEND(1); \n" + "    storageRecs.EXTEND(1); \n" + "   storageRecs(1) := 'term1';   \n" +
            "    storageRecs.EXTEND(1); \n" + "   storageRecs(2) := 'term2';   \n" +
            "   storageRecs(3) := 'term3';   \n" +
            //        "    open l_rc for " +

            //        "        select * from Table(scott.emp);" +
            //        "    ? := l_rc;" +
            " open l_rc for " + " select storageRecs(1) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(2) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(3) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(4) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(5) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(6) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(7) id, 'hello' name from dual \n" + " union \n" +
            " select storageRecs(8) id, 'hello' name from dual; \n" + " ? := l_rc; \n" + "  end; ";

        "    open l_rc for " +
        "    select StorageRecs(1).term p1, StorageRecs(1).meaning p2 from dual " +
                        " Union " +
        "    select StorageRecs(2).term , StorageRecs(2).meaning  from dual; " +
//        "    close l_rc; " +
        "   ? :=  l_rc;   " +
        "  end; ";

        plsql = AWRCollectionSQL.getStorageMetricsSQL();
        OracleCallableStatement cs = (OracleCallableStatement) c.prepareCall(plsql);
        //cs.setString(1, "12345")
        cs.registerOutParameter(1, OracleTypes.VARCHAR);
        cs.registerOutParameter(2, OracleTypes.VARCHAR);
        cs.registerOutParameter(3, OracleTypes.VARCHAR);
        cs.registerOutParameter(4, OracleTypes.VARCHAR);
        cs.registerOutParameter(5, OracleTypes.VARCHAR);
        cs.registerOutParameter(6, OracleTypes.VARCHAR);
        //cs.registerOutParameter(1, OracleTypes.ARRAY);
        //cs.registerOutParameter(1, OracleTypes.ARRAY);
        //cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.execute();

        //System.out.println("Result = " + cs.getObject(2));

        java.lang.String dgGroups = cs.getString(1);
        dgGroups = cs.getString(1);
        System.out.println(dgGroups);
        dgGroups = cs.getString(2);
        System.out.println(dgGroups);
        dgGroups = cs.getString(3);
        System.out.println(dgGroups);
        dgGroups = cs.getString(4);
        System.out.println(dgGroups);
        dgGroups = cs.getString(5);
        System.out.println(dgGroups);
        dgGroups = cs.getString(6);
        System.out.println(dgGroups);

        cs.close();
        c.close();
*/
    }
}
