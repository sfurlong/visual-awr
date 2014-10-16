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

import java.util.Iterator;
import javax.swing.JPanel;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class IOStatTimeSeriesChart extends RootChartFrame {

    private BorderLayout borderLayout = new BorderLayout();
    private int _totalNumRacInstances = 1;

    public IOStatTimeSeriesChart() {

    }

    public IOStatTimeSeriesChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        TimeSeriesCollection flashXyDataset = createDataset("0", "FLASH");
        boolean noFlashData = flashXyDataset.getSeries(0).isEmpty();
        //Check to see if the dataset is empty...meaning that there are no FLASH entries in the OSWData
        if (!noFlashData) {
            this._totalNumRacInstances++;
            JFreeChart flashChart = createChart(flashXyDataset, metricName, 0, "Flash IOPS", "sec");
            ChartPanel flashChartPanel = (ChartPanel) createChartPanel(flashChart);
            THE_ROOT_CONTENT_PANEL.add(flashChartPanel);
        }

        TimeSeriesCollection diskXyDataset = createDataset("0", "DISK");
        JFreeChart diskChart = createChart(diskXyDataset, metricName, 0, "DISK IOPS", "sec");
        ChartPanel diskChartPanel = (ChartPanel) createChartPanel(diskChart);
        THE_ROOT_CONTENT_PANEL.add(diskChartPanel);



        //Size of Y should be 200 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + (260 * _totalNumRacInstances)));
        add(THE_SCROLL_PANE, BorderLayout.CENTER);

        this.setVisible(true);
    }

    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;
    }

    protected TimeSeriesCollection createDataset(String racInst, String awrMetric) {

        //String metricLabel = AWRMetrics.getInstance().getMetricDescription(awrMetric);
        TimeSeriesCollection xyDataset = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("r/s");
        TimeSeries s2 = new TimeSeries("w/s");

        ArrayList<DBRec> ioStatRecs = OSWData.getInstance().getIoStatRecs();
        Iterator iter = ioStatRecs.iterator();
        while (iter.hasNext()) {
            DBRec ioStatRec = (DBRec) iter.next();

            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date dateTimeD = (Date)ioStatRec.getAttrib("DATE").getObjValue();
            String deviceType = ioStatRec.getAttribVal("DEVICE_TYPE");
            String readValS = ioStatRec.getAttribVal("r/s");
            String writeValS = ioStatRec.getAttribVal("w/s");

            try {
                double readValD = Double.parseDouble(readValS);
                double writeValD = Double.parseDouble(writeValS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                }
                if (deviceType.equals(awrMetric)) {
                    s1.add(new Second(dateTimeD), readValD);
                    s2.add(new Second(dateTimeD), writeValD);
                }
            } catch (org.jfree.data.general.SeriesException se) {
                System.out.println("Error plotting SnapShot: " + ioStatRec + ", Inst: " + racInst + " " +
                                   dateTimeD.toString());
                System.out.println(se.getLocalizedMessage());
                if (SessionMetaData.getInstance().debugOn()) {
                    //se.printStackTrace();
                }

            } catch (Exception e) {
                System.out.println("Error plotting SnapShot: " + ioStatRec + ", Inst: " + racInst + " " +
                                   dateTimeD.toString());
                e.printStackTrace();
            }
        }

        xyDataset.addSeries(s1);
        xyDataset.addSeries(s2);

        return xyDataset;
    }

    //assumes format 09/10/2014 07:13:02 AM
    private Date parseDateTime(String dateS, String timeS) {
        Date ret = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
            ret = dateFormat.parse(dateS + " " + timeS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ret;
    }
}
