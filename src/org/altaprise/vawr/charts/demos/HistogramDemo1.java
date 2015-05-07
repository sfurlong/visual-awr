/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package org.altaprise.vawr.charts.demos;

import java.awt.Dimension;

import java.io.IOException;

import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class HistogramDemo1 extends ApplicationFrame
{

        public HistogramDemo1(String s)
        {
                super(s);
                JPanel jpanel = createDemoPanel();
                jpanel.setPreferredSize(new Dimension(500, 270));
                setContentPane(jpanel);
        }

        private static IntervalXYDataset createDataset()
        {
                HistogramDataset histogramdataset = new HistogramDataset();
                double ad[] = new double[1000];
                Random random = new Random(0xbc614eL);
                for (int i = 0; i < 1000; i++)
                        ad[i] = random.nextGaussian() + 5D;

                //histogramdataset.addSeries("H1", ad, 100, 2D, 8D);
                ad = new double[1000];
                for (int j = 0; j < 1000; j++)
                        ad[j] = random.nextGaussian() + 7D;

                //histogramdataset.addSeries("H2", ad, 100, 4D, 10D);
                histogramdataset.addSeries("H1", new double[]{94.6}, 1);
                histogramdataset.addSeries("H2", new double[]{3.64}, 1);
                //histogramdataset.addSeries("H1", new double[]{94.6}, 100, 0D, 100D);
                return histogramdataset;
        }

        private static JFreeChart createChart(IntervalXYDataset intervalxydataset)
        {
                JFreeChart jfreechart = ChartFactory.createHistogram("Histogram Demo 1", null, null, intervalxydataset, PlotOrientation.VERTICAL, true, true, false);
                XYPlot xyplot = (XYPlot)jfreechart.getPlot();
                xyplot.setForegroundAlpha(0.85F);
                XYBarRenderer xybarrenderer = (XYBarRenderer)xyplot.getRenderer();
                xybarrenderer.setDrawBarOutline(false);
                return jfreechart;
        }

        public static JPanel createDemoPanel()
        {
                JFreeChart jfreechart = createChart(createDataset());
                return new ChartPanel(jfreechart);
        }

        public static void main(String args[])
                throws IOException
        {
                HistogramDemo1 histogramdemo1 = new HistogramDemo1("JFreeChart : HistogramDemo1");
                histogramdemo1.pack();
                RefineryUtilities.centerFrameOnScreen(histogramdemo1);
                histogramdemo1.setVisible(true);
        }
}

