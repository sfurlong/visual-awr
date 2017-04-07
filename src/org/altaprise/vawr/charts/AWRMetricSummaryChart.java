package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBRec;

import dai.shared.businessObjs.DBRecSet;

import java.awt.BorderLayout;
import java.awt.Color;

import java.util.ArrayList;
import java.util.Calendar;
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

public class AWRMetricSummaryChart extends RootChartFrame {

    private BorderLayout borderLayout = new BorderLayout();
    private int _totalNumCharts = 0;

    public AWRMetricSummaryChart() {

    }

    public AWRMetricSummaryChart(String metricName) {
        super("Visual AWR Charting", "");
        
        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        setChartHeaderText(getChartHeaderHTML());


        //Add Average Max CPU Chart        
        TimeSeriesCollection cpuDataset = AWRTimeSeriesChart.createMetricMaxDataset("OS_CPU");
        JFreeChart cpuChart = createChart(cpuDataset, metricName, 0, "Average CPU - Max For All Nodes", "");
        XYPlot cpuPlot = (XYPlot) cpuChart.getPlot();
        cpuPlot.getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        ChartPanel cpuChartPanel = (ChartPanel) createChartPanel(cpuChart);
        THE_ROOT_CONTENT_PANEL.add(cpuChartPanel);
        this._totalNumCharts++;

        //Add tot Read IOPS Chart        
        TimeSeriesCollection xyDataset = AWRTimeSeriesChart.createMetricSumDataset("READ_IOPS");
        JFreeChart chart = createChart(xyDataset, metricName, 0, "Total Read IOPS - Sum All Nodes", "");
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);
        THE_ROOT_CONTENT_PANEL.add(chartPanel);
        this._totalNumCharts++;

        //Add tot Write IOPS Chart        
        TimeSeriesCollection wIOPSDataset = AWRTimeSeriesChart.createMetricSumDataset("WRITE_IOPS");
        JFreeChart wIOPSChart = createChart(wIOPSDataset, metricName, 0, "Total Write IOPS - Sum All Nodes", "");
        XYPlot wIOPSPlot = (XYPlot) wIOPSChart.getPlot();
        wIOPSPlot.getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        ChartPanel wIOPSChartPanel = (ChartPanel) createChartPanel(wIOPSChart);
        THE_ROOT_CONTENT_PANEL.add(wIOPSChartPanel);
        this._totalNumCharts++;
        
        //Add tot Memory Chart        
        TimeSeriesCollection memDataset = AWRMemoryTimeSeriesChart.createTotMemDataset();
        JFreeChart memChart = createChart(memDataset, metricName, 0, "Total DB Memory Consumption - Sum All Nodes", "");
        ChartPanel memChartPanel = (ChartPanel) createChartPanel(memChart);
        THE_ROOT_CONTENT_PANEL.add(memChartPanel);
        this._totalNumCharts++;

        //Add Size On Disk        
        TimeSeriesCollection sizeOnDiskDataset = SizeOnDiskChart.createSizeOnDiskDataset();
        JFreeChart sizeOnDiskChart = createChart(sizeOnDiskDataset, metricName, 0, "Database Size On Disk (GB)", "");
        ChartPanel sizeOnDiskChartPanel = (ChartPanel) createChartPanel(sizeOnDiskChart);
        THE_ROOT_CONTENT_PANEL.add(sizeOnDiskChartPanel);
        this._totalNumCharts++;

        //Size of Y should be 250 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + (260*(_totalNumCharts+1))));
        this.add(THE_SCROLL_PANE, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;    
    }
    


    protected TimeSeriesCollection createDataset(String racInstNum, String awrMetric) {

        return null;
    }

    protected void setChartHeaderText(String chartHeaderText) {
        int SIZE_OF_HEADER_TEXT = 300;
        String headerText = "";
        _headerTextPane.setContentType("text/html");
        _headerTextPane.setEditable(false);

        int numHeaderLines = countLines(chartHeaderText);
        System.out.println("num header lines: " + numHeaderLines);

        _headerTextPane.setPreferredSize(new java.awt.Dimension(800, (30 * (numHeaderLines))+SIZE_OF_HEADER_TEXT));
        
        headerText += "<style type=\'text/css\'>";
        headerText += "<body style=\"font: 10px Helvetica, sans-serif;\">";
        headerText += "<center>" + chartHeaderText + "</center>";
        headerText += "</body>";
        _headerTextPane.setText(headerText);
        THE_HEADER_TEXT_PANEL.add(_headerTextPane);
        THE_ROOT_CONTENT_PANEL.add(_headerTextPane);
    }
    
