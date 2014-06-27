package org.altaprise.vawr.ui;

import daiBeans.BrowserPanel;
import daiBeans.daiGrid;

import java.awt.*;
import java.awt.event.*;

import java.awt.event.ActionEvent;


import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetric;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.file.ReadAWRMinerFile;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.utils.SessionMetaData;

public class MetricsDescriptionPanel extends JPanel {

    private JEditorPane _metricDescGrid = new JEditorPane();
    private JScrollPane jScrollPane = new JScrollPane(_metricDescGrid);
    private BorderLayout borderLayout = new BorderLayout();
    
    /**The default constructor for form
     */
    public MetricsDescriptionPanel() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**the JbInit method
     */
    public void jbInit() throws Exception {
        this.setLayout(borderLayout);
        this.add(jScrollPane, null);
        
        this.populateMetricDescriptions();
    }

    private void populateMetricDescriptions() {
        String htmlString = "";
        
        ArrayList<AWRMetric> metricDetails = AWRMetrics.getMetricDetails();
        htmlString += "<style type=\'text/css\'> td{font-family:Arial; color:blue; font-size:11pt;} th{font-family:Arial; color:black; font-size:12pt;} </style> ";
        htmlString += "<table border=\"1\" font size=\"1\">\n";
        htmlString += "<tr>";
        htmlString += "<th>AWRMiner Metric Name</th><th>Oracle AWR Metric ID</th><th>Oracle AWR Metric Name</th><th>AWR Metric Unit</th><th>ChartTitle</th>";
        htmlString += "</tr>";
        
        for (int i=0; i<metricDetails.size(); i++) {
            htmlString += "<tr>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getAWRMinerMetricName();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getAwrOracleMetricId();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getAWROracleMetricName();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getAWROracleMetricUnit();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getMetricChartTitle();
            htmlString += "</td>\n";
            htmlString += "</tr>\n";
        }
        htmlString += "</table>\n";
        
        _metricDescGrid.setContentType("text/html");
        _metricDescGrid.setText(htmlString);
    }

}
