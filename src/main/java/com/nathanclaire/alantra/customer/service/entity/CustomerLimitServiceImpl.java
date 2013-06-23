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

import com.nathanclaire.alantra.customer.model.CustomerLimit;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.LimitType;
import com.nathanclaire.alantra.customer.request.CustomerLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerLimitResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
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
public class CustomerLimitServiceImpl 
	extends BaseEntityServiceImpl<CustomerLimit, CustomerLimitResponse, CustomerLimitRequest> 
	implements CustomerLimitService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_LIMITTYPE = "limitType";
	private static final String ENTITY_NAME = "CustomerLimit";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERLIMIT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERLIMIT";
	
	private Logger logger = LoggerFactory.getLogger(CustomerLimitServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	@Inject
	LimitTypeService  limitTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerLimitServiceImpl() {
		super(CustomerLimit.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#findById(java.lang.Integer)
	 */
	@Override
	public CustomerLimit findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#findByCode(java.lang.String)
	 */
	@Override
	public CustomerLimit findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#findByName(java.lang.String)
	 */
	@Override
	public CustomerLimit findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerLimit> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#createCustomerLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerLimit create(CustomerLimitRequest customerLimitRequest) throws ApplicationException {
		return createInstance(customerLimitRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#deleteCustomerLimit(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerLimit#updateCustomerLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerLimit update(CustomerLimitRequest customerLimitRequest) throws ApplicationException {
		return updateInstance(customerLimitRequest);
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
		List<ListItemResponse> customers = customerService.asListItem();
		List<ListItemResponse> limitTypes = limitTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
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
		for(CustomerLimit customerlimit: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customerlimit.getId(), customerlimit.getCode(), customerlimit.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerLimit convertRequestToModel(CustomerLimitRequest customerLimitRequest) 
     throws ApplicationException {
		CustomerLimit customerLimit = new CustomerLimit();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerLimitRequest, customerLimit, allowedEntityFields);
    	//Process many to one relationships
    	if (customerLimitRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, customerLimitRequest.getCustomerId());
    		customerLimit.setCustomer(customer);
    	}
    	if (customerLimitRequest.getLimitTypeId() != null)
    	{
    		LimitType limitType = getEntityManager().find(LimitType.class, customerLimitRequest.getLimitTypeId());
    		customerLimit.setLimitType(limitType);
    	}
		return customerLimit;
	}
	
	@Override
	public CustomerLimitResponse convertModelToResponse(CustomerLimit model) throws ApplicationException {
		if (model == null) return null;
		CustomerLimitResponse customerLimitResponse = new CustomerLimitResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerLimitResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			customerLimitResponse.setCustomerId(model.getCustomer().getId());
			customerLimitResponse.setCustomerText(model.getCustomer().getName());
		if(model.getLimitType() != null)
			customerLimitResponse.setLimitTypeId(model.getLimitType().getId());
			customerLimitResponse.setLimitTypeText(model.getLimitType().getName());
		return customerLimitResponse;
	}
}