    public String getChartHeaderHTML() {
        String ret = "";
        AWRData awrData = AWRData.getInstance();

        ret += "<center><h1> AWR Summary Report</h1></center>";
        ret += "<table border=\"1\">";
        ret += "<tr>";
        ret += "<td>";
        ret += "<b>Chart Date</b>";
        ret += "</td>";
        ret += "<td>";
        ret += Calendar.getInstance().getTime().toString();
        ret += "</td>";
        ret += "</tr>";
        ret += "<tr><td><b>DB Name</b></td><td>" + awrData.getPlatformInfoAttribute("DB_NAME") + "</td></tr>";
        ret += "<tr><td><b>Nodes (Num RAC instances)</b></td><td>" + awrData.getPlatformInfoAttribute("INSTANCES") + "</td></tr>";
        ret +=
            "<tr><td><b>Sockets (CPU sockets per host)</b></td><td>" + awrData.getPlatformInfoAttribute("NUM_CPU_SOCKETS") + "</td></tr>";
        ret += "<tr><td><b>Cores (Cores per host)</b></td><td>" + awrData.getPlatformInfoAttribute("NUM_CPU_CORES") + "</td></tr>";
        ret += "<tr><td><b>Threads (tot threads per host)</b></td><td>" + awrData.getPlatformInfoAttribute("NUM_CPUS") + "</td></tr>";
        ret +=
            "<tr><td><b>Physical Memory/host(GB)</b></td><td>" + awrData.getPlatformInfoAttribute("PHYSICAL_MEMORY_GB") +
            "</td></tr>";

        ret += "<tr><td><b>CPU (The max of the avg CPU% accross all hosts)</b></td><td>" + this.getCPU() + "</td></tr>";
        ret += "<tr><td><b>R_IOPS (The max of the avg read iops accross all hosts)</b></td><td>" + _maxRIOPS + "</td></tr>";
        ret += "<tr><td><b>W_IOPS (The max of the avg write iops accross all hosts)</b></td><td>" + _maxWIOPS + "</td></tr>";
        ret += "<tr><td><b>SGA (tot sga size in GB accross all hosts)</b></td><td>" + _maxSGA + "</td></tr>";
        ret += "<tr><td><b>PGA (tot pga size in GB accross all hosts)</b></td><td>" + _maxPGA + "</td></tr>";
        ret += "<tr><td><b>Mem Used (tot physical mem used accross all hosts)</b></td><td>" + (_maxSGA + _maxPGA) + "</td></tr>";
        ret += "<tr><td><b>Size GB (tot DB size on disk)</b></td><td>" + getMaxSizeOnDisk() + "</td></tr>";
        ret += "<tr><td><b>Hosts</b></td><td>" + awrData.getPlatformInfoAttribute("HOSTS") + "</td></tr>";


        ret += "</table></body>";

        return ret;
    }

    double _maxRIOPS = 0;
    double _maxWIOPS = 0;
    double _maxSGA = 0;
    double _maxPGA = 0;
    private double getCPU() {
        int totalNumRacInstances = AWRData.getInstance().getNumRACInstances();
        double maxCPU = 0;

        for (int i=1; i <= totalNumRacInstances; i++) {

            LinkedHashSet<String> snapshotIds = AWRData.getInstance().getUniqueSnapshotIds();
            Iterator iter = snapshotIds.iterator();
            while (iter.hasNext()) {
                String snapshotId = (String) iter.next();
                String metricValS = "0.0";

                try {
                    AWRRecord awrRec = AWRData.getInstance().getAWRRecordByKey(snapshotId, Integer.toString(i));

                    if (awrRec != null) {
                        metricValS = awrRec.getVal("OS_CPU");
                        double metricValD = Double.parseDouble(metricValS);
                        if (metricValD > maxCPU) {
                            maxCPU = metricValD;
                        }
                        double rIOPS = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "READ_IOPS");
                        if (rIOPS > _maxRIOPS) _maxRIOPS = rIOPS;
                        double wIOPS = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "WRITE_IOPS");
                        if (wIOPS > _maxWIOPS) _maxWIOPS = wIOPS;
                        double sga = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "SGA");
                        if (sga > _maxSGA) _maxSGA = sga;
                        double pga = AWRData.getInstance().getMetricSumForSnapshotId(snapshotId, "PGA");
                        if (pga > _maxPGA) _maxPGA = pga;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return maxCPU;
    }

    private double getMaxSizeOnDisk() {
        ArrayList<AWRRecord> awrRecs = AWRData.getInstance().getSizeOnDiskRecordArray();
        double maxSizeOnDisk = 0;

        for (int i=0; i < awrRecs.size(); i++) {

            AWRRecord awrRec = awrRecs.get(i);
            
            String sizeOnDiskS = awrRec.getVal("SIZE_GB");
            double sizeOnDiskD = Double.parseDouble(sizeOnDiskS);
            
            if (sizeOnDiskD > maxSizeOnDisk) {
                maxSizeOnDisk = sizeOnDiskD;
            }
        }
        return maxSizeOnDisk;
    }
}
