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
        
        ArrayList<AWRMetrics.AWRMetric> metricDetails = AWRMetrics.getMetricDetails();
        
        htmlString += "<table border=\"1\">\n";
        htmlString += "<tr>";
        htmlString += "<th>MetricName</th><th>Metric Desc</th><th>ChartTitle</th><th>Chart Range Desc</th>";
        htmlString += "</tr>";
        
        for (int i=0; i<metricDetails.size(); i++) {
            htmlString += "<tr>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getMetricName();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getMetricDescription();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getMetricChartTitle();
            htmlString += "</td>\n";
            htmlString += "<td>\n";
            htmlString += metricDetails.get(i).getMetricChartRangeDescription();
            htmlString += "</td>\n";
            htmlString += "</tr>\n";
        }
        htmlString += "</table>\n";
        
        _metricDescGrid.setContentType("text/html");
        _metricDescGrid.setText(htmlString);
    }

}
