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
public class RowDataImpl implements RowData {
	
	List<CellData> rows = new ArrayList<CellData>();

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.RowData#setColumns(java.util.List)
	 */
	@Override
	public void setColumns(List<CellData> rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.RowData#getColumns()
	 */
	@Override
	public List<CellData> getColumns() {
		return rows;
	}

}
