
//Title:        Business Artifacts
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:


package dai.shared.cmnSvcs;

public class daiRemoteServiceException extends Exception
{
	public String m_msg;
	public String m_sourceClassName;
	public String m_dataVal1;
	public String m_dataVal2;
	public Object m_sourceObject;

	public daiRemoteServiceException(   String msg,
                                        String className,
                                        String dataVal1,
                                        String dataVal2,
                                        Object sourceObject) {

    	m_msg               = msg;
	    m_sourceClassName    = className;
    	m_dataVal1          = dataVal1;
	    m_dataVal2          = dataVal2;
    	m_sourceObject      = sourceObject;
    }

	public daiRemoteServiceException(String msg, Object sourceObject)
	{

		super(msg);
		m_msg = msg;
	}
}