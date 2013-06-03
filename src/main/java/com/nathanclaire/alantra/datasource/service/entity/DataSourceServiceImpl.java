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

import com.nathanclaire.alantra.datasource.model.DataSource;
import com.nathanclaire.alantra.datasource.model.DataSourceStructure;
import com.nathanclaire.alantra.datasource.model.DataSourceType;
import com.nathanclaire.alantra.datasource.request.DataSourceRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceStructureService;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataSourceServiceImpl 
	extends BaseEntityServiceImpl<DataSource, DataSourceResponse, DataSourceRequest> 
	implements DataSourceService
{
	private static final String LIST_ITEM_DATASOURCESTRUCTURE = "dataSourceStructure";
	private static final String LIST_ITEM_DATASOURCETYPE = "dataSourceType";
	private static final String ENTITY_NAME = "DataSource";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASOURCE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASOURCE";
	
	private Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataSourceStructureService  dataSourceStructureService;
	@Inject
	DataSourceTypeService  dataSourceTypeService;
	
	/**
	 * @param entityClass
	 */
	public DataSourceServiceImpl() {
		super(DataSource.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#findById(java.lang.Integer)
	 */
	@Override
	public DataSource findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#findByCode(java.lang.String)
	 */
	@Override
	public DataSource findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#findByName(java.lang.String)
	 */
	@Override
	public DataSource findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#findAll(java.util.Map)
	 */
	@Override
	public List<DataSource> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#createDataSource(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSource create(DataSourceRequest dataSourceRequest) throws ApplicationException {
		return createInstance(dataSourceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#deleteDataSource(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSource#updateDataSource(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSource update(DataSourceRequest dataSourceRequest) throws ApplicationException {
		return updateInstance(dataSourceRequest);
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
		List<ListItemResponse> dataSourceStructures = dataSourceStructureService.asListItem();
		List<ListItemResponse> dataSourceTypes = dataSourceTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_DATASOURCESTRUCTURE, dataSourceStructures); 
		listItems.put(LIST_ITEM_DATASOURCETYPE, dataSourceTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataSource datasource: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datasource.getId(), datasource.getCode(), datasource.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataSource convertRequestToModel(DataSourceRequest dataSourceRequest) 
     throws ApplicationException {
		DataSource dataSource = new DataSource();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataSourceRequest, dataSource, allowedEntityFields);
    	//Process many to one relationships
    	if (dataSourceRequest.getDataSourceStructureId() != null)
    	{
    		DataSourceStructure dataSourceStructure = getEntityManager().find(DataSourceStructure.class, dataSourceRequest.getDataSourceStructureId());
    		dataSource.setDataSourceStructure(dataSourceStructure);
    	}
    	if (dataSourceRequest.getDataSourceTypeId() != null)
    	{
    		DataSourceType dataSourceType = getEntityManager().find(DataSourceType.class, dataSourceRequest.getDataSourceTypeId());
    		dataSource.setDataSourceType(dataSourceType);
    	}
		return dataSource;
	}
	
	@Override
	public DataSourceResponse convertModelToResponse(DataSource model) throws ApplicationException {
		if (model == null) return null;
		DataSourceResponse dataSourceResponse = new DataSourceResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataSourceResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataSourceStructure() != null)
			dataSourceResponse.setDataSourceStructureId(model.getDataSourceStructure().getId());
			dataSourceResponse.setDataSourceStructureText(model.getDataSourceStructure().getName());
		if(model.getDataSourceType() != null)
			dataSourceResponse.setDataSourceTypeId(model.getDataSourceType().getId());
			dataSourceResponse.setDataSourceTypeText(model.getDataSourceType().getName());
		return dataSourceResponse;
	}
}
