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

import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataInputJobStatus;
import com.nathanclaire.alantra.datasource.model.DataInputJobType;
import com.nathanclaire.alantra.datasource.model.DataInput;
import com.nathanclaire.alantra.datasource.request.DataInputJobRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobStatusService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputJobTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataInputService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobServiceImpl 
	extends BaseEntityServiceImpl<DataInputJob, DataInputJobResponse, DataInputJobRequest> 
	implements DataInputJobService
{
	private static final String LIST_ITEM_DATAINPUTJOBSTATUS = "dataInputJobStatus";
	private static final String LIST_ITEM_DATAINPUTJOBTYPE = "dataInputJobType";
	private static final String LIST_ITEM_DATAINPUT = "dataInput";
	private static final String ENTITY_NAME = "DataInputJob";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOB";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOB";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataInputJobStatusService  dataInputJobStatusService;
	@Inject
	DataInputJobTypeService  dataInputJobTypeService;
	@Inject
	DataInputService  dataInputService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobServiceImpl() {
		super(DataInputJob.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJob findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJob findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findByName(java.lang.String)
	 */
	@Override
	public DataInputJob findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJob> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#createDataInputJob(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJob create(DataInputJobRequest dataInputJobRequest) throws ApplicationException {
		return createInstance(dataInputJobRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#deleteDataInputJob(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#updateDataInputJob(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJob update(DataInputJobRequest dataInputJobRequest) throws ApplicationException {
		return updateInstance(dataInputJobRequest);
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
		List<ListItemResponse> dataInputJobStatuss = dataInputJobStatusService.asListItem();
		List<ListItemResponse> dataInputJobTypes = dataInputJobTypeService.asListItem();
		List<ListItemResponse> dataInputs = dataInputService.asListItem();
    	
		listItems.put(LIST_ITEM_DATAINPUTJOBSTATUS, dataInputJobStatuss); 
		listItems.put(LIST_ITEM_DATAINPUTJOBTYPE, dataInputJobTypes); 
		listItems.put(LIST_ITEM_DATAINPUT, dataInputs); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputJob datainputjob: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputjob.getId(), datainputjob.getCode(), datainputjob.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJob convertRequestToModel(DataInputJobRequest dataInputJobRequest) 
     throws ApplicationException {
		DataInputJob dataInputJob = new DataInputJob();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobRequest, dataInputJob, allowedEntityFields);
    	//Process many to one relationships
    	if (dataInputJobRequest.getDataInputJobStatusId() != null)
    	{
    		DataInputJobStatus dataInputJobStatus = getEntityManager().find(DataInputJobStatus.class, dataInputJobRequest.getDataInputJobStatusId());
    		dataInputJob.setDataInputJobStatus(dataInputJobStatus);
    	}
    	if (dataInputJobRequest.getDataInputJobTypeId() != null)
    	{
    		DataInputJobType dataInputJobType = getEntityManager().find(DataInputJobType.class, dataInputJobRequest.getDataInputJobTypeId());
    		dataInputJob.setDataInputJobType(dataInputJobType);
    	}
    	if (dataInputJobRequest.getDataInputId() != null)
    	{
    		DataInput dataInput = getEntityManager().find(DataInput.class, dataInputJobRequest.getDataInputId());
    		dataInputJob.setDataInput(dataInput);
    	}
		return dataInputJob;
	}
	
	@Override
	public DataInputJobResponse convertModelToResponse(DataInputJob model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobResponse dataInputJobResponse = new DataInputJobResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataInputJobStatus() != null)
			dataInputJobResponse.setDataInputJobStatusId(model.getDataInputJobStatus().getId());
			dataInputJobResponse.setDataInputJobStatusText(model.getDataInputJobStatus().getName());
		if(model.getDataInputJobType() != null)
			dataInputJobResponse.setDataInputJobTypeId(model.getDataInputJobType().getId());
			dataInputJobResponse.setDataInputJobTypeText(model.getDataInputJobType().getName());
		if(model.getDataInput() != null)
			dataInputJobResponse.setDataInputId(model.getDataInput().getId());
			dataInputJobResponse.setDataInputText(model.getDataInput().getName());
		return dataInputJobResponse;
	}
}
