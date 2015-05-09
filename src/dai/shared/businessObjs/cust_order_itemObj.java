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

public class cust_order_itemObj extends BusinessObject
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
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("id", get_id(), "CHAR", true);
		imutables[1] = new DBAttributes("locality", get_locality(), "CHAR", true);
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
	}

	//Transient Fields:
	//transient methods are indicated with a preceding underscore.
	private int qtyToShip = 0;
	public int _get_qty_to_ship() {return qtyToShip;}
	public void _set_qty_to_ship(int qty) {qtyToShip = qty;}
	//Transient member used to store the list of subcomponents
	//associated with an Item.
	public java.util.Vector _ITEM_BOM_OBJS = new java.util.Vector();

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "CUST_ORDER_ITEM";
	static public final String ID = "CUST_ORDER_ITEM.ID";
	static public final String LOCALITY = "CUST_ORDER_ITEM.LOCALITY";
	static public final String DETAIL_ID = "CUST_ORDER_ITEM.DETAIL_ID";
	static public final String DATE_CREATED = "CUST_ORDER_ITEM.DATE_CREATED";
	static public final String ITEM_ID = "CUST_ORDER_ITEM.ITEM_ID";
	static public final String DESCRIPTION1 = "CUST_ORDER_ITEM.DESCRIPTION1";
	static public final String DESCRIPTION2 = "CUST_ORDER_ITEM.DESCRIPTION2";
	static public final String ECCN = "CUST_ORDER_ITEM.ECCN";
	static public final String HTS = "CUST_ORDER_ITEM.HTS";
	static public final String IS_HAZMAT = "CUST_ORDER_ITEM.IS_HAZMAT";
	static public final String HAZMAT_DESCRIPT = "CUST_ORDER_ITEM.HAZMAT_DESCRIPT";
	static public final String QTY_ORDERED = "CUST_ORDER_ITEM.QTY_ORDERED";
	static public final String QTY_AVAIL = "CUST_ORDER_ITEM.QTY_AVAIL";
	static public final String QTY_SHIPPED = "CUST_ORDER_ITEM.QTY_SHIPPED";
	static public final String QTY_BACKORDER = "CUST_ORDER_ITEM.QTY_BACKORDER";
	static public final String UNIT_PRICE = "CUST_ORDER_ITEM.UNIT_PRICE";
	static public final String EXTENDED_PRICE = "CUST_ORDER_ITEM.EXTENDED_PRICE";
	static public final String QTY_INVENTORY_POSTED = "CUST_ORDER_ITEM.QTY_INVENTORY_POSTED";
	static public final String NOTE1 = "CUST_ORDER_ITEM.NOTE1";
	static public final String NOTE2 = "CUST_ORDER_ITEM.NOTE2";
	static public final String IS_INTERNAL_REPAIR = "CUST_ORDER_ITEM.IS_INTERNAL_REPAIR";
	static public final String IS_EXTERNAL_REPAIR = "CUST_ORDER_ITEM.IS_EXTERNAL_REPAIR";
	static public final String ACCOUNT_ID = "CUST_ORDER_ITEM.ACCOUNT_ID";
	static public final String ACCOUNT_NAME = "CUST_ORDER_ITEM.ACCOUNT_NAME";
	static public final String EXPECTED_SHIP_DATE = "CUST_ORDER_ITEM.EXPECTED_SHIP_DATE";
	static public final String OUTSIDE_REPAIR_COST = "CUST_ORDER_ITEM.OUTSIDE_REPAIR_COST";


	//Constructor
	public cust_order_itemObj()
	{
		//Private Field Members.
		MAX_COLS = 26;
		TAB_NAME = "CUST_ORDER_ITEM";
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
		m_dbAttribs[7] = new DBAttributes("ECCN", null, "VARCHAR", 10 ,true);
		m_dbAttribs[8] = new DBAttributes("HTS", null, "VARCHAR", 20 ,true);
		m_dbAttribs[9] = new DBAttributes("IS_HAZMAT", null, "CHAR", 1 ,true);
		m_dbAttribs[10] = new DBAttributes("HAZMAT_DESCRIPT", null, "VARCHAR", 80 ,true);
		m_dbAttribs[11] = new DBAttributes("QTY_ORDERED", null, "INTEGER", 10 ,true);
		m_dbAttribs[12] = new DBAttributes("QTY_AVAIL", null, "INTEGER", 10 ,true);
		m_dbAttribs[13] = new DBAttributes("QTY_SHIPPED", null, "INTEGER", 10 ,true);
		m_dbAttribs[14] = new DBAttributes("QTY_BACKORDER", null, "INTEGER", 10 ,true);
		m_dbAttribs[15] = new DBAttributes("UNIT_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[16] = new DBAttributes("EXTENDED_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[17] = new DBAttributes("QTY_INVENTORY_POSTED", null, "NUMERIC", 0 ,true);
		m_dbAttribs[18] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[19] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[20] = new DBAttributes("IS_INTERNAL_REPAIR", null, "CHAR", 1 ,true);
		m_dbAttribs[21] = new DBAttributes("IS_EXTERNAL_REPAIR", null, "CHAR", 1 ,true);
		m_dbAttribs[22] = new DBAttributes("ACCOUNT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[23] = new DBAttributes("ACCOUNT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[24] = new DBAttributes("EXPECTED_SHIP_DATE", null, "DATE", 19 ,true);
		m_dbAttribs[25] = new DBAttributes("OUTSIDE_REPAIR_COST", null, "NUMERIC", 0 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_date_created() {return m_dbAttribs[3].getValue();}
	public String get_item_id() {return m_dbAttribs[4].getValue();}
	public String get_description1() {return m_dbAttribs[5].getValue();}
	public String get_description2() {return m_dbAttribs[6].getValue();}
	public String get_eccn() {return m_dbAttribs[7].getValue();}
	public String get_hts() {return m_dbAttribs[8].getValue();}
	public String get_is_hazmat() {return m_dbAttribs[9].getValue();}
	public String get_hazmat_descript() {return m_dbAttribs[10].getValue();}
	public String get_qty_ordered() {return m_dbAttribs[11].getValue();}
	public String get_qty_avail() {return m_dbAttribs[12].getValue();}
	public String get_qty_shipped() {return m_dbAttribs[13].getValue();}
	public String get_qty_backorder() {return m_dbAttribs[14].getValue();}
	public String get_unit_price() {return m_dbAttribs[15].getValue();}
	public String get_extended_price() {return m_dbAttribs[16].getValue();}
	public String get_qty_inventory_posted() {return m_dbAttribs[17].getValue();}
	public String get_note1() {return m_dbAttribs[18].getValue();}
	public String get_note2() {return m_dbAttribs[19].getValue();}
	public String get_is_internal_repair() {return m_dbAttribs[20].getValue();}
	public String get_is_external_repair() {return m_dbAttribs[21].getValue();}
	public String get_account_id() {return m_dbAttribs[22].getValue();}
	public String get_account_name() {return m_dbAttribs[23].getValue();}
	public String get_expected_ship_date() {return m_dbAttribs[24].getValue();}
	public String get_outside_repair_cost() {return m_dbAttribs[25].getValue();}
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

	public void set_eccn(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_hts(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_is_hazmat(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_hazmat_descript(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_qty_ordered(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_qty_avail(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_qty_shipped(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_qty_backorder(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_unit_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_extended_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_qty_inventory_posted(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_is_internal_repair(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_is_external_repair(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_account_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_account_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_expected_ship_date(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_outside_repair_cost(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new cust_order_itemObj();
	}


}
