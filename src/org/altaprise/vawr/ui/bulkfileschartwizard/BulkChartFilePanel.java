package org.altaprise.vawr.ui.bulkfileschartwizard;

import java.awt.*;
import java.awt.event.*;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import daiBeans.daiListBox;
import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.file.ReadAWRMinerFile;
import org.altaprise.vawr.charts.AWRMetricSummaryChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.utils.PropertyFile;
import org.altaprise.vawr.utils.SessionMetaData;

public class BulkChartFilePanel extends JPanel {
    private daiListBox daiListBox_fileNames = new daiListBox();
    private JButton jButton_selectFile = new JButton();
    private JButton jButton_chart = new JButton();
    private JComboBox jComboBox_metricName = new JComboBox();
    private JCheckBox jCheckBox_showReport = new JCheckBox();
    private JPanel  jPanel_panelTitle = new JPanel();
    private JPanel jPanel_contentPanel = new JPanel();
    private JLabel jLabel_panelTitle = new JLabel();
    private JTextPane jTextArea_osInfo = new JTextPane();
    private JScrollPane jScrollPane_osInfo = new JScrollPane(jTextArea_osInfo);

    private static String AWR_FILE_NAME = "INITIALIZED";
    private ReadAWRMinerFile _awrParser = null;
    private JLabel jLabel1 = new JLabel("Platform Details:");
    private JSeparator jSeparator1 = new JSeparator();

    /**The default constructor for form
     */
    public BulkChartFilePanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**the JbInit method
     */
    public void jbInit() throws Exception {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.setSize(new Dimension(603, 503));

        daiListBox_fileNames.setBounds(new Rectangle(40, 25, 350, 120));
        jLabel_panelTitle.setText("Select one or more AWRMiner files, then view the Summary Metrics.");
        jLabel_panelTitle.setBounds(new Rectangle(20, 5, 500, 30));
        jLabel_panelTitle.setFont(new Font("Arial", 1, 16));

        jScrollPane_osInfo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        jPanel_panelTitle.setLayout(null);
        jPanel_panelTitle.setBackground(new Color(247, 247, 247));
        jPanel_panelTitle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jPanel_panelTitle.setMinimumSize(new Dimension(48, 50));
        jPanel_panelTitle.setPreferredSize(new Dimension(48, 50));
        jPanel_panelTitle.add(jLabel_panelTitle, null);
        
        jPanel_contentPanel.setLayout(null);

        //jPanel_contentPanel.setSize(new Dimension(706, 300));
        jScrollPane_osInfo.setBounds(new Rectangle(35, 200, 560, 220));
        jScrollPane_osInfo.setMinimumSize(new Dimension(48, 200));
        jScrollPane_osInfo.setPreferredSize(new Dimension(48, 200));

        jLabel1.setBounds(new Rectangle(35, 185, 325, 15));

        jLabel1.setText("Summary Metrics for all workloads(Paste into Excel):");
        jSeparator1.setBounds(new Rectangle(15, 170, 570, 5));
        jPanel_contentPanel.setBounds(new Rectangle(20, 5, 400, 30));
        jPanel_contentPanel.setMinimumSize(new Dimension(48, 200));
        jPanel_contentPanel.add(jSeparator1, null);
        jPanel_contentPanel.add(jLabel1, null);
        jPanel_contentPanel.add(jComboBox_metricName, null);
        jPanel_contentPanel.add(jCheckBox_showReport, null);
        
        jPanel_contentPanel.add(jButton_chart, null);

        jPanel_contentPanel.add(jButton_selectFile, null);
        jPanel_contentPanel.add(daiListBox_fileNames, null);

        jPanel_contentPanel.add(jScrollPane_osInfo, null);
        this.add(jPanel_panelTitle, BorderLayout.NORTH);

        this.add(jPanel_contentPanel, BorderLayout.CENTER);
        String appHome = SessionMetaData.getInstance().getDaiHome();
        jButton_selectFile.setText("Select Files");
        jButton_selectFile.setBounds(new Rectangle(415, 25, 130, 20));
        jButton_selectFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_selectFile_actionPerformed(e);
                }
            });
        jButton_chart.setText("Generate Metrics");
        jButton_chart.setBounds(new Rectangle(415, 50, 130, 20));
        jButton_chart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_chart_actionPerformed(e);
                }
            });
        jComboBox_metricName.setBounds(new Rectangle(40, 150, 300, 20));
        jCheckBox_showReport.setBounds(new Rectangle(350, 150, 300, 20));
        jCheckBox_showReport.setText("Launch Report?");
        //jTextArea_osInfo.setEnabled(false);
        jTextArea_osInfo.setFont(new Font("monospaced", Font.PLAIN, 11));
        setComboBoxOptions();
    }
    File[] _selectedFiles = null;
    private void jButton_selectFile_actionPerformed(ActionEvent e) {
        String lastPath = PropertyFile.getInstance().getLastFilePath();
        String appHome = System.getProperty("user.dir");
        if (lastPath != null && lastPath.trim().length() > 0) appHome = lastPath;
        JFileChooser chooser = new JFileChooser(appHome);
        chooser.setMultiSelectionEnabled(true);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter(
        //    "JPG & GIF Images", "jpg", "gif");
        //chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            _selectedFiles = chooser.getSelectedFiles();
            this.daiListBox_fileNames.removeAllItems();
            for (int i=0; i< _selectedFiles.length; i++) {
                String fileName = _selectedFiles[i].getName();
                String filePath = _selectedFiles[i].getPath();
                this.daiListBox_fileNames.addItem(fileName);
                System.out.println("You chose to open this file/path: " + fileName + "/" + filePath);
            }
            String justPath = chooser.getCurrentDirectory().getPath();
            PropertyFile.getInstance().setLastFilePath(justPath);
            PropertyFile.getInstance().serializeIt();
        }

    }

    private void jButton_chart_actionPerformed(ActionEvent e) {

        //SetCursor
        RootFrame.startWaitCursor();

        try {
            boolean showReport = this.jCheckBox_showReport.isSelected();
            String results = "";
            //Read and parse the file
            for (int i=0; i<this._selectedFiles.length; i++) {
                String selectedFile = this._selectedFiles[i].getPath();
                AWRData.getInstance().clearAWRData();
                _awrParser = new ReadAWRMinerFile();
                _awrParser.parse(selectedFile);
                _awrParser.parseMemData(selectedFile);
                AWR_FILE_NAME = selectedFile;

                AWRMetricSummaryChart chart = new AWRMetricSummaryChart("SUMMARY", showReport);
                boolean returnHeader = false;
                if (i==0) {
                    returnHeader = true;
                }    
                String summaryMetrics = chart.getChartHeaderCSV(returnHeader);
                results += summaryMetrics + "\n";
                System.out.println(summaryMetrics);
                //chart.dispose();

            }
            this.jTextArea_osInfo.setText(results);

        } catch (Exception ex) {
            //SetCursor
            RootFrame.stopWaitCursor();
            
            ex.printStackTrace();

            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          ex.getLocalizedMessage(), "Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
        //SetCursor
        RootFrame.stopWaitCursor();

    }

    private void setComboBoxOptions() {
        ArrayList<String> metricNames = AWRMetrics.getInstance().getOracleMetricNames();
        for (int i = 0; i < metricNames.size(); i++) {
            jComboBox_metricName.addItem(metricNames.get(i));
        }
    }

}
