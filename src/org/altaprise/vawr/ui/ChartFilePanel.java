package org.altaprise.vawr.ui;

import java.awt.*;
import java.awt.event.*;

import java.awt.event.ActionEvent;


import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.file.ReadAWRMinerFile;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.utils.SessionMetaData;

public class ChartFilePanel extends JPanel {
    private JTextField jTextField_fileName = new JTextField();
    private JButton jButton_selectFile = new JButton();
    private JButton jButton_chart = new JButton();
    private JComboBox jComboBox_metricName = new JComboBox();

    private static String AWR_FILE_NAME = "";
    private ReadAWRMinerFile _awrParser = null;

    /**The default constructor for form
     */
    public ChartFilePanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**the JbInit method
     */
    public void jbInit() throws Exception {
        this.setLayout(null);
        this.setSize(new Dimension(706, 300));
        this.add(jComboBox_metricName, null);
        this.add(jButton_chart, null);
        this.add(jButton_selectFile, null);
        this.add(jTextField_fileName, null);


        jTextField_fileName.setBounds(new Rectangle(40, 40, 545, 20));
        String appHome = SessionMetaData.getInstance().getDaiHome();
//        jTextField_fileName.setText(appHome + "\\testing\\awr-hist-389926331-U-775-985.out");
        jButton_selectFile.setText("Select File");
        jButton_selectFile.setBounds(new Rectangle(590, 40, 85, 20));
        jButton_selectFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_selectFile_actionPerformed(e);
                }
            });
        jButton_chart.setText("Chart Metric");
        jButton_chart.setBounds(new Rectangle(285, 90, 95, 20));
        jButton_chart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_chart_actionPerformed(e);
                }
            });
        jComboBox_metricName.setBounds(new Rectangle(40, 90, 240, 20));

        setComboBoxOptions();
    }

    private void jButton_selectFile_actionPerformed(ActionEvent e) {

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

    private void jButton_chart_actionPerformed(ActionEvent e) {
        String selectedFile = this.jTextField_fileName.getText();

        //SetCursor
        RootFrame.startWaitCursor();

        if (!AWR_FILE_NAME.equals(selectedFile)) {
            AWR_FILE_NAME = selectedFile;
            _awrParser = new ReadAWRMinerFile();
            _awrParser.parse(AWR_FILE_NAME);
            _awrParser.dumpData();
        }
        
        //Chart the data
        AWRData awrData = _awrParser.getAWRData();
        String metricName = (String)jComboBox_metricName.getSelectedItem();
        
        if (awrData.awrMetricExists(metricName)) {
            AWRTimeSeriesChart cpuChart =
                new AWRTimeSeriesChart(metricName, awrData);
        } else {
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(), metricName + " Metric Does not exist in this file.", "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
        //SetCursor
        RootFrame.stopWaitCursor();

    }

    private void setComboBoxOptions() {
        ArrayList<String> metricNames = AWRMetrics.getInstance().getMetricNames();
        for (int i=0; i < metricNames.size(); i++) {
            jComboBox_metricName.addItem(metricNames.get(i));
        }
    }
}
