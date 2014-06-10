
//Title:        Business Artifacts
//Version:
//Copyright:    Copyright (c) 1998
//Author:       Stephen P. Furlong
//Company:      Digital Artifacts Inc.
//Description:

package daiBeans;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class daiListBox extends JPanel {
    JList theList = new JList();
    DefaultListModel listModel = new DefaultListModel();
    ListCellRenderer listCellRenderer = new NameAndIconListCellRenderer();
    JScrollPane scrollPane;
    private transient Vector listSelectionListeners;
    private transient Vector daiListBoxItemSelectedListeners;
    BorderLayout borderLayout1 = new BorderLayout();

    public daiListBox() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        //setCellRenderer(listCellRenderer);
        setBorder(BorderFactory.createEtchedBorder());
        setMaximumSize(new Dimension(120, 57));
        setMinimumSize(new Dimension(120, 57));
        setPreferredSize(new Dimension(120, 57));
        this.setLayout(borderLayout1);

        theList.setModel(listModel);

        theList.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    listBox_mouseClicked(e);
                }
            });
        scrollPane = new JScrollPane(theList);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void addItem(String itemText) {
        listModel.addElement(itemText);
        listModel.contains(itemText);
    }

    public void addItemDistinct(String itemText) {
        if (!listModel.contains(itemText)) {
            listModel.addElement(itemText);
        }
    }

    public void removeAllItems() {
        listModel.clear();
    }

    public String getSelectedValue() {
        return (String)theList.getSelectedValue();
    }

    public synchronized void removedaiListBoxItemSelectedListener(daiGenericEventListener l) {
        if (daiListBoxItemSelectedListeners != null &&
            daiListBoxItemSelectedListeners.contains(l)) {
            Vector v = (Vector)daiListBoxItemSelectedListeners.clone();
            v.removeElement(l);
            daiListBoxItemSelectedListeners = v;
        }
    }

    public synchronized void adddaiListBoxItemSelectedListener(daiGenericEventListener l) {
        Vector v =
            daiListBoxItemSelectedListeners == null ? new Vector(2) : (Vector)daiListBoxItemSelectedListeners.clone();
        if (!v.contains(l)) {
            v.addElement(l);
            daiListBoxItemSelectedListeners = v;
        }
    }

    protected void fireitemDoubleClicked(daiGenericEvent e) {
        if (daiListBoxItemSelectedListeners != null) {
            Vector listeners = daiListBoxItemSelectedListeners;
            int count = listeners.size();
            for (int i = 0; i < count; i++) {
                ((daiGenericEventListener)listeners.elementAt(i)).genericEventAction(e);
            }
        }
    }

    private void listBox_mouseClicked(MouseEvent e) {
        int index = theList.locationToIndex(e.getPoint());

        //if (e.getClickCount() == 2 && index > 0) {
        daiGenericEvent newEvent;
        newEvent = new daiGenericEvent(listModel.getElementAt(index));
        fireitemDoubleClicked(newEvent);
        //}
    }
}


class NameAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private Border lineBorder =
        BorderFactory.createLineBorder(Color.red, 2), emptyBorder =
        BorderFactory.createEmptyBorder(2, 2, 2, 2);

    public NameAndIconListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        } else {
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }

        if (cellHasFocus)
            setBorder(lineBorder);
        else
            setBorder(emptyBorder);

        return this;
    }
}
