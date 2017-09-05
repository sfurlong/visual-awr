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

public class SizeOnDiskChart extends RootChartFrame {

    BorderLayout borderLayout = new BorderLayout();

    public SizeOnDiskChart(String metricName, String chartHeaderText) {
        super("VisualAWR Charting", chartHeaderText);

        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));

        TimeSeriesCollection xyDataset = createDataset("1", metricName);

        JFreeChart chart = createChart(xyDataset, metricName, 0, "", "");

        ChartPanel chartPanel = (ChartPanel) createChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(600, 600));

        THE_ROOT_CONTENT_PANEL.add(chartPanel);
        //Size of Y should be 200 (for header) + 260 * numCharts)
        THE_ROOT_CONTENT_PANEL.setPreferredSize(new java.awt.Dimension(600, 200 + 260));
        this.add(THE_SCROLL_PANE, BorderLayout.CENTER);
        
        this.setVisible(true);
    }


    public JPanel getChartPanel() {
        return THE_ROOT_CONTENT_PANEL;    
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return The dataset.
     */
    protected TimeSeriesCollection createDataset(String racInst, String awrMetric) {
        return createSizeOnDiskDataset();
    }

    public static TimeSeriesCollection createSizeOnDiskDataset() {

        TimeSeriesCollection xyDataset = new TimeSeriesCollection();

        TimeSeries s1 = new TimeSeries("SizeOnDisk");

        ArrayList<AWRRecord> sizeOnDiskRecs = AWRData.getInstance().getSizeOnDiskRecordArray();

        String snapId = "";
        String sizeOnDiskValS = "0.0";

        for (int i = 0; i < sizeOnDiskRecs.size(); i++) {
            try {
                snapId = sizeOnDiskRecs.get(i).getVal("SNAP_ID");
                sizeOnDiskValS = sizeOnDiskRecs.get(i).getVal("SIZE_GB");
                
                //Get the snapshot datetime.  RAC Instance is hardcoded to "1"
                Date snapShotDate = AWRData.getInstance().getAWRRecordByKey(snapId, "1").getSnapShotDateTime();

                if (sizeOnDiskRecs.size() > 0) {
                    s1.add(new Minute(snapShotDate), Double.parseDouble(sizeOnDiskValS));
                } else {
                    System.out.println("No Size-On-Disk Data in file for SnapId: " + snapId);
                }
            } catch (Exception e) {
                System.out.println("Error at snapid: " + snapId);
                System.out.println(e.getLocalizedMessage() + " ClassName: SizeOnDiskChart");
                if (SessionMetaData.getInstance().debugOn()) {
                    e.printStackTrace();
                }
            }
        }

        xyDataset.addSeries(s1);

        return xyDataset;
    }
}
