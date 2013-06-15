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

import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerRequest;
import com.nathanclaire.alantra.customer.response.CustomerResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationService;
import com.nathanclaire.alantra.customer.service.entity.CustomerTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerServiceImpl 
	extends BaseEntityServiceImpl<Customer, CustomerResponse, CustomerRequest> 
	implements CustomerService
{
	private static final String LIST_ITEM_CUSTOMERCLASSIFICATION = "customerClassification";
	private static final String LIST_ITEM_CUSTOMERTYPE = "customerType";
	private static final String ENTITY_NAME = "Customer";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMER";
	
	private Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerClassificationService  customerClassificationService;
	@Inject
	CustomerTypeService  customerTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerServiceImpl() {
		super(Customer.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findById(java.lang.Integer)
	 */
	@Override
	public Customer findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findByCode(java.lang.String)
	 */
	@Override
	public Customer findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findByName(java.lang.String)
	 */
	@Override
	public Customer findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#findAll(java.util.Map)
	 */
	@Override
	public List<Customer> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#createCustomer(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Customer create(CustomerRequest customerRequest) throws ApplicationException {
		logger.info("Creating customer {} with code:{}", customerRequest.getName(),
				customerRequest.getCode());
		return createInstance(customerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#deleteCustomer(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.Customer#updateCustomer(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public Customer update(CustomerRequest customerRequest) throws ApplicationException {
		return updateInstance(customerRequest);
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
		List<ListItemResponse> customerClassifications = customerClassificationService.asListItem();
		List<ListItemResponse> customerTypes = customerTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMERCLASSIFICATION, customerClassifications); 
		listItems.put(LIST_ITEM_CUSTOMERTYPE, customerTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Customer customer: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customer.getId(), customer.getCode(), customer.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Customer convertRequestToModel(CustomerRequest customerRequest) 
     throws ApplicationException {
		Customer customer = new Customer();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerRequest, customer, allowedEntityFields);
    	//Process many to one relationships
    	if (customerRequest.getCustomerClassificationId() != null)
    	{
    		CustomerClassification customerClassification = getEntityManager().find(CustomerClassification.class, customerRequest.getCustomerClassificationId());
    		customer.setCustomerClassification(customerClassification);
    	}
    	if (customerRequest.getCustomerTypeId() != null)
    	{
    		CustomerType customerType = getEntityManager().find(CustomerType.class, customerRequest.getCustomerTypeId());
    		customer.setCustomerType(customerType);
    	}
    	logger.info("Final customer is customer:{} code:{}", customer.getName(),
    			customer.getCode());
		return customer;
	}
	
	@Override
	public CustomerResponse convertModelToResponse(Customer model) throws ApplicationException {
		if (model == null) return null;
		CustomerResponse customerResponse = new CustomerResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomerClassification() != null)
			customerResponse.setCustomerClassificationId(model.getCustomerClassification().getId());
			customerResponse.setCustomerClassificationText(model.getCustomerClassification().getName());
		if(model.getCustomerType() != null)
			customerResponse.setCustomerTypeId(model.getCustomerType().getId());
			customerResponse.setCustomerTypeText(model.getCustomerType().getName());
		return customerResponse;
	}
}
