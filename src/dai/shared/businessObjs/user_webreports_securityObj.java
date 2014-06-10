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

public class user_webreports_securityObj extends BusinessObject
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
	public int _get_qty_to_ship()
	{return qtyToShip;}
	public void _set_qty_to_ship(int qty)
	{qtyToShip = qty;}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "USER_WEBREPORTS_SECURITY";
	static public final String ID = "USER_WEBREPORTS_SECURITY.ID";
	static public final String LOCALITY = "USER_WEBREPORTS_SECURITY.LOCALITY";
	static public final String COMPONENT_ID = "USER_WEBREPORTS_SECURITY.COMPONENT_ID";
	static public final String READ_PERMISSION = "USER_WEBREPORTS_SECURITY.READ_PERMISSION";
	static public final String XML_FILE_NAME = "USER_WEBREPORTS_SECURITY.XML_FILE_NAME";
	static public final String REPORT_NAME = "USER_WEBREPORTS_SECURITY.REPORT_NAME";
	static public final String REPORT_DESCRIPTION = "USER_WEBREPORTS_SECURITY.REPORT_DESCRIPTION";
	static public final String DISPLAY_GROUP_NAME = "USER_WEBREPORTS_SECURITY.DISPLAY_GROUP_NAME";
	static public final String RPT_URL = "USER_WEBREPORTS_SECURITY.RPT_URL";


	//Constructor
	public user_webreports_securityObj()
	{
		//Private Field Members.
		MAX_COLS = 9;
		TAB_NAME = "USER_WEBREPORTS_SECURITY";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init()
	{
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("COMPONENT_ID", null, "VARCHAR", 50 ,true);
		m_dbAttribs[3] = new DBAttributes("READ_PERMISSION", null, "CHAR", 1 ,true);
		m_dbAttribs[4] = new DBAttributes("XML_FILE_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("REPORT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[6] = new DBAttributes("REPORT_DESCRIPTION", null, "VARCHAR", 200 ,true);
		m_dbAttribs[7] = new DBAttributes("DISPLAY_GROUP_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("RPT_URL", null, "VARCHAR", 100 ,true);
	}
	public String get_id()
	{return m_dbAttribs[0].getValue();}
	public String get_locality()
	{return m_dbAttribs[1].getValue();}
	public String get_component_id()
	{return m_dbAttribs[2].getValue();}
	public String get_read_permission()
	{return m_dbAttribs[3].getValue();}
	public String get_xml_file_name()
	{return m_dbAttribs[4].getValue();}
	public String get_report_name()
	{return m_dbAttribs[5].getValue();}
	public String get_report_description()
	{return m_dbAttribs[6].getValue();}
	public String get_display_group_name()
	{return m_dbAttribs[7].getValue();}
	public String get_rpt_url()
	{return m_dbAttribs[8].getValue();}
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

	public void set_component_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_read_permission(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_xml_file_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_report_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_report_description(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_display_group_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_rpt_url(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new user_webreports_securityObj();
	}


}
