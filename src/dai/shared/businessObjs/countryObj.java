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

public class countryObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public void setDefaults()
	{
		//Add the current date to the Date Created Field.
		Calendar now = Calendar.getInstance();
		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();

		set_locality(sessionMeta.getLocality());

		set_date_created(now.get(now.MONTH)+1 + "/" +
						 now.get(now.DAY_OF_MONTH) + "/" +
						 now.get(now.YEAR));

		set_created_by(sessionMeta.getUserId());
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
		DBAttributes imutables[] = new DBAttributes[1];
		imutables[0] = new DBAttributes("locality", get_locality(), "VARCHAR", true);
		return imutables;
	}
	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "COUNTRY";
	static public final String ID = "COUNTRY.ID";
	static public final String LOCALITY = "COUNTRY.LOCALITY";
	static public final String CREATED_BY = "COUNTRY.CREATED_BY";
	static public final String DATE_CREATED = "COUNTRY.DATE_CREATED";
	static public final String NAME = "COUNTRY.NAME";
	static public final String EXCHANGE_RATE = "COUNTRY.EXCHANGE_RATE";
	static public final String CURRENTY = "COUNTRY.CURRENTY";
	static public final String NOTE1 = "COUNTRY.NOTE1";
	static public final String USER1 = "COUNTRY.USER1";
	static public final String USER2 = "COUNTRY.USER2";
	static public final String USER3 = "COUNTRY.USER3";
	static public final String USER4 = "COUNTRY.USER4";
	static public final String USER5 = "COUNTRY.USER5";


	//Constructor
	public countryObj()
	{
		//Private Field Members.
		MAX_COLS = 13;
		TAB_NAME = "COUNTRY";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("CREATED_BY", null, "VARCHAR", true);
		m_dbAttribs[3] = new DBAttributes("DATE_CREATED", null, "DATE", true);
		m_dbAttribs[4] = new DBAttributes("NAME", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("EXCHANGE_RATE", null, "NUMERIC", true);
		m_dbAttribs[6] = new DBAttributes("CURRENTY", null, "VARCHAR", true);
		m_dbAttribs[7] = new DBAttributes("NOTE1", null, "VARCHAR", true);
		m_dbAttribs[8] = new DBAttributes("USER1", null, "VARCHAR", true);
		m_dbAttribs[9] = new DBAttributes("USER2", null, "VARCHAR", true);
		m_dbAttribs[10] = new DBAttributes("USER3", null, "VARCHAR", true);
		m_dbAttribs[11] = new DBAttributes("USER4", null, "DATE", true);
		m_dbAttribs[12] = new DBAttributes("USER5", null, "DATE", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_created_by() {return m_dbAttribs[2].getValue();}
	public String get_date_created() {return m_dbAttribs[3].getValue();}
	public String get_name() {return m_dbAttribs[4].getValue();}
	public String get_exchange_rate() {return m_dbAttribs[5].getValue();}
	public String get_currenty() {return m_dbAttribs[6].getValue();}
	public String get_note1() {return m_dbAttribs[7].getValue();}
	public String get_user1() {return m_dbAttribs[8].getValue();}
	public String get_user2() {return m_dbAttribs[9].getValue();}
	public String get_user3() {return m_dbAttribs[10].getValue();}
	public String get_user4() {return m_dbAttribs[11].getValue();}
	public String get_user5() {return m_dbAttribs[12].getValue();}
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

	public void set_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_exchange_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_currenty(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_user5(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new countryObj();
	}


}
