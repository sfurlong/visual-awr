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

public class TestLoad {
    
    static String _pwd = "";

    public TestLoad() {
    }

    private static Connection getConnection() {
        Connection c = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            c = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.1.250:1521/orapdb", "system", _pwd);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return c;
        }
    }

    public static void doInsert(int empNo) {

        Connection c = getConnection();

        java.util.Date hireDate = Calendar.getInstance().getTime();
        java.sql.Date hireDateSQL = new java.sql.Date(hireDate.getTime());
        String sqlStmt = " insert into hr.EMPLOYEES (employee_id, first_name, last_name, job_id, manager_id, Department_id, email, hire_date)" +
            " values " + " ( " + empNo + ", 'FEmp Name', 'LEmp Name', 'IT_PROG', 100, 90, '" + empNo + "', SYSDATE)";
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

    public static void doDelete(int empNo) {
        Connection c = getConnection();
        String sqlStmt = " delete from hr.EMPLOYEES where employee_id = " + empNo;
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

    public static void doQuery() {
        Connection c = getConnection();
        String sqlStmt = " select count(*) from hr.EMPLOYEES ";
        int numRecs = 0;
        try {

            Statement dbStmt = c.createStatement();

            ResultSet resultSet = dbStmt.executeQuery(sqlStmt);
            
            if (resultSet.next()) {
                numRecs = resultSet.getInt(1);
            }
            System.out.println("retrieved: " + numRecs);

            //c.commit();
            c.close();             

        } catch (Exception e) {
            String msg = "Error executing the current statement.\n" + e.getLocalizedMessage() + "\n" + sqlStmt;
            System.out.println(msg);
            e.printStackTrace();
        }
    }

    private static class InsertLoop
        implements Runnable {
        public void run() {
            try {
                for (int i = 0;
                     i < 10000;
                     i++) {
                    // Pause for 4 seconds
                    Thread.sleep(3000);
                    // Print a message
                    doInsert(i);
                }
            } catch (InterruptedException e) {
                System.out.println("I wasn't done!");
            }
        }
    }

    private static class DeleteLoop
        implements Runnable {
        public void run() {
            try {
                for (int i = 0;
                     i < 10000;
                     i++) {
                    // Pause for 4 seconds
                    Thread.sleep(4000);
                    // Print a message
                    doDelete(i);
                }
            } catch (InterruptedException e) {
                System.out.println("I wasn't done!");
            }
        }
    }

    private static class QueryLoop
        implements Runnable {
        public void run() {
            try {
                for (int i = 0;
                     i < 10000;
                     i++) {
                    // Pause for 4 seconds
                    Thread.sleep(2000);
                    // Print a message
                    doQuery();
                }
            } catch (InterruptedException e) {
                System.out.println("I wasn't done!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        
        _pwd = args[0];

        Thread insertThread = new Thread(new InsertLoop());
        insertThread.start();

        Thread deleteThread = new Thread(new DeleteLoop());
        deleteThread.start();

        Thread queryThread = new Thread(new QueryLoop());
        queryThread.start();

    }
}
