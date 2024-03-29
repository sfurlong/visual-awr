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
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Vector;

import javax.swing.JLabel;

public class daiQueryLabel extends JLabel implements Serializable, MouseListener
{
    private boolean HREFstyle = false;
    private boolean fromMouseClicked = false;
    private transient Vector daiActionListeners;

    public daiQueryLabel()
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

    public daiQueryLabel(String labelText)
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
        this.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                fireDaiActionEvent(new daiActionEvent(e));
            }
        });
    }

    public void setHREFstyle(boolean newHREFstyle) {
        HREFstyle = newHREFstyle;
        this.addMouseListener(this);
        this.setForeground(Color.blue);
        //this.setFont(new Font("Dialog", 3, 11));
    }

    //This is necessary for the HREF style
    public void paint (Graphics g)
    {
        super.paint(g);

        //This does the underline for the HREF style
        if (HREFstyle) {
            Rectangle r;
            r = g.getClipBounds();
            g.drawLine(0, r.height - this.getFontMetrics(
                this.getFont()).getDescent(), this.getFontMetrics(
                this.getFont()).stringWidth(this.getText()),
                r.height - this.getFontMetrics(this.getFont()).getDescent()
                );
        }
    }

    public boolean isHREFstyle() {
        return HREFstyle;
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if (HREFstyle == true) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    public void mouseExited(MouseEvent e) {
        if (HREFstyle == true) {
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

