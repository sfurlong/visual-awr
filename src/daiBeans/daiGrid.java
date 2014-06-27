// Title: Your Product Name
//Version:
//Copyright: Copyright (c) 1998
//Author: Stephen P. Furlong
//Company: DAI
//Description: Beans

package daiBeans;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

//import org.altaprise.vawr.utils.plaf.ThemeUtilities;

public class daiGrid extends JPanel {
    private JTable _theGrid = null;

    private daiGridModel _gridModel = null;

    private int _activeRow = 0;
    private boolean _showRowNumbers = false;

    //Properties
    public static final int DEFAULT_ITEM_NUM_COL_WIDTH = 20;
    public static final int DEFAULT_ID_COL_WIDTH = 100;
    public static final int DEFAULT_DESC_COL_WIDTH = 175;
    public static final int DEFAULT_SUBJECT_COL_WIDTH = 200;
    public static final int DEFAULT_NOTE_COL_WIDTH = 200;
    public static final int INTEGER_COL_TYPE = 0;
    public static final int DOUBLE_COL_TYPE = 1;
    public static final int CHECKBOX_COL_TYPE = 2;
    public static final int DATE_COL_TYPE = 3;
    public static final int CHAR_COL_TYPE = 4;
    public static final int CURRENCY_COL_TYPE = 5;

    private BorderLayout borderLayout1 = new BorderLayout();

    private transient Vector listSelectionListeners;

    private daiGridController _gridController = null;

