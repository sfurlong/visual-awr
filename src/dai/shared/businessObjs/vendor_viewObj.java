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

import dai.shared.businessObjs.*;
import org.altaprise.vawr.utils.SessionMetaData;
import java.util.Calendar;

public class vendor_viewObj extends BusinessObject {

	// !!!!!!BEGIN USER SECTION!!!!!!!
	public DBAttributes[] getImmutableAttribs() {
		DBAttributes imutables[] = new DBAttributes[2];
		imutables[0] = new DBAttributes("id", get_id(), "CHAR", true);
		return imutables;
	}

	public void clear(boolean clearImmutables) {
		for (int i = 0; i < MAX_COLS; i++) {
			m_dbAttribs[i].setValue(null);
		}
	}

	public String get_id() {
		return get_vendor_id();
	}

	public void set_id(String val) {
		set_vendor_id(val);
	}

	// !!!!!! END USER SECTION !!!!!!!

	// STATIC DATA //
	static public final String TABLE_NAME = "VENDOR_VIEW";

	static public final String VENDOR_ID = "VENDOR_VIEW.VENDOR_ID";

	static public final String NAME = "VENDOR_VIEW.NAME";

	static public final String PARENT_ID = "VENDOR_VIEW.PARENT_ID";

	static public final String TERM_NAME = "VENDOR_VIEW.TERM_NAME";

	static public final String VENDTYPE_NAME = "VENDOR_VIEW.VENDTYPE_NAME";

	static public final String TAX_ID = "VENDOR_VIEW.TAX_ID";

	static public final String CREDIT_LIMIT = "VENDOR_VIEW.CREDIT_LIMIT";

	static public final String VENDOR_ACCTNO = "VENDOR_VIEW.VENDOR_ACCTNO";

	static public final String ACCT_LABEL = "VENDOR_VIEW.ACCT_LABEL";

	static public final String GL_ACCTNO = "VENDOR_VIEW.GL_ACCTNO";

	static public final String BILLING_TYPE = "VENDOR_VIEW.BILLING_TYPE";

	static public final String ONHOLD = "VENDOR_VIEW.ONHOLD";

	static public final String COMMENTS = "VENDOR_VIEW.COMMENTS";

	static public final String FORM1099TYPE = "VENDOR_VIEW.FORM1099TYPE";

	static public final String FORM1099BOX = "VENDOR_VIEW.FORM1099BOX";

	static public final String PAYMENTPRIORITY = "VENDOR_VIEW.PAYMENTPRIORITY";

	static public final String HIDEDISPLAYCONTACT = "VENDOR_VIEW.HIDEDISPLAYCONTACT";

	static public final String IS_ACTIVE = "VENDOR_VIEW.IS_ACTIVE";

	static public final String CONTACT_NAME = "VENDOR_VIEW.CONTACT_NAME";

	static public final String COMPANY_NAME = "VENDOR_VIEW.COMPANY_NAME";

	static public final String MRMS = "VENDOR_VIEW.MRMS";

	static public final String FIRST_NAME = "VENDOR_VIEW.FIRST_NAME";

	static public final String MI = "VENDOR_VIEW.MI";

	static public final String LAST_NAME = "VENDOR_VIEW.LAST_NAME";

	static public final String PRINT_AS = "VENDOR_VIEW.PRINT_AS";

	static public final String PHONE1 = "VENDOR_VIEW.PHONE1";

	static public final String PHONE2 = "VENDOR_VIEW.PHONE2";

	static public final String CELLPHONE = "VENDOR_VIEW.CELLPHONE";

	static public final String PAGER = "VENDOR_VIEW.PAGER";

	static public final String FAX = "VENDOR_VIEW.FAX";

	static public final String EMAIL1 = "VENDOR_VIEW.EMAIL1";

	static public final String EMAIL2 = "VENDOR_VIEW.EMAIL2";

	static public final String URL1 = "VENDOR_VIEW.URL1";

	static public final String URL2 = "VENDOR_VIEW.URL2";

	static public final String ADDRESS1 = "VENDOR_VIEW.ADDRESS1";

	static public final String ADDRESS2 = "VENDOR_VIEW.ADDRESS2";

	static public final String CITY = "VENDOR_VIEW.CITY";

	static public final String STATE = "VENDOR_VIEW.STATE";

