package org.altaprise.vawr.charts;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.awt.geom.Rectangle2D;

import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

abstract class RootChartFrame extends JFrame {

    private JMenuBar menubarFrame = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem itemFilePrint = new JMenuItem();
    private JMenuItem itemFileExit = new JMenuItem();

    public RootChartFrame() {
        super();
    }

    public RootChartFrame(String metricName) {
        super("Visual AWR Charting");
        
        menuFile.setText("File");
        itemFilePrint.setText("Print");
        itemFilePrint.setMnemonic('P');
        itemFilePrint.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Nothing for now
                }
            });
        itemFileExit.setText("Exit");
        itemFileExit.setMnemonic('X');
        itemFileExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

        setJMenuBar(menubarFrame);
        menuFile.add(itemFilePrint);
        menuFile.addSeparator();
        menuFile.add(itemFileExit);
        menubarFrame.add(menuFile);
        
    }

    public abstract JPanel getChartPanel();
    
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
    protected static JPanel createChartPanel(JFreeChart chart) {


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
        panel.setMouseWheelEnabled(false);
        return panel;
    }

    protected void windowClosing(final WindowEvent event) {
        if (event.getWindow() == this) {
            dispose();
        }
    }


    protected abstract TimeSeriesCollection createDataset(String racInst, String awrMetric);


    /**
     * Creates a chart.
     *
     * @param dataset  a dataset.
     *
     * @return A chart.
     */
    protected static JFreeChart createChart(XYDataset dataset, String metricName, 
                                        int racInstNum, String chartTitleOverride, String rangeOverride) {

        String chartTitle = "";
        if (chartTitleOverride != null && chartTitleOverride.length() > 0) {
            chartTitle = chartTitleOverride;
        } else {
            chartTitle = AWRMetrics.getInstance().getMetricChartTitle(metricName);
        }
        if (racInstNum > 0) {
            chartTitle += " Instance-" + racInstNum;
        }
        String metricRangeName = "";
        if (rangeOverride != null && rangeOverride.length() > 0) {
            metricRangeName = rangeOverride;
        } else {
            metricRangeName = AWRMetrics.getInstance().getMetricRangeDescription(metricName);
        }

        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle,
                                                              // title
                                                              "Date", // x-axis label
                                                              metricRangeName, // y-axis label
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

        XYItemRenderer r2 = new XYLineAndShapeRenderer(true, false) {

            public LegendItem getLegendItem(int datasetIndex, int series) {
                if (getPlot() == null) {
                    return null;
                }
                XYDataset dataset = getPlot().getDataset(datasetIndex);
                if (dataset == null) {
                    return null;
                }
                String label = dataset.getSeriesKey(series).toString();
                LegendItem legendItem = new LegendItem(label, lookupSeriesPaint(series));
                legendItem.setLine(new Rectangle2D.Double(0.0, 0.0, 5.0,
                                                          5.0)); //setLine takes a Shape, not just Lines so you can pass any Shape to it...
                return legendItem;
            }
        };
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r2;
        renderer.setBaseShapesVisible(true);
        renderer.setBaseShapesFilled(true);
        renderer.setDrawSeriesLineAsPath(true);
        plot.setRenderer(r2);
        
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));

        return chart;

    }
}
