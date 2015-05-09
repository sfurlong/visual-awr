
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      DAI
//Description:  Beans

package daiBeans;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;

public class daiButton extends JButton {
    BorderLayout borderLayout1 = new BorderLayout();
    private transient Vector daiActionListeners;

    public daiButton() {
        try  {
            jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setFont(new java.awt.Font("Dialog", 1, 11));
        this.setMargin(new Insets(1, 14, 1, 14));
        this.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                this_keyPressed(e);
            }
        });
        this.addActionListener(new ActionListener ()  {
          public void actionPerformed(ActionEvent e)  {
            fireDaiActionEvent(new daiActionEvent(e));
          }
        });
    }

    //This is usefull for setting a bunch of buttons the same size
    //like for the wizard buttons.
    public void setLength(int x) {
        //27 is the default height for JButton
        this.setMaximumSize(new Dimension(x, 27));
        this.setMinimumSize(new Dimension(x, 27));
        this.setPreferredSize(new Dimension(x, 27));
    }

    //This is usefull for setting a bunch of buttons the same size
    //like for the wizard buttons.
    public void setButtonSize(int x, int y) {
        this.setMaximumSize(new Dimension(x, y));
        this.setMinimumSize(new Dimension(x, y));
        this.setPreferredSize(new Dimension(x, y));
    }

    public void setEnabled(boolean b)
    {
        super.setEnabled(b);
    }

    void this_keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.doClick();
            fireDaiActionEvent(new daiActionEvent(e));
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

