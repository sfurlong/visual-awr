package org.altaprise.vawr.ui.oswchartwizard;

import dai.shared.businessObjs.DBRec;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.SpinnerDateModel;
import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.file.ReadCellSrvStatFile;
import org.altaprise.vawr.awrdata.file.ReadIOStatFile;
import org.altaprise.vawr.awrdata.file.ReadTopStatFile;
import org.altaprise.vawr.charts.TopStatTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.SessionMetaData;


public class OSWSelectFilesPanel extends WizardContentBasePanel {
    private JLabel jLabel_selectMetrics = new JLabel();
    private JTextField jTextField_fileName = new JTextField();
    private JButton jButton_selectFile = new JButton();
    private static String OSW_FILE_NAME = "INITIALIZED";
    private static JRadioButton jRadioButton_topStat = new JRadioButton();
    private static JRadioButton jRadioButton_IoStat = new JRadioButton();
    private static JRadioButton jRadioButton_VmStat = new JRadioButton();
    private static JRadioButton jRadioButton_CellSrvStat = new JRadioButton();
    private JLabel jLabel2 = new JLabel();
    private ButtonGroup buttonGroup_fileType = new ButtonGroup();
    private JSeparator jSeparator1 = new JSeparator();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTextArea1 = new JTextArea();
    private static JSpinner jSpinner_startTime = new JSpinner(new SpinnerDateModel());
    private static JSpinner jSpinner_endTime = new JSpinner(new SpinnerDateModel());
    private JButton jButton_testFiles = new JButton();
    private static JCheckBox jCheckBox_useTimeFilter = new JCheckBox();
    private static ArrayList<String> _filteredFileNameList = new ArrayList<String>();
    private static ArrayList<String> _selectedFileNameList = new ArrayList<String>();
    private static String _filesPath = "";
    private static File[] _selectedFiles = null;

