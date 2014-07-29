package org.altaprise.vawr.ui.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import java.awt.Rectangle;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.altaprise.vawr.ui.dbchartwizard.AWRQueryPanel;
import org.altaprise.vawr.ui.dbchartwizard.ChartPanel;
import org.altaprise.vawr.ui.dbchartwizard.DBConnectPanel;
import org.altaprise.vawr.ui.dbchartwizard.SelectDBIdPanel;
import org.altaprise.vawr.ui.dbchartwizard.SnapIdSelectPanel;


public abstract class WizardRootPanel extends JPanel {

    private JButton jButton_previous = new JButton("Previous");
    private JButton jButton_next = new JButton("Next");

    protected ArrayList<WizardContentBasePanel> WIZARD_PANEL_DECK = new ArrayList<WizardContentBasePanel>();
    int CURRENT_PANEL_NUM = 0;
    private JPanel jPanel_panelTitle = new JPanel();
    private JPanel jPanel_panelContent = new JPanel();
    private JLabel jLabel_panelTitle = new JLabel();
    private JPanel jPanel_panelNavigation = new JPanel();

    public WizardRootPanel() {
        super();
    }
        
    abstract protected void setWizardPanels(ArrayList<WizardContentBasePanel> wizardPanels);

    private WizardRootPanel(boolean b) {
        super(b);
    }

    private WizardRootPanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    private WizardRootPanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }
        
    protected void jbInit() throws Exception {
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);
        //this.setSize(new Dimension(760, 550));
        this.setSize(new Dimension(400, 300));
        jButton_previous.setText("Previous");
        jButton_previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_previous_actionPerformed(e);
            }
        });
        jButton_next.setText("Next");
        jButton_next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_next_actionPerformed(e);
            }
        });

        jPanel_panelTitle.setBackground(new Color(247, 247, 247));
        jPanel_panelTitle.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jPanel_panelTitle.setMinimumSize(new Dimension(48, 50));
        jPanel_panelTitle.setPreferredSize(new Dimension(48, 50));
        jPanel_panelTitle.setLayout(null);
        jLabel_panelTitle.setText("jLabel1");
        jLabel_panelTitle.setBounds(new Rectangle(15, 10, 530, 30));
        jLabel_panelTitle.setFont(new Font("Arial", 1, 16));
        jPanel_panelNavigation.setBounds(new Rectangle(0, 500, 760, 50));
        jPanel_panelNavigation.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        jPanel_panelNavigation.add(jButton_previous, null);
        jPanel_panelNavigation.add(jButton_next, null);
        jPanel_panelContent.setBackground(new Color(247, 200, 147));
        jPanel_panelContent = WIZARD_PANEL_DECK.get(0);
        this.add(this.jPanel_panelContent, BorderLayout.CENTER);
        jPanel_panelTitle.add(jLabel_panelTitle, null);
        this.add(jPanel_panelTitle, BorderLayout.NORTH);
        this.add(jPanel_panelNavigation, BorderLayout.SOUTH);
        jLabel_panelTitle.setText(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM).getPanelLabel());
        jButton_previous.setEnabled(false);
    }

    
    private void jButton_next_actionPerformed(ActionEvent e) {

        if (CURRENT_PANEL_NUM < WIZARD_PANEL_DECK.size()-1) {
            jButton_next.setEnabled(true);
            jButton_previous.setEnabled(true);
            this.remove(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM));
            CURRENT_PANEL_NUM++;
            this.add(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM), BorderLayout.CENTER);
            jLabel_panelTitle.setText(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM).getPanelLabel());
            this.revalidate();
            this.repaint();
            WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM).doNextOperation();
        } else {
            jButton_next.setEnabled(false);
        }
    }

    private void jButton_previous_actionPerformed(ActionEvent e) {

        if (CURRENT_PANEL_NUM > 0) {
            jButton_previous.setEnabled(true);
            jButton_next.setEnabled(true);
            this.remove(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM));
            CURRENT_PANEL_NUM--;
            this.add(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM), BorderLayout.CENTER);
            jLabel_panelTitle.setText(WIZARD_PANEL_DECK.get(CURRENT_PANEL_NUM).getPanelLabel());
            this.revalidate();
            this.repaint();
        } else {
            jButton_previous.setEnabled(false);
        }

    }
}
