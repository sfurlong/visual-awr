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

package daiBeans;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JRadioButton;

public class daiRadioButton extends JRadioButton implements Serializable{
    private transient Vector daiDataModifiedListeners;

    public daiRadioButton()
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

    public daiRadioButton(String label)
    {
        try
        {
            jbInit();
            this.setText(label);
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

    //Were overriding this so that we can capture programatic changes
    //to values in this EditField.
    public void setSelected(boolean flag)
    {
        super.setSelected(flag);
        if (this.isEnabled()) {
            fireDaiDataModified(new daiDataModifiedEvent(this));
        }
    }

    public void setDisabled(boolean disable)
    {
        if (disable)
        {
            this.setBackground(Color.lightGray);
            this.setEnabled(false);
        } else {
            this.setBackground(Color.white);
            this.setEnabled(true);
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

