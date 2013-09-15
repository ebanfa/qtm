/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.DataExtractedEvent;
import com.nathanclaire.alantra.datasource.annotation.DataLoadedEvent;
import com.nathanclaire.alantra.datasource.annotation.DataTransformedEvent;
import com.nathanclaire.alantra.datasource.etl.extraction.DataExtractor;
import com.nathanclaire.alantra.datasource.etl.loading.DataLoader;
import com.nathanclaire.alantra.datasource.etl.transformation.DataProcessor;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.event.DataInputEvent;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ETLServiceImpl extends BaseProcessService implements ETLService {
	
	@Inject DataService dataService;
	@Inject DataInputService dataInputService;
	@Inject ProducerService producerService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject @DataLoadedEvent Event<DataInputEvent> dataLoadedEvent;
	@Inject @DataExtractedEvent Event<DataInputEvent> dataExtractedEvent;
	@Inject @DataTransformedEvent Event<DataInputEvent> dataTransformedEvent;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.ETLService#extractData(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void extractData(DataInputJob inputJob)	throws ApplicationException {
		logger.debug("Extracting data for input job {}", inputJob);
		try {
			DataExtractor extractor = 
					producerService.getDataExtractor(inputJob);
			TableData tableData = extractor.extract(inputJob);
			tableData.setJobId(inputJob.getId());
			dataExtractedEvent.fire(new DataInputEvent(inputJob.getId(), inputJob.getCode(), tableData));
			logger.debug("Extracted {} rows of data for input job {}", tableData.getRows().size(), inputJob);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.ETLS_DATA_EXTRACTION_ERROR_CD);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.ETLService#processData(com.nathanclaire.alantra.datasource.model.DataInputJob, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	public void transformData(DataInputJob inputJob, TableData tableData)	throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {inputJob, tableData});
		logger.debug("Transforming {} rows of data for input job {}", tableData.getRows().size(), inputJob);
		try {
			DataProcessor dataProcessor = 
					producerService.getDataProcessor(inputJob);
			tableData = dataProcessor.processData(inputJob, tableData);
			dataTransformedEvent.fire(new DataInputEvent(inputJob.getId(), inputJob.getCode(), tableData));
			logger.debug("Data transformation complete");
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.ETLS_DATA_TRANSFORMATION_ERROR_CD);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.ETLService#loadData(com.nathanclaire.alantra.datasource.model.DataInputJob, com.nathanclaire.alantra.datasource.etl.TableData)
	 */
	@Override
	public void loadData(DataInputJob inputJob, TableData tableData) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {inputJob, tableData});
		logger.debug("Loading data {} for input job {}", tableData, inputJob);
		try {
			DataLoader dataLoader = 
					producerService.getDataLoader(inputJob);
			tableData = dataLoader.loadData(inputJob, tableData);
			dataLoadedEvent.fire(new DataInputEvent(inputJob.getId(), inputJob.getCode(), tableData));
			logger.debug("Loaded data");
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.ETLS_DATA_LOADING_ERROR_CD);
		}
	}

}
