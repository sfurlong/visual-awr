package org.altaprise.vawr.ui.oswchartwizard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.util.ArrayList;

import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.OSWMetrics;
import org.altaprise.vawr.awrdata.file.ReadCellSrvStatFile;
import org.altaprise.vawr.awrdata.file.ReadTopStatFile;
import org.altaprise.vawr.charts.OSWCellSrvStatChart;
import org.altaprise.vawr.charts.TopStatTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.SessionMetaData;
import daiBeans.daiListBox;

import java.awt.Font;

import org.altaprise.vawr.awrdata.file.ReadIOStatFile;
import org.altaprise.vawr.charts.IOStatStorageCellTimeSeriesChart;

public class OSWSelectChartPanel extends WizardContentBasePanel {
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private static String OSW_FILE_NAME = "INITIALIZED";
    private JLabel jLabel1 = new JLabel();
    private JTextArea jTextArea_chartHeading = new JTextArea(3, 60);
    private JSeparator jSeparator1 = new JSeparator();

    private daiListBox listBox_filesToRead = new daiListBox();
    private JSeparator jSeparator2 = new JSeparator();
    private JLabel jLabel2 = new JLabel();
    private JTextField jTextField_startFilterDate = new JTextField();
    private JTextField jTextField_endFilterDate = new JTextField();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JTextField jTextField_fileType = new JTextField();
    private JTextField jTextField_filesPath = new JTextField();
    private JLabel jLabel5 = new JLabel();
    private JCheckBox jCheckBox_chartFlash = new JCheckBox();
    private boolean _isExadataStorage = false;

