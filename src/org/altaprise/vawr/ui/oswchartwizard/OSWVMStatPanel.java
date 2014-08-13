package org.altaprise.vawr.ui.oswchartwizard;

import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.file.ReadVMStatFile;
import org.altaprise.vawr.charts.AWRMemoryTimeSeriesChart;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;


public class OSWVMStatPanel extends WizardContentBasePanel {
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private ArrayList<String> _awrStringRecs = null;
    private JLabel jLabel_selectMetrics =
        new JLabel("Select AWR Metric to Chart");
    private JTextField jTextField_fileName = new JTextField();
    private JButton jButton_selectFile = new JButton();

    public OSWVMStatPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        //this.setSize(new Dimension(660, 520));

        this.setSize(new Dimension(548, 341));
        jButton_chartMetric.setBounds(new Rectangle(410, 130, 100, 20));
        jButton_chartMetric.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_chartMetric_actionPerformed(e);
            }
        });
        jLabel_selectMetrics.setBounds(new Rectangle(70, 45, 150, 15));

        jTextField_fileName.setBounds(new Rectangle(45, 60, 355, 25));
        jButton_selectFile.setText("jButton1");
        jButton_selectFile.setBounds(new Rectangle(410, 65, 75, 21));
        jButton_selectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        this.add(jButton_selectFile, null);
        this.add(jTextField_fileName, null);
        this.add(jLabel_selectMetrics, null);
        this.add(jButton_chartMetric, null);

        
        //Set the Wizard Label
        this.setPanelLabel("5. Select a metric and chart it.");
    }


    private void jButton1_actionPerformed(ActionEvent e) {

        String appHome = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(appHome);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //    "JPG & GIF Images", "jpg", "gif");
        //chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String fileName = chooser.getSelectedFile().getName();
            String filePath = chooser.getSelectedFile().getPath();
            System.out.println("You chose to open this file: " + filePath);
            this.jTextField_fileName.setText(filePath);
        }

    }

    private void jButton_chartMetric_actionPerformed(ActionEvent e) {
        String selectedFile = this.jTextField_fileName.getText();

        //SetCursor
        RootFrame.startWaitCursor();

        ReadVMStatFile readFile = new ReadVMStatFile();
        
        try {
            
            readFile.parse(selectedFile);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        RootFrame.startWaitCursor();
    }
}

