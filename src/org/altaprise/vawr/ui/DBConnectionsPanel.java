package org.altaprise.vawr.ui;

import dai.server.dbService.dbconnect;

import daiBeans.daiListBox;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.ui.dbwizard.DBConnectPanel;
import org.altaprise.vawr.utils.DBConnectionProps;
import org.altaprise.vawr.utils.PropertyFile;

public class DBConnectionsPanel extends JPanel {
    private JTextField jTextField_connName = new JTextField();
    private JTextField jTextField_uId = new JTextField();
    private JTextField jTextField_hostName = new JTextField();
    private JTextField jTextField_port = new JTextField();
    private JTextField jTextField_SID = new JTextField();
    private JTextField jTextField_serviceName = new JTextField();
    private JLabel jLabel_connName = new JLabel("Connection Name");
    private JLabel jLabel_uId = new JLabel("Username");
    private JLabel jLabel_pwd = new JLabel("Password");
    private JLabel jLabel_hostName = new JLabel("Hostname");
    private JLabel jLabel_port = new JLabel("Port");
    private JRadioButton jRadioButton_SID = new JRadioButton("SID");
    private JRadioButton jRadioButton_serviceName =
        new JRadioButton("Service Name");
    private JPasswordField jPasswordField_pwd = new JPasswordField();
    private daiListBox jList_connectionNames = new daiListBox();
    private JButton jButton_testConn = new JButton("Test Connection");
    private JButton jButton_save = new JButton("Save");
    private JButton jButton_cancel = new JButton("Cancel");
    private JTextArea jTextArea_dbTestResults = new JTextArea();
    private JScrollPane jScrollPane1 =
        new JScrollPane(jTextArea_dbTestResults);
    private ButtonGroup buttonGroup_SID = new ButtonGroup();

    PropertyFile _propFile = PropertyFile.getInstance();


