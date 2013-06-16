/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataExtractor;
import com.nathanclaire.alantra.datasource.etl.DataInputLogger;
import com.nathanclaire.alantra.datasource.etl.DataLoader;
import com.nathanclaire.alantra.datasource.etl.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.etl.producers.DataExtractorProducer;
import com.nathanclaire.alantra.datasource.etl.producers.DataInputLoggerProducer;
import com.nathanclaire.alantra.datasource.etl.producers.DataLoaderProducer;
import com.nathanclaire.alantra.datasource.etl.producers.DataProcessorProducer;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataStructure;

/**
 * @author Edward Banfa 
 * 
 * To be able to load a certain type of data into the advice pro system do the following
 * 1. Subclass DataInputJobRunnerImpl.
 * 2. Optionally subclass BaseDataLoaderImpl and implement the DataExtractor interface (If dealing with a new data input job type).
 * 3. Implement the DataProcessor interface.
 * 4. Subclass BaseEntityDataProviderImpl and implement the EntityDataProvider interface.
 * 5. Configure the job using the admin user interface
 */
@Stateless
public class DataInputJobRunnerImpl implements DataInputJobRunner {
	
	@Inject DataLoaderProducer dataLoaderProducer;
	@Inject DataExtractorProducer dataExtractorProducer;
	@Inject	DataProcessorProducer dataProcessorProducer;
	@Inject	DataInputLoggerProducer inputLoggerProducer;
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobRunnerImpl.class);
	

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputJobRunner#start()
	 */
	public void start(DataInputJob inputJob) throws ApplicationException {
		try {
			// 1. Ensure we have an actual job
			validateDataInputJob(inputJob);
			DataInput dataInput = inputJob.getDataInput();
			Data dataConfig = dataInput.getData();
			DataStructure dataStructure = getDataStructure(dataConfig);
			// 3. Get the data extractor
			DataExtractor dataExtractor = dataExtractorProducer.getDataExtractor(dataInput);
			if(dataExtractor == null)
				throw new ApplicationException(NO_DATA_EXTRACTOR_FOUND);
			// 2. Extract the data
			TableData data = dataExtractor.extract(dataConfig);
			// Set the target entity names
			data.setPrimEntityName(dataStructure.getTargetPriEntityCd());
			data.setSecEntityName(dataStructure.getTargetSecEntityCd());
			// 3. Get the data processor and process the data
			DataProcessor dataProcessor = dataProcessorProducer.getDataProcessor(dataInput);
			data = dataProcessor.processData(data, dataStructure.getDataFields());
			// 4. Get the data loader
			DataLoader dataLoader = dataLoaderProducer.getDataLoader(dataInput);
			if(dataLoader == null)
				throw new ApplicationException(NO_DATA_LOADER_FOUND);
			// 4. Load the data
			dataLoader.loadData(data, dataStructure.getDataFields());
			// 5. Log the data input
			DataInputLogger dataInputLogger = inputLoggerProducer.getDataInputLogger(inputJob);
			//dataInputLogger.logDataInp/ut(inputJob, data);
		} 
		catch (ApplicationException e) 
		{
			logger.error("Job: {} not started. Error type: {} Error Message: {}", inputJob.getCode(), e.getCode() , e.getMessage());
		}
		catch (Exception e) 
		{
			logger.error("Unknown error starting job {}. Message {}", inputJob.getCode(), e.getMessage());
		}
	}

	/**
	 * @param dataConfig
	 * @return
	 * @throws ApplicationException
	 */
	private DataStructure getDataStructure(Data dataConfig) 	throws ApplicationException {
		DataStructure dataStructure = dataConfig.getDataStructure();
		if(dataStructure == null) throw new ApplicationException(NO_DATA_STRUCTURE_FOUND);
		return dataStructure;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputJobRunner#stop()
	 */
	public void stop(DataInputJob inputJob) {
	}

	/**
	 * Validate the job configuration
	 * @param inputJob
	 * @throws ApplicationException
	 */
	private void validateDataInputJob(DataInputJob inputJob) throws ApplicationException 
	{
		if(inputJob == null) 
			throw new ApplicationException(NO_JOB_PROVIDED);
		// Validate the data input configuration
		DataInput dataInput = inputJob.getDataInput();
		if(dataInput == null)
			throw new ApplicationException(NO_DATA_INPUT_PROVIDED);
		// Validate the data input definition
		Data dataDefinition = dataInput.getData();
		if(dataDefinition == null)
			throw new ApplicationException(NO_DATA_DEFINITION_PROVIDED);
		// Validate the data source
		DataChannel dataChannel = dataDefinition.getDataChannel();
		if(dataChannel == null)
			throw new ApplicationException(NO_DATASOURCE_PROVIDED);
	}
}
