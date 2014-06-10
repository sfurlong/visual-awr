package dai.shared.cmnSvcs;

import java.util.Vector;

public  class daiSessionSecurity
{
    //The following is the list of all the component Ids in the system.
    //If security is to be added to any component in the system, an Id
    //must be defined here.  All Ids must follow the pattern described
    //below.  Ensure that all component Ids are grouped by the module that
    //contains the component.
    private Vector components = new Vector();

    public daiSessionSecurity() {
        components.addElement("OrderFrame");
        components.addElement("CreateShipmentWizardFrame");
        components.addElement("ShipmentFrame");
        components.addElement("CashReceiptFrame");

        //Document Printing Security
        components.addElement("PrintPackSlipDoc");
        components.addElement("PrintSalesInvoiceDoc");
        components.addElement("PrintOrderAckDoc");
        components.addElement("PrintPurchOrderDoc");
        components.addElement("PrintCheckDoc");
        components.addElement("PrintCreditMemoDoc");
        components.addElement("PrintCustStmtDoc");
        components.addElement("PrintQuoteDoc");
        components.addElement("PrintProspectLabelDoc");

        components.addElement("PurchOrderFrame");
        components.addElement("ReceiveInventoryFrame");
        components.addElement("InvoiceReceiptFrame");
        components.addElement("BillReceiptFrame");
        components.addElement("PayBillsFrame");
        components.addElement("PayVoucherFrame");
        components.addElement("customerFrame");
        components.addElement("vendorFrame");
        components.addElement("carrierFrame");
        components.addElement("ItemFrame");
        components.addElement("FinanceAcctsFrame");
        components.addElement("DefaultAccountsFrame");
        components.addElement("SystemAdminFrame");
        components.addElement("LocationFrame");
        components.addElement("UserProfileFrame");
        components.addElement("ReceiptFrame");
        components.addElement("daiWebRptFrame");
        components.addElement("UpdateShipmentFrame");
        components.addElement("CreateCreditMemoFrame");
        components.addElement("InventoryAdjustFrame");
        components.addElement("ProspectFrame");
        components.addElement("QuoteFrame");
    }

    public Vector getAllSecurityComponents() {
        return components;
    }
}
