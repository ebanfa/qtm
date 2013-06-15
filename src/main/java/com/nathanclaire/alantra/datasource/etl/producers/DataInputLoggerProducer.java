/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.producers;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataInputLogger;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface DataInputLoggerProducer {
	
	public DataInputLogger getDataInputLogger(DataInputJob inputJob) throws ApplicationException;
	

}
