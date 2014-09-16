package org.altaprise.vawr.charts;

import java.awt.BorderLayout;
import java.util.ArrayList;


import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.AWRData;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class AWRMemoryTimeSeriesChart extends RootChartFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();

    public AWRMemoryTimeSeriesChart(String metricName) {
        super("Visual AWR Charting");

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));


        for (int i = 0; i < AWRData.getInstance().getNumRACInstances(); i++) {

            int racInstNum = i + 1;
            
            String racInstNumS = Integer.toString(racInstNum);

            TimeSeriesCollection xyDataset = createDataset(racInstNumS, metricName);

            JFreeChart chart = createChart(xyDataset, metricName, racInstNum, "", "");

            ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

            chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));

            _outerP.add(chartPanel);
        }

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
        int racInstP = Integer.parseInt(racInst);
        TimeSeries s1 = new TimeSeries("SGA");
        TimeSeries s2 = new TimeSeries("PGA");
        TimeSeries s3 = new TimeSeries("SGA+PGA");

        ArrayList<AWRRecord> awrRecords = AWRData.getInstance().getAWRRecordArray();
        for (int i = 0; i < awrRecords.size(); i++) {
            AWRRecord awrRec = awrRecords.get(i);
            String sgaValS = awrRec.getVal("SGA");
            String pgaValS = awrRec.getVal("PGA");
            String sga_pga_totValS = awrRec.getVal("SGA_PGA_TOT");
            String inst = awrRec.getVal("INST");
            int instI = Integer.parseInt(inst);
            Date snapShotDate = null;
            try {
                double sgaValD = Double.parseDouble(sgaValS);
                double pgaValD = Double.parseDouble(pgaValS);
                double sga_pga_totValD = Double.parseDouble(sga_pga_totValS);

                snapShotDate = awrRec.getSnapShotDateTime();
                if (instI == racInstP) {
                    if (SessionMetaData.getInstance().debugOn()) {
                        System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                    }
                    s1.add(new Minute(snapShotDate), sgaValD);
                    s2.add(new Minute(snapShotDate), pgaValD);
                    s3.add(new Minute(snapShotDate), sga_pga_totValD);
                }
            } catch (Exception e) {
                System.out.println("Error at snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
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
}
