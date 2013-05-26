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

import com.nathanclaire.alantra.datasource.model.DataSourceCategory;
import com.nathanclaire.alantra.datasource.request.DataSourceCategoryRequest;
import com.nathanclaire.alantra.datasource.response.DataSourceCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataSourceCategoryServiceImpl 
	extends BaseEntityServiceImpl<DataSourceCategory, DataSourceCategoryResponse, DataSourceCategoryRequest> 
	implements DataSourceCategoryService
{
	private static final String ENTITY_NAME = "DataSourceCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATASOURCECATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATASOURCECATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(DataSourceCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataSourceCategoryServiceImpl() {
		super(DataSourceCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#findById(java.lang.Integer)
	 */
	@Override
	public DataSourceCategory findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#findByCode(java.lang.String)
	 */
	@Override
	public DataSourceCategory findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#findByName(java.lang.String)
	 */
	@Override
	public DataSourceCategory findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#findAll(java.util.Map)
	 */
	@Override
	public List<DataSourceCategory> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#createDataSourceCategory(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceCategory create(DataSourceCategoryRequest dataSourceCategoryRequest) {
		return createInstance(dataSourceCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#deleteDataSourceCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataSourceCategory#updateDataSourceCategory(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataSourceCategory update(DataSourceCategoryRequest dataSourceCategoryRequest) {
		return updateInstance(dataSourceCategoryRequest);
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
		for(DataSourceCategory datasourcecategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datasourcecategory.getId(), datasourcecategory.getCode(), datasourcecategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataSourceCategory convertRequestToModel(DataSourceCategoryRequest dataSourceCategoryRequest) 
    {
		DataSourceCategory dataSourceCategory = new DataSourceCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataSourceCategoryRequest, dataSourceCategory, allowedEntityFields);
    	//Process many to one relationships
		return dataSourceCategory;
	}
	
	@Override
	public DataSourceCategoryResponse convertModelToResponse(DataSourceCategory model) {
		if (model == null) return null;
		DataSourceCategoryResponse dataSourceCategoryResponse = new DataSourceCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataSourceCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataSourceCategoryResponse;
	}
}
