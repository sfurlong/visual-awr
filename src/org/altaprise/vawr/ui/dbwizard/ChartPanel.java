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

        jComboBox_metrics.setBounds(new Rectangle(70, 65, 215, 20));
        jComboBox_metrics.setVisible(true);
        jComboBox_metrics.setEditable(false);
        jButton_chartMetric.setBounds(new Rectangle(295, 65, 100, 20));
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
        //os_cpu os_cpu_max db_wait_ratio db_cpu_ratio        aas    aas_max sql_res_t_cs bkgd_t_per_s
        //logons_s logons_total     exec_s   hard_p_s  l_reads_s  commits_s  read_mb_s read_mb_s_max
        //read_iops read_iops_max write_mb_s write_mb_s_max write_iops write_iops_max  redo_mb_s
        //db_block_gets_s db_block_changes_s
        ArrayList<String> metricNames = AWRMetrics.getInstance().getMetricNames();
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

        String metricName = (String)jComboBox_metrics.getSelectedItem();

        if (awrData == null || awrData.getAWRDataRecordCount() <= 0) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          "No AWR Data Found.",
                                          "Error", JOptionPane.ERROR_MESSAGE);

        } else if (!awrData.awrMetricExists(metricName)) {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          metricName + " Metric Does not exist in this query.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            AWRTimeSeriesChart cpuChart =
                new AWRTimeSeriesChart(metricName, awrData);

        }
    }
}
