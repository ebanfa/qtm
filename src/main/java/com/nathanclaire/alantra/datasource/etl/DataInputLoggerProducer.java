/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface DataInputLoggerProducer {
	
	public DataInputLogger getDataInputLogger(DataInputJob inputJob) throws ApplicationException;
	

}
