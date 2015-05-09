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

public class component_securityObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public void clear(boolean clearImmutables)
	{
		String userId = get_user_id();
		String locality = get_locality();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_user_id(userId);
			set_locality(locality);
		}
	}

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		imutables[1] = new DBAttributes("user_id", get_user_id(), "CHAR", true);
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
	//!!!!!! END USER SECTION !!!!!!!

	//Constructor
	public component_securityObj()
	{
		//Private Field Members.
		MAX_COLS = 10;
		m_dbAttribs = new DBAttributes[MAX_COLS];
		TAB_NAME = "COMPONENT_SECURITY";
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("USER_ID", null, "VARCHAR", true);
		m_dbAttribs[3] = new DBAttributes("DESCRIPTION", null, "VARCHAR", true);
		m_dbAttribs[4] = new DBAttributes("IN_MODULE", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("CREATE_PERMISSION", null, "INTEGER", true);
		m_dbAttribs[6] = new DBAttributes("READ_PERMISSION", null, "INTEGER", true);
		m_dbAttribs[7] = new DBAttributes("MODIFY_PERMISSION", null, "INTEGER", true);
		m_dbAttribs[8] = new DBAttributes("DELETE_PERMISSION", null, "INTEGER", true);
		m_dbAttribs[9] = new DBAttributes("EXECUTE_PERMISSION", null, "INTEGER", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_user_id() {return m_dbAttribs[2].getValue();}
	public String get_description() {return m_dbAttribs[3].getValue();}
	public String get_in_module() {return m_dbAttribs[4].getValue();}
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

	public void set_user_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_description(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_in_module(String val)
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
		return new component_securityObj();
	}


}
