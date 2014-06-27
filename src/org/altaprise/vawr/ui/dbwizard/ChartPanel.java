package org.altaprise.vawr.ui.dbwizard;


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

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;

public class ChartPanel extends WizardContentBasePanel {
    JComboBox jComboBox_metrics = new JComboBox();
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private ArrayList<String> _awrStringRecs = null;
    private JLabel jLabel_selectMetrics =
        new JLabel("Select AWR Metric to Chart");

    public ChartPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChartPanel(boolean b) {
        super(b);
    }

    public ChartPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public ChartPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }


    private void jbInit() throws Exception {
        this.setLayout(null);
        this.setSize(new Dimension(660, 520));

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
    }

    protected void doNextOperation() {
        _awrStringRecs = AWRQueryPanel.getAWRData();
    }

    private void setComboBoxOptions() {
        ArrayList<String> metricNames = AWRMetrics.getInstance().getOracleMetricNames();
        for (int i = 0; i < metricNames.size(); i++) {
            jComboBox_metrics.addItem(metricNames.get(i));
        }
    }

    private void jButton_chartMetric_actionPerformed(ActionEvent e) {
        AWRData awrData = new AWRData();

        for (int i = 0; i < _awrStringRecs.size(); i++) {
            if (i == 0) {
                awrData.parseHeaders(_awrStringRecs.get(i));
            } else {
                awrData.parseDataRecord(_awrStringRecs.get(i));
            }
        }

        String oracleMetricName = (String)jComboBox_metrics.getSelectedItem();
        //Convert to AWRMiner metric name
        String awrMetricName = AWRMetrics.getAWRMinerMetricName(oracleMetricName);


        if (awrData == null || awrData.getAWRDataRecordCount() <= 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "No AWR Data Found.",
                                          "Error", JOptionPane.ERROR_MESSAGE);

        } else if (!awrData.awrMetricExists(awrMetricName)) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          awrMetricName + " Metric Does not exist in this query.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            AWRTimeSeriesChart cpuChart =
                new AWRTimeSeriesChart(awrMetricName, awrData);

        }
    }
}
