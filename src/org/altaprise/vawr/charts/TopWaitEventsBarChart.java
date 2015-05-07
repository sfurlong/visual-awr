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

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.TopWaitEventsRecord;
import org.altaprise.vawr.awrdata.file.ReadAWRMinerFile;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a bar chart.
 *
 */
public class TopWaitEventsBarChart extends JFrame {

    JPanel _outerP = new JPanel();
    JScrollPane _thePanel = new JScrollPane(_outerP);
    BorderLayout borderLayout = new BorderLayout();

    public TopWaitEventsBarChart(String metricName) {

        super("VisualAWR Charting");
        
        this.setLayout(borderLayout);
        this.setSize(new java.awt.Dimension(800, 800));
        _outerP.setLayout(new BoxLayout(_outerP, BoxLayout.Y_AXIS));
        
        //Create the chart
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, metricName);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        _outerP.add(chartPanel);
        this.add(_thePanel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    /**
     * Returns a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset() {

        ArrayList<TopWaitEventsRecord> waitEventsRecords = AWRData.getInstance().getTopWaitEventsRecordArray();
        //Sort the array by PCT of total db time.
        Collections.sort(waitEventsRecords, new Comparator<TopWaitEventsRecord>() {
            @Override
            public int compare(TopWaitEventsRecord r1, TopWaitEventsRecord r2) {
                int ret = Double.compare(r2.getCummDBWaitTimePct(),r1.getCummDBWaitTimePct());
                return ret;
            }
        });

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < waitEventsRecords.size() && i < 10; i++) {
            TopWaitEventsRecord waitEventRecord = waitEventsRecords.get(i);
            double pct = waitEventRecord.getCummDBWaitTimePct();
            String waitEvent = waitEventRecord.getEventName();
            String waitClass = waitEventRecord.getWaitClass();
            dataset.addValue(pct, waitEvent, "");
        }
        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset, String metricName) {

        String chartTitle = AWRMetrics.getInstance().getMetricChartTitle(metricName);
        String chartYAxisLabel = AWRMetrics.getInstance().getMetricRangeDescription(metricName);

        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(chartTitle, // chart title
                                                             "Timed Event", // domain axis label
                                                             "PCT of Total DB Time", // range axis label
                                                             dataset, // data
                                                             PlotOrientation.VERTICAL, // orientation
                                                             true, // include legend
                                                             true, // tooltips?
                                                             false // URLs?
                                                             );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        // set the background color for the chart...
        /*

        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue,
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green,
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red,
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
 */

        return chart;

    }

    public void windowClosing(final WindowEvent event) {
        if (event.getWindow() == this) {
            dispose();
        }
    }

    public static void main(final String[] args) {

        ReadAWRMinerFile reader = new ReadAWRMinerFile();
        try {
            reader.parse("C:\\Git\\visual-awr\\testing\\awr-hist-2759590387-WEBPRD-6671-7090.out");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final TopWaitEventsBarChart demo = new TopWaitEventsBarChart("Bar Chart Demo");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}
