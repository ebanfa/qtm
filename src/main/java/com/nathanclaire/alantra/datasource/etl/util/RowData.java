/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Banfa 
 *
 */
public class RowData {

	private TableData tableData;
	private Boolean errors = false;
    private Character inputStatusFg = 'C';
	List<CellData> columns = new ArrayList<CellData>();
	
	/**
	 * 
	 */
	public RowData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param errors
	 * @param tableData
	 * @param columns
	 */
	public RowData(TableData tableData, List<CellData> columns, Boolean errors) {
		this.errors = errors;
		this.tableData = tableData;
		this.columns = columns;
	}

	/**
	 * @return the columns
	 */
	public List<CellData> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<CellData> columns) {
		this.columns = columns;
	}

	public boolean isHeaderRow() {
		return false;
	}

	/**
	 * @return the errors
	 */
	public Boolean isErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Boolean errors) {
		this.errors = errors;
	}

	/**
	 * @return the tableData
	 */
	public TableData getTableData() {
		return tableData;
	}

	/**
	 * @param tableData the tableData to set
	 */
	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RowData [TableData=" + tableData + ", columns=" + columns.size() + "errors=" + errors + "]";
	}

	/**
	 * @return the inputStatusFg
	 */
	public Character getInputStatusFg() {
		return inputStatusFg;
	}

	/**
	 * @param inputStatusFg the inputStatusFg to set
	 */
	public void setInputStatusFg(Character inputStatusFg) {
		this.inputStatusFg = inputStatusFg;
	}

}
