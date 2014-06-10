
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      DAI
//Description:  Beans

package daiBeans;

import java.util.EventListener;

public interface daiGenericEventListener extends EventListener
{
    public void genericEventAction(daiGenericEvent e);
} 