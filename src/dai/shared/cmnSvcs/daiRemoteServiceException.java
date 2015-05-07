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