/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Banfa 
 *
 */
public class RowData {
	
	List<CellData> columns = new ArrayList<CellData>();

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

}
