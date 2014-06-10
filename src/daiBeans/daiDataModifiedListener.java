
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      DAI
//Description:  Beans

package daiBeans;

import java.util.EventListener;

public interface daiDataModifiedListener extends EventListener
{
    public void daiDataModified(daiDataModifiedEvent e);
} 