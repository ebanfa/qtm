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

import com.nathanclaire.alantra.datasource.model.DataInputJobRecordsSummary;
import com.nathanclaire.alantra.datasource.model.DataInputJobSummary;
import com.nathanclaire.alantra.datasource.model.DataInputJobRecordsSummaryStatus;
import com.nathanclaire.alantra.datasource.request.DataInputJobRecordsSummaryRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobRecordsSummaryResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobSummaryService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobRecordsSummaryStatusService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobRecordsSummaryServiceImpl 
	extends BaseEntityServiceImpl<DataInputJobRecordsSummary, DataInputJobRecordsSummaryResponse, DataInputJobRecordsSummaryRequest> 
	implements DataInputJobRecordsSummaryService
{
	private static final String LIST_ITEM_DATAINPUTJOBSUMMARY = "dataInputJobSummary";
	private static final String LIST_ITEM_DATAINPUTJOBRECORDSSUMMARYSTATUS = "dataInputJobRecordsSummaryStatus";
	private static final String ENTITY_NAME = "DataInputJobRecordsSummary";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOBRECORDSSUMMARY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOBRECORDSSUMMARY";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobRecordsSummaryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataInputJobSummaryService  dataInputJobSummaryService;
	@Inject
	DataInputJobRecordsSummaryStatusService  dataInputJobRecordsSummaryStatusService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobRecordsSummaryServiceImpl() {
		super(DataInputJobRecordsSummary.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJobRecordsSummary findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJobRecordsSummary findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#findByName(java.lang.String)
	 */
	@Override
	public DataInputJobRecordsSummary findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJobRecordsSummary> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#createDataInputJobRecordsSummary(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobRecordsSummary create(DataInputJobRecordsSummaryRequest dataInputJobRecordsSummaryRequest) throws ApplicationException {
		return createInstance(dataInputJobRecordsSummaryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#deleteDataInputJobRecordsSummary(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobRecordsSummary#updateDataInputJobRecordsSummary(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobRecordsSummary update(DataInputJobRecordsSummaryRequest dataInputJobRecordsSummaryRequest) throws ApplicationException {
		return updateInstance(dataInputJobRecordsSummaryRequest);
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
		List<ListItemResponse> dataInputJobSummarys = dataInputJobSummaryService.asListItem();
		List<ListItemResponse> dataInputJobRecordsSummaryStatuss = dataInputJobRecordsSummaryStatusService.asListItem();
    	
		listItems.put(LIST_ITEM_DATAINPUTJOBSUMMARY, dataInputJobSummarys); 
		listItems.put(LIST_ITEM_DATAINPUTJOBRECORDSSUMMARYSTATUS, dataInputJobRecordsSummaryStatuss); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputJobRecordsSummary datainputjobrecordssummary: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputjobrecordssummary.getId(), datainputjobrecordssummary.getCode(), datainputjobrecordssummary.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJobRecordsSummary convertRequestToModel(DataInputJobRecordsSummaryRequest dataInputJobRecordsSummaryRequest) 
     throws ApplicationException {
		DataInputJobRecordsSummary dataInputJobRecordsSummary = new DataInputJobRecordsSummary();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobRecordsSummaryRequest, dataInputJobRecordsSummary, allowedEntityFields);
    	//Process many to one relationships
    	if (dataInputJobRecordsSummaryRequest.getDataInputJobSummaryId() != null)
    	{
    		DataInputJobSummary dataInputJobSummary = getEntityManager().find(DataInputJobSummary.class, dataInputJobRecordsSummaryRequest.getDataInputJobSummaryId());
    		dataInputJobRecordsSummary.setDataInputJobSummary(dataInputJobSummary);
    	}
    	if (dataInputJobRecordsSummaryRequest.getDataInputJobRecordsSummaryStatusId() != null)
    	{
    		DataInputJobRecordsSummaryStatus dataInputJobRecordsSummaryStatus = getEntityManager().find(DataInputJobRecordsSummaryStatus.class, dataInputJobRecordsSummaryRequest.getDataInputJobRecordsSummaryStatusId());
    		dataInputJobRecordsSummary.setDataInputJobRecordsSummaryStatus(dataInputJobRecordsSummaryStatus);
    	}
		return dataInputJobRecordsSummary;
	}
	
	@Override
	public DataInputJobRecordsSummaryResponse convertModelToResponse(DataInputJobRecordsSummary model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobRecordsSummaryResponse dataInputJobRecordsSummaryResponse = new DataInputJobRecordsSummaryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobRecordsSummaryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataInputJobSummary() != null)
			dataInputJobRecordsSummaryResponse.setDataInputJobSummaryId(model.getDataInputJobSummary().getId());
			dataInputJobRecordsSummaryResponse.setDataInputJobSummaryText(model.getDataInputJobSummary().getCode());
		if(model.getDataInputJobRecordsSummaryStatus() != null)
			dataInputJobRecordsSummaryResponse.setDataInputJobRecordsSummaryStatusId(model.getDataInputJobRecordsSummaryStatus().getId());
			dataInputJobRecordsSummaryResponse.setDataInputJobRecordsSummaryStatusText(model.getDataInputJobRecordsSummaryStatus().getName());
		return dataInputJobRecordsSummaryResponse;
	}
}
