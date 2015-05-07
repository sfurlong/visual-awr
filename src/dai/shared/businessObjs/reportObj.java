/*******************************************************************************
 *
 * Copyright 2015 Stephen Furlong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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

public class reportObj extends BusinessObject
{


	//!!!!!!BEGIN USER SECTION!!!!!!!
	public reportObj(String reportType)
	{
		//Private Field Members.
		MAX_COLS = 13;
		TAB_NAME = "REPORT";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		set_report_type(reportType);
		setDefaults();
	}

	public void clear(boolean clearImmutables)
	{
		String reportType = get_report_type();
		String locality = get_locality();

		for (int i=0; i < MAX_COLS; i++)
		{
			m_dbAttribs[i].setValue(null);
		}

		if (!clearImmutables)
		{
			set_report_type(reportType);
			set_locality(locality);
		}
	}

	public DBAttributes[] getImmutableAttribs()
	{
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("locality", get_locality(), "CHAR", true);
		imutables[1] = new DBAttributes("report_type", get_report_type(), "CHAR", true);
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
	public reportObj()
	{
		//Private Field Members.
		MAX_COLS = 13;
		m_dbAttribs = new DBAttributes[MAX_COLS];
		TAB_NAME = "REPORT";
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("ID", null, "VARCHAR", true);
		m_dbAttribs[1] = new DBAttributes("REPORT_TYPE", null, "VARCHAR", true);
		m_dbAttribs[2] = new DBAttributes("LOCALITY", null, "VARCHAR", true);
		m_dbAttribs[3] = new DBAttributes("DATE_CREATED", null, "DATE", true);
		m_dbAttribs[4] = new DBAttributes("CREATED_BY", null, "VARCHAR", true);
		m_dbAttribs[5] = new DBAttributes("DESCRIPTION", null, "VARCHAR", true);
		m_dbAttribs[6] = new DBAttributes("SQL_STATEMENT", null, "VARCHAR", true);
		m_dbAttribs[7] = new DBAttributes("WHERE_CLAUSE", null, "VARCHAR", true);
		m_dbAttribs[8] = new DBAttributes("TITLE", null, "VARCHAR", true);
		m_dbAttribs[9] = new DBAttributes("IMAGE", null, "VARCHAR", true);
		m_dbAttribs[10] = new DBAttributes("REPORT_TEMPLATE", null, "VARCHAR", true);
		m_dbAttribs[11] = new DBAttributes("HEADER_NOTE", null, "VARCHAR", true);
		m_dbAttribs[12] = new DBAttributes("FOOTER_NOTE", null, "VARCHAR", true);
	}
	public String get_id() {return m_dbAttribs[0].getValue();}
	public String get_report_type() {return m_dbAttribs[1].getValue();}
	public String get_locality() {return m_dbAttribs[2].getValue();}
	public String get_date_created() {return m_dbAttribs[3].getValue();}
	public String get_created_by() {return m_dbAttribs[4].getValue();}
	public String get_description() {return m_dbAttribs[5].getValue();}
	public String get_sql_statement() {return m_dbAttribs[6].getValue();}
	public String get_where_clause() {return m_dbAttribs[7].getValue();}
	public String get_title() {return m_dbAttribs[8].getValue();}
	public String get_image() {return m_dbAttribs[9].getValue();}
	public String get_report_template() {return m_dbAttribs[10].getValue();}
	public String get_header_note() {return m_dbAttribs[11].getValue();}
	public String get_footer_note() {return m_dbAttribs[12].getValue();}
	public void set_id(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[0].setValue(null);
		else
			m_dbAttribs[0].setValue(val);
	}

	public void set_report_type(String val)
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

	public void set_date_created(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_created_by(String val)
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

	public void set_sql_statement(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_where_clause(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_title(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_image(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_report_template(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_header_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_footer_note(String val)
	{
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public BusinessObject getNewInstance()
	{
		return new reportObj();
	}


}
