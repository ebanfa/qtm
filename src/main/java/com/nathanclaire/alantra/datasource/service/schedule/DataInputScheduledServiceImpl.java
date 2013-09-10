/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.schedule;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseTimerService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.service.process.JobService;

/**
 * @author Edward Banfa 
 *
 */
@Startup
@Singleton
public class DataInputScheduledServiceImpl extends BaseTimerService implements DataInputScheduledService 
{
	@Inject JobService jobService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#start()
	 */
	@Override
	@PostConstruct
	@Schedule(minute="*/2", hour="*")
	public void start() {
		try {
			DateTime startTime = new DateTime();
			logger.info("Starting Data Input Scheduled Service at: {}", startTime);
			jobService.startAllJobs();
			logger.info("Data Input Scheduled Service run completed successfully in {}", new DateTime().minus(startTime.getMillis()));
		} catch (Exception e) {
			ExceptionUtil.logException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stop()
	 */
	@Override
	public void stop() {
		try {
			DateTime startTime = new DateTime();
			logger.info("Stopping Data Input Scheduled Service jobs at: ", startTime);
			jobService.stopAllJobs();
			logger.info("Data Input Scheduled Service run stopped successfully in {}", new DateTime().minus(startTime.getMillis()));
		} catch (ApplicationException e) {
			ExceptionUtil.logException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#startAllJobs()
	 
	@Override
	public void startAllJobs() throws ApplicationException 
	{
		inputJobs = this.getAllJobs(); 
		//runDummyJob();
		for(DataInputJob inputJob: inputJobs)
		{
			try {
				this.startJob(inputJob);
			} catch (ApplicationException e) {
				logger.error("Problem executing job: {}. Message: {}", inputJob.getName(), e.getMessage());
			}
		}
	}
	
	private void runDummyJob() throws ApplicationException
	{

		DataStructure dataStructure = dataStructureService.findByCode("CTS_TXN_DATA_STRUCTURE");
		DataType dataType = dataTypeService.findByCode(DataTypeService.TRANSACTION_DATA);
		DataChannel dataChannel = channelService.findByCode(DataChannelService.TRANSACTION_DATA_IMPORT_SERVICE);
		helper.createNonCyclicLocalFileDataInputJob("/home/administrator/Projects/alantraadvice_data.csv", 
				"CSV", dataStructure, dataType, DataChannelTypeService.FILE_CSV_CHANNEL);
	}

	 (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stopAllJobs()
	 
	@Override
	public void stopAllJobs() {
		for(DataInputJob inputJob: this.inputJobs)
		{
			try {
				this.stopJob(inputJob);
			} catch (ApplicationException e) {
				logger.error("Problem executing job: {}. Message: {}", inputJob.getName(), e.getMessage());
			}
		}
	}

	 (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#startJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 
	@Override
	public void startJob(DataInputJob inputJob)throws ApplicationException  {
		if(inputJob.getRecSt() == 'A')
		{
			logger.info("Processing start job request for job: {}", inputJob.getName());
			inputJobRunner.start(inputJob);
		}
	}

	 (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stopJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 
	@Override
	public void stopJob(DataInputJob inputJob) throws ApplicationException  {
		inputJobRunner.stop(inputJob);
	}

	 (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#restartJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 
	@Override
	public void restartJob(DataInputJob inputJob) throws ApplicationException {
		this.stopJob(inputJob);
		this.startJob(inputJob);
	}

	 (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#restartAllJobs()
	 
	@Override
	public void restartAllJobs() {
		for(DataInputJob inputJob: inputJobs)
		{
			try {
				this.restartJob(inputJob);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
		}
	}

	*//**
	 * @return
	 *//*
	private List<DataInputJob> getAllJobs() throws ApplicationException {
		return jobsService.findAll(null);
	}*/

}
