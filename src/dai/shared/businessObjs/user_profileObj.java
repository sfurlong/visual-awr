//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

//!!WARNING:  THIS IS A GENERATED CLASS
//!!WARNING:  ONLY MODIFY SECTION BETWEEN BEGIN AND END OF USER SECTION

package dai.shared.businessObjs;


public class user_profileObj extends BusinessObject
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
		DBAttributes imutables[] = new DBAttributes[1];
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		return imutables;
	}

	public void setDefaults()
	{
		//Initialize some of the attributes that should have default values.
		//Initialize some of the attributes that should have default values.
		set_locality(this.getObjLocality());
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
   static public final String TABLE_NAME = "USER_PROFILE";
	static public final String ID = "USER_PROFILE.ID";
	static public final String LOCALITY = "USER_PROFILE.LOCALITY";
	static public final String ENCRYPTED_PWD = "USER_PROFILE.ENCRYPTED_PWD";
	static public final String USER_HOME_LOCALITY = "USER_PROFILE.USER_HOME_LOCALITY";
	static public final String USER_NAME = "USER_PROFILE.USER_NAME";
	static public final String USER_TITLE = "USER_PROFILE.USER_TITLE";
	static public final String USER_ROLE_ID = "USER_PROFILE.USER_ROLE_ID";
	static public final String USER_1 = "USER_PROFILE.USER_1";
	static public final String USER_2 = "USER_PROFILE.USER_2";
	static public final String USER_3 = "USER_PROFILE.USER_3";
	static public final String USER_EMAIL = "USER_PROFILE.USER_EMAIL";
	static public final String NOTE = "USER_PROFILE.NOTE";
	static public final String DATE_CREATED = "USER_PROFILE.DATE_CREATED";
	static public final String CREATED_BY = "USER_PROFILE.CREATED_BY";
	static public final String IS_ADMINISTRATOR = "USER_PROFILE.IS_ADMINISTRATOR";


	//Constructor
	public user_profileObj()
	{
		//Private Field Members.
		MAX_COLS = 15;
		TAB_NAME = "USER_PROFILE";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init()
	{
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[1] = new DBAttributes("LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[2] = new DBAttributes("ENCRYPTED_PWD", null, "VARCHAR", 20 ,true);
		m_dbAttribs[3] = new DBAttributes("USER_HOME_LOCALITY", null, "VARCHAR", 20 ,true);
		m_dbAttribs[4] = new DBAttributes("USER_NAME", null, "VARCHAR", 50 ,true);
		m_dbAttribs[5] = new DBAttributes("USER_TITLE", null, "VARCHAR", 50 ,true);
		m_dbAttribs[6] = new DBAttributes("USER_ROLE_ID", null, "VARCHAR", 30 ,true);
		m_dbAttribs[7] = new DBAttributes("USER_1", null, "VARCHAR", 50 ,true);
		m_dbAttribs[8] = new DBAttributes("USER_2", null, "VARCHAR", 50 ,true);
		m_dbAttribs[9] = new DBAttributes("USER_3", null, "VARCHAR", 50 ,true);
		m_dbAttribs[10] = new DBAttributes("USER_EMAIL", null, "VARCHAR", 100 ,true);
		m_dbAttribs[11] = new DBAttributes("NOTE", null, "VARCHAR", 2000 ,true);
		m_dbAttribs[12] = new DBAttributes("DATE_CREATED", null, "DATE", 19 ,true);
		m_dbAttribs[13] = new DBAttributes("CREATED_BY", null, "VARCHAR", 30 ,true);
		m_dbAttribs[14] = new DBAttributes("IS_ADMINISTRATOR", null, "CHAR", 1 ,true);
	}
	public String get_id()
	{return m_dbAttribs[0].getValue();}
	public String get_locality()
	{return m_dbAttribs[1].getValue();}
	public String get_encrypted_pwd()
	{return m_dbAttribs[2].getValue();}
	public String get_user_home_locality()
	{return m_dbAttribs[3].getValue();}
	public String get_user_name()
	{return m_dbAttribs[4].getValue();}
	public String get_user_title()
	{return m_dbAttribs[5].getValue();}
	public String get_user_role_id()
	{return m_dbAttribs[6].getValue();}
	public String get_user_1()
	{return m_dbAttribs[7].getValue();}
	public String get_user_2()
	{return m_dbAttribs[8].getValue();}
	public String get_user_3()
	{return m_dbAttribs[9].getValue();}
	public String get_user_email()
	{return m_dbAttribs[10].getValue();}
	public String get_note()
	{return m_dbAttribs[11].getValue();}
	public String get_date_created()
	{return m_dbAttribs[12].getValue();}
	public String get_created_by()
	{return m_dbAttribs[13].getValue();}
	public String get_is_administrator()
	{return m_dbAttribs[14].getValue();}
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

	public void set_encrypted_pwd(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_user_home_locality(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_user_name(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_user_title(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_user_role_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_user_1(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_user_2(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_user_3(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_user_email(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_created_by(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_is_administrator(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new user_profileObj();
	}


}
