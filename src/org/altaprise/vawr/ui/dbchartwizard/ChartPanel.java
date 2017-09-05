package org.altaprise.vawr.ui.dbchartwizard;


import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.util.Calendar;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.charts.AWRIOPSTimeSeriesChart;
import org.altaprise.vawr.charts.AWRMemoryTimeSeriesChart;
import org.altaprise.vawr.charts.AWRMetricSummaryChart;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.charts.AvgActiveSessionChart;
import org.altaprise.vawr.charts.SizeOnDiskChart;
import org.altaprise.vawr.charts.TopWaitEventsBarChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.SessionMetaData;

public class ChartPanel extends WizardContentBasePanel {
    JComboBox jComboBox_metrics = new JComboBox();
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private JLabel jLabel_selectMetrics = new JLabel("Select AWR Metric to Chart");
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTextArea_reportHeader = new JTextArea();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();

    public ChartPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doNextOperation() {
        this.jTextArea_reportHeader.setText("");
        this.jTextArea_reportHeader.append("AWR Metric Analysis<br>\n");
        this.jTextArea_reportHeader.append("Database ID: " + SelectDBIdPanel.getSelectedDBId() + "  Database Name: " + SelectDBIdPanel.getSelectedDBName()+"<br>\n");
        this.jTextArea_reportHeader.append("Start SnapShot: " + SnapIdSelectPanel.getStartSnapId() + "  End SnapShot: " + SnapIdSelectPanel.getEndSnapId()+"<br>\n");
        this.jTextArea_reportHeader.append("Date: " + Calendar.getInstance().getTime().toString() + "<br>\n");
        this.jTextArea_reportHeader.setCaretPosition(1);
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        //this.setSize(new Dimension(660, 520));

        this.setSize(new Dimension(628, 342));
        jComboBox_metrics.setBounds(new Rectangle(20, 60, 320, 20));
        jComboBox_metrics.setVisible(true);
        jComboBox_metrics.setEditable(false);
        jButton_chartMetric.setBounds(new Rectangle(355, 60, 100, 20));
        jButton_chartMetric.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_chartMetric_actionPerformed(e);
            }
        });
        jLabel_selectMetrics.setBounds(new Rectangle(20, 45, 225, 15));

        //jScrollPane_osInfo.setBounds(new Rectangle(60, 160, 450, 160));
        //this.add(jScrollPane_osInfo, null);
        jScrollPane1.setBounds(new Rectangle(20, 135, 580, 125));
        jLabel1.setText("Report Header");
        jLabel1.setBounds(new Rectangle(20, 105, 160, 15));
        jLabel2.setText("(Use this space for text that will appear on your Report Header, i.e. Machine Name, Date, etc. HTML markup supported.)");
        jLabel2.setBounds(new Rectangle(20, 120, 600, 15));
        jScrollPane1.getViewport().add(jTextArea_reportHeader, null);
        this.add(jLabel2, null);
        this.add(jLabel1, null);
        this.add(jScrollPane1, null);
        this.add(jLabel_selectMetrics, null);
        this.add(jButton_chartMetric, null);
        this.add(jComboBox_metrics, null);
        this.setComboBoxOptions();

        //Set the Wizard Label
        this.setPanelLabel("5. Select a metric and chart it.");
    }

    private void setComboBoxOptions() {
        ArrayList<String> metricNames = AWRMetrics.getInstance().getOracleMetricNames();
        for (int i = 0; i < metricNames.size(); i++) {
            jComboBox_metrics.addItem(metricNames.get(i));
        }
    }

    private void jButton_chartMetric_actionPerformed(ActionEvent e) {

        String startSnapId = SnapIdSelectPanel.getStartSnapId();
        String endSnapId = SnapIdSelectPanel.getEndSnapId();
        
        String oracleMetricName = (String) jComboBox_metrics.getSelectedItem();
        //Convert to AWRMiner metric name
        String metricName = AWRMetrics.getAWRMinerMetricName(oracleMetricName);
        
        if (SessionMetaData.getInstance().debugOn()) {
            AWRData.getInstance().dumpData();
        }

        if (AWRData.getInstance().getAWRDataRecordCount() <= 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(), "No AWR Data Found.", "Error",
                                          JOptionPane.ERROR_MESSAGE);

        } else if (startSnapId.equals(endSnapId)) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(), "The Start & End Snapshot IDs cannot be the same.", "Error",
                                          JOptionPane.ERROR_MESSAGE);
            
        } else if (AWRData.getInstance().awrMetricExists(metricName)|| metricName.equals("SUMMARY")) {
            //SetCursor
            RootFrame.startWaitCursor();
            
            if (metricName.equals("SGA_PGA_TOT")) {
                //Get the memory Data
                new AWRMemoryTimeSeriesChart(metricName, this.jTextArea_reportHeader.getText());
            } else if (metricName.equals("AVG_ACTIVE_SESS_WAITS")) {
                new AvgActiveSessionChart(metricName, this.jTextArea_reportHeader.getText());
            } else if (metricName.equals("TOP_N_TIMED_EVENTS")) {
                new TopWaitEventsBarChart(metricName);
            } else if (metricName.equals("WRITE_IOPS") || metricName.equals("READ_IOPS")) {
                new AWRIOPSTimeSeriesChart(metricName, AWRData.getInstance().getChartHeaderHTML());
            } else if (metricName.equals("SUMMARY")) {
                new AWRMetricSummaryChart(metricName, true);
            } else if (metricName.equals("SIZE_GB")) {
                new SizeOnDiskChart(metricName, this.jTextArea_reportHeader.getText());
            } else {
                String chartHeaderHTML = AWRData.getInstance().getChartHeaderHTML();
                new AWRTimeSeriesChart(metricName, this.jTextArea_reportHeader.getText());
                //Need to fix the query for OS Stats for Chart header.
                //new AWRTimeSeriesChart(awrMetricName, chartHeaderHTML);
            }    
            
            //SetCursor
            RootFrame.stopWaitCursor();
        } else {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          metricName + " Metric Does not exist in this query.", "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
}
