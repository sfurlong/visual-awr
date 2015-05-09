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

public class purch_order_itemObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

	//Transient Data Field.  This field is not stored in the table
	//It is used to temporarily store this value in the object.
	static public final String _QTY_TO_RECEIVE = "PURCH_ORDER_ITEM._QTY_TO_RECEIVE";
	private String _qtyToReceive;
	public String get_qtyToReceive()
	{
		return _qtyToReceive;
	}
	public void set_qtyToReceive(String val)
	{
		if (val == null || val.length() == 0)
			_qtyToReceive = null;
		else
			_qtyToReceive = val;
	}


	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		//Add the current date to the Date Created Field.
		Calendar now = Calendar.getInstance();
		set_date_created(now.get(now.MONTH)+1 + "/" +
						 now.get(now.DAY_OF_MONTH) + "/" +
						 now.get(now.YEAR));

		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();

		set_locality(sessionMeta.getLocality());
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
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("id", get_id(), "CHAR", true);
		imutables[1] = new DBAttributes("locality", get_locality(), "CHAR", true);
		return imutables;
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "PURCH_ORDER_ITEM";
	static public final String ID = "PURCH_ORDER_ITEM.ID";
	static public final String LOCALITY = "PURCH_ORDER_ITEM.LOCALITY";
	static public final String DETAIL_ID = "PURCH_ORDER_ITEM.DETAIL_ID";
	static public final String DATE_CREATED = "PURCH_ORDER_ITEM.DATE_CREATED";
	static public final String ITEM_ID = "PURCH_ORDER_ITEM.ITEM_ID";
	static public final String DESCRIPTION1 = "PURCH_ORDER_ITEM.DESCRIPTION1";
	static public final String DESCRIPTION2 = "PURCH_ORDER_ITEM.DESCRIPTION2";
	static public final String IS_HAZMAT = "PURCH_ORDER_ITEM.IS_HAZMAT";
	static public final String HAZMAT_DESCRIPT = "PURCH_ORDER_ITEM.HAZMAT_DESCRIPT";
	static public final String QTY_ORDERED = "PURCH_ORDER_ITEM.QTY_ORDERED";
	static public final String QTY_RECEIVED = "PURCH_ORDER_ITEM.QTY_RECEIVED";
	static public final String QTY_BACKORDER = "PURCH_ORDER_ITEM.QTY_BACKORDER";
	static public final String UNIT_PRICE = "PURCH_ORDER_ITEM.UNIT_PRICE";
	static public final String EXTENDED_PRICE = "PURCH_ORDER_ITEM.EXTENDED_PRICE";
	static public final String QTY_INVENTORY_POSTED = "PURCH_ORDER_ITEM.QTY_INVENTORY_POSTED";
	static public final String NOTE1 = "PURCH_ORDER_ITEM.NOTE1";
	static public final String NOTE2 = "PURCH_ORDER_ITEM.NOTE2";
	static public final String PURCH_PRICE = "PURCH_ORDER_ITEM.PURCH_PRICE";
	static public final String DISCOUNT_PERCENT = "PURCH_ORDER_ITEM.DISCOUNT_PERCENT";
	static public final String ACCOUNT_ID = "PURCH_ORDER_ITEM.ACCOUNT_ID";
	static public final String ACCOUNT_NAME = "PURCH_ORDER_ITEM.ACCOUNT_NAME";
	static public final String IS_ITEM_REPAIR = "PURCH_ORDER_ITEM.IS_ITEM_REPAIR";
	static public final String VENDOR_ITEM_ID = "PURCH_ORDER_ITEM.VENDOR_ITEM_ID";
	static public final String VENDOR_ITEM_DESC = "PURCH_ORDER_ITEM.VENDOR_ITEM_DESC";
	static public final String VENDOR_MODEL = "PURCH_ORDER_ITEM.VENDOR_MODEL";
	static public final String QTY_FOR_CUST = "PURCH_ORDER_ITEM.QTY_FOR_CUST";
	static public final String QTY_FOR_STOCK = "PURCH_ORDER_ITEM.QTY_FOR_STOCK";
	static public final String DATE_ITEM_EXPECTED = "PURCH_ORDER_ITEM.DATE_ITEM_EXPECTED";


	//Constructor
	public purch_order_itemObj()
	{
		//Private Field Members.
		MAX_COLS = 28;
		TAB_NAME = "PURCH_ORDER_ITEM";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", 10 ,true);
		m_dbAttribs[3] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[4] = new DBAttributes("ITEM_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[5] = new DBAttributes("DESCRIPTION1", null, "VARCHAR", 500 ,true);
		m_dbAttribs[6] = new DBAttributes("DESCRIPTION2", null, "VARCHAR", 500 ,true);
		m_dbAttribs[7] = new DBAttributes("IS_HAZMAT", null, "CHAR", 1 ,true);
		m_dbAttribs[8] = new DBAttributes("HAZMAT_DESCRIPT", null, "VARCHAR", 80 ,true);
		m_dbAttribs[9] = new DBAttributes("QTY_ORDERED", null, "INTEGER", 10 ,true);
		m_dbAttribs[10] = new DBAttributes("QTY_RECEIVED", null, "INTEGER", 10 ,true);
		m_dbAttribs[11] = new DBAttributes("QTY_BACKORDER", null, "INTEGER", 10 ,true);
		m_dbAttribs[12] = new DBAttributes("UNIT_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[13] = new DBAttributes("EXTENDED_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[14] = new DBAttributes("QTY_INVENTORY_POSTED", null, "INTEGER", 10 ,true);
		m_dbAttribs[15] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[16] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[17] = new DBAttributes("PURCH_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[18] = new DBAttributes("DISCOUNT_PERCENT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[19] = new DBAttributes("ACCOUNT_ID", null, "VARCHAR", 20 ,true);
		m_dbAttribs[20] = new DBAttributes("ACCOUNT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[21] = new DBAttributes("IS_ITEM_REPAIR", null, "CHAR", 1 ,true);
		m_dbAttribs[22] = new DBAttributes("VENDOR_ITEM_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[23] = new DBAttributes("VENDOR_ITEM_DESC", null, "VARCHAR", 500 ,true);
		m_dbAttribs[24] = new DBAttributes("VENDOR_MODEL", null, "VARCHAR", 30 ,true);
		m_dbAttribs[25] = new DBAttributes("QTY_FOR_CUST", null, "INTEGER", 10 ,true);
		m_dbAttribs[26] = new DBAttributes("QTY_FOR_STOCK", null, "INTEGER", 10 ,true);
		m_dbAttribs[27] = new DBAttributes("DATE_ITEM_EXPECTED", null, "DATE", 19 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_date_created() {return m_dbAttribs[3].getValue();}
	public String get_item_id() {return m_dbAttribs[4].getValue();}
	public String get_description1() {return m_dbAttribs[5].getValue();}
	public String get_description2() {return m_dbAttribs[6].getValue();}
	public String get_is_hazmat() {return m_dbAttribs[7].getValue();}
	public String get_hazmat_descript() {return m_dbAttribs[8].getValue();}
	public String get_qty_ordered() {return m_dbAttribs[9].getValue();}
	public String get_qty_received() {return m_dbAttribs[10].getValue();}
	public String get_qty_backorder() {return m_dbAttribs[11].getValue();}
	public String get_unit_price() {return m_dbAttribs[12].getValue();}
	public String get_extended_price() {return m_dbAttribs[13].getValue();}
	public String get_qty_inventory_posted() {return m_dbAttribs[14].getValue();}
	public String get_note1() {return m_dbAttribs[15].getValue();}
	public String get_note2() {return m_dbAttribs[16].getValue();}
	public String get_purch_price() {return m_dbAttribs[17].getValue();}
	public String get_discount_percent() {return m_dbAttribs[18].getValue();}
	public String get_account_id() {return m_dbAttribs[19].getValue();}
	public String get_account_name() {return m_dbAttribs[20].getValue();}
	public String get_is_item_repair() {return m_dbAttribs[21].getValue();}
	public String get_vendor_item_id() {return m_dbAttribs[22].getValue();}
	public String get_vendor_item_desc() {return m_dbAttribs[23].getValue();}
	public String get_vendor_model() {return m_dbAttribs[24].getValue();}
	public String get_qty_for_cust() {return m_dbAttribs[25].getValue();}
	public String get_qty_for_stock() {return m_dbAttribs[26].getValue();}
	public String get_date_item_expected() {return m_dbAttribs[27].getValue();}
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

	public void set_detail_id(String val)
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

	public void set_item_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_description1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_description2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_is_hazmat(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_hazmat_descript(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_qty_ordered(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_qty_received(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_qty_backorder(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_unit_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_extended_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_qty_inventory_posted(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_purch_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_discount_percent(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_account_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_account_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_is_item_repair(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_vendor_item_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_vendor_item_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_vendor_model(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_qty_for_cust(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_qty_for_stock(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_date_item_expected(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new purch_order_itemObj();
	}


}
