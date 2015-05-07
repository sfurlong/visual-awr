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
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JLabel;

public class daiLabel extends JLabel implements Serializable, MouseListener
{
    private boolean HREFstyle = false;
    private transient Vector daiActionListeners;
    private boolean _isDisabled = false;

    public daiLabel()
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

    public daiLabel(String labelText)
    {
        try
        {
            jbInit();
            this.setText(labelText);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception
    {
        this.setFont(new java.awt.Font("Dialog", 0, 11));
        this.setHorizontalAlignment(RIGHT);
    }

    public void setHREFstyle(boolean newHREFstyle) {
        HREFstyle = newHREFstyle;
        this.addMouseListener(this);
        this.setForeground(Color.blue);
    }

    public void paintComponent (Graphics g) {
      super.paintComponent(g);
        //This does the underline for the HREF style
        if (HREFstyle) {
          g.drawLine(0, this.getHeight() - this.getFontMetrics(
            this.getFont()).getDescent(), this.getFontMetrics(
            this.getFont()).stringWidth(this.getText()),
            this.getHeight() - this.getFontMetrics(this.getFont()).getDescent()
            );

        }
    }
    public boolean isHREFstyle() {
        return HREFstyle;
    }

    public void setDisabled(boolean flag) {
        //Only applies to HREF type labels.
        if (HREFstyle) {
            _isDisabled = flag;
            if (flag) {
                this.setForeground(Color.darkGray);
            } else {
                this.setForeground(Color.blue);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        if (HREFstyle && !_isDisabled) {
            fireDaiActionEvent(new daiActionEvent(e));
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (HREFstyle == true && !_isDisabled) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (HREFstyle == true && !_isDisabled) {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public synchronized void removedaiActionListener(daiActionListener l)
    {
        if(daiActionListeners != null && daiActionListeners.contains(l))
        {
            Vector v = (Vector) daiActionListeners.clone();
            v.removeElement(l);
            daiActionListeners = v;
        }
    }

    public synchronized void adddaiActionListener(daiActionListener l)
    {
        Vector v = daiActionListeners == null ? new Vector(2) : (Vector) daiActionListeners.clone();
        if(!v.contains(l))
        {
            v.addElement(l);
            daiActionListeners = v;
        }
    }

    protected void fireDaiActionEvent(daiActionEvent e)
    {
        if(daiActionListeners != null)
        {
            Vector listeners = daiActionListeners;
            int count = listeners.size();
            for (int i = 0; i < count; i++)
            {
                ((daiActionListener) listeners.elementAt(i)).daiActionEvent(e);
            }
        }
    }
}

