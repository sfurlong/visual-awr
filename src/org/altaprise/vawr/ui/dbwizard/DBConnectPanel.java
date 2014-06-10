package org.altaprise.vawr.ui.dbwizard;

import dai.server.dbService.dbconnect;

import dai.shared.businessObjs.DBRecSet;

import daiBeans.daiComboBox;

import java.awt.Dimension;
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
import org.altaprise.vawr.utils.DBConnectionProps;
import org.altaprise.vawr.utils.PropertyFile;
import org.altaprise.vawr.ui.RootFrame;

public class DBConnectPanel extends WizardContentBasePanel {
    daiComboBox comboBox_DBConns = new daiComboBox();
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
        this.setSize(new Dimension(660, 520));

        comboBox_DBConns.setBounds(new Rectangle(155, 200, 55, 21));
        comboBox_DBConns.setVisible(true);
        jComboBox_dbConnect.setBounds(new Rectangle(105, 65, 250, 20));
        jButton_connectDB.setBounds(new Rectangle(365, 65, 75, 21));
        jButton_connectDB.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_connectDB_actionPerformed(e);
                }
            });
        this.scrollPaneTextArea.setBounds(new Rectangle(45, 100, 505, 135));
        jLabel1.setBounds(new Rectangle(105, 50, 155, 15));
        this.add(this.scrollPaneTextArea, null);

        this.add(jLabel1, null);
        this.add(jButton_connectDB, null);
        this.add(jComboBox_dbConnect, null);
        PropertyFile propFile = PropertyFile.getInstance();
        ArrayList<DBConnectionProps> dbProps = propFile.getDBConnectionProps();
        for (int i=0; i<dbProps.size(); i++) {
            jComboBox_dbConnect.addItem(dbProps.get(i).getConnectionName());
            comboBox_DBConns.addItem(dbProps.get(i).getConnectionName());
        }

    }

    public static String getDBConnectName() {
        String dbConnName = (String)jComboBox_dbConnect.getSelectedItem();
        return dbConnName;
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
