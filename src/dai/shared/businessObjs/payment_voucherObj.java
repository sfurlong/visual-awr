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

public class payment_voucherObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

	//Transient attributes just used to pass data accross client/server connections.
	static public final String _SHIP_CHARGES_ACCT_ID = "_SHIP_CHARGES_ACCT_ID";
	static public final String _SHIP_CHARGES_ACCT_NAME = "_SHIP_CHARGES_ACCT_NAME";
	static public final String _NUM_PAYMENTS = "_NUM_PAYMENTS";

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
		//Add the current date to the Date Created Field.
		Calendar now = Calendar.getInstance();
		SessionMetaData sessionMeta = null;
		sessionMeta = SessionMetaData.getInstance();

		set_date_created(now.get(Calendar.MONTH)+1 + "/" +
						 now.get(Calendar.DAY_OF_MONTH) + "/" +
						 now.get(Calendar.YEAR));

		set_locality(sessionMeta.getLocality());

		set_created_by(sessionMeta.getUserId());
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "PAYMENT_VOUCHER";
	static public final String ID = "PAYMENT_VOUCHER.ID";
	static public final String LOCALITY = "PAYMENT_VOUCHER.LOCALITY";
	static public final String CREATED_BY = "PAYMENT_VOUCHER.CREATED_BY";
	static public final String DATE_CREATED = "PAYMENT_VOUCHER.DATE_CREATED";
	static public final String CHECK_NUM = "PAYMENT_VOUCHER.CHECK_NUM";
	static public final String PAYMENT_METHOD = "PAYMENT_VOUCHER.PAYMENT_METHOD";
	static public final String CC_NUM = "PAYMENT_VOUCHER.CC_NUM";
	static public final String CC_EXP_DATE = "PAYMENT_VOUCHER.CC_EXP_DATE";
	static public final String NOTE = "PAYMENT_VOUCHER.NOTE";
	static public final String PAYMENT_AMT = "PAYMENT_VOUCHER.PAYMENT_AMT";
	static public final String PRINT_CHECK = "PAYMENT_VOUCHER.PRINT_CHECK";
	static public final String ACCTID = "PAYMENT_VOUCHER.ACCTID";
	static public final String ACCTNAME = "PAYMENT_VOUCHER.ACCTNAME";
	static public final String INVOICE_NUM = "PAYMENT_VOUCHER.INVOICE_NUM";
	static public final String INVOICE_DATE = "PAYMENT_VOUCHER.INVOICE_DATE";
	static public final String PURCH_ORDER_ID = "PAYMENT_VOUCHER.PURCH_ORDER_ID";
	static public final String TOTAL_VALUE = "PAYMENT_VOUCHER.TOTAL_VALUE";
	static public final String TOTAL_PAYMENTS_POSTED = "PAYMENT_VOUCHER.TOTAL_PAYMENTS_POSTED";
	static public final String VENDOR_ID = "PAYMENT_VOUCHER.VENDOR_ID";
	static public final String VENDOR_NAME = "PAYMENT_VOUCHER.VENDOR_NAME";
	static public final String PAYMENT_DUE_DATE = "PAYMENT_VOUCHER.PAYMENT_DUE_DATE";
	static public final String PAYMENT_TERMS = "PAYMENT_VOUCHER.PAYMENT_TERMS";
	static public final String DATE_PAID = "PAYMENT_VOUCHER.DATE_PAID";
	static public final String PAY_FROM_ACCT_ID = "PAYMENT_VOUCHER.PAY_FROM_ACCT_ID";
	static public final String PAY_FROM_ACCT_NAME = "PAYMENT_VOUCHER.PAY_FROM_ACCT_NAME";
	static public final String TOTAL_SHIPPING_CHARGES = "PAYMENT_VOUCHER.TOTAL_SHIPPING_CHARGES";
	static public final String SUBTOTAL_AMT = "PAYMENT_VOUCHER.SUBTOTAL_AMT";
	static public final String CHECK_TOT_AMT_FOR_PRINTING = "PAYMENT_VOUCHER.CHECK_TOT_AMT_FOR_PRINTING";
	static public final String CHECK_TOT_AMT_TEXT_FOR_PRINTING = "PAYMENT_VOUCHER.CHECK_TOT_AMT_TEXT_FOR_PRINTING";
	static public final String VENDOR_ADDR1 = "PAYMENT_VOUCHER.VENDOR_ADDR1";
	static public final String VENDOR_ADDR2 = "PAYMENT_VOUCHER.VENDOR_ADDR2";
	static public final String VENDOR_ADDR3 = "PAYMENT_VOUCHER.VENDOR_ADDR3";
	static public final String VENDOR_ADDR4 = "PAYMENT_VOUCHER.VENDOR_ADDR4";
	static public final String VENDOR_CITY = "PAYMENT_VOUCHER.VENDOR_CITY";
	static public final String VENDOR_STATE_CODE = "PAYMENT_VOUCHER.VENDOR_STATE_CODE";
	static public final String VENDOR_ZIP = "PAYMENT_VOUCHER.VENDOR_ZIP";
	static public final String VENDOR_COUNTRY_CODE = "PAYMENT_VOUCHER.VENDOR_COUNTRY_CODE";
	static public final String VENDOR_COUNTRY_NAME = "PAYMENT_VOUCHER.VENDOR_COUNTRY_NAME";
	static public final String OUR_ACCT_NO_WITH_VENDOR = "PAYMENT_VOUCHER.OUR_ACCT_NO_WITH_VENDOR";
	static public final String IS_VOIDED = "PAYMENT_VOUCHER.IS_VOIDED";
	static public final String DATE_CHECK_SENT = "PAYMENT_VOUCHER.DATE_CHECK_SENT";


	//Constructor
	public payment_voucherObj()
	{
		//Private Field Members.
		MAX_COLS = 41;
		TAB_NAME = "PAYMENT_VOUCHER";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init()
	{
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[3] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[4] = new DBAttributes("CHECK_NUM", null, "VARCHAR", 20 ,true);
		m_dbAttribs[5] = new DBAttributes("PAYMENT_METHOD", null, "VARCHAR", 20 ,true);
		m_dbAttribs[6] = new DBAttributes("CC_NUM", null, "VARCHAR", 20 ,true);
		m_dbAttribs[7] = new DBAttributes("CC_EXP_DATE", null, "VARCHAR", 12 ,true);
		m_dbAttribs[8] = new DBAttributes("NOTE", null, "VARCHAR", 80 ,true);
		m_dbAttribs[9] = new DBAttributes("PAYMENT_AMT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[10] = new DBAttributes("PRINT_CHECK", null, "CHAR", 1 ,true);
		m_dbAttribs[11] = new DBAttributes("ACCTID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[12] = new DBAttributes("ACCTNAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[13] = new DBAttributes("INVOICE_NUM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[14] = new DBAttributes("INVOICE_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[15] = new DBAttributes("PURCH_ORDER_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[16] = new DBAttributes("TOTAL_VALUE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[17] = new DBAttributes("TOTAL_PAYMENTS_POSTED", null, "NUMERIC", 0 ,true);
		m_dbAttribs[18] = new DBAttributes("VENDOR_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[19] = new DBAttributes("VENDOR_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[20] = new DBAttributes("PAYMENT_DUE_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[21] = new DBAttributes("PAYMENT_TERMS", null, "VARCHAR", 30 ,true);
		m_dbAttribs[22] = new DBAttributes("DATE_PAID", null, "DATE", 19 ,true);
		m_dbAttribs[23] = new DBAttributes("PAY_FROM_ACCT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[24] = new DBAttributes("PAY_FROM_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[25] = new DBAttributes("TOTAL_SHIPPING_CHARGES", null, "NUMERIC", 0 ,true);
		m_dbAttribs[26] = new DBAttributes("SUBTOTAL_AMT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[27] = new DBAttributes("CHECK_TOT_AMT_FOR_PRINTING", null, "NUMERIC", 0 ,true);
		m_dbAttribs[28] = new DBAttributes("CHECK_TOT_AMT_TEXT_FOR_PRINTING", null, "VARCHAR", 132 ,true);
		m_dbAttribs[29] = new DBAttributes("VENDOR_ADDR1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[30] = new DBAttributes("VENDOR_ADDR2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[31] = new DBAttributes("VENDOR_ADDR3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[32] = new DBAttributes("VENDOR_ADDR4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[33] = new DBAttributes("VENDOR_CITY", null, "VARCHAR", 50 ,true);
		m_dbAttribs[34] = new DBAttributes("VENDOR_STATE_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[35] = new DBAttributes("VENDOR_ZIP", null, "VARCHAR", 15 ,true);
		m_dbAttribs[36] = new DBAttributes("VENDOR_COUNTRY_CODE", null, "VARCHAR", 2 ,true);
		m_dbAttribs[37] = new DBAttributes("VENDOR_COUNTRY_NAME", null, "VARCHAR", 30 ,true);
		m_dbAttribs[38] = new DBAttributes("OUR_ACCT_NO_WITH_VENDOR", null, "VARCHAR", 30 ,true);
		m_dbAttribs[39] = new DBAttributes("IS_VOIDED", null, "CHAR", 1 ,true);
		m_dbAttribs[40] = new DBAttributes("DATE_CHECK_SENT", null, "DATE", 19 ,true);
	}
	public String get_id()
	{return m_dbAttribs[0].getValue();}
	public String get_locality()
	{return m_dbAttribs[1].getValue();}
	public String get_created_by()
	{return m_dbAttribs[2].getValue();}
	public String get_date_created()
	{return m_dbAttribs[3].getValue();}
	public String get_check_num()
	{return m_dbAttribs[4].getValue();}
	public String get_payment_method()
	{return m_dbAttribs[5].getValue();}
	public String get_cc_num()
	{return m_dbAttribs[6].getValue();}
	public String get_cc_exp_date()
	{return m_dbAttribs[7].getValue();}
	public String get_note()
	{return m_dbAttribs[8].getValue();}
	public String get_payment_amt()
	{return m_dbAttribs[9].getValue();}
	public String get_print_check()
	{return m_dbAttribs[10].getValue();}
	public String get_acctid()
	{return m_dbAttribs[11].getValue();}
	public String get_acctname()
	{return m_dbAttribs[12].getValue();}
	public String get_invoice_num()
	{return m_dbAttribs[13].getValue();}
	public String get_invoice_date()
	{return m_dbAttribs[14].getValue();}
	public String get_purch_order_id()
	{return m_dbAttribs[15].getValue();}
	public String get_total_value()
	{return m_dbAttribs[16].getValue();}
	public String get_total_payments_posted()
	{return m_dbAttribs[17].getValue();}
	public String get_vendor_id()
	{return m_dbAttribs[18].getValue();}
	public String get_vendor_name()
	{return m_dbAttribs[19].getValue();}
	public String get_payment_due_date()
	{return m_dbAttribs[20].getValue();}
	public String get_payment_terms()
	{return m_dbAttribs[21].getValue();}
	public String get_date_paid()
	{return m_dbAttribs[22].getValue();}
	public String get_pay_from_acct_id()
	{return m_dbAttribs[23].getValue();}
	public String get_pay_from_acct_name()
	{return m_dbAttribs[24].getValue();}
	public String get_total_shipping_charges()
	{return m_dbAttribs[25].getValue();}
	public String get_subtotal_amt()
	{return m_dbAttribs[26].getValue();}
	public String get_check_tot_amt_for_printing()
	{return m_dbAttribs[27].getValue();}
	public String get_check_tot_amt_text_for_printing()
	{return m_dbAttribs[28].getValue();}
	public String get_vendor_addr1()
	{return m_dbAttribs[29].getValue();}
	public String get_vendor_addr2()
	{return m_dbAttribs[30].getValue();}
	public String get_vendor_addr3()
	{return m_dbAttribs[31].getValue();}
	public String get_vendor_addr4()
	{return m_dbAttribs[32].getValue();}
	public String get_vendor_city()
	{return m_dbAttribs[33].getValue();}
	public String get_vendor_state_code()
	{return m_dbAttribs[34].getValue();}
	public String get_vendor_zip()
	{return m_dbAttribs[35].getValue();}
	public String get_vendor_country_code()
	{return m_dbAttribs[36].getValue();}
	public String get_vendor_country_name()
	{return m_dbAttribs[37].getValue();}
	public String get_our_acct_no_with_vendor()
	{return m_dbAttribs[38].getValue();}
	public String get_is_voided()
	{return m_dbAttribs[39].getValue();}
	public String get_date_check_sent()
	{return m_dbAttribs[40].getValue();}
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

	public void set_check_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_payment_method(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_cc_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_cc_exp_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_payment_amt(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_print_check(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_acctid(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_acctname(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_invoice_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_invoice_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_purch_order_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_total_value(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_total_payments_posted(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_vendor_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_vendor_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_payment_due_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_payment_terms(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_date_paid(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_pay_from_acct_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_pay_from_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_total_shipping_charges(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_subtotal_amt(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_check_tot_amt_for_printing(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_check_tot_amt_text_for_printing(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_vendor_addr1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_vendor_addr2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_vendor_addr3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_vendor_addr4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_vendor_city(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_vendor_state_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_vendor_zip(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_vendor_country_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_vendor_country_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_our_acct_no_with_vendor(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_is_voided(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_date_check_sent(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new payment_voucherObj();
	}


}
