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

public class global_settings_pay_methodsObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		//Add the current date to the Date Created Field.
		Calendar now = Calendar.getInstance();
		SessionMetaData sessionMeta = SessionMetaData.getInstance();
		set_locality(sessionMeta.getLocality());

	}

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

    final public static String PAY_METHOD_TYPE_CREDIT_CARD = "CREDIT CARD";
    final public static String PAY_METHOD_TYPE_CREDIT_MEMO = "CREDIT MEMO";
    final public static String PAY_METHOD_TYPE_CHECK = "CHECK";
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "GLOBAL_SETTINGS_PAY_METHODS";
	static public final String ID = "GLOBAL_SETTINGS_PAY_METHODS.ID";
	static public final String LOCALITY = "GLOBAL_SETTINGS_PAY_METHODS.LOCALITY";
	static public final String DETAIL_ID = "GLOBAL_SETTINGS_PAY_METHODS.DETAIL_ID";
	static public final String PAY_METHOD_ID = "GLOBAL_SETTINGS_PAY_METHODS.PAY_METHOD_ID";
	static public final String PAY_METHOD_TYPE = "GLOBAL_SETTINGS_PAY_METHODS.PAY_METHOD_TYPE";
	static public final String PAY_TYPE_COMMISION_PCT = "GLOBAL_SETTINGS_PAY_METHODS.PAY_TYPE_COMMISION_PCT";


	//Constructor
	public global_settings_pay_methodsObj()
	{
		//Private Field Members.
		MAX_COLS = 6;
		TAB_NAME = "GLOBAL_SETTINGS_PAY_METHODS";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", 10 ,true);
		m_dbAttribs[3] = new DBAttributes("PAY_METHOD_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[4] = new DBAttributes("PAY_METHOD_TYPE", null, "VARCHAR", 20 ,true);
		m_dbAttribs[5] = new DBAttributes("PAY_TYPE_COMMISION_PCT", null, "NUMERIC", 0 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_pay_method_id() {return m_dbAttribs[3].getValue();}
	public String get_pay_method_type() {return m_dbAttribs[4].getValue();}
	public String get_pay_type_commision_pct() {return m_dbAttribs[5].getValue();}
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

	public void set_pay_method_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_pay_method_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_pay_type_commision_pct(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new global_settings_pay_methodsObj();
	}


}
