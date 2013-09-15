/**
 * 
 */
package com.nathanclaire.alantra.base.service.process;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelEntityService;

/**
 * @author Edward Banfa 
 *
 */
public class BaseProcessService {

    /**
     * Entity
     */
    @Inject EntityManager entityManager;

	@Inject DataChannelEntityService dataChannelEntityService;

	private static final String CONFIG_ERROR_DATA_CHANNEL_TYPE_NOT_SPECIFIED = 
			"BaseProcessService.CONFIG_ERROR_DATA_CHANNEL_TYPE_NOT_SPECIFIED";
	
	private static final String INVALID_CHANNEL_PROVIDED = "BaseProcessService.INVALID_CHANNEL_PROVIDED";
	protected static final String DATA_IMPORT_SERVICE_NOT_FOUND =  "BaseProcessService.DATA_IMPORT_SERVICE_NOT_FOUND";
	

	public static final String ENTITY_CREATION_ERROR_CD = "BaseProcessService.AS_ADVICE_CREATION_ERROR_CD";
	public static final String ENTITY_CREATION_ERROR_MSG = "BaseProcessService.ENTITY_CREATION_ERROR_MSG";
	

	public static final char ENTITY_STATUS_ACTIVE = 'A';

	/**
	 * @return
	 * @throws ApplicationException
	 */
	protected DataChannel getDataImportService(String code) throws ApplicationException {
		// Get the service
		DataChannel service = dataChannelEntityService.findByCode(code);
		if(service == null)
			throw new ApplicationException(DATA_IMPORT_SERVICE_NOT_FOUND);
		return service;
	}

	/**
	 * @param dataChannel
	 * @return
	 */
	protected DataChannelCategory getDataChannelCategory(DataChannel dataChannel) {
		DataChannelType dataChannelType = dataChannel.getDataChannelType();
		return dataChannelType.getDataChannelCategory();
	}

	protected DataChannelType getChannelType(DataChannel channel) throws ApplicationException
	{
		if(channel == null)
			throw new ApplicationException(INVALID_CHANNEL_PROVIDED);
		DataChannelType channelType = channel.getDataChannelType();
		if(channelType == null)
			throw new ApplicationException(CONFIG_ERROR_DATA_CHANNEL_TYPE_NOT_SPECIFIED);
		return channelType;
	}
	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

	/*
	protected void flagDataInputAccepted(TableDataLite tableDataLite) throws ApplicationException {

		DataInputJobSummary jobSummary = summaryService.findById(tableDataLite.getJobSummaryId());
		jobSummary.setRecordsRead(jobSummary.getRecordsRead() + 1);
		jobSummary.setPrimEntityRecordsCreated(jobSummary.getPrimEntityRecordsCreated() + 1);
		jobSummary.setTotalEntitiesCreated(jobSummary.getTotalEntitiesCreated() + 1);
		getEntityManager().flush();
	}

	protected void flagDataInputRejected(TableDataLite tableDataLite) throws ApplicationException 
	{
		DataInputJobSummary jobSummary = summaryService.findById(tableDataLite.getJobSummaryId());
		jobSummary.setRecordsRejected(jobSummary.getRecordsRejected() + 1);
		jobSummary.setPrimEntityRecordsRejected(jobSummary.getPrimEntityRecordsRejected() + 1);
		jobSummary.setTotalEntitiesRejected(jobSummary.getTotalEntitiesRejected() + 1);
		getEntityManager().merge(jobSummary);
	}*/

	/**
	 * @return
	 */
	protected Long getCurrentTimeInMilliSeconds() {
		return new Date().getTime();
	}
	

}