	static public final String ZIP = "VENDOR_VIEW.ZIP";

	static public final String COUNTRY = "VENDOR_VIEW.COUNTRY";

	// Constructor
	public vendor_viewObj() {
		// Private Field Members.
		MAX_COLS = 40;
		TAB_NAME = "VENDOR_VIEW";
		m_dbAttribs = new DBAttributes[MAX_COLS];
		Init();
		setDefaults();
	}

	public void Init() {
		m_dbAttribs[0] = new DBAttributes("VENDOR_ID", null, "VARCHAR", 30,
				true);
		m_dbAttribs[1] = new DBAttributes("NAME", null, "VARCHAR", 50, true);
		m_dbAttribs[2] = new DBAttributes("PARENT_ID", null, "CHAR", 0, true);
		m_dbAttribs[3] = new DBAttributes("TERM_NAME", null, "CHAR", 0, true);
		m_dbAttribs[4] = new DBAttributes("VENDTYPE_NAME", null, "VARCHAR", 20,
				true);
		m_dbAttribs[5] = new DBAttributes("TAX_ID", null, "VARCHAR", 15, true);
		m_dbAttribs[6] = new DBAttributes("CREDIT_LIMIT", null, "CHAR", 0, true);
		m_dbAttribs[7] = new DBAttributes("VENDOR_ACCTNO", null, "VARCHAR", 30,
				true);
		m_dbAttribs[8] = new DBAttributes("ACCT_LABEL", null, "CHAR", 0, true);
		m_dbAttribs[9] = new DBAttributes("GL_ACCTNO", null, "VARCHAR", 30,
				true);
		m_dbAttribs[10] = new DBAttributes("BILLING_TYPE", null, "CHAR", 0,
				true);
		m_dbAttribs[11] = new DBAttributes("ONHOLD", null, "CHAR", 0, true);
		m_dbAttribs[12] = new DBAttributes("COMMENTS", null, "VARCHAR", 2000,
				true);
		m_dbAttribs[13] = new DBAttributes("FORM1099TYPE", null, "CHAR", 0,
				true);
		m_dbAttribs[14] = new DBAttributes("FORM1099BOX", null, "CHAR", 0, true);
		m_dbAttribs[15] = new DBAttributes("PAYMENTPRIORITY", null, "CHAR", 0,
				true);
		m_dbAttribs[16] = new DBAttributes("HIDEDISPLAYCONTACT", null, "CHAR",
				0, true);
		m_dbAttribs[17] = new DBAttributes("IS_ACTIVE", null, "CHAR", 0, true);
		m_dbAttribs[18] = new DBAttributes("CONTACT_NAME", null, "VARCHAR", 25,
				true);
		m_dbAttribs[19] = new DBAttributes("COMPANY_NAME", null, "VARCHAR", 50,
				true);
		m_dbAttribs[20] = new DBAttributes("MRMS", null, "CHAR", 0, true);
		m_dbAttribs[21] = new DBAttributes("FIRST_NAME", null, "CHAR", 0, true);
		m_dbAttribs[22] = new DBAttributes("MI", null, "CHAR", 0, true);
		m_dbAttribs[23] = new DBAttributes("LAST_NAME", null, "CHAR", 0, true);
		m_dbAttribs[24] = new DBAttributes("PRINT_AS", null, "CHAR", 0, true);
		m_dbAttribs[25] = new DBAttributes("PHONE1", null, "VARCHAR", 25, true);
		m_dbAttribs[26] = new DBAttributes("PHONE2", null, "VARCHAR", 25, true);
		m_dbAttribs[27] = new DBAttributes("CELLPHONE", null, "VARCHAR", 25,
				true);
		m_dbAttribs[28] = new DBAttributes("PAGER", null, "VARCHAR", 25, true);
		m_dbAttribs[29] = new DBAttributes("FAX", null, "VARCHAR", 25, true);
		m_dbAttribs[30] = new DBAttributes("EMAIL1", null, "VARCHAR", 50, true);
		m_dbAttribs[31] = new DBAttributes("EMAIL2", null, "VARCHAR", 50, true);
		m_dbAttribs[32] = new DBAttributes("URL1", null, "VARCHAR", 50, true);
		m_dbAttribs[33] = new DBAttributes("URL2", null, "CHAR", 0, true);
		m_dbAttribs[34] = new DBAttributes("ADDRESS1", null, "CHAR", 0, true);
		m_dbAttribs[35] = new DBAttributes("ADDRESS2", null, "CHAR", 0, true);
		m_dbAttribs[36] = new DBAttributes("CITY", null, "CHAR", 0, true);
		m_dbAttribs[37] = new DBAttributes("STATE", null, "CHAR", 0, true);
		m_dbAttribs[38] = new DBAttributes("ZIP", null, "CHAR", 0, true);
		m_dbAttribs[39] = new DBAttributes("COUNTRY", null, "CHAR", 0, true);
	}

