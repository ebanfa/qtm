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

import com.nathanclaire.alantra.datasource.model.DataInputJobCategory;
import com.nathanclaire.alantra.datasource.request.DataInputJobCategoryRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataInputJobCategoryServiceImpl 
	extends BaseEntityServiceImpl<DataInputJobCategory, DataInputJobCategoryResponse, DataInputJobCategoryRequest> 
	implements DataInputJobCategoryService
{
	private static final String ENTITY_NAME = "DataInputJobCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATAINPUTJOBCATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATAINPUTJOBCATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(DataInputJobCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataInputJobCategoryServiceImpl() {
		super(DataInputJobCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#findById(java.lang.Integer)
	 */
	@Override
	public DataInputJobCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#findByCode(java.lang.String)
	 */
	@Override
	public DataInputJobCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#findByName(java.lang.String)
	 */
	@Override
	public DataInputJobCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#findAll(java.util.Map)
	 */
	@Override
	public List<DataInputJobCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#createDataInputJobCategory(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobCategory create(DataInputJobCategoryRequest dataInputJobCategoryRequest) throws ApplicationException {
		return createInstance(dataInputJobCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#deleteDataInputJobCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataInputJobCategory#updateDataInputJobCategory(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataInputJobCategory update(DataInputJobCategoryRequest dataInputJobCategoryRequest) throws ApplicationException {
		return updateInstance(dataInputJobCategoryRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(DataInputJobCategory datainputjobcategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datainputjobcategory.getId(), datainputjobcategory.getCode(), datainputjobcategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataInputJobCategory convertRequestToModel(DataInputJobCategoryRequest dataInputJobCategoryRequest) 
     throws ApplicationException {
		DataInputJobCategory dataInputJobCategory = new DataInputJobCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(dataInputJobCategoryRequest, dataInputJobCategory, allowedEntityFields);
    	//Process many to one relationships
		return dataInputJobCategory;
	}
	
	@Override
	public DataInputJobCategoryResponse convertModelToResponse(DataInputJobCategory model) throws ApplicationException {
		if (model == null) return null;
		DataInputJobCategoryResponse dataInputJobCategoryResponse = new DataInputJobCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, dataInputJobCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataInputJobCategoryResponse;
	}
}
