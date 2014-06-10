
//Title:        Business Artifacts
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:  

package daiBeans;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JCheckBox;

public class daiCheckBox extends JCheckBox implements Serializable{
    private transient Vector daiDataModifiedListeners;

    public daiCheckBox()
    {
        try
        {
            jbInit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public daiCheckBox(String label)
    {
        try
        {
            this.setText(label);
            jbInit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception
    {
        this.setOpaque(false);
        this.setFont(new java.awt.Font("Dialog", 0, 11));

        this.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fireDaiDataModified(new daiDataModifiedEvent(this));
            }
        });
    }

    public void setValue(String text)
    {
        if (text != null && text.equals("Y")) {
            this.setSelected(true);
        } else {
            this.setSelected(false);
        }
        if (this.isEnabled()) {
            fireDaiDataModified(new daiDataModifiedEvent(this));
        }
    }

    public String getValue()
    {
        if (this.isSelected()) {
            return "Y";
        } else {
            return "N";
        }
    }

    public void setDisabled(boolean bool)
    {
        this.setEnabled(!bool);
        if (bool)
        {
            this.setBackground(Color.lightGray);
        } else {
            this.setBackground(Color.white);
        }
    }

    public synchronized void removedaiDataModifiedListener(daiDataModifiedListener l)
    {
        if(daiDataModifiedListeners != null && daiDataModifiedListeners.contains(l))
        {
            Vector v = (Vector) daiDataModifiedListeners.clone();
            v.removeElement(l);
            daiDataModifiedListeners = v;
        }
    }

    public synchronized void adddaiDataModifiedListener(daiDataModifiedListener l)
    {
        Vector v = daiDataModifiedListeners == null ? new Vector(2) : (Vector) daiDataModifiedListeners.clone();
        if(!v.contains(l))
        {
            v.addElement(l);
            daiDataModifiedListeners = v;
        }
    }

    protected void fireDaiDataModified(daiDataModifiedEvent e)
    {
        if(daiDataModifiedListeners != null)
        {
            Vector listeners = daiDataModifiedListeners;
            int count = listeners.size();
            for (int i = 0; i < count; i++)
            {
                ((daiDataModifiedListener) listeners.elementAt(i)).daiDataModified(e);
            }
        }
    }
}