	public String get_vendor_id() {
		return m_dbAttribs[0].getValue();
	}

	public String get_name() {
		return m_dbAttribs[1].getValue();
	}

	public String get_parent_id() {
		return m_dbAttribs[2].getValue();
	}

	public String get_term_name() {
		return m_dbAttribs[3].getValue();
	}

	public String get_vendtype_name() {
		return m_dbAttribs[4].getValue();
	}

	public String get_tax_id() {
		return m_dbAttribs[5].getValue();
	}

	public String get_credit_limit() {
		return m_dbAttribs[6].getValue();
	}

	public String get_vendor_acctno() {
		return m_dbAttribs[7].getValue();
	}

	public String get_acct_label() {
		return m_dbAttribs[8].getValue();
	}

	public String get_gl_acctno() {
		return m_dbAttribs[9].getValue();
	}

	public String get_billing_type() {
		return m_dbAttribs[10].getValue();
	}

	public String get_onhold() {
		return m_dbAttribs[11].getValue();
	}

	public String get_comments() {
		return m_dbAttribs[12].getValue();
	}

	public String get_form1099type() {
		return m_dbAttribs[13].getValue();
	}

	public String get_form1099box() {
		return m_dbAttribs[14].getValue();
	}

	public String get_paymentpriority() {
		return m_dbAttribs[15].getValue();
	}

	public String get_hidedisplaycontact() {
		return m_dbAttribs[16].getValue();
	}

	public String get_is_active() {
		return m_dbAttribs[17].getValue();
	}

	public String get_contact_name() {
		return m_dbAttribs[18].getValue();
	}

	public String get_company_name() {
		return m_dbAttribs[19].getValue();
	}

	public String get_mrms() {
		return m_dbAttribs[20].getValue();
	}

	public String get_first_name() {
		return m_dbAttribs[21].getValue();
	}

	public String get_mi() {
		return m_dbAttribs[22].getValue();
	}

	public String get_last_name() {
		return m_dbAttribs[23].getValue();
	}

	public String get_print_as() {
		return m_dbAttribs[24].getValue();
	}

	public String get_phone1() {
		return m_dbAttribs[25].getValue();
	}

	public String get_phone2() {
		return m_dbAttribs[26].getValue();
	}

	public String get_cellphone() {
		return m_dbAttribs[27].getValue();
	}

	public String get_pager() {
		return m_dbAttribs[28].getValue();
	}

	public String get_fax() {
		return m_dbAttribs[29].getValue();
	}

	public String get_email1() {
		return m_dbAttribs[30].getValue();
	}

	public String get_email2() {
		return m_dbAttribs[31].getValue();
	}

	public String get_url1() {
		return m_dbAttribs[32].getValue();
	}

	public String get_url2() {
		return m_dbAttribs[33].getValue();
	}

	public String get_address1() {
		return m_dbAttribs[34].getValue();
	}

	public String get_address2() {
		return m_dbAttribs[35].getValue();
	}

	public String get_city() {
		return m_dbAttribs[36].getValue();
	}

	public String get_state() {
		return m_dbAttribs[37].getValue();
	}

	public String get_zip() {
		return m_dbAttribs[38].getValue();
	}

	public String get_country() {
		return m_dbAttribs[39].getValue();
	}

