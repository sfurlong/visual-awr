/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;

import dai.shared.businessObjs.*;
import org.altaprise.vawr.utils.SessionMetaData;
import java.util.Calendar;

public class item_viewObj extends BusinessObject {

	// !!!!!!BEGIN USER SECTION!!!!!!!
	public DBAttributes[] getImmutableAttribs() {
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("id", get_id(), "CHAR", true);
		return imutables;
	}

	public void clear(boolean clearImmutables) {
		for (int i = 0; i < MAX_COLS; i++) {
			m_dbAttribs[i].setValue(null);
		}
	}

	public String get_id() {
		return get_item_id();
	}

	public void set_id(String val) {
		set_item_id(val);
	}

	// !!!!!! END USER SECTION !!!!!!!

	// STATIC DATA //
	static public final String TABLE_NAME = "ITEM_VIEW";

	static public final String ITEM_ID = "ITEM_VIEW.ITEM_ID";

	static public final String STATUS = "ITEM_VIEW.STATUS";

	static public final String NAME = "ITEM_VIEW.NAME";

	static public final String ITEM_TYPE = "ITEM_VIEW.ITEM_TYPE";

	static public final String DESCRIPTION = "ITEM_VIEW.DESCRIPTION";

	static public final String PRODUCTLINE_ID = "ITEM_VIEW.PRODUCTLINE_ID";

	static public final String GLGROUP = "ITEM_VIEW.GLGROUP";

	static public final String SUBSTITUTE_ITEM_ID = "ITEM_VIEW.SUBSTITUTE_ITEM_ID";

	static public final String WHEN_LASTSOLD = "ITEM_VIEW.WHEN_LASTSOLD";

	static public final String WHEN_LASTRECEIVED = "ITEM_VIEW.WHEN_LASTRECEIVED";

	static public final String TAXABLE = "ITEM_VIEW.TAXABLE";

	static public final String TAXGROUP = "ITEM_VIEW.TAXGROUP";

	static public final String SHIP_WEIGHT = "ITEM_VIEW.SHIP_WEIGHT";

	static public final String COST_METHOD = "ITEM_VIEW.COST_METHOD";

	static public final String STANDARD_COST = "ITEM_VIEW.STANDARD_COST";

	static public final String INV_PRECISION = "ITEM_VIEW.INV_PRECISION";

	static public final String SO_PRECISION = "ITEM_VIEW.SO_PRECISION";

	static public final String PO_PRECISION = "ITEM_VIEW.PO_PRECISION";

	static public final String STANDARD_UNIT = "ITEM_VIEW.STANDARD_UNIT";

	static public final String PURCHASE_UNIT = "ITEM_VIEW.PURCHASE_UNIT";

	static public final String SALES_UNIT = "ITEM_VIEW.SALES_UNIT";

	static public final String PURCHASE_UNIT_FACTOR = "ITEM_VIEW.PURCHASE_UNIT_FACTOR";

	static public final String SALES_UNIT_FACTOR = "ITEM_VIEW.SALES_UNIT_FACTOR";

	static public final String DEFAULT_WAREHOUSE = "ITEM_VIEW.DEFAULT_WAREHOUSE";

	static public final String NOTE = "ITEM_VIEW.NOTE";

	static public final String CYCLE = "ITEM_VIEW.CYCLE";

	static public final String V_LINE_NO = "ITEM_VIEW.V_LINE_NO";

	static public final String V_VENDOR_ID = "ITEM_VIEW.V_VENDOR_ID";

	static public final String V_STOCK_NO = "ITEM_VIEW.V_STOCK_NO";

	static public final String V_LEAD_TIME = "ITEM_VIEW.V_LEAD_TIME";

	static public final String V_ECONOMIC_ORDER_QTY = "ITEM_VIEW.V_ECONOMIC_ORDER_QTY";

	static public final String W_LINE_NO = "ITEM_VIEW.W_LINE_NO";

	static public final String W_WAREHOUSE_ID = "ITEM_VIEW.W_WAREHOUSE_ID";

	static public final String W_BEGBAL_QTY = "ITEM_VIEW.W_BEGBAL_QTY";

