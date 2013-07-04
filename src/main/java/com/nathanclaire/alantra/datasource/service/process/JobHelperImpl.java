/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;


import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelStatus;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobStatus;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;
import com.nathanclaire.alantra.datasource.model.DataLoader;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.model.DataType;
import com.nathanclaire.alantra.datasource.request.DataChannelRequest;
import com.nathanclaire.alantra.datasource.request.DataInputJobRequest;
import com.nathanclaire.alantra.datasource.request.DataInputRequest;
import com.nathanclaire.alantra.datasource.request.DataRequest;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelStatusService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobStatusService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputService;
import com.nathanclaire.alantra.datasource.service.entity.DataLoaderService;
import com.nathanclaire.alantra.datasource.service.entity.DataService;
import com.nathanclaire.alantra.datasource.service.entity.DataTypeService;
import com.nathanclaire.alantra.messaging.service.entity.MessageAttachmentService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class JobHelperImpl extends BaseProcessService implements JobHelper {

	@Inject DataService dataService;
	@Inject DataInputService dataInputService;
	@Inject DataLoaderService dataLoaderService;
	@Inject DataChannelService dataChannelService;
	@Inject DataInputJobService dataInputJobService;
	@Inject DataChannelTypeService dataChannelTypeService;
	@Inject DataInputJobTypeService dataInputJobTypeService;
	@Inject DataChannelStatusService dataChannelStatusService;
	@Inject DataInputJobStatusService dataInputJobStatusService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.process.JobHelper#createNonCyclicLocalFileDataInputJob(java.lang.String, com.nathanclaire.alantra.datasource.model.DataStructure, java.lang.String, java.lang.String)
	 */
	@Override
	public DataInputJob createNonCyclicLocalFileDataInputJob(String fileName, String fileMimeType, 
			DataStructure dataStructure, DataType dataType, String channel) throws ApplicationException {
		// 1. Get the data channel
		DataChannelType dataChannelType = this.getDataChannelType(fileName, fileMimeType);
		if(dataChannelType == null)
			throw new ApplicationException(COULD_NOT_FIND_DATA_CHANNEL_TYPE);
		DataChannelStatus dataChannelStatus = this.getDataChannelStatus();
		if(dataChannelStatus == null)
			throw new ApplicationException(COULD_NOT_FIND_DATA_CHANNEL_STATUS);
		DataChannel dataChannel = this.createDataChannel(dataChannelType, dataChannelStatus, fileName);
		if(dataChannel == null)
			throw new ApplicationException(COULD_NOT_CREATE_DATA_CHANNEL);
		// 2. Create the data request
		Data data = createDataRequest(dataChannel, dataType, dataStructure);
		// 3. Create the data input
		DataInput dataInput = createDataInput(data);
		// 4. For create the dataInputJob
		DataInputJob inputJob = this.createDataInputJob(dataInput);
		// Return the Job to caller
		return inputJob;
	}
	
	/**
	 * @param fileName
	 * @param fileMimeType
	 * @return
	 * @throws ApplicationException
	 */
	private DataChannelType getDataChannelType(String fileName, String fileMimeType) throws ApplicationException
	{
		DataChannelType dataChannelType = null;
		if(fileMimeType.equals(MessageAttachmentService.CSV_MIME_TYPE))
			dataChannelType = dataChannelTypeService.findByCode(DataChannelTypeService.FILE_CSV_CHANNEL);
		if(fileMimeType.equals(MessageAttachmentService.EXCEL_MIME_TYPE))
			dataChannelType = dataChannelTypeService.findByCode(DataChannelTypeService.FILE_EXCEL_CHANNEL);
		return dataChannelType;
	}
	
	private DataChannelStatus getDataChannelStatus() throws ApplicationException
	{
		return dataChannelStatusService.findByCode(DataChannelStatusService.ACTIVE);
	}
	
	private DataChannel createDataChannel(DataChannelType channelType, 
			DataChannelStatus channelStatus, String fileName) throws ApplicationException
	{
		DataChannelRequest channelRequest = new DataChannelRequest();
		PropertyUtils.initializeBaseFields(channelRequest);
		channelRequest.setDataChannelStatusId(channelStatus.getId());
		channelRequest.setDataChannelTypeId(channelType.getId());
		channelRequest.setCode("AUTO_CHANNEL" + Long.valueOf(new Date().getTime() * 3));
		channelRequest.setName(channelType.getName() + ":" + Long.valueOf(new Date().getTime() * 3));
		channelRequest.setUrl(fileName);
		return dataChannelService.create(channelRequest);
	}
	
	/**
	 * @param data
	 * @return
	 * @throws ApplicationException
	 */
	private DataInput createDataInput(Data data) throws ApplicationException 
	{
		DataInputRequest dataInputRequest = new DataInputRequest();
		PropertyUtils.initializeBaseFields(dataInputRequest);
		dataInputRequest.setCode(Long.valueOf(new Date().getTime()).toString());
		DataType dataType = data.getDataType();
		DataLoader dataLoader = null;
		if(dataType.getCode().equals(DataTypeService.TRANSACTION_DATA))
			dataLoader = dataLoaderService.findByCode(DataLoaderService.TRANSACTION_DATA_LOADER);
		if(dataLoader == null) 
			throw new ApplicationException(CONFIG_ERROR_DATA_LOADER_NOT_FOUND);
		dataInputRequest.setDataLoaderId(dataLoader.getId());
		dataInputRequest.setDataId(data.getId());
		dataInputRequest.setName(Long.valueOf(new Date().getTime()).toString());
		return dataInputService.create(dataInputRequest);
	}
	
	/**
	 * @param dataInput
	 * @return
	 * @throws ApplicationException 
	 */
	private DataInputJob createDataInputJob(DataInput dataInput) throws ApplicationException {
		DataInputJobRequest inputJobRequest = new DataInputJobRequest();
		PropertyUtils.initializeBaseFields(inputJobRequest);
		inputJobRequest.setDiFreqCd(MINUTE);
		inputJobRequest.setDiFreqVal(1);
		inputJobRequest.setCode(dataInput.getName() + Long.valueOf(new Date().getTime()));
		inputJobRequest.setName(dataInput.getName());
		inputJobRequest.setDataInputId(dataInput.getId());
		// Set the job type to NON_CYCLIC
		DataInputJobType inputJobType = dataInputJobTypeService.findByCode(DataInputJobTypeService.NON_CYCLIC);
		if(inputJobType == null)
			throw new ApplicationException(CONFIG_ERROR_JOB_TY_NOT_FOUND);
		inputJobRequest.setDataInputJobTypeId(inputJobType.getId());
		// Set the job status to running
		DataInputJobStatus inputJobStatus = dataInputJobStatusService.findByCode(DataInputJobStatusService.RUNNING);
		if(inputJobStatus == null)
			throw new ApplicationException(CONFIG_ERROR_JOB_TY_NOT_FOUND);
		inputJobRequest.setDataInputJobStatusId(inputJobStatus.getId());
		return dataInputJobService.create(inputJobRequest);
	}

	private Data createDataRequest(DataChannel dataChannel, DataType dataType, DataStructure dataStructure) throws ApplicationException
	{
		DataRequest dataRequest = new DataRequest();
		PropertyUtils.initializeBaseFields(dataRequest);
		dataRequest.setCode(dataStructure.getCode() + new Date().getTime() / 3);
		dataRequest.setName(dataType.getName() + ":" + dataStructure.getName() + new Date().getTime() );
		dataRequest.setDataStructureId(dataStructure.getId());
		dataRequest.setDataChannelId(dataChannel.getId());
		dataRequest.setDataTypeId(dataType.getId());
		return dataService.create(dataRequest);
	}

}
