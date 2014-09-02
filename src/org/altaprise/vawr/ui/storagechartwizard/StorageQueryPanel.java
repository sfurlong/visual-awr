package org.altaprise.vawr.ui.storagechartwizard;

import dai.server.dbService.SQLResolver;
import dai.server.dbService.dbconnect;

import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import daiBeans.daiComboBox;

import daiBeans.daiGrid;

import java.awt.Dimension;
import java.awt.LayoutManager;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;

import java.util.ArrayList;

import java.util.StringTokenizer;

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
    //Create and set up the content pane.
    daiGrid outputTable = new daiGrid();

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
        scrollPaneTextArea.setBounds(new Rectangle(15, 145, 500, 125));
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

        outputTable.setBounds(new Rectangle(25, 190, 630, 180));
        this.add(jLabel_endSnapId, null);
        this.add(jLabel_startSnapId, null);
        this.add(jLabel_dbId, null);
        this.add(jLabel_connName, null);
        this.add(jTextField_connName, null);
        this.add(jTextField_endSnapId, null);
        this.add(jTextField_startSnapId, null);
        this.add(jTextField_dbId, null);
        this.add(jButton_doQuery, null);
        //this.add(scrollPaneTextArea, null);

        //Set the Wizard Label
        this.add(outputTable, null);
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

//            { "Disk Group Name", "Num Disks", "Max Tot MB",
//                                                           "Tot MB", "Tot MB Used",
//                                                           "Tot MB Free", "Tot PCT Free"});            String textAreaString = "";
            String dgGroupsRec = cs.getString(1);
            ArrayList<String> vals1 = this.getStorageColValues(dgGroupsRec);
            System.out.println(dgGroupsRec);

            String numDisksRec = cs.getString(2);
            ArrayList<String> vals2 = this.getStorageColValues(numDisksRec);
            System.out.println(numDisksRec);

            String maxTotMBRec = cs.getString(3);
            ArrayList<String> vals3 = this.getStorageColValues(maxTotMBRec);
            System.out.println(maxTotMBRec);

            String totMBRec = cs.getString(4);
            ArrayList<String> vals4 = this.getStorageColValues(totMBRec);
            System.out.println(totMBRec);

            String totMBUsedRec = cs.getString(5);
            ArrayList<String> vals5 = this.getStorageColValues(totMBUsedRec);
            System.out.println(totMBUsedRec);

            String totMBFreeRec = cs.getString(6);
            ArrayList<String> vals6 = this.getStorageColValues(totMBFreeRec);
            System.out.println(totMBFreeRec);
            
            String totPctFreeRec = cs.getString(7);
            ArrayList<String> vals7 = this.getStorageColValues(totPctFreeRec);
            System.out.println(totPctFreeRec);
            
            cs.close();
            //c.close();


            //grid.setOpaque(true); //content panes must be opaque

            //grid.createColumns(14);
            int[] colTypes = new int[7];
            colTypes[0] = daiGrid.CHAR_COL_TYPE;
            colTypes[1] = daiGrid.CHAR_COL_TYPE;
            colTypes[2] = daiGrid.CHAR_COL_TYPE;
            colTypes[3] = daiGrid.CHAR_COL_TYPE;
            colTypes[4] = daiGrid.CHAR_COL_TYPE;
            colTypes[5] = daiGrid.CHAR_COL_TYPE;
            colTypes[6] = daiGrid.CHAR_COL_TYPE;
            outputTable.createColumns(colTypes);
            outputTable.setHeaderNames(new String[] { "Disk Group Name", "Num Disks", "Max Size Per Disk MB",
                                               "Tot DG Size MB", "Tot Used MB",
                                               "Tot Free MB", "Tot PCT Free"});
            outputTable.removeAllRows();
            for (int i=0; i<vals1.size(); i++) {
                outputTable.addRow();
                outputTable.set(i, 0, vals1.get(i));
                outputTable.set(i, 1, vals2.get(i));
                outputTable.set(i, 2, vals3.get(i));
                outputTable.set(i, 3, vals4.get(i));
                outputTable.set(i, 4, vals5.get(i));
                outputTable.set(i, 5, vals6.get(i));
                outputTable.set(i, 6, vals7.get(i));
            }
            //grid.set(0, 2, "123.00");
            //grid.setColEditable(2, false);
            //grid.setColEditable(3, false);
            //grid.setColEditable(5, false);
            //              grid.set(0, 1, new Double("123.00"));
            //grid.hideColumn(3);
            outputTable.setColumnSize(0, 100);
            outputTable.setColumnSize(1, 75);
            outputTable.setColumnSize(2, 75);
            outputTable.setColumnSize(3, 75);
            outputTable.setColumnSize(4, 75);
            outputTable.setColumnSize(5, 75);
            outputTable.setColumnSize(6, 75);
            //Turn off row selection
            outputTable.allowRowSelection(false);





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

    private ArrayList<String> getStorageColValues (String rec) {
        ArrayList<String> ret = new ArrayList<String>();
        if (rec.length() > 0) {

            StringTokenizer st = new StringTokenizer(rec, "~");
            DBAttributes dbAttribs = new DBAttributes();
            while (st.hasMoreTokens()) {
                String tok = st.nextToken().trim();
                ret.add(tok);
            }
        }
        return ret;
    }
    private void jButton_doQuery_actionPerformed(ActionEvent e) {

        this.doAWRQuery();
    }
}
