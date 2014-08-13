package org.altaprise.vawr.ui.storagechartwizard;

import dai.server.dbService.SQLResolver;
import dai.server.dbService.dbconnect;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import daiBeans.daiComboBox;

import java.awt.Dimension;
import java.awt.LayoutManager;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JTextField;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;

public class StorageQueryPanel extends WizardContentBasePanel {
    private JButton jButton_doQuery = new JButton("Query AWR Data");
    private JTextArea textArea_awrData = new JTextArea();
    private JScrollPane scrollPaneTextArea = new JScrollPane(textArea_awrData);
    private JTextField jTextField_connName = new JTextField();
    private JTextField jTextField_dbId = new JTextField();
    private JTextField jTextField_startSnapId = new JTextField();
    private JTextField jTextField_endSnapId = new JTextField();
    private JLabel jLabel_connName = new JLabel("Connection Name:");
    private JLabel jLabel_dbId = new JLabel("Databse ID:");
    private JLabel jLabel_startSnapId = new JLabel("Start Snapshot ID:");
    private JLabel jLabel_endSnapId = new JLabel("End Snapshot ID:");

    public StorageQueryPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        this.setSize(new Dimension(760, 429));

        jButton_doQuery.setBounds(new Rectangle(310, 20, 150, 20));
        jButton_doQuery.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_doQuery_actionPerformed(e);
                }
            });
        scrollPaneTextArea.setBounds(new Rectangle(15, 145, 500, 185));
        scrollPaneTextArea.setSize(new Dimension(500, 185));
        textArea_awrData.setEditable(false);
        jTextField_dbId.setBounds(new Rectangle(115, 50, 140, 20));
        jTextField_dbId.setEditable(false);
        jTextField_startSnapId.setBounds(new Rectangle(115, 80, 140, 20));
        jTextField_startSnapId.setEditable(false);
        jTextField_endSnapId.setBounds(new Rectangle(115, 110, 140, 20));
        jTextField_endSnapId.setEditable(false);
        jTextField_connName.setBounds(new Rectangle(115, 20, 140, 20));
        jTextField_connName.setEditable(false);
        jLabel_connName.setBounds(new Rectangle(10, 25, 100, 15));
        jLabel_dbId.setBounds(new Rectangle(10, 55, 65, 15));
        jLabel_startSnapId.setBounds(new Rectangle(10, 85, 95, 15));
        jLabel_endSnapId.setBounds(new Rectangle(10, 115, 90, 15));

        this.add(jLabel_endSnapId, null);
        this.add(jLabel_startSnapId, null);
        this.add(jLabel_dbId, null);
        this.add(jLabel_connName, null);
        this.add(jTextField_connName, null);
        this.add(jTextField_endSnapId, null);
        this.add(jTextField_startSnapId, null);
        this.add(jTextField_dbId, null);
        this.add(jButton_doQuery, null);
        this.add(scrollPaneTextArea, null);
        
        //Set the Wizard Label
        this.setPanelLabel("4. Query the database for AWR data.");
    }

    protected void doNextOperation() {
        this.jTextField_connName.setText(DBConnectPanel.getDBConnectName());
    }

    private void doAWRQuery() {
        
        //SetCursor
        RootFrame.startWaitCursor();
        
        String textAreaStatus = "";
        //Update the Status in the Text Area
        textAreaStatus += "Running AWR Metrics Query....\n";
        this.textArea_awrData.setText(textAreaStatus);

        //First lets clear any previous AWRData
        AWRData.getInstance().clearAWRData();

        SQLResolver sqlResolver = new SQLResolver();
        try {

            this.textArea_awrData.setText("");

            Connection c = dbconnect.getInstance().getDBConnection();            


            String plsql = AWRCollectionSQL.getStorageMetricsSQL();
            OracleCallableStatement cs = (OracleCallableStatement)c.prepareCall(plsql);
            //cs.setString(1, "12345")
            cs.registerOutParameter(1, OracleTypes.VARCHAR);
            cs.registerOutParameter(2, OracleTypes.VARCHAR);
            cs.registerOutParameter(3, OracleTypes.VARCHAR);
            cs.registerOutParameter(4, OracleTypes.VARCHAR);
            cs.registerOutParameter(5, OracleTypes.VARCHAR);
            cs.registerOutParameter(6, OracleTypes.VARCHAR);
            cs.registerOutParameter(7, OracleTypes.VARCHAR);
            //cs.registerOutParameter(1, OracleTypes.ARRAY);
            //cs.registerOutParameter(1, OracleTypes.ARRAY);
            //cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            //System.out.println("Result = " + cs.getObject(2));

            String textAreaString = "";
            String dgGroups = cs.getString(1);
            textAreaString += dgGroups + "\n";

            dgGroups = cs.getString(2);
            textAreaString += dgGroups + "\n";
            System.out.println(dgGroups);

            dgGroups = cs.getString(3);
            textAreaString += dgGroups + "\n";
            System.out.println(dgGroups);

            dgGroups = cs.getString(4);
            textAreaString += dgGroups + "\n";
            System.out.println(dgGroups);

            dgGroups = cs.getString(5);
            textAreaString += dgGroups + "\n";
            System.out.println(dgGroups);

            dgGroups = cs.getString(6);
            textAreaString += dgGroups + "\n";
            System.out.println(dgGroups);
            
            dgGroups = cs.getString(7);
            textAreaString += dgGroups + "\n";
            System.out.println(dgGroups);
            
            cs.close();
            c.close();





            //Update the Status in the Text Area
            this.textArea_awrData.setText(textAreaString);
            this.textArea_awrData.repaint();

        } catch (Exception ex) {
            daiBeans.daiDetailInfoDialog dialog =
                new daiBeans.daiDetailInfoDialog(null, "Error", true,
                                                 ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        this.textArea_awrData.setCaretPosition(0);

        //SetCursor
        RootFrame.stopWaitCursor();
    }

    private void jButton_doQuery_actionPerformed(ActionEvent e) {

        this.doAWRQuery();
    }
}
