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

public class default_accountsObj extends BusinessObject
{


//!!!!!!BEGIN USER SECTION!!!!!!!

//!!!!NOTE: ONLY ONE ROW OF THIS OBJECT SHOULD BE STORED IN THE DB.
//!!!!THE ROW SHOULD ALWAYS HAVE AN ID OF SINGLETON
static public final String SINGLETON_ID = "SINGLETON";

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
SessionMetaData sessionMeta = null;
sessionMeta = sessionMeta.getInstance();

set_locality(this.getObjLocality());
}
//!!!!!! END USER SECTION !!!!!!!
//!!!!!! END USER SECTION !!!!!!!

//STATIC DATA //
static public final String TABLE_NAME = "DEFAULT_ACCOUNTS";
static public final String ACCTS_RECEIVABLE_ID = "DEFAULT_ACCOUNTS.ACCTS_RECEIVABLE_ID";
static public final String ACCTS_RECEIVABLE_NAME = "DEFAULT_ACCOUNTS.ACCTS_RECEIVABLE_NAME";
static public final String SALES_TAX_PAYABLE_ID = "DEFAULT_ACCOUNTS.SALES_TAX_PAYABLE_ID";
static public final String SALES_TAX_PAYABLE_NAME = "DEFAULT_ACCOUNTS.SALES_TAX_PAYABLE_NAME";
static public final String UNDEPOSITED_FUNDS_ID = "DEFAULT_ACCOUNTS.UNDEPOSITED_FUNDS_ID";
static public final String UNDEPOSITED_FUNDS_NAME = "DEFAULT_ACCOUNTS.UNDEPOSITED_FUNDS_NAME";
static public final String CHECKING_ID = "DEFAULT_ACCOUNTS.CHECKING_ID";
static public final String CHECKING_NAME = "DEFAULT_ACCOUNTS.CHECKING_NAME";
static public final String ACCTS_PAYABLE_ID = "DEFAULT_ACCOUNTS.ACCTS_PAYABLE_ID";
static public final String ACCTS_PAYABLE_NAME = "DEFAULT_ACCOUNTS.ACCTS_PAYABLE_NAME";
static public final String ID = "DEFAULT_ACCOUNTS.ID";
static public final String LOCALITY = "DEFAULT_ACCOUNTS.LOCALITY";
static public final String COST_GOODS_SOLD_ID = "DEFAULT_ACCOUNTS.COST_GOODS_SOLD_ID";
static public final String COST_GOODS_SOLD_NAME = "DEFAULT_ACCOUNTS.COST_GOODS_SOLD_NAME";
static public final String SHIPPING_IN_ID = "DEFAULT_ACCOUNTS.SHIPPING_IN_ID";
static public final String SHIPPING_OUT_ID = "DEFAULT_ACCOUNTS.SHIPPING_OUT_ID";
static public final String SHIPPING_OUT_NAME = "DEFAULT_ACCOUNTS.SHIPPING_OUT_NAME";
static public final String SHIPPING_IN_NAME = "DEFAULT_ACCOUNTS.SHIPPING_IN_NAME";
static public final String INVENTORY_ID = "DEFAULT_ACCOUNTS.INVENTORY_ID";
static public final String INVENTORY_NAME = "DEFAULT_ACCOUNTS.INVENTORY_NAME";


   //Constructor
   public default_accountsObj()
   {
//Private Field Members.
MAX_COLS = 20;
TAB_NAME = "DEFAULT_ACCOUNTS";
m_dbAttribs = new DBAttributes[MAX_COLS];
      Init();
      setDefaults();
   }

