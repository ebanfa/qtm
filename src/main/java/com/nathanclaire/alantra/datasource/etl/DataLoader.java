/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.datasource.model.DataSource;

/**
 * @author Edward Banfa 
 *
 */
public interface DataLoader {
	
	/**
	 * @return
	 */
	public TableData loadData(DataSource dataSource);

}
