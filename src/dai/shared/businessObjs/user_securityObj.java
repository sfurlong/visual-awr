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

public class user_securityObj extends BusinessObject
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
		SessionMetaData sessionMeta = SessionMetaData.getInstance();

		set_locality(sessionMeta.getLocality());
	}

	//Transient Fields:
	//transient methods are indicated with a preceding underscore.
	private int qtyToShip = 0;
	public int _get_qty_to_ship() {return qtyToShip;}
	public void _set_qty_to_ship(int qty) {qtyToShip = qty;}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "USER_SECURITY";
	static public final String ID = "USER_SECURITY.ID";
	static public final String LOCALITY = "USER_SECURITY.LOCALITY";
	static public final String DETAIL_ID = "USER_SECURITY.DETAIL_ID";
	static public final String COMPONENT_ID = "USER_SECURITY.COMPONENT_ID";
	static public final String DESCRIPTION = "USER_SECURITY.DESCRIPTION";
	static public final String CREATE_PERMISSION = "USER_SECURITY.CREATE_PERMISSION";
	static public final String READ_PERMISSION = "USER_SECURITY.READ_PERMISSION";
	static public final String MODIFY_PERMISSION = "USER_SECURITY.MODIFY_PERMISSION";
	static public final String DELETE_PERMISSION = "USER_SECURITY.DELETE_PERMISSION";
	static public final String EXECUTE_PERMISSION = "USER_SECURITY.EXECUTE_PERMISSION";


	//Constructor
	public user_securityObj()
	{
		//Private Field Members.
		MAX_COLS = 10;
		TAB_NAME = "USER_SECURITY";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
                this.
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", 10 ,true);
		m_dbAttribs[3] = new DBAttributes("COMPONENT_ID", null, "VARCHAR", 50 ,true);
		m_dbAttribs[4] = new DBAttributes("DESCRIPTION", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("CREATE_PERMISSION", null, "CHAR", 1 ,true);
		m_dbAttribs[6] = new DBAttributes("READ_PERMISSION", null, "CHAR", 1 ,true);
		m_dbAttribs[7] = new DBAttributes("MODIFY_PERMISSION", null, "CHAR", 1 ,true);
		m_dbAttribs[8] = new DBAttributes("DELETE_PERMISSION", null, "CHAR", 1 ,true);
		m_dbAttribs[9] = new DBAttributes("EXECUTE_PERMISSION", null, "CHAR", 1 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_component_id() {return m_dbAttribs[3].getValue();}
	public String get_description() {return m_dbAttribs[4].getValue();}
	public String get_create_permission() {return m_dbAttribs[5].getValue();}
	public String get_read_permission() {return m_dbAttribs[6].getValue();}
	public String get_modify_permission() {return m_dbAttribs[7].getValue();}
	public String get_delete_permission() {return m_dbAttribs[8].getValue();}
	public String get_execute_permission() {return m_dbAttribs[9].getValue();}
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

	public void set_component_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_description(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_create_permission(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_read_permission(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_modify_permission(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_delete_permission(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_execute_permission(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new user_securityObj();
	}


}
