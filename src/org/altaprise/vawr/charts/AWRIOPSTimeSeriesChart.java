package org.altaprise.vawr.charts;

import java.awt.BorderLayout;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import javax.swing.JPanel;
import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Minute;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class AWRIOPSTimeSeriesChart extends RootChartFrame {

    private BorderLayout borderLayout = new BorderLayout();

    public AWRIOPSTimeSeriesChart() {

    }

    public AWRIOPSTimeSeriesChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        int totalNumRacInstances = 0;
        int _totNumCharts = 0;
                                
        totalNumRacInstances = AWRData.getInstance().getNumRACInstances();
        

        for (int i = 0; i < totalNumRacInstances; i++) {

            int racInstNum = i + 1;

            String racInstNumS = Integer.toString(racInstNum);

            TimeSeriesCollection xyDataset = createDataset(racInstNumS, metricName);

            JFreeChart chart = createChart(xyDataset, metricName, racInstNum, "", "");

            ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

            THE_ROOT_CONTENT_PANEL.add(chartPanel);
            _totNumCharts++;
        }

        //Create the IOPS Summary Chart
        TimeSeriesCollection xyDataset = AWRTimeSeriesChart.createMetricSumDataset(metricName);
        JFreeChart chart = createChart(xyDataset, metricName, 0, metricName + " - Sum All Nodes", "");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);
        THE_ROOT_CONTENT_PANEL.add(chartPanel);
        _totNumCharts++;

        //Create the Read Flash Hits IOPS Summary Chart
        TimeSeriesCollection flashHitsDataset = this.createFlashUtilDataset();
        JFreeChart flashHitsChart = createChart(flashHitsDataset, metricName, 0, "Read Flash Hits vs Total Read IOPS - Sum All Nodes", "");
        XYPlot flashHitsPlot = (XYPlot) flashHitsChart.getPlot();
        flashHitsPlot.getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        ChartPanel flashHitsChartPanel = (ChartPanel) createChartPanel(flashHitsChart);
        THE_ROOT_CONTENT_PANEL.add(flashHitsChartPanel);
        _totNumCharts++;

        //Size of Y should be 200 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + (260*(_totNumCharts+1))));
        add(THE_SCROLL_PANE, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;    
    }
    
    protected TimeSeriesCollection createFlashUtilDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("ReadFlashHits");
        TimeSeries s2 = new TimeSeries("ReadIOPS");
        ArrayList<AWRRecord> sysstatRecs = AWRData.getInstance().getSysstatRecordArray();
        String snapId = "";
        String readFlashHitsValS = "0.0";
        String readIOPSValS = "0.0";

        for (int i = 0; i < sysstatRecs.size(); i++) {
            try {
                snapId = sysstatRecs.get(i).getVal("SNAP_ID");
                readFlashHitsValS = sysstatRecs.get(i).getVal("CELL_FLASH_HITS");
                readIOPSValS = sysstatRecs.get(i).getVal("READ_IOPS");
                
                //Get the snapshot datetime.  RAC Instance is hardcoded to "1"
                Date snapShotDate = AWRData.getInstance().getAWRRecordByKey(snapId, "1").getSnapShotDateTime();

                s1.add(new Minute(snapShotDate), Double.parseDouble(readFlashHitsValS));
                s2.add(new Minute(snapShotDate), Double.parseDouble(readIOPSValS));

            } catch (Exception e) {
                System.out.println("Error at snapid: " + snapId);
                System.out.println(e.getLocalizedMessage());
                if (SessionMetaData.getInstance().debugOn()) {
                    e.printStackTrace();
                }
            }
        }

        xyDataset.addSeries(s1);
        xyDataset.addSeries(s2);

        return xyDataset;
    }
}
