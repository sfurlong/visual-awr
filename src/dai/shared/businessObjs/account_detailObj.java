//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;

import org.altaprise.vawr.utils.SessionMetaData;

public class account_detailObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public static final String TRANS_TYPE_CREATE_SHIP = "Create Shipment";
	public static final String TRANS_TYPE_UPDATE_SHIP = "Update Shipment";
	public static final String TRANS_TYPE_RECEIVE_PAY = "Receive Payment";
	public static final String TRANS_TYPE_UPDATE_RECEIPT = "Update Receipt";
        public static final String TRANS_TYPE_DELETE_RECEIPT = "Deleted Receipt";
        public static final String TRANS_TYPE_RECEIVE_INVENTORY = "Receive Inventory";
	public static final String TRANS_TYPE_PAY_BILL = "Pay Bill";
	public static final String TRANS_TYPE_VOID_PAYMENT = "VOID Payment";
	public static final String TRANS_TYPE_GET_EXPENSE_BILL = "Receive Expense Bill";
	public static final String TRANS_TYPE_GET_PURCHORD_BILL = "Receive Invoice";
	public static final String TRANS_TYPE_CREATE_CREDIT_MEMO = "Create Credit Memo";

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
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("id", get_id(), "CHAR", true);
		imutables[1] = new DBAttributes("locality", get_locality(), "CHAR", true);
		return imutables;
	}

	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();

		set_locality(sessionMeta.getLocality());
	}
	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "ACCOUNT_DETAIL";
	static public final String ID = "ACCOUNT_DETAIL.ID";
	static public final String LOCALITY = "ACCOUNT_DETAIL.LOCALITY";
	static public final String DETAIL_ID = "ACCOUNT_DETAIL.DETAIL_ID";
	static public final String TRANS_DATE = "ACCOUNT_DETAIL.TRANS_DATE";
	static public final String TRANS_REF = "ACCOUNT_DETAIL.TRANS_REF";
	static public final String TRANS_TYPE = "ACCOUNT_DETAIL.TRANS_TYPE";
	static public final String TRANS_REF_ACCT = "ACCOUNT_DETAIL.TRANS_REF_ACCT";
	static public final String DEBIT = "ACCOUNT_DETAIL.DEBIT";
	static public final String CREDIT = "ACCOUNT_DETAIL.CREDIT";
	static public final String BALANCE = "ACCOUNT_DETAIL.BALANCE";
	static public final String RECONCILE_STATUS = "ACCOUNT_DETAIL.RECONCILE_STATUS";
	static public final String NOTE1 = "ACCOUNT_DETAIL.NOTE1";
	static public final String USER1 = "ACCOUNT_DETAIL.USER1";
	static public final String USER2 = "ACCOUNT_DETAIL.USER2";
	static public final String USER3 = "ACCOUNT_DETAIL.USER3";
	static public final String USER4 = "ACCOUNT_DETAIL.USER4";
	static public final String USER5 = "ACCOUNT_DETAIL.USER5";


	//Constructor
	public account_detailObj()
	{
		//Private Field Members.
		MAX_COLS = 17;
		TAB_NAME = "ACCOUNT_DETAIL";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", true);
		m_dbAttribs[3] = new DBAttributes("TRANS_DATE", null, "DATE", true);
		m_dbAttribs[4] = new DBAttributes("TRANS_REF", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("TRANS_TYPE", null, "VARCHAR", true);
		m_dbAttribs[6] = new DBAttributes("TRANS_REF_ACCT", null, "VARCHAR", true);
		m_dbAttribs[7] = new DBAttributes("DEBIT", null, "NUMERIC", true);
		m_dbAttribs[8] = new DBAttributes("CREDIT", null, "NUMERIC", true);
		m_dbAttribs[9] = new DBAttributes("BALANCE", null, "NUMERIC", true);
		m_dbAttribs[10] = new DBAttributes("RECONCILE_STATUS", null, "VARCHAR", true);
		m_dbAttribs[11] = new DBAttributes("NOTE1", null, "VARCHAR", true);
		m_dbAttribs[12] = new DBAttributes("USER1", null, "VARCHAR", true);
		m_dbAttribs[13] = new DBAttributes("USER2", null, "VARCHAR", true);
		m_dbAttribs[14] = new DBAttributes("USER3", null, "VARCHAR", true);
		m_dbAttribs[15] = new DBAttributes("USER4", null, "VARCHAR", true);
		m_dbAttribs[16] = new DBAttributes("USER5", null, "VARCHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_trans_date() {return m_dbAttribs[3].getValue();}
	public String get_trans_ref() {return m_dbAttribs[4].getValue();}
	public String get_trans_type() {return m_dbAttribs[5].getValue();}
	public String get_trans_ref_acct() {return m_dbAttribs[6].getValue();}
	public String get_debit() {return m_dbAttribs[7].getValue();}
	public String get_credit() {return m_dbAttribs[8].getValue();}
	public String get_balance() {return m_dbAttribs[9].getValue();}
	public String get_reconcile_status() {return m_dbAttribs[10].getValue();}
	public String get_note1() {return m_dbAttribs[11].getValue();}
	public String get_user1() {return m_dbAttribs[12].getValue();}
	public String get_user2() {return m_dbAttribs[13].getValue();}
	public String get_user3() {return m_dbAttribs[14].getValue();}
	public String get_user4() {return m_dbAttribs[15].getValue();}
	public String get_user5() {return m_dbAttribs[16].getValue();}
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

	public void set_detail_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_trans_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_trans_ref(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_trans_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_trans_ref_acct(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_debit(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_credit(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_balance(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_reconcile_status(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_user5(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new account_detailObj();
	}


}
