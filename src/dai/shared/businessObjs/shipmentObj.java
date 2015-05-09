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

public class shipmentObj extends BusinessObject
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

		set_locality(sessionMeta.getLocality());

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
	static public final String TABLE_NAME = "SHIPMENT";
	static public final String ID = "SHIPMENT.ID";
	static public final String LOCALITY = "SHIPMENT.LOCALITY";
	static public final String DATE_CREATED = "SHIPMENT.DATE_CREATED";
	static public final String CREATED_BY = "SHIPMENT.CREATED_BY";
	static public final String ORDER_NUM = "SHIPMENT.ORDER_NUM";
	static public final String SHIPMENT_TYPE = "SHIPMENT.SHIPMENT_TYPE";
	static public final String PO_NUM = "SHIPMENT.PO_NUM";
	static public final String PO_DATE = "SHIPMENT.PO_DATE";
	static public final String CUSTOMER_ID = "SHIPMENT.CUSTOMER_ID";
	static public final String SHIPTO_ADDR1 = "SHIPMENT.SHIPTO_ADDR1";
	static public final String SHIPTO_ADDR2 = "SHIPMENT.SHIPTO_ADDR2";
	static public final String SHIPTO_ADDR3 = "SHIPMENT.SHIPTO_ADDR3";
	static public final String SHIPTO_ADDR4 = "SHIPMENT.SHIPTO_ADDR4";
	static public final String SHIPTO_CITY = "SHIPMENT.SHIPTO_CITY";
	static public final String SHIPTO_STATE_CODE = "SHIPMENT.SHIPTO_STATE_CODE";
	static public final String SHIPTO_ZIP = "SHIPMENT.SHIPTO_ZIP";
	static public final String SHIPTO_COUNTRY_CODE = "SHIPMENT.SHIPTO_COUNTRY_CODE";
	static public final String SHIPTO_COUNTRY_NAME = "SHIPMENT.SHIPTO_COUNTRY_NAME";
	static public final String BILLTO_ADDR1 = "SHIPMENT.BILLTO_ADDR1";
	static public final String BILLTO_ADDR2 = "SHIPMENT.BILLTO_ADDR2";
	static public final String BILLTO_ADDR3 = "SHIPMENT.BILLTO_ADDR3";
	static public final String BILLTO_ADDR4 = "SHIPMENT.BILLTO_ADDR4";
	static public final String BILLTO_CITY = "SHIPMENT.BILLTO_CITY";
	static public final String BILLTO_STATE_CODE = "SHIPMENT.BILLTO_STATE_CODE";
	static public final String BILLTO_ZIP = "SHIPMENT.BILLTO_ZIP";
	static public final String BILLTO_COUNTRY_CODE = "SHIPMENT.BILLTO_COUNTRY_CODE";
	static public final String BILLTO_COUNTRY_NAME = "SHIPMENT.BILLTO_COUNTRY_NAME";
	static public final String SALES_REP = "SHIPMENT.SALES_REP";
	static public final String IS_REPAIR = "SHIPMENT.IS_REPAIR";
	static public final String COMMISION_AMOUNT = "SHIPMENT.COMMISION_AMOUNT";
	static public final String DATE_SHIPPED = "SHIPMENT.DATE_SHIPPED";
	static public final String DATE_PROMISED = "SHIPMENT.DATE_PROMISED";
	static public final String DATE_NEEDED = "SHIPMENT.DATE_NEEDED";
	static public final String PAYMENT_TERMS = "SHIPMENT.PAYMENT_TERMS";
	static public final String FREIGHT_TERMS = "SHIPMENT.FREIGHT_TERMS";
	static public final String CARRIER_ID = "SHIPMENT.CARRIER_ID";
	static public final String CARRIER_NAME = "SHIPMENT.CARRIER_NAME";
	static public final String COD = "SHIPMENT.COD";
	static public final String FOB = "SHIPMENT.FOB";
	static public final String TAX_RATE = "SHIPMENT.TAX_RATE";
	static public final String BOL_NUM = "SHIPMENT.BOL_NUM";
	static public final String AIR_BILL_NUM = "SHIPMENT.AIR_BILL_NUM";
	static public final String CURRENCY = "SHIPMENT.CURRENCY";
	static public final String EXCHANGE_RATE = "SHIPMENT.EXCHANGE_RATE";
	static public final String SUBTOTAL_OVERRIDE = "SHIPMENT.SUBTOTAL_OVERRIDE";
	static public final String SUBTOTAL = "SHIPMENT.SUBTOTAL";
	static public final String TOTAL_VALUE = "SHIPMENT.TOTAL_VALUE";
	static public final String TOTAL_DISCOUNT = "SHIPMENT.TOTAL_DISCOUNT";
	static public final String TOTAL_TAX = "SHIPMENT.TOTAL_TAX";
	static public final String TOTAL_SHIPPING = "SHIPMENT.TOTAL_SHIPPING";
	static public final String CUSTOMER_NAME = "SHIPMENT.CUSTOMER_NAME";
	static public final String SHIPMENT_STATUS = "SHIPMENT.SHIPMENT_STATUS";
	static public final String TOTAL_CASH_RECEIVED = "SHIPMENT.TOTAL_CASH_RECEIVED";
	static public final String NOTE1 = "SHIPMENT.NOTE1";
	static public final String NOTE2 = "SHIPMENT.NOTE2";
	static public final String SHIPTO_ATTN = "SHIPMENT.SHIPTO_ATTN";
	static public final String BILLTO_ATTN = "SHIPMENT.BILLTO_ATTN";
	static public final String IS_CREDIT_MEMO = "SHIPMENT.IS_CREDIT_MEMO";
	static public final String TOTAL_RESTOCK_FEE = "SHIPMENT.TOTAL_RESTOCK_FEE";
	static public final String IS_CANCELED = "SHIPMENT.IS_CANCELED";
	static public final String SHIPTO_IS_DROPSHIP = "SHIPMENT.SHIPTO_IS_DROPSHIP";


	//Constructor
	public shipmentObj()
	{
		//Private Field Members.
		MAX_COLS = 61;
		TAB_NAME = "SHIPMENT";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("ORDER_NUM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[5] = new DBAttributes("SHIPMENT_TYPE", null, "VARCHAR", 30 ,true);
		m_dbAttribs[6] = new DBAttributes("PO_NUM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[7] = new DBAttributes("PO_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[8] = new DBAttributes("CUSTOMER_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[9] = new DBAttributes("SHIPTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[10] = new DBAttributes("SHIPTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[11] = new DBAttributes("SHIPTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("SHIPTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[13] = new DBAttributes("SHIPTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[14] = new DBAttributes("SHIPTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[15] = new DBAttributes("SHIPTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[16] = new DBAttributes("SHIPTO_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[17] = new DBAttributes("SHIPTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[18] = new DBAttributes("BILLTO_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[19] = new DBAttributes("BILLTO_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[20] = new DBAttributes("BILLTO_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[21] = new DBAttributes("BILLTO_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[22] = new DBAttributes("BILLTO_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[23] = new DBAttributes("BILLTO_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[24] = new DBAttributes("BILLTO_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[25] = new DBAttributes("BILLTO_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[26] = new DBAttributes("BILLTO_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[27] = new DBAttributes("SALES_REP", null, "VARCHAR", 30 ,true);
		m_dbAttribs[28] = new DBAttributes("IS_REPAIR", null, "CHAR", 1 ,true);
		m_dbAttribs[29] = new DBAttributes("COMMISION_AMOUNT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[30] = new DBAttributes("DATE_SHIPPED", null, "DATE", 19 ,true);
		m_dbAttribs[31] = new DBAttributes("DATE_PROMISED", null, "DATE", 19 ,true);
		m_dbAttribs[32] = new DBAttributes("DATE_NEEDED", null, "DATE", 19 ,true);
		m_dbAttribs[33] = new DBAttributes("PAYMENT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[34] = new DBAttributes("FREIGHT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[35] = new DBAttributes("CARRIER_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[36] = new DBAttributes("CARRIER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[37] = new DBAttributes("COD", null, "VARCHAR", 50 ,true);
		m_dbAttribs[38] = new DBAttributes("FOB", null, "VARCHAR", 50 ,true);
		m_dbAttribs[39] = new DBAttributes("TAX_RATE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[40] = new DBAttributes("BOL_NUM", null, "CHAR", 30 ,true);
		m_dbAttribs[41] = new DBAttributes("AIR_BILL_NUM", null, "CHAR", 30 ,true);
		m_dbAttribs[42] = new DBAttributes("CURRENCY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[43] = new DBAttributes("EXCHANGE_RATE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[44] = new DBAttributes("SUBTOTAL_OVERRIDE", null, "VARCHAR", 1 ,true);
		m_dbAttribs[45] = new DBAttributes("SUBTOTAL", null, "NUMERIC", 0 ,true);
		m_dbAttribs[46] = new DBAttributes("TOTAL_VALUE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[47] = new DBAttributes("TOTAL_DISCOUNT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[48] = new DBAttributes("TOTAL_TAX", null, "NUMERIC", 0 ,true);
		m_dbAttribs[49] = new DBAttributes("TOTAL_SHIPPING", null, "NUMERIC", 0 ,true);
		m_dbAttribs[50] = new DBAttributes("CUSTOMER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[51] = new DBAttributes("SHIPMENT_STATUS", null, "VARCHAR", 20 ,true);
		m_dbAttribs[52] = new DBAttributes("TOTAL_CASH_RECEIVED", null, "NUMERIC", 0 ,true);
		m_dbAttribs[53] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[54] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[55] = new DBAttributes("SHIPTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[56] = new DBAttributes("BILLTO_ATTN", null, "VARCHAR", 50 ,true);
		m_dbAttribs[57] = new DBAttributes("IS_CREDIT_MEMO", null, "CHAR", 1 ,true);
		m_dbAttribs[58] = new DBAttributes("TOTAL_RESTOCK_FEE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[59] = new DBAttributes("IS_CANCELED", null, "CHAR", 1 ,true);
		m_dbAttribs[60] = new DBAttributes("SHIPTO_IS_DROPSHIP", null, "CHAR", 1 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_date_created() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_order_num() {return m_dbAttribs[4].getValue();}
	public String get_shipment_type() {return m_dbAttribs[5].getValue();}
	public String get_po_num() {return m_dbAttribs[6].getValue();}
	public String get_po_date() {return m_dbAttribs[7].getValue();}
	public String get_customer_id() {return m_dbAttribs[8].getValue();}
	public String get_shipto_addr1() {return m_dbAttribs[9].getValue();}
	public String get_shipto_addr2() {return m_dbAttribs[10].getValue();}
	public String get_shipto_addr3() {return m_dbAttribs[11].getValue();}
	public String get_shipto_addr4() {return m_dbAttribs[12].getValue();}
	public String get_shipto_city() {return m_dbAttribs[13].getValue();}
	public String get_shipto_state_code() {return m_dbAttribs[14].getValue();}
	public String get_shipto_zip() {return m_dbAttribs[15].getValue();}
	public String get_shipto_country_code() {return m_dbAttribs[16].getValue();}
	public String get_shipto_country_name() {return m_dbAttribs[17].getValue();}
	public String get_billto_addr1() {return m_dbAttribs[18].getValue();}
	public String get_billto_addr2() {return m_dbAttribs[19].getValue();}
	public String get_billto_addr3() {return m_dbAttribs[20].getValue();}
	public String get_billto_addr4() {return m_dbAttribs[21].getValue();}
	public String get_billto_city() {return m_dbAttribs[22].getValue();}
	public String get_billto_state_code() {return m_dbAttribs[23].getValue();}
	public String get_billto_zip() {return m_dbAttribs[24].getValue();}
	public String get_billto_country_code() {return m_dbAttribs[25].getValue();}
	public String get_billto_country_name() {return m_dbAttribs[26].getValue();}
	public String get_sales_rep() {return m_dbAttribs[27].getValue();}
	public String get_is_repair() {return m_dbAttribs[28].getValue();}
	public String get_commision_amount() {return m_dbAttribs[29].getValue();}
	public String get_date_shipped() {return m_dbAttribs[30].getValue();}
	public String get_date_promised() {return m_dbAttribs[31].getValue();}
	public String get_date_needed() {return m_dbAttribs[32].getValue();}
	public String get_payment_terms() {return m_dbAttribs[33].getValue();}
	public String get_freight_terms() {return m_dbAttribs[34].getValue();}
	public String get_carrier_id() {return m_dbAttribs[35].getValue();}
	public String get_carrier_name() {return m_dbAttribs[36].getValue();}
	public String get_cod() {return m_dbAttribs[37].getValue();}
	public String get_fob() {return m_dbAttribs[38].getValue();}
	public String get_tax_rate() {return m_dbAttribs[39].getValue();}
	public String get_bol_num() {return m_dbAttribs[40].getValue();}
	public String get_air_bill_num() {return m_dbAttribs[41].getValue();}
	public String get_currency() {return m_dbAttribs[42].getValue();}
	public String get_exchange_rate() {return m_dbAttribs[43].getValue();}
	public String get_subtotal_override() {return m_dbAttribs[44].getValue();}
	public String get_subtotal() {return m_dbAttribs[45].getValue();}
	public String get_total_value() {return m_dbAttribs[46].getValue();}
	public String get_total_discount() {return m_dbAttribs[47].getValue();}
	public String get_total_tax() {return m_dbAttribs[48].getValue();}
	public String get_total_shipping() {return m_dbAttribs[49].getValue();}
	public String get_customer_name() {return m_dbAttribs[50].getValue();}
	public String get_shipment_status() {return m_dbAttribs[51].getValue();}
	public String get_total_cash_received() {return m_dbAttribs[52].getValue();}
	public String get_note1() {return m_dbAttribs[53].getValue();}
	public String get_note2() {return m_dbAttribs[54].getValue();}
	public String get_shipto_attn() {return m_dbAttribs[55].getValue();}
	public String get_billto_attn() {return m_dbAttribs[56].getValue();}
	public String get_is_credit_memo() {return m_dbAttribs[57].getValue();}
	public String get_total_restock_fee() {return m_dbAttribs[58].getValue();}
	public String get_is_canceled() {return m_dbAttribs[59].getValue();}
	public String get_shipto_is_dropship() {return m_dbAttribs[60].getValue();}
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

	public void set_order_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_shipment_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_po_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_po_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_customer_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_shipto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_shipto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_shipto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_shipto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_shipto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_shipto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_shipto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_shipto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_shipto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_billto_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_billto_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_billto_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_billto_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_billto_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_billto_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_billto_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_billto_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_billto_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_sales_rep(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_is_repair(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_commision_amount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_date_shipped(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_date_promised(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_date_needed(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_payment_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_freight_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_carrier_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_carrier_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_cod(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_fob(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_tax_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_bol_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_air_bill_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_currency(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public void set_exchange_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[43].setValue(null);
		else
			m_dbAttribs[43].setValue(val);
	}

	public void set_subtotal_override(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[44].setValue(null);
		else
			m_dbAttribs[44].setValue(val);
	}

	public void set_subtotal(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[45].setValue(null);
		else
			m_dbAttribs[45].setValue(val);
	}

	public void set_total_value(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[46].setValue(null);
		else
			m_dbAttribs[46].setValue(val);
	}

	public void set_total_discount(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[47].setValue(null);
		else
			m_dbAttribs[47].setValue(val);
	}

	public void set_total_tax(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[48].setValue(null);
		else
			m_dbAttribs[48].setValue(val);
	}

	public void set_total_shipping(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[49].setValue(null);
		else
			m_dbAttribs[49].setValue(val);
	}

	public void set_customer_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[50].setValue(null);
		else
			m_dbAttribs[50].setValue(val);
	}

	public void set_shipment_status(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[51].setValue(null);
		else
			m_dbAttribs[51].setValue(val);
	}

	public void set_total_cash_received(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[52].setValue(null);
		else
			m_dbAttribs[52].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[53].setValue(null);
		else
			m_dbAttribs[53].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[54].setValue(null);
		else
			m_dbAttribs[54].setValue(val);
	}

	public void set_shipto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[55].setValue(null);
		else
			m_dbAttribs[55].setValue(val);
	}

	public void set_billto_attn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[56].setValue(null);
		else
			m_dbAttribs[56].setValue(val);
	}

	public void set_is_credit_memo(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[57].setValue(null);
		else
			m_dbAttribs[57].setValue(val);
	}

	public void set_total_restock_fee(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[58].setValue(null);
		else
			m_dbAttribs[58].setValue(val);
	}

	public void set_is_canceled(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[59].setValue(null);
		else
			m_dbAttribs[59].setValue(val);
	}

	public void set_shipto_is_dropship(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[60].setValue(null);
		else
			m_dbAttribs[60].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new shipmentObj();
	}


}
