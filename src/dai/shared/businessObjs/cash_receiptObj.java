//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;

import java.util.Calendar;

import org.altaprise.vawr.utils.SessionMetaData;

public class cash_receiptObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

	public void clear(boolean clearImmutables)
	{
		String locality = get_locality();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_locality(locality);
		}
	}

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[1];
		imutables[0] = new DBAttributes("locality", get_locality(), "VARCHAR", true);
		return imutables;
	}

	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		//Add the current date to the Date Created Field.
		Calendar now = Calendar.getInstance();
		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();

		set_date_created(now.get(now.MONTH)+1 + "/" +
						 now.get(now.DAY_OF_MONTH) + "/" +
						 now.get(now.YEAR));

		set_locality(sessionMeta.getLocality());

		set_created_by(sessionMeta.getUserId());
	}

    //Transient field.  Not stored in the DB.
    static public final String _IS_PREPAID_ORDER = "_IS_PREPAID_ORDER";

	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "CASH_RECEIPT";
	static public final String ID = "CASH_RECEIPT.ID";
	static public final String LOCALITY = "CASH_RECEIPT.LOCALITY";
	static public final String CREATED_BY = "CASH_RECEIPT.CREATED_BY";
	static public final String DATE_CREATED = "CASH_RECEIPT.DATE_CREATED";
	static public final String CHECK_NUM = "CASH_RECEIPT.CHECK_NUM";
	static public final String PAYMENT_METHOD = "CASH_RECEIPT.PAYMENT_METHOD";
	static public final String CC_NUM = "CASH_RECEIPT.CC_NUM";
	static public final String DATE_RECEIVED = "CASH_RECEIPT.DATE_RECEIVED";
	static public final String NOTE = "CASH_RECEIPT.NOTE";
	static public final String CC_EXP_DATE = "CASH_RECEIPT.CC_EXP_DATE";
	static public final String RECEIVABLE_ACCT_ID = "CASH_RECEIPT.RECEIVABLE_ACCT_ID";
	static public final String RECEIVABLE_ACCT_NAME = "CASH_RECEIPT.RECEIVABLE_ACCT_NAME";
	static public final String DEPOSIT_ACCT_ID = "CASH_RECEIPT.DEPOSIT_ACCT_ID";
	static public final String DEPOSIT_ACCT_NAME = "CASH_RECEIPT.DEPOSIT_ACCT_NAME";
	static public final String SHIPMENT_ID = "CASH_RECEIPT.SHIPMENT_ID";
	static public final String CUST_ID = "CASH_RECEIPT.CUST_ID";
	static public final String CUST_NAME = "CASH_RECEIPT.CUST_NAME";
	static public final String PAYMENT_AMT = "CASH_RECEIPT.PAYMENT_AMT";
	static public final String INVOICE_DATE = "CASH_RECEIPT.INVOICE_DATE";
        static public final String TOTAL_PAID = "CASH_RECEIPT.TOTAL_PAID";
        static public final String TOTAL_DUE = "CASH_RECEIPT.TOTAL_DUE";


	//Constructor
	public cash_receiptObj()
	{
		//Private Field Members.
		MAX_COLS = 21;
		TAB_NAME = "CASH_RECEIPT";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[3] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[4] = new DBAttributes("CHECK_NUM", null, "VARCHAR", 20 ,true);
		m_dbAttribs[5] = new DBAttributes("PAYMENT_METHOD", null, "VARCHAR", 20 ,true);
		m_dbAttribs[6] = new DBAttributes("CC_NUM", null, "VARCHAR", 20 ,true);
		m_dbAttribs[7] = new DBAttributes("DATE_RECEIVED", null, "DATE", 19 ,true);
		m_dbAttribs[8] = new DBAttributes("NOTE", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[9] = new DBAttributes("CC_EXP_DATE", null, "VARCHAR", 12 ,true);
		m_dbAttribs[10] = new DBAttributes("RECEIVABLE_ACCT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[11] = new DBAttributes("RECEIVABLE_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("DEPOSIT_ACCT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[13] = new DBAttributes("DEPOSIT_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[14] = new DBAttributes("SHIPMENT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[15] = new DBAttributes("CUST_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[16] = new DBAttributes("CUST_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[17] = new DBAttributes("PAYMENT_AMT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[18] = new DBAttributes("INVOICE_DATE", null, "DATE", 19 ,true);
                m_dbAttribs[19] = new DBAttributes("TOTAL_PAID", null, "NUMERIC", 0 ,true);
                m_dbAttribs[20] = new DBAttributes("TOTAL_DUE", null, "NUMERIC", 0 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_created_by() {return m_dbAttribs[2].getValue();}
	public String get_date_created() {return m_dbAttribs[3].getValue();}
	public String get_check_num() {return m_dbAttribs[4].getValue();}
	public String get_payment_method() {return m_dbAttribs[5].getValue();}
	public String get_cc_num() {return m_dbAttribs[6].getValue();}
	public String get_date_received() {return m_dbAttribs[7].getValue();}
	public String get_note() {return m_dbAttribs[8].getValue();}
	public String get_cc_exp_date() {return m_dbAttribs[9].getValue();}
	public String get_receivable_acct_id() {return m_dbAttribs[10].getValue();}
	public String get_receivable_acct_name() {return m_dbAttribs[11].getValue();}
	public String get_deposit_acct_id() {return m_dbAttribs[12].getValue();}
	public String get_deposit_acct_name() {return m_dbAttribs[13].getValue();}
	public String get_shipment_id() {return m_dbAttribs[14].getValue();}
	public String get_cust_id() {return m_dbAttribs[15].getValue();}
	public String get_cust_name() {return m_dbAttribs[16].getValue();}
	public String get_payment_amt() {return m_dbAttribs[17].getValue();}
	public String get_invoice_date() {return m_dbAttribs[18].getValue();}
	public String get_total_paid() {return m_dbAttribs[19].getValue();}
        public String get_total_due() {return m_dbAttribs[20].getValue();}
        public void set_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[0].setValue(null);
		else
			m_dbAttribs[0].setValue(val);
	}

	public void set_locality(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[1].setValue(null);
		else
			m_dbAttribs[1].setValue(val);
	}

	public void set_created_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_check_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_payment_method(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_cc_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_date_received(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_cc_exp_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_receivable_acct_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_receivable_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_deposit_acct_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_deposit_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_shipment_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_cust_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_cust_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_payment_amt(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_invoice_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_total_paid(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

        public void set_total_due(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new cash_receiptObj();
	}


}
