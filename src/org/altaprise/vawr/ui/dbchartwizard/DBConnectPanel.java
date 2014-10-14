package org.altaprise.vawr.ui.dbchartwizard;

import dai.server.dbService.dbconnect;

import dai.shared.businessObjs.DBRecSet;

import daiBeans.daiComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.DBConnectionProps;
import org.altaprise.vawr.utils.PropertyFile;
import org.altaprise.vawr.ui.RootFrame;

public class DBConnectPanel extends WizardContentBasePanel {
    //private static daiComboBox comboBox_DBConns = new daiComboBox();
    private static JComboBox jComboBox_dbConnect = new JComboBox();
    private JButton jButton_connectDB = new JButton("Connect");
    private JTextArea textArea_DBConn = new JTextArea();
    private JScrollPane scrollPaneTextArea = new JScrollPane(textArea_DBConn);
    private JLabel jLabel1 = new JLabel("Select Database Connection");

    public DBConnectPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DBConnectPanel(boolean b) {
        super(b);
    }

    public DBConnectPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public DBConnectPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
     
        jComboBox_dbConnect.setBounds(new Rectangle(45, 65, 250, 20));
        jButton_connectDB.setBounds(new Rectangle(315, 65, 75, 21));
        jButton_connectDB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_connectDB_actionPerformed(e);
                }
            });
        this.scrollPaneTextArea.setBounds(new Rectangle(45, 100, 505, 135));
        jLabel1.setBounds(new Rectangle(45, 50, 215, 15));

        this.textArea_DBConn.setEnabled(false);
        this.textArea_DBConn.setFont(new Font("monospaced", Font.PLAIN, 11));


        this.add(this.scrollPaneTextArea, null);
        this.add(jLabel1, null);
        this.add(jButton_connectDB, null);
        this.add(jComboBox_dbConnect, null);
        
        setDBConnections();
        
        //Set the Wizard Label
        this.setPanelLabel("1. Select the DB Connection for collection AWR data.");
    }

    public static String getDBConnectName() {
        String dbConnName = (String)jComboBox_dbConnect.getSelectedItem();
        return dbConnName;
    }
    
    //Just in case we added some connections since we started, allow other windows
    //to call this.
    public static void setDBConnections() {
        jComboBox_dbConnect.removeAllItems();
        PropertyFile propFile = PropertyFile.getInstance();
        ArrayList<DBConnectionProps> dbProps = propFile.getDBConnectionProps();
        for (int i=0; i<dbProps.size(); i++) {
            jComboBox_dbConnect.addItem(dbProps.get(i).getConnectionName());
        }
    }
    
    private void jButton_connectDB_actionPerformed(ActionEvent e) {
        
        //SetCursor
        RootFrame.startWaitCursor();
        
        String dbConnName = (String)jComboBox_dbConnect.getSelectedItem();
        DBConnectionProps dbProps = PropertyFile.getInstance().getDBConnectionProps(dbConnName);
        
        String dbURL = "jdbc:oracle:thin:@"; //@192.168.1.7:1521:orcl"
        String dbDriverName = "oracle.jdbc.driver.OracleDriver";
               
        dbconnect dbConn = dbconnect.getInstance();


        if (dbProps.isUseSID()) {
            dbURL += dbProps.getHostName() + ":" + dbProps.getPort() + ":" + dbProps.getSID();
            
        } else {
            dbURL += dbProps.getHostName() + ":" + dbProps.getPort() + "/" + dbProps.getServiceName();
        }
        
        System.out.println(dbURL);
        String dbConnectResult = null;
        try {
            dbConnectResult = dbConn.connectToDB(dbURL,
                               dbDriverName, dbProps.getUId(),
                               dbProps.getPwd());

            
        } catch (Exception ex) {
            ex.printStackTrace();
            dbConnectResult = ex.getLocalizedMessage();
        }
        
        this.textArea_DBConn.setText(dbConnectResult);
        this.textArea_DBConn.setCaretPosition(0);

        RootFrame.stopWaitCursor();

    }
}
