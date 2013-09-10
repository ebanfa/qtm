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

import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.request.DataChannelCategoryRequest;
import com.nathanclaire.alantra.datasource.response.DataChannelCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class DataChannelCategoryServiceImpl 
	extends BaseEntityServiceImpl<DataChannelCategory, DataChannelCategoryResponse, DataChannelCategoryRequest> 
	implements DataChannelCategoryService
{
	private static final String ENTITY_NAME = "DataChannelCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_DATASOURCE_DATACHANNELCATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_DATASOURCE_DATACHANNELCATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(DataChannelCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public DataChannelCategoryServiceImpl() {
		super(DataChannelCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#findById(java.lang.Integer)
	 */
	@Override
	public DataChannelCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#findByCode(java.lang.String)
	 */
	@Override
	public DataChannelCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#findByName(java.lang.String)
	 */
	@Override
	public DataChannelCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#findAll(java.util.Map)
	 */
	@Override
	public List<DataChannelCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#createDataChannelCategory(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelCategory create(DataChannelCategoryRequest dataChannelCategoryRequest) throws ApplicationException {
		return createInstance(dataChannelCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#deleteDataChannelCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.service.DataChannelCategory#updateDataChannelCategory(com.nathanclaire.alantra.datasource.rest.request.ServiceRequest)
	 */
	@Override
	public DataChannelCategory update(DataChannelCategoryRequest dataChannelCategoryRequest) throws ApplicationException {
		return updateInstance(dataChannelCategoryRequest);
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
		for(DataChannelCategory datachannelcategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(datachannelcategory.getId(), datachannelcategory.getCode(), datachannelcategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public DataChannelCategory convertRequestToModel(DataChannelCategoryRequest dataChannelCategoryRequest) 
     throws ApplicationException {
		DataChannelCategory dataChannelCategory = new DataChannelCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(dataChannelCategoryRequest, dataChannelCategory, allowedEntityFields);
    	//Process many to one relationships
		return dataChannelCategory;
	}
	
	@Override
	public DataChannelCategoryResponse convertModelToResponse(DataChannelCategory model) throws ApplicationException {
		if (model == null) return null;
		DataChannelCategoryResponse dataChannelCategoryResponse = new DataChannelCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, dataChannelCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return dataChannelCategoryResponse;
	}
}
