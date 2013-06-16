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

	public static final String NO_JOB_PROVIDED = "DataInputJobRunner.NO_JOB_PROVIDED";
	public static final String NO_DATA_LOADER_FOUND = "DataInputJobRunner.NO_DATA_LOADER_FOUND";
	public static final String NO_DATA_INPUT_PROVIDED = "DataInputJobRunner.NO_DATA_INPUT_PROVIDED";
	public static final String NO_DATASOURCE_PROVIDED = "DataInputJobRunner.NO_DATASOURCE_PROVIDED";
	public static final String NO_DATA_STRUCTURE_FOUND = "DataInputJobRunner.NO_DATA_STRUCTURE_FOUND";
	public static final String NO_DATA_EXTRACTOR_FOUND = "DataInputJobRunner.NO_DATA_EXTRACTOR_FOUND";
	public static final String NO_DATA_PROCESSORS_FOUND = "DataInputJobRunner.NO_DATA_PROCESSORS_FOUND";
	public static final String NO_DATA_DEFINITION_PROVIDED = "DataInputJobRunner.NO_DATA_DEFINITION_PROVIDED";
	
	public void start(DataInputJob inputJob) throws ApplicationException;
	public void stop(DataInputJob inputJob) throws ApplicationException;

}
