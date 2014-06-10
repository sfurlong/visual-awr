package org.altaprise.vawr.ui.dbwizard;

import dai.server.dbService.SQLResolver;

import dai.shared.businessObjs.DBRecSet;

import daiBeans.daiComboBox;

import daiBeans.daiListBox;

import dai.server.dbService.dbconnect;

import dai.shared.businessObjs.DBAttributes;

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

import javax.swing.JTextField;

import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;
import org.altaprise.vawr.utils.DBConnectionProps;
import org.altaprise.vawr.utils.PropertyFile;

public class SnapIdSelectPanel extends WizardContentBasePanel {
    private static daiListBox jList_startSnapId = new daiListBox();
    private JScrollPane scrollPane_startSnapId =
        new JScrollPane(this.jList_startSnapId);
    private static daiListBox jList_endSnapId = new daiListBox();
    private JScrollPane scrollPane_endSnapId =
        new JScrollPane(this.jList_endSnapId);
    private JButton jButton1 = new JButton();
    private JTextField jTextField_dbId = new JTextField();
    private JLabel jLabel1 = new JLabel("Select Begining Snapshot ID");
    private JLabel jLabel2 = new JLabel("Select Ending Snapshot ID");

    public SnapIdSelectPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SnapIdSelectPanel(boolean b) {
        super(b);
    }

    public SnapIdSelectPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public SnapIdSelectPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        this.setSize(new Dimension(693, 422));

        scrollPane_startSnapId.setBounds(new Rectangle(55, 70, 200, 280));
        scrollPane_endSnapId.setBounds(new Rectangle(345, 70, 200, 280));
        jButton1.setText("Get Snapshot Ids");
        jButton1.setBounds(new Rectangle(250, 25, 125, 20));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        jTextField_dbId.setBounds(new Rectangle(95, 25, 145, 20));
        jTextField_dbId.setEditable(false);
        jLabel1.setBounds(new Rectangle(60, 55, 150, 15));
        jLabel2.setBounds(new Rectangle(345, 55, 140, 15));
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        this.add(jTextField_dbId, null);
        this.add(jButton1, null);
        this.add(scrollPane_startSnapId, null);
        this.add(scrollPane_endSnapId, null);


    }

    public static String getStartSnapId() {
        String ret = null;
        String val = jList_startSnapId.getSelectedValue();
        if (val != null) {
            int pos = val.indexOf("-");
            ret = val.substring(0, pos);
        }

        return ret;
    }

    public static String getEndSnapId() {
        String ret = null;
        String val = jList_endSnapId.getSelectedValue();
        if (val != null) {
            int pos = val.indexOf("-");
            ret = val.substring(0, pos);
        }

        return ret;
    }

    protected void doNextOperation() {
        jTextField_dbId.setText(SelectDBIdPanel.getSelectedDBId());
    }

    private void showSnapShots() {
        this.refreshPanel();
        SQLResolver sqlResolver = new SQLResolver();
        try {
            long dbId = Long.parseLong(jTextField_dbId.getText());

            DBRecSet snapIds =
                sqlResolver.executeDynamicSQL(dbconnect.getInstance(), AWRCollectionSQL.getAllSnapIdsAndTimesSQL(dbId));

            for (int i = 0; i < snapIds.getSize(); i++) {
                //snap_id, startup_time, begin_interval_time
                String snapId = snapIds.getRec(i).getAttribVal("SNAP_ID");
                String beginIntervalTime =
                    snapIds.getRec(i).getAttribVal("BEGIN_INTERVAL_TIME");

                this.jList_startSnapId.addItem(snapId + "---->" +
                                               beginIntervalTime);
            }

            for (int i = 0; i < snapIds.getSize(); i++) {
                //snap_id, startup_time, begin_interval_time
                String snapId = snapIds.getRec(i).getAttribVal("SNAP_ID");
                String beginIntervalTime =
                    snapIds.getRec(i).getAttribVal("BEGIN_INTERVAL_TIME");

                this.jList_endSnapId.addItem(snapId + "---->" +
                                             beginIntervalTime);
            }

        } catch (Exception ex) {
            daiBeans.daiDetailInfoDialog dialog = new daiBeans.daiDetailInfoDialog(null, "Error", true,
                    ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        this.showSnapShots();
    }
    
    private void refreshPanel() {
        this.jList_startSnapId.removeAllItems();
        this.jList_endSnapId.removeAllItems();
    }
        
}
