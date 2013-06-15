/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.datasource.model.DataInputJobSummary;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobSummaryStatus;
import com.nathanclaire.alantra.datasource.request.DataInputJobSummaryRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobSummaryResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryStatusService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobSummaryServiceImpl 
	extends BaseEntityServiceImpl<DataInputJobSummary, DataInputJobSummaryResponse, DataInputJobSummaryRequest> 
	implements DataInputJobSummaryService
{
	private static final String LIST_ITEM_DATAINPUTJOB = "dataInputJob";
	private static final String LIST_ITEM_DATAINPUTJOBSUMMARYSTATUS = "dataInputJobSummaryStatus";
	private static final String ENTITY_NAME = "DataInputJobSummary";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOBSUMMARY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOBSUMMARY";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobSummaryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataInputJobService  dataInputJobService;
	@Inject
	DataInputJobSummaryStatusService  dataInputJobSummaryStatusService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobSummaryServiceImpl() {
		super(DataInputJobSummary.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJobSummary findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJobSummary findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#findByName(java.lang.String)
	 */
	@Override
	public DataInputJobSummary findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJobSummary> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#createDataInputJobSummary(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobSummary create(DataInputJobSummaryRequest dataInputJobSummaryRequest) throws ApplicationException {
		return createInstance(dataInputJobSummaryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#deleteDataInputJobSummary(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobSummary#updateDataInputJobSummary(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobSummary update(DataInputJobSummaryRequest dataInputJobSummaryRequest) throws ApplicationException {
		return updateInstance(dataInputJobSummaryRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() throws ApplicationException {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() throws ApplicationException {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() throws ApplicationException {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() throws ApplicationException {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	 throws ApplicationException {
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> dataInputJobs = dataInputJobService.asListItem();
		List<ListItemResponse> dataInputJobSummaryStatuss = dataInputJobSummaryStatusService.asListItem();
    	
		listItems.put(LIST_ITEM_DATAINPUTJOB, dataInputJobs); 
		listItems.put(LIST_ITEM_DATAINPUTJOBSUMMARYSTATUS, dataInputJobSummaryStatuss); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputJobSummary datainputjobsummary: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputjobsummary.getId(), datainputjobsummary.getCode(), datainputjobsummary.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJobSummary convertRequestToModel(DataInputJobSummaryRequest dataInputJobSummaryRequest) 
     throws ApplicationException {
		DataInputJobSummary dataInputJobSummary = new DataInputJobSummary();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobSummaryRequest, dataInputJobSummary, allowedEntityFields);
    	//Process many to one relationships
    	if (dataInputJobSummaryRequest.getDataInputJobId() != null)
    	{
    		DataInputJob dataInputJob = getEntityManager().find(DataInputJob.class, dataInputJobSummaryRequest.getDataInputJobId());
    		dataInputJobSummary.setDataInputJob(dataInputJob);
    	}
    	if (dataInputJobSummaryRequest.getDataInputJobSummaryStatusId() != null)
    	{
    		DataInputJobSummaryStatus dataInputJobSummaryStatus = getEntityManager().find(DataInputJobSummaryStatus.class, dataInputJobSummaryRequest.getDataInputJobSummaryStatusId());
    		dataInputJobSummary.setDataInputJobSummaryStatus(dataInputJobSummaryStatus);
    	}
		return dataInputJobSummary;
	}
	
	@Override
	public DataInputJobSummaryResponse convertModelToResponse(DataInputJobSummary model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobSummaryResponse dataInputJobSummaryResponse = new DataInputJobSummaryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobSummaryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataInputJob() != null)
			dataInputJobSummaryResponse.setDataInputJobId(model.getDataInputJob().getId());
			dataInputJobSummaryResponse.setDataInputJobText(model.getDataInputJob().getName());
		if(model.getDataInputJobSummaryStatus() != null)
			dataInputJobSummaryResponse.setDataInputJobSummaryStatusId(model.getDataInputJobSummaryStatus().getId());
			dataInputJobSummaryResponse.setDataInputJobSummaryStatusText(model.getDataInputJobSummaryStatus().getName());
		return dataInputJobSummaryResponse;
	}
}
