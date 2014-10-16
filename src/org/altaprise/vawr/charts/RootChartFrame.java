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

abstract class RootChartFrame extends JFrame implements Printable {

    private JMenuBar menubarFrame = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem itemFilePrint = new JMenuItem();
    private JMenuItem itemFileExit = new JMenuItem();

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
        _headerTextPane.setContentType("text/html");
        _headerTextPane.setEditable(false);
        
        int numHeaderLines = countLines(chartHeaderText);
        System.out.println("num header lines: " + numHeaderLines);
        
        _headerTextPane.setPreferredSize(new java.awt.Dimension(800, 30 * numHeaderLines));
        _headerTextPane.setText("<style type=\\'text/css\\'><center>" + chartHeaderText + "</center>");
        THE_HEADER_TEXT_PANEL.add(_headerTextPane);
        THE_ROOT_CONTENT_PANEL.add(_headerTextPane);


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

    private static int countLines(String str){
        int ret = 0;
        if (str != null) {
            String[] lines = str.split("\r\n|\r|\n|/tr");
            ret = lines.length;
        }
       return  ret;
    }
    
    
    public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {

            if (pageIndex < IMAGES_TO_PRINT.length) {
                System.out.println("pageformat/page!!: " + pf.toString() + "/" + pageIndex);

                saveImageAsJPEG(IMAGES_TO_PRINT[pageIndex], "img"+pageIndex);
                
                graphics.drawImage(IMAGES_TO_PRINT[pageIndex], IMAGES_TO_PRINT[pageIndex].getWidth(), IMAGES_TO_PRINT[pageIndex].getHeight(), null);
                return PAGE_EXISTS;
            } else {
                return NO_SUCH_PAGE;
            }
        }

    private void doPrintJob() {

        JOptionPane.showMessageDialog(this,
                                      "Coming Soon", "Message",
                                      JOptionPane.INFORMATION_MESSAGE);

        /*

        try {
            THE_ROOT_CONTENT_PANEL.setSize(600, THE_ROOT_CONTENT_PANEL.getSize().height);
            this.setSize(600, this.getSize().height);
            Component[] comp = THE_ROOT_CONTENT_PANEL.getComponents();
            PDDocument document = new PDDocument();
            for (int i = 0; i < comp.length; i++) {

                System.out.println(comp[i].getClass().getName());
                comp[i].setSize(600, comp[i].getSize().height);
                this.saveComponentAsJPEG(comp[i], "comp" + i);

                Dimension size = comp[i].getSize();
                BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);

                GraphicsConfiguration gc = BufferedImageGraphicsConfig.getConfig(myImage);

                Graphics2D g2 = myImage.createGraphics();
                comp[i].paint(g2);

                //New image needs to be resized to no more than 600 wide
                //BufferedImage newImage = this.resizeImage(myImage, 600, myImage.getHeight());
                //BufferedImage newImage = this.resizeImage(myImage, myImage.getWidth(), myImage.getHeight());
                //BufferedImage newImage = Scalr.resize(myImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                //               600, myImage.getHeight(), Scalr.OP_ANTIALIAS);
                //BufferedImage newImage = gc.createCompatibleImage(myImage.getWidth(), myImage.getHeight());

                float width = myImage.getWidth();
                float height = myImage.getHeight();

                //PDPage page = new PDPage(new PDRectangle(width, height));
                System.out.println("Letter width/hight: " + PDPage.PAGE_SIZE_LETTER.getWidth() + "/" +
                                   PDPage.PAGE_SIZE_LETTER.getHeight());
                System.out.println("Image width/hight: " + width + "/" + height);
                PDPage page =
                    new PDPage(new PDRectangle(PDPage.PAGE_SIZE_LETTER.getWidth(),
                                               PDPage.PAGE_SIZE_LETTER.getHeight()));
                document.addPage(page);
                PDXObjectImage img = new PDJpeg(document, myImage);
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                contentStream.drawImage(img, 0, 400);
                contentStream.close();


                //saveComponentAsJPEG(comp[i], "comp"+i);
            }
            document.save("test.pdf");
            document.close();
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
    */
    }

    BufferedImage[] IMAGES_TO_PRINT = null;
    private void doPrintJob2() {

        try {
            IMAGES_TO_PRINT = new BufferedImage[THE_ROOT_CONTENT_PANEL.getComponents().length];
            THE_ROOT_CONTENT_PANEL.setSize(600, THE_ROOT_CONTENT_PANEL.getSize().height);
            this.setSize(600, this.getSize().height);
            Component[] comp = THE_ROOT_CONTENT_PANEL.getComponents();
            for (int i = 0; i < comp.length; i++) {

                System.out.println(comp[i].getClass().getName());
                comp[i].setSize(600, comp[i].getSize().height);
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

    private BufferedImage resizeImage(BufferedImage inputImage, int scaledWidth, int scaledHeight) throws IOException {

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        // scales the input image to the output image
        //Graphics2D g2d = outputImage.createGraphics();
        Graphics g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return outputImage;
    }

    private void saveImageAsJPEG(BufferedImage img, String filename) {
        try {
            OutputStream out = new FileOutputStream(filename + ".jpg");
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            //encoder.encode(img);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
/*    
    private void saveComponentAsJPEG(Component myComponent, String filename) {
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        //BufferedImage newImage =
        //    Scalr.resize(myImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, 600, myImage.getHeight(),
        //                 Scalr.OP_ANTIALIAS);
        try {
            OutputStream out = new FileOutputStream(filename + ".jpg");
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(newImage);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
*/
}
