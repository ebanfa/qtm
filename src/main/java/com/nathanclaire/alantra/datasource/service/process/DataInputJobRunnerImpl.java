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
import com.nathanclaire.alantra.datasource.etl.DataExtractorProducer;
import com.nathanclaire.alantra.datasource.etl.DataInputLogger;
import com.nathanclaire.alantra.datasource.etl.DataInputLoggerProducer;
import com.nathanclaire.alantra.datasource.etl.DataLoader;
import com.nathanclaire.alantra.datasource.etl.DataLoaderProducer;
import com.nathanclaire.alantra.datasource.etl.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.DataProcessorProducer;
import com.nathanclaire.alantra.datasource.etl.TableDataLite;
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

	public static final String DATA_PROCESSING_ERROR = "DataInputJobRunner.DATA_PROCESSING_ERROR";
	public static final String NO_DATA_PROCESSOR_FOUND = "DataInputJobRunner.NO_DATA_PROCESSOR_FOUND";
	public static final String STARTING_DATA_INPUT_JOB_MSG = "DataInputJobRunner.STARTING_DATA_INPUT_JOB_MSG";
	public static final String DATA_INPUT_JOB_APPL_ERROR_MESSAGE = "DataInputJobRunner.DATA_INPUT_JOB_APPL_ERROR_MESSAGE";
	public static final String DATA_INPUT_JOB_SUCCESSFULLY_STARTED = "DataInputJobRunner.DATA_INPUT_JOB_SUCCESSFULLY_STARTED";
	public static final String DATA_INPUT_JOB_UNKNOWN_ERROR_MESSAGE = "DataInputJobRunner.DATA_INPUT_JOB_UNKNOWN_ERROR_MESSAGE";
	
	@Inject DataLoaderProducer dataLoaderProducer;
	@Inject DataExtractorProducer dataExtractorProducer;
	@Inject	DataProcessorProducer dataProcessorProducer;
	@Inject	DataInputLoggerProducer inputLoggerProducer;
	private Logger logger = LoggerFactory.getLogger(DataInputJobRunnerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputJobRunner#start()
	 */
	public void start(DataInputJob inputJob) throws ApplicationException {
		logger.debug(STARTING_DATA_INPUT_JOB_MSG, inputJob.getCode());
		try {
			TableDataLite data = 
					loadData(inputJob, 
							processData(inputJob, 
									extractData(inputJob)));
		logger.debug(DATA_INPUT_JOB_SUCCESSFULLY_STARTED);
		} 
		catch (ApplicationException e) 	{
			logger.error(DATA_INPUT_JOB_APPL_ERROR_MESSAGE, inputJob.getCode(), e.getCode() , e.getMessage());
		}
		catch (Exception e) {
			logger.error(DATA_INPUT_JOB_UNKNOWN_ERROR_MESSAGE, inputJob.getCode(), e.getMessage());
		}
	}

	/**
	 * @param dataInput
	 * @param dataConfig
	 * @return
	 * @throws ApplicationException
	 */
	private TableDataLite extractData(DataInputJob inputJob)	throws ApplicationException {
		TableDataLite data = null;
		logger.debug("Extracting data for input job {}", inputJob);
		try {
			validateDataInputJob(inputJob);
			DataExtractor dataExtractor = getDataExtractor(inputJob.getDataInput());
			data = dataExtractor.extract(inputJob.getDataInput().getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postDataExtract(inputJob, data);
	}
	
	/**
	 * @param dataInput
	 * @param dataStructure
	 * @param data
	 * @return
	 * @throws ApplicationException
	 */
	private TableDataLite processData(DataInputJob inputJob, TableDataLite data) throws ApplicationException {
		try {
			logger.debug("Processing {} rows of data for input job {}", data.getRows().size(), inputJob);
			DataStructure dataStructure = getDataStructure(inputJob.getDataInput().getData());
			DataProcessor dataProcessor = getDataProcessor(inputJob);
			data = dataProcessor.processData(data, dataStructure.getDataFields());
		} catch (Exception e) {
			throw new ApplicationException(DATA_PROCESSING_ERROR);
		}
		return data;
	}

	/**
	 * @param dataInput
	 * @param dataStructure
	 * @param data
	 * @throws ApplicationException
	 */
	private TableDataLite loadData(DataInputJob inputJob, TableDataLite data) throws ApplicationException {
		try {
			logger.debug("Loading {} rows of data for input job {}", data.getRows().size(), inputJob);
			DataLoader dataLoader = getDataLoader(inputJob.getDataInput());
			DataStructure dataStructure = getDataStructure(inputJob.getDataInput().getData());
			data = dataLoader.loadData(data, dataStructure.getDataFields());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	postDataLoad(inputJob, data);
	}
	
	/**
	 * @param inputJob
	 * @param dataInput
	 * @param dataStructure
	 * @param data
	 * @throws ApplicationException 
	 */
	private TableDataLite postDataExtract(DataInputJob inputJob, TableDataLite data) throws ApplicationException {
		// Set job related data
		DataStructure dataStructure = getDataStructure(inputJob.getDataInput().getData());
		data.setJobId(inputJob.getId());
		data.setDataStructureId(dataStructure.getId());
		data.setDataInputId(inputJob.getDataInput().getId());
		data.setPrimEntityName(dataStructure.getTargetPriEntityCd());
		data.setSecEntityName(dataStructure.getTargetSecEntityCd());
		return data;
	}
	
	/**
	 * @param inputJob
	 * @param data
	 * @throws ApplicationException
	 */
	private TableDataLite postDataLoad(DataInputJob inputJob, TableDataLite data) throws ApplicationException {
		DataInputLogger dataInputLogger = inputLoggerProducer.getDataInputLogger(inputJob);
		TableDataLite loggedData = dataInputLogger.logDataInput(inputJob, data);
		return loggedData;
	}

	/**
	 * @param dataInput
	 * @return
	 * @throws ApplicationException
	 */
	private DataExtractor getDataExtractor(DataInput dataInput) throws ApplicationException {
		DataExtractor dataExtractor = dataExtractorProducer.getDataExtractor(dataInput);
		if(dataExtractor == null) throw new ApplicationException(NO_DATA_EXTRACTOR_FOUND);
		return dataExtractor;
	}

	/**
	 * @param inputJob
	 * @return
	 * @throws ApplicationException
	 */
	private DataProcessor getDataProcessor(DataInputJob inputJob) throws ApplicationException {
		DataProcessor dataProcessor = dataProcessorProducer.getDataProcessor(inputJob.getDataInput());
		if(dataProcessor == null) throw new ApplicationException(NO_DATA_PROCESSOR_FOUND);
		return dataProcessor;
	}

	/**
	 * @param dataInput
	 * @return
	 * @throws ApplicationException
	 */
	private DataLoader getDataLoader(DataInput dataInput) throws ApplicationException {
		DataLoader dataLoader = dataLoaderProducer.getDataLoader(dataInput);
		if(dataLoader == null) throw new ApplicationException(NO_DATA_LOADER_FOUND);
		return dataLoader;
	}

	
	/**
	 * @param dataConfig
	 * @return
	 * @throws ApplicationException
	 */
	private DataStructure getDataStructure(Data dataConfig) throws ApplicationException {
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
		if(inputJob == null) throw new ApplicationException(NO_JOB_PROVIDED);
		// Validate the data input configuration
		DataInput dataInput = inputJob.getDataInput();
		if(dataInput == null) throw new ApplicationException(NO_DATA_INPUT_PROVIDED);
		// Validate the data input definition
		Data dataDefinition = dataInput.getData();
		if(dataDefinition == null)	throw new ApplicationException(NO_DATA_DEFINITION_PROVIDED);
		// Validate the data source
		DataChannel dataChannel = dataDefinition.getDataChannel();
		if(dataChannel == null) 	throw new ApplicationException(NO_DATASOURCE_PROVIDED);
	}
}
