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

public class customer_contactObj extends BusinessObject
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
static public final String TABLE_NAME = "CUSTOMER_CONTACT";
static public final String ID = "CUSTOMER_CONTACT.ID";
static public final String LOCALITY = "CUSTOMER_CONTACT.LOCALITY";
static public final String DETAIL_ID = "CUSTOMER_CONTACT.DETAIL_ID";
static public final String NAME = "CUSTOMER_CONTACT.NAME";
static public final String TITLE = "CUSTOMER_CONTACT.TITLE";
static public final String PHONE1 = "CUSTOMER_CONTACT.PHONE1";
static public final String PHONE2 = "CUSTOMER_CONTACT.PHONE2";
static public final String FAX1 = "CUSTOMER_CONTACT.FAX1";
static public final String FAX2 = "CUSTOMER_CONTACT.FAX2";
static public final String PAGER = "CUSTOMER_CONTACT.PAGER";
static public final String MOBLE_PHONE = "CUSTOMER_CONTACT.MOBLE_PHONE";
static public final String EMAIL1 = "CUSTOMER_CONTACT.EMAIL1";
static public final String EMAIL2 = "CUSTOMER_CONTACT.EMAIL2";
static public final String WEB = "CUSTOMER_CONTACT.WEB";
static public final String NOTE = "CUSTOMER_CONTACT.NOTE";
static public final String IS_PRIMARY = "CUSTOMER_CONTACT.IS_PRIMARY";
static public final String ADDR1 = "CUSTOMER_CONTACT.ADDR1";
static public final String ADDR2 = "CUSTOMER_CONTACT.ADDR2";
static public final String ADDR3 = "CUSTOMER_CONTACT.ADDR3";
static public final String ATTN = "CUSTOMER_CONTACT.ATTN";
static public final String CITY = "CUSTOMER_CONTACT.CITY";
static public final String ZIP = "CUSTOMER_CONTACT.ZIP";
static public final String COUNTRY_CODE = "CUSTOMER_CONTACT.COUNTRY_CODE";
static public final String COUNTRY_NAME = "CUSTOMER_CONTACT.COUNTRY_NAME";
static public final String STATE_CODE = "CUSTOMER_CONTACT.STATE_CODE";


   //Constructor
   public customer_contactObj()
   {
//Private Field Members.
MAX_COLS = 25;
TAB_NAME = "CUSTOMER_CONTACT";
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
m_dbAttribs[16] = new DBAttributes("ADDR1", null, "VARCHAR", 50 ,true);
m_dbAttribs[17] = new DBAttributes("ADDR2", null, "VARCHAR", 50 ,true);
m_dbAttribs[18] = new DBAttributes("ADDR3", null, "VARCHAR", 50 ,true);
m_dbAttribs[19] = new DBAttributes("ATTN", null, "VARCHAR", 50 ,true);
m_dbAttribs[20] = new DBAttributes("CITY", null, "VARCHAR", 50 ,true);
m_dbAttribs[21] = new DBAttributes("ZIP", null, "VARCHAR", 15 ,true);
m_dbAttribs[22] = new DBAttributes("COUNTRY_CODE", null, "VARCHAR", 2 ,true);
m_dbAttribs[23] = new DBAttributes("COUNTRY_NAME", null, "VARCHAR", 30 ,true);
m_dbAttribs[24] = new DBAttributes("STATE_CODE", null, "VARCHAR", 10 ,true);
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
public String get_addr1() {return m_dbAttribs[16].getValue();}
public String get_addr2() {return m_dbAttribs[17].getValue();}
public String get_addr3() {return m_dbAttribs[18].getValue();}
public String get_attn() {return m_dbAttribs[19].getValue();}
public String get_city() {return m_dbAttribs[20].getValue();}
public String get_zip() {return m_dbAttribs[21].getValue();}
public String get_country_code() {return m_dbAttribs[22].getValue();}
public String get_country_name() {return m_dbAttribs[23].getValue();}
public String get_state_code() {return m_dbAttribs[24].getValue();}
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

public void set_addr1(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[16].setValue(null);
else
m_dbAttribs[16].setValue(val);
}

public void set_addr2(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[17].setValue(null);
else
m_dbAttribs[17].setValue(val);
}

public void set_addr3(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[18].setValue(null);
else
m_dbAttribs[18].setValue(val);
}

public void set_attn(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[19].setValue(null);
else
m_dbAttribs[19].setValue(val);
}

public void set_city(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[20].setValue(null);
else
m_dbAttribs[20].setValue(val);
}

public void set_zip(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[21].setValue(null);
else
m_dbAttribs[21].setValue(val);
}

public void set_country_code(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[22].setValue(null);
else
m_dbAttribs[22].setValue(val);
}

public void set_country_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[23].setValue(null);
else
m_dbAttribs[23].setValue(val);
}

public void set_state_code(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[24].setValue(null);
else
m_dbAttribs[24].setValue(val);
}

public BusinessObject getNewInstance()
{
return new customer_contactObj();
}


}
