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

public class carrierObj extends BusinessObject
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
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		return imutables;
	}

	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		//Add the current date to the Date Created Field.
		Calendar now = Calendar.getInstance();
		SessionMetaData sessionMeta = SessionMetaData.getInstance();

		set_date_created(now.get(now.MONTH)+1 + "/" +
						 now.get(now.DAY_OF_MONTH) + "/" +
						 now.get(now.YEAR));

		set_locality(this.getObjLocality());

		set_created_by(sessionMeta.getUserId());
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "CARRIER";
	static public final String ID = "CARRIER.ID";
	static public final String LOCALITY = "CARRIER.LOCALITY";
	static public final String NAME = "CARRIER.NAME";
	static public final String ADDR1 = "CARRIER.ADDR1";
	static public final String ADDR2 = "CARRIER.ADDR2";
	static public final String ADDR3 = "CARRIER.ADDR3";
	static public final String ADDR4 = "CARRIER.ADDR4";
	static public final String CITY = "CARRIER.CITY";
	static public final String STATE_CODE = "CARRIER.STATE_CODE";
	static public final String STATE_NAME = "CARRIER.STATE_NAME";
	static public final String ZIP = "CARRIER.ZIP";
	static public final String COUNTRY_NAME = "CARRIER.COUNTRY_NAME";
	static public final String COUNTRY_CODE = "CARRIER.COUNTRY_CODE";
	static public final String CONTACT = "CARRIER.CONTACT";
	static public final String TITLE = "CARRIER.TITLE";
	static public final String PHONE = "CARRIER.PHONE";
	static public final String FAX = "CARRIER.FAX";
	static public final String NOTE = "CARRIER.NOTE";
	static public final String ATTN = "CARRIER.ATTN";
	static public final String CARRIER_ACCT_NUM = "CARRIER.CARRIER_ACCT_NUM";
	static public final String DATE_CREATED = "CARRIER.DATE_CREATED";
	static public final String CREATED_BY = "CARRIER.CREATED_BY";
	static public final String PRIORITY = "CARRIER.PRIORITY";


	//Constructor
	public carrierObj()
	{
		//Private Field Members.
		MAX_COLS = 23;
		TAB_NAME = "CARRIER";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[3] = new DBAttributes("ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[4] = new DBAttributes("ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[6] = new DBAttributes("ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[7] = new DBAttributes("CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("STATE_CODE", null, "CHAR", 2 ,true);
		m_dbAttribs[9] = new DBAttributes("STATE_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[10] = new DBAttributes("ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[11] = new DBAttributes("COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[12] = new DBAttributes("COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[13] = new DBAttributes("CONTACT", null, "VARCHAR", 25 ,true);
		m_dbAttribs[14] = new DBAttributes("TITLE", null, "VARCHAR", 25 ,true);
		m_dbAttribs[15] = new DBAttributes("PHONE", null, "VARCHAR", 18 ,true);
		m_dbAttribs[16] = new DBAttributes("FAX", null, "VARCHAR", 18 ,true);
		m_dbAttribs[17] = new DBAttributes("NOTE", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[18] = new DBAttributes("ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[19] = new DBAttributes("CARRIER_ACCT_NUM", null, "VARCHAR", 50 ,true);
		m_dbAttribs[20] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[21] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[22] = new DBAttributes("PRIORITY", null, "INTEGER", 10 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_name() {return m_dbAttribs[2].getValue();}
	public String get_addr1() {return m_dbAttribs[3].getValue();}
	public String get_addr2() {return m_dbAttribs[4].getValue();}
	public String get_addr3() {return m_dbAttribs[5].getValue();}
	public String get_addr4() {return m_dbAttribs[6].getValue();}
	public String get_city() {return m_dbAttribs[7].getValue();}
	public String get_state_code() {return m_dbAttribs[8].getValue();}
	public String get_state_name() {return m_dbAttribs[9].getValue();}
	public String get_zip() {return m_dbAttribs[10].getValue();}
	public String get_country_name() {return m_dbAttribs[11].getValue();}
	public String get_country_code() {return m_dbAttribs[12].getValue();}
	public String get_contact() {return m_dbAttribs[13].getValue();}
	public String get_title() {return m_dbAttribs[14].getValue();}
	public String get_phone() {return m_dbAttribs[15].getValue();}
	public String get_fax() {return m_dbAttribs[16].getValue();}
	public String get_note() {return m_dbAttribs[17].getValue();}
	public String get_attn() {return m_dbAttribs[18].getValue();}
	public String get_carrier_acct_num() {return m_dbAttribs[19].getValue();}
	public String get_date_created() {return m_dbAttribs[20].getValue();}
	public String get_created_by() {return m_dbAttribs[21].getValue();}
	public String get_priority() {return m_dbAttribs[22].getValue();}
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

	public void set_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_state_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_contact(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_title(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_phone(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_fax(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_carrier_acct_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_created_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_priority(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new carrierObj();
	}


}
