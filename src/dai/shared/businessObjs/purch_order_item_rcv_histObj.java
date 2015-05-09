//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;


public class purch_order_item_rcv_histObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		set_locality(this.getObjLocality());
	}

	public void clear(boolean clearImmutables)
	{
		String locality = get_locality();
        String detailId = get_detail_id();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_locality(locality);
            set_detail_id(detailId);
		}
	}

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[3];
		imutables[0] = new DBAttributes("id", get_id(), "CHAR", true);
		imutables[1] = new DBAttributes("locality", get_locality(), "CHAR", true);
		imutables[2] = new DBAttributes("detail_id", get_detail_id(), "NUMERIC", false);
		return imutables;
	}

	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "PURCH_ORDER_ITEM_RCV_HIST";
	static public final String ID = "PURCH_ORDER_ITEM_RCV_HIST.ID";
	static public final String LOCALITY = "PURCH_ORDER_ITEM_RCV_HIST.LOCALITY";
	static public final String DETAIL_ID = "PURCH_ORDER_ITEM_RCV_HIST.DETAIL_ID";
	static public final String SUB_DETAIL_ID = "PURCH_ORDER_ITEM_RCV_HIST.SUB_DETAIL_ID";
	static public final String DATE_RECEIVED = "PURCH_ORDER_ITEM_RCV_HIST.DATE_RECEIVED";
	static public final String QTY_RECEIVED = "PURCH_ORDER_ITEM_RCV_HIST.QTY_RECEIVED";
	static public final String BILL_RECEIVED = "PURCH_ORDER_ITEM_RCV_HIST.BILL_RECEIVED";


	//Constructor
	public purch_order_item_rcv_histObj()
	{
		//Private Field Members.
		MAX_COLS = 7;
		TAB_NAME = "PURCH_ORDER_ITEM_RCV_HIST";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", true);
		m_dbAttribs[3] = new DBAttributes("SUB_DETAIL_ID", null, "INTEGER", true);
		m_dbAttribs[4] = new DBAttributes("DATE_RECEIVED", null, "DATE", true);
		m_dbAttribs[5] = new DBAttributes("QTY_RECEIVED", null, "INTEGER", true);
		m_dbAttribs[6] = new DBAttributes("BILL_RECEIVED", null, "CHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_sub_detail_id() {return m_dbAttribs[3].getValue();}
	public String get_date_received() {return m_dbAttribs[4].getValue();}
	public String get_qty_received() {return m_dbAttribs[5].getValue();}
	public String get_bill_received() {return m_dbAttribs[6].getValue();}
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

	public void set_sub_detail_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_date_received(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_qty_received(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_bill_received(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new purch_order_item_rcv_histObj();
	}


}
