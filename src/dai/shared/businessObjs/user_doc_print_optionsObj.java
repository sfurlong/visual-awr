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

public class user_doc_print_optionsObj extends BusinessObject
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
	static public final String TABLE_NAME = "USER_DOC_PRINT_OPTIONS";
	static public final String ID = "USER_DOC_PRINT_OPTIONS.ID";
	static public final String LOCALITY = "USER_DOC_PRINT_OPTIONS.LOCALITY";
	static public final String REPORT_NAME = "USER_DOC_PRINT_OPTIONS.REPORT_NAME";
	static public final String PRINTER_NAME = "USER_DOC_PRINT_OPTIONS.PRINTER_NAME";
	static public final String PRINTER_PORT = "USER_DOC_PRINT_OPTIONS.PRINTER_PORT";
	static public final String PRINTER_TRAY = "USER_DOC_PRINT_OPTIONS.PRINTER_TRAY";
	static public final String NOTE1 = "USER_DOC_PRINT_OPTIONS.NOTE1";
	static public final String USER1 = "USER_DOC_PRINT_OPTIONS.USER1";
	static public final String USER2 = "USER_DOC_PRINT_OPTIONS.USER2";
	static public final String USER3 = "USER_DOC_PRINT_OPTIONS.USER3";
	static public final String USER4 = "USER_DOC_PRINT_OPTIONS.USER4";
	static public final String USER5 = "USER_DOC_PRINT_OPTIONS.USER5";
	static public final String PRINTER_NUM_COPIES = "USER_DOC_PRINT_OPTIONS.PRINTER_NUM_COPIES";


	//Constructor
	public user_doc_print_optionsObj()
	{
		//Private Field Members.
		MAX_COLS = 13;
		TAB_NAME = "USER_DOC_PRINT_OPTIONS";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("REPORT_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[3] = new DBAttributes("PRINTER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[4] = new DBAttributes("PRINTER_PORT", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("PRINTER_TRAY", null, "CHAR", 1 ,true);
		m_dbAttribs[6] = new DBAttributes("NOTE1", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[7] = new DBAttributes("USER1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("USER2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[9] = new DBAttributes("USER3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[10] = new DBAttributes("USER4", null, "VARCHAR", 50 ,true);
		m_dbAttribs[11] = new DBAttributes("USER5", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("PRINTER_NUM_COPIES", null, "INTEGER", 10 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_report_name() {return m_dbAttribs[2].getValue();}
	public String get_printer_name() {return m_dbAttribs[3].getValue();}
	public String get_printer_port() {return m_dbAttribs[4].getValue();}
	public String get_printer_tray() {return m_dbAttribs[5].getValue();}
	public String get_note1() {return m_dbAttribs[6].getValue();}
	public String get_user1() {return m_dbAttribs[7].getValue();}
	public String get_user2() {return m_dbAttribs[8].getValue();}
	public String get_user3() {return m_dbAttribs[9].getValue();}
	public String get_user4() {return m_dbAttribs[10].getValue();}
	public String get_user5() {return m_dbAttribs[11].getValue();}
	public String get_printer_num_copies() {return m_dbAttribs[12].getValue();}
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

	public void set_report_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_printer_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_printer_port(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_printer_tray(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_note1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_user1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_user2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_user3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_user4(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_user5(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_printer_num_copies(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new user_doc_print_optionsObj();
	}


}
