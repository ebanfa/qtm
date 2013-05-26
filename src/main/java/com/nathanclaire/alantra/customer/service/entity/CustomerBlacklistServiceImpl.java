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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.customer.model.CustomerBlacklist;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.request.CustomerBlacklistRequest;
import com.nathanclaire.alantra.customer.response.CustomerBlacklistResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerBlacklistServiceImpl 
	extends BaseEntityServiceImpl<CustomerBlacklist, CustomerBlacklistResponse, CustomerBlacklistRequest> 
	implements CustomerBlacklistService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String ENTITY_NAME = "CustomerBlacklist";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERBLACKLIST";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERBLACKLIST";
	
	private Logger logger = LoggerFactory.getLogger(CustomerBlacklistServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	
	/**
	 * @param entityClass
	 */
	public CustomerBlacklistServiceImpl() {
		super(CustomerBlacklist.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findById(java.lang.Integer)
	 */
	@Override
	public CustomerBlacklist findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findByCode(java.lang.String)
	 */
	@Override
	public CustomerBlacklist findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findByName(java.lang.String)
	 */
	@Override
	public CustomerBlacklist findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerBlacklist> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#createCustomerBlacklist(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerBlacklist create(CustomerBlacklistRequest customerBlacklistRequest) {
		return createInstance(customerBlacklistRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#deleteCustomerBlacklist(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerBlacklist#updateCustomerBlacklist(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerBlacklist update(CustomerBlacklistRequest customerBlacklistRequest) {
		return updateInstance(customerBlacklistRequest);
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
		List<ListItemResponse> customers = customerService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerBlacklist customerblacklist: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customerblacklist.getId(), customerblacklist.getCode(), customerblacklist.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerBlacklist convertRequestToModel(CustomerBlacklistRequest customerBlacklistRequest) 
    {
		CustomerBlacklist customerBlacklist = new CustomerBlacklist();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerBlacklistRequest, customerBlacklist, allowedEntityFields);
    	//Process many to one relationships
    	if (customerBlacklistRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, customerBlacklistRequest.getCustomerId());
    		customerBlacklist.setCustomer(customer);
    	}
		return customerBlacklist;
	}
	
	@Override
	public CustomerBlacklistResponse convertModelToResponse(CustomerBlacklist model) {
		if (model == null) return null;
		CustomerBlacklistResponse customerBlacklistResponse = new CustomerBlacklistResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerBlacklistResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			customerBlacklistResponse.setCustomerId(model.getCustomer().getId());
		return customerBlacklistResponse;
	}
}
