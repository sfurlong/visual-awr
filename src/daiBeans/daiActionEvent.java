
//Title:        Your Product Name
//Version:      
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      DAI
//Description:  Beans

package daiBeans;


public class daiActionEvent
{
    protected Object _source;

    public daiActionEvent(Object source)
    {
        _source = source;
    }

    public Object getSource() {
        return _source;
    }
}
 