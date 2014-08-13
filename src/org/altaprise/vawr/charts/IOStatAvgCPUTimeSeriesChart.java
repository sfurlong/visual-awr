package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBRec;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;


import java.awt.event.WindowEvent;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.awrdata.OSWRecord;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class IOStatAvgCPUTimeSeriesChart extends JFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();

    public IOStatAvgCPUTimeSeriesChart(String metricName) {
        super("Visual AWR Charting");

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));


        //for (int i = 0; i < AWRData.getInstance().getNumRACInstances(); i++) {

            //int racInstNum = i + 1;

            TimeSeriesCollection xyDataset = createDataset();

            JFreeChart chart = createChart(xyDataset, metricName);

            ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

            chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));

            _outerP.add(chartPanel);
        //}

        this.add(_thePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }


    private static final long serialVersionUID = 1L;

    {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow", true));
    }


    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public static JPanel createChartPanel(JFreeChart chart) {


        ChartPanel panel = new ChartPanel(chart);

        int numSeries = panel.getChart().getXYPlot().getSeriesCount();

        for (int i = 0; i < numSeries; i++) {
            //panel.getChart().getXYPlot().getRenderer().setSeriesPaint(1, legend.getColor());
            panel.getChart().getXYPlot().getRenderer().setSeriesStroke(i, new BasicStroke(1.0f));
            //panel.getChart().getXYPlot().getRenderer().setseriess.setSeriesShapesVisible(1, false);
            XYLineAndShapeRenderer r = (XYLineAndShapeRenderer) panel.getChart().getXYPlot().getRenderer();
            r.setSeriesShapesVisible(i, false);
        }

        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    public void windowClosing(final WindowEvent event) {
        if (event.getWindow() == this) {
            dispose();
        }
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    private TimeSeriesCollection createDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("SGA");
        TimeSeries s2 = new TimeSeries("PGA");
        TimeSeries s3 = new TimeSeries("SGA+PGA");

        ArrayList<DBRec> awrRecords = OSWRecord.getInstance().getAvgCPUData();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            String dateS = awrRec.getAttribVal("DATE");
            String timeS = awrRec.getAttribVal("TIME");
            String userCpuS = awrRec.getAttribVal("%user");

            Date snapShotDate = null;
            try {
                double userCpuD = Double.parseDouble(userCpuS);

                snapShotDate = this.getAvgCPUDateTime(dateS, timeS);
                    if (SessionMetaData.getInstance().debugOn()) {
                        //System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                    }
                    s1.add(new Second(snapShotDate), userCpuD);
            } catch (Exception e) {
                //System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                System.out.println(e.getLocalizedMessage());
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
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset, String metricName) {

        String chartTitle = "";//AWRMetrics.getInstance().getMetricChartTitle(metricName);
        String chartYAxisLabel = "";//AWRMetrics.getInstance().getMetricRangeDescription(metricName);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle,
                                                              // title
                                                              "Date", // x-axis label
                                                              chartYAxisLabel, // y-axis label
                                                              dataset, // data
                                                              true, // create legend?
                                                              true, // generate tooltips?
                                                              false); // generate URLs?

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yy HH:mm:ss"));

        return chart;

    }
    
    private Date getAvgCPUDateTime(String dateS, String timeS) {
        //01/30/14 00:59:01
        Calendar cal = Calendar.getInstance();
        int yy = Integer.parseInt(dateS.substring(6, 8)) + 2000;
        int mm = Integer.parseInt(dateS.substring(0, 2)) - 1;
        int dd = Integer.parseInt(dateS.substring(3, 5));
        int hh = Integer.parseInt(timeS.substring(0, 2));
        int min = Integer.parseInt(timeS.substring(3, 5));
        int sec = Integer.parseInt(timeS.substring(6, 8));
        cal.set(yy, mm, dd, hh, min, sec);
        Date date = cal.getTime();
            
        return date;
    }


}
