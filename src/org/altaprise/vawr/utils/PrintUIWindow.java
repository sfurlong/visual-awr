package org.altaprise.vawr.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.altaprise.vawr.charts.AWRTimeSeriesChart;

public class PrintUIWindow extends JFrame implements Printable {

    JTextArea _textArea = new JTextArea(50, 20);
    JPanel _contentPanel = new JPanel();
    JScrollPane _theScrollPanel = null;
    JPanel[] daPanels = new JPanel[2];

    public PrintUIWindow() {
        AWRTimeSeriesChart chart1 = new AWRTimeSeriesChart("OS_CPU");
        AWRTimeSeriesChart chart2 = new AWRTimeSeriesChart("AAS");
        daPanels[0] = chart1.getChartPanel();
        daPanels[1] = chart2.getChartPanel();
        jbInit(daPanels);
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /*
     * User (0,0) is typically outside the imageable area, so we must translate
     * by the X and Y values in the PageFormat to avoid clipping
     */
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now print the window and its visible contents */
        //frameToPrint.printComponents(g);
        //frameToPrint.printAll(g);
        Component[] comp = _theScrollPanel.getComponents();
        for (int i = 0; i < comp.length; i++) {
            System.out.println(comp[i].getClass().getName());
            if (comp[i].getClass().getName().equals("javax.swing.JViewport")) {
                comp[i].printAll(g);
            }
        }

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }


    private void jButton_print_actionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        //job.setPrintable(this);
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
                /* The job did not successfully complete */
            }
        }
    }

    public void jbInit(JPanel[] newPanel) {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        this.setTitle("Print UI Example");
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        _contentPanel.setLayout(new BoxLayout(_contentPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < newPanel.length; i++) {
            newPanel[i].setPreferredSize(new Dimension(300, 400));

            _contentPanel.add(newPanel[i]);


        }

        _theScrollPanel = new JScrollPane(_contentPanel);
        
        this.setSize(new java.awt.Dimension(400, 400));
        _theScrollPanel.setPreferredSize(new Dimension(300,400));

        this.add("Center", _theScrollPanel);
        JButton printButton = new JButton("Print This Window");
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_print_actionPerformed(e);
            }
        });
        this.add("South", printButton);
        //this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }


    public static void main(String[] args) {
        PrintUIWindow uiw = new PrintUIWindow();
    }

}
