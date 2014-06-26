package org.altaprise.vawr.ui;

import daiBeans.BrowserPanel;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import org.altaprise.vawr.ui.dbwizard.DBConnectPanel;
import org.altaprise.vawr.ui.dbwizard.WizardRootPanel;

public class RootPanel extends JPanel {

    JTabbedPane tabbedPane = new JTabbedPane();
    DBConnectionsPanel _dbConnPanel = new DBConnectionsPanel();
    ChartFilePanel _fileTabPanel = new ChartFilePanel();
    WizardRootPanel _dbChartTab = new WizardRootPanel();
    AppAboutPanel _aboutPanel = new AppAboutPanel();
    MetricsDescriptionPanel _metricDescPanel = new MetricsDescriptionPanel();

    public RootPanel() {
        super(new GridLayout(1, 1));
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jbInit() throws Exception {
        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>About</body></html>",
                          _aboutPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Chart File Data</body></html>",
                          this._fileTabPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>Chart DB Data</body></html>",
                          this._dbChartTab);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>DB Connections</body></html>",
                          _dbConnPanel);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=15 marginheight=5>About AWR Metrics</body></html>",
                          _metricDescPanel);
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        tabbedPane.setPreferredSize(new Dimension(760, 455));
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(696, 350));


        RootPanel rootPanel = new RootPanel();


        //Add content to the window.
        frame.add(rootPanel, BorderLayout.CENTER);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.

        createAndShowGUI();
    }
}
