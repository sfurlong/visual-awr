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

public class system_setupObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!

	public void clear(boolean clearImmutables)
	{
		String setupType = get_setup_type();
		String locality = get_locality();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_setup_type(setupType);
			set_locality(locality);
		}
	}

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		imutables[1] = new DBAttributes("setup_type", get_setup_type(), "CHAR", true);
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

		set_locality(sessionMeta.getLocality());

		set_created_by(sessionMeta.getUserId());
	}

	//!!!!!! END USER SECTION !!!!!!!

	//Constructor
	public system_setupObj()
	{
		//Private Field Members.
		MAX_COLS = 6;
		m_dbAttribs = new DBAttributes[MAX_COLS];
		TAB_NAME = "SYSTEM_SETUP";
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("SETUP_TYPE", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[3] = new DBAttributes("CREATED_BY", null, "VARCHAR", true);
		m_dbAttribs[4] = new DBAttributes("DATE_CREATED", null, "DATE", true);
		m_dbAttribs[5] = new DBAttributes("DESCRIPTION", null, "VARCHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_setup_type() {return m_dbAttribs[1].getValue();}
	public String get_locality() {return m_dbAttribs[2].getValue();}
	public String get_created_by() {return m_dbAttribs[3].getValue();}
	public String get_date_created() {return m_dbAttribs[4].getValue();}
	public String get_description() {return m_dbAttribs[5].getValue();}
	public void set_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[0].setValue(null);
		else
			m_dbAttribs[0].setValue(val);
	}

	public void set_setup_type(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[1].setValue(null);
		else
			m_dbAttribs[1].setValue(val);
	}

	public void set_locality(String val)
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

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_description(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new system_setupObj();
	}


}
