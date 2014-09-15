package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBAttributes;
import dai.shared.businessObjs.DBRec;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.Color;


import java.awt.event.WindowEvent;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

public class TopStatTimeSeriesChart extends JFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();

    public TopStatTimeSeriesChart(String metricName) {
        super("Visual AWR Charting");

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));

        //Chart CPU Metrics
        TimeSeriesCollection xyCpuDataset = null;
        if (OSWData.getInstance().getPlatformType().equals("SUNOS")) {
            xyCpuDataset = createSunCpuDataset();
        } else {
            xyCpuDataset = createLinuxCpuDataset();
        }
        JFreeChart cpuChart = createChart(xyCpuDataset, "CPU Utilization");
        ChartPanel cpuChartPanel = (ChartPanel) createChartPanel(cpuChart);
        cpuChartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        _outerP.add(cpuChartPanel);

        //Chart Memory Metrics
        TimeSeriesCollection xyMemDataset = null;
        if (OSWData.getInstance().getPlatformType().equals("SUNOS")) {
            xyMemDataset = createSunMemDataset();
        } else {
            xyMemDataset = createLinuxMemDataset();
        }
        JFreeChart memChart = createChart(xyMemDataset, "Memory Utilization");
        ChartPanel memChartPanel = (ChartPanel) createChartPanel(memChart);
        memChartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        _outerP.add(memChartPanel);


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

    private TimeSeriesCollection createSunCpuDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("IDLE");
        TimeSeries s2 = new TimeSeries("USER");
        TimeSeries s3 = new TimeSeries("KERNEL");
        TimeSeries s4 = new TimeSeries("IOWAIT");
        TimeSeries s5 = new TimeSeries("SWAP");

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getTopStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String idleCpuS = awrRec.getAttribVal("idle");
            String userCpuS = awrRec.getAttribVal("user");
            String kernelCpuS = awrRec.getAttribVal("kernel");
            String iowaitCpuS = awrRec.getAttribVal("iowait");
            String swapCpuS = awrRec.getAttribVal("swap");

            try {
                double idleCpuD = Double.parseDouble(idleCpuS);
                double userCpuD = Double.parseDouble(userCpuS);
                double kernelCpuD = Double.parseDouble(kernelCpuS);
                double iowaitCpuD = Double.parseDouble(iowaitCpuS);
                double swapCpuD = Double.parseDouble(swapCpuS);

                if (SessionMetaData.getInstance().debugOn()) {
                    System.out.println("Date/idle/user/kernel/iowait/swap: " + snapShotDate.toString() + "/" +
                                       idleCpuD + "/" + userCpuD);
                }
                s1.add(new Second(snapShotDate), idleCpuD);
                s2.add(new Second(snapShotDate), userCpuD);
                s3.add(new Second(snapShotDate), kernelCpuD);
                s4.add(new Second(snapShotDate), iowaitCpuD);
                s5.add(new Second(snapShotDate), swapCpuD);
            } catch (Exception e) {
                //System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
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

        return xyDataset;
    }

    private TimeSeriesCollection createLinuxCpuDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("us");
        TimeSeries s2 = new TimeSeries("sy");
        TimeSeries s3 = new TimeSeries("ni");
        TimeSeries s4 = new TimeSeries("id");
        TimeSeries s5 = new TimeSeries("wa");
        TimeSeries s6 = new TimeSeries("hi");
        TimeSeries s7 = new TimeSeries("si");
        TimeSeries s8 = new TimeSeries("st");

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getTopStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String usCpuS = awrRec.getAttribVal("us");
            String syCpuS = awrRec.getAttribVal("sy");
            String niCpuS = awrRec.getAttribVal("ni");
            String idCpuS = awrRec.getAttribVal("id");
            String waCpuS = awrRec.getAttribVal("wa");
            String hiCpuS = awrRec.getAttribVal("hi");
            String siCpuS = awrRec.getAttribVal("si");
            String stCpuS = awrRec.getAttribVal("st");

            try {
                double usCpuD = Double.parseDouble(usCpuS);
                double syCpuD = Double.parseDouble(syCpuS);
                double niCpuD = Double.parseDouble(niCpuS);
                double idCpuD = Double.parseDouble(idCpuS);
                double waCpuD = Double.parseDouble(waCpuS);
                double hiCpuD = Double.parseDouble(hiCpuS);
                double siCpuD = Double.parseDouble(siCpuS);
                double stCpuD = Double.parseDouble(stCpuS);

                if (SessionMetaData.getInstance().debugOn()) {
                    System.out.println("snapshot date: " + snapShotDate.toString());
                }
                s1.add(new Second(snapShotDate), usCpuD);
                s2.add(new Second(snapShotDate), syCpuD);
                s3.add(new Second(snapShotDate), niCpuD);
                s4.add(new Second(snapShotDate), idCpuD);
                s5.add(new Second(snapShotDate), waCpuD);
                s6.add(new Second(snapShotDate), hiCpuD);
                s7.add(new Second(snapShotDate), siCpuD);
                s8.add(new Second(snapShotDate), stCpuD);
            } catch (Exception e) {
                //System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                System.out.println(e.getCause().getLocalizedMessage());
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

        return xyDataset;
    }

    private TimeSeriesCollection createSunMemDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("PhysMem");
        TimeSeries s2 = new TimeSeries("FreeMem");
        TimeSeries s3 = new TimeSeries("TotalSwap");
        TimeSeries s4 = new TimeSeries("FreeSwap");

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getTopStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String physMemS = awrRec.getAttribVal("PhysMem");
            String freeMemS = awrRec.getAttribVal("FreeMem");
            String totalSwapS = awrRec.getAttribVal("TotalSwap");
            String freeSwapS = awrRec.getAttribVal("FreeSwap");

            try {
                double physMemD = Double.parseDouble(physMemS);
                double freeMemD = Double.parseDouble(freeMemS);
                double totalSwapD = Double.parseDouble(totalSwapS);
                double freeSwapD = Double.parseDouble(freeSwapS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //System.out.println("Date/idle/user/kernel/iowait/swap: " + snapShotDate.toString() + "/" + idleCpuD + "/" + userCpuD);
                }
                s1.add(new Second(snapShotDate), physMemD);
                s2.add(new Second(snapShotDate), freeMemD);
                s3.add(new Second(snapShotDate), totalSwapD);
                s4.add(new Second(snapShotDate), freeSwapD);
            } catch (Exception e) {
                //System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
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

        return xyDataset;
    }

    private TimeSeriesCollection createLinuxMemDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("TotalMem");
        TimeSeries s2 = new TimeSeries("UsedMem");
        TimeSeries s3 = new TimeSeries("Free");
        TimeSeries s4 = new TimeSeries("Buffers");

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getTopStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String totMemS = awrRec.getAttribVal("total");
            String usedMemS = awrRec.getAttribVal("used");
            String freeSwapS = awrRec.getAttribVal("free");
            String buffSwapS = awrRec.getAttribVal("buffers");

            try {
                double totMemD = Double.parseDouble(totMemS);
                double usedMemD = Double.parseDouble(usedMemS);
                double freeSwapD = Double.parseDouble(freeSwapS);
                double buffSwapD = Double.parseDouble(buffSwapS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //System.out.println("Date/idle/user/kernel/iowait/swap: " + snapShotDate.toString() + "/" + idleCpuD + "/" + userCpuD);
                }
                s1.add(new Second(snapShotDate), totMemD);
                s2.add(new Second(snapShotDate), usedMemD);
                s3.add(new Second(snapShotDate), freeSwapD);
                s4.add(new Second(snapShotDate), buffSwapD);
            } catch (Exception e) {
                //System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
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

        String chartTitle = metricName; //AWRMetrics.getInstance().getMetricChartTitle(metricName);
        String chartYAxisLabel = ""; //AWRMetrics.getInstance().getMetricRangeDescription(metricName);
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
}
