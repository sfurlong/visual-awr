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

public class item_inventoryObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
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

                set_date_modified(now.get(now.MONTH)+1 + "/" +
						 now.get(now.DAY_OF_MONTH) + "/" +
						 now.get(now.YEAR));

		set_locality(sessionMeta.getLocality());

		set_created_by(sessionMeta.getUserId());
	}

	public void clear(boolean clearImmutables)
	{
		String id = get_id();
		String locality = get_locality();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_id(id);
			set_locality(locality);
		}
	}
	static public final String DO_MANUAL_ADJUST_CUST_BACK = "DO_MANUAL_ADJUST_CUST_BACK";
	static public final String DO_MANUAL_ADJUST_VEND_BACK = "DO_MANUAL_ADJUST_VEND_BACK";
	static public final String DO_MANUAL_ADJUST_ONHAND = "DO_MANUAL_ADJUST_ONHAND";
	static public final String ADJUST_TYPE_CUST_ORDER = "Cust Order";
	static public final String ADJUST_TYPE_SHIPMENT = "Shipment";
	static public final String ADJUST_TYPE_PURCH_ORDER = "Purchase Order";
	static public final String ADJUST_TYPE_RECEIVE_INVENTORY = "Receive Inventory";
	static public final String ADJUST_TYPE_MANUAL_ADJUSTMENT = "Manual Adjustment";
	static public final String ADJUST_TYPE_DELETE_SHIPMENT = "Shipment Deleted";
	static public final String ADJUST_TYPE_DELETE_ORDER = "Order Deleted";
	static public final String ADJUST_TYPE_DELETE_PURCH_ORDER = "Purch Order Deleted";

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "ITEM_INVENTORY";
	static public final String ID = "ITEM_INVENTORY.ID";
	static public final String LOCALITY = "ITEM_INVENTORY.LOCALITY";
	static public final String DETAIL_ID = "ITEM_INVENTORY.DETAIL_ID";
	static public final String CREATED_BY = "ITEM_INVENTORY.CREATED_BY";
	static public final String DATE_CREATED = "ITEM_INVENTORY.DATE_CREATED";
	static public final String FOR_STOCK = "ITEM_INVENTORY.FOR_STOCK";
	static public final String FOR_CUSTOMER = "ITEM_INVENTORY.FOR_CUSTOMER";
	static public final String ADJUSTMENT_REASON = "ITEM_INVENTORY.ADJUSTMENT_REASON";
	static public final String ADJUSTMENT_TYPE = "ITEM_INVENTORY.ADJUSTMENT_TYPE";
	static public final String NOTE = "ITEM_INVENTORY.NOTE";
	static public final String USER1 = "ITEM_INVENTORY.USER1";
	static public final String USER2 = "ITEM_INVENTORY.USER2";
	static public final String USER3 = "ITEM_INVENTORY.USER3";
	static public final String USER4 = "ITEM_INVENTORY.USER4";
	static public final String ADJUSTMENT_QTY = "ITEM_INVENTORY.ADJUSTMENT_QTY";
	static public final String QTY_ONHAND = "ITEM_INVENTORY.QTY_ONHAND";
	static public final String QTY_CUST_BACK_ORD = "ITEM_INVENTORY.QTY_CUST_BACK_ORD";
	static public final String QTY_VEND_BACK_ORD = "ITEM_INVENTORY.QTY_VEND_BACK_ORD";
	static public final String ITEM_PURCH_PRICE = "ITEM_INVENTORY.ITEM_PURCH_PRICE";
	static public final String QTY_AVAIL_FOR_INVENTORY_ACCTING = "ITEM_INVENTORY.QTY_AVAIL_FOR_INVENTORY_ACCTING";
        static public final String DATE_MODIFIED = "ITEM_INVENTORY.DATE_MODIFIED";

	//Constructor
	public item_inventoryObj()
	{
		//Private Field Members.
		MAX_COLS = 21;
		TAB_NAME = "ITEM_INVENTORY";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init()
	{
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", 10 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[5] = new DBAttributes("FOR_STOCK", null, "CHAR", 1 ,true);
		m_dbAttribs[6] = new DBAttributes("FOR_CUSTOMER", null, "CHAR", 1 ,true);
		m_dbAttribs[7] = new DBAttributes("ADJUSTMENT_REASON", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("ADJUSTMENT_TYPE", null, "VARCHAR", 20 ,true);
		m_dbAttribs[9] = new DBAttributes("NOTE", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[10] = new DBAttributes("USER1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[11] = new DBAttributes("USER2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[12] = new DBAttributes("USER3", null, "DATE", 19 ,true);
		m_dbAttribs[13] = new DBAttributes("USER4", null, "DATE", 19 ,true);
		m_dbAttribs[14] = new DBAttributes("ADJUSTMENT_QTY", null, "INTEGER", 10 ,true);
		m_dbAttribs[15] = new DBAttributes("QTY_ONHAND", null, "INTEGER", 10 ,true);
		m_dbAttribs[16] = new DBAttributes("QTY_CUST_BACK_ORD", null, "INTEGER", 10 ,true);
		m_dbAttribs[17] = new DBAttributes("QTY_VEND_BACK_ORD", null, "INTEGER", 10 ,true);
		m_dbAttribs[18] = new DBAttributes("ITEM_PURCH_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[19] = new DBAttributes("QTY_AVAIL_FOR_INVENTORY_ACCTING", null, "INTEGER", 10 ,true);
	        m_dbAttribs[20] = new DBAttributes("DATE_MODIFIED", null, "DATE", 19 ,true);
        }
	public String get_id()
	{return m_dbAttribs[0].getValue();}
	public String get_locality()
	{return m_dbAttribs[1].getValue();}
	public String get_detail_id()
	{return m_dbAttribs[2].getValue();}
	public String get_created_by()
	{return m_dbAttribs[3].getValue();}
	public String get_date_created()
	{return m_dbAttribs[4].getValue();}
	public String get_for_stock()
	{return m_dbAttribs[5].getValue();}
	public String get_for_customer()
	{return m_dbAttribs[6].getValue();}
	public String get_adjustment_reason()
	{return m_dbAttribs[7].getValue();}
	public String get_adjustment_type()
	{return m_dbAttribs[8].getValue();}
	public String get_note()
	{return m_dbAttribs[9].getValue();}
	public String get_user1()
	{return m_dbAttribs[10].getValue();}
	public String get_user2()
	{return m_dbAttribs[11].getValue();}
	public String get_user3()
	{return m_dbAttribs[12].getValue();}
	public String get_user4()
	{return m_dbAttribs[13].getValue();}
	public String get_adjustment_qty()
	{return m_dbAttribs[14].getValue();}
	public String get_qty_onhand()
	{return m_dbAttribs[15].getValue();}
	public String get_qty_cust_back_ord()
	{return m_dbAttribs[16].getValue();}
	public String get_qty_vend_back_ord()
	{return m_dbAttribs[17].getValue();}
	public String get_item_purch_price()
	{return m_dbAttribs[18].getValue();}
	public String get_qty_avail_for_inventory_accting()
	{return m_dbAttribs[19].getValue();}
        public String get_date_modified()
	{return m_dbAttribs[20].getValue();}

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

	public void set_created_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_for_stock(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_for_customer(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_adjustment_reason(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_adjustment_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_adjustment_qty(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_qty_onhand(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_qty_cust_back_ord(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_qty_vend_back_ord(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_item_purch_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_qty_avail_for_inventory_accting(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}
        public void set_date_modified(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new item_inventoryObj();
	}


}
