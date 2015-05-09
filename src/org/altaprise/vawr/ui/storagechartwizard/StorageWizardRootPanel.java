package org.altaprise.vawr.ui.storagechartwizard;

import java.util.ArrayList;

import org.altaprise.vawr.ui.common.WizardContentBasePanel;
import org.altaprise.vawr.ui.common.WizardRootPanel;

public class StorageWizardRootPanel extends WizardRootPanel {

    public StorageWizardRootPanel() {

        super();

        ArrayList<WizardContentBasePanel> wizardPanels = new ArrayList<WizardContentBasePanel>();
        wizardPanels.add(new DBConnectPanel());
        wizardPanels.add(new StorageQueryPanel());
        wizardPanels.add(new ChartStoragePanel());

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