public void Init() {
m_dbAttribs[0] = new DBAttributes("ACCTS_RECEIVABLE_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[1] = new DBAttributes("ACCTS_RECEIVABLE_NAME", null, "VARCHAR", 100 ,true);
m_dbAttribs[2] = new DBAttributes("SALES_TAX_PAYABLE_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[3] = new DBAttributes("SALES_TAX_PAYABLE_NAME", null, "VARCHAR", 100 ,true);
m_dbAttribs[4] = new DBAttributes("UNDEPOSITED_FUNDS_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[5] = new DBAttributes("UNDEPOSITED_FUNDS_NAME", null, "VARCHAR", 100 ,true);
m_dbAttribs[6] = new DBAttributes("CHECKING_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[7] = new DBAttributes("CHECKING_NAME", null, "VARCHAR", 100 ,true);
m_dbAttribs[8] = new DBAttributes("ACCTS_PAYABLE_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[9] = new DBAttributes("ACCTS_PAYABLE_NAME", null, "VARCHAR", 100 ,true);
m_dbAttribs[10] = new DBAttributes("ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[11] = new DBAttributes("LOCALITY", null, "VARCHAR", 30 ,true);
m_dbAttribs[12] = new DBAttributes("COST_GOODS_SOLD_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[13] = new DBAttributes("COST_GOODS_SOLD_NAME", null, "VARCHAR", 100 ,true);
m_dbAttribs[14] = new DBAttributes("SHIPPING_IN_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[15] = new DBAttributes("SHIPPING_OUT_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[16] = new DBAttributes("SHIPPING_OUT_NAME", null, "VARCHAR", 50 ,true);
m_dbAttribs[17] = new DBAttributes("SHIPPING_IN_NAME", null, "VARCHAR", 50 ,true);
m_dbAttribs[18] = new DBAttributes("INVENTORY_ID", null, "VARCHAR", 30 ,true);
m_dbAttribs[19] = new DBAttributes("INVENTORY_NAME", null, "VARCHAR", 50 ,true);
}
public String get_accts_receivable_id() {return m_dbAttribs[0].getValue();}
public String get_accts_receivable_name() {return m_dbAttribs[1].getValue();}
public String get_sales_tax_payable_id() {return m_dbAttribs[2].getValue();}
public String get_sales_tax_payable_name() {return m_dbAttribs[3].getValue();}
public String get_undeposited_funds_id() {return m_dbAttribs[4].getValue();}
public String get_undeposited_funds_name() {return m_dbAttribs[5].getValue();}
public String get_checking_id() {return m_dbAttribs[6].getValue();}
public String get_checking_name() {return m_dbAttribs[7].getValue();}
public String get_accts_payable_id() {return m_dbAttribs[8].getValue();}
public String get_accts_payable_name() {return m_dbAttribs[9].getValue();}
public String get_id() {return m_dbAttribs[10].getValue();}
public String get_locality() {return m_dbAttribs[11].getValue();}
public String get_cost_goods_sold_id() {return m_dbAttribs[12].getValue();}
public String get_cost_goods_sold_name() {return m_dbAttribs[13].getValue();}
public String get_shipping_in_id() {return m_dbAttribs[14].getValue();}
public String get_shipping_out_id() {return m_dbAttribs[15].getValue();}
public String get_shipping_out_name() {return m_dbAttribs[16].getValue();}
public String get_shipping_in_name() {return m_dbAttribs[17].getValue();}
public String get_inventory_id() {return m_dbAttribs[18].getValue();}
public String get_inventory_name() {return m_dbAttribs[19].getValue();}
public void set_accts_receivable_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[0].setValue(null);
else
m_dbAttribs[0].setValue(val);
}

public void set_accts_receivable_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[1].setValue(null);
else
m_dbAttribs[1].setValue(val);
}

public void set_sales_tax_payable_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[2].setValue(null);
else
m_dbAttribs[2].setValue(val);
}

public void set_sales_tax_payable_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[3].setValue(null);
else
m_dbAttribs[3].setValue(val);
}

public void set_undeposited_funds_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[4].setValue(null);
else
m_dbAttribs[4].setValue(val);
}

public void set_undeposited_funds_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[5].setValue(null);
else
m_dbAttribs[5].setValue(val);
}

public void set_checking_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[6].setValue(null);
else
m_dbAttribs[6].setValue(val);
}

public void set_checking_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[7].setValue(null);
else
m_dbAttribs[7].setValue(val);
}

public void set_accts_payable_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[8].setValue(null);
else
m_dbAttribs[8].setValue(val);
}

public void set_accts_payable_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[9].setValue(null);
else
m_dbAttribs[9].setValue(val);
}

public void set_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[10].setValue(null);
else
m_dbAttribs[10].setValue(val);
}

public void set_locality(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[11].setValue(null);
else
m_dbAttribs[11].setValue(val);
}

public void set_cost_goods_sold_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[12].setValue(null);
else
m_dbAttribs[12].setValue(val);
}

public void set_cost_goods_sold_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[13].setValue(null);
else
m_dbAttribs[13].setValue(val);
}

public void set_shipping_in_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[14].setValue(null);
else
m_dbAttribs[14].setValue(val);
}

public void set_shipping_out_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[15].setValue(null);
else
m_dbAttribs[15].setValue(val);
}

public void set_shipping_out_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[16].setValue(null);
else
m_dbAttribs[16].setValue(val);
}

public void set_shipping_in_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[17].setValue(null);
else
m_dbAttribs[17].setValue(val);
}

public void set_inventory_id(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[18].setValue(null);
else
m_dbAttribs[18].setValue(val);
}

public void set_inventory_name(String val)
{
if (val == null || val.length() == 0)
m_dbAttribs[19].setValue(null);
else
m_dbAttribs[19].setValue(val);
}

public BusinessObject getNewInstance()
{
return new default_accountsObj();
}


}
