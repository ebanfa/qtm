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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataSource;
import com.nathanclaire.alantra.datasource.request.DataInputJobRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
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
	private static final String LIST_ITEM_DATASOURCE = "dataSource";
	private static final String ENTITY_NAME = "DataInputJob";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOB";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOB";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataSourceService  dataSourceService;
	
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
	public DataInputJob findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJob findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findByName(java.lang.String)
	 */
	@Override
	public DataInputJob findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJob> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#createDataInputJob(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJob create(DataInputJobRequest dataInputJobRequest) {
		return createInstance(dataInputJobRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#deleteDataInputJob(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJob#updateDataInputJob(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJob update(DataInputJobRequest dataInputJobRequest) {
		return updateInstance(dataInputJobRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
		List<ListItemResponse> dataSources = dataSourceService.asListItem();
    	
		listItems.put(LIST_ITEM_DATASOURCE, dataSources); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
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
    {
		DataInputJob dataInputJob = new DataInputJob();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobRequest, dataInputJob, allowedEntityFields);
    	//Process many to one relationships
    	if (dataInputJobRequest.getDataSourceId() != null)
    	{
    		DataSource dataSource = getEntityManager().find(DataSource.class, dataInputJobRequest.getDataSourceId());
    		dataInputJob.setDataSource(dataSource);
    	}
		return dataInputJob;
	}
	
	@Override
	public DataInputJobResponse convertModelToResponse(DataInputJob model) {
		if (model == null) return null;
		DataInputJobResponse dataInputJobResponse = new DataInputJobResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataSource() != null)
			dataInputJobResponse.setDataSourceId(model.getDataSource().getId());
		return dataInputJobResponse;
	}
}
