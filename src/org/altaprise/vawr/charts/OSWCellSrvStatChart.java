package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.JPanel;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.OSWMetrics;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class OSWCellSrvStatChart extends RootChartFrame {

    private BorderLayout borderLayout = new BorderLayout();
    private int _totalNumRacInstances = 0;

    public OSWCellSrvStatChart() {

    }

    public OSWCellSrvStatChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));


        TimeSeriesCollection xyDataset = createDataset("", metricName);

        JFreeChart chart = createChart(xyDataset, metricName, 0, metricName, "range");

        ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

        THE_ROOT_CONTENT_PANEL.add(chartPanel);

        //Size of Y should be 200 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + (260 * _totalNumRacInstances)));
        add(THE_SCROLL_PANE, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;
    }

    protected TimeSeriesCollection createDataset(String racInst, String cellSrvMetricName) {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries(cellSrvMetricName);

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getCellSrvStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String idleCpuS = awrRec.getAttribVal(cellSrvMetricName);

            try {
                double idleCpuD = Double.parseDouble(idleCpuS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //                    System.out.println("Date/idle/user/kernel/iowait/swap: " + snapShotDate.toString() + "/" +
                    //                                       idleCpuD + "/" + userCpuD);
                }
                s1.add(new Second(snapShotDate), idleCpuD);
            } catch (Exception e) {
                //System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                System.out.println(e.getLocalizedMessage());
                if (SessionMetaData.getInstance().debugOn()) {
                    e.printStackTrace();
                }
            }
        }

        xyDataset.addSeries(s1);

        return xyDataset;
    }

}
