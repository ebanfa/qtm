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

import com.nathanclaire.alantra.customer.model.CustomerMessage;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.customer.request.CustomerMessageRequest;
import com.nathanclaire.alantra.customer.response.CustomerMessageResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerMessageServiceImpl 
	extends BaseEntityServiceImpl<CustomerMessage, CustomerMessageResponse, CustomerMessageRequest> 
	implements CustomerMessageService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_MESSAGE = "message";
	private static final String ENTITY_NAME = "CustomerMessage";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERMESSAGE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERMESSAGE";
	
	private Logger logger = LoggerFactory.getLogger(CustomerMessageServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	@Inject
	MessageService  messageService;
	
	/**
	 * @param entityClass
	 */
	public CustomerMessageServiceImpl() {
		super(CustomerMessage.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#findById(java.lang.Integer)
	 */
	@Override
	public CustomerMessage findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#findByCode(java.lang.String)
	 */
	@Override
	public CustomerMessage findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#findByName(java.lang.String)
	 */
	@Override
	public CustomerMessage findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerMessage> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#createCustomerMessage(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerMessage create(CustomerMessageRequest customerMessageRequest) throws ApplicationException {
		return createInstance(customerMessageRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#deleteCustomerMessage(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerMessage#updateCustomerMessage(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerMessage update(CustomerMessageRequest customerMessageRequest) throws ApplicationException {
		return updateInstance(customerMessageRequest);
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
		List<ListItemResponse> messages = messageService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		listItems.put(LIST_ITEM_MESSAGE, messages); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerMessage customermessage: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customermessage.getId(), customermessage.getCode(), customermessage.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerMessage convertRequestToModel(CustomerMessageRequest customerMessageRequest) 
     throws ApplicationException {
		CustomerMessage customerMessage = new CustomerMessage();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerMessageRequest, customerMessage, allowedEntityFields);
    	//Process many to one relationships
    	if (customerMessageRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, customerMessageRequest.getCustomerId());
    		customerMessage.setCustomer(customer);
    	}
    	if (customerMessageRequest.getMessageId() != null)
    	{
    		Message message = getEntityManager().find(Message.class, customerMessageRequest.getMessageId());
    		customerMessage.setMessage(message);
    	}
		return customerMessage;
	}
	
	@Override
	public CustomerMessageResponse convertModelToResponse(CustomerMessage model) throws ApplicationException {
		if (model == null) return null;
		CustomerMessageResponse customerMessageResponse = new CustomerMessageResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerMessageResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			customerMessageResponse.setCustomerId(model.getCustomer().getId());
			customerMessageResponse.setCustomerText(model.getCustomer().getName());
		if(model.getMessage() != null)
			customerMessageResponse.setMessageId(model.getMessage().getId());
			customerMessageResponse.setMessageText(model.getMessage().getCode());
		return customerMessageResponse;
	}
}
