/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.altaprise.vawr.charts;

import dai.shared.businessObjs.DBRec;
import dai.shared.businessObjs.DBRecSet;

import java.awt.BorderLayout;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.JPanel;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;

import org.altaprise.vawr.awrdata.AWRRecord;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.OSWMetrics;
import org.altaprise.vawr.utils.SessionMetaData;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Minute;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class CellSrvStatChart extends RootChartFrame {

    private BorderLayout borderLayout = new BorderLayout();
    private int _totalNumRacInstances = 4;

    public CellSrvStatChart() {

    }

    public CellSrvStatChart(String metricName, String chartHeaderText) {
        super("Visual AWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));
        
        ArrayList<DBRec> dbRecs = OSWData.getInstance().getCellSrvStatRecs();
        DBRec dbRec = dbRecs.get(0);
        _totalNumRacInstances = dbRec.size() - 1;
        
        for (int i=1; i<this._totalNumRacInstances; i++) {
            metricName = dbRec.getAttrib(i).getName();
            TimeSeriesCollection diskXyDataset = createDataset("0", metricName);
            JFreeChart diskChart = createChart(diskXyDataset, metricName, 0, metricName, " ");
            ChartPanel diskChartPanel = (ChartPanel) createChartPanel(diskChart);
            THE_ROOT_CONTENT_PANEL.add(diskChartPanel);
        }

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
        TimeSeries s1 = new TimeSeries(awrMetric);

        ArrayList<DBRec> dbRecs = OSWData.getInstance().getCellSrvStatRecs();
        Iterator iter = dbRecs.iterator();
        while (iter.hasNext()) {
            DBRec ioStatRec = (DBRec) iter.next();

            //avg-cpu:  %user   %nice %system %iowait  %steal   %idle
            Date dateTimeD = (Date)ioStatRec.getAttrib("DATE").getObjValue();
            String metricValS = ioStatRec.getAttribVal(awrMetric);
            if (metricValS == null || metricValS.trim().length()<= 0) {
                metricValS = "0";
            }

            try {
                double metricValD = Double.parseDouble(metricValS);

                if (SessionMetaData.getInstance().debugOn()) {
                    //System.out.println("snapid/inst/sgaValS: " + awrRec.getSnapId() + "/" + awrRec.getInst() + "/" + sgaValS);
                }

                s1.add(new Second(dateTimeD), metricValD);

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
