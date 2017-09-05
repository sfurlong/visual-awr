package org.altaprise.test;

import java.awt.event.WindowEvent;

import java.io.File;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.altaprise.vawr.awrdata.AWRData;
import org.altaprise.vawr.awrdata.AWRMetrics;
import org.altaprise.vawr.awrdata.file.ReadAWRMinerFile;
import org.altaprise.vawr.charts.AWRIOPSTimeSeriesChart;
import org.altaprise.vawr.charts.AWRMemoryTimeSeriesChart;
import org.altaprise.vawr.charts.AWRMetricSummaryChart;
import org.altaprise.vawr.charts.AWRTimeSeriesChart;
import org.altaprise.vawr.charts.AvgActiveSessionChart;
import org.altaprise.vawr.charts.SizeOnDiskChart;
import org.altaprise.vawr.charts.TopWaitEventsBarChart;
import org.altaprise.vawr.ui.RootFrame;
import org.altaprise.vawr.utils.SessionMetaData;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDemoFilesAndCharts {
    public TestDemoFilesAndCharts() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * @see ReadAWRMinerFile#parse(String)
     */
    @Test
    public void testParse() {
        System.out.println("hello world");
        
        String filePath = "./testing/AWRMiner";
        String selectedFile = "";
        try {

            File folder = new File(filePath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("\n\n~~File " + listOfFiles[i].getName());
                    this.readFile(filePath + "/" + listOfFiles[i].getName());
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    private void readFile(String fileName) {
        ReadAWRMinerFile _awrParser = null;
        try {
            AWRData.getInstance().clearAWRData();
            _awrParser = new ReadAWRMinerFile();
            _awrParser.parse(fileName);
            _awrParser.parseMemData(fileName);
            if (SessionMetaData.getInstance().debugOn()) {
                AWRData.getInstance().dumpData();
            }
            ArrayList<String> metricNames = AWRMetrics.getInstance().getOracleMetricNames();
            for (int i = 0; i < metricNames.size(); i++) {
                String oraMetricName = metricNames.get(i);
                //Convert to AWRMiner metric name
                String metricName = AWRMetrics.getAWRMinerMetricName(oraMetricName);                
                
                System.out.println("~~~Report Name: " + oraMetricName);
                if (AWRData.getInstance().awrMetricExists(metricName)|| metricName.equals("SUMMARY")) {
                    System.out.println("~~~~Report Name Executing: " + oraMetricName);
                    if (metricName.equals("SGA_PGA_TOT")) {
                        AWRMemoryTimeSeriesChart frame = new AWRMemoryTimeSeriesChart(metricName, AWRData.getInstance().getChartHeaderHTML());
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } else if (metricName.equals("AVG_ACTIVE_SESS_WAITS")) {
                        AvgActiveSessionChart frame = new AvgActiveSessionChart(metricName, AWRData.getInstance().getChartHeaderHTML());
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } else if (metricName.equals("TOP_N_TIMED_EVENTS")) {
                        TopWaitEventsBarChart frame = new TopWaitEventsBarChart(metricName);
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } else if (metricName.equals("WRITE_IOPS") || metricName.equals("READ_IOPS")) {
                        AWRIOPSTimeSeriesChart frame = new AWRIOPSTimeSeriesChart(metricName, AWRData.getInstance().getChartHeaderHTML());
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } else if (metricName.equals("SUMMARY")) {
                        AWRMetricSummaryChart frame = new AWRMetricSummaryChart(metricName, true);
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } else if (metricName.equals("SIZE_GB")) {
                        SizeOnDiskChart frame = new SizeOnDiskChart(metricName, AWRData.getInstance().getChartHeaderHTML());
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } else {
                        AWRTimeSeriesChart frame = new AWRTimeSeriesChart(metricName, AWRData.getInstance().getChartHeaderHTML());
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                } else {
                    System.out.println("WARNING: " + metricName + " Metric Does not exist in this file." + " FILE NAME: " + AWRData.getInstance().getAWRFileName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
