package org.altaprise.vawr.ui.dbwizard;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class WizardContentBasePanel extends JPanel {
    
    private String panelLabel = "";
    
    public WizardContentBasePanel() {
        super();
    }

    public WizardContentBasePanel(boolean b) {
        super(b);
    }

    public WizardContentBasePanel(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public WizardContentBasePanel(LayoutManager layoutManager, boolean b) {
        super(layoutManager, b);
    }
    
    public String getPanelLabel () {
        return panelLabel;    
    }

    private void jbInit() throws Exception {
        
    }
    
    protected void doNextOperation() {
        
    }
    
    protected void setPanelLabel (String label) {
        panelLabel = label;    
    }

    
    private void refreshPanel() {
        
    }
    
}
