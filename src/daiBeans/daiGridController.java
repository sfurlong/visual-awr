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

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class daiGridController extends JPanel
{
	daiButton daiButton_new = new daiButton();
	daiButton daiButton_edit = new daiButton();
	daiButton daiButton_enter = new daiButton();
	daiButton daiButton_cancel = new daiButton();
	daiButton daiButton_delete = new daiButton();

    JPanel spacer = new JPanel();

    //The GRID Instance.
	daiBeans.daiGrid GRID = new daiBeans.daiGrid(this);

    private transient Vector actionListeners;
    private boolean _isDetailCommitted = true;
    FlowLayout flowLayout1 = new FlowLayout();

	public daiGridController() {
		try
		{
			jbInit();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
        daiButton_new.setButtonSize(57, 22);
        daiButton_edit.setButtonSize(57, 22);
        daiButton_enter.setButtonSize(57, 22);
        daiButton_cancel.setButtonSize(57, 22);
        daiButton_delete.setButtonSize(57, 22);

        daiButton_new.setMargin(new Insets(1, 3, 1, 3));
        daiButton_new.setText("New");
        daiButton_new.setMnemonic(KeyEvent.VK_N);
        daiButton_new.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                daiButton_new_actionPerformed(e);
            }
        });
        daiButton_edit.setMargin(new Insets(1, 3, 1, 3));
        daiButton_edit.setText("Edit");
        daiButton_edit.setMnemonic(KeyEvent.VK_I);
        daiButton_edit.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                daiButton_edit_actionPerformed(e);
            }
        });
        daiButton_enter.setMargin(new Insets(1, 3, 1, 3));
        daiButton_enter.setText("Enter");
        daiButton_enter.setMnemonic(KeyEvent.VK_E);
        daiButton_enter.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                daiButton_enter_actionPerformed();
            }
        });
        daiButton_cancel.setMargin(new Insets(1, 3, 1, 3));
        daiButton_cancel.setText("Cancel");
        daiButton_cancel.setMnemonic(KeyEvent.VK_C);
        daiButton_cancel.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                daiButton_cancel_actionPerformed(e);
            }
        });
        daiButton_delete.setMargin(new Insets(1, 3, 1, 3));
        daiButton_delete.setText("Delete");
        daiButton_delete.setMnemonic(KeyEvent.VK_T);
        daiButton_delete.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                daiButton_delete_actionPerformed(e);
            }
        });
        //Add a listener to this grid.
        GRID.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                GRID_rowSelected(e);
            }
        });
		this.setLayout(flowLayout1);
        this.setOpaque(false);
        this.add(daiButton_new, null);
		this.add(daiButton_edit, null);
        this.add(new Spacer());
		this.add(daiButton_enter, null);
		this.add(daiButton_cancel, null);
        this.add(new Spacer());
		this.add(daiButton_delete, null);

		//Disable all but the New button.
		daiButton_edit.setEnabled(false);
		daiButton_enter.setEnabled(false);
		daiButton_cancel.setEnabled(false);
		daiButton_delete.setEnabled(false);
		daiButton_new.requestDefaultFocus();
	}

    public boolean isDetailCommitted() {
        return _isDetailCommitted;
    }

    public void setDetailCommitted(boolean flag) {
        _isDetailCommitted = flag;
    }

	public daiGrid getGridInstance()
	{
        return GRID;
	}

    public synchronized void removeActionListener(ActionListener l)
    {
        if(actionListeners != null && actionListeners.contains(l))
        {
            Vector v = (Vector) actionListeners.clone();
            v.removeElement(l);
            actionListeners = v;
        }
    }

    public synchronized void addActionListener(ActionListener l)
    {
        Vector v = actionListeners == null ? new Vector(2) : (Vector) actionListeners.clone();
        if(!v.contains(l))
        {
            v.addElement(l);
            actionListeners = v;
        }
    }

    protected void fireActionPerformed(ActionEvent e)
    {
        if(actionListeners != null)
        {
            Vector listeners = actionListeners;
            int count = listeners.size();
            for (int i = 0; i < count; i++)
            {
                ((ActionListener) listeners.elementAt(i)).actionPerformed(e);
            }
        }
    }

    private void daiButton_edit_actionPerformed(ActionEvent e) {
        //Don't want to do anything if it's disabled.
        if (!daiButton_edit.isEnabled()) return ;
		daiButton_enter.setEnabled(true);
		daiButton_cancel.setEnabled(true);
		daiButton_delete.setEnabled(false);
		daiButton_edit.setEnabled(false);
		daiButton_new.setEnabled(false);

        _isDetailCommitted = false;

        fireActionPerformed(new ActionEvent(this, 1, "EDIT"));
	}

    private void daiButton_new_actionPerformed(ActionEvent e){
        //Don't want to do anything if it's disabled.
        if (!daiButton_new.isEnabled()) return ;

		GRID.addRow();

        _isDetailCommitted = false;

		daiButton_new.setEnabled(false);
		daiButton_edit.setEnabled(false);
		daiButton_delete.setEnabled(false);
		daiButton_enter.setEnabled(true);
		daiButton_cancel.setEnabled(true);

        fireActionPerformed(new ActionEvent(this, 1, "NEW"));
	}

    public void daiButton_enter_actionPerformed() {
        //Don't want to do anything if it's disabled.
        if (!daiButton_enter.isEnabled()) return ;

		daiButton_new.setEnabled(true);
		daiButton_edit.setEnabled(false);
		daiButton_enter.setEnabled(false);
		daiButton_cancel.setEnabled(false);
		daiButton_delete.setEnabled(false);

        _isDetailCommitted = true;

        fireActionPerformed(new ActionEvent(this, 2, "ENTER"));
	}

    private void daiButton_cancel_actionPerformed(ActionEvent e) {
        //Don't want to do anything if it's disabled.
        if (!daiButton_cancel.isEnabled()) return ;

		daiButton_new.setEnabled(true);
		daiButton_edit.setEnabled(false);
		daiButton_enter.setEnabled(false);
		daiButton_cancel.setEnabled(false);
		daiButton_delete.setEnabled(false);

        _isDetailCommitted = true;

        fireActionPerformed(new ActionEvent(this, 3, "CANCEL"));
	}

    private void daiButton_delete_actionPerformed(ActionEvent e) {
        //Don't want to do anything if it's disabled.
        if (!daiButton_delete.isEnabled()) return ;

		daiButton_new.setEnabled(true);
		daiButton_edit.setEnabled(false);
		daiButton_enter.setEnabled(false);
		daiButton_cancel.setEnabled(false);
		daiButton_delete.setEnabled(false);

        _isDetailCommitted = true;

        fireActionPerformed(new ActionEvent(this, 4, "DELETE"));
	}

    //One of the rows in the grid was clicked.  Enable the
    //EDIT, NEW, and DELETE buttons.
    private void GRID_rowSelected(ListSelectionEvent e)
    {
        //Don't allow the buttons to change for new slections, if
        //we have other rows waiting to be commited.
        if (_isDetailCommitted) {
    		//Disable all but the New button.
	    	daiButton_edit.setEnabled(true);
    		daiButton_new.setEnabled(true);
		    daiButton_delete.setEnabled(true);
	    	daiButton_enter.setEnabled(false);
		    daiButton_cancel.setEnabled(false);
        }
    }

    //Reset the buttons so that all but the NEW button are activated.
    public void resetButtons()
    {
        //Disable all but the New button.
        daiButton_edit.setEnabled(false);
        daiButton_new.setEnabled(true);
        daiButton_delete.setEnabled(false);
        daiButton_enter.setEnabled(false);
        daiButton_cancel.setEnabled(false);
    }

    class Spacer extends JPanel {
        public Spacer() {
            this.setOpaque(false);
            this.setMaximumSize(new Dimension(5, 5));
            this.setMinimumSize(new Dimension(5, 5));
            this.setPreferredSize(new Dimension(5, 5));
        }
    }
}

