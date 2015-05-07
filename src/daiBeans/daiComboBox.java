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
//Author:       Stephen P. Furlong
//Company:      DAI
//Description:  Beans

package daiBeans;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class daiComboBox extends JComboBox implements Serializable, Cloneable
{

    Vector dataVect;
    DefaultComboBoxModel model;
    private transient Vector daiDataModifiedListeners;
    private boolean _isDisabled = false;

    public daiComboBox() {
        try
        {
            dataVect = new Vector();
            model = new DefaultComboBoxModel(dataVect);
            jbInit();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public daiComboBox(Vector vect) {
        try
        {
            dataVect = new Vector(vect);
            model = new DefaultComboBoxModel(dataVect);
            jbInit();
            this.setText(null);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //Copy constructor
    public daiComboBox(daiComboBox oldComboBox) {
        try
        {
            this.model = oldComboBox.model;
            this.dataVect = oldComboBox.dataVect;
            jbInit();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setModel(model);
        this.addActionListener(new java.awt.event.ActionListener()
                               {
                                   public void actionPerformed(ActionEvent e)
                                   {
                                       if (!_isDisabled)
                                       {
                                           fireDaiDataModified(new daiDataModifiedEvent(e));
                                       }
                                   }
                               });
        this.setFont(new Font("Dialog", 0, 11));
        this.setMaximumSize(new Dimension(32767, 21));
        this.setMinimumSize(new Dimension(0, 21));
        this.setPreferredSize(new Dimension(35, 21));
        this.setEditable(true);
    }

    public void addItems(Object[] objs)
    {
        for (int i=0; i<objs.length; i++)
        {
            dataVect.addElement(objs[i]);
        }
    }

    public void addItems(Vector vect)
    {
        dataVect = new Vector(vect);
    }

    public void addItem(Object obj)
    {
        dataVect.addElement(obj);
    }

    public void setSelectedItem(Object anObject)
    {
        super.setSelectedItem(anObject);
        if (!_isDisabled)
        {
            fireDaiDataModified(new daiDataModifiedEvent(this));
        }
    }

    public void setText(String t) {
        this.setSelectedItem(t);
        if (!_isDisabled)
        {
            fireDaiDataModified(new daiDataModifiedEvent(this));
        }
    }

    public String getText() {
        return(String)this.getSelectedItem();
    }

    public void setDisabled(boolean disable)
    {
        if (disable)
        {
            this.setBackground(Color.lightGray);
            this.setEnabled(false);
            _isDisabled = true;
        } else
        {
            this.setBackground(Color.white);
            this.setEnabled(true);
            _isDisabled = false;
        }
    }

    public boolean isDisabled() {
        return _isDisabled;
    }

    public synchronized void removedaiDataModifiedListener(daiDataModifiedListener l)
    {
        if (daiDataModifiedListeners != null && daiDataModifiedListeners.contains(l))
        {
            Vector v = (Vector) daiDataModifiedListeners.clone();
            v.removeElement(l);
            daiDataModifiedListeners = v;
        }
    }

    public synchronized void adddaiDataModifiedListener(daiDataModifiedListener l)
    {
        Vector v = daiDataModifiedListeners == null ? new Vector(2) : (Vector) daiDataModifiedListeners.clone();
        if (!v.contains(l))
        {
            v.addElement(l);
            daiDataModifiedListeners = v;
        }
    }

    protected void fireDaiDataModified(daiDataModifiedEvent e)
    {
        if (daiDataModifiedListeners != null)
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