	static public final String W_BEGBAL_PRICE = "ITEM_VIEW.W_BEGBAL_PRICE";

	static public final String W_REORDER_METHOD = "ITEM_VIEW.W_REORDER_METHOD";

	static public final String W_STANDARD_COST = "ITEM_VIEW.W_STANDARD_COST";

	static public final String W_LAST_COST = "ITEM_VIEW.W_LAST_COST";

	static public final String W_AVERAGE_COST = "ITEM_VIEW.W_AVERAGE_COST";

	static public final String W_MIN_ORDER_QTY = "ITEM_VIEW.W_MIN_ORDER_QTY";

	static public final String W_MAX_ORDER_QTY = "ITEM_VIEW.W_MAX_ORDER_QTY";

	static public final String W_MIN_STOCK = "ITEM_VIEW.W_MIN_STOCK";

	static public final String W_MAX_STOCK = "ITEM_VIEW.W_MAX_STOCK";

	static public final String W_DATE_LAST_SOLD = "ITEM_VIEW.W_DATE_LAST_SOLD";

	static public final String W_DATE_LAST_RECEIVED = "ITEM_VIEW.W_DATE_LAST_RECEIVED";

	// Constructor
	public item_viewObj() {
		// Private Field Members.
		MAX_COLS = 45;
		TAB_NAME = "ITEM_VIEW";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ITEM_ID", null, "VARCHAR", 30, true);
		m_dbAttribs[1] = new DBAttributes("STATUS", null, "CHAR", 0, true);
		m_dbAttribs[2] = new DBAttributes("NAME", null, "VARCHAR", 30, true);
		m_dbAttribs[3] = new DBAttributes("ITEM_TYPE", null, "VARCHAR", 20,
				true);
		m_dbAttribs[4] = new DBAttributes("DESCRIPTION", null, "VARCHAR", 500,
				true);
		m_dbAttribs[5] = new DBAttributes("PRODUCTLINE_ID", null, "VARCHAR",
				10, true);
		m_dbAttribs[6] = new DBAttributes("GLGROUP", null, "CHAR", 0, true);
		m_dbAttribs[7] = new DBAttributes("SUBSTITUTE_ITEM_ID", null, "CHAR",
				0, true);
		m_dbAttribs[8] = new DBAttributes("WHEN_LASTSOLD", null, "CHAR", 0,
				true);
		m_dbAttribs[9] = new DBAttributes("WHEN_LASTRECEIVED", null, "CHAR", 0,
				true);
		m_dbAttribs[10] = new DBAttributes("TAXABLE", null, "CHAR", 0, true);
		m_dbAttribs[11] = new DBAttributes("TAXGROUP", null, "CHAR", 0, true);
		m_dbAttribs[12] = new DBAttributes("SHIP_WEIGHT", null, "NUMERIC", 0,
				true);
		m_dbAttribs[13] = new DBAttributes("COST_METHOD", null, "CHAR", 0, true);
		m_dbAttribs[14] = new DBAttributes("STANDARD_COST", null, "NUMERIC", 0,
				true);
		m_dbAttribs[15] = new DBAttributes("INV_PRECISION", null, "CHAR", 0,
				true);
		m_dbAttribs[16] = new DBAttributes("SO_PRECISION", null, "CHAR", 0,
				true);
		m_dbAttribs[17] = new DBAttributes("PO_PRECISION", null, "CHAR", 0,
				true);
		m_dbAttribs[18] = new DBAttributes("STANDARD_UNIT", null, "VARCHAR",
				10, true);
		m_dbAttribs[19] = new DBAttributes("PURCHASE_UNIT", null, "VARCHAR",
				10, true);
		m_dbAttribs[20] = new DBAttributes("SALES_UNIT", null, "CHAR", 0, true);
		m_dbAttribs[21] = new DBAttributes("PURCHASE_UNIT_FACTOR", null,
				"CHAR", 0, true);
		m_dbAttribs[22] = new DBAttributes("SALES_UNIT_FACTOR", null, "CHAR",
				0, true);
		m_dbAttribs[23] = new DBAttributes("DEFAULT_WAREHOUSE", null, "CHAR",
				0, true);
		m_dbAttribs[24] = new DBAttributes("NOTE", null, "VARCHAR", 2000, true);
		m_dbAttribs[25] = new DBAttributes("CYCLE", null, "CHAR", 0, true);
		m_dbAttribs[26] = new DBAttributes("V_LINE_NO", null, "CHAR", 0, true);
		m_dbAttribs[27] = new DBAttributes("V_VENDOR_ID", null, "VARCHAR", 30,
				true);
		m_dbAttribs[28] = new DBAttributes("V_STOCK_NO", null, "VARCHAR", 30,
				true);
		m_dbAttribs[29] = new DBAttributes("V_LEAD_TIME", null, "CHAR", 0, true);
		m_dbAttribs[30] = new DBAttributes("V_ECONOMIC_ORDER_QTY", null,
				"CHAR", 0, true);
		m_dbAttribs[31] = new DBAttributes("W_LINE_NO", null, "CHAR", 0, true);
		m_dbAttribs[32] = new DBAttributes("W_WAREHOUSE_ID", null, "CHAR", 0,
				true);
		m_dbAttribs[33] = new DBAttributes("W_BEGBAL_QTY", null, "CHAR", 0,
				true);
		m_dbAttribs[34] = new DBAttributes("W_BEGBAL_PRICE", null, "CHAR", 0,
				true);
		m_dbAttribs[35] = new DBAttributes("W_REORDER_METHOD", null, "CHAR", 0,
				true);
		m_dbAttribs[36] = new DBAttributes("W_STANDARD_COST", null, "CHAR", 0,
				true);
		m_dbAttribs[37] = new DBAttributes("W_LAST_COST", null, "CHAR", 0, true);
		m_dbAttribs[38] = new DBAttributes("W_AVERAGE_COST", null, "CHAR", 0,
				true);
		m_dbAttribs[39] = new DBAttributes("W_MIN_ORDER_QTY", null, "CHAR", 0,
				true);
		m_dbAttribs[40] = new DBAttributes("W_MAX_ORDER_QTY", null, "CHAR", 0,
				true);
		m_dbAttribs[41] = new DBAttributes("W_MIN_STOCK", null, "CHAR", 0, true);
		m_dbAttribs[42] = new DBAttributes("W_MAX_STOCK", null, "CHAR", 0, true);
		m_dbAttribs[43] = new DBAttributes("W_DATE_LAST_SOLD", null, "CHAR", 0,
				true);
		m_dbAttribs[44] = new DBAttributes("W_DATE_LAST_RECEIVED", null,
				"CHAR", 0, true);
	}

