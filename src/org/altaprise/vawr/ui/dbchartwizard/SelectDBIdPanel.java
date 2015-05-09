package org.altaprise.vawr.ui.dbchartwizard;

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

import org.altaprise.vawr.awrdata.db.AWRCollectionSQL;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.DBConnectionProps;
import org.altaprise.vawr.utils.PropertyFile;

public class SelectDBIdPanel extends WizardContentBasePanel {
    private static daiListBox jList_connectionNames = new daiListBox();
    private JScrollPane scrollPane_connNames =
        new JScrollPane(this.jList_connectionNames);
    private JButton jButton1 = new JButton();
    private JLabel jLabel1 = new JLabel("DatabaseId--->DatabaseName");

    public SelectDBIdPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SelectDBIdPanel(boolean b) {
        super(b);
    }

    public SelectDBIdPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public SelectDBIdPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }

    private void jbInit() throws Exception {
        this.setLayout(null);

        scrollPane_connNames.setBounds(new Rectangle(125, 60, 250, 255));
        scrollPane_connNames.setSize(new Dimension(250, 256));
        jButton1.setText("Get Database Ids");
        jButton1.setBounds(new Rectangle(405, 60, 135, 20));
        jButton1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton1_actionPerformed(e);
                }
            });

        jLabel1.setBounds(new Rectangle(125, 40, 225, 15));
        this.add(jLabel1, null);
        this.add(jButton1, null);
        this.add(scrollPane_connNames, null);
        
        //Set the Wizard Label
        this.setPanelLabel("2. Select the Database ID to collect metrics from.");
    }

    public static String getSelectedDBId() {
        String ret = null;
        String val = jList_connectionNames.getSelectedValue(); 
        if (val != null) {
            int pos = val.indexOf("-");
            ret = val.substring(0, pos);
        }
        
        return ret;
    }
    
    public static String getSelectedDBName() {
        String ret = null;
        String val = jList_connectionNames.getSelectedValue(); 
        if (val != null) {
            int pos = val.indexOf(">");
            ret = val.substring(pos+1, val.length());
        }
        
        return ret;
    }

    public void showDBIds() {
        
        //SetCursor
        RootFrame.startWaitCursor();

        //Clear the listbox from any previous queries
        this.jList_connectionNames.removeAllItems();
        
        SQLResolver sqlResolver = new SQLResolver();
        try {
            DBRecSet dbIds =
                sqlResolver.executeDynamicSQL(dbconnect.getInstance(), AWRCollectionSQL.getDbIdSQL());

            for (int i = 0; i < dbIds.getSize(); i++) {
                String dbId = dbIds.getRec(i).getAttribVal("DBID");
                String db_name = dbIds.getRec(i).getAttribVal("DB_NAME");

                this.jList_connectionNames.addItem(dbId + "---->" + db_name);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            daiBeans.daiDetailInfoDialog dialog = new daiBeans.daiDetailInfoDialog(null, "Error", true,
                    ex.getLocalizedMessage());
        }
        //SetCursor
        RootFrame.stopWaitCursor();

    }

    private void jButton1_actionPerformed(ActionEvent e) {
        this.showDBIds();
    }
}
