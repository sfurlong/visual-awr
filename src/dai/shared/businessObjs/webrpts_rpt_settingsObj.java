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

public class webrpts_rpt_settingsObj extends BusinessObject
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

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "WEBRPTS_RPT_SETTINGS";
	static public final String ID = "WEBRPTS_RPT_SETTINGS.ID";
	static public final String LOCALITY = "WEBRPTS_RPT_SETTINGS.LOCALITY";
	static public final String RPT_NAME = "WEBRPTS_RPT_SETTINGS.RPT_NAME";
	static public final String RPT_DESCRIPTION = "WEBRPTS_RPT_SETTINGS.RPT_DESCRIPTION";
	static public final String XML_FILE_NAME = "WEBRPTS_RPT_SETTINGS.XML_FILE_NAME";
	static public final String RPT_URL = "WEBRPTS_RPT_SETTINGS.RPT_URL";
	static public final String RPT_SEQ_IN_TAB = "WEBRPTS_RPT_SETTINGS.RPT_SEQ_IN_TAB";
	static public final String TAB_ID = "WEBRPTS_RPT_SETTINGS.TAB_ID";
	static public final String RPT_PARAM_PAGE_NAME = "WEBRPTS_RPT_SETTINGS.RPT_PARAM_PAGE_NAME";


	//Constructor
	public webrpts_rpt_settingsObj()
	{
		//Private Field Members.
		MAX_COLS = 9;
		TAB_NAME = "WEBRPTS_RPT_SETTINGS";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init()
	{
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("RPT_NAME", null, "VARCHAR", 100 ,true);
		m_dbAttribs[3] = new DBAttributes("RPT_DESCRIPTION", null, "VARCHAR", 500 ,true);
		m_dbAttribs[4] = new DBAttributes("XML_FILE_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("RPT_URL", null, "VARCHAR", 100 ,true);
		m_dbAttribs[6] = new DBAttributes("RPT_SEQ_IN_TAB", null, "INTEGER", 10 ,true);
		m_dbAttribs[7] = new DBAttributes("TAB_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[8] = new DBAttributes("RPT_PARAM_PAGE_NAME", null, "VARCHAR", 50 ,true);
	}
	public String get_id()
	{return m_dbAttribs[0].getValue();}
	public String get_locality()
	{return m_dbAttribs[1].getValue();}
	public String get_rpt_name()
	{return m_dbAttribs[2].getValue();}
	public String get_rpt_description()
	{return m_dbAttribs[3].getValue();}
	public String get_xml_file_name()
	{return m_dbAttribs[4].getValue();}
	public String get_rpt_url()
	{return m_dbAttribs[5].getValue();}
	public String get_rpt_seq_in_tab()
	{return m_dbAttribs[6].getValue();}
	public String get_tab_id()
	{return m_dbAttribs[7].getValue();}
	public String get_rpt_param_page_name()
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

	public void set_rpt_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_rpt_description(String val)
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

	public void set_rpt_url(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_rpt_seq_in_tab(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_tab_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_rpt_param_page_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new webrpts_rpt_settingsObj();
	}


}
