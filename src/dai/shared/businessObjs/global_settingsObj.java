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


public class global_settingsObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

	/////////Global Setting IDs./////
    //This setting is used to know which is that last invoice id that
    //was generated for the MOnth, so that we can know when to roll over the invoice id counter.
	static public final String SETTING_LAST_INVOICE_MO = "SETTING_LAST_INVOICE_MO";

    //This setting is used to compare the current client version (in the version.txt file).
	//If they are different, a client update is needed.
	static public final String CURRENT_CLIENT_VERSION = "CURRENT_CLIENT_VERSION";

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[1];
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		return imutables;
	}

	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		set_locality(this.getObjLocality());
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

	//STATIC DATA //
	static public final String TABLE_NAME = "GLOBAL_SETTINGS";
	static public final String ID = "GLOBAL_SETTINGS.ID";
	static public final String LOCALITY = "GLOBAL_SETTINGS.LOCALITY";
	static public final String VARCHAR_SETTING1 = "GLOBAL_SETTINGS.VARCHAR_SETTING1";
	static public final String VARCHAR_SETTING2 = "GLOBAL_SETTINGS.VARCHAR_SETTING2";
	static public final String DATE_SETTING1 = "GLOBAL_SETTINGS.DATE_SETTING1";
	static public final String DATE_SETTING2 = "GLOBAL_SETTINGS.DATE_SETTING2";
	static public final String INT_SETTING1 = "GLOBAL_SETTINGS.INT_SETTING1";
	static public final String INT_SETTING2 = "GLOBAL_SETTINGS.INT_SETTING2";
	static public final String NUM_SETTING1 = "GLOBAL_SETTINGS.NUM_SETTING1";
	static public final String NUM_SETTING2 = "GLOBAL_SETTINGS.NUM_SETTING2";


	//Constructor
	public global_settingsObj()
	{
		//Private Field Members.
		MAX_COLS = 10;
		TAB_NAME = "GLOBAL_SETTINGS";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("VARCHAR_SETTING1", null, "VARCHAR", 30 ,true);
		m_dbAttribs[3] = new DBAttributes("VARCHAR_SETTING2", null, "VARCHAR", 30 ,true);
		m_dbAttribs[4] = new DBAttributes("DATE_SETTING1", null, "DATE", 19 ,true);
		m_dbAttribs[5] = new DBAttributes("DATE_SETTING2", null, "DATE", 19 ,true);
		m_dbAttribs[6] = new DBAttributes("INT_SETTING1", null, "INTEGER", 10 ,true);
		m_dbAttribs[7] = new DBAttributes("INT_SETTING2", null, "INTEGER", 10 ,true);
		m_dbAttribs[8] = new DBAttributes("NUM_SETTING1", null, "NUMERIC", 0 ,true);
		m_dbAttribs[9] = new DBAttributes("NUM_SETTING2", null, "NUMERIC", 0 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_varchar_setting1() {return m_dbAttribs[2].getValue();}
	public String get_varchar_setting2() {return m_dbAttribs[3].getValue();}
	public String get_date_setting1() {return m_dbAttribs[4].getValue();}
	public String get_date_setting2() {return m_dbAttribs[5].getValue();}
	public String get_int_setting1() {return m_dbAttribs[6].getValue();}
	public String get_int_setting2() {return m_dbAttribs[7].getValue();}
	public String get_num_setting1() {return m_dbAttribs[8].getValue();}
	public String get_num_setting2() {return m_dbAttribs[9].getValue();}
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

	public void set_varchar_setting1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_varchar_setting2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_date_setting1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_date_setting2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_int_setting1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_int_setting2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_num_setting1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_num_setting2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new global_settingsObj();
	}
}
