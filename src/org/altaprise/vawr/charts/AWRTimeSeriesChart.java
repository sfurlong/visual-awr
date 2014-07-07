package org.altaprise.vawr.charts;

import java.awt.BasicStroke;

import java.awt.Color;

import java.awt.event.WindowEvent;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;

import javax.swing.BoxLayout;
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

public class AWRTimeSeriesChart extends ApplicationFrame {
    
    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    
    public AWRTimeSeriesChart(String metricName) {
        super("Oracle DB Performance Analytics");
        
        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));

        
        for (int i=0;i<AWRData.getInstance().getNumRACInstances(); i++) {
            TimeSeriesCollection xyDataset = createDataset(i+1, metricName);
            
            JFreeChart chart = createChart(xyDataset, metricName);

            ChartPanel chartPanel = (ChartPanel)createChartPanel(chart);
            
            chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
            
            _outerP.add(chartPanel);
        }
        
        this.setContentPane(_thePanel);
        
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);
    }


    private static final long serialVersionUID = 1L;

    {
        // set a theme using the new shadow generator feature available in
        // 1.0.14 - for backwards compatibility it is not enabled by default
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                                                          true));
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
            panel.getChart().getXYPlot().getRenderer().setSeriesStroke(i,
                                                                       new BasicStroke(1.0f));
            //panel.getChart().getXYPlot().getRenderer().setseriess.setSeriesShapesVisible(1, false);
            XYLineAndShapeRenderer r =
                (XYLineAndShapeRenderer)panel.getChart().getXYPlot().getRenderer();
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
        TimeSeries s1 = new TimeSeries("Instance " + racInstance);

        ArrayList<AWRRecord> awrRecords = AWRData.getInstance().getAWRRecordArray();
        for (int i = 0; i < awrRecords.size(); i++) {
            AWRRecord awrRec = awrRecords.get(i);
            String metricValS = awrRec.getVal(awrMetric.toUpperCase());
            String inst = awrRec.getVal("INST");
            int instI = Integer.parseInt(inst);
            double metricValD = Double.parseDouble(metricValS);

            Date date = awrRec.getSnapShotDateTime();
            try {
                if (instI == racInstance) {
                    if (SessionMetaData.getInstance().debugOn()) {
                        //System.out.println("insert# " + i + ", inst: " + instI +
                        //                   " " + date.toString());
                    }
                    s1.add(new Minute(date), metricValD);
                }
            } catch (Exception e) {
                System.out.println("insert# " + i + ", inst: " + instI +
                                   " " + date.toString());
                e.printStackTrace();
            }
        }

        xyDataset.addSeries(s1);

        final TimeSeries mav = MovingAverage.createMovingAverage(
                    s1, "Moving Average", s1.getItemCount(), s1.getItemCount());
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
    private static JFreeChart createChart(XYDataset dataset, String metricName) {

        String chartTitle = AWRMetrics.getInstance().getMetricChartTitle(metricName);
        String chartYAxisLabel = AWRMetrics.getInstance().getMetricRangeDescription(metricName);
        JFreeChart chart =
            ChartFactory.createTimeSeriesChart(chartTitle,
                // title
                "Date", // x-axis label
                chartYAxisLabel, // y-axis label
                dataset, // data
                true, // create legend?
                true, // generate tooltips?
                false); // generate URLs?

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));

        return chart;

    }

}