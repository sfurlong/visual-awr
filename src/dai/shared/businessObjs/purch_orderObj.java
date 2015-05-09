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

public class purch_orderObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	static public final String PURCH_ORDER_TYPE_EXPENSE = "EXPENSE";
	static public final String PURCH_ORDER_TYPE_PURCHASE = "PURCHASE";

	static public final String PAY_TERMS_NET15 = "Net 15";
	static public final String PAY_TERMS_NET30 = "Net 30";
	static public final String PAY_TERMS_NET45 = "Net 45";
	static public final String PAY_TERMS_NET60 = "Net 60";
	static public final String PAY_TERMS_ON_RECEIPT = "On Receipt";
	static public final String PAY_TERMS_NET30_1_10 = "1% 10 Net 30";
	static public final String PAY_TERMS_NET30_2_10 = "2% 10 Net 30";

	//Transient field.(i.e. not stored in the database.  used to pass
	//data between the client/server.
	static public final String _BILL_PAYMENT_AMT = "_BILL_PAYMENT_AMT";
	String _bill_payment_amt = "0.0";
	public String get__bill_payment_amt() {
		return _bill_payment_amt;
	}
	public void set__bill_payment_amt(String amt) {
		_bill_payment_amt = amt;
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

		set_locality(this.getObjLocality());

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
	static public final String TABLE_NAME = "PURCH_ORDER";
	static public final String ID = "PURCH_ORDER.ID";
	static public final String LOCALITY = "PURCH_ORDER.LOCALITY";
	static public final String DATE_CREATED = "PURCH_ORDER.DATE_CREATED";
	static public final String CREATED_BY = "PURCH_ORDER.CREATED_BY";
	static public final String PURCH_TYPE = "PURCH_ORDER.PURCH_TYPE";
	static public final String PURCH_DATE = "PURCH_ORDER.PURCH_DATE";
	static public final String VENDOR_ID = "PURCH_ORDER.VENDOR_ID";
	static public final String VENDOR_NAME = "PURCH_ORDER.VENDOR_NAME";
	static public final String VENDOR_ADDR1 = "PURCH_ORDER.VENDOR_ADDR1";
	static public final String VENDOR_ADDR2 = "PURCH_ORDER.VENDOR_ADDR2";
	static public final String VENDOR_ADDR3 = "PURCH_ORDER.VENDOR_ADDR3";
	static public final String VENDOR_ADDR4 = "PURCH_ORDER.VENDOR_ADDR4";
	static public final String VENDOR_CITY = "PURCH_ORDER.VENDOR_CITY";
	static public final String VENDOR_STATE_CODE = "PURCH_ORDER.VENDOR_STATE_CODE";
	static public final String VENDOR_ZIP = "PURCH_ORDER.VENDOR_ZIP";
	static public final String VENDOR_COUNTRY_CODE = "PURCH_ORDER.VENDOR_COUNTRY_CODE";
	static public final String VENDOR_COUNTRY_NAME = "PURCH_ORDER.VENDOR_COUNTRY_NAME";
	static public final String SHIPTO_ID = "PURCH_ORDER.SHIPTO_ID";
	static public final String SHIPTO_ADDR1 = "PURCH_ORDER.SHIPTO_ADDR1";
	static public final String SHIPTO_ADDR2 = "PURCH_ORDER.SHIPTO_ADDR2";
	static public final String SHIPTO_ADDR3 = "PURCH_ORDER.SHIPTO_ADDR3";
	static public final String SHIPTO_ADDR4 = "PURCH_ORDER.SHIPTO_ADDR4";
	static public final String SHIPTO_CITY = "PURCH_ORDER.SHIPTO_CITY";
	static public final String SHIPTO_STATE_CODE = "PURCH_ORDER.SHIPTO_STATE_CODE";
	static public final String SHIPTO_ZIP = "PURCH_ORDER.SHIPTO_ZIP";
	static public final String SHIPTO_COUNTRY_CODE = "PURCH_ORDER.SHIPTO_COUNTRY_CODE";
	static public final String SHIPTO_COUNTRY_NAME = "PURCH_ORDER.SHIPTO_COUNTRY_NAME";
	static public final String BILLTO_ID = "PURCH_ORDER.BILLTO_ID";
	static public final String BILLTO_ADDR1 = "PURCH_ORDER.BILLTO_ADDR1";
	static public final String BILLTO_ADDR2 = "PURCH_ORDER.BILLTO_ADDR2";
	static public final String BILLTO_ADDR3 = "PURCH_ORDER.BILLTO_ADDR3";
	static public final String BILLTO_ADDR4 = "PURCH_ORDER.BILLTO_ADDR4";
	static public final String BILLTO_CITY = "PURCH_ORDER.BILLTO_CITY";
	static public final String BILLTO_STATE_CODE = "PURCH_ORDER.BILLTO_STATE_CODE";
	static public final String BILLTO_ZIP = "PURCH_ORDER.BILLTO_ZIP";
	static public final String BILLTO_COUNTRY_CODE = "PURCH_ORDER.BILLTO_COUNTRY_CODE";
	static public final String BILLTO_COUNTRY_NAME = "PURCH_ORDER.BILLTO_COUNTRY_NAME";
	static public final String SALES_REP = "PURCH_ORDER.SALES_REP";
	static public final String PURCH_STATUS = "PURCH_ORDER.PURCH_STATUS";
	static public final String DATE_PROMISED = "PURCH_ORDER.DATE_PROMISED";
	static public final String DATE_NEEDED = "PURCH_ORDER.DATE_NEEDED";
	static public final String ACCOUNT_NUM = "PURCH_ORDER.ACCOUNT_NUM";
	static public final String ACCOUNT_NAME = "PURCH_ORDER.ACCOUNT_NAME";
	static public final String PAYMENT_TERMS = "PURCH_ORDER.PAYMENT_TERMS";
	static public final String FREIGHT_TERMS = "PURCH_ORDER.FREIGHT_TERMS";
	static public final String CARRIER_ID = "PURCH_ORDER.CARRIER_ID";
	static public final String CARRIER_NAME = "PURCH_ORDER.CARRIER_NAME";
	static public final String COD = "PURCH_ORDER.COD";
	static public final String FOB = "PURCH_ORDER.FOB";
	static public final String TAX_RATE = "PURCH_ORDER.TAX_RATE";
	static public final String AIR_BILL_NUM = "PURCH_ORDER.AIR_BILL_NUM";
	static public final String CURRENCY = "PURCH_ORDER.CURRENCY";
	static public final String EXCHANGE_RATE = "PURCH_ORDER.EXCHANGE_RATE";
	static public final String SUBTOTAL_OVERRIDE = "PURCH_ORDER.SUBTOTAL_OVERRIDE";
	static public final String SUBTOTAL = "PURCH_ORDER.SUBTOTAL";
	static public final String TOTAL_VALUE = "PURCH_ORDER.TOTAL_VALUE";
	static public final String TOTAL_DISCOUNT = "PURCH_ORDER.TOTAL_DISCOUNT";
	static public final String TOTAL_TAX = "PURCH_ORDER.TOTAL_TAX";
	static public final String TOTAL_SHIPPING = "PURCH_ORDER.TOTAL_SHIPPING";
	static public final String PAYMENT_DUE_DATE = "PURCH_ORDER.PAYMENT_DUE_DATE";
	static public final String PURCH_ORDER_TYPE = "PURCH_ORDER.PURCH_ORDER_TYPE";
	static public final String SHIPTO_NAME = "PURCH_ORDER.SHIPTO_NAME";
	static public final String TOTAL_PAYMENTS_POSTED = "PURCH_ORDER.TOTAL_PAYMENTS_POSTED";
	static public final String NOTE1 = "PURCH_ORDER.NOTE1";
	static public final String NOTE2 = "PURCH_ORDER.NOTE2";
	static public final String IS_FOR_STOCK = "PURCH_ORDER.IS_FOR_STOCK";
	static public final String IS_DIRECT_CUST_SHIP = "PURCH_ORDER.IS_DIRECT_CUST_SHIP";
	static public final String VENDOR_ATTN = "PURCH_ORDER.VENDOR_ATTN";
	static public final String BILLTO_ATTN = "PURCH_ORDER.BILLTO_ATTN";
	static public final String SHIPTO_ATTN = "PURCH_ORDER.SHIPTO_ATTN";
	static public final String OUR_CONTACT = "PURCH_ORDER.OUR_CONTACT";
	static public final String OUR_CONTACT_INFO = "PURCH_ORDER.OUR_CONTACT_INFO";
	static public final String VENDOR_CONTACT_INFO = "PURCH_ORDER.VENDOR_CONTACT_INFO";
	static public final String VENDOR_CONTACT = "PURCH_ORDER.VENDOR_CONTACT";
	static public final String IS_FOR_CUST = "PURCH_ORDER.IS_FOR_CUST";
	static public final String CUST_ID = "PURCH_ORDER.CUST_ID";
	static public final String CUST_NAME = "PURCH_ORDER.CUST_NAME";
   //added manually
   static public final String VENDOR_FAX = "PURCH_ORDER.VENDOR_FAX";


	//Constructor
	public purch_orderObj()
	{
		//Private Field Members.
      //incremented max_cols by 1
		MAX_COLS = 78;
		TAB_NAME = "PURCH_ORDER";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("PURCH_TYPE", null, "VARCHAR", 30 ,true);
		m_dbAttribs[5] = new DBAttributes("PURCH_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[6] = new DBAttributes("VENDOR_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[7] = new DBAttributes("VENDOR_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("VENDOR_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[9] = new DBAttributes("VENDOR_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[10] = new DBAttributes("VENDOR_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[11] = new DBAttributes("VENDOR_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("VENDOR_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[13] = new DBAttributes("VENDOR_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[14] = new DBAttributes("VENDOR_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[15] = new DBAttributes("VENDOR_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[16] = new DBAttributes("VENDOR_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[17] = new DBAttributes("SHIPTO_ID", null, "VARCHAR", 50 ,true);
		m_dbAttribs[18] = new DBAttributes("SHIPTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[19] = new DBAttributes("SHIPTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[20] = new DBAttributes("SHIPTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[21] = new DBAttributes("SHIPTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[22] = new DBAttributes("SHIPTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[23] = new DBAttributes("SHIPTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[24] = new DBAttributes("SHIPTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[25] = new DBAttributes("SHIPTO_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[26] = new DBAttributes("SHIPTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[27] = new DBAttributes("BILLTO_ID", null, "VARCHAR", 50 ,true);
		m_dbAttribs[28] = new DBAttributes("BILLTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[29] = new DBAttributes("BILLTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[30] = new DBAttributes("BILLTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[31] = new DBAttributes("BILLTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[32] = new DBAttributes("BILLTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[33] = new DBAttributes("BILLTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[34] = new DBAttributes("BILLTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[35] = new DBAttributes("BILLTO_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[36] = new DBAttributes("BILLTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[37] = new DBAttributes("SALES_REP", null, "VARCHAR", 30 ,true);
		m_dbAttribs[38] = new DBAttributes("PURCH_STATUS", null, "VARCHAR", 20 ,true);
		m_dbAttribs[39] = new DBAttributes("DATE_PROMISED", null, "DATE", 19 ,true);
		m_dbAttribs[40] = new DBAttributes("DATE_NEEDED", null, "DATE", 19 ,true);
		m_dbAttribs[41] = new DBAttributes("ACCOUNT_NUM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[42] = new DBAttributes("ACCOUNT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[43] = new DBAttributes("PAYMENT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[44] = new DBAttributes("FREIGHT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[45] = new DBAttributes("CARRIER_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[46] = new DBAttributes("CARRIER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[47] = new DBAttributes("COD", null, "VARCHAR", 50 ,true);
		m_dbAttribs[48] = new DBAttributes("FOB", null, "VARCHAR", 50 ,true);
		m_dbAttribs[49] = new DBAttributes("TAX_RATE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[50] = new DBAttributes("AIR_BILL_NUM", null, "CHAR", 30 ,true);
		m_dbAttribs[51] = new DBAttributes("CURRENCY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[52] = new DBAttributes("EXCHANGE_RATE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[53] = new DBAttributes("SUBTOTAL_OVERRIDE", null, "VARCHAR", 1 ,true);
		m_dbAttribs[54] = new DBAttributes("SUBTOTAL", null, "NUMERIC", 0 ,true);
		m_dbAttribs[55] = new DBAttributes("TOTAL_VALUE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[56] = new DBAttributes("TOTAL_DISCOUNT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[57] = new DBAttributes("TOTAL_TAX", null, "NUMERIC", 0 ,true);
		m_dbAttribs[58] = new DBAttributes("TOTAL_SHIPPING", null, "NUMERIC", 0 ,true);
		m_dbAttribs[59] = new DBAttributes("PAYMENT_DUE_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[60] = new DBAttributes("PURCH_ORDER_TYPE", null, "VARCHAR", 30 ,true);
		m_dbAttribs[61] = new DBAttributes("SHIPTO_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[62] = new DBAttributes("TOTAL_PAYMENTS_POSTED", null, "NUMERIC", 0 ,true);
		m_dbAttribs[63] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[64] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[65] = new DBAttributes("IS_FOR_STOCK", null, "CHAR", 1 ,true);
		m_dbAttribs[66] = new DBAttributes("IS_DIRECT_CUST_SHIP", null, "CHAR", 1 ,true);
		m_dbAttribs[67] = new DBAttributes("VENDOR_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[68] = new DBAttributes("BILLTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[69] = new DBAttributes("SHIPTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[70] = new DBAttributes("OUR_CONTACT", null, "VARCHAR", 50 ,true);
		m_dbAttribs[71] = new DBAttributes("OUR_CONTACT_INFO", null, "VARCHAR", 50 ,true);
		m_dbAttribs[72] = new DBAttributes("VENDOR_CONTACT_INFO", null, "VARCHAR", 50 ,true);
		m_dbAttribs[73] = new DBAttributes("VENDOR_CONTACT", null, "VARCHAR", 50 ,true);
		m_dbAttribs[74] = new DBAttributes("IS_FOR_CUST", null, "CHAR", 1 ,true);
		m_dbAttribs[75] = new DBAttributes("CUST_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[76] = new DBAttributes("CUST_NAME", null, "VARCHAR", 50 ,true);
      //added manually
      m_dbAttribs[77] = new DBAttributes("VENDOR_FAX", null, "VARCHAR", 25, true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_date_created() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_purch_type() {return m_dbAttribs[4].getValue();}
	public String get_purch_date() {return m_dbAttribs[5].getValue();}
	public String get_vendor_id() {return m_dbAttribs[6].getValue();}
	public String get_vendor_name() {return m_dbAttribs[7].getValue();}
	public String get_vendor_addr1() {return m_dbAttribs[8].getValue();}
	public String get_vendor_addr2() {return m_dbAttribs[9].getValue();}
	public String get_vendor_addr3() {return m_dbAttribs[10].getValue();}
	public String get_vendor_addr4() {return m_dbAttribs[11].getValue();}
	public String get_vendor_city() {return m_dbAttribs[12].getValue();}
	public String get_vendor_state_code() {return m_dbAttribs[13].getValue();}
	public String get_vendor_zip() {return m_dbAttribs[14].getValue();}
	public String get_vendor_country_code() {return m_dbAttribs[15].getValue();}
	public String get_vendor_country_name() {return m_dbAttribs[16].getValue();}
	public String get_shipto_id() {return m_dbAttribs[17].getValue();}
	public String get_shipto_addr1() {return m_dbAttribs[18].getValue();}
	public String get_shipto_addr2() {return m_dbAttribs[19].getValue();}
	public String get_shipto_addr3() {return m_dbAttribs[20].getValue();}
	public String get_shipto_addr4() {return m_dbAttribs[21].getValue();}
	public String get_shipto_city() {return m_dbAttribs[22].getValue();}
	public String get_shipto_state_code() {return m_dbAttribs[23].getValue();}
	public String get_shipto_zip() {return m_dbAttribs[24].getValue();}
	public String get_shipto_country_code() {return m_dbAttribs[25].getValue();}
	public String get_shipto_country_name() {return m_dbAttribs[26].getValue();}
	public String get_billto_id() {return m_dbAttribs[27].getValue();}
	public String get_billto_addr1() {return m_dbAttribs[28].getValue();}
	public String get_billto_addr2() {return m_dbAttribs[29].getValue();}
	public String get_billto_addr3() {return m_dbAttribs[30].getValue();}
	public String get_billto_addr4() {return m_dbAttribs[31].getValue();}
	public String get_billto_city() {return m_dbAttribs[32].getValue();}
	public String get_billto_state_code() {return m_dbAttribs[33].getValue();}
	public String get_billto_zip() {return m_dbAttribs[34].getValue();}
	public String get_billto_country_code() {return m_dbAttribs[35].getValue();}
	public String get_billto_country_name() {return m_dbAttribs[36].getValue();}
	public String get_sales_rep() {return m_dbAttribs[37].getValue();}
	public String get_purch_status() {return m_dbAttribs[38].getValue();}
	public String get_date_promised() {return m_dbAttribs[39].getValue();}
	public String get_date_needed() {return m_dbAttribs[40].getValue();}
	public String get_account_num() {return m_dbAttribs[41].getValue();}
	public String get_account_name() {return m_dbAttribs[42].getValue();}
	public String get_payment_terms() {return m_dbAttribs[43].getValue();}
	public String get_freight_terms() {return m_dbAttribs[44].getValue();}
	public String get_carrier_id() {return m_dbAttribs[45].getValue();}
	public String get_carrier_name() {return m_dbAttribs[46].getValue();}
	public String get_cod() {return m_dbAttribs[47].getValue();}
	public String get_fob() {return m_dbAttribs[48].getValue();}
	public String get_tax_rate() {return m_dbAttribs[49].getValue();}
	public String get_air_bill_num() {return m_dbAttribs[50].getValue();}
	public String get_currency() {return m_dbAttribs[51].getValue();}
	public String get_exchange_rate() {return m_dbAttribs[52].getValue();}
	public String get_subtotal_override() {return m_dbAttribs[53].getValue();}
	public String get_subtotal() {return m_dbAttribs[54].getValue();}
	public String get_total_value() {return m_dbAttribs[55].getValue();}
	public String get_total_discount() {return m_dbAttribs[56].getValue();}
	public String get_total_tax() {return m_dbAttribs[57].getValue();}
	public String get_total_shipping() {return m_dbAttribs[58].getValue();}
	public String get_payment_due_date() {return m_dbAttribs[59].getValue();}
	public String get_purch_order_type() {return m_dbAttribs[60].getValue();}
	public String get_shipto_name() {return m_dbAttribs[61].getValue();}
	public String get_total_payments_posted() {return m_dbAttribs[62].getValue();}
	public String get_note1() {return m_dbAttribs[63].getValue();}
	public String get_note2() {return m_dbAttribs[64].getValue();}
	public String get_is_for_stock() {return m_dbAttribs[65].getValue();}
	public String get_is_direct_cust_ship() {return m_dbAttribs[66].getValue();}
	public String get_vendor_attn() {return m_dbAttribs[67].getValue();}
	public String get_billto_attn() {return m_dbAttribs[68].getValue();}
	public String get_shipto_attn() {return m_dbAttribs[69].getValue();}
	public String get_our_contact() {return m_dbAttribs[70].getValue();}
	public String get_our_contact_info() {return m_dbAttribs[71].getValue();}
	public String get_vendor_contact_info() {return m_dbAttribs[72].getValue();}
	public String get_vendor_contact() {return m_dbAttribs[73].getValue();}
	public String get_is_for_cust() {return m_dbAttribs[74].getValue();}
	public String get_cust_id() {return m_dbAttribs[75].getValue();}
	public String get_cust_name() {return m_dbAttribs[76].getValue();}
   //added manually
   public String get_vendor_fax() {return m_dbAttribs[77].getValue();}

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

	public void set_purch_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_purch_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_vendor_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_vendor_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_vendor_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_vendor_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_vendor_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_vendor_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_vendor_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_vendor_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_vendor_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_vendor_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_vendor_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_shipto_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_shipto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_shipto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_shipto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_shipto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_shipto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_shipto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_shipto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_shipto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_shipto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_billto_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_billto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_billto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_billto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_billto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_billto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_billto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_billto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_billto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_billto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_sales_rep(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_purch_status(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_date_promised(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_date_needed(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_account_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_account_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public void set_payment_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[43].setValue(null);
		else
			m_dbAttribs[43].setValue(val);
	}

	public void set_freight_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[44].setValue(null);
		else
			m_dbAttribs[44].setValue(val);
	}

	public void set_carrier_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[45].setValue(null);
		else
			m_dbAttribs[45].setValue(val);
	}

	public void set_carrier_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[46].setValue(null);
		else
			m_dbAttribs[46].setValue(val);
	}

	public void set_cod(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[47].setValue(null);
		else
			m_dbAttribs[47].setValue(val);
	}

	public void set_fob(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[48].setValue(null);
		else
			m_dbAttribs[48].setValue(val);
	}

	public void set_tax_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[49].setValue(null);
		else
			m_dbAttribs[49].setValue(val);
	}

	public void set_air_bill_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[50].setValue(null);
		else
			m_dbAttribs[50].setValue(val);
	}

	public void set_currency(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[51].setValue(null);
		else
			m_dbAttribs[51].setValue(val);
	}

	public void set_exchange_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[52].setValue(null);
		else
			m_dbAttribs[52].setValue(val);
	}

	public void set_subtotal_override(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[53].setValue(null);
		else
			m_dbAttribs[53].setValue(val);
	}

	public void set_subtotal(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[54].setValue(null);
		else
			m_dbAttribs[54].setValue(val);
	}

	public void set_total_value(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[55].setValue(null);
		else
			m_dbAttribs[55].setValue(val);
	}

	public void set_total_discount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[56].setValue(null);
		else
			m_dbAttribs[56].setValue(val);
	}

	public void set_total_tax(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[57].setValue(null);
		else
			m_dbAttribs[57].setValue(val);
	}

	public void set_total_shipping(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[58].setValue(null);
		else
			m_dbAttribs[58].setValue(val);
	}

	public void set_payment_due_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[59].setValue(null);
		else
			m_dbAttribs[59].setValue(val);
	}

	public void set_purch_order_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[60].setValue(null);
		else
			m_dbAttribs[60].setValue(val);
	}

	public void set_shipto_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[61].setValue(null);
		else
			m_dbAttribs[61].setValue(val);
	}

	public void set_total_payments_posted(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[62].setValue(null);
		else
			m_dbAttribs[62].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[63].setValue(null);
		else
			m_dbAttribs[63].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[64].setValue(null);
		else
			m_dbAttribs[64].setValue(val);
	}

	public void set_is_for_stock(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[65].setValue(null);
		else
			m_dbAttribs[65].setValue(val);
	}

	public void set_is_direct_cust_ship(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[66].setValue(null);
		else
			m_dbAttribs[66].setValue(val);
	}

	public void set_vendor_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[67].setValue(null);
		else
			m_dbAttribs[67].setValue(val);
	}

	public void set_billto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[68].setValue(null);
		else
			m_dbAttribs[68].setValue(val);
	}

	public void set_shipto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[69].setValue(null);
		else
			m_dbAttribs[69].setValue(val);
	}

	public void set_our_contact(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[70].setValue(null);
		else
			m_dbAttribs[70].setValue(val);
	}

	public void set_our_contact_info(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[71].setValue(null);
		else
			m_dbAttribs[71].setValue(val);
	}

	public void set_vendor_contact_info(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[72].setValue(null);
		else
			m_dbAttribs[72].setValue(val);
	}

	public void set_vendor_contact(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[73].setValue(null);
		else
			m_dbAttribs[73].setValue(val);
	}

	public void set_is_for_cust(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[74].setValue(null);
		else
			m_dbAttribs[74].setValue(val);
	}

	public void set_cust_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[75].setValue(null);
		else
			m_dbAttribs[75].setValue(val);
	}

	public void set_cust_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[76].setValue(null);
		else
			m_dbAttribs[76].setValue(val);
	}

   //added manually
   public void set_vendor_fax(String val)
   {
      if (val == null || val.length() == 0)
         m_dbAttribs[77].setValue(null);
      else
         m_dbAttribs[77].setValue(val);
   }

	public BusinessObject getNewInstance()
	{
		return new purch_orderObj();
	}


}
