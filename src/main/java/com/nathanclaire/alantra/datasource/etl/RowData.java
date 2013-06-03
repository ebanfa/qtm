/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import java.util.List;

/**
 * @author Edward Banfa 
 *
 */
public interface RowData {

	/**
	 * @param rows
	 */
	public void setColumns(List<CellData> rows);
	
	/**
	 * @return
	 */
	public List<CellData> getColumns();
}
