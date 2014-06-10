package org.altaprise.vawr.ui.dbwizard;

import java.awt.Dimension;
import java.awt.LayoutManager;

import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WizardRootPanel extends JPanel {

    //DBConnectPanel dbChartTab = new DBConnectPanel();
    private JButton jButton_previous = new JButton("Previous");
    private JButton jButton_next = new JButton("Next");

    ArrayList<WizardContentBasePanel> WIZARD_PANEL_DECK =
        new ArrayList<WizardContentBasePanel>();
    int CURRENT_PANEL_NUM = 0;

    public WizardRootPanel() {
        super();
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WizardRootPanel(boolean b) {
        super(b);
    }

    public WizardRootPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public WizardRootPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }

    private void jbInit() throws Exception {
        this.setLayout(null);
        this.setSize(new Dimension(760, 550));
        jButton_previous.setText("Previous");
        jButton_previous.setBounds(new Rectangle(375, 410, 75, 21));
        jButton_previous.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_previous_actionPerformed(e);
                }
            });
        jButton_next.setText("Next");
        jButton_next.setBounds(new Rectangle(470, 410, 75, 21));
        jButton_next.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jButton_next_actionPerformed(e);
                }
            });
        //this.add(dbChartTab, null);

        this.add(jButton_previous, null);
        this.add(jButton_next, null);
        WIZARD_PANEL_DECK.add(new DBConnectPanel());
        WIZARD_PANEL_DECK.add(new SelectDBIdPanel());
        WIZARD_PANEL_DECK.add(new SnapIdSelectPanel());
        WIZARD_PANEL_DECK.add(new AWRQueryPanel());
        WIZARD_PANEL_DECK.add(new ChartPanel());
        this.addWizardPanelToUI(WIZARD_PANEL_DECK.get(0), true);
        this.addWizardPanelToUI(WIZARD_PANEL_DECK.get(1), false);
        this.addWizardPanelToUI(WIZARD_PANEL_DECK.get(2), false);
        this.addWizardPanelToUI(WIZARD_PANEL_DECK.get(3), false);
        this.addWizardPanelToUI(WIZARD_PANEL_DECK.get(4), false);

    }

    private void addWizardPanelToUI(JPanel panel, boolean isVisible) {
        panel.setBounds(new Rectangle(5, 35, 585, 350));
        this.add(panel);
        if (isVisible) {
            panel.setVisible(true);
        } else {
            panel.setVisible(false);
        }
    }

    private void jButton_next_actionPerformed(ActionEvent e) {

        if (CURRENT_PANEL_NUM == 0) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(true);
            WIZARD_PANEL_DECK.get(1).doNextOperation();
            WIZARD_PANEL_DECK.get(2).setVisible(false);
            WIZARD_PANEL_DECK.get(3).setVisible(false);
            WIZARD_PANEL_DECK.get(4).setVisible(false);
            CURRENT_PANEL_NUM = 1;
        } else if (CURRENT_PANEL_NUM == 1) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(false);
            WIZARD_PANEL_DECK.get(2).setVisible(true);
            WIZARD_PANEL_DECK.get(2).doNextOperation();
            WIZARD_PANEL_DECK.get(3).setVisible(false);
            WIZARD_PANEL_DECK.get(4).setVisible(false);
            CURRENT_PANEL_NUM = 2;
        } else if (CURRENT_PANEL_NUM == 2) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(false);
            WIZARD_PANEL_DECK.get(2).setVisible(false);
            WIZARD_PANEL_DECK.get(3).setVisible(true);
            WIZARD_PANEL_DECK.get(3).doNextOperation();
            WIZARD_PANEL_DECK.get(4).setVisible(false);
            CURRENT_PANEL_NUM = 3;
        } else if (CURRENT_PANEL_NUM == 3) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(false);
            WIZARD_PANEL_DECK.get(2).setVisible(false);
            WIZARD_PANEL_DECK.get(3).setVisible(false);
            WIZARD_PANEL_DECK.get(4).setVisible(true);
            WIZARD_PANEL_DECK.get(4).doNextOperation();
            CURRENT_PANEL_NUM = 4;
        }
    }

    private void jButton_previous_actionPerformed(ActionEvent e) {
        if (CURRENT_PANEL_NUM == 0) {
            //do nothing
        } else if (CURRENT_PANEL_NUM == 1) {
            WIZARD_PANEL_DECK.get(0).setVisible(true);
            WIZARD_PANEL_DECK.get(1).setVisible(false);
            WIZARD_PANEL_DECK.get(2).setVisible(false);
            WIZARD_PANEL_DECK.get(3).setVisible(false);
            CURRENT_PANEL_NUM = 0;

        } else if (CURRENT_PANEL_NUM == 2) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(true);
            WIZARD_PANEL_DECK.get(2).setVisible(false);
            WIZARD_PANEL_DECK.get(3).setVisible(false);
            CURRENT_PANEL_NUM = 1;

        } else if (CURRENT_PANEL_NUM == 3) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(false);
            WIZARD_PANEL_DECK.get(2).setVisible(true);
            WIZARD_PANEL_DECK.get(3).setVisible(false);
            CURRENT_PANEL_NUM = 2;
        } else if (CURRENT_PANEL_NUM == 4) {
            WIZARD_PANEL_DECK.get(0).setVisible(false);
            WIZARD_PANEL_DECK.get(1).setVisible(false);
            WIZARD_PANEL_DECK.get(2).setVisible(false);
            WIZARD_PANEL_DECK.get(3).setVisible(true);
            WIZARD_PANEL_DECK.get(4).setVisible(false);
            CURRENT_PANEL_NUM = 3;
        }
    }
}
