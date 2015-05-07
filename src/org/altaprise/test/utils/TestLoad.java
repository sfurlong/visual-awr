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

    public TestLoad() {
    }

    private static Connection getConnection() {
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

    public static void doInsert(int empNo) {

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

    public static void doDelete(int empNo) {
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

    public static void doQuery() {
        Connection c = getConnection();
        String sqlStmt = " select count(*) from SCOTT.EMP ";;
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

        Thread insertThread = new Thread(new InsertLoop());
        insertThread.start();

        Thread deleteThread = new Thread(new DeleteLoop());
        deleteThread.start();

        Thread queryThread = new Thread(new QueryLoop());
        queryThread.start();

    }
}
