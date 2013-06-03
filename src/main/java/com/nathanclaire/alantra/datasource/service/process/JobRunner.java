/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataLoader;
import com.nathanclaire.alantra.datasource.etl.TableDataProcessor;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa 
 *
 */
public interface JobRunner {

	public DataInputJob getJob();
	public void setJob(DataInputJob inputJob);
	
	public TableDataProcessor getProcessor();
	public DataLoader getLoader();
	
	public void start() throws ApplicationException;
	public void stop() throws ApplicationException;

}
