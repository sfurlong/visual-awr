
package dai.shared.businessObjs;

import java.io.Serializable;

abstract public class BusinessObject implements Serializable, Cloneable
{
	public static String DATA_TYPE_NUMERIC  = "NUMERIC";
	public static String DATA_TYPE_VARCHAR  = "VARCHAR";
	public static String DATA_TYPE_DATE     = "DATE";
	public static String DATA_TYPE_CHAR     = "CHAR";

	public boolean EXISTS_IN_DB = false;

	//Constructor.
	public BusinessObject()
	{
	}

	//Determines the locality for this object (or decendant objects)
	//
	public static String getObjLocality()
	{
		//!!TBD Logic
		//Check the list of BusinessObjects (a singleton) to determin if
		//this table is marked as locational or not.
		//If table is locational, return the currently logged in users locality
		//otherwise return "SUPER".
		return "SUPER";
	}

	abstract public String get_id();
	abstract public void set_id(String id);
	abstract public BusinessObject getNewInstance();
	abstract public void clear(boolean clearImmutables);

	public void setDefaults(){};

	//Only used for detail record types.
	public String get_detail_id()
	{
		return null;
	}

	//Only used for detail record types.
	public void set_detail_id(String val)
	{
	}

	public void set_date_created(String val) {
	}

	public void set_created_by(String val) {
	}

	//Used to acess the table name when all we have
	//is a reference to the base class.
	public String getTableName()
	{
		return TAB_NAME;
	}

	public DBAttributes[] getAttribList()
	{
		return m_dbAttribs;
	}

	public void setAttribList(DBAttributes[] dbAttribs)
	{
		m_dbAttribs = dbAttribs;
	}

	public Object Clone() {
		try
		{
			BusinessObject newObj = (BusinessObject)super.clone();
			//Need this step to copy the array.
			newObj.m_dbAttribs = (DBAttributes[])m_dbAttribs.clone();
			return newObj;
		} catch (CloneNotSupportedException e)
		{
			//This should never happen.
			throw new InternalError(e.toString());
		}
	}

	abstract public DBAttributes[] getImmutableAttribs();

	//Private Field Members.
	protected int MAX_COLS;
	//Used to acess the table name when all we have
	//is a reference to the base class.
	protected String TAB_NAME;
	protected DBAttributes m_dbAttribs[];

}
