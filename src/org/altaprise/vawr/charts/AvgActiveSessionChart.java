package org.altaprise.vawr.charts;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;


import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.event.WindowEvent;

import java.awt.geom.Rectangle2D;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.awrdata.AvgActiveSessRecord;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.StandardGradientPaintTransformer;

public class AvgActiveSessionChart extends JFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();

    public AvgActiveSessionChart(String metricName) {
        super("Visual AWR Charting");

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));


        TimeSeriesCollection xyDataset = createDataset(1, metricName);

        JFreeChart chart = createChart(xyDataset, metricName, 1);

        ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));

        _outerP.add(chartPanel);

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
    private TimeSeriesCollection createDataset(int racInstance, String awrMetric) {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("Administrative");
        TimeSeries s2 = new TimeSeries("Application");
        TimeSeries s3 = new TimeSeries("Cluster");
        TimeSeries s4 = new TimeSeries("Commit");
        TimeSeries s5 = new TimeSeries("Concurrency");
        TimeSeries s6 = new TimeSeries("Configuration");
        TimeSeries s7 = new TimeSeries("DB-CPU");
        TimeSeries s8 = new TimeSeries("Network");
        TimeSeries s9 = new TimeSeries("Other");
        TimeSeries s10 = new TimeSeries("System-IO");
        TimeSeries s11 = new TimeSeries("User-IO");

        ArrayList<AWRRecord> avgActiveSessRecs = AWRData.getInstance().getAvgActiveSessRecordArray();

        String snapId = "";

        for (int i = 0; i < avgActiveSessRecs.size(); i++) {
            try {
                ArrayList<AvgActiveSessRecord> avgActiveSessRec = avgActiveSessRecs.get(i).getAvgActiveSessData();
                snapId = avgActiveSessRecs.get(i).getSnapId();

                Date snapShotDate = avgActiveSessRecs.get(i).getSnapShotDateTime();
                s1.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(0).getAVG_SESS()));
                s2.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(1).getAVG_SESS()));
                s3.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(2).getAVG_SESS()));
                s4.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(3).getAVG_SESS()));
                s5.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(4).getAVG_SESS()));
                s6.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(5).getAVG_SESS()));
                s7.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(6).getAVG_SESS()));
                s8.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(7).getAVG_SESS()));
                s9.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(8).getAVG_SESS()));
                s10.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(9).getAVG_SESS()));
                s11.add(new Minute(snapShotDate), Double.parseDouble(avgActiveSessRec.get(10).getAVG_SESS()));

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
        xyDataset.addSeries(s3);
        xyDataset.addSeries(s4);
        xyDataset.addSeries(s5);
        xyDataset.addSeries(s6);
        xyDataset.addSeries(s7);
        xyDataset.addSeries(s8);
        xyDataset.addSeries(s9);
        xyDataset.addSeries(s10);
        xyDataset.addSeries(s11);

        return xyDataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(XYDataset dataset, String metricName, int racInstNum) {

        String chartTitle =
            "Average ActiveSessions"; //AWRMetrics.getInstance().getMetricChartTitle(metricName) + " Instance-" + racInstNum;
        String chartYAxisLabel = "Sess"; //AWRMetrics.getInstance().getMetricRangeDescription(metricName);
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

        /*
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));
*/

        ///
        XYItemRenderer r2 = new XYLineAndShapeRenderer(true, false) {

            public LegendItem getLegendItem(int datasetIndex, int series) {
                if (getPlot() == null) {
                    return null;
                }
                XYDataset dataset = getPlot().getDataset(datasetIndex);
                if (dataset == null) {
                    return null;
                }
                String label = dataset.getSeriesKey(series).toString();
                LegendItem legendItem = new LegendItem(label, lookupSeriesPaint(series));
                legendItem.setLine(new Rectangle2D.Double(0.0, 0.0, 5.0,
                                                          5.0)); //setLine takes a Shape, not just Lines so you can pass any Shape to it...
                return legendItem;
            }
        };
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r2;
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setDrawSeriesLineAsPath(true);
        plot.setRenderer(r2);
        ///


        return chart;

    }


}
