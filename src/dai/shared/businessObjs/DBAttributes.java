
//Title:        Business Artifacts
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:


package dai.shared.businessObjs;

import java.io.Serializable;

public class DBAttributes implements Serializable
{

   //Constructor
   public DBAttributes()
   {
   }

   public DBAttributes(String name, String value, String type, boolean needsQuotes)
   {
          m_attribName  = name;
          m_attribValue = value;
          m_attribType = type;
          m_needsQuotes = needsQuotes;
   }

   public DBAttributes(String name, String value, String type, int len, boolean needsQuotes)
   {
          m_attribName  = name;
          m_attribValue = value;
          m_attribType = type;
          m_attribLength = len;
          m_needsQuotes = needsQuotes;
   }

   public DBAttributes(String name, String value, String label, int len)
   {
          m_attribName  = name;
          m_attribValue = value;
          m_attribLabel = label;
          m_attribLength = len;
   }

   public DBAttributes(String name, String label, int len)
   {
          m_attribName  = name;
          m_attribLabel = label;
          m_attribLength = len;
   }

   public DBAttributes(String name, String value)
   {
          m_attribName  = name;
          m_attribValue = value;
   }

    public DBAttributes(String name, Object value)
    {
           m_attribName  = name;
           m_attribObjValue = value;
    }

   //Sets
   public void setName(String name) {m_attribName = name;}
   public void setValue(String value) {m_attribValue = value;}
    public void setObjValue(Object val) {m_attribObjValue = val;}
   public void setType(String type) {m_attribType = type;}
   public void setAttribLength(int len) {m_attribLength = len;}
   public void setNeedsQuotes(boolean nq) {m_needsQuotes = nq;}
   public void setLabel(String label) {m_attribLabel = label;}

   //Gets
   public String getName() {return m_attribName;}
   public String getValue() {return m_attribValue;}
   public Object getObjValue() {return m_attribObjValue;}
   public String getType() {return m_attribType;}
   public int getAttribLength() {return m_attribLength;}
   public boolean getNeedsQuotes() {return m_needsQuotes;}
   public String getLabel() {return m_attribLabel;}

   //Private
   private Object m_attribObjValue = null;
   private String m_attribName = null;
   private String m_attribValue = null;
   private String m_attribType = null;
   private int m_attribLength = 0;
   private boolean m_needsQuotes = false;
   private String m_attribLabel = null; //Label for display purposes.  Not required.
}