	public String get_item_id() {
		return m_dbAttribs[0].getValue();
	}

	public String get_status() {
		return m_dbAttribs[1].getValue();
	}

	public String get_name() {
		return m_dbAttribs[2].getValue();
	}

	public String get_item_type() {
		return m_dbAttribs[3].getValue();
	}

	public String get_description() {
		return m_dbAttribs[4].getValue();
	}

	public String get_productline_id() {
		return m_dbAttribs[5].getValue();
	}

	public String get_glgroup() {
		return m_dbAttribs[6].getValue();
	}

	public String get_substitute_item_id() {
		return m_dbAttribs[7].getValue();
	}

	public String get_when_lastsold() {
		return m_dbAttribs[8].getValue();
	}

	public String get_when_lastreceived() {
		return m_dbAttribs[9].getValue();
	}

	public String get_taxable() {
		return m_dbAttribs[10].getValue();
	}

	public String get_taxgroup() {
		return m_dbAttribs[11].getValue();
	}

	public String get_ship_weight() {
		return m_dbAttribs[12].getValue();
	}

	public String get_cost_method() {
		return m_dbAttribs[13].getValue();
	}

	public String get_standard_cost() {
		return m_dbAttribs[14].getValue();
	}

	public String get_inv_precision() {
		return m_dbAttribs[15].getValue();
	}

	public String get_so_precision() {
		return m_dbAttribs[16].getValue();
	}

	public String get_po_precision() {
		return m_dbAttribs[17].getValue();
	}

