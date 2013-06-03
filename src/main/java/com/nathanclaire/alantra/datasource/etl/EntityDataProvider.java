/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;


/**
 * @author Edward Banfa 
 *
 */
public interface EntityDataProvider {
	
	/**
	 * 
	 */
	public void loadEntityData(TableData ableData)throws ApplicationException;

}
