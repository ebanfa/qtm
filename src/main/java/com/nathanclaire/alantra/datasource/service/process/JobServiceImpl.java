/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.datasource.annotation.DataInputJobStartedEvent;
import com.nathanclaire.alantra.datasource.event.DataInputEvent;
import com.nathanclaire.alantra.datasource.model.DataInputJob;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class JobServiceImpl extends BaseProcessService implements JobService {

	@Inject ETLService etlService;
	@Inject DataInputService dataInputService;
	@Inject @DataInputJobStartedEvent Event<DataInputEvent> dataInputJobStartedEvent;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobService#startAllJobs()
	 */
	@Override
	public void startAllJobs() throws ApplicationException {
		try {
			for(DataInputJob inputJob : dataInputService.getAllRunnableJobs())
				if(dataInputService.isReadyToRun(inputJob))	this.startJob(inputJob);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.JS_DATA_INPUT_JOB_START_ERROR_CD);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobService#stopAllJobs()
	 */
	@Override
	public void stopAllJobs() throws ApplicationException {
		try {
			for(DataInputJob inputJob : dataInputService.getAllRunningJobs())
				this.stopJob(inputJob);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.JS_DATA_INPUT_JOB_STOP_ERROR_CD);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobService#startJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void startJob(DataInputJob inputJob) throws ApplicationException {
		try {
			dataInputJobStartedEvent.fire(new DataInputEvent(inputJob.getId()));
			etlService.extractData(inputJob);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.JS_DATA_INPUT_JOB_START_ERROR_CD);
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobService#stopJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void stopJob(DataInputJob inputJob) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}
}
