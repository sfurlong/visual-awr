package org.altaprise.vawr.ui.oswchartwizard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.OSWMetrics;
import org.altaprise.vawr.awrdata.file.ReadCellSrvStatFile;
import org.altaprise.vawr.awrdata.file.ReadTopStatFile;
import org.altaprise.vawr.charts.OSWCellSrvStatChart;
import org.altaprise.vawr.charts.TopStatTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.SessionMetaData;


public class OSWSelectChartPanel extends WizardContentBasePanel {
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private JLabel jLabel_selectMetrics =
        new JLabel("Select AWR Metric to Chart");
    private static String OSW_FILE_NAME = "INITIALIZED";
    private File[] _selectedFiles = null;
    private JLabel jLabel1 = new JLabel();
    private JTextArea jTextArea_chartHeading = new JTextArea(3, 60);
    private ButtonGroup buttonGroup_fileType = new ButtonGroup();
    private JSeparator jSeparator1 = new JSeparator();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTextArea1 = new JTextArea();
    private JComboBox jComboBox_metricNames = new JComboBox();

    public OSWSelectChartPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        //this.setSize(new Dimension(660, 520));

        this.setSize(new Dimension(686, 395));
        jButton_chartMetric.setBounds(new Rectangle(515, 220, 100, 20));
        jButton_chartMetric.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_chartMetric_actionPerformed(e);
            }
        });
        jLabel_selectMetrics.setBounds(new Rectangle(10, 65, 150, 15));

        jLabel_selectMetrics.setText("OSW-ExaWatcher File(s):");
        jLabel1.setText("Report Header");
        jLabel1.setBounds(new Rectangle(10, 145, 110, 15));
        jTextArea_chartHeading.setBounds(new Rectangle(100, 145, 515, 65));
        
        this.jTextArea_chartHeading.setColumns(60);
        this.jTextArea_chartHeading.setRows(3);
        this.jTextArea_chartHeading.setLineWrap(false);

        jTextArea_chartHeading.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jSeparator1.setBounds(new Rectangle(50, 250, 575, 2));
        jScrollPane1.setBounds(new Rectangle(100, 260, 510, 110));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jTextArea1.setEditable(false);
        jTextArea1.setBackground(Color.lightGray);
        jComboBox_metricNames.setBounds(new Rectangle(145, 65, 390, 20));

        jScrollPane1.getViewport().add(jTextArea1, null);
        this.add(jComboBox_metricNames, null);
        this.add(jScrollPane1, null);
        this.add(jSeparator1, null);
        this.add(jTextArea_chartHeading, null);
        this.add(jLabel1, null);
        this.add(jLabel_selectMetrics, null);

        //Set the Wizard Label
        this.add(jButton_chartMetric, null);
        this.setPanelLabel("OSWatcher/ExaWatcher Analytics");
        
        loadComboBox();
    }

    private void loadComboBox() {
           
        ArrayList<String> metricNames = OSWMetrics.getInstance().getMetricNames();
        for (int i=0; i<metricNames.size(); i++) {
            this.jComboBox_metricNames.addItem((String)metricNames.get(i));
        }
    }
    
    private void jButton_chartMetric_actionPerformed(ActionEvent e) {
        
        //SetCursor
        RootFrame.startWaitCursor();

        ReadTopStatFile topStatFileParser = new ReadTopStatFile();
        ReadCellSrvStatFile cellSrvStatFileParser = new ReadCellSrvStatFile();
        
        try {
            
            new OSWCellSrvStatChart((String)this.jComboBox_metricNames.getSelectedItem(), "");
            
        } catch (Exception ex) {
            RootFrame.stopWaitCursor();
            
            ex.printStackTrace();
            String msg = "Are you sure this is a ";
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          ex.getLocalizedMessage() +"\n" + msg, "Error",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            RootFrame.stopWaitCursor();
        }

    }
}

