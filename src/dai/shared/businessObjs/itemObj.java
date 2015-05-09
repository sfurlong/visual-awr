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

public class itemObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
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

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "ITEM";
	static public final String ID = "ITEM.ID";
	static public final String LOCALITY = "ITEM.LOCALITY";
	static public final String DATE_CREATED = "ITEM.DATE_CREATED";
	static public final String CREATED_BY = "ITEM.CREATED_BY";
	static public final String ITEM_TYPE = "ITEM.ITEM_TYPE";
	static public final String ITEM_FAMILY_ID = "ITEM.ITEM_FAMILY_ID";
	static public final String ITEM_MODEL = "ITEM.ITEM_MODEL";
	static public final String ITEM_LOT = "ITEM.ITEM_LOT";
	static public final String STANDARD_DESC = "ITEM.STANDARD_DESC";
	static public final String SALES_PRICE = "ITEM.SALES_PRICE";
	static public final String PURCHASE_PRICE = "ITEM.PURCHASE_PRICE";
	static public final String WHOLESALE_PRICE = "ITEM.WHOLESALE_PRICE";
	static public final String UNIT_OF_MEAS = "ITEM.UNIT_OF_MEAS";
	static public final String BL_CODE = "ITEM.BL_CODE";
	static public final String SBL_CODE = "ITEM.SBL_CODE";
	static public final String EPA_FLAG = "ITEM.EPA_FLAG";
	static public final String LEN = "ITEM.LEN";
	static public final String LEN_UOM = "ITEM.LEN_UOM";
	static public final String WIDTH = "ITEM.WIDTH";
	static public final String WIDTH_UOM = "ITEM.WIDTH_UOM";
	static public final String HEIGHT = "ITEM.HEIGHT";
	static public final String HEIGHT_UOM = "ITEM.HEIGHT_UOM";
	static public final String GROSS_WGT_LB = "ITEM.GROSS_WGT_LB";
	static public final String GROSS_WGT_KG = "ITEM.GROSS_WGT_KG";
	static public final String NET_WGT_LB = "ITEM.NET_WGT_LB";
	static public final String NET_WGT_KG = "ITEM.NET_WGT_KG";
	static public final String INITIAL_ONHAND_QTY = "ITEM.INITIAL_ONHAND_QTY";
	static public final String ONHAND_QTY = "ITEM.ONHAND_QTY";
	static public final String VENDOR_ID = "ITEM.VENDOR_ID";
	static public final String VENDOR_ITEM_ID = "ITEM.VENDOR_ITEM_ID";
	static public final String VENDOR_ITEM_DESC = "ITEM.VENDOR_ITEM_DESC";
	static public final String VENDOR_ITEM_MODEL = "ITEM.VENDOR_ITEM_MODEL";
	static public final String VENDOR_ITEM_LOT = "ITEM.VENDOR_ITEM_LOT";
	static public final String VENDOR_RETAIL_PRICE = "ITEM.VENDOR_RETAIL_PRICE";
	static public final String MFG_ID = "ITEM.MFG_ID";
	static public final String MFG_ITEM_ID = "ITEM.MFG_ITEM_ID";
	static public final String MFG_ITEM_DESC = "ITEM.MFG_ITEM_DESC";
	static public final String MFG_ITEM_MODEL = "ITEM.MFG_ITEM_MODEL";
	static public final String MFG_ITEM_LOT = "ITEM.MFG_ITEM_LOT";
	static public final String DUTY_RATE = "ITEM.DUTY_RATE";
	static public final String EXPORT_DESC = "ITEM.EXPORT_DESC";
	static public final String HARMONIZED_NO = "ITEM.HARMONIZED_NO";
	static public final String HAZARDOUS_FLAG = "ITEM.HAZARDOUS_FLAG";
	static public final String HAZARDOUS_DESC = "ITEM.HAZARDOUS_DESC";
	static public final String COUNTRY_OF_MFG = "ITEM.COUNTRY_OF_MFG";
	static public final String CTP = "ITEM.CTP";
	static public final String ECCN_NO = "ITEM.ECCN_NO";
	static public final String USER1 = "ITEM.USER1";
	static public final String USER2 = "ITEM.USER2";
	static public final String USER3 = "ITEM.USER3";
	static public final String USER4 = "ITEM.USER4";
	static public final String DIMS_WGT = "ITEM.DIMS_WGT";
	static public final String BACKORDER_TO_CUST_QTY = "ITEM.BACKORDER_TO_CUST_QTY";
	static public final String BACKORDER_TO_VENDOR_QTY = "ITEM.BACKORDER_TO_VENDOR_QTY";
	static public final String PURCH_ACCT_ID = "ITEM.PURCH_ACCT_ID";
	static public final String PURCH_ACCT_NAME = "ITEM.PURCH_ACCT_NAME";
	static public final String SALES_ACCT_ID = "ITEM.SALES_ACCT_ID";
	static public final String SALES_ACCT_NAME = "ITEM.SALES_ACCT_NAME";
	static public final String INVENTORY_ACCT_ID = "ITEM.INVENTORY_ACCT_ID";
	static public final String INVENTORY_ACCT_NAME = "ITEM.INVENTORY_ACCT_NAME";
	static public final String VENDOR_DISCOUNT_PCT = "ITEM.VENDOR_DISCOUNT_PCT";
	static public final String NOTE1 = "ITEM.NOTE1";
	static public final String NOTE2 = "ITEM.NOTE2";
	static public final String MFG_NAME = "ITEM.MFG_NAME";
	static public final String VENDOR_NAME = "ITEM.VENDOR_NAME";
        static public final String DATE_MODIFIED = "ITEM.DATE_MODIFIED";


	//Constructor
	public itemObj()
	{
		//Private Field Members.
		MAX_COLS = 66;
		TAB_NAME = "ITEM";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("ITEM_TYPE", null, "VARCHAR", 20 ,true);
		m_dbAttribs[5] = new DBAttributes("ITEM_FAMILY_ID", null, "VARCHAR", 10 ,true);
		m_dbAttribs[6] = new DBAttributes("ITEM_MODEL", null, "VARCHAR", 30 ,true);
		m_dbAttribs[7] = new DBAttributes("ITEM_LOT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[8] = new DBAttributes("STANDARD_DESC", null, "VARCHAR", 500 ,true);
		m_dbAttribs[9] = new DBAttributes("SALES_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[10] = new DBAttributes("PURCHASE_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[11] = new DBAttributes("WHOLESALE_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[12] = new DBAttributes("UNIT_OF_MEAS", null, "VARCHAR", 10 ,true);
		m_dbAttribs[13] = new DBAttributes("BL_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[14] = new DBAttributes("SBL_CODE", null, "VARCHAR", 10 ,true);
		m_dbAttribs[15] = new DBAttributes("EPA_FLAG", null, "CHAR", 1 ,true);
		m_dbAttribs[16] = new DBAttributes("LEN", null, "NUMERIC", 0 ,true);
		m_dbAttribs[17] = new DBAttributes("LEN_UOM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[18] = new DBAttributes("WIDTH", null, "NUMERIC", 0 ,true);
		m_dbAttribs[19] = new DBAttributes("WIDTH_UOM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[20] = new DBAttributes("HEIGHT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[21] = new DBAttributes("HEIGHT_UOM", null, "VARCHAR", 30 ,true);
		m_dbAttribs[22] = new DBAttributes("GROSS_WGT_LB", null, "NUMERIC", 0 ,true);
		m_dbAttribs[23] = new DBAttributes("GROSS_WGT_KG", null, "NUMERIC", 0 ,true);
		m_dbAttribs[24] = new DBAttributes("NET_WGT_LB", null, "NUMERIC", 0 ,true);
		m_dbAttribs[25] = new DBAttributes("NET_WGT_KG", null, "NUMERIC", 0 ,true);
		m_dbAttribs[26] = new DBAttributes("INITIAL_ONHAND_QTY", null, "NUMERIC", 0 ,true);
		m_dbAttribs[27] = new DBAttributes("ONHAND_QTY", null, "NUMERIC", 0 ,true);
		m_dbAttribs[28] = new DBAttributes("VENDOR_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[29] = new DBAttributes("VENDOR_ITEM_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[30] = new DBAttributes("VENDOR_ITEM_DESC", null, "VARCHAR", 500 ,true);
		m_dbAttribs[31] = new DBAttributes("VENDOR_ITEM_MODEL", null, "VARCHAR", 30 ,true);
		m_dbAttribs[32] = new DBAttributes("VENDOR_ITEM_LOT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[33] = new DBAttributes("VENDOR_RETAIL_PRICE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[34] = new DBAttributes("MFG_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[35] = new DBAttributes("MFG_ITEM_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[36] = new DBAttributes("MFG_ITEM_DESC", null, "VARCHAR", 500 ,true);
		m_dbAttribs[37] = new DBAttributes("MFG_ITEM_MODEL", null, "VARCHAR", 30 ,true);
		m_dbAttribs[38] = new DBAttributes("MFG_ITEM_LOT", null, "VARCHAR", 30 ,true);
		m_dbAttribs[39] = new DBAttributes("DUTY_RATE", null, "NUMERIC", 0 ,true);
		m_dbAttribs[40] = new DBAttributes("EXPORT_DESC", null, "VARCHAR", 500 ,true);
		m_dbAttribs[41] = new DBAttributes("HARMONIZED_NO", null, "VARCHAR", 20 ,true);
		m_dbAttribs[42] = new DBAttributes("HAZARDOUS_FLAG", null, "CHAR", 1 ,true);
		m_dbAttribs[43] = new DBAttributes("HAZARDOUS_DESC", null, "VARCHAR", 10 ,true);
		m_dbAttribs[44] = new DBAttributes("COUNTRY_OF_MFG", null, "CHAR", 2 ,true);
		m_dbAttribs[45] = new DBAttributes("CTP", null, "VARCHAR", 8 ,true);
		m_dbAttribs[46] = new DBAttributes("ECCN_NO", null, "VARCHAR", 6 ,true);
		m_dbAttribs[47] = new DBAttributes("USER1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[48] = new DBAttributes("USER2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[49] = new DBAttributes("USER3", null, "VARCHAR", 30 ,true);
		m_dbAttribs[50] = new DBAttributes("USER4", null, "VARCHAR", 30 ,true);
		m_dbAttribs[51] = new DBAttributes("DIMS_WGT", null, "NUMERIC", 0 ,true);
		m_dbAttribs[52] = new DBAttributes("BACKORDER_TO_CUST_QTY", null, "NUMERIC", 0 ,true);
		m_dbAttribs[53] = new DBAttributes("BACKORDER_TO_VENDOR_QTY", null, "NUMERIC", 0 ,false);
		m_dbAttribs[54] = new DBAttributes("PURCH_ACCT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[55] = new DBAttributes("PURCH_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[56] = new DBAttributes("SALES_ACCT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[57] = new DBAttributes("SALES_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[58] = new DBAttributes("INVENTORY_ACCT_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[59] = new DBAttributes("INVENTORY_ACCT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[60] = new DBAttributes("VENDOR_DISCOUNT_PCT", null, "INTEGER", 10 ,true);
		m_dbAttribs[61] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[62] = new DBAttributes("NOTE2", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[63] = new DBAttributes("MFG_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[64] = new DBAttributes("VENDOR_NAME", null, "VARCHAR", 50 ,true);
	        m_dbAttribs[65] = new DBAttributes("DATE_MODIFIED", null, "DATE", 19 ,true);
        }
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_date_created() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_item_type() {return m_dbAttribs[4].getValue();}
	public String get_item_family_id() {return m_dbAttribs[5].getValue();}
	public String get_item_model() {return m_dbAttribs[6].getValue();}
	public String get_item_lot() {return m_dbAttribs[7].getValue();}
	public String get_standard_desc() {return m_dbAttribs[8].getValue();}
	public String get_sales_price() {return m_dbAttribs[9].getValue();}
	public String get_purchase_price() {return m_dbAttribs[10].getValue();}
	public String get_wholesale_price() {return m_dbAttribs[11].getValue();}
	public String get_unit_of_meas() {return m_dbAttribs[12].getValue();}
	public String get_bl_code() {return m_dbAttribs[13].getValue();}
	public String get_sbl_code() {return m_dbAttribs[14].getValue();}
	public String get_epa_flag() {return m_dbAttribs[15].getValue();}
	public String get_len() {return m_dbAttribs[16].getValue();}
	public String get_len_uom() {return m_dbAttribs[17].getValue();}
	public String get_width() {return m_dbAttribs[18].getValue();}
	public String get_width_uom() {return m_dbAttribs[19].getValue();}
	public String get_height() {return m_dbAttribs[20].getValue();}
	public String get_height_uom() {return m_dbAttribs[21].getValue();}
	public String get_gross_wgt_lb() {return m_dbAttribs[22].getValue();}
	public String get_gross_wgt_kg() {return m_dbAttribs[23].getValue();}
	public String get_net_wgt_lb() {return m_dbAttribs[24].getValue();}
	public String get_net_wgt_kg() {return m_dbAttribs[25].getValue();}
	public String get_initial_onhand_qty() {return m_dbAttribs[26].getValue();}
	public String get_onhand_qty() {return m_dbAttribs[27].getValue();}
	public String get_vendor_id() {return m_dbAttribs[28].getValue();}
	public String get_vendor_item_id() {return m_dbAttribs[29].getValue();}
	public String get_vendor_item_desc() {return m_dbAttribs[30].getValue();}
	public String get_vendor_item_model() {return m_dbAttribs[31].getValue();}
	public String get_vendor_item_lot() {return m_dbAttribs[32].getValue();}
	public String get_vendor_retail_price() {return m_dbAttribs[33].getValue();}
	public String get_mfg_id() {return m_dbAttribs[34].getValue();}
	public String get_mfg_item_id() {return m_dbAttribs[35].getValue();}
	public String get_mfg_item_desc() {return m_dbAttribs[36].getValue();}
	public String get_mfg_item_model() {return m_dbAttribs[37].getValue();}
	public String get_mfg_item_lot() {return m_dbAttribs[38].getValue();}
	public String get_duty_rate() {return m_dbAttribs[39].getValue();}
	public String get_export_desc() {return m_dbAttribs[40].getValue();}
	public String get_harmonized_no() {return m_dbAttribs[41].getValue();}
	public String get_hazardous_flag() {return m_dbAttribs[42].getValue();}
	public String get_hazardous_desc() {return m_dbAttribs[43].getValue();}
	public String get_country_of_mfg() {return m_dbAttribs[44].getValue();}
	public String get_ctp() {return m_dbAttribs[45].getValue();}
	public String get_eccn_no() {return m_dbAttribs[46].getValue();}
	public String get_user1() {return m_dbAttribs[47].getValue();}
	public String get_user2() {return m_dbAttribs[48].getValue();}
	public String get_user3() {return m_dbAttribs[49].getValue();}
	public String get_user4() {return m_dbAttribs[50].getValue();}
	public String get_dims_wgt() {return m_dbAttribs[51].getValue();}
	public String get_backorder_to_cust_qty() {return m_dbAttribs[52].getValue();}
	public String get_backorder_to_vendor_qty() {return m_dbAttribs[53].getValue();}
	public String get_purch_acct_id() {return m_dbAttribs[54].getValue();}
	public String get_purch_acct_name() {return m_dbAttribs[55].getValue();}
	public String get_sales_acct_id() {return m_dbAttribs[56].getValue();}
	public String get_sales_acct_name() {return m_dbAttribs[57].getValue();}
	public String get_inventory_acct_id() {return m_dbAttribs[58].getValue();}
	public String get_inventory_acct_name() {return m_dbAttribs[59].getValue();}
	public String get_vendor_discount_pct() {return m_dbAttribs[60].getValue();}
	public String get_note1() {return m_dbAttribs[61].getValue();}
	public String get_note2() {return m_dbAttribs[62].getValue();}
	public String get_mfg_name() {return m_dbAttribs[63].getValue();}
	public String get_vendor_name() {return m_dbAttribs[64].getValue();}
	public String get_date_modified() {return m_dbAttribs[65].getValue();}
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

	public void set_item_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_item_family_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_item_model(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_item_lot(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_standard_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_sales_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_purchase_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_wholesale_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_unit_of_meas(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_bl_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_sbl_code(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_epa_flag(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_len(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_len_uom(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_width(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_width_uom(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_height(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_height_uom(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_gross_wgt_lb(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_gross_wgt_kg(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_net_wgt_lb(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_net_wgt_kg(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_initial_onhand_qty(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_onhand_qty(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_vendor_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_vendor_item_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_vendor_item_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_vendor_item_model(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_vendor_item_lot(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_vendor_retail_price(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_mfg_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_mfg_item_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_mfg_item_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_mfg_item_model(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_mfg_item_lot(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_duty_rate(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_export_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_harmonized_no(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_hazardous_flag(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public void set_hazardous_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[43].setValue(null);
		else
			m_dbAttribs[43].setValue(val);
	}

	public void set_country_of_mfg(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[44].setValue(null);
		else
			m_dbAttribs[44].setValue(val);
	}

	public void set_ctp(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[45].setValue(null);
		else
			m_dbAttribs[45].setValue(val);
	}

	public void set_eccn_no(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[46].setValue(null);
		else
			m_dbAttribs[46].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[47].setValue(null);
		else
			m_dbAttribs[47].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[48].setValue(null);
		else
			m_dbAttribs[48].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[49].setValue(null);
		else
			m_dbAttribs[49].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[50].setValue(null);
		else
			m_dbAttribs[50].setValue(val);
	}

	public void set_dims_wgt(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[51].setValue(null);
		else
			m_dbAttribs[51].setValue(val);
	}

	public void set_backorder_to_cust_qty(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[52].setValue(null);
		else
			m_dbAttribs[52].setValue(val);
	}

	public void set_backorder_to_vendor_qty(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[53].setValue(null);
		else
			m_dbAttribs[53].setValue(val);
	}

	public void set_purch_acct_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[54].setValue(null);
		else
			m_dbAttribs[54].setValue(val);
	}

	public void set_purch_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[55].setValue(null);
		else
			m_dbAttribs[55].setValue(val);
	}

	public void set_sales_acct_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[56].setValue(null);
		else
			m_dbAttribs[56].setValue(val);
	}

	public void set_sales_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[57].setValue(null);
		else
			m_dbAttribs[57].setValue(val);
	}

	public void set_inventory_acct_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[58].setValue(null);
		else
			m_dbAttribs[58].setValue(val);
	}

	public void set_inventory_acct_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[59].setValue(null);
		else
			m_dbAttribs[59].setValue(val);
	}

	public void set_vendor_discount_pct(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[60].setValue(null);
		else
			m_dbAttribs[60].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[61].setValue(null);
		else
			m_dbAttribs[61].setValue(val);
	}

	public void set_note2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[62].setValue(null);
		else
			m_dbAttribs[62].setValue(val);
	}

	public void set_mfg_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[63].setValue(null);
		else
			m_dbAttribs[63].setValue(val);
	}

	public void set_vendor_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[64].setValue(null);
		else
			m_dbAttribs[64].setValue(val);
	}
        public void set_date_modified(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[65].setValue(null);
		else
			m_dbAttribs[65].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new itemObj();
	}


}
