/**
 * 
 */
package com.nathanclaire.alantra.customer.service.entity;

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

import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.request.CustomerCategoryRequest;
import com.nathanclaire.alantra.customer.response.CustomerCategoryResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerCategoryServiceImpl 
	extends BaseEntityServiceImpl<CustomerCategory, CustomerCategoryResponse, CustomerCategoryRequest> 
	implements CustomerCategoryService
{
	private static final String ENTITY_NAME = "CustomerCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERCATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERCATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(CustomerCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public CustomerCategoryServiceImpl() {
		super(CustomerCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#findById(java.lang.Integer)
	 */
	@Override
	public CustomerCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#findByCode(java.lang.String)
	 */
	@Override
	public CustomerCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#findByName(java.lang.String)
	 */
	@Override
	public CustomerCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#createCustomerCategory(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerCategory create(CustomerCategoryRequest customerCategoryRequest) throws ApplicationException {
		return createInstance(customerCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#deleteCustomerCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategory#updateCustomerCategory(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerCategory update(CustomerCategoryRequest customerCategoryRequest) throws ApplicationException {
		return updateInstance(customerCategoryRequest);
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
		for(CustomerCategory customercategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customercategory.getId(), customercategory.getCode(), customercategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerCategory convertRequestToModel(CustomerCategoryRequest customerCategoryRequest) 
     throws ApplicationException {
		CustomerCategory customerCategory = new CustomerCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerCategoryRequest, customerCategory, allowedEntityFields);
    	//Process many to one relationships
		return customerCategory;
	}
	
	@Override
	public CustomerCategoryResponse convertModelToResponse(CustomerCategory model) throws ApplicationException {
		if (model == null) return null;
		CustomerCategoryResponse customerCategoryResponse = new CustomerCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return customerCategoryResponse;
	}
}
