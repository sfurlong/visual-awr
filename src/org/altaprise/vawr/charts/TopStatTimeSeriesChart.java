package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBRec;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.utils.SessionMetaData;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TopStatTimeSeriesChart extends RootChartFrame {

    BorderLayout borderLayout = new BorderLayout();

    public TopStatTimeSeriesChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        //Chart CPU Metrics
        TimeSeriesCollection xyCpuDataset = null;
        if (OSWData.getInstance().getPlatformType().equals("SUNOS")) {
            xyCpuDataset = createSunCpuDataset();
        } else {
            xyCpuDataset = createLinuxCpuDataset();
        }
        JFreeChart cpuChart = createChart(xyCpuDataset, "CPU Utilization", 0, "CPU Utilization", " ");
        ChartPanel cpuChartPanel = (ChartPanel) createChartPanel(cpuChart);
        THE_ROOT_CONTENT_PANEL.add(cpuChartPanel);

        //Chart Memory Metrics
        TimeSeriesCollection xyMemDataset = null;
        if (OSWData.getInstance().getPlatformType().equals("SUNOS")) {
            xyMemDataset = createSunMemDataset();
        } else {
            xyMemDataset = createLinuxMemDataset();
        }
        JFreeChart memChart = createChart(xyMemDataset, "Memory Utilization", 0, "Memory Utilization", " ");
        ChartPanel memChartPanel = (ChartPanel) createChartPanel(memChart);
        //memChartPanel.setPreferredSize(new java.awt.Dimension(600, 260));


        this.setVisible(true);
        THE_ROOT_CONTENT_PANEL.add(memChartPanel);
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

        TimeSeries s1 = new TimeSeries("user");
        TimeSeries s2 = new TimeSeries("system");
        TimeSeries s3 = new TimeSeries("nice");
        TimeSeries s4 = new TimeSeries("idle");
        TimeSeries s5 = new TimeSeries("wait-io");
        TimeSeries s6 = new TimeSeries("HW IRQ");
        TimeSeries s7 = new TimeSeries("SW IRQ");
        TimeSeries s8 = new TimeSeries("steal");

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
}
