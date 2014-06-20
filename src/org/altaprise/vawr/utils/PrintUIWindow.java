package org.altaprise.vawr.utils;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class PrintUIWindow extends JFrame implements Printable {

    JTextArea _textArea = new JTextArea(50, 20);

    public int print(Graphics g, PageFormat pf,
                     int page) throws PrinterException {

        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }

        /*
     * User (0,0) is typically outside the imageable area, so we must translate
     * by the X and Y values in the PageFormat to avoid clipping
     */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now print the window and its visible contents */
        //frameToPrint.printComponents(g);
        //frameToPrint.printAll(g);
        _textArea.print(g);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public PrintUIWindow() {
        jbInit();
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

    public void jbInit() {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        this.setTitle("Print UI Example");
        this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        for (int i = 1; i <= 50; i++) {
            _textArea.append("Line " + i + "\n");
        }
        JScrollPane pane = new JScrollPane(_textArea);
        pane.setPreferredSize(new Dimension(250, 200));
        this.add("Center", pane);
        JButton printButton = new JButton("Print This Window");
        printButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_print_actionPerformed(e);
                }
            });
        this.add("South", printButton);
        this.pack();
        this.setVisible(true);
    }

    
    public static void main(String[] args) {
        PrintUIWindow uiw = new PrintUIWindow();
    }

}
