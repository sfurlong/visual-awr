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

public class item_bomObj extends BusinessObject
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
		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();
		set_locality(sessionMeta.getLocality());
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
	static public final String TABLE_NAME = "ITEM_BOM";
	static public final String ID = "ITEM_BOM.ID";
	static public final String LOCALITY = "ITEM_BOM.LOCALITY";
	static public final String DETAIL_ID = "ITEM_BOM.DETAIL_ID";
	static public final String BOM_LEVEL = "ITEM_BOM.BOM_LEVEL";
	static public final String SUBCOMPONENT_ID = "ITEM_BOM.SUBCOMPONENT_ID";
	static public final String SUBCOMPONENT_DESC = "ITEM_BOM.SUBCOMPONENT_DESC";
	static public final String QTY_CONSUMED = "ITEM_BOM.QTY_CONSUMED";
	static public final String UNIT_OF_MEAS = "ITEM_BOM.UNIT_OF_MEAS";
	static public final String NOTE = "ITEM_BOM.NOTE";


	//Constructor
	public item_bomObj()
	{
		//Private Field Members.
		MAX_COLS = 9;
		TAB_NAME = "ITEM_BOM";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", true);
		m_dbAttribs[3] = new DBAttributes("BOM_LEVEL", null, "INTEGER", true);
		m_dbAttribs[4] = new DBAttributes("SUBCOMPONENT_ID", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("SUBCOMPONENT_DESC", null, "VARCHAR", true);
		m_dbAttribs[6] = new DBAttributes("QTY_CONSUMED", null, "NUMERIC", true);
		m_dbAttribs[7] = new DBAttributes("UNIT_OF_MEAS", null, "VARCHAR", true);
		m_dbAttribs[8] = new DBAttributes("NOTE", null, "VARCHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_bom_level() {return m_dbAttribs[3].getValue();}
	public String get_subcomponent_id() {return m_dbAttribs[4].getValue();}
	public String get_subcomponent_desc() {return m_dbAttribs[5].getValue();}
	public String get_qty_consumed() {return m_dbAttribs[6].getValue();}
	public String get_unit_of_meas() {return m_dbAttribs[7].getValue();}
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

	public void set_bom_level(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_subcomponent_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_subcomponent_desc(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_qty_consumed(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_unit_of_meas(String val)
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
		return new item_bomObj();
	}


}
