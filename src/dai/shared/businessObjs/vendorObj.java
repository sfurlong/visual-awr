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

public class vendorObj extends BusinessObject
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
	static public final String TABLE_NAME = "VENDOR";
	static public final String ID = "VENDOR.ID";
	static public final String LOCALITY = "VENDOR.LOCALITY";
	static public final String DATE_CREATED = "VENDOR.DATE_CREATED";
	static public final String CREATED_BY = "VENDOR.CREATED_BY";
	static public final String NAME = "VENDOR.NAME";
	static public final String ALSO_KNOWN_AS = "VENDOR.ALSO_KNOWN_AS";
	static public final String SS_NUM = "VENDOR.SS_NUM";
	static public final String VENDOR_TYPE = "VENDOR.VENDOR_TYPE";
	static public final String PRIORITY = "VENDOR.PRIORITY";
	static public final String PAYMENT_DAYS = "VENDOR.PAYMENT_DAYS";
	static public final String DISCOUNT_PERCENT = "VENDOR.DISCOUNT_PERCENT";
	static public final String PRIMARY_ACCT = "VENDOR.PRIMARY_ACCT";
	static public final String SECONDARY_ACCT = "VENDOR.SECONDARY_ACCT";
	static public final String USER1 = "VENDOR.USER1";
	static public final String USER2 = "VENDOR.USER2";
	static public final String USER3 = "VENDOR.USER3";
	static public final String USER4 = "VENDOR.USER4";
	static public final String PRIMARY_ACCT_NAME = "VENDOR.PRIMARY_ACCT_NAME";
	static public final String SECONDARY_ACCT_NAME = "VENDOR.SECONDARY_ACCT_NAME";
	static public final String OUR_ACCT_NO_WITH_VENDOR = "VENDOR.OUR_ACCT_NO_WITH_VENDOR";
	static public final String NOTE1 = "VENDOR.NOTE1";
	static public final String NOTE2 = "VENDOR.NOTE2";
	static public final String REMIT_ADDR1 = "VENDOR.REMIT_ADDR1";
	static public final String REMIT_ADDR2 = "VENDOR.REMIT_ADDR2";
	static public final String REMIT_ADDR3 = "VENDOR.REMIT_ADDR3";
	static public final String REMIT_ADDR4 = "VENDOR.REMIT_ADDR4";
	static public final String REMIT_CITY = "VENDOR.REMIT_CITY";
	static public final String REMIT_STATE_CODE = "VENDOR.REMIT_STATE_CODE";
	static public final String REMIT_ZIP = "VENDOR.REMIT_ZIP";
	static public final String REMIT_COUNTRY_CODE = "VENDOR.REMIT_COUNTRY_CODE";
	static public final String REMIT_COUNTRY_NAME = "VENDOR.REMIT_COUNTRY_NAME";
	static public final String SHIPTO_COUNTRY_NAME = "VENDOR.SHIPTO_COUNTRY_NAME";
	static public final String SHIPTO_ADDR1 = "VENDOR.SHIPTO_ADDR1";
	static public final String SHIPTO_ADDR2 = "VENDOR.SHIPTO_ADDR2";
	static public final String SHIPTO_ADDR3 = "VENDOR.SHIPTO_ADDR3";
	static public final String SHIPTO_ADDR4 = "VENDOR.SHIPTO_ADDR4";
	static public final String SHIPTO_CITY = "VENDOR.SHIPTO_CITY";
	static public final String SHIPTO_STATE_CODE = "VENDOR.SHIPTO_STATE_CODE";
	static public final String SHIPTO_ZIP = "VENDOR.SHIPTO_ZIP";
	static public final String SHIPTO_COUNTRY_CODE = "VENDOR.SHIPTO_COUNTRY_CODE";
	static public final String REMITTO_ATTN = "VENDOR.REMITTO_ATTN";
	static public final String SHIPTO_ATTN = "VENDOR.SHIPTO_ATTN";
	static public final String IS_REMITTO_SAMEAS_SHIPTO = "VENDOR.IS_REMITTO_SAMEAS_SHIPTO";


	//Constructor
	public vendorObj()
	{
		//Private Field Members.
		MAX_COLS = 43;
		TAB_NAME = "VENDOR";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("ALSO_KNOWN_AS", null, "VARCHAR", 50 ,true);
		m_dbAttribs[6] = new DBAttributes("SS_NUM", null, "VARCHAR", 15 ,true);
		m_dbAttribs[7] = new DBAttributes("VENDOR_TYPE", null, "VARCHAR", 20 ,true);
		m_dbAttribs[8] = new DBAttributes("PRIORITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[9] = new DBAttributes("PAYMENT_DAYS", null, "INTEGER", 10 ,true);
		m_dbAttribs[10] = new DBAttributes("DISCOUNT_PERCENT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[11] = new DBAttributes("PRIMARY_ACCT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[12] = new DBAttributes("SECONDARY_ACCT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[13] = new DBAttributes("USER1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[14] = new DBAttributes("USER2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[15] = new DBAttributes("USER3", null, "VARCHAR", 30 ,true);
		m_dbAttribs[16] = new DBAttributes("USER4", null, "VARCHAR", 30 ,true);
		m_dbAttribs[17] = new DBAttributes("PRIMARY_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[18] = new DBAttributes("SECONDARY_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[19] = new DBAttributes("OUR_ACCT_NO_WITH_VENDOR", null, "VARCHAR", 30 ,true);
		m_dbAttribs[20] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[21] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[22] = new DBAttributes("REMIT_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[23] = new DBAttributes("REMIT_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[24] = new DBAttributes("REMIT_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[25] = new DBAttributes("REMIT_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[26] = new DBAttributes("REMIT_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[27] = new DBAttributes("REMIT_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[28] = new DBAttributes("REMIT_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[29] = new DBAttributes("REMIT_COUNTRY_CODE", null, "CHAR", 2 ,true);
		m_dbAttribs[30] = new DBAttributes("REMIT_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[31] = new DBAttributes("SHIPTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[32] = new DBAttributes("SHIPTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[33] = new DBAttributes("SHIPTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[34] = new DBAttributes("SHIPTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[35] = new DBAttributes("SHIPTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[36] = new DBAttributes("SHIPTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[37] = new DBAttributes("SHIPTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[38] = new DBAttributes("SHIPTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[39] = new DBAttributes("SHIPTO_COUNTRY_CODE", null, "CHAR", 2 ,true);
		m_dbAttribs[40] = new DBAttributes("REMITTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[41] = new DBAttributes("SHIPTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[42] = new DBAttributes("IS_REMITTO_SAMEAS_SHIPTO", null, "CHAR", 1 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_date_created() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_name() {return m_dbAttribs[4].getValue();}
	public String get_also_known_as() {return m_dbAttribs[5].getValue();}
	public String get_ss_num() {return m_dbAttribs[6].getValue();}
	public String get_vendor_type() {return m_dbAttribs[7].getValue();}
	public String get_priority() {return m_dbAttribs[8].getValue();}
	public String get_payment_days() {return m_dbAttribs[9].getValue();}
	public String get_discount_percent() {return m_dbAttribs[10].getValue();}
	public String get_primary_acct() {return m_dbAttribs[11].getValue();}
	public String get_secondary_acct() {return m_dbAttribs[12].getValue();}
	public String get_user1() {return m_dbAttribs[13].getValue();}
	public String get_user2() {return m_dbAttribs[14].getValue();}
	public String get_user3() {return m_dbAttribs[15].getValue();}
	public String get_user4() {return m_dbAttribs[16].getValue();}
	public String get_primary_acct_name() {return m_dbAttribs[17].getValue();}
	public String get_secondary_acct_name() {return m_dbAttribs[18].getValue();}
	public String get_our_acct_no_with_vendor() {return m_dbAttribs[19].getValue();}
	public String get_note1() {return m_dbAttribs[20].getValue();}
	public String get_note2() {return m_dbAttribs[21].getValue();}
	public String get_remit_addr1() {return m_dbAttribs[22].getValue();}
	public String get_remit_addr2() {return m_dbAttribs[23].getValue();}
	public String get_remit_addr3() {return m_dbAttribs[24].getValue();}
	public String get_remit_addr4() {return m_dbAttribs[25].getValue();}
	public String get_remit_city() {return m_dbAttribs[26].getValue();}
	public String get_remit_state_code() {return m_dbAttribs[27].getValue();}
	public String get_remit_zip() {return m_dbAttribs[28].getValue();}
	public String get_remit_country_code() {return m_dbAttribs[29].getValue();}
	public String get_remit_country_name() {return m_dbAttribs[30].getValue();}
	public String get_shipto_country_name() {return m_dbAttribs[31].getValue();}
	public String get_shipto_addr1() {return m_dbAttribs[32].getValue();}
	public String get_shipto_addr2() {return m_dbAttribs[33].getValue();}
	public String get_shipto_addr3() {return m_dbAttribs[34].getValue();}
	public String get_shipto_addr4() {return m_dbAttribs[35].getValue();}
	public String get_shipto_city() {return m_dbAttribs[36].getValue();}
	public String get_shipto_state_code() {return m_dbAttribs[37].getValue();}
	public String get_shipto_zip() {return m_dbAttribs[38].getValue();}
	public String get_shipto_country_code() {return m_dbAttribs[39].getValue();}
	public String get_remitto_attn() {return m_dbAttribs[40].getValue();}
	public String get_shipto_attn() {return m_dbAttribs[41].getValue();}
	public String get_is_remitto_sameas_shipto() {return m_dbAttribs[42].getValue();}
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

	public void set_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_also_known_as(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_ss_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_vendor_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_priority(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_payment_days(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_discount_percent(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_primary_acct(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_secondary_acct(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_primary_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_secondary_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_our_acct_no_with_vendor(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_remit_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_remit_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_remit_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_remit_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_remit_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_remit_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_remit_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_remit_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_remit_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_shipto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_shipto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_shipto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_shipto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_shipto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_shipto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_shipto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_shipto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_shipto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_remitto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_shipto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_is_remitto_sameas_shipto(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new vendorObj();
	}


}
