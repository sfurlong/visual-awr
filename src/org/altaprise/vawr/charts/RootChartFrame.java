package org.altaprise.vawr.charts;

import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;

import javax.swing.border.Border;

import org.altaprise.vawr.awrdata.AWRMetrics;


import org.altaprise.vawr.ui.RootFrame;

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

import sun.awt.image.BufferedImageGraphicsConfig;

abstract public class RootChartFrame extends JFrame implements Printable {

    private JMenuBar menubarFrame = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem itemFilePrint = new JMenuItem();
    private JMenuItem itemFileExit = new JMenuItem();
    private BufferedImage[] IMAGES_TO_PRINT = null;

    protected JPanel THE_ROOT_CONTENT_PANEL = new JPanel();
    protected JScrollPane THE_SCROLL_PANE = new JScrollPane(THE_ROOT_CONTENT_PANEL);
    protected JPanel THE_HEADER_TEXT_PANEL = new JPanel();
    protected JTextPane _headerTextPane = new JTextPane();

    public RootChartFrame() {
        super();
    }

    public RootChartFrame(String metricName, String chartHeaderText) {
        super("Visual AWR Charting");

        //Setup the Header Text Panel
        THE_ROOT_CONTENT_PANEL.setLayout(new BoxLayout(THE_ROOT_CONTENT_PANEL, BoxLayout.Y_AXIS));

        this.setChartHeaderText(chartHeaderText);

        menuFile.setText("File");
        itemFilePrint.setText("Print");
        itemFilePrint.setMnemonic('P');
        itemFilePrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doPrintJob();
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


    protected void setChartHeaderText(String chartHeaderText) {
        _headerTextPane.setContentType("text/html");
        _headerTextPane.setEditable(false);

        int numHeaderLines = countLines(chartHeaderText);
        System.out.println("num header lines: " + numHeaderLines);

        _headerTextPane.setPreferredSize(new java.awt.Dimension(800, 30 * numHeaderLines));
        _headerTextPane.setText("<style type=\\'text/css\\'><center>" + chartHeaderText + "</center>");
        THE_HEADER_TEXT_PANEL.add(_headerTextPane);
        THE_ROOT_CONTENT_PANEL.add(_headerTextPane);
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
    protected static JFreeChart createChart(XYDataset dataset, String metricName, int racInstNum,
                                            String chartTitleOverride, String rangeOverride) {

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
    
    protected static int countLines(String str) {
        int ret = 0;
        if (str != null) {
            String[] lines = str.split("\r\n|\r|\n|/tr");
            ret = lines.length;
        }
        return ret;
    }

    @Override
    public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex < IMAGES_TO_PRINT.length) {
            int pWidth = 0;
            int pHeight = 0;
            System.out.println("portrait/pageindex/pf.ImageableWidth/pf.getImageableX/pf.getImageableY/IMage.getWidth/IMage.getHeight: " +
                               pageIndex + "/" + pf.getImageableWidth() + "/" + pf.getImageableX() + "/" +
                               pf.getImageableY() + "/" + IMAGES_TO_PRINT[pageIndex].getWidth() + "/" +
                               IMAGES_TO_PRINT[pageIndex].getHeight());
            pWidth = (int) Math.min(pf.getImageableWidth(), (double) IMAGES_TO_PRINT[pageIndex].getWidth());
            pHeight = pWidth * IMAGES_TO_PRINT[pageIndex].getHeight() / IMAGES_TO_PRINT[pageIndex].getWidth();
            graphics.drawImage(IMAGES_TO_PRINT[pageIndex], (int) pf.getImageableX(), (int) pf.getImageableY(), pWidth,
                               pHeight, null);
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }

    private void doPrintJob() {

        try {
            IMAGES_TO_PRINT = new BufferedImage[THE_ROOT_CONTENT_PANEL.getComponents().length];
            Component[] comp = THE_ROOT_CONTENT_PANEL.getComponents();
            for (int i = 0; i < comp.length; i++) {

                System.out.println(comp[i].getClass().getName());
                Dimension size = comp[i].getSize();
                IMAGES_TO_PRINT[i] = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

                Graphics2D g2 = IMAGES_TO_PRINT[i].createGraphics();
                comp[i].paint(g2);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

}