	public String get_standard_unit() {
		return m_dbAttribs[18].getValue();
	}

	public String get_purchase_unit() {
		return m_dbAttribs[19].getValue();
	}

	public String get_sales_unit() {
		return m_dbAttribs[20].getValue();
	}

	public String get_purchase_unit_factor() {
		return m_dbAttribs[21].getValue();
	}

	public String get_sales_unit_factor() {
		return m_dbAttribs[22].getValue();
	}

	public String get_default_warehouse() {
		return m_dbAttribs[23].getValue();
	}

	public String get_note() {
		return m_dbAttribs[24].getValue();
	}

	public String get_cycle() {
		return m_dbAttribs[25].getValue();
	}

	public String get_v_line_no() {
		return m_dbAttribs[26].getValue();
	}

	public String get_v_vendor_id() {
		return m_dbAttribs[27].getValue();
	}

	public String get_v_stock_no() {
		return m_dbAttribs[28].getValue();
	}

	public String get_v_lead_time() {
		return m_dbAttribs[29].getValue();
	}

	public String get_v_economic_order_qty() {
		return m_dbAttribs[30].getValue();
	}

	public String get_w_line_no() {
		return m_dbAttribs[31].getValue();
	}

	public String get_w_warehouse_id() {
		return m_dbAttribs[32].getValue();
	}

	public String get_w_begbal_qty() {
		return m_dbAttribs[33].getValue();
	}

	public String get_w_begbal_price() {
		return m_dbAttribs[34].getValue();
	}

	public String get_w_reorder_method() {
		return m_dbAttribs[35].getValue();
	}

	public String get_w_standard_cost() {
		return m_dbAttribs[36].getValue();
	}

	public String get_w_last_cost() {
		return m_dbAttribs[37].getValue();
	}

	public String get_w_average_cost() {
		return m_dbAttribs[38].getValue();
	}

	public String get_w_min_order_qty() {
		return m_dbAttribs[39].getValue();
	}

	public String get_w_max_order_qty() {
		return m_dbAttribs[40].getValue();
	}

	public String get_w_min_stock() {
		return m_dbAttribs[41].getValue();
	}

	public String get_w_max_stock() {
		return m_dbAttribs[42].getValue();
	}

	public String get_w_date_last_sold() {
		return m_dbAttribs[43].getValue();
	}

	public String get_w_date_last_received() {
		return m_dbAttribs[44].getValue();
	}

	public void set_item_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[0].setValue(null);
		else
			m_dbAttribs[0].setValue(val);
	}

	public void set_status(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[1].setValue(null);
		else
			m_dbAttribs[1].setValue(val);
	}

	public void set_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_item_type(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_description(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_productline_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_glgroup(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_substitute_item_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_when_lastsold(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_when_lastreceived(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_taxable(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_taxgroup(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_ship_weight(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_cost_method(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_standard_cost(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_inv_precision(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_so_precision(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_po_precision(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_standard_unit(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_purchase_unit(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_sales_unit(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_purchase_unit_factor(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_sales_unit_factor(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_default_warehouse(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_note(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_cycle(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_v_line_no(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_v_vendor_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_v_stock_no(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_v_lead_time(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_v_economic_order_qty(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_w_line_no(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_w_warehouse_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_w_begbal_qty(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_w_begbal_price(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_w_reorder_method(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_w_standard_cost(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_w_last_cost(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_w_average_cost(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_w_min_order_qty(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public void set_w_max_order_qty(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[40].setValue(null);
		else
			m_dbAttribs[40].setValue(val);
	}

	public void set_w_min_stock(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[41].setValue(null);
		else
			m_dbAttribs[41].setValue(val);
	}

	public void set_w_max_stock(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[42].setValue(null);
		else
			m_dbAttribs[42].setValue(val);
	}

	public void set_w_date_last_sold(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[43].setValue(null);
		else
			m_dbAttribs[43].setValue(val);
	}

	public void set_w_date_last_received(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[44].setValue(null);
		else
			m_dbAttribs[44].setValue(val);
	}

	public BusinessObject getNewInstance() {
		return new item_viewObj();
	}

}
