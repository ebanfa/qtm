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
/**
 * @author Edward Banfa 
 *
 */
public interface JobsManager {
	
	/**
	 * 
	 */
	public void start() throws ApplicationException ;
	
	/**
	 * 
	 */
	public void stop() throws ApplicationException ;
	
	/**
	 * @param inputJob
	 * @return
	 */
	public void startJob(DataInputJob inputJob) throws ApplicationException ;
	
	/**
	 * @param inputJob
	 * @return
	 */
	public void stopJob(DataInputJob inputJob) throws ApplicationException ;
	
	/**
	 * 
	 */
	public void startAllJobs() throws ApplicationException ;
	
	/**
	 * 
	 */
	public void stopAllJobs() throws ApplicationException ;
	
	/**
	 * @param inputJob
	 */
	public void restartJob(DataInputJob inputJob) throws ApplicationException ;
	
	/**
	 * 
	 */
	public void restartAllJobs() throws ApplicationException ;

}
