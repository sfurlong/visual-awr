package daiBeans;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class daiGridModel extends AbstractTableModel {
	private ColumnProperties[] _colProps = null; 
	private Object[] _rowTemplate = null;
	private Vector _rowData = null;
	private int COLCOUNT = 0;

	public daiGridModel() {
		_rowData = new Vector();
		_colProps = new ColumnProperties[0];
	}

	public void createTableStructure(Object[] colTypes) {
		_rowData.clear();
		_colProps = new ColumnProperties[colTypes.length];
		COLCOUNT = colTypes.length;
		_rowTemplate = colTypes;
		for (int i = 0; i < colTypes.length; i++) {
			_colProps[i] = new ColumnProperties();
			_colProps[i].colTemplate = colTypes[i];
			_colProps[i].isHidden = false;
			_colProps[i].isEditable = true;
		}
		super.fireTableStructureChanged();
	}

	public void hideColumn(int col, boolean b) {
		_colProps[col].isHidden = b;
		super.fireTableStructureChanged();
	}
	
	public void setColumnHeaders(String[] names) {
		for (int i = 0; i < _colProps.length; i++) {
			_colProps[i].colName = names[i];
		}
		super.fireTableStructureChanged();
	}

	public void addRow() {
		Object newRow = _rowTemplate.clone();
		_rowData.add(newRow);
		super.fireTableDataChanged();
	}

	public void deleteRow(int row) {
		_rowData.remove(row);
		super.fireTableDataChanged();
	}

	public int getColumnCount() {
		int n = 0;
        for (int i = 0; i < COLCOUNT; i++)
            if (!_colProps[i].isHidden()) n++;
        return n;
	}

	public void clearGrid() {
		_rowData.clear();
		super.fireTableDataChanged();
	}
	
	public int getRowCount() {
		return _rowData.size();
	}

	public String getColumnName(int col) {
		return _colProps[col].colName;
	}

	public Object getValueAt(int row, int col) {
		Object[] cols = (Object[]) _rowData.get(row);
		return cols[col];
	}

	public Class getColumnClass(int c) {
		return _colProps[c].colTemplate.getClass();
	}

	public boolean isCellEditable(int row, int col) {
		int viewCol = getNumber(col);
		if (_colProps[viewCol].isEditable()) {
			return true;
		} else {
			return false;
		}
	}

	public void setColEditable(int col, boolean b) {
		_colProps[col].setEditable(b);
	}
	
	public void setValueAt(Object value, int row, int col) {
		//Try to coerce the type if necessary.
		if (value != null && value.getClass() != _colProps[col].colTemplate.getClass()) {
			if (_colProps[col].colTemplate instanceof Double) {
				value = new Double(value.toString());
			}
		}
		Object[] cols = (Object[]) _rowData.get(row);
		cols[col] = value;
		fireTableCellUpdated(row, col);
	}

	/** 
     * This functiun converts a column number in the table
     * to the right number of the datas.
     */
    private int getNumber (int col) {
        int n = col;    // right number to return
        int i = 0;
        do {
            if (_colProps[i].isHidden()) n++;
            i++;
        } while (i < n);
        // If we are on an invisible column, 
        // we have to go one step further
        while (n < _colProps.length && _colProps[n].isHidden()) {
        	n++;
        }
        return n;
    }

	
	class ColumnProperties {
		/**
		 * @return Returns the colName.
		 */
		public String getColName() {
			return colName;
		}
		/**
		 * @param colName The colName to set.
		 */
		public void setColName(String colName) {
			this.colName = colName;
		}
		/**
		 * @return Returns the colTemplate.
		 */
		public Object getColTemplate() {
			return colTemplate;
		}
		/**
		 * @param colTemplate The colTemplate to set.
		 */
		public void setColTemplate(Object colTemplate) {
			this.colTemplate = colTemplate;
		}
		/**
		 * @return Returns the isEditable.
		 */
		public boolean isEditable() {
			return isEditable;
		}
		/**
		 * @param isEditable The isEditable to set.
		 */
		public void setEditable(boolean isEditable) {
			this.isEditable = isEditable;
		}
		/**
		 * @return Returns the isHidden.
		 */
		public boolean isHidden() {
			return isHidden;
		}
		/**
		 * @param isHidden The isHidden to set.
		 */
		public void setHidden(boolean isHidden) {
			this.isHidden = isHidden;
		}
		private String colName = null;
		private boolean isHidden = false;
		private boolean isEditable = true;
		private Object colTemplate = null;
	}
}
	