    public OSWSelectChartPanel() {
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
        
        this.setSize(new Dimension(686, 395));
        jButton_chartMetric.setBounds(new Rectangle(520, 205, 100, 20));
        jButton_chartMetric.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_chartMetric_actionPerformed(e);
            }
        });

        jLabel1.setText("Report Header");
        jLabel1.setBounds(new Rectangle(30, 230, 110, 15));
        jTextArea_chartHeading.setBounds(new Rectangle(30, 250, 595, 65));
        
        this.jTextArea_chartHeading.setColumns(60);
        this.jTextArea_chartHeading.setRows(3);
        this.jTextArea_chartHeading.setLineWrap(false);

        jTextArea_chartHeading.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jSeparator1.setBounds(new Rectangle(45, 190, 575, 2));

        listBox_filesToRead.setBounds(new Rectangle(280, 10, 335, 130));
        jSeparator2.setBounds(new Rectangle(265, 15, 5, 130));
        jSeparator2.setOrientation(SwingConstants.VERTICAL);
        jLabel2.setText("File Type:");
        jLabel2.setBounds(new Rectangle(20, 25, 60, 15));
        jTextField_startFilterDate.setBounds(new Rectangle(100, 50, 135, 20));
        jTextField_endFilterDate.setBounds(new Rectangle(100, 80, 135, 20));
        jLabel3.setText("Start Filter:");
        jLabel3.setBounds(new Rectangle(20, 55, 65, 15));
        jLabel4.setText("End Filter:");
        jLabel4.setBounds(new Rectangle(20, 85, 75, 15));
        jTextField_fileType.setBounds(new Rectangle(100, 20, 140, 20));
        jTextField_filesPath.setBounds(new Rectangle(100, 150, 515, 20));
        jLabel5.setText("File(s)  Path:");
        jLabel5.setBounds(new Rectangle(20, 155, 75, 15));

        jCheckBox_chartFlash.setText("Chart Exadata Flash – Separate Charts for DISK and FLASH  ");
        jCheckBox_chartFlash.setBounds(new Rectangle(30, 200, 410, 20));
        jTextField_filesPath.setEnabled(false);
        jTextField_fileType.setEnabled(false);
        jTextField_startFilterDate.setEnabled(false);
        jTextField_endFilterDate.setEnabled(false);
        listBox_filesToRead.setEnabled(false);

        this.add(jCheckBox_chartFlash, null);
        this.add(jLabel5, null);
        this.add(jTextField_filesPath, null);
        this.add(jTextField_fileType, null);
        this.add(jLabel4, null);
        this.add(jLabel3, null);
        this.add(jTextField_endFilterDate, null);
        this.add(jTextField_startFilterDate, null);
        this.add(jLabel2, null);
        this.add(jSeparator2, null);
        this.add(listBox_filesToRead, null);
        this.add(jSeparator1, null);
        this.add(jTextArea_chartHeading, null);

        this.add(jLabel1, null);
        this.add(jButton_chartMetric, null);
        this.listBox_filesToRead.setListItemsFont(new Font("monospaced", Font.PLAIN, 11));

        //Set the Wizard Label
        this.setPanelLabel("OSWatcher/ExaWatcher Analytics");
        
    }

    protected void doNextOperation() {
        this.jTextField_filesPath.setText(OSWSelectFilesPanel.getFilesPath());
        ArrayList<String> filesToRead = OSWSelectFilesPanel.getfilteredFileNames();
        listBox_filesToRead.removeAllItems();
        listBox_filesToRead.getAllListItems();
        for (int i=0; i<filesToRead.size(); i++) {
            listBox_filesToRead.addItem(filesToRead.get(i));
        }
        this.jTextField_fileType.setText(OSWSelectFilesPanel.getFileType());
        this.jTextField_startFilterDate.setText(OSWSelectFilesPanel.getStartFilterDate());
        this.jTextField_endFilterDate.setText(OSWSelectFilesPanel.getEndFilterDate());
        if (this.jTextField_fileType.getText().equals("IOSTAT")) {
            jCheckBox_chartFlash.setVisible(true);
            jCheckBox_chartFlash.setSelected(false);
        } else {
            jCheckBox_chartFlash.setVisible(false);
        }
    }

    private void jButton_chartMetric_actionPerformed(ActionEvent e) {
        
        //SetCursor
        RootFrame.startWaitCursor();

        ArrayList<String> filesToRead = this.listBox_filesToRead.getAllListItems();
        String errFileName = "";
        
        try {

            OSWData.getInstance().clearData();
            if (this.jTextField_fileType.getText().equals("TOPSTAT")) {
                for (int i=0; filesToRead != null && i<filesToRead.size(); i++) {
                    errFileName = filesToRead.get(i);
                    String fullPathName = this.jTextField_filesPath.getText() + File.separator + filesToRead.get(i);
                    System.out.println("read this file: " + fullPathName);
                    ReadTopStatFile topStatFileParser = new ReadTopStatFile();
                    topStatFileParser.parse(fullPathName);
                }
                if (SessionMetaData.getInstance().debugOn()) {
                    OSWData.getInstance().dump();
                }
                if (OSWData.getInstance().getTopStatRecs().size() > 0) {
                    new TopStatTimeSeriesChart("", this.jTextArea_chartHeading.getText().trim());
                } else {
                    JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                                  " TOPSTAT data does not exist in this file(s).", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            } else if (jTextField_fileType.getText().equals("IOSTAT")) {
                if (this.jCheckBox_chartFlash.isSelected()) {
                    _isExadataStorage = true;    
                } else {
                    _isExadataStorage = false;
                }
                for (int i=0; filesToRead != null && i<filesToRead.size(); i++) {
                    errFileName = filesToRead.get(i);
                    String fullPathName = this.jTextField_filesPath.getText() + File.separator + filesToRead.get(i);
                    System.out.println("read this file: " + fullPathName);
                    ReadIOStatFile ioStatFileParser = new ReadIOStatFile();
                    ioStatFileParser.parse(fullPathName, _isExadataStorage);
                }
                if (SessionMetaData.getInstance().debugOn()) {
                    OSWData.getInstance().dump();
                }
                if (OSWData.getInstance().getIoStatRecs().size() > 0) {
                    new IOStatStorageCellTimeSeriesChart("FLASH", "Cell IOPS");
                } else {
                    JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                                  " IOSTAT data does not exist in this file(s).", "Error",
                                                  JOptionPane.ERROR_MESSAGE);
                }
            } 
            
        } catch (Exception ex) {
            RootFrame.stopWaitCursor();
            
            ex.printStackTrace();
            String msg = errFileName + "\n" + "Are you sure this is a ";
            msg += "OSW " + this.jTextField_fileType.getText() + " File?";
            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          msg +"\n" + "Error: " + ex.getLocalizedMessage(), "Error",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            RootFrame.stopWaitCursor();
        }

    }
}

