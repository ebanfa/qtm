/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.schedule;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
/**
 * @author Edward Banfa 
 *
 */
public interface DataInputScheduledService {
	
	/**
	 * 
	 */
	public void start() throws ApplicationException ;
	
	/**
	 * 
	 */
	public void stop() throws ApplicationException ;

}
