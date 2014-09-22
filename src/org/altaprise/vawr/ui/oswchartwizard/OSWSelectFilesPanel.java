package org.altaprise.vawr.ui.oswchartwizard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.awrdata.OSWData;
import org.altaprise.vawr.awrdata.file.ReadCellSrvStatFile;
import org.altaprise.vawr.awrdata.file.ReadTopStatFile;
import org.altaprise.vawr.charts.TopStatTimeSeriesChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.utils.SessionMetaData;


public class OSWSelectFilesPanel extends WizardContentBasePanel {
    private JButton jButton_chartMetric = new JButton("Chart Metric");
    private JLabel jLabel_selectMetrics =
        new JLabel("Select AWR Metric to Chart");
    private JTextField jTextField_fileName = new JTextField();
    private JButton jButton_selectFile = new JButton();
    private static String OSW_FILE_NAME = "INITIALIZED";
    private File[] _selectedFiles = null;
    private JLabel jLabel1 = new JLabel();
    private JRadioButton jRadioButton_topStat = new JRadioButton();
    private JRadioButton jRadioButton_cellSrvStat = new JRadioButton();
    private JLabel jLabel2 = new JLabel();
    private JTextArea jTextArea_chartHeading = new JTextArea(3, 60);
    private ButtonGroup buttonGroup_fileType = new ButtonGroup();
    private JSeparator jSeparator1 = new JSeparator();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextArea jTextArea1 = new JTextArea();

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
        jButton_chartMetric.setBounds(new Rectangle(515, 220, 100, 20));
        jButton_chartMetric.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_chartMetric_actionPerformed(e);
            }
        });
        jLabel_selectMetrics.setBounds(new Rectangle(10, 65, 150, 15));

        jLabel_selectMetrics.setText("OSW-ExaWatcher File(s):");
        jTextField_fileName.setBounds(new Rectangle(165, 60, 290, 20));
        jTextField_fileName.setSize(new Dimension(355, 20));
        jButton_selectFile.setText("Browse...");
        jButton_selectFile.setBounds(new Rectangle(525, 60, 90, 20));
        jButton_selectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton1_actionPerformed(e);
            }
        });
        jLabel1.setText("Report Header");
        jLabel1.setBounds(new Rectangle(10, 145, 110, 15));
        jRadioButton_topStat.setText("TopStat");
        jRadioButton_topStat.setBounds(new Rectangle(165, 100, 86, 18));
        jRadioButton_cellSrvStat.setText("CellSrvStat");
        jRadioButton_cellSrvStat.setBounds(new Rectangle(260, 100, 86, 18));
        jLabel2.setText("OSW/ExaWatcher File Type:");
        jLabel2.setBounds(new Rectangle(10, 100, 145, 15));
        jTextArea_chartHeading.setBounds(new Rectangle(100, 145, 515, 65));
        
        this.jTextArea_chartHeading.setColumns(60);
        this.jTextArea_chartHeading.setRows(3);
        this.jTextArea_chartHeading.setLineWrap(false);

        jTextArea_chartHeading.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jSeparator1.setBounds(new Rectangle(50, 250, 575, 2));
        jScrollPane1.setBounds(new Rectangle(100, 260, 510, 110));
        jScrollPane1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jTextArea1.setEditable(false);
        jTextArea1.setBackground(Color.lightGray);
        this.jRadioButton_topStat.setSelected(true);
        buttonGroup_fileType.add(this.jRadioButton_topStat);
        buttonGroup_fileType.add(this.jRadioButton_cellSrvStat);

        jScrollPane1.getViewport().add(jTextArea1, null);
        this.add(jScrollPane1, null);
        this.add(jSeparator1, null);
        this.add(jTextArea_chartHeading, null);
        this.add(jLabel2, null);
        this.add(jRadioButton_cellSrvStat, null);
        this.add(jRadioButton_topStat, null);
        this.add(jLabel1, null);
        this.add(jButton_selectFile, null);
        this.add(jTextField_fileName, null);
        this.add(jLabel_selectMetrics, null);

        //Set the Wizard Label
        this.add(jButton_chartMetric, null);
        this.setPanelLabel("OSWatcher/ExaWatcher Analytics");
    }


    private void jButton1_actionPerformed(ActionEvent e) {

        //FileChooser.setMultiSelectionEnabled(true). 
        //After the file chooser has been dismissed, the selected files are retrieved with 
        //JFileChooser.getSelectedFiles()

        String appHome = System.getProperty("user.dir");
        JFileChooser chooser = new JFileChooser(appHome);
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            _selectedFiles = chooser.getSelectedFiles();
            String filePath = chooser.getSelectedFile().getPath();
            System.out.println("You chose to open this file: " + filePath);
            this.jTextField_fileName.setText(filePath);
        }

    }

    private void jButton_chartMetric_actionPerformed(ActionEvent e) {
        
        //SetCursor
        RootFrame.startWaitCursor();

        ReadTopStatFile topStatFileParser = new ReadTopStatFile();
        ReadCellSrvStatFile cellSrvStatFileParser = new ReadCellSrvStatFile();
        
        try {

/*
            if (!OSW_FILE_NAME.equals(selectedFile)) {
                TopStatData.getInstance().clearData();
                oswFileParser.parse(selectedFile);
                OSW_FILE_NAME = selectedFile;
                if (SessionMetaData.getInstance().debugOn()) {
                    TopStatData.getInstance().dump();
                }
            }
*/            
            
            OSWData.getInstance().clearData();
            if (this.jRadioButton_topStat.isSelected()) {
                for (int i=0; _selectedFiles != null && i<_selectedFiles.length; i++) {
                    topStatFileParser.parse(_selectedFiles[i].getPath());
                }
                if (SessionMetaData.getInstance().debugOn()) {
                    OSWData.getInstance().dump();
                }
                if (OSWData.getInstance().getTopStatRecs().size() > 0) {
                    new TopStatTimeSeriesChart("", this.jTextArea_chartHeading.getText().trim());
                }
            } else if (this.jRadioButton_cellSrvStat.isSelected()) {
                for (int i=0; _selectedFiles != null && i<_selectedFiles.length; i++) {
                    cellSrvStatFileParser.parse(_selectedFiles[i].getPath());
                }
                if (SessionMetaData.getInstance().debugOn()) {
                    OSWData.getInstance().dump();
                }
                if (OSWData.getInstance().getCellSrvStatRecs().size() > 0) {
                    
                }
            } else {
                JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                              " TopStat data does not exist in this file.",
                                              "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }

            
        } catch (Exception ex) {
            RootFrame.stopWaitCursor();
            
            ex.printStackTrace();
            String msg = "Are you sure this is a ";
            if (this.jRadioButton_topStat.isSelected()) {
                msg += "OSW TopStat File?";
            } else {
                msg += "OSW CellSrvStat File?";
            }

            JOptionPane.showMessageDialog(RootFrame.getFrameRef(),
                                          ex.getLocalizedMessage() +"\n" + msg, "Error",
                                          JOptionPane.ERROR_MESSAGE);
        } finally {
            RootFrame.stopWaitCursor();
        }

    }
}

