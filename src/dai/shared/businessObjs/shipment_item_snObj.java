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

import org.altaprise.vawr.utils.SessionMetaData;

public class shipment_item_snObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		//Add the current date to the Date Created Field.
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
		DBAttributes imutables[] = new DBAttributes[1];
		imutables[0] = new DBAttributes("locality", get_locality(), "VARCHAR", true);
		return imutables;
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "SHIPMENT_ITEM_SN";
	static public final String ID = "SHIPMENT_ITEM_SN.ID";
	static public final String LOCALITY = "SHIPMENT_ITEM_SN.LOCALITY";
	static public final String DETAIL_ID = "SHIPMENT_ITEM_SN.DETAIL_ID";
	static public final String DETAIL_SUB_ID = "SHIPMENT_ITEM_SN.DETAIL_SUB_ID";
	static public final String SERIAL_NUM = "SHIPMENT_ITEM_SN.SERIAL_NUM";
	static public final String SERIAL_NUM_DESC = "SHIPMENT_ITEM_SN.SERIAL_NUM_DESC";
	static public final String LOT_NUM = "SHIPMENT_ITEM_SN.LOT_NUM";
	static public final String LOT_NUM_DESC = "SHIPMENT_ITEM_SN.LOT_NUM_DESC";
	static public final String NOTE = "SHIPMENT_ITEM_SN.NOTE";


	//Constructor
	public shipment_item_snObj()
	{
		//Private Field Members.
		MAX_COLS = 9;
		TAB_NAME = "SHIPMENT_ITEM_SN";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", true);
		m_dbAttribs[3] = new DBAttributes("DETAIL_SUB_ID", null, "INTEGER", true);
		m_dbAttribs[4] = new DBAttributes("SERIAL_NUM", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("SERIAL_NUM_DESC", null, "VARCHAR", true);
		m_dbAttribs[6] = new DBAttributes("LOT_NUM", null, "VARCHAR", true);
		m_dbAttribs[7] = new DBAttributes("LOT_NUM_DESC", null, "VARCHAR", true);
		m_dbAttribs[8] = new DBAttributes("NOTE", null, "VARCHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_detail_sub_id() {return m_dbAttribs[3].getValue();}
	public String get_serial_num() {return m_dbAttribs[4].getValue();}
	public String get_serial_num_desc() {return m_dbAttribs[5].getValue();}
	public String get_lot_num() {return m_dbAttribs[6].getValue();}
	public String get_lot_num_desc() {return m_dbAttribs[7].getValue();}
	public String get_note() {return m_dbAttribs[8].getValue();}
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

	public void set_detail_sub_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_serial_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_serial_num_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_lot_num(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_lot_num_desc(String val)
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

	public BusinessObject getNewInstance()
	{
		return new shipment_item_snObj();
	}


}
