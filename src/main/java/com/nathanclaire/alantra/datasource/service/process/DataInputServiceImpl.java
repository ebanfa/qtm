/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobStatus;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;
import com.nathanclaire.alantra.datasource.request.DataInputJobRequest;
import com.nathanclaire.alantra.datasource.request.DataInputRequest;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobStatusService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobTypeService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputServiceImpl extends BaseProcessService implements
		DataInputService {
	@Inject DataInputJobTypeService dataInputJobTypeService;
	@Inject DataInputJobStatusService dataInputJobStatusService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject com.nathanclaire.alantra.datasource.service.entity.DataInputEntityService dataInputEntityService;
	@Inject com.nathanclaire.alantra.datasource.service.entity.DataInputJobService inputJobEntityService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#isCyclicDataInputJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public Boolean isCyclicDataInputJob(DataInputJob inputJob) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {inputJob, inputJob.getDataInputJobType()});
		String jobTypeCode = inputJob.getDataInputJobType().getCode();
		if(jobTypeCode.equals(DataInputJobTypeService.CYCLIC))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#isNonCyclicDataInputJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public Boolean isNonCyclicDataInputJob(DataInputJob inputJob) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {inputJob, inputJob.getDataInputJobType()});
		String jobTypeCode = inputJob.getDataInputJobType().getCode();
		if(jobTypeCode.equals(DataInputJobTypeService.NON_CYCLIC))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#validateDataInputJob(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public void validateDataInputJob(DataInputJob inputJob)	throws ApplicationException {
		logger.debug("Validating data input job {} ...", inputJob);
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {inputJob});
		DataInput dataInput = inputJob.getDataInput();
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {inputJob.getDataInputJobType(), dataInput});
		Data data = dataInput.getData();
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[] {data, ((data != null) ? data.getDataStructure():null)});
		logger.debug("Validated data input job {} ...", inputJob);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getJobsByStatus(java.lang.String)
	 */
	@Override
	public List<DataInputJob> getJobsByStatus(String statusCode) throws ApplicationException {
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("dataInputJobStatus.code", statusCode);
		return inputJobEntityService.findByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getDataInputJob(java.lang.String)
	 */
	@Override
	public DataInputJob getDataInputJob(String jobCode)	throws ApplicationException {
		return (DataInputJob) EntityUtil.returnOrThrowIfNull(
				inputJobEntityService.findByCode(jobCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataInputJob");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getDataInputJob(java.lang.Integer)
	 */
	@Override
	public DataInputJob getDataInputJob(Integer inputJobId)	throws ApplicationException {
		return (DataInputJob) EntityUtil.returnOrThrowIfNull(
				inputJobEntityService.findById(inputJobId), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataInputJob");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getDataInputJobType(java.lang.String)
	 */
	@Override
	public DataInputJobType getDataInputJobType(String jobTypeCode)	throws ApplicationException {
		return (DataInputJobType) EntityUtil.returnOrThrowIfNull(
				dataInputJobTypeService.findByCode(jobTypeCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataInputJobType");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getDataInputJobStatus(java.lang.String)
	 */
	@Override
	public DataInputJobStatus getDataInputJobStatus(String statusCode) throws ApplicationException {
		return (DataInputJobStatus) EntityUtil.returnOrThrowIfNull(
				dataInputJobStatusService.findByCode(statusCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataInputJobStatus");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getAllRunnableJobs()
	 */
	@Override
	public List<DataInputJob> getAllRunnableJobs() throws ApplicationException {
		logger.debug("Loading all runnable jobs ...");
		List<DataInputJob> allRunnableJobs = new ArrayList<DataInputJob>();
		try {
			allRunnableJobs.addAll(this.getJobsByStatus(DataInputJobStatusService.NOT_RUNNING));
			allRunnableJobs.addAll(this.getJobsByStatus(DataInputJobStatusService.PROCESSING_CYCLES));
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.DIS_LOAD_RUNNABLE_JOBS_ERROR_CD);
		}
		logger.debug("Loaded {} runnable jobs ...", allRunnableJobs.size());
		return allRunnableJobs;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getAllRunningJobs()
	 */
	@Override
	public List<DataInputJob> getAllRunningJobs() throws ApplicationException {
		return this.getJobsByStatus(DataInputJobStatusService.RUNNING);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#isReadyToRun(com.nathanclaire.alantra.datasource.model.DataInputJob)
	 */
	@Override
	public Boolean isReadyToRun(DataInputJob inputJob) throws ApplicationException 
	{
		try {
			if(isCyclicDataInputJob(inputJob))
				if(new DateTime(inputJob.getStartTs()).isBeforeNow())
					if(new DateTime(inputJob.getNextRunTs()).isBeforeNow())
						return true;
			else if(isNonCyclicDataInputJob(inputJob))
				if(new DateTime(inputJob.getStartTs()).isBeforeNow())
					return true;
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.DIS_RESOLVE_RUNNABLE_JOB_ERROR_CD);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#getDataInput(java.lang.String)
	 */
	@Override
	public DataInput getDataInput(String dataInputCode) throws ApplicationException {
		return (DataInput) EntityUtil.returnOrThrowIfNull(
				dataInputEntityService.findByCode(dataInputCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "DataInput");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#createDataInput(com.nathanclaire.alantra.datasource.model.Data, com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.datasource.etl.DataLoader, java.lang.String, java.lang.String)
	 */
	@Override
	public DataInput createDataInput(Data data, DataChannel channel, String name, String code)
			throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating data input: data: {}, " +
				"channel: {}, importStatusFg: {}", data, channel, name, code);
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( new Object[] {data, channel, name, code});
		
		try {
			DataInputRequest dataInputRequest = new DataInputRequest(data.getId(), channel.getId(), name, code);
			PropertyUtil.initializeBaseFields(dataInputRequest);
			return dataInputEntityService.create(dataInputRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.DataInputService#createDataInputJob(com.nathanclaire.alantra.datasource.model.DataInputJobType, com.nathanclaire.alantra.datasource.model.DataInputJobStatus, com.nathanclaire.alantra.datasource.model.DataInput, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public DataInputJob createDataInputJob(DataInputJobType dataInputJobType, DataInputJobStatus inputJobStatus, 
			DataInput dataInput, String name, String code, String diFreqCode, Integer diFreqValue) throws ApplicationException {
		// Debug and validate the provided parameters
		logger.debug("Creating data input job: dataInputJobType : {}, dataInputJobStatus: {},  " +
				"inputJobStatus: {}, dataInput: {}, name: {}, code, diFreqCode: {}, diFreqValue: {}" , 
				dataInputJobType, inputJobStatus, dataInput, name, code, diFreqCode, diFreqValue);
		
		EntityUtil.returnOrThrowIfParamsArrayContainsNull( 
				new Object[] {dataInputJobType, inputJobStatus, dataInput, name, diFreqCode, diFreqValue});
		try 
		{
			DataInputJobRequest dataInputJobRequest = 
					new DataInputJobRequest(dataInputJobType.getId(), dataInput.getId(),
							inputJobStatus.getId(), name, code, diFreqCode, diFreqValue);
			
			PropertyUtil.initializeBaseFields(dataInputJobRequest);
			return inputJobEntityService.create(dataInputJobRequest);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.BPS_ENTITY_CREATION_ERROR_CD);
		}
		return null;
	}
}
