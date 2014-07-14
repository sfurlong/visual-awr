package org.altaprise.vawr.charts;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.WindowEvent;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Iterator;
import java.util.LinkedHashSet;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;

import org.altaprise.vawr.awrdata.AWRRecord;

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
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

public class AWRTimeSeriesChart extends JFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();
    int _totalNumRacInstances = 0;

    public AWRTimeSeriesChart() {

    }

    public AWRTimeSeriesChart(String metricName) {
        super("Visual AWR Charting");

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));
        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));

        _totalNumRacInstances = AWRData.getInstance().getNumRACInstances();

        for (int i = 0; i < _totalNumRacInstances; i++) {

            int racInstNum = i + 1;

            String racInstNumS = Integer.toString(racInstNum);

            TimeSeriesCollection xyDataset = createDataset(racInstNumS, metricName);

            JFreeChart chart = createChart(xyDataset, metricName, racInstNum);

            ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

            _outerP.add(chartPanel);
        }

        add(_thePanel, BorderLayout.CENTER);

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


    private TimeSeriesCollection createDataset(String racInst, String awrMetric) {

        String metricLabel = AWRMetrics.getInstance().getMetricDescription(awrMetric);
        TimeSeriesCollection xyDataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries(metricLabel);

        LinkedHashSet<String> snapshotIds = AWRData.getInstance().getUniqueSnapshotIds();
        Iterator iter = snapshotIds.iterator();
        while (iter.hasNext()) {
            String snapshotId = (String) iter.next();
            String metricValS = "0.0";
            Date snapshotDate = null;

            try {
                AWRRecord awrRec = AWRData.getInstance().getAWRRecordByKey(snapshotId, racInst);

                if (awrRec != null) {
                    metricValS = awrRec.getVal(awrMetric.toUpperCase());
                    snapshotDate = awrRec.getSnapShotDateTime();
                } else {
                    //How do we get the snapshot Id?
                    snapshotDate = this.getMissingSnapshotDate(snapshotId);
                    if (snapshotDate == null) {
                        throw new Exception("Could not find the snapshot date to plot.");
                    }
                }

                if (SessionMetaData.getInstance().debugOn()) {
                    System.out.println("TRACE: Adding date/SnapId/InstId/val: " + snapshotDate.toString() + "/" +
                                       snapshotId + "/" + racInst + "/" + metricValS + "/" + awrRec);
                }

                double metricValD = Double.parseDouble(metricValS);
                s1.add(new Minute(snapshotDate), metricValD);

            } catch (org.jfree.data.general.SeriesException se) {
                System.out.println("Error plotting SnapShot: " + snapshotId + ", Inst: " + racInst + " " +
                                   snapshotDate.toString());
                System.out.println(se.getLocalizedMessage());
                if (SessionMetaData.getInstance().debugOn()) {
                    //se.printStackTrace();
                }

            } catch (Exception e) {
                System.out.println("Error plotting SnapShot: " + snapshotId + ", Inst: " + racInst + " " +
                                   snapshotDate.toString());
                e.printStackTrace();
            }
        }

        xyDataset.addSeries(s1);

        final TimeSeries mav =
            MovingAverage.createMovingAverage(s1, "Moving Average", s1.getItemCount(), s1.getItemCount());

        xyDataset.addSeries(mav);

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

        String chartTitle = AWRMetrics.getInstance().getMetricChartTitle(metricName) + " Instance-" + racInstNum;
        String metricRangeName = AWRMetrics.getInstance().getMetricRangeDescription(metricName);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle,
                                                              // title
                                                              "Date", // x-axis label
                                                              metricRangeName, // y-axis label
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
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));

        return chart;

    }

    private Date getMissingSnapshotDate(String snapId) {
        boolean found = false;
        int idx = 0;
        Date snapDate = null;
        while (!found && idx <= _totalNumRacInstances) {
            idx++;
            String instNumS = Integer.toString(idx);
            AWRRecord awrRec = AWRData.getInstance().getAWRRecordByKey(snapId, instNumS);
            if (awrRec != null) {
                snapDate = awrRec.getSnapShotDateTime();
                found = true;
            }
        }
        return snapDate;
    }

}
