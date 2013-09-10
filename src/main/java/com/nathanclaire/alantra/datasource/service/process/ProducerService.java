/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractor;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoader;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.transformation.DataTransformer;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa
 *
 */
public interface ProducerService {
	
	public DataExtractor getDataExtractor(DataInputJob inputJob) throws ApplicationException;

	public DataProcessor getDataProcessor(DataInputJob inputJob) throws ApplicationException;

	public DataLoader getDataLoader(DataInputJob inputJob) throws ApplicationException;

	public DataTransformer getDataTransformer(String transformerCode) throws ApplicationException;

}
