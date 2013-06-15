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

import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.request.CustomerAccountRequest;
import com.nathanclaire.alantra.customer.response.CustomerAccountResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerAccountServiceImpl 
	extends BaseEntityServiceImpl<CustomerAccount, CustomerAccountResponse, CustomerAccountRequest> 
	implements CustomerAccountService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String ENTITY_NAME = "CustomerAccount";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERACCOUNT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERACCOUNT";
	
	private Logger logger = LoggerFactory.getLogger(CustomerAccountServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	
	/**
	 * @param entityClass
	 */
	public CustomerAccountServiceImpl() {
		super(CustomerAccount.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#findById(java.lang.Integer)
	 */
	@Override
	public CustomerAccount findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#findByCode(java.lang.String)
	 */
	@Override
	public CustomerAccount findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#findByName(java.lang.String)
	 */
	@Override
	public CustomerAccount findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerAccount> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#createCustomerAccount(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerAccount create(CustomerAccountRequest customerAccountRequest) throws ApplicationException {
		return createInstance(customerAccountRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#deleteCustomerAccount(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerAccount#updateCustomerAccount(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerAccount update(CustomerAccountRequest customerAccountRequest) throws ApplicationException {
		return updateInstance(customerAccountRequest);
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
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerAccount customeraccount: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customeraccount.getId(), customeraccount.getCode(), customeraccount.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerAccount convertRequestToModel(CustomerAccountRequest customerAccountRequest) 
     throws ApplicationException {
		CustomerAccount customerAccount = new CustomerAccount();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerAccountRequest, customerAccount, allowedEntityFields);
    	//Process many to one relationships
    	if (customerAccountRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, customerAccountRequest.getCustomerId());
    		customerAccount.setCustomer(customer);
    	}
		return customerAccount;
	}
	
	@Override
	public CustomerAccountResponse convertModelToResponse(CustomerAccount model) throws ApplicationException {
		if (model == null) return null;
		CustomerAccountResponse customerAccountResponse = new CustomerAccountResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerAccountResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			customerAccountResponse.setCustomerId(model.getCustomer().getId());
			customerAccountResponse.setCustomerText(model.getCustomer().getName());
		return customerAccountResponse;
	}
}
