/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseTimerService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;

/**
 * @author Edward Banfa 
 *
 */
@Startup
@Singleton
public class JobsManagerImpl extends BaseTimerService implements JobsManager 
{
	private List<DataInputJob> inputJobs;
	@Inject DataInputJobService jobsService;
	@Inject	DataInputJobRunner inputJobRunner;
	private Logger logger = LoggerFactory.getLogger(JobsManagerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#start()
	 */
	@Override
	@PostConstruct
	@Schedule(minute="*/2", hour="*")
	public void start() {
		logger.info("Starting JobManager");
		try {
			this.startAllJobs();
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
		}
		logger.info("JobManager started");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stop()
	 */
	@Override
	public void stop() {
		this.stopAllJobs();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#startAllJobs()
	 */
	@Override
	public void startAllJobs() throws ApplicationException 
	{
		inputJobs = this.getAllJobs(); 
		for(DataInputJob inputJob: inputJobs)
		{
			try {
				this.startJob(inputJob);
			} catch (ApplicationException e) {
				logger.error("Problem executing job: {}. Message: {}", inputJob.getName(), e.getMessage());
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stopAllJobs()
	 */
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

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#startJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void startJob(DataInputJob inputJob)throws ApplicationException  {
		if(inputJob.getRecSt() == 'A')
		{
			logger.info("Processing start job request for job: {}", inputJob.getName());
			inputJobRunner.start(inputJob);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stopJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void stopJob(DataInputJob inputJob) throws ApplicationException  {
		inputJobRunner.stop(inputJob);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#restartJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void restartJob(DataInputJob inputJob) throws ApplicationException {
		this.stopJob(inputJob);
		this.startJob(inputJob);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#restartAllJobs()
	 */
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

	/**
	 * @return
	 */
	private List<DataInputJob> getAllJobs() throws ApplicationException {
		return jobsService.findAll(null);
	}

}
