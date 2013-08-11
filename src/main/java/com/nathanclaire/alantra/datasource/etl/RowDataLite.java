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
public class RowDataLite {
	
	private boolean errors;
	
	List<CellDataLite> columns = new ArrayList<CellDataLite>();

	/**
	 * @return the columns
	 */
	public List<CellDataLite> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<CellDataLite> columns) {
		this.columns = columns;
	}

	public boolean isHeaderRow() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the errors
	 */
	public boolean isErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(boolean errors) {
		this.errors = errors;
	}

}
