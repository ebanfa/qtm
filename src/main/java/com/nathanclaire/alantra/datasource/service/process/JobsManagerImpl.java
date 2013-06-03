/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.service.process.TransactionProcessorService;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobCategoryService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;

/**
 * @author Edward Banfa 
 *
 */
@Startup
@Singleton
public class JobsManagerImpl extends BaseProcessService implements JobsManager 
{
	@Inject 
	DataInputJobService jobsService;
	
	private Map<String, JobRunner> jobRunners;
	private List<DataInputJob> inputJobs;
	
	@PersistenceContext(unitName = "primary")
    protected EntityManager entityManager;
	
	@Resource(name = "java:jboss/datasources/alantraDS")
	javax.sql.DataSource ds; 
	
	@Inject
	TransactionProcessorService transactionProcessor;
	
	@Inject
	TransactionDataInputJobRunner transactionDataInputJobRunner;
	
	private Logger logger = LoggerFactory.getLogger(JobsManagerImpl.class);
	
	private static final String JOB_RUNNER_NOT_FOUND = "JobsManagerImpl.JOB_RUNNER_NOT_FOUND";

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
			e.printStackTrace();
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
		this.inputJobs = this.getAllJobs(); 
		for(DataInputJob inputJob: this.inputJobs)
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
	public JobRunner startJob(DataInputJob inputJob)throws ApplicationException  {
		logger.info("Processing start job request for job: {}", inputJob.getName());
		JobRunner jobRunner = null;
		jobRunner = getJobRunner(inputJob.getDataInputJobCategory().getCode());
		jobRunner.setJob(inputJob);
		jobRunner.start();
		return jobRunner;
	}

	/**
	 * @param categoryCode
	 * @return
	 */
	private JobRunner getJobRunner(String categoryCode) throws ApplicationException {
		JobRunner jobRunner = null;
		logger.info("Loading job runner: {}", categoryCode);
		if(categoryCode.equals(DataInputJobCategoryService.TRANSACTION_DATA))
			jobRunner = transactionDataInputJobRunner;
		if(categoryCode.equals(DataInputJobCategoryService.CUSTOMER_DATA))
			jobRunner = transactionDataInputJobRunner;
		if (jobRunner == null)
			throw new ApplicationException(JOB_RUNNER_NOT_FOUND);
		return jobRunner;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobsManager#stopJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public JobRunner stopJob(DataInputJob inputJob) throws ApplicationException  {
		JobRunner jobRunner = null;
		if(jobRunners.containsKey(inputJob.getCode()))
			jobRunner = jobRunners.get(inputJob.getCode());
		    jobRunner.stop();
		return jobRunner;
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
