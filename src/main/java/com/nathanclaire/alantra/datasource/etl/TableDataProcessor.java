/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa 
 *
 */
public interface TableDataProcessor {
	
	public void processData(TableData data) throws ApplicationException;

}
