//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;


public class accountObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

	//Types of Accounts
	static public final String ACCT_TYPE_BANK = "BANK";
	static public final String ACCT_TYPE_ACCT_REC = "RECEIVABLES";
	static public final String ACCT_TYPE_ASSET = "ASSET";
	static public final String ACCT_TYPE_ACCT_PAY = "PAYABLES";
	static public final String ACCT_TYPE_CREDIT_CRD = "CREDIT_CARD";
	static public final String ACCT_TYPE_LIABILITY = "LIABILITY";
	static public final String ACCT_TYPE_EQUITY = "EQUITY";
	static public final String ACCT_TYPE_INCOME = "INCOME";
	static public final String ACCT_TYPE_COGS = "COGS";
	static public final String ACCT_TYPE_EXPENSE = "EXPENSE";
	static public final String ACCT_TYPE_NON_POST = "NON_POSTING";

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[1];
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		return imutables;
	}

	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		set_locality(this.getObjLocality());
	}

	public void clear(boolean clearImmutables)
	{
		String id = get_id();
		String locality = get_locality();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_id(id);
			set_locality(locality);
		}
	}
	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "ACCOUNT";
	static public final String ID = "ACCOUNT.ID";
	static public final String LOCALITY = "ACCOUNT.LOCALITY";
	static public final String ACCOUNT_TYPE = "ACCOUNT.ACCOUNT_TYPE";
	static public final String IS_SUBACCOUNT = "ACCOUNT.IS_SUBACCOUNT";
	static public final String SUBACCOUNT = "ACCOUNT.SUBACCOUNT";
	static public final String DESCRIPTION = "ACCOUNT.DESCRIPTION";
	static public final String BANK_NUM = "ACCOUNT.BANK_NUM";
	static public final String TAX_CATEGORY = "ACCOUNT.TAX_CATEGORY";
	static public final String OPENING_BALANCE = "ACCOUNT.OPENING_BALANCE";
	static public final String OPENING_BALANCE_DATE = "ACCOUNT.OPENING_BALANCE_DATE";
	static public final String NOTE = "ACCOUNT.NOTE";
	static public final String USER1 = "ACCOUNT.USER1";
	static public final String USER2 = "ACCOUNT.USER2";
	static public final String USER3 = "ACCOUNT.USER3";
	static public final String USER4 = "ACCOUNT.USER4";
	static public final String USER5 = "ACCOUNT.USER5";
	static public final String ACCOUNT_CATEGORY = "ACCOUNT.ACCOUNT_CATEGORY";
	static public final String DATE_CREATED = "ACCOUNT.DATE_CREATED";
	static public final String CREATED_BY = "ACCOUNT.CREATED_BY";
	static public final String IS_NOT_ACTIVE = "ACCOUNT.IS_NOT_ACTIVE";


	//Constructor
	public accountObj()
	{
		//Private Field Members.
		MAX_COLS = 20;
		TAB_NAME = "ACCOUNT";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("ACCOUNT_TYPE", null, "VARCHAR", 20 ,true);
		m_dbAttribs[3] = new DBAttributes("IS_SUBACCOUNT", null, "VARCHAR", 1 ,true);
		m_dbAttribs[4] = new DBAttributes("SUBACCOUNT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[5] = new DBAttributes("DESCRIPTION", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[6] = new DBAttributes("BANK_NUM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[7] = new DBAttributes("TAX_CATEGORY", null, "VARCHAR", 30 ,true);
		m_dbAttribs[8] = new DBAttributes("OPENING_BALANCE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[9] = new DBAttributes("OPENING_BALANCE_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[10] = new DBAttributes("NOTE", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[11] = new DBAttributes("USER1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[12] = new DBAttributes("USER2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[13] = new DBAttributes("USER3", null, "VARCHAR", 30 ,true);
		m_dbAttribs[14] = new DBAttributes("USER4", null, "DATE", 19 ,true);
		m_dbAttribs[15] = new DBAttributes("USER5", null, "DATE", 19 ,true);
		m_dbAttribs[16] = new DBAttributes("ACCOUNT_CATEGORY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[17] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[18] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[19] = new DBAttributes("IS_NOT_ACTIVE", null, "CHAR", 1 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_account_type() {return m_dbAttribs[2].getValue();}
	public String get_is_subaccount() {return m_dbAttribs[3].getValue();}
	public String get_subaccount() {return m_dbAttribs[4].getValue();}
	public String get_description() {return m_dbAttribs[5].getValue();}
	public String get_bank_num() {return m_dbAttribs[6].getValue();}
	public String get_tax_category() {return m_dbAttribs[7].getValue();}
	public String get_opening_balance() {return m_dbAttribs[8].getValue();}
	public String get_opening_balance_date() {return m_dbAttribs[9].getValue();}
	public String get_note() {return m_dbAttribs[10].getValue();}
	public String get_user1() {return m_dbAttribs[11].getValue();}
	public String get_user2() {return m_dbAttribs[12].getValue();}
	public String get_user3() {return m_dbAttribs[13].getValue();}
	public String get_user4() {return m_dbAttribs[14].getValue();}
	public String get_user5() {return m_dbAttribs[15].getValue();}
	public String get_account_category() {return m_dbAttribs[16].getValue();}
	public String get_date_created() {return m_dbAttribs[17].getValue();}
	public String get_created_by() {return m_dbAttribs[18].getValue();}
	public String get_is_not_active() {return m_dbAttribs[19].getValue();}
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

	public void set_account_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_is_subaccount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_subaccount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_description(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_bank_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_tax_category(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_opening_balance(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_opening_balance_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_user5(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_account_category(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_created_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_is_not_active(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new accountObj();
	}


}
