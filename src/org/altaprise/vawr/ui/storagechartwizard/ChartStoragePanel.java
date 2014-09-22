package org.altaprise.vawr.ui.storagechartwizard;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.charts.AWRMemoryTimeSeriesChart;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;


public class ChartStoragePanel extends WizardContentBasePanel {
    JComboBox jComboBox_metrics = new JComboBox();
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private ArrayList<String> _awrStringRecs = null;
    private JLabel jLabel_selectMetrics =
        new JLabel("Select AWR Metric to Chart");

    public ChartStoragePanel() {
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

        jComboBox_metrics.setBounds(new Rectangle(70, 65, 320, 20));
        jComboBox_metrics.setVisible(true);
        jComboBox_metrics.setEditable(false);
        jButton_chartMetric.setBounds(new Rectangle(400, 65, 100, 20));
        jButton_chartMetric.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_chartMetric_actionPerformed(e);
                }
            });
        jLabel_selectMetrics.setBounds(new Rectangle(70, 45, 150, 15));

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

        String oracleMetricName = (String)jComboBox_metrics.getSelectedItem();
        //Convert to AWRMiner metric name
        String awrMetricName = AWRMetrics.getAWRMinerMetricName(oracleMetricName);


        if (AWRData.getInstance().getAWRDataRecordCount() <= 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "No AWR Data Found.",
                                          "Error", JOptionPane.ERROR_MESSAGE);

        } else if (!AWRData.getInstance().awrMetricExists(awrMetricName)) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          awrMetricName + " Metric Does not exist in this query.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //SetCursor
            RootFrame.startWaitCursor();

            if (awrMetricName.equals("SGA_PGA_TOT")) {
                //Get the memory Data
                new AWRMemoryTimeSeriesChart(awrMetricName,"");
            } else {
                new AWRTimeSeriesChart(awrMetricName, "");
            }
            //SetCursor
            RootFrame.stopWaitCursor();
        }
    }
}

