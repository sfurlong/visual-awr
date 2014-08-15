package org.altaprise.vawr.ui;

import java.awt.*;
import java.awt.event.*;

import java.awt.event.ActionEvent;


import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.file.ReadAWRMinerFile;
import org.altaprise.vawr.charts.AWRMemoryTimeSeriesChart;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.charts.AvgActiveSessionChart;
import org.altaprise.vawr.charts.TopWaitEventsBarChart;
import org.altaprise.vawr.utils.SessionMetaData;

public class ChartFilePanel extends JPanel {
    private JTextField jTextField_fileName = new JTextField();
    private JButton jButton_selectFile = new JButton();
    private JButton jButton_chart = new JButton();
    private JComboBox jComboBox_metricName = new JComboBox();
    private JPanel  jPanel_panelTitle = new JPanel();
    private JPanel jPanel_contentPanel = new JPanel();
    private JLabel jLabel_panelTitle = new JLabel();

    private static String AWR_FILE_NAME = "INITIALIZED";
    private ReadAWRMinerFile _awrParser = null;

    /**The default constructor for form
     */
    public ChartFilePanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**the JbInit method
     */
    public void jbInit() throws Exception {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        jLabel_panelTitle.setText("Select an AWRMiner file, then chart an AWR metric.");
        jLabel_panelTitle.setBounds(new Rectangle(20, 5, 400, 30));
        jLabel_panelTitle.setFont(new Font("Arial", 1, 16));

        jPanel_panelTitle.setLayout(null);
        jPanel_panelTitle.setBackground(new Color(247, 247, 247));
        jPanel_panelTitle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jPanel_panelTitle.setMinimumSize(new Dimension(48, 50));
        jPanel_panelTitle.setPreferredSize(new Dimension(48, 50));
        jPanel_panelTitle.add(jLabel_panelTitle, null);
        
        jPanel_contentPanel.setLayout(null);
        //jPanel_contentPanel.setSize(new Dimension(706, 300));
        jPanel_contentPanel.add(jComboBox_metricName, null);
        jPanel_contentPanel.add(jButton_chart, null);

        jPanel_contentPanel.add(jButton_selectFile, null);
        jPanel_contentPanel.add(jTextField_fileName, null);
        this.add(jPanel_panelTitle, BorderLayout.NORTH);

        this.add(jPanel_contentPanel, BorderLayout.CENTER);
        jTextField_fileName.setBounds(new Rectangle(40, 60, 480, 20));
        String appHome = SessionMetaData.getInstance().getDaiHome();
        //        jTextField_fileName.setText(appHome + "\\testing\\awr-hist-389926331-U-775-985.out");
        jButton_selectFile.setText("Select File");
        jButton_selectFile.setBounds(new Rectangle(525, 60, 85, 20));
        jButton_selectFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_selectFile_actionPerformed(e);
                }
            });
        jButton_chart.setText("Chart Metric");
        jButton_chart.setBounds(new Rectangle(345, 110, 95, 20));
        jButton_chart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_chart_actionPerformed(e);
                }
            });
        jComboBox_metricName.setBounds(new Rectangle(40, 110, 300, 20));

        setComboBoxOptions();
    }

    private void jButton_selectFile_actionPerformed(ActionEvent e) {

        String appHome = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(appHome);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //    "JPG & GIF Images", "jpg", "gif");
        //chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = chooser.getSelectedFile().getName();
            String filePath = chooser.getSelectedFile().getPath();
            System.out.println("You chose to open this file: " + filePath);
            this.jTextField_fileName.setText(filePath);
        }

    }

    private void jButton_chart_actionPerformed(ActionEvent e) {
        String selectedFile = this.jTextField_fileName.getText();

        //SetCursor
        RootFrame.startWaitCursor();

        try {

            if (!AWR_FILE_NAME.equals(selectedFile)) {
                AWRData.getInstance().clearAWRData();
                _awrParser = new ReadAWRMinerFile();
                _awrParser.parse(selectedFile);
                _awrParser.parseMemData(selectedFile);
                AWR_FILE_NAME = selectedFile;
                if (SessionMetaData.getInstance().debugOn()) {
                    AWRData.getInstance().dumpData();
                }
            }

            //Chart the data
            String oracleMetricName = (String)jComboBox_metricName.getSelectedItem();
            //Convert to AWRMiner metric name
            String metricName = AWRMetrics.getAWRMinerMetricName(oracleMetricName);

            if (AWRData.getInstance().awrMetricExists(metricName)) {
                if (metricName.equals("SGA_PGA_TOT")) {
                    new AWRMemoryTimeSeriesChart(metricName);
                } else if (metricName.equals("AVG_ACTIVE_SESS_WAITS")) {
                    new AvgActiveSessionChart(metricName);
                } else if (metricName.equals("TOP_N_TIMED_EVENTS")) {
                        new TopWaitEventsBarChart(metricName);
                } else {
                    new AWRTimeSeriesChart(metricName);
                }
            } else {
                JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                              metricName +
                                              " Metric Does not exist in this file.",
                                              "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            //SetCursor
            RootFrame.stopWaitCursor();
            
            ex.printStackTrace();

            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          ex.getLocalizedMessage(), "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
        //SetCursor
        RootFrame.stopWaitCursor();

    }

    private void setComboBoxOptions() {
        ArrayList<String> metricNames = AWRMetrics.getInstance().getOracleMetricNames();
        for (int i = 0; i < metricNames.size(); i++) {
            jComboBox_metricName.addItem(metricNames.get(i));
        }
    }
}