    public DBConnectionsPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        this.setSize(new Dimension(651, 419));
        jTextField_connName.setBounds(new Rectangle(300, 25, 320, 20));
        jTextField_uId.setBounds(new Rectangle(300, 55, 320, 20));
        jTextField_hostName.setBounds(new Rectangle(300, 130, 320, 20));
        jTextField_port.setBounds(new Rectangle(300, 160, 320, 20));
        jTextField_SID.setBounds(new Rectangle(300, 200, 320, 20));
        jTextField_serviceName.setBounds(new Rectangle(300, 230, 320, 20));
        jLabel_connName.setBounds(new Rectangle(195, 30, 90, 15));
        jLabel_uId.setBounds(new Rectangle(195, 60, 60, 15));
        jLabel_pwd.setBounds(new Rectangle(195, 85, 60, 15));
        jLabel_hostName.setBounds(new Rectangle(195, 135, 80, 15));
        jLabel_port.setBounds(new Rectangle(195, 165, 95, 15));
        jRadioButton_SID.setBounds(new Rectangle(195, 200, 90, 20));
        jRadioButton_serviceName.setBounds(new Rectangle(195, 230, 95, 20));
        jPasswordField_pwd.setBounds(new Rectangle(300, 85, 320, 20));
        jList_connectionNames.setBounds(new Rectangle(25, 25, 130, 320));
        jList_connectionNames.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jButton_testConn.setBounds(new Rectangle(195, 270, 105, 20));
        jButton_testConn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_testConn_actionPerformed(e);
                }
            });
        jButton_save.setBounds(new Rectangle(430, 390, 75, 21));
        jButton_save.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_save_actionPerformed(e);
                }
            });
        jButton_cancel.setBounds(new Rectangle(545, 390, 75, 21));
        jButton_cancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_cancel_actionPerformed(e);
                }
            });
        jTextArea_dbTestResults.setBounds(new Rectangle(195, 295, 430, 85));
        jTextArea_dbTestResults.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jScrollPane1.setBounds(new Rectangle(195, 295, 425, 85));
        buttonGroup_SID.add(jRadioButton_SID);
        buttonGroup_SID.add(jRadioButton_serviceName);
        jRadioButton_SID.setSelected(true);
        this.jList_connectionNames.adddaiListBoxItemSelectedListener(new daiBeans.daiGenericEventListener() {
                public void genericEventAction(daiBeans.daiGenericEvent e) {
                    doListBoxClickedEvent(e);
                }

            });
        this.add(jScrollPane1, null);
        this.add(jButton_cancel, null);
        this.add(jButton_save, null);
        this.add(jButton_testConn, null);
        this.add(jList_connectionNames, null);
        this.add(jPasswordField_pwd, null);
        this.add(jRadioButton_serviceName, null);
        this.add(jRadioButton_SID, null);
        this.add(jLabel_port, null);
        this.add(jLabel_hostName, null);
        this.add(jLabel_pwd, null);
        this.add(jLabel_uId, null);
        this.add(jLabel_connName, null);
        this.add(jTextField_serviceName, null);
        this.add(jTextField_SID, null);
        this.add(jTextField_port, null);
        this.add(jTextField_hostName, null);
        this.add(jTextField_uId, null);
        this.add(jTextField_connName, null);


        ArrayList<DBConnectionProps> dbProps =
            _propFile.getDBConnectionProps();
        for (int i = 0; i < dbProps.size(); i++) {
            jList_connectionNames.addItem(dbProps.get(i).getConnectionName());
        }
    }

    private void doListBoxClickedEvent(daiBeans.daiGenericEvent e) {
        DBConnectionProps dbProps =
            this._propFile.getDBConnectionProps((String)e.getSource());

        jTextField_connName.setText(dbProps.getConnectionName());
        jTextField_hostName.setText(dbProps.getHostName());
        jTextField_port.setText(dbProps.getPort());
        jTextField_SID.setText(dbProps.getSID());
        jTextField_serviceName.setText(dbProps.getServiceName());
        jTextField_uId.setText(dbProps.getUId());
        jPasswordField_pwd.setText(dbProps.getPwd());
        if (dbProps.isUseSID()) {
            jRadioButton_SID.setSelected(true);
        } else {
            jRadioButton_serviceName.setSelected(true);
        }
    }

    private void jButton_save_actionPerformed(ActionEvent e) {
        String dbConnName = this.jTextField_connName.getText();
        DBConnectionProps dbProps = new DBConnectionProps();
        dbProps.setConnectionName(dbConnName);
        dbProps.setConnectionURL("jdbc:oracle:thin:@"); //@192.168.1.7:1521:orcl"
        dbProps.setDbDriverName("oracle.jdbc.driver.OracleDriver");
        dbProps.setHostName(jTextField_hostName.getText());
        dbProps.setPort(jTextField_port.getText());
        dbProps.setSID(jTextField_SID.getText());
        dbProps.setServiceName(jTextField_serviceName.getText());
        dbProps.setUId(jTextField_uId.getText());
        dbProps.setPwd(jPasswordField_pwd.getText());
        if (jRadioButton_SID.isSelected()) {
            dbProps.setUseSID(true);
        } else {
            dbProps.setUseSID(false);
        }

        _propFile.addDBConnectionProps(dbProps);

        _propFile.serializeIt();

        jList_connectionNames.addItemDistinct(dbConnName);
        
        //Update the DBConnections combobox
        DBConnectPanel.setDBConnections();

    }

    private void jButton_testConn_actionPerformed(ActionEvent e) {
        String dbURL = "jdbc:oracle:thin:@"; //@192.168.1.7:1521:orcl"
        String dbDriverName = "oracle.jdbc.driver.OracleDriver";
        String hostName = jTextField_hostName.getText();
        String port = jTextField_port.getText();
        String SID = jTextField_SID.getText();
        String serviceName = jTextField_serviceName.getText();
        String uId = jTextField_uId.getText();
        String pwd = jPasswordField_pwd.getText();

        System.out.println(hostName);
        System.out.println(port);
        System.out.println(SID);
        System.out.println(serviceName);
        System.out.println(uId);
        System.out.println(pwd);

        dbconnect dbConn = dbconnect.getInstance();

        if (jRadioButton_SID.isSelected()) {
            dbURL += hostName + ":" + port + ":" + SID;
            
        } else {
            dbURL += "//" + hostName + ":" + port + "/" + serviceName;
        }
            
        System.out.println(dbURL);
        String dbConnectResult = null;

        //SetCursor
        RootFrame.startWaitCursor();


        try {
            dbConnectResult =
                    dbConn.connectToDB(dbURL, dbDriverName, uId, pwd);

        } catch (Exception ex) {
            ex.printStackTrace();
            dbConnectResult = ex.getLocalizedMessage();
        }
        this.jTextArea_dbTestResults.setText(dbConnectResult);
        jTextArea_dbTestResults.setCaretPosition(0);

        //SetCursor
        RootFrame.stopWaitCursor();
    }

    private void jButton_cancel_actionPerformed(ActionEvent e) {
        jTextField_connName.setText("");
        jTextField_hostName.setText("");
        jTextField_port.setText("");
        jTextField_SID.setText("");
        jTextField_serviceName.setText("");
        jTextField_uId.setText("");
        jPasswordField_pwd.setText("");
        jTextArea_dbTestResults.setText("");
        jTextArea_dbTestResults.setCaretPosition(0);
        jRadioButton_SID.setSelected(true);
    }
}
