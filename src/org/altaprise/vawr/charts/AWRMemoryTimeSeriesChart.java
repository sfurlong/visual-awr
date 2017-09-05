package org.altaprise.vawr.charts;

import java.awt.BorderLayout;
import java.util.ArrayList;


import java.util.Date;

import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.AWRData;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class AWRMemoryTimeSeriesChart extends RootChartFrame {

    BorderLayout borderLayout = new BorderLayout();
    private int _totalNumRacInstances = 0;
    
    public AWRMemoryTimeSeriesChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));
        _totalNumRacInstances = AWRData.getInstance().getNumRACInstances();

        for (int i = 0; i < _totalNumRacInstances; i++) {

            int racInstNum = i + 1;
            
            String racInstNumS = Integer.toString(racInstNum);

            TimeSeriesCollection xyDataset = createDataset(racInstNumS, metricName);

            JFreeChart chart = createChart(xyDataset, metricName, racInstNum, "", "");

            ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

            chartPanel.setPreferredSize(new java.awt.Dimension(600, 370));

            THE_ROOT_CONTENT_PANEL.add(chartPanel);
        }

        //Size of Y should be 200 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + (260*_totalNumRacInstances)));
        this.add(THE_SCROLL_PANE, BorderLayout.CENTER);
        this.setVisible(true);
    }


    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;    
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    protected TimeSeriesCollection createDataset(String racInst, String awrMetric) {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();
        int racInstP = Integer.parseInt(racInst);
        TimeSeries s1 = new TimeSeries("SGA");
        TimeSeries s2 = new TimeSeries("PGA");
        TimeSeries s3 = new TimeSeries("SGA+PGA");

        ArrayList<AWRRecord> awrRecords = AWRData.getInstance().getAWRRecordArray();
        for (int i = 0; i < awrRecords.size(); i++) {
            AWRRecord awrRec = awrRecords.get(i);
            String sgaValS = awrRec.getVal("SGA");
            String pgaValS = awrRec.getVal("PGA");
            String sga_pga_totValS = awrRec.getVal("SGA_PGA_TOT");
            String inst = awrRec.getVal("INST");
            int instI = Integer.parseInt(inst);
            Date snapShotDate = null;
            try {
                double sgaValD = Double.parseDouble(sgaValS);
                double pgaValD = Double.parseDouble(pgaValS);
                double sga_pga_totValD = Double.parseDouble(sga_pga_totValS);

                snapShotDate = awrRec.getSnapShotDateTime();
                if (instI == racInstP) {
                    if (SessionMetaData.getInstance().debugOn()) {
                        System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                    }
                    s1.add(new Minute(snapShotDate), sgaValD);
                    s2.add(new Minute(snapShotDate), pgaValD);
                    s3.add(new Minute(snapShotDate), sga_pga_totValD);
                }
            } catch (Exception e) {
                System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                System.out.println(e.getLocalizedMessage() + " ClassName: " + this.getClass().getName());
                if (SessionMetaData.getInstance().debugOn()){
                    e.printStackTrace();
                }
            }
        }

        xyDataset.addSeries(s1);
        xyDataset.addSeries(s2);
        xyDataset.addSeries(s3);

        return xyDataset;
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    protected static TimeSeriesCollection createTotMemDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("SGA");
        TimeSeries s2 = new TimeSeries("PGA");
        TimeSeries s3 = new TimeSeries("SGA+PGA");

        LinkedHashSet<String> snapshotIds = AWRData.getInstance().getUniqueSnapshotIds();
        Iterator iter = snapshotIds.iterator();
        while (iter.hasNext()) {
            String snapshotId = (String) iter.next();
            String metricValS = "0.0";
            Date snapshotDate = null;

            double sgaVal = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "SGA");
            double pgaVal = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "PGA");
            double sga_pga_totVal = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "SGA_PGA_TOT");

            try {

                snapshotDate = getMissingSnapshotDate(snapshotId);
                if (snapshotDate == null) {
                    throw new Exception("Could not find the snapshot date to plot.");
                }
                    s1.add(new Minute(snapshotDate), sgaVal);
                    s2.add(new Minute(snapshotDate), pgaVal);
                    s3.add(new Minute(snapshotDate), sga_pga_totVal);
            } catch (Exception e) {
                System.out.println("Error at snapid/inst/sgaValS: " + snapshotId);
                System.out.println(e.getLocalizedMessage() + " ClassName: AWRMemoryTimeSeriesChart");
                if (SessionMetaData.getInstance().debugOn()){
                    e.printStackTrace();
                }
            }
        }

        xyDataset.addSeries(s1);
        xyDataset.addSeries(s2);
        xyDataset.addSeries(s3);

        return xyDataset;
    }

}
