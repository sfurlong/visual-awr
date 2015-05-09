package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBRec;

import java.awt.BasicStroke;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;
import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class VMStatTimeSeriesChart extends RootChartFrame {

    BorderLayout borderLayout = new BorderLayout();

    public VMStatTimeSeriesChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        //Chart CPU Metrics
        TimeSeriesCollection xyCpuDataset = null;
        xyCpuDataset = createCpuDataset();
        
        JFreeChart cpuChart = createChart(xyCpuDataset, "CPU Utilization", 0, "CPU Utilization", " ");
        ChartPanel cpuChartPanel = (ChartPanel) createChartPanel(cpuChart);
        THE_ROOT_CONTENT_PANEL.add(cpuChartPanel);

        //Chart Memory Metrics
        TimeSeriesCollection xyMemDataset = null;
        xyCpuDataset = createMemDataset();
        
        JFreeChart memChart = createChart(xyCpuDataset, "Memory Utilization", 0, "Memory Utilization", " ");
        ChartPanel memChartPanel = (ChartPanel) createChartPanel(memChart);
        THE_ROOT_CONTENT_PANEL.add(memChartPanel);

        this.setVisible(true);

        //Size of Y should be 200 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + (260* 2)));
        add(THE_SCROLL_PANE, BorderLayout.CENTER);
        
    }


    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;    
    }

    //Just implementing to satisfy the abstract base class
    protected TimeSeriesCollection createDataset(String racInst, String awrMetric) {
        return null;    
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
    private TimeSeriesCollection createCpuDataset() {
        
        String metricToChart = "free";

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("user");
        TimeSeries s2 = new TimeSeries("sys");
        TimeSeries s3 = new TimeSeries("idle");
        TimeSeries s4 = new TimeSeries("wait");
        TimeSeries s5 = new TimeSeries("steal");

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getVmStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String userCpuS = awrRec.getAttribVal("us");
            String sysCpuS = awrRec.getAttribVal("sy");
            String idleCpuS = awrRec.getAttribVal("id");
            String waitCpuS = awrRec.getAttribVal("wa");
            String stealCpuS = awrRec.getAttribVal("st");

            try {
                double userCpuD = Double.parseDouble(userCpuS);
                double sysCpuD = Double.parseDouble(sysCpuS);
                double idleCpuD = Double.parseDouble(idleCpuS);
                double waitCpuD = Double.parseDouble(waitCpuS);
                double stealCpuD = Double.parseDouble(stealCpuS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                }
                s1.add(new Second(snapShotDate), userCpuD);
                s2.add(new Second(snapShotDate), sysCpuD);
                s3.add(new Second(snapShotDate), idleCpuD);
                s4.add(new Second(snapShotDate), waitCpuD);
                s5.add(new Second(snapShotDate), stealCpuD);
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

    private TimeSeriesCollection createMemDataset() {
        
        String metricToChart = "free";

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("swpd");
        TimeSeries s2 = new TimeSeries("free");
        TimeSeries s3 = new TimeSeries("buff");
        TimeSeries s4 = new TimeSeries("cache");

        ArrayList<DBRec> awrRecords = OSWData.getInstance().getVmStatRecs();
        for (int i = 0; i < awrRecords.size(); i++) {
            DBRec awrRec = awrRecords.get(i);
            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date snapShotDate = (Date) awrRec.getAttrib("DATE").getObjValue();
            String swapdMemS = awrRec.getAttribVal("swpd");
            String freeMemS = awrRec.getAttribVal("free");
            String buffMemS = awrRec.getAttribVal("buff");
            String cacheMemS = awrRec.getAttribVal("cache");

            try {
                double swapdMemD = Double.parseDouble(swapdMemS);
                double freeMemD = Double.parseDouble(freeMemS);
                double buffMemD = Double.parseDouble(buffMemS);
                double cacheMemD = Double.parseDouble(cacheMemS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                }
                s1.add(new Second(snapShotDate), swapdMemD);
                s2.add(new Second(snapShotDate), freeMemD);
                s3.add(new Second(snapShotDate), buffMemD);
                s4.add(new Second(snapShotDate), cacheMemD);
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
}
