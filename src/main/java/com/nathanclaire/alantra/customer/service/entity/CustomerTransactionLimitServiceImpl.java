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

import com.nathanclaire.alantra.customer.model.CustomerTransactionLimit;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.LimitType;
import com.nathanclaire.alantra.customer.request.CustomerTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerTransactionLimitResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.customer.service.entity.LimitTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerTransactionLimitServiceImpl 
	extends BaseEntityServiceImpl<CustomerTransactionLimit, CustomerTransactionLimitResponse, CustomerTransactionLimitRequest> 
	implements CustomerTransactionLimitService
{
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_LIMITTYPE = "limitType";
	private static final String ENTITY_NAME = "CustomerTransactionLimit";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERTRANSACTIONLIMIT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERTRANSACTIONLIMIT";
	
	private Logger logger = LoggerFactory.getLogger(CustomerTransactionLimitServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	@Inject
	CustomerService  customerService;
	@Inject
	LimitTypeService  limitTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerTransactionLimitServiceImpl() {
		super(CustomerTransactionLimit.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#findById(java.lang.Integer)
	 */
	@Override
	public CustomerTransactionLimit findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#findByCode(java.lang.String)
	 */
	@Override
	public CustomerTransactionLimit findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#findByName(java.lang.String)
	 */
	@Override
	public CustomerTransactionLimit findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerTransactionLimit> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#createCustomerTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerTransactionLimit create(CustomerTransactionLimitRequest customerTransactionLimitRequest) throws ApplicationException {
		return createInstance(customerTransactionLimitRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#deleteCustomerTransactionLimit(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTransactionLimit#updateCustomerTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerTransactionLimit update(CustomerTransactionLimitRequest customerTransactionLimitRequest) throws ApplicationException {
		return updateInstance(customerTransactionLimitRequest);
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
		List<ListItemResponse> customers = customerService.asListItem();
		List<ListItemResponse> limitTypes = limitTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
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
		for(CustomerTransactionLimit customertransactionlimit: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customertransactionlimit.getId(), customertransactionlimit.getCode(), customertransactionlimit.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerTransactionLimit convertRequestToModel(CustomerTransactionLimitRequest customerTransactionLimitRequest) 
     throws ApplicationException {
		CustomerTransactionLimit customerTransactionLimit = new CustomerTransactionLimit();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(customerTransactionLimitRequest, customerTransactionLimit, allowedEntityFields);
    	//Process many to one relationships
    	if (customerTransactionLimitRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, customerTransactionLimitRequest.getServiceTransactionTypeId());
    		customerTransactionLimit.setServiceTransactionType(serviceTransactionType);
    	}
    	if (customerTransactionLimitRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, customerTransactionLimitRequest.getCustomerId());
    		customerTransactionLimit.setCustomer(customer);
    	}
    	if (customerTransactionLimitRequest.getLimitTypeId() != null)
    	{
    		LimitType limitType = getEntityManager().find(LimitType.class, customerTransactionLimitRequest.getLimitTypeId());
    		customerTransactionLimit.setLimitType(limitType);
    	}
		return customerTransactionLimit;
	}
	
	@Override
	public CustomerTransactionLimitResponse convertModelToResponse(CustomerTransactionLimit model) throws ApplicationException {
		if (model == null) return null;
		CustomerTransactionLimitResponse customerTransactionLimitResponse = new CustomerTransactionLimitResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, customerTransactionLimitResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceTransactionType() != null)
			customerTransactionLimitResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			customerTransactionLimitResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		if(model.getCustomer() != null)
			customerTransactionLimitResponse.setCustomerId(model.getCustomer().getId());
			customerTransactionLimitResponse.setCustomerText(model.getCustomer().getName());
		if(model.getLimitType() != null)
			customerTransactionLimitResponse.setLimitTypeId(model.getLimitType().getId());
			customerTransactionLimitResponse.setLimitTypeText(model.getLimitType().getName());
		return customerTransactionLimitResponse;
	}
}
