package org.altaprise.vawr.ui.dbwizard;

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

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JTextField;

import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;
import org.altaprise.vawr.ui.RootFrame;

public class AWRQueryPanel extends WizardContentBasePanel {
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
    private static DBRecSet _awrRecSetData = null;
    private static ArrayList<String> _awrStringRecs = new ArrayList<String>();

    public AWRQueryPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AWRQueryPanel(boolean b) {
        super(b);
    }

    public AWRQueryPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public AWRQueryPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
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
    }

    public static ArrayList<String> getAWRData() {
        return _awrStringRecs;
    }

    protected void doNextOperation() {
        this.jTextField_dbId.setText(SelectDBIdPanel.getSelectedDBId());
        this.jTextField_startSnapId.setText(SnapIdSelectPanel.getStartSnapId());
        this.jTextField_endSnapId.setText(SnapIdSelectPanel.getEndSnapId());
        this.jTextField_connName.setText(DBConnectPanel.getDBConnectName());
    }

    private void doAWRQuery() {
        //Reset AWR Data
        this._awrStringRecs.clear();

        SQLResolver sqlResolver = new SQLResolver();
        try {
            long dbId = Long.parseLong(this.jTextField_dbId.getText());
            long startSnapId =
                Long.parseLong(this.jTextField_startSnapId.getText());
            long endSnapId =
                Long.parseLong(this.jTextField_endSnapId.getText());

            this.textArea_awrData.setText("");
            _awrRecSetData =
                    sqlResolver.executeDynamicSQL(dbconnect.getInstance(), AWRCollectionSQL.getMainAWRMetricsSQL(dbId,
                                                                                        startSnapId,
                                                                                        endSnapId));

            for (int i = 0; i < 1; i++) {
                String rec = "";
                DBRec dbRec = _awrRecSetData.getRec(i);
                for (int j = 0; j < dbRec.size(); j++) {
                    String val = dbRec.getAttrib(j).getName();
                    if (j < dbRec.size()) {
                        rec += val + " ";
                    }
                }
                if (i == 0) {
                    this.textArea_awrData.setText(this.textArea_awrData.getText() +
                                                  rec);
                } else {
                    this.textArea_awrData.setText(this.textArea_awrData.getText() +
                                                  "\n" +
                            rec);
                }
                this._awrStringRecs.add(rec);
            }
            for (int i = 0; i < _awrRecSetData.getSize(); i++) {
                String rec = "";
                DBRec dbRec = _awrRecSetData.getRec(i);
                for (int j = 0; j < dbRec.size(); j++) {
                    String val = dbRec.getAttrib(j).getValue();
                    if (j < dbRec.size()) {
                        rec += val + " ";
                    }
                }
                this.textArea_awrData.setText(this.textArea_awrData.getText() +
                                              "\n" +
                        rec);
                this._awrStringRecs.add(rec);
            }
        } catch (Exception ex) {
            daiBeans.daiDetailInfoDialog dialog =
                new daiBeans.daiDetailInfoDialog(null, "Error", true,
                                                 ex.getLocalizedMessage());
            ex.printStackTrace();
        }
        this.textArea_awrData.setCaretPosition(0);
    }

    private void jButton_doQuery_actionPerformed(ActionEvent e) {
        if (jTextField_dbId.getText() == null || jTextField_dbId.getText().length() == 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "Invalid Database ID",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (jTextField_startSnapId.getText() == null || jTextField_startSnapId.getText().length() == 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "Invalid Start Snapshot ID",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (jTextField_endSnapId.getText() == null || jTextField_endSnapId.getText().length() == 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "Invalid End Snapshot ID",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Clear the previous AWRData
        this.doAWRQuery();
    }
}
