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

public class AvgActiveSessionChart extends RootChartFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();

    public AvgActiveSessionChart(String metricName) {
        super("VisualAWR Charting");

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));
        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));

        TimeSeriesCollection xyDataset = createDataset("", metricName);

        JFreeChart chart = createChart(xyDataset, metricName, 0, "", "");

        ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));

        _outerP.add(chartPanel);

        this.add(_thePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }


    public JPanel getChartPanel() {
        return _outerP;    
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    protected TimeSeriesCollection createDataset(String racInst, String awrMetric) {

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
                if (avgActiveSessRec.size() > 0) {
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
                } else {
                    System.out.println("No Avg Active Session Data in file for SnapId: " + snapId);
                }
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
}
