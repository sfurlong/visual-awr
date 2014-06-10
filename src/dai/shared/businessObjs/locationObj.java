//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;


public class locationObj extends BusinessObject
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
		set_locality(this.getObjLocality());
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "LOCATION";
	static public final String ID = "LOCATION.ID";
	static public final String DESCRIPTION = "LOCATION.DESCRIPTION";
	static public final String SHIPTO_ADDR1 = "LOCATION.SHIPTO_ADDR1";
	static public final String SHIPTO_ADDR2 = "LOCATION.SHIPTO_ADDR2";
	static public final String SHIPTO_ADDR3 = "LOCATION.SHIPTO_ADDR3";
	static public final String SHIPTO_ADDR4 = "LOCATION.SHIPTO_ADDR4";
	static public final String SHIPTO_CITY = "LOCATION.SHIPTO_CITY";
	static public final String SHIPTO_STATE_CODE = "LOCATION.SHIPTO_STATE_CODE";
	static public final String SHIPTO_STATE_NAME = "LOCATION.SHIPTO_STATE_NAME";
	static public final String SHIPTO_ZIP = "LOCATION.SHIPTO_ZIP";
	static public final String SHIPTO_COUNTRY_CODE = "LOCATION.SHIPTO_COUNTRY_CODE";
	static public final String SHIPTO_COUNTRY_NAME = "LOCATION.SHIPTO_COUNTRY_NAME";
	static public final String BILLTO_ADDR1 = "LOCATION.BILLTO_ADDR1";
	static public final String BILLTO_ADDR2 = "LOCATION.BILLTO_ADDR2";
	static public final String BILLTO_ADDR3 = "LOCATION.BILLTO_ADDR3";
	static public final String BILLTO_ADDR4 = "LOCATION.BILLTO_ADDR4";
	static public final String BILLTO_CITY = "LOCATION.BILLTO_CITY";
	static public final String BILLTO_STATE_CODE = "LOCATION.BILLTO_STATE_CODE";
	static public final String BILLTO_STATE_NAME = "LOCATION.BILLTO_STATE_NAME";
	static public final String BILLTO_ZIP = "LOCATION.BILLTO_ZIP";
	static public final String BILLTO_COUNTRY_CODE = "LOCATION.BILLTO_COUNTRY_CODE";
	static public final String BILLTO_COUNTRY_NAME = "LOCATION.BILLTO_COUNTRY_NAME";
	static public final String IS_PRIMARY_LOC = "LOCATION.IS_PRIMARY_LOC";
	static public final String FISCAL_START_MO = "LOCATION.FISCAL_START_MO";
	static public final String FED_TAX_ID = "LOCATION.FED_TAX_ID";
	static public final String USER1 = "LOCATION.USER1";
	static public final String USER2 = "LOCATION.USER2";
	static public final String USER3 = "LOCATION.USER3";
	static public final String USER4 = "LOCATION.USER4";
	static public final String USER5 = "LOCATION.USER5";
	static public final String LOCALITY = "LOCATION.LOCALITY";
	static public final String NOTE = "LOCATION.NOTE";
	static public final String SHIPTO_ATTN = "LOCATION.SHIPTO_ATTN";
	static public final String BILLTO_ATTN = "LOCATION.BILLTO_ATTN";


	//Constructor
	public locationObj()
	{
		//Private Field Members.
		MAX_COLS = 34;
		TAB_NAME = "LOCATION";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("DESCRIPTION", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("SHIPTO_ADDR1", null, "VARCHAR", true);
		m_dbAttribs[3] = new DBAttributes("SHIPTO_ADDR2", null, "VARCHAR", true);
		m_dbAttribs[4] = new DBAttributes("SHIPTO_ADDR3", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("SHIPTO_ADDR4", null, "VARCHAR", true);
		m_dbAttribs[6] = new DBAttributes("SHIPTO_CITY", null, "VARCHAR", true);
		m_dbAttribs[7] = new DBAttributes("SHIPTO_STATE_CODE", null, "VARCHAR", true);
		m_dbAttribs[8] = new DBAttributes("SHIPTO_STATE_NAME", null, "VARCHAR", true);
		m_dbAttribs[9] = new DBAttributes("SHIPTO_ZIP", null, "VARCHAR", true);
		m_dbAttribs[10] = new DBAttributes("SHIPTO_COUNTRY_CODE", null, "VARCHAR", true);
		m_dbAttribs[11] = new DBAttributes("SHIPTO_COUNTRY_NAME", null, "VARCHAR", true);
		m_dbAttribs[12] = new DBAttributes("BILLTO_ADDR1", null, "VARCHAR", true);
		m_dbAttribs[13] = new DBAttributes("BILLTO_ADDR2", null, "VARCHAR", true);
		m_dbAttribs[14] = new DBAttributes("BILLTO_ADDR3", null, "VARCHAR", true);
		m_dbAttribs[15] = new DBAttributes("BILLTO_ADDR4", null, "VARCHAR", true);
		m_dbAttribs[16] = new DBAttributes("BILLTO_CITY", null, "VARCHAR", true);
		m_dbAttribs[17] = new DBAttributes("BILLTO_STATE_CODE", null, "VARCHAR", true);
		m_dbAttribs[18] = new DBAttributes("BILLTO_STATE_NAME", null, "VARCHAR", true);
		m_dbAttribs[19] = new DBAttributes("BILLTO_ZIP", null, "VARCHAR", true);
		m_dbAttribs[20] = new DBAttributes("BILLTO_COUNTRY_CODE", null, "VARCHAR", true);
		m_dbAttribs[21] = new DBAttributes("BILLTO_COUNTRY_NAME", null, "VARCHAR", true);
		m_dbAttribs[22] = new DBAttributes("IS_PRIMARY_LOC", null, "CHAR", true);
		m_dbAttribs[23] = new DBAttributes("FISCAL_START_MO", null, "VARCHAR", true);
		m_dbAttribs[24] = new DBAttributes("FED_TAX_ID", null, "VARCHAR", true);
		m_dbAttribs[25] = new DBAttributes("USER1", null, "VARCHAR", true);
		m_dbAttribs[26] = new DBAttributes("USER2", null, "VARCHAR", true);
		m_dbAttribs[27] = new DBAttributes("USER3", null, "VARCHAR", true);
		m_dbAttribs[28] = new DBAttributes("USER4", null, "VARCHAR", true);
		m_dbAttribs[29] = new DBAttributes("USER5", null, "VARCHAR", true);
		m_dbAttribs[30] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[31] = new DBAttributes("NOTE", null, "VARCHAR", true);
		m_dbAttribs[32] = new DBAttributes("SHIPTO_ATTN", null, "VARCHAR", true);
		m_dbAttribs[33] = new DBAttributes("BILLTO_ATTN", null, "VARCHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_description() {return m_dbAttribs[1].getValue();}
	public String get_shipto_addr1() {return m_dbAttribs[2].getValue();}
	public String get_shipto_addr2() {return m_dbAttribs[3].getValue();}
	public String get_shipto_addr3() {return m_dbAttribs[4].getValue();}
	public String get_shipto_addr4() {return m_dbAttribs[5].getValue();}
	public String get_shipto_city() {return m_dbAttribs[6].getValue();}
	public String get_shipto_state_code() {return m_dbAttribs[7].getValue();}
	public String get_shipto_state_name() {return m_dbAttribs[8].getValue();}
	public String get_shipto_zip() {return m_dbAttribs[9].getValue();}
	public String get_shipto_country_code() {return m_dbAttribs[10].getValue();}
	public String get_shipto_country_name() {return m_dbAttribs[11].getValue();}
	public String get_billto_addr1() {return m_dbAttribs[12].getValue();}
	public String get_billto_addr2() {return m_dbAttribs[13].getValue();}
	public String get_billto_addr3() {return m_dbAttribs[14].getValue();}
	public String get_billto_addr4() {return m_dbAttribs[15].getValue();}
	public String get_billto_city() {return m_dbAttribs[16].getValue();}
	public String get_billto_state_code() {return m_dbAttribs[17].getValue();}
	public String get_billto_state_name() {return m_dbAttribs[18].getValue();}
	public String get_billto_zip() {return m_dbAttribs[19].getValue();}
	public String get_billto_country_code() {return m_dbAttribs[20].getValue();}
	public String get_billto_country_name() {return m_dbAttribs[21].getValue();}
	public String get_is_primary_loc() {return m_dbAttribs[22].getValue();}
	public String get_fiscal_start_mo() {return m_dbAttribs[23].getValue();}
	public String get_fed_tax_id() {return m_dbAttribs[24].getValue();}
	public String get_user1() {return m_dbAttribs[25].getValue();}
	public String get_user2() {return m_dbAttribs[26].getValue();}
	public String get_user3() {return m_dbAttribs[27].getValue();}
	public String get_user4() {return m_dbAttribs[28].getValue();}
	public String get_user5() {return m_dbAttribs[29].getValue();}
	public String get_locality() {return m_dbAttribs[30].getValue();}
	public String get_note() {return m_dbAttribs[31].getValue();}
	public String get_shipto_attn() {return m_dbAttribs[32].getValue();}
	public String get_billto_attn() {return m_dbAttribs[33].getValue();}
	public void set_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[0].setValue(null);
		else
			m_dbAttribs[0].setValue(val);
	}

	public void set_description(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[1].setValue(null);
		else
			m_dbAttribs[1].setValue(val);
	}

	public void set_shipto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_shipto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_shipto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_shipto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_shipto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_shipto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_shipto_state_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_shipto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_shipto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_shipto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_billto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_billto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_billto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_billto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_billto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_billto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_billto_state_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_billto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_billto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_billto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_is_primary_loc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_fiscal_start_mo(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_fed_tax_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_user5(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_locality(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_shipto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_billto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new locationObj();
	}


}
