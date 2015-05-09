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

public class quoteObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

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

		set_locality(this.getObjLocality());

		set_created_by(sessionMeta.getUserId());
        set_carrier_id("BEST WAY");
        set_carrier_name("BEST WAY");
        set_fob("ORIGIN");
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
	static public final String TABLE_NAME = "QUOTE";
	static public final String ID = "QUOTE.ID";
	static public final String LOCALITY = "QUOTE.LOCALITY";
	static public final String DATE_CREATED = "QUOTE.DATE_CREATED";
	static public final String CREATED_BY = "QUOTE.CREATED_BY";
	static public final String ORDER_TYPE = "QUOTE.ORDER_TYPE";
	static public final String ORDER_DATE = "QUOTE.ORDER_DATE";
	static public final String CUSTOMER_ID = "QUOTE.CUSTOMER_ID";
	static public final String SHIPTO_ADDR1 = "QUOTE.SHIPTO_ADDR1";
	static public final String SHIPTO_ADDR2 = "QUOTE.SHIPTO_ADDR2";
	static public final String SHIPTO_ADDR3 = "QUOTE.SHIPTO_ADDR3";
	static public final String SHIPTO_ADDR4 = "QUOTE.SHIPTO_ADDR4";
	static public final String SHIPTO_CITY = "QUOTE.SHIPTO_CITY";
	static public final String SHIPTO_STATE_CODE = "QUOTE.SHIPTO_STATE_CODE";
	static public final String SHIPTO_ZIP = "QUOTE.SHIPTO_ZIP";
	static public final String SHIPTO_COUNTRY_CODE = "QUOTE.SHIPTO_COUNTRY_CODE";
	static public final String SHIPTO_COUNTRY_NAME = "QUOTE.SHIPTO_COUNTRY_NAME";
	static public final String BILLTO_ADDR1 = "QUOTE.BILLTO_ADDR1";
	static public final String BILLTO_ADDR2 = "QUOTE.BILLTO_ADDR2";
	static public final String BILLTO_ADDR3 = "QUOTE.BILLTO_ADDR3";
	static public final String BILLTO_ADDR4 = "QUOTE.BILLTO_ADDR4";
	static public final String BILLTO_CITY = "QUOTE.BILLTO_CITY";
	static public final String BILLTO_STATE_CODE = "QUOTE.BILLTO_STATE_CODE";
	static public final String BILLTO_ZIP = "QUOTE.BILLTO_ZIP";
	static public final String BILLTO_COUNTRY_CODE = "QUOTE.BILLTO_COUNTRY_CODE";
	static public final String BILLTO_COUNTRY_NAME = "QUOTE.BILLTO_COUNTRY_NAME";
	static public final String SALES_REP = "QUOTE.SALES_REP";
	static public final String IS_REPAIR = "QUOTE.IS_REPAIR";
	static public final String COMMISION_AMOUNT = "QUOTE.COMMISION_AMOUNT";
	static public final String DATE_SHIPPED = "QUOTE.DATE_SHIPPED";
	static public final String DATE_PROMISED = "QUOTE.DATE_PROMISED";
	static public final String DATE_NEEDED = "QUOTE.DATE_NEEDED";
	static public final String PAYMENT_TERMS = "QUOTE.PAYMENT_TERMS";
	static public final String FREIGHT_TERMS = "QUOTE.FREIGHT_TERMS";
	static public final String CARRIER_ID = "QUOTE.CARRIER_ID";
	static public final String CARRIER_NAME = "QUOTE.CARRIER_NAME";
	static public final String COD = "QUOTE.COD";
	static public final String FOB = "QUOTE.FOB";
	static public final String TAX_RATE = "QUOTE.TAX_RATE";
	static public final String BOL_NUM = "QUOTE.BOL_NUM";
	static public final String AIR_BILL_NUM = "QUOTE.AIR_BILL_NUM";
	static public final String CURRENCY = "QUOTE.CURRENCY";
	static public final String EXCHANGE_RATE = "QUOTE.EXCHANGE_RATE";
	static public final String SUBTOTAL_OVERRIDE = "QUOTE.SUBTOTAL_OVERRIDE";
	static public final String SUBTOTAL = "QUOTE.SUBTOTAL";
	static public final String TOTAL_VALUE = "QUOTE.TOTAL_VALUE";
	static public final String TOTAL_DISCOUNT = "QUOTE.TOTAL_DISCOUNT";
	static public final String TOTAL_TAX = "QUOTE.TOTAL_TAX";
	static public final String TOTAL_SHIPPING = "QUOTE.TOTAL_SHIPPING";
	static public final String CUSTOMER_NAME = "QUOTE.CUSTOMER_NAME";
	static public final String ORDER_STATUS = "QUOTE.ORDER_STATUS";
	static public final String NOTE1 = "QUOTE.NOTE1";
	static public final String NOTE2 = "QUOTE.NOTE2";
	static public final String SHIPTO_ATTN = "QUOTE.SHIPTO_ATTN";
	static public final String BILLTO_ATTN = "QUOTE.BILLTO_ATTN";
	static public final String OUR_CONTACT_INFO = "QUOTE.OUR_CONTACT_INFO";
	static public final String OUR_CONTACT = "QUOTE.OUR_CONTACT";
	static public final String CUST_CONTACT = "QUOTE.CUST_CONTACT";
	static public final String CUST_CONTACT_INFO = "QUOTE.CUST_CONTACT_INFO";
	static public final String CUSTOMER_IS_PROSPECT = "QUOTE.CUSTOMER_IS_PROSPECT";
	static public final String CUST_PHONE = "QUOTE.CUST_PHONE";
	static public final String CUST_FAX = "QUOTE.CUST_FAX";
	static public final String OUR_EMAIL = "QUOTE.OUR_EMAIL";
	static public final String QUOTE_DELIV_NOTE = "QUOTE.QUOTE_DELIV_NOTE";


	//Constructor
	public quoteObj()
	{
		//Private Field Members.
		MAX_COLS = 63;
		TAB_NAME = "QUOTE";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("ORDER_TYPE", null, "VARCHAR", 30 ,true);
		m_dbAttribs[5] = new DBAttributes("ORDER_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[6] = new DBAttributes("CUSTOMER_ID", null, "VARCHAR", 30 ,true);
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
		m_dbAttribs[25] = new DBAttributes("SALES_REP", null, "VARCHAR", 30 ,true);
		m_dbAttribs[26] = new DBAttributes("IS_REPAIR", null, "CHAR", 1 ,true);
		m_dbAttribs[27] = new DBAttributes("COMMISION_AMOUNT", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[28] = new DBAttributes("DATE_SHIPPED", null, "DATE", 19 ,true);
		m_dbAttribs[29] = new DBAttributes("DATE_PROMISED", null, "DATE", 19 ,true);
		m_dbAttribs[30] = new DBAttributes("DATE_NEEDED", null, "DATE", 19 ,true);
		m_dbAttribs[31] = new DBAttributes("PAYMENT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[32] = new DBAttributes("FREIGHT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[33] = new DBAttributes("CARRIER_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[34] = new DBAttributes("CARRIER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[35] = new DBAttributes("COD", null, "VARCHAR", 50 ,true);
		m_dbAttribs[36] = new DBAttributes("FOB", null, "VARCHAR", 50 ,true);
		m_dbAttribs[37] = new DBAttributes("TAX_RATE", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[38] = new DBAttributes("BOL_NUM", null, "CHAR", 30 ,true);
		m_dbAttribs[39] = new DBAttributes("AIR_BILL_NUM", null, "CHAR", 30 ,true);
		m_dbAttribs[40] = new DBAttributes("CURRENCY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[41] = new DBAttributes("EXCHANGE_RATE", null, "DECIMAL", 9 ,true);
		m_dbAttribs[42] = new DBAttributes("SUBTOTAL_OVERRIDE", null, "VARCHAR", 1 ,true);
		m_dbAttribs[43] = new DBAttributes("SUBTOTAL", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[44] = new DBAttributes("TOTAL_VALUE", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[45] = new DBAttributes("TOTAL_DISCOUNT", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[46] = new DBAttributes("TOTAL_TAX", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[47] = new DBAttributes("TOTAL_SHIPPING", null, "DOUBLE PRECISION", 15 ,true);
		m_dbAttribs[48] = new DBAttributes("CUSTOMER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[49] = new DBAttributes("ORDER_STATUS", null, "VARCHAR", 20 ,true);
		m_dbAttribs[50] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[51] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[52] = new DBAttributes("SHIPTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[53] = new DBAttributes("BILLTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[54] = new DBAttributes("OUR_CONTACT_INFO", null, "VARCHAR", 50 ,true);
		m_dbAttribs[55] = new DBAttributes("OUR_CONTACT", null, "VARCHAR", 50 ,true);
		m_dbAttribs[56] = new DBAttributes("CUST_CONTACT", null, "VARCHAR", 50 ,true);
		m_dbAttribs[57] = new DBAttributes("CUST_CONTACT_INFO", null, "VARCHAR", 50 ,true);
		m_dbAttribs[58] = new DBAttributes("CUSTOMER_IS_PROSPECT", null, "CHAR", 1 ,true);
		m_dbAttribs[59] = new DBAttributes("CUST_PHONE", null, "VARCHAR", 25 ,true);
		m_dbAttribs[60] = new DBAttributes("CUST_FAX", null, "VARCHAR", 25 ,true);
		m_dbAttribs[61] = new DBAttributes("OUR_EMAIL", null, "VARCHAR", 50 ,true);
		m_dbAttribs[62] = new DBAttributes("QUOTE_DELIV_NOTE", null, "VARCHAR", 50 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_date_created() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_order_type() {return m_dbAttribs[4].getValue();}
	public String get_order_date() {return m_dbAttribs[5].getValue();}
	public String get_customer_id() {return m_dbAttribs[6].getValue();}
	public String get_shipto_addr1() {return m_dbAttribs[7].getValue();}
	public String get_shipto_addr2() {return m_dbAttribs[8].getValue();}
	public String get_shipto_addr3() {return m_dbAttribs[9].getValue();}
	public String get_shipto_addr4() {return m_dbAttribs[10].getValue();}
	public String get_shipto_city() {return m_dbAttribs[11].getValue();}
	public String get_shipto_state_code() {return m_dbAttribs[12].getValue();}
	public String get_shipto_zip() {return m_dbAttribs[13].getValue();}
	public String get_shipto_country_code() {return m_dbAttribs[14].getValue();}
	public String get_shipto_country_name() {return m_dbAttribs[15].getValue();}
	public String get_billto_addr1() {return m_dbAttribs[16].getValue();}
	public String get_billto_addr2() {return m_dbAttribs[17].getValue();}
	public String get_billto_addr3() {return m_dbAttribs[18].getValue();}
	public String get_billto_addr4() {return m_dbAttribs[19].getValue();}
	public String get_billto_city() {return m_dbAttribs[20].getValue();}
	public String get_billto_state_code() {return m_dbAttribs[21].getValue();}
	public String get_billto_zip() {return m_dbAttribs[22].getValue();}
	public String get_billto_country_code() {return m_dbAttribs[23].getValue();}
	public String get_billto_country_name() {return m_dbAttribs[24].getValue();}
	public String get_sales_rep() {return m_dbAttribs[25].getValue();}
	public String get_is_repair() {return m_dbAttribs[26].getValue();}
	public String get_commision_amount() {return m_dbAttribs[27].getValue();}
	public String get_date_shipped() {return m_dbAttribs[28].getValue();}
	public String get_date_promised() {return m_dbAttribs[29].getValue();}
	public String get_date_needed() {return m_dbAttribs[30].getValue();}
	public String get_payment_terms() {return m_dbAttribs[31].getValue();}
	public String get_freight_terms() {return m_dbAttribs[32].getValue();}
	public String get_carrier_id() {return m_dbAttribs[33].getValue();}
	public String get_carrier_name() {return m_dbAttribs[34].getValue();}
	public String get_cod() {return m_dbAttribs[35].getValue();}
	public String get_fob() {return m_dbAttribs[36].getValue();}
	public String get_tax_rate() {return m_dbAttribs[37].getValue();}
	public String get_bol_num() {return m_dbAttribs[38].getValue();}
	public String get_air_bill_num() {return m_dbAttribs[39].getValue();}
	public String get_currency() {return m_dbAttribs[40].getValue();}
	public String get_exchange_rate() {return m_dbAttribs[41].getValue();}
	public String get_subtotal_override() {return m_dbAttribs[42].getValue();}
	public String get_subtotal() {return m_dbAttribs[43].getValue();}
	public String get_total_value() {return m_dbAttribs[44].getValue();}
	public String get_total_discount() {return m_dbAttribs[45].getValue();}
	public String get_total_tax() {return m_dbAttribs[46].getValue();}
	public String get_total_shipping() {return m_dbAttribs[47].getValue();}
	public String get_customer_name() {return m_dbAttribs[48].getValue();}
	public String get_order_status() {return m_dbAttribs[49].getValue();}
	public String get_note1() {return m_dbAttribs[50].getValue();}
	public String get_note2() {return m_dbAttribs[51].getValue();}
	public String get_shipto_attn() {return m_dbAttribs[52].getValue();}
	public String get_billto_attn() {return m_dbAttribs[53].getValue();}
	public String get_our_contact_info() {return m_dbAttribs[54].getValue();}
	public String get_our_contact() {return m_dbAttribs[55].getValue();}
	public String get_cust_contact() {return m_dbAttribs[56].getValue();}
	public String get_cust_contact_info() {return m_dbAttribs[57].getValue();}
	public String get_customer_is_prospect() {return m_dbAttribs[58].getValue();}
	public String get_cust_phone() {return m_dbAttribs[59].getValue();}
	public String get_cust_fax() {return m_dbAttribs[60].getValue();}
	public String get_our_email() {return m_dbAttribs[61].getValue();}
	public String get_quote_deliv_note() {return m_dbAttribs[62].getValue();}
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

	public void set_order_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_order_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_customer_id(String val)
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

	public void set_sales_rep(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_is_repair(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_commision_amount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_date_shipped(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_date_promised(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_date_needed(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_payment_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_freight_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_carrier_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_carrier_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_cod(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_fob(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_tax_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_bol_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_air_bill_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_currency(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_exchange_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_subtotal_override(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public void set_subtotal(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[43].setValue(null);
		else
			m_dbAttribs[43].setValue(val);
	}

	public void set_total_value(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[44].setValue(null);
		else
			m_dbAttribs[44].setValue(val);
	}

	public void set_total_discount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[45].setValue(null);
		else
			m_dbAttribs[45].setValue(val);
	}

	public void set_total_tax(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[46].setValue(null);
		else
			m_dbAttribs[46].setValue(val);
	}

	public void set_total_shipping(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[47].setValue(null);
		else
			m_dbAttribs[47].setValue(val);
	}

	public void set_customer_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[48].setValue(null);
		else
			m_dbAttribs[48].setValue(val);
	}

	public void set_order_status(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[49].setValue(null);
		else
			m_dbAttribs[49].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[50].setValue(null);
		else
			m_dbAttribs[50].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[51].setValue(null);
		else
			m_dbAttribs[51].setValue(val);
	}

	public void set_shipto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[52].setValue(null);
		else
			m_dbAttribs[52].setValue(val);
	}

	public void set_billto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[53].setValue(null);
		else
			m_dbAttribs[53].setValue(val);
	}

	public void set_our_contact_info(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[54].setValue(null);
		else
			m_dbAttribs[54].setValue(val);
	}

	public void set_our_contact(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[55].setValue(null);
		else
			m_dbAttribs[55].setValue(val);
	}

	public void set_cust_contact(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[56].setValue(null);
		else
			m_dbAttribs[56].setValue(val);
	}

	public void set_cust_contact_info(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[57].setValue(null);
		else
			m_dbAttribs[57].setValue(val);
	}

	public void set_customer_is_prospect(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[58].setValue(null);
		else
			m_dbAttribs[58].setValue(val);
	}

	public void set_cust_phone(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[59].setValue(null);
		else
			m_dbAttribs[59].setValue(val);
	}

	public void set_cust_fax(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[60].setValue(null);
		else
			m_dbAttribs[60].setValue(val);
	}

	public void set_our_email(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[61].setValue(null);
		else
			m_dbAttribs[61].setValue(val);
	}

	public void set_quote_deliv_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[62].setValue(null);
		else
			m_dbAttribs[62].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new quoteObj();
	}


}
