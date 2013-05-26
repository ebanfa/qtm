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

import com.nathanclaire.alantra.datasource.model.DataSourceField;
import com.nathanclaire.alantra.datasource.model.DataSourceStructure;
import com.nathanclaire.alantra.datasource.model.DataSourceFieldType;
import com.nathanclaire.alantra.datasource.request.DataSourceFieldRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceFieldResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceStructureService;
import com.nathanclaire.alantra.datasource.service.entity.DataSourceFieldTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataSourceFieldServiceImpl 
	extends BaseEntityServiceImpl<DataSourceField, DataSourceFieldResponse, DataSourceFieldRequest> 
	implements DataSourceFieldService
{
	private static final String LIST_ITEM_DATASOURCESTRUCTURE = "dataSourceStructure";
	private static final String LIST_ITEM_DATASOURCEFIELDTYPE = "dataSourceFieldType";
	private static final String ENTITY_NAME = "DataSourceField";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASOURCEFIELD";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASOURCEFIELD";
	
	private Logger logger = LoggerFactory.getLogger(DataSourceFieldServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataSourceStructureService  dataSourceStructureService;
	@Inject
	DataSourceFieldTypeService  dataSourceFieldTypeService;
	
	/**
	 * @param entityClass
	 */
	public DataSourceFieldServiceImpl() {
		super(DataSourceField.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#findById(java.lang.Integer)
	 */
	@Override
	public DataSourceField findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#findByCode(java.lang.String)
	 */
	@Override
	public DataSourceField findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#findByName(java.lang.String)
	 */
	@Override
	public DataSourceField findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#findAll(java.util.Map)
	 */
	@Override
	public List<DataSourceField> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#createDataSourceField(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceField create(DataSourceFieldRequest dataSourceFieldRequest) {
		return createInstance(dataSourceFieldRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#deleteDataSourceField(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceField#updateDataSourceField(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceField update(DataSourceFieldRequest dataSourceFieldRequest) {
		return updateInstance(dataSourceFieldRequest);
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
		List<ListItemResponse> dataSourceStructures = dataSourceStructureService.asListItem();
		List<ListItemResponse> dataSourceFieldTypes = dataSourceFieldTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_DATASOURCESTRUCTURE, dataSourceStructures); 
		listItems.put(LIST_ITEM_DATASOURCEFIELDTYPE, dataSourceFieldTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataSourceField datasourcefield: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datasourcefield.getId(), datasourcefield.getCode(), datasourcefield.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataSourceField convertRequestToModel(DataSourceFieldRequest dataSourceFieldRequest) 
    {
		DataSourceField dataSourceField = new DataSourceField();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataSourceFieldRequest, dataSourceField, allowedEntityFields);
    	//Process many to one relationships
    	if (dataSourceFieldRequest.getDataSourceStructureId() != null)
    	{
    		DataSourceStructure dataSourceStructure = getEntityManager().find(DataSourceStructure.class, dataSourceFieldRequest.getDataSourceStructureId());
    		dataSourceField.setDataSourceStructure(dataSourceStructure);
    	}
    	if (dataSourceFieldRequest.getDataSourceFieldTypeId() != null)
    	{
    		DataSourceFieldType dataSourceFieldType = getEntityManager().find(DataSourceFieldType.class, dataSourceFieldRequest.getDataSourceFieldTypeId());
    		dataSourceField.setDataSourceFieldType(dataSourceFieldType);
    	}
		return dataSourceField;
	}
	
	@Override
	public DataSourceFieldResponse convertModelToResponse(DataSourceField model) {
		if (model == null) return null;
		DataSourceFieldResponse dataSourceFieldResponse = new DataSourceFieldResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataSourceFieldResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataSourceStructure() != null)
			dataSourceFieldResponse.setDataSourceStructureId(model.getDataSourceStructure().getId());
		if(model.getDataSourceFieldType() != null)
			dataSourceFieldResponse.setDataSourceFieldTypeId(model.getDataSourceFieldType().getId());
		return dataSourceFieldResponse;
	}
}
