/**
 * 
 */
package com.nathanclaire.alantra.base.service.process;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobSummary;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryService;

/**
 * @author Edward Banfa 
 *
 */
public class BaseProcessService {

    /**
     * Entity
     */
    @Inject
    private EntityManager entityManager;
    
	protected static final String DATA_IMPORT_SERVICE_NOT_FOUND = 
			"BaseProcessService.DATA_IMPORT_SERVICE_NOT_FOUND";

	@Inject DataChannelService dataChannelService;
	@Inject DataInputJobSummaryService summaryService;
	/**
	 * @return
	 * @throws ApplicationException
	 */
	protected DataChannel getDataImportService(String code) throws ApplicationException {
		// Get the service
		DataChannel service = dataChannelService.findByCode(code);
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
	

	/**
	 * @param jobSummary
	 * @throws ApplicationException 
	 */
	protected void flagDataInputAccepted(TableData tableData) throws ApplicationException {

		DataInputJobSummary jobSummary = summaryService.findById(tableData.getJobSummaryId());
		jobSummary.setRecordsRead(jobSummary.getRecordsRead() + 1);
		jobSummary.setPrimEntityRecordsCreated(jobSummary.getPrimEntityRecordsCreated() + 1);
		jobSummary.setTotalEntitiesCreated(jobSummary.getTotalEntitiesCreated() + 1);
		getEntityManager().flush();
	}

	/**
	 * @param jobSummary
	 * @throws ApplicationException 
	 */
	protected void flagDataInputRejected(TableData tableData) throws ApplicationException 
	{
		DataInputJobSummary jobSummary = summaryService.findById(tableData.getJobSummaryId());
		jobSummary.setRecordsRejected(jobSummary.getRecordsRejected() + 1);
		jobSummary.setPrimEntityRecordsRejected(jobSummary.getPrimEntityRecordsRejected() + 1);
		jobSummary.setTotalEntitiesRejected(jobSummary.getTotalEntitiesRejected() + 1);
		getEntityManager().merge(jobSummary);
	}
	

}
