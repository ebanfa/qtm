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

import com.nathanclaire.alantra.datasource.model.DataSourceStructure;
import com.nathanclaire.alantra.datasource.request.DataSourceStructureRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceStructureResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataSourceStructureServiceImpl 
	extends BaseEntityServiceImpl<DataSourceStructure, DataSourceStructureResponse, DataSourceStructureRequest> 
	implements DataSourceStructureService
{
	private static final String ENTITY_NAME = "DataSourceStructure";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASOURCESTRUCTURE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASOURCESTRUCTURE";
	
	private Logger logger = LoggerFactory.getLogger(DataSourceStructureServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataSourceStructureServiceImpl() {
		super(DataSourceStructure.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#findById(java.lang.Integer)
	 */
	@Override
	public DataSourceStructure findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#findByCode(java.lang.String)
	 */
	@Override
	public DataSourceStructure findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#findByName(java.lang.String)
	 */
	@Override
	public DataSourceStructure findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#findAll(java.util.Map)
	 */
	@Override
	public List<DataSourceStructure> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#createDataSourceStructure(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceStructure create(DataSourceStructureRequest dataSourceStructureRequest) {
		return createInstance(dataSourceStructureRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#deleteDataSourceStructure(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceStructure#updateDataSourceStructure(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceStructure update(DataSourceStructureRequest dataSourceStructureRequest) {
		return updateInstance(dataSourceStructureRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataSourceStructure datasourcestructure: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datasourcestructure.getId(), datasourcestructure.getCode(), datasourcestructure.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataSourceStructure convertRequestToModel(DataSourceStructureRequest dataSourceStructureRequest) 
    {
		DataSourceStructure dataSourceStructure = new DataSourceStructure();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataSourceStructureRequest, dataSourceStructure, allowedEntityFields);
    	//Process many to one relationships
		return dataSourceStructure;
	}
	
	@Override
	public DataSourceStructureResponse convertModelToResponse(DataSourceStructure model) {
		if (model == null) return null;
		DataSourceStructureResponse dataSourceStructureResponse = new DataSourceStructureResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataSourceStructureResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataSourceStructureResponse;
	}
}
