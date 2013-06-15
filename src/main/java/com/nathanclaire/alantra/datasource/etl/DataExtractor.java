/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.Data;

/**
 * @author Edward Banfa 
 *
 */
public interface DataExtractor {
	
	/**
	 * @return
	 */
	public TableData extract(Data data) throws ApplicationException ;

}
