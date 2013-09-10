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
public interface JobService {

	public void startAllJobs() throws ApplicationException;

	public void stopAllJobs() throws ApplicationException;

	public void startJob(DataInputJob inputJob) throws ApplicationException;

	public void stopJob(DataInputJob inputJob) throws ApplicationException;
}
