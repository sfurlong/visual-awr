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

public class vendor_contactObj extends BusinessObject
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
		//Add the current date to the Date Created Field.
		SessionMetaData sessionMeta = null;
		sessionMeta = sessionMeta.getInstance();
		set_locality(sessionMeta.getLocality());
	}

	//!!!!!! END USER SECTION !!!!!!!
	//!!!!!! END USER SECTION !!!!!!!

	//STATIC DATA //
	static public final String TABLE_NAME = "VENDOR_CONTACT";
	static public final String ID = "VENDOR_CONTACT.ID";
	static public final String LOCALITY = "VENDOR_CONTACT.LOCALITY";
	static public final String DETAIL_ID = "VENDOR_CONTACT.DETAIL_ID";
	static public final String NAME = "VENDOR_CONTACT.NAME";
	static public final String TITLE = "VENDOR_CONTACT.TITLE";
	static public final String PHONE1 = "VENDOR_CONTACT.PHONE1";
	static public final String PHONE2 = "VENDOR_CONTACT.PHONE2";
	static public final String FAX1 = "VENDOR_CONTACT.FAX1";
	static public final String FAX2 = "VENDOR_CONTACT.FAX2";
	static public final String PAGER = "VENDOR_CONTACT.PAGER";
	static public final String MOBLE_PHONE = "VENDOR_CONTACT.MOBLE_PHONE";
	static public final String EMAIL1 = "VENDOR_CONTACT.EMAIL1";
	static public final String EMAIL2 = "VENDOR_CONTACT.EMAIL2";
	static public final String WEB = "VENDOR_CONTACT.WEB";
	static public final String NOTE = "VENDOR_CONTACT.NOTE";
	static public final String IS_PRIMARY = "VENDOR_CONTACT.IS_PRIMARY";


	//Constructor
	public vendor_contactObj()
	{
		//Private Field Members.
		MAX_COLS = 16;
		TAB_NAME = "VENDOR_CONTACT";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("DETAIL_ID", null, "INTEGER", 10 ,true);
		m_dbAttribs[3] = new DBAttributes("NAME", null, "VARCHAR", 25 ,true);
		m_dbAttribs[4] = new DBAttributes("TITLE", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("PHONE1", null, "VARCHAR", 25 ,true);
		m_dbAttribs[6] = new DBAttributes("PHONE2", null, "VARCHAR", 25 ,true);
		m_dbAttribs[7] = new DBAttributes("FAX1", null, "VARCHAR", 25 ,true);
		m_dbAttribs[8] = new DBAttributes("FAX2", null, "VARCHAR", 25 ,true);
		m_dbAttribs[9] = new DBAttributes("PAGER", null, "VARCHAR", 25 ,true);
		m_dbAttribs[10] = new DBAttributes("MOBLE_PHONE", null, "VARCHAR", 25 ,true);
		m_dbAttribs[11] = new DBAttributes("EMAIL1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[12] = new DBAttributes("EMAIL2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[13] = new DBAttributes("WEB", null, "VARCHAR", 50 ,true);
		m_dbAttribs[14] = new DBAttributes("NOTE", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[15] = new DBAttributes("IS_PRIMARY", null, "CHAR", 1 ,true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_locality() {return m_dbAttribs[1].getValue();}
	public String get_detail_id() {return m_dbAttribs[2].getValue();}
	public String get_name() {return m_dbAttribs[3].getValue();}
	public String get_title() {return m_dbAttribs[4].getValue();}
	public String get_phone1() {return m_dbAttribs[5].getValue();}
	public String get_phone2() {return m_dbAttribs[6].getValue();}
	public String get_fax1() {return m_dbAttribs[7].getValue();}
	public String get_fax2() {return m_dbAttribs[8].getValue();}
	public String get_pager() {return m_dbAttribs[9].getValue();}
	public String get_moble_phone() {return m_dbAttribs[10].getValue();}
	public String get_email1() {return m_dbAttribs[11].getValue();}
	public String get_email2() {return m_dbAttribs[12].getValue();}
	public String get_web() {return m_dbAttribs[13].getValue();}
	public String get_note() {return m_dbAttribs[14].getValue();}
	public String get_is_primary() {return m_dbAttribs[15].getValue();}
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

	public void set_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_title(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_phone1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_phone2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_fax1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_fax2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_pager(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_moble_phone(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_email1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_email2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_web(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_is_primary(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new vendor_contactObj();
	}


}
