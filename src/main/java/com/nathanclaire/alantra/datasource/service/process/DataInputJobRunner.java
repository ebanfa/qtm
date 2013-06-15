/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface DataInputJobRunner {
	
	public void start(DataInputJob inputJob) throws ApplicationException;
	public void stop(DataInputJob inputJob) throws ApplicationException;

}