	public void set_vendor_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[0].setValue(null);
		else
			m_dbAttribs[0].setValue(val);
	}

	public void set_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[1].setValue(null);
		else
			m_dbAttribs[1].setValue(val);
	}

	public void set_parent_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[2].setValue(null);
		else
			m_dbAttribs[2].setValue(val);
	}

	public void set_term_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[3].setValue(null);
		else
			m_dbAttribs[3].setValue(val);
	}

	public void set_vendtype_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[4].setValue(null);
		else
			m_dbAttribs[4].setValue(val);
	}

	public void set_tax_id(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[5].setValue(null);
		else
			m_dbAttribs[5].setValue(val);
	}

	public void set_credit_limit(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[6].setValue(null);
		else
			m_dbAttribs[6].setValue(val);
	}

	public void set_vendor_acctno(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[7].setValue(null);
		else
			m_dbAttribs[7].setValue(val);
	}

	public void set_acct_label(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[8].setValue(null);
		else
			m_dbAttribs[8].setValue(val);
	}

	public void set_gl_acctno(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[9].setValue(null);
		else
			m_dbAttribs[9].setValue(val);
	}

	public void set_billing_type(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[10].setValue(null);
		else
			m_dbAttribs[10].setValue(val);
	}

	public void set_onhold(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[11].setValue(null);
		else
			m_dbAttribs[11].setValue(val);
	}

	public void set_comments(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[12].setValue(null);
		else
			m_dbAttribs[12].setValue(val);
	}

	public void set_form1099type(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[13].setValue(null);
		else
			m_dbAttribs[13].setValue(val);
	}

	public void set_form1099box(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[14].setValue(null);
		else
			m_dbAttribs[14].setValue(val);
	}

	public void set_paymentpriority(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[15].setValue(null);
		else
			m_dbAttribs[15].setValue(val);
	}

	public void set_hidedisplaycontact(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[16].setValue(null);
		else
			m_dbAttribs[16].setValue(val);
	}

	public void set_is_active(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[17].setValue(null);
		else
			m_dbAttribs[17].setValue(val);
	}

	public void set_contact_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[18].setValue(null);
		else
			m_dbAttribs[18].setValue(val);
	}

	public void set_company_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[19].setValue(null);
		else
			m_dbAttribs[19].setValue(val);
	}

	public void set_mrms(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[20].setValue(null);
		else
			m_dbAttribs[20].setValue(val);
	}

	public void set_first_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[21].setValue(null);
		else
			m_dbAttribs[21].setValue(val);
	}

	public void set_mi(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[22].setValue(null);
		else
			m_dbAttribs[22].setValue(val);
	}

	public void set_last_name(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[23].setValue(null);
		else
			m_dbAttribs[23].setValue(val);
	}

	public void set_print_as(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[24].setValue(null);
		else
			m_dbAttribs[24].setValue(val);
	}

	public void set_phone1(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[25].setValue(null);
		else
			m_dbAttribs[25].setValue(val);
	}

	public void set_phone2(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[26].setValue(null);
		else
			m_dbAttribs[26].setValue(val);
	}

	public void set_cellphone(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[27].setValue(null);
		else
			m_dbAttribs[27].setValue(val);
	}

	public void set_pager(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[28].setValue(null);
		else
			m_dbAttribs[28].setValue(val);
	}

	public void set_fax(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[29].setValue(null);
		else
			m_dbAttribs[29].setValue(val);
	}

	public void set_email1(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[30].setValue(null);
		else
			m_dbAttribs[30].setValue(val);
	}

	public void set_email2(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[31].setValue(null);
		else
			m_dbAttribs[31].setValue(val);
	}

	public void set_url1(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[32].setValue(null);
		else
			m_dbAttribs[32].setValue(val);
	}

	public void set_url2(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[33].setValue(null);
		else
			m_dbAttribs[33].setValue(val);
	}

	public void set_address1(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[34].setValue(null);
		else
			m_dbAttribs[34].setValue(val);
	}

	public void set_address2(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[35].setValue(null);
		else
			m_dbAttribs[35].setValue(val);
	}

	public void set_city(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[36].setValue(null);
		else
			m_dbAttribs[36].setValue(val);
	}

	public void set_state(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[37].setValue(null);
		else
			m_dbAttribs[37].setValue(val);
	}

	public void set_zip(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[38].setValue(null);
		else
			m_dbAttribs[38].setValue(val);
	}

	public void set_country(String val) {
		if (val == null || val.length() == 0)
			m_dbAttribs[39].setValue(null);
		else
			m_dbAttribs[39].setValue(val);
	}

	public BusinessObject getNewInstance() {
		return new vendor_viewObj();
	}

}
