/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.DataLoader;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.etl.TableDataProcessor;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobCategory;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;
import com.nathanclaire.alantra.datasource.model.DataSource;
import com.nathanclaire.alantra.datasource.request.TxnDataInputJobStatsRequest;
import com.nathanclaire.alantra.datasource.service.entity.TxnDataInputJobStatsService;

/**
 * @author Edward Banfa 
 *
 */
public abstract class DataInputJobRunnerImpl {

	/**
	 * 
	 */
	private DataInputJob inputJob;
	
	/**
	 * 
	 */
	@Inject
	TxnDataInputJobStatsService statsService;
	
	/**
	 * 
	 */
	private static final String NO_JOB_PROVIDED = "DataInputJobRunnerImpl.NO_JOB_PROVIDED";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobRunnerImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobRunner#getJob()
	 */
	public DataInputJob getJob() {
		return inputJob;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobRunner#setJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	public void setJob(DataInputJob inputJob) {
		this.inputJob = inputJob;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobRunner#stop()
	 */
	public void stop() {
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobRunner#start()
	 */
	public void start() throws ApplicationException {
		// 1. Ensure we have an actual job
		if(this.inputJob == null) 
			throw new ApplicationException(NO_JOB_PROVIDED);
		// 2. Get the loader, processor and datasource
		logger.info("Starting job {}", inputJob.getCode());
		DataLoader dataLoader = getLoader();
		TableDataProcessor tableDataProcessor = getProcessor();
		DataSource dataSource = this.getJob().getDataSource();
		// 4. Tell data loader to load
		TableData data = dataLoader.loadData(dataSource);
		// 5. If loaded, give data to processor
		tableDataProcessor.processData(data);
		statsService.create(this.initialzeJobStat(this.getJob(), data));
		logger.info("Started job {} Read: {} Created: {} Matched: {} Unmatched: {}", inputJob.getCode(), 
				data.getRecordsRead(), data.getRecordsCreated(), data.getTxnMatched(), data.getTxnUnMatched());
	}

	/**
	 * @return
	 */
	public TableDataProcessor getProcessor() {
		DataInputJobCategory jobCategory = this.getJob().getDataInputJobCategory();
		TableDataProcessor tableDataProcessor = getDataProcessor(jobCategory.getName());
		return tableDataProcessor;
	}

	/**
	 * @return
	 */
	public DataLoader getLoader() {
		DataInputJobType dataInputJobType = this.getJob().getDataInputJobType();
		DataLoader dataLoader = getDataLoader(dataInputJobType.getName());
		return dataLoader;
	}

	/**
	 * @param name
	 * @return
	 */
	protected abstract TableDataProcessor getDataProcessor(String name);
	
	/**
	 * @param loaderClassName
	 * @param dataLoader
	 * @return
	 */
	private DataLoader getDataLoader(String loaderClassName) {
		DataLoader dataLoader = null;
		try {
			dataLoader = (DataLoader)Class.forName(loaderClassName).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dataLoader;
	}

	/**
	 * @param ctsJob
	 * @param data 
	 * @return
	 */
	protected TxnDataInputJobStatsRequest initialzeJobStat(DataInputJob ctsJob, TableData data) {
		TxnDataInputJobStatsRequest dataInputJobStatsRequest = new TxnDataInputJobStatsRequest();
		dataInputJobStatsRequest.setCode(ctsJob.getCode() + new Date().getTime());
		dataInputJobStatsRequest.setName(ctsJob.getName() + new Date().getTime());
		dataInputJobStatsRequest.setJobId(ctsJob.getId());
		dataInputJobStatsRequest.setCreatedDt(new Date());
		dataInputJobStatsRequest.setCreatedByUsr("CTS");
		dataInputJobStatsRequest.setEffectiveDt(new Date());
		dataInputJobStatsRequest.setRecSt(BaseEntityServiceImpl.ENTITY_STATUS_ACTIVE);
		dataInputJobStatsRequest.setRecordsCreated(data.getRecordsCreated());
		dataInputJobStatsRequest.setRecordsRead(data.getRecordsRead());
		dataInputJobStatsRequest.setTxnsCreated(data.getRecordsCreated());
		dataInputJobStatsRequest.setTxnsMatched(data.getTxnMatched());
		dataInputJobStatsRequest.setTxnsUnmatched(data.getTxnUnMatched());
		return dataInputJobStatsRequest;
	}

}