    public OSWSelectFilesPanel() {
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
        jLabel_selectMetrics.setBounds(new Rectangle(10, 65, 150, 15));

        jLabel_selectMetrics.setText("OSW-ExaWatcher File(s):");
        jTextField_fileName.setBounds(new Rectangle(165, 60, 290, 20));
        jTextField_fileName.setSize(new Dimension(355, 20));
        jButton_selectFile.setText("Browse...");
        jButton_selectFile.setBounds(new Rectangle(525, 60, 90, 20));
        jButton_selectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_selectFile_actionPerformed(e);
            }
        });
        jRadioButton_topStat.setText("TopStat");
        jRadioButton_topStat.setBounds(new Rectangle(165, 100, 86, 18));
        jRadioButton_IoStat.setText("IOStat");
        jRadioButton_IoStat.setBounds(new Rectangle(260, 100, 86, 18));
        jRadioButton_VmStat.setText("VMStat");
        jRadioButton_VmStat.setBounds(new Rectangle(355, 100, 86, 18));
        jRadioButton_CellSrvStat.setText("CellSrvStat");
        jRadioButton_CellSrvStat.setBounds(new Rectangle(450, 100, 86, 18));
        jLabel2.setText("OSW/ExaWatcher File Type:");
        jLabel2.setBounds(new Rectangle(10, 100, 145, 15));


        jSeparator1.setBounds(new Rectangle(45, 230, 545, 2));
        jScrollPane1.setBounds(new Rectangle(15, 240, 615, 150));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        jTextArea1.setFont(new Font("monospaced", Font.PLAIN, 11));
        jTextArea1.setEnabled(false);

        JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(jSpinner_startTime, "MM/dd/yyyy HH:mm");
        JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(jSpinner_endTime, "MM/dd/yyyy HH:mm");
        jSpinner_startTime.setEditor(startTimeEditor);
        jSpinner_endTime.setEditor(endTimeEditor);
        jSpinner_startTime.setBounds(new Rectangle(125, 145, 120, 20));
        jSpinner_endTime.setBounds(new Rectangle(265, 145, 120, 20));

        jCheckBox_useTimeFilter.setText("Date/Time Filter");
        jCheckBox_useTimeFilter.setBounds(new Rectangle(15, 145, 105, 20));
        jButton_testFiles.setText("Test Files");
        jButton_testFiles.setBounds(new Rectangle(530, 190, 85, 20));
        jButton_testFiles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_testFiles_actionPerformed(e);
            }
        });
        this.jRadioButton_topStat.setSelected(true);
        buttonGroup_fileType.add(this.jRadioButton_topStat);
        buttonGroup_fileType.add(this.jRadioButton_IoStat);
        buttonGroup_fileType.add(this.jRadioButton_VmStat);
        buttonGroup_fileType.add(this.jRadioButton_CellSrvStat);

        jScrollPane1.getViewport().add(jTextArea1, null);
        this.add(jButton_testFiles, null);
        this.add(jCheckBox_useTimeFilter, null);
        this.add(jSpinner_endTime, null);
        this.add(jSpinner_startTime, null);
        this.add(jScrollPane1, null);
        this.add(jSeparator1, null);
        this.add(jLabel2, null);
        this.add(jRadioButton_IoStat, null);
        this.add(jRadioButton_topStat, null);
        this.add(jRadioButton_VmStat, null);
        this.add(jRadioButton_CellSrvStat, null);
        this.add(jButton_selectFile, null);
        this.add(jTextField_fileName, null);
        this.add(jLabel_selectMetrics, null);

        //Set the Wizard Label
        this.setPanelLabel("1. Select the file(s) to chart and 0ptionally filter on date/time.");
    }


    private void jButton_selectFile_actionPerformed(ActionEvent e) {

        _selectedFileNameList.clear();
        _filteredFileNameList.clear();

        String appHome = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(appHome);
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            _selectedFiles = chooser.getSelectedFiles();
            String absolutePath = _selectedFiles[0].getAbsolutePath();
            _filesPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
            System.out.println("You chose this path: " + _filesPath);
            this.jTextField_fileName.setText(_filesPath);
        }
        for (int i = 0; _selectedFiles != null && i < _selectedFiles.length; i++) {
            _selectedFileNameList.add(_selectedFiles[i].getName());
        }
    }


    private void jButton_testFiles_actionPerformed(ActionEvent e) {

        jTextArea1.setText("");
        //SetCursor
        RootFrame.startWaitCursor();

        ReadTopStatFile topStatFileParser = new ReadTopStatFile();
        ReadIOStatFile ioStatFileParser = new ReadIOStatFile();

        Date startTimeD = (Date) jSpinner_startTime.getValue();
        Date endTimeD = (Date) jSpinner_endTime.getValue();
        boolean doFilter = false;
        _filteredFileNameList.clear();
        DBRec testResults = null;
        String fileType = "";
        boolean foundErr = false;
        String selectedFile = "";

        if (jCheckBox_useTimeFilter.isSelected()) {
            doFilter = true;
        }

        for (int i = 0; _selectedFiles != null && i < _selectedFiles.length && !foundErr; i++) {
            try {
                selectedFile = _selectedFiles[i].getName();
                if (jRadioButton_topStat.isSelected()) {
                    fileType = "TOPSTAT";
                    testResults =
                        topStatFileParser.testFile(_selectedFiles[i].getPath(), startTimeD, endTimeD, doFilter);
                } else {
                    fileType = "IOSTAT";
                    testResults =
                        ioStatFileParser.testFile(_selectedFiles[i].getPath(), startTimeD, endTimeD, doFilter);
                }
                if (testResults != null) {
                    String startDateTimeS = testResults.getAttribVal("FILE_START_DATETIME");
                    String endDateTimeS = testResults.getAttribVal("FILE_END_DATETIME");
                    jTextArea1.append(_selectedFiles[i].getName() + " | " + startDateTimeS + " | " + endDateTimeS +
                                      "\n");
                    _filteredFileNameList.add(_selectedFiles[i].getName());
                }
            } catch (Exception ex) {
                foundErr = true;
                ex.printStackTrace();
                String msg = "Are you sure this is a ";
                msg += "OSW " + fileType + " File?:\n" + selectedFile;
                JOptionPane.showMessageDialog(RootFrame.getFrameRef(), ex.getLocalizedMessage() + "\n" + msg, "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }
        }

        RootFrame.stopWaitCursor();

    }

    public static ArrayList<String> getfilteredFileNames() {
        ArrayList<String> ret = null;
        if (jCheckBox_useTimeFilter.isSelected()) {
            ret = _filteredFileNameList;
        } else {
            ret = _selectedFileNameList;
        }
        return ret;
    }

    public static String getFilesPath() {
        return _filesPath;
    }

    public static String getStartFilterDate() {
        String ret = "";

        if (jCheckBox_useTimeFilter.isSelected()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            ret = sdf.format((Date) jSpinner_startTime.getValue());
        }
        return ret;
    }

    public static String getEndFilterDate() {
        String ret = "";

        if (jCheckBox_useTimeFilter.isSelected()) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            ret = sdf.format((Date) jSpinner_endTime.getValue());
        }
        return ret;
    }

    public static String getFileType() {
        String ret = "";
        if (jRadioButton_topStat.isSelected()) {
            ret = "TOPSTAT";
        } else if (jRadioButton_IoStat.isSelected()) {
            ret = "IOSTAT";
        } else if (jRadioButton_VmStat.isSelected()) {
            ret = "VMSTAT";
        } else if (jRadioButton_CellSrvStat.isSelected()) {
            ret = "CELLSRVSTAT";
        }
        return ret;
    }

}
