package daiBeans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class daiTextArea extends JPanel
{
    private transient Vector daiDataModifiedListeners;
    JScrollPane scrollPane;
    TextAreaBugFix   textArea = new TextAreaBugFix();
    BorderLayout borderLayout1 = new BorderLayout();

    public daiTextArea()
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

    private void jbInit() throws Exception
    {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textArea);
        this.setLayout(borderLayout1);
        this.add(scrollPane);
        textArea.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode() == KeyEvent.VK_TAB && ke.isShiftDown()) { 
                	ke.consume(); 
                    textArea.transferFocusBackward(); 
                } else if (ke.getKeyCode() == KeyEvent.VK_TAB) { 
                    ke.consume(); 
                    textArea.transferFocus(); 
                } else {
            		fireDaiDataModified(new daiDataModifiedEvent(this));
                }
            }
        });
    }

    //Were overriding this so that we can capture programatic changes
    //to values in this EditField.
    public void setText(String text)
    {
        textArea.setText(text);
        if (textArea.isEditable()) {
            fireDaiDataModified(new daiDataModifiedEvent(this));
        }
    }

    public String getText()
    {
        return textArea.getText();
    }

    public void setDisabled(boolean disable)
    {
        if (disable)
        {
            textArea.setBackground(Color.lightGray);
            textArea.setEditable(false);
        } else {
            textArea.setBackground(Color.white);
            textArea.setEditable(true);
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


    //The following code is a bug fix for JTextArea bug in JDK 1.2.2 and before.
    //The bug occurs when a mnemonic is pressed (i.e. alt-xx) the character
    //is not suppressed.  It shows up in the entry TextArea field.
    class TextAreaBugFix extends JTextArea {
/*
    	protected synchronized void processComponentKeyEvent(KeyEvent anEvent) {
            super.processComponentKeyEvent(anEvent);
            ivLastKeyEventWasAlt = anEvent.isAltDown();
            ivLastKeyEventWasCtl = anEvent.isControlDown();
        }
        protected synchronized void processInputMethodEvent(InputMethodEvent e) {
            if (ivLastKeyEventWasAlt || ivLastKeyEventWasCtl) {
                e.consume();
            }
            super.processInputMethodEvent(e);
            super.ism
        }
        
        public boolean isManagingFocus() {
        	return false;
        }
*/        
        private transient boolean ivLastKeyEventWasAlt = false;
        private transient boolean ivLastKeyEventWasCtl = false;
    }

}


