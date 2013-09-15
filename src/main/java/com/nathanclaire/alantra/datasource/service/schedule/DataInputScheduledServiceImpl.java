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
	@Schedule(minute="*/1", hour="*")
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
}
