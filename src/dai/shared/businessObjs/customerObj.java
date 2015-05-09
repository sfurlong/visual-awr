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

public class customerObj extends BusinessObject
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
	static public final String TABLE_NAME = "CUSTOMER";
	static public final String ID = "CUSTOMER.ID";
	static public final String LOCALITY = "CUSTOMER.LOCALITY";
	static public final String DATE_CREATED = "CUSTOMER.DATE_CREATED";
	static public final String CREATED_BY = "CUSTOMER.CREATED_BY";
	static public final String ACCOUNT = "CUSTOMER.ACCOUNT";
	static public final String NAME = "CUSTOMER.NAME";
	static public final String ALSO_KNOWN_AS = "CUSTOMER.ALSO_KNOWN_AS";
	static public final String SHIPTO_ADDR1 = "CUSTOMER.SHIPTO_ADDR1";
	static public final String SHIPTO_ADDR2 = "CUSTOMER.SHIPTO_ADDR2";
	static public final String SHIPTO_ADDR3 = "CUSTOMER.SHIPTO_ADDR3";
	static public final String SHIPTO_ADDR4 = "CUSTOMER.SHIPTO_ADDR4";
	static public final String SHIPTO_CITY = "CUSTOMER.SHIPTO_CITY";
	static public final String SHIPTO_STATE_CODE = "CUSTOMER.SHIPTO_STATE_CODE";
	static public final String SHIPTO_ZIP = "CUSTOMER.SHIPTO_ZIP";
	static public final String SHIPTO_COUNTRY_CODE = "CUSTOMER.SHIPTO_COUNTRY_CODE";
	static public final String SHIPTO_COUNTRY_NAME = "CUSTOMER.SHIPTO_COUNTRY_NAME";
	static public final String BILLTO_ADDR1 = "CUSTOMER.BILLTO_ADDR1";
	static public final String BILLTO_ADDR2 = "CUSTOMER.BILLTO_ADDR2";
	static public final String BILLTO_ADDR3 = "CUSTOMER.BILLTO_ADDR3";
	static public final String BILLTO_ADDR4 = "CUSTOMER.BILLTO_ADDR4";
	static public final String BILLTO_CITY = "CUSTOMER.BILLTO_CITY";
	static public final String BILLTO_STATE_CODE = "CUSTOMER.BILLTO_STATE_CODE";
	static public final String BILLTO_ZIP = "CUSTOMER.BILLTO_ZIP";
	static public final String BILLTO_COUNTRY_CODE = "CUSTOMER.BILLTO_COUNTRY_CODE";
	static public final String BILLTO_COUNTRY_NAME = "CUSTOMER.BILLTO_COUNTRY_NAME";
	static public final String PRIORITY = "CUSTOMER.PRIORITY";
	static public final String COD_STATUS = "CUSTOMER.COD_STATUS";
	static public final String PAYMENT_STATUS = "CUSTOMER.PAYMENT_STATUS";
	static public final String REFERED_BY = "CUSTOMER.REFERED_BY";
	static public final String CREDIT_CARD_TYPE = "CUSTOMER.CREDIT_CARD_TYPE";
	static public final String CREDIT_CAR_NUM = "CUSTOMER.CREDIT_CAR_NUM";
	static public final String CREDIT_CARD_EXP_DATE = "CUSTOMER.CREDIT_CARD_EXP_DATE";
	static public final String PREFERED_CARRIER = "CUSTOMER.PREFERED_CARRIER";
	static public final String CARRIER_ACCT = "CUSTOMER.CARRIER_ACCT";
	static public final String CALL_BACK = "CUSTOMER.CALL_BACK";
	static public final String BILLTO_IS_SHIPTO = "CUSTOMER.BILLTO_IS_SHIPTO";
	static public final String USER1 = "CUSTOMER.USER1";
	static public final String USER2 = "CUSTOMER.USER2";
	static public final String USER3 = "CUSTOMER.USER3";
	static public final String USER4 = "CUSTOMER.USER4";
	static public final String TAXABLE = "CUSTOMER.TAXABLE";
	static public final String TAX_RATE = "CUSTOMER.TAX_RATE";
	static public final String TAX_RESALE_EXEMPT_NO = "CUSTOMER.TAX_RESALE_EXEMPT_NO";
	static public final String HAS_RECEIVED_DISCOUNT = "CUSTOMER.HAS_RECEIVED_DISCOUNT";
	static public final String NOTE1 = "CUSTOMER.NOTE1";
	static public final String NOTE2 = "CUSTOMER.NOTE2";
	static public final String SHIPTO_ATTN = "CUSTOMER.SHIPTO_ATTN";
	static public final String BILLTO_ATTN = "CUSTOMER.BILLTO_ATTN";
	static public final String DATE_OF_FIRST_ORDER = "CUSTOMER.DATE_OF_FIRST_ORDER";
	static public final String DATE_OF_LAST_ORDER = "CUSTOMER.DATE_OF_LAST_ORDER";


	//Constructor
	public customerObj()
	{
		//Private Field Members.
		MAX_COLS = 50;
		TAB_NAME = "CUSTOMER";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init()
	{
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("ACCOUNT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[5] = new DBAttributes("NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[6] = new DBAttributes("ALSO_KNOWN_AS", null, "VARCHAR", 50 ,true);
		m_dbAttribs[7] = new DBAttributes("SHIPTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("SHIPTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[9] = new DBAttributes("SHIPTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[10] = new DBAttributes("SHIPTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[11] = new DBAttributes("SHIPTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("SHIPTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[13] = new DBAttributes("SHIPTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[14] = new DBAttributes("SHIPTO_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[15] = new DBAttributes("SHIPTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[16] = new DBAttributes("BILLTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[17] = new DBAttributes("BILLTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[18] = new DBAttributes("BILLTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[19] = new DBAttributes("BILLTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[20] = new DBAttributes("BILLTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[21] = new DBAttributes("BILLTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[22] = new DBAttributes("BILLTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[23] = new DBAttributes("BILLTO_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[24] = new DBAttributes("BILLTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[25] = new DBAttributes("PRIORITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[26] = new DBAttributes("COD_STATUS", null, "VARCHAR", 5 ,true);
		m_dbAttribs[27] = new DBAttributes("PAYMENT_STATUS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[28] = new DBAttributes("REFERED_BY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[29] = new DBAttributes("CREDIT_CARD_TYPE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[30] = new DBAttributes("CREDIT_CAR_NUM", null, "VARCHAR", 20 ,true);
		m_dbAttribs[31] = new DBAttributes("CREDIT_CARD_EXP_DATE", null, "VARCHAR", 12 ,true);
		m_dbAttribs[32] = new DBAttributes("PREFERED_CARRIER", null, "VARCHAR", 20 ,true);
		m_dbAttribs[33] = new DBAttributes("CARRIER_ACCT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[34] = new DBAttributes("CALL_BACK", null, "VARCHAR", 1 ,true);
		m_dbAttribs[35] = new DBAttributes("BILLTO_IS_SHIPTO", null, "VARCHAR", 1 ,true);
		m_dbAttribs[36] = new DBAttributes("USER1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[37] = new DBAttributes("USER2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[38] = new DBAttributes("USER3", null, "VARCHAR", 30 ,true);
		m_dbAttribs[39] = new DBAttributes("USER4", null, "VARCHAR", 30 ,true);
		m_dbAttribs[40] = new DBAttributes("TAXABLE", null, "CHAR", 1 ,true);
		m_dbAttribs[41] = new DBAttributes("TAX_RATE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[42] = new DBAttributes("TAX_RESALE_EXEMPT_NO", null, "VARCHAR", 20 ,true);
		m_dbAttribs[43] = new DBAttributes("HAS_RECEIVED_DISCOUNT", null, "CHAR", 1 ,true);
		m_dbAttribs[44] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[45] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[46] = new DBAttributes("SHIPTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[47] = new DBAttributes("BILLTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[48] = new DBAttributes("DATE_OF_FIRST_ORDER", null, "DATE", 19 ,true);
		m_dbAttribs[49] = new DBAttributes("DATE_OF_LAST_ORDER", null, "DATE", 19 ,true);
	}
	public String get_id()
	{return m_dbAttribs[0].getValue();}
	public String get_locality()
	{return m_dbAttribs[1].getValue();}
	public String get_date_created()
	{return m_dbAttribs[2].getValue();}
	public String get_created_by()
	{return m_dbAttribs[3].getValue();}
	public String get_account()
	{return m_dbAttribs[4].getValue();}
	public String get_name()
	{return m_dbAttribs[5].getValue();}
	public String get_also_known_as()
	{return m_dbAttribs[6].getValue();}
	public String get_shipto_addr1()
	{return m_dbAttribs[7].getValue();}
	public String get_shipto_addr2()
	{return m_dbAttribs[8].getValue();}
	public String get_shipto_addr3()
	{return m_dbAttribs[9].getValue();}
	public String get_shipto_addr4()
	{return m_dbAttribs[10].getValue();}
	public String get_shipto_city()
	{return m_dbAttribs[11].getValue();}
	public String get_shipto_state_code()
	{return m_dbAttribs[12].getValue();}
	public String get_shipto_zip()
	{return m_dbAttribs[13].getValue();}
	public String get_shipto_country_code()
	{return m_dbAttribs[14].getValue();}
	public String get_shipto_country_name()
	{return m_dbAttribs[15].getValue();}
	public String get_billto_addr1()
	{return m_dbAttribs[16].getValue();}
	public String get_billto_addr2()
	{return m_dbAttribs[17].getValue();}
	public String get_billto_addr3()
	{return m_dbAttribs[18].getValue();}
	public String get_billto_addr4()
	{return m_dbAttribs[19].getValue();}
	public String get_billto_city()
	{return m_dbAttribs[20].getValue();}
	public String get_billto_state_code()
	{return m_dbAttribs[21].getValue();}
	public String get_billto_zip()
	{return m_dbAttribs[22].getValue();}
	public String get_billto_country_code()
	{return m_dbAttribs[23].getValue();}
	public String get_billto_country_name()
	{return m_dbAttribs[24].getValue();}
	public String get_priority()
	{return m_dbAttribs[25].getValue();}
	public String get_cod_status()
	{return m_dbAttribs[26].getValue();}
	public String get_payment_status()
	{return m_dbAttribs[27].getValue();}
	public String get_refered_by()
	{return m_dbAttribs[28].getValue();}
	public String get_credit_card_type()
	{return m_dbAttribs[29].getValue();}
	public String get_credit_car_num()
	{return m_dbAttribs[30].getValue();}
	public String get_credit_card_exp_date()
	{return m_dbAttribs[31].getValue();}
	public String get_prefered_carrier()
	{return m_dbAttribs[32].getValue();}
	public String get_carrier_acct()
	{return m_dbAttribs[33].getValue();}
	public String get_call_back()
	{return m_dbAttribs[34].getValue();}
	public String get_billto_is_shipto()
	{return m_dbAttribs[35].getValue();}
	public String get_user1()
	{return m_dbAttribs[36].getValue();}
	public String get_user2()
	{return m_dbAttribs[37].getValue();}
	public String get_user3()
	{return m_dbAttribs[38].getValue();}
	public String get_user4()
	{return m_dbAttribs[39].getValue();}
	public String get_taxable()
	{return m_dbAttribs[40].getValue();}
	public String get_tax_rate()
	{return m_dbAttribs[41].getValue();}
	public String get_tax_resale_exempt_no()
	{return m_dbAttribs[42].getValue();}
	public String get_has_received_discount()
	{return m_dbAttribs[43].getValue();}
	public String get_note1()
	{return m_dbAttribs[44].getValue();}
	public String get_note2()
	{return m_dbAttribs[45].getValue();}
	public String get_shipto_attn()
	{return m_dbAttribs[46].getValue();}
	public String get_billto_attn()
	{return m_dbAttribs[47].getValue();}
	public String get_date_of_first_order()
	{return m_dbAttribs[48].getValue();}
	public String get_date_of_last_order()
	{return m_dbAttribs[49].getValue();}
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

	public void set_account(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_also_known_as(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_shipto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_shipto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_shipto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_shipto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_shipto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_shipto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_shipto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_shipto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_shipto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_billto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_billto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_billto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_billto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_billto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_billto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_billto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_billto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_billto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_priority(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_cod_status(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_payment_status(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_refered_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_credit_card_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_credit_car_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_credit_card_exp_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_prefered_carrier(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_carrier_acct(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_call_back(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_billto_is_shipto(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_taxable(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_tax_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_tax_resale_exempt_no(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public void set_has_received_discount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[43].setValue(null);
		else
			m_dbAttribs[43].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[44].setValue(null);
		else
			m_dbAttribs[44].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[45].setValue(null);
		else
			m_dbAttribs[45].setValue(val);
	}

	public void set_shipto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[46].setValue(null);
		else
			m_dbAttribs[46].setValue(val);
	}

	public void set_billto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[47].setValue(null);
		else
			m_dbAttribs[47].setValue(val);
	}

	public void set_date_of_first_order(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[48].setValue(null);
		else
			m_dbAttribs[48].setValue(val);
	}

	public void set_date_of_last_order(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[49].setValue(null);
		else
			m_dbAttribs[49].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new customerObj();
	}


}
