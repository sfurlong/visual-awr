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
package org.altaprise.vawr.ui.dbchartwizard;

import dai.server.dbService.SQLResolver;

import dai.shared.businessObjs.DBRecSet;

import daiBeans.daiComboBox;

import daiBeans.daiDetailInfoDialog;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JTextField;

import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
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
    private JLabel jLabel3 = new JLabel("Database ID:");

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

        this.setSize(new Dimension(537, 353));
        scrollPane_startSnapId.setBounds(new Rectangle(55, 70, 200, 280));
        scrollPane_endSnapId.setBounds(new Rectangle(345, 70, 200, 280));
        jButton1.setText("Get Snapshot Ids");
        jButton1.setBounds(new Rectangle(345, 25, 125, 20));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });
        jTextField_dbId.setBounds(new Rectangle(155, 25, 145, 20));
        jTextField_dbId.setEditable(false);
        jLabel1.setBounds(new Rectangle(60, 55, 200, 15));
        jLabel2.setBounds(new Rectangle(345, 55, 190, 15));
        jLabel3.setBounds(new Rectangle(60, 30, 95, 15));
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        this.add(jTextField_dbId, null);
        this.add(jButton1, null);
        this.add(scrollPane_startSnapId, null);
        this.add(scrollPane_endSnapId, null);
        
        //Set the Wizard Label
        this.setPanelLabel("3. Select the Begining and Ending Snapshot Ids for reporting.");

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

        //SetCursor
        RootFrame.startWaitCursor();

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

                this.jList_startSnapId.addItemDistinct(snapId + "---->" +
                                               beginIntervalTime);
            }

            for (int i = 0; i < snapIds.getSize(); i++) {
                //snap_id, startup_time, begin_interval_time
                String snapId = snapIds.getRec(i).getAttribVal("SNAP_ID");
                String beginIntervalTime =
                    snapIds.getRec(i).getAttribVal("BEGIN_INTERVAL_TIME");

                this.jList_endSnapId.addItemDistinct(snapId + "---->" +
                                             beginIntervalTime);
            }

        } catch (Exception ex) {
            daiDetailInfoDialog dialog = new daiDetailInfoDialog(null, "Error", true,
                    ex.getLocalizedMessage());
            ex.printStackTrace();
        }

        //SetCursor
        RootFrame.stopWaitCursor();
    }

    private void jButton1_actionPerformed(ActionEvent e) {
        if (jTextField_dbId.getText() == null || jTextField_dbId.getText().length() == 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "Invalid Database ID",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.showSnapShots();
    }
    
    private void refreshPanel() {
        this.jList_startSnapId.removeAllItems();
        this.jList_endSnapId.removeAllItems();
    }
        
}
