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

import com.nathanclaire.alantra.customer.model.CustomerCategoryTransactionLimit;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.customer.model.LimitType;
import com.nathanclaire.alantra.customer.request.CustomerCategoryTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerCategoryTransactionLimitResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryService;
import com.nathanclaire.alantra.customer.service.entity.LimitTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerCategoryTransactionLimitServiceImpl 
	extends BaseEntityServiceImpl<CustomerCategoryTransactionLimit, CustomerCategoryTransactionLimitResponse, CustomerCategoryTransactionLimitRequest> 
	implements CustomerCategoryTransactionLimitService
{
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String LIST_ITEM_CUSTOMERCATEGORY = "customerCategory";
	private static final String LIST_ITEM_LIMITTYPE = "limitType";
	private static final String ENTITY_NAME = "CustomerCategoryTransactionLimit";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERCATEGORYTRANSACTIONLIMIT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERCATEGORYTRANSACTIONLIMIT";
	
	private Logger logger = LoggerFactory.getLogger(CustomerCategoryTransactionLimitServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	@Inject
	CustomerCategoryService  customerCategoryService;
	@Inject
	LimitTypeService  limitTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerCategoryTransactionLimitServiceImpl() {
		super(CustomerCategoryTransactionLimit.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#findById(java.lang.Integer)
	 */
	@Override
	public CustomerCategoryTransactionLimit findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#findByCode(java.lang.String)
	 */
	@Override
	public CustomerCategoryTransactionLimit findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#findByName(java.lang.String)
	 */
	@Override
	public CustomerCategoryTransactionLimit findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerCategoryTransactionLimit> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#createCustomerCategoryTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerCategoryTransactionLimit create(CustomerCategoryTransactionLimitRequest customerCategoryTransactionLimitRequest) throws ApplicationException {
		return createInstance(customerCategoryTransactionLimitRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#deleteCustomerCategoryTransactionLimit(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryTransactionLimit#updateCustomerCategoryTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerCategoryTransactionLimit update(CustomerCategoryTransactionLimitRequest customerCategoryTransactionLimitRequest) throws ApplicationException {
		return updateInstance(customerCategoryTransactionLimitRequest);
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
		List<ListItemResponse> serviceTransactionTypes = serviceTransactionTypeService.asListItem();
		List<ListItemResponse> customerCategorys = customerCategoryService.asListItem();
		List<ListItemResponse> limitTypes = limitTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
		listItems.put(LIST_ITEM_CUSTOMERCATEGORY, customerCategorys); 
		listItems.put(LIST_ITEM_LIMITTYPE, limitTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerCategoryTransactionLimit customercategorytransactionlimit: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customercategorytransactionlimit.getId(), customercategorytransactionlimit.getCode(), customercategorytransactionlimit.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerCategoryTransactionLimit convertRequestToModel(CustomerCategoryTransactionLimitRequest customerCategoryTransactionLimitRequest) 
     throws ApplicationException {
		CustomerCategoryTransactionLimit customerCategoryTransactionLimit = new CustomerCategoryTransactionLimit();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerCategoryTransactionLimitRequest, customerCategoryTransactionLimit, allowedEntityFields);
    	//Process many to one relationships
    	if (customerCategoryTransactionLimitRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, customerCategoryTransactionLimitRequest.getServiceTransactionTypeId());
    		customerCategoryTransactionLimit.setServiceTransactionType(serviceTransactionType);
    	}
    	if (customerCategoryTransactionLimitRequest.getCustomerCategoryId() != null)
    	{
    		CustomerCategory customerCategory = getEntityManager().find(CustomerCategory.class, customerCategoryTransactionLimitRequest.getCustomerCategoryId());
    		customerCategoryTransactionLimit.setCustomerCategory(customerCategory);
    	}
    	if (customerCategoryTransactionLimitRequest.getLimitTypeId() != null)
    	{
    		LimitType limitType = getEntityManager().find(LimitType.class, customerCategoryTransactionLimitRequest.getLimitTypeId());
    		customerCategoryTransactionLimit.setLimitType(limitType);
    	}
		return customerCategoryTransactionLimit;
	}
	
	@Override
	public CustomerCategoryTransactionLimitResponse convertModelToResponse(CustomerCategoryTransactionLimit model) throws ApplicationException {
		if (model == null) return null;
		CustomerCategoryTransactionLimitResponse customerCategoryTransactionLimitResponse = new CustomerCategoryTransactionLimitResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerCategoryTransactionLimitResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceTransactionType() != null)
			customerCategoryTransactionLimitResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			customerCategoryTransactionLimitResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		if(model.getCustomerCategory() != null)
			customerCategoryTransactionLimitResponse.setCustomerCategoryId(model.getCustomerCategory().getId());
			customerCategoryTransactionLimitResponse.setCustomerCategoryText(model.getCustomerCategory().getName());
		if(model.getLimitType() != null)
			customerCategoryTransactionLimitResponse.setLimitTypeId(model.getLimitType().getId());
			customerCategoryTransactionLimitResponse.setLimitTypeText(model.getLimitType().getName());
		return customerCategoryTransactionLimitResponse;
	}
}
