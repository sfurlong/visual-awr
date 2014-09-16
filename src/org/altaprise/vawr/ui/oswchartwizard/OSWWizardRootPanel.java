package org.altaprise.vawr.ui.oswchartwizard;

import java.util.ArrayList;

import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.ui.common.WizardRootPanel;

public class OSWWizardRootPanel extends WizardRootPanel {

    public OSWWizardRootPanel() {

        super();

        ArrayList<WizardContentBasePanel> wizardPanels = new ArrayList<WizardContentBasePanel>();
        wizardPanels.add(new OSWChartPanel());

        setWizardPanels(wizardPanels);
    }

    protected void setWizardPanels(ArrayList<WizardContentBasePanel> wizardPanels) {
        WIZARD_PANEL_DECK = wizardPanels;
        try {
            super.jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
