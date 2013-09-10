/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractor;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractorProducer;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoader;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoaderProducer;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessorProducer;
import com.nathanclaire.alantra.datasource.etl.transformation.DataTransformer;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.service.entity.DataTransformerService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ProducerServiceImpl implements ProducerService {
	
	@Inject DataLoaderProducer dataLoaderProducer;
	@Inject DataExtractorProducer dataExtractorProducer;
	@Inject	DataProcessorProducer dataProcessorProducer;
	@Inject DataTransformerService dataTransformerService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataExtractor(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataExtractor getDataExtractor(DataInputJob inputJob) throws ApplicationException {
		return (DataExtractor) EntityUtil.returnOrThrowIfNull(
				dataExtractorProducer.getDataExtractor(inputJob.getDataInput()), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataExtractor");
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataProcessor(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataProcessor getDataProcessor(DataInputJob inputJob) throws ApplicationException {
		return (DataProcessor) EntityUtil.returnOrThrowIfNull(
				dataProcessorProducer.getDataProcessor(inputJob.getDataInput()), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataProcessor");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataLoader(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public DataLoader getDataLoader(DataInputJob inputJob) throws ApplicationException {
		return (DataLoader) EntityUtil.returnOrThrowIfNull(
				dataLoaderProducer.getDataLoader(inputJob.getDataInput()), 
				ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataLoader");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataService#getDataTransformer(java.lang.String)
	 */
	@Override
	public DataTransformer getDataTransformer(String transformerCode) throws ApplicationException {
		return (DataTransformer) EntityUtil.returnOrThrowIfNull(
				dataTransformerService.findByCode(transformerCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataTransformer");
	}

}
