
//Title:        Business Artifacts
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:  


package dai.shared.cmnSvcs;

public class daiException extends Exception
{
	public String m_msg;
	public String m_sourceFileName;
	public String m_dataVal1;
	public String m_dataVal2;
	public Object m_sourceObject;

	public daiException(String msg, Object sourceObject)
	{

		super(msg);
		m_msg = msg;

	}
}