    public daiGrid() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    public daiGrid(daiGridController controller) {
        try {
            jbInit();
            _gridController = controller;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }


    private void jbInit() throws Exception {

        _gridModel = new daiGridModel();
        _theGrid = new JTable(_gridModel);
        _theGrid.setColumnSelectionAllowed(false);
        _theGrid.setCellSelectionEnabled(false);
        _theGrid.getTableHeader().setReorderingAllowed(false);

        _theGrid.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (_gridController != null &&
                        !_gridController.isDetailCommitted()) {
                        if (_activeRow != getSelectedRow()) {
                            JOptionPane.showConfirmDialog(null,
                                                          "The current record has unsaved changes.  " +
                                                          "Please save, cancel, or delete this record\n before continuing.",
                                                          "Warning",
                                                          JOptionPane.DEFAULT_OPTION,
                                                          JOptionPane.WARNING_MESSAGE,
                                                          null);
                        }
                    } else {
                        _activeRow = getSelectedRow();
                        if (_activeRow > -1) {
                            fireRowSelectionChanged(e);
                        }
                    }
                }
            });

        JScrollPane scrollPane = new JScrollPane(_theGrid);
        _theGrid.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setLayout(borderLayout1);
        this.setBackground(Color.white);
        this.add(scrollPane, BorderLayout.CENTER);
        this.setRowSelectionEnabled(true);
    }

    //Note this must be called before any columns are added to the
    //grid.

    public void showRowNumbers() {
        _showRowNumbers = true;
        for (int i = 0; i < _gridModel.getRowCount(); i++) {
            this.set(i, 0, Integer.toString(i + 1));
        }
    }

    //!!Depricated

    public void createColumns(int numCols) {
        //Default the column types to String.
        Object[] rowTemplate = new Object[numCols];
        for (int i = 0; i < numCols; i++)
            rowTemplate[i] = new String();
        _gridModel.createTableStructure(rowTemplate);
        setTableCellRenderers(rowTemplate.length);
    }

    //Note: Use the properties above for the definition of colTypes.

    public void createColumns(int[] colTypes) {

        Object[] rowTemplate = new Object[colTypes.length];

        for (int i = 0; i < colTypes.length; i++) {
            if (colTypes[i] == CHAR_COL_TYPE) {
                rowTemplate[i] = new String();
            } else if (colTypes[i] == DATE_COL_TYPE) {
                rowTemplate[i] = new String();
            } else if (colTypes[i] == CHECKBOX_COL_TYPE) {
                rowTemplate[i] = new Boolean(false);
            } else if (colTypes[i] == DOUBLE_COL_TYPE) {
                rowTemplate[i] = new Double(0.0);
            } else if (colTypes[i] == CURRENCY_COL_TYPE) {
                rowTemplate[i] = new Double(0.0);
            } else if (colTypes[i] == INTEGER_COL_TYPE) {
                rowTemplate[i] = new Integer(0);
            } else {
                System.out.println("unknown field type");
                rowTemplate[i] = new String();
            }
        }
        _gridModel.createTableStructure(rowTemplate);
        setTableCellRenderers(rowTemplate.length);
    }

    /**
     * Add an Action Handler for a key stroke by adding an action to the Grid's Input Map.
     * For example: keyStroke should be javax.swing.KeyStroke.getKeyStroke("ENTER").
     * and action could be:
     *	javax.swing.Action doEnter = new javax.swing.AbstractAction() {
     *		public void actionPerformed(ActionEvent e) {
     * 			System.out.println("ENTEREVENT: " + e);
     *		}
     *	};
     * @param keyStroke
     * @param action
     */
    public void addKeyMapAction(javax.swing.KeyStroke keyStroke,
                                javax.swing.Action action) {
        _theGrid.getInputMap().put(keyStroke, "pressed");
        _theGrid.getActionMap().put("pressed", action);
    }

    private void setTableCellRenderers(int numCols) {
        for (int i = 0; i < numCols; i++) {
            TableCellEditor tce =
                _theGrid.getDefaultEditor(_gridModel.getColumnClass(i));
            DefaultCellEditor dtce = (DefaultCellEditor)tce;
            //Uncommenting the line below will alow editing of cells with only one mouse click.
            //THere is a nasty side effect though.  While "editing", the setText operations on the
            //cell, will not be accepted.
            //dtce.setClickCountToStart(1);
            TableCellRenderer tcr =
                new daiTableCellRenderer(_theGrid.getDefaultRenderer(_gridModel.getColumnClass(i)));
            _theGrid.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    public void setHeaderNames(String[] names) {
        _gridModel.setColumnHeaders(names);
        setTableCellRenderers(names.length);
    }

    public void setColumnSize(int col, int colSize) {
        TableColumn tabCol = _theGrid.getColumnModel().getColumn(col);
        tabCol.setPreferredWidth(colSize);
    }

    public void setRowSelectionEnabled(boolean enable) {
        _theGrid.setRowSelectionAllowed(enable);
    }

    public void setTableEditable(boolean b) {
        //jTable.setEditable(b);
    }

    public void addRow() {
        _gridModel.addRow();
        //This will set the currently selected row. So that if we
        //call getSelectedRow, the new row will be selected.
        this.setRowFocus(_gridModel.getRowCount());
        _activeRow = _gridModel.getRowCount() - 1;
        if (_showRowNumbers)
            showRowNumbers();
    }

    public void deleteRow(int row) {
        _gridModel.deleteRow(row);
        if (_showRowNumbers)
            showRowNumbers();
    }

    public void set(int row, int col, Object obj) {
        _gridModel.setValueAt(obj, row, col);
    }

    public Object get(int row, int col) {
        Object ret = _gridModel.getValueAt(row, col);
        return ret;
    }

    public int getRowCount() {
        return _gridModel.getRowCount();
    }

    public void removeAllRows() {
        _gridModel.clearGrid();
        //Reset the buttons so that only the NEW is active
        if (_gridController != null) {
            _gridController.resetButtons();
        }
    }

    public void hideColumn(int col) {
        this._theGrid.removeColumn(_theGrid.getColumnModel().getColumn(col));
    }

    public void clearAllRowSelections() {
        _theGrid.getSelectionModel().clearSelection();
        //Reset the buttons so that only the NEW is active
        if (_gridController != null) {
            _gridController.resetButtons();
        }
    }

    public void setColEditable(int col, boolean isEditable) {
        this._gridModel.setColEditable(col, isEditable);
    }

    /**
     * Ensure that the cell the is being edited is committed, even if the
     * user does not navigate out of the cell.
     * @param row
     * @param col
     */
    public void stopCellEditing(int row, int col) {
        try {
            TableCellEditor tce = _theGrid.getCellEditor(row, col);
            if (tce != null)
                tce.stopCellEditing();
        } catch (Exception e) {
            //Sometimes we get a null pointer exception for no apparent reason!!
            //put this here so we can safely go on.
            System.out.println("daiGrid.stopCellEditing Error: " +
                               e.getLocalizedMessage());
        }
    }

    public int getSelectedRow() {
        int selectedRow = _theGrid.getSelectedRow();
        if (selectedRow == getRowCount()) {
            return selectedRow - 1;
        } else {
            return selectedRow;
        }
    }

    public boolean isRowSelected(int rowN) {
        return _theGrid.isRowSelected(rowN);
    }

    /**
     *
     * @param rowN row counting starts at 0.
     */
    public void setRowFocus(int rowN) {
        _theGrid.scrollRectToVisible(_theGrid.getCellRect(rowN, 0, true));
    }

    //This is usually called by the Grid Controller. Is should be called
    //When a row is selected for editing.

    public int getActiveRow() {
        return _activeRow;
    }

    public void allowRowSelection(boolean bool) {
        _theGrid.setRowSelectionAllowed(bool);
    }

    public void setTableFont(Font font) {
        _theGrid.setFont(font);
    }

    public synchronized void removeListSelectionListener(ListSelectionListener l) {
        if (listSelectionListeners != null &&
            listSelectionListeners.contains(l)) {
            Vector v = (Vector)listSelectionListeners.clone();
            v.removeElement(l);
            listSelectionListeners = v;
        }
    }

    public synchronized void addListSelectionListener(ListSelectionListener l) {
        Vector v =
            listSelectionListeners == null ? new Vector(2) : (Vector)listSelectionListeners.clone();
        if (!v.contains(l)) {
            v.addElement(l);
            listSelectionListeners = v;
        }
    }

    protected void fireRowSelectionChanged(ListSelectionEvent e) {
        if (listSelectionListeners != null) {
            Vector listeners = listSelectionListeners;
            int count = listeners.size();
            for (int i = 0; i < count; i++) {
                ((ListSelectionListener)listeners.elementAt(i)).valueChanged(e);
            }
        }
    }

    /**
     * This renderer is used to do some custom display props for things like
     * graying our disabled columns.
     * @author sfurlong
     */
    class daiTableCellRenderer extends DefaultTableCellRenderer {
        TableCellRenderer _tcr = null;

        public daiTableCellRenderer(TableCellRenderer tcr) {
            _tcr = tcr;
        }

        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row, int column) {
            Component c =
                _tcr.getTableCellRendererComponent(table, value, isSelected,
                                                   hasFocus, row, column);
            if (!_gridModel.isCellEditable(row, column)) {
                c.setBackground(Color.LIGHT_GRAY);
            } else {
                if (!table.isRowSelected(row)) {
                    c.setBackground(Color.WHITE);
                } else {
                    //c.setBackground(ThemeUtilities.getGridCellHighlightBackgroundColor());
                }
            }
            return c;
        }
    }

    public static void main(String[] args) {

        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        daiGrid grid = new daiGrid();
        //grid.setOpaque(true); //content panes must be opaque

        //grid.createColumns(14);
        int[] colTypes = new int[6];
        colTypes[0] = 0;
        colTypes[1] = 1;
        colTypes[2] = 2;
        colTypes[3] = 3;
        colTypes[4] = 4;
        colTypes[5] = 5;
        grid.createColumns(colTypes);
        grid.setHeaderNames(new String[] { "h", "OBJ", "Item Id",
                                           "Description", "Unit Price",
                                           "Qty Ordered" });
        //		"inRpr", "outRpr", "AcctId", "AcctName", "ShipDate",
        //		"RepairCost" });
        grid.removeAllRows();
        grid.addRow();
        grid.addRow();
        grid.addRow();
        //grid.set(0, 2, "123.00");
        //grid.setColEditable(2, false);
        //grid.setColEditable(3, false);
        //grid.setColEditable(5, false);
        //		grid.set(0, 1, new Double("123.00"));
        //grid.hideColumn(3);
        grid.setColumnSize(0, 50);
        //Turn off row selection
        grid.allowRowSelection(false);

        frame.setContentPane(grid);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

