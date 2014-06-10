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

public class prospectObj extends BusinessObject
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
		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();

		set_date_created(now.get(now.MONTH)+1 + "/" +
						 now.get(now.DAY_OF_MONTH) + "/" +
						 now.get(now.YEAR));

		set_locality(sessionMeta.getLocality());

		set_created_by(sessionMeta.getUserId());
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "PROSPECT";
	static public final String ID = "PROSPECT.ID";
	static public final String LOCALITY = "PROSPECT.LOCALITY";
	static public final String DATE_CREATED = "PROSPECT.DATE_CREATED";
	static public final String CREATED_BY = "PROSPECT.CREATED_BY";
	static public final String FIRST_NAME = "PROSPECT.FIRST_NAME";
	static public final String LAST_NAME = "PROSPECT.LAST_NAME";
	static public final String TITLE = "PROSPECT.TITLE";
	static public final String COMPANY_NAME = "PROSPECT.COMPANY_NAME";
	static public final String ADDR1 = "PROSPECT.ADDR1";
	static public final String ADDR2 = "PROSPECT.ADDR2";
	static public final String ADDR3 = "PROSPECT.ADDR3";
	static public final String ADDR4 = "PROSPECT.ADDR4";
	static public final String CITY = "PROSPECT.CITY";
	static public final String STATE_CODE = "PROSPECT.STATE_CODE";
	static public final String ZIP = "PROSPECT.ZIP";
	static public final String COUNTRY_CODE = "PROSPECT.COUNTRY_CODE";
	static public final String COUNTRY_NAME = "PROSPECT.COUNTRY_NAME";
	static public final String EMAIL = "PROSPECT.EMAIL";
	static public final String WEB = "PROSPECT.WEB";
	static public final String PRIORITY = "PROSPECT.PRIORITY";
	static public final String REFERED_BY = "PROSPECT.REFERED_BY";
	static public final String IS_CUSTOMER = "PROSPECT.IS_CUSTOMER";
	static public final String SEND_PRINT = "PROSPECT.SEND_PRINT";
	static public final String SEND_CD = "PROSPECT.SEND_CD";
	static public final String USER1 = "PROSPECT.USER1";
	static public final String USER2 = "PROSPECT.USER2";
	static public final String USER3 = "PROSPECT.USER3";
	static public final String USER4 = "PROSPECT.USER4";
	static public final String NOTE1 = "PROSPECT.NOTE1";
	static public final String NOTE2 = "PROSPECT.NOTE2";
	static public final String CUSTOMER_ID = "PROSPECT.CUSTOMER_ID";
	static public final String PHONE = "PROSPECT.PHONE";
	static public final String FAX = "PROSPECT.FAX";


	//Constructor
	public prospectObj()
	{
		//Private Field Members.
		MAX_COLS = 33;
		TAB_NAME = "PROSPECT";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("FIRST_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("LAST_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[6] = new DBAttributes("TITLE", null, "VARCHAR", 50 ,true);
		m_dbAttribs[7] = new DBAttributes("COMPANY_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[9] = new DBAttributes("ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[10] = new DBAttributes("ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[11] = new DBAttributes("ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[13] = new DBAttributes("STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[14] = new DBAttributes("ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[15] = new DBAttributes("COUNTRY_CODE", null, "CHAR", 2 ,true);
		m_dbAttribs[16] = new DBAttributes("COUNTRY_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[17] = new DBAttributes("EMAIL", null, "VARCHAR", 50 ,true);
		m_dbAttribs[18] = new DBAttributes("WEB", null, "VARCHAR", 50 ,true);
		m_dbAttribs[19] = new DBAttributes("PRIORITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[20] = new DBAttributes("REFERED_BY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[21] = new DBAttributes("IS_CUSTOMER", null, "CHAR", 1 ,true);
		m_dbAttribs[22] = new DBAttributes("SEND_PRINT", null, "CHAR", 1 ,true);
		m_dbAttribs[23] = new DBAttributes("SEND_CD", null, "CHAR", 1 ,true);
		m_dbAttribs[24] = new DBAttributes("USER1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[25] = new DBAttributes("USER2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[26] = new DBAttributes("USER3", null, "VARCHAR", 30 ,true);
		m_dbAttribs[27] = new DBAttributes("USER4", null, "VARCHAR", 30 ,true);
		m_dbAttribs[28] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[29] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[30] = new DBAttributes("CUSTOMER_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[31] = new DBAttributes("PHONE", null, "VARCHAR", 25 ,true);
		m_dbAttribs[32] = new DBAttributes("FAX", null, "VARCHAR", 25 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_date_created() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_first_name() {return m_dbAttribs[4].getValue();}
	public String get_last_name() {return m_dbAttribs[5].getValue();}
	public String get_title() {return m_dbAttribs[6].getValue();}
	public String get_company_name() {return m_dbAttribs[7].getValue();}
	public String get_addr1() {return m_dbAttribs[8].getValue();}
	public String get_addr2() {return m_dbAttribs[9].getValue();}
	public String get_addr3() {return m_dbAttribs[10].getValue();}
	public String get_addr4() {return m_dbAttribs[11].getValue();}
	public String get_city() {return m_dbAttribs[12].getValue();}
	public String get_state_code() {return m_dbAttribs[13].getValue();}
	public String get_zip() {return m_dbAttribs[14].getValue();}
	public String get_country_code() {return m_dbAttribs[15].getValue();}
	public String get_country_name() {return m_dbAttribs[16].getValue();}
	public String get_email() {return m_dbAttribs[17].getValue();}
	public String get_web() {return m_dbAttribs[18].getValue();}
	public String get_priority() {return m_dbAttribs[19].getValue();}
	public String get_refered_by() {return m_dbAttribs[20].getValue();}
	public String get_is_customer() {return m_dbAttribs[21].getValue();}
	public String get_send_print() {return m_dbAttribs[22].getValue();}
	public String get_send_cd() {return m_dbAttribs[23].getValue();}
	public String get_user1() {return m_dbAttribs[24].getValue();}
	public String get_user2() {return m_dbAttribs[25].getValue();}
	public String get_user3() {return m_dbAttribs[26].getValue();}
	public String get_user4() {return m_dbAttribs[27].getValue();}
	public String get_note1() {return m_dbAttribs[28].getValue();}
	public String get_note2() {return m_dbAttribs[29].getValue();}
	public String get_customer_id() {return m_dbAttribs[30].getValue();}
	public String get_phone() {return m_dbAttribs[31].getValue();}
	public String get_fax() {return m_dbAttribs[32].getValue();}
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

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_created_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_first_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_last_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_title(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_company_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_email(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_web(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_priority(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_refered_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_is_customer(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_send_print(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_send_cd(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_customer_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_phone(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_fax(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new prospectObj();
	}


}
