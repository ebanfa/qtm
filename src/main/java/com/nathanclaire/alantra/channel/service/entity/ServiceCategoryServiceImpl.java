/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

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

import com.nathanclaire.alantra.channel.model.ServiceCategory;
import com.nathanclaire.alantra.channel.request.ServiceCategoryRequest;
import com.nathanclaire.alantra.channel.response.ServiceCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceCategoryServiceImpl 
	extends BaseEntityServiceImpl<ServiceCategory, ServiceCategoryResponse, ServiceCategoryRequest> 
	implements ServiceCategoryService
{
	private static final String ENTITY_NAME = "ServiceCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICECATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICECATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(ServiceCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ServiceCategoryServiceImpl() {
		super(ServiceCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findById(java.lang.Integer)
	 */
	@Override
	public ServiceCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findByCode(java.lang.String)
	 */
	@Override
	public ServiceCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findByName(java.lang.String)
	 */
	@Override
	public ServiceCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#createServiceCategory(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceCategory create(ServiceCategoryRequest serviceCategoryRequest) throws ApplicationException {
		return createInstance(serviceCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#deleteServiceCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceCategory#updateServiceCategory(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceCategory update(ServiceCategoryRequest serviceCategoryRequest) throws ApplicationException {
		return updateInstance(serviceCategoryRequest);
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
		for(ServiceCategory servicecategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicecategory.getId(), servicecategory.getCode(), servicecategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceCategory convertRequestToModel(ServiceCategoryRequest serviceCategoryRequest) 
     throws ApplicationException {
		ServiceCategory serviceCategory = new ServiceCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(serviceCategoryRequest, serviceCategory, allowedEntityFields);
    	//Process many to one relationships
		return serviceCategory;
	}
	
	@Override
	public ServiceCategoryResponse convertModelToResponse(ServiceCategory model) throws ApplicationException {
		if (model == null) return null;
		ServiceCategoryResponse serviceCategoryResponse = new ServiceCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return serviceCategoryResponse;
	}
}
