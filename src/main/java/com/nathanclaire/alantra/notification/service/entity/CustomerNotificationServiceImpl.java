/**
 * 
 */
package com.nathanclaire.alantra.notification.service.entity;

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

import com.nathanclaire.alantra.notification.model.CustomerNotification;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.request.CustomerNotificationRequest;
import com.nathanclaire.alantra.notification.response.CustomerNotificationResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerService;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerNotificationServiceImpl 
	extends BaseEntityServiceImpl<CustomerNotification, CustomerNotificationResponse, CustomerNotificationRequest> 
	implements CustomerNotificationService
{
	private static final String LIST_ITEM_CUSTOMER = "customer";
	private static final String LIST_ITEM_NOTIFICATIONTYPE = "notificationType";
	private static final String ENTITY_NAME = "CustomerNotification";
	private static final String LIST_ACTIVITY_CODE = "LIST_NOTIFICATION_CUSTOMERNOTIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_NOTIFICATION_CUSTOMERNOTIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(CustomerNotificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerService  customerService;
	@Inject
	NotificationTypeService  notificationTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerNotificationServiceImpl() {
		super(CustomerNotification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#findById(java.lang.Integer)
	 */
	@Override
	public CustomerNotification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#findByCode(java.lang.String)
	 */
	@Override
	public CustomerNotification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#findByName(java.lang.String)
	 */
	@Override
	public CustomerNotification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerNotification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#createCustomerNotification(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerNotification create(CustomerNotificationRequest customerNotificationRequest) throws ApplicationException {
		return createInstance(customerNotificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#deleteCustomerNotification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.notification.service.CustomerNotification#updateCustomerNotification(com.nathanclaire.alantra.notification.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerNotification update(CustomerNotificationRequest customerNotificationRequest) throws ApplicationException {
		return updateInstance(customerNotificationRequest);
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
		List<ListItemResponse> notificationTypes = notificationTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMER, customers); 
		listItems.put(LIST_ITEM_NOTIFICATIONTYPE, notificationTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerNotification customernotification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customernotification.getId(), customernotification.getCode(), customernotification.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerNotification convertRequestToModel(CustomerNotificationRequest customerNotificationRequest) 
     throws ApplicationException {
		CustomerNotification customerNotification = new CustomerNotification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerNotificationRequest, customerNotification, allowedEntityFields);
    	//Process many to one relationships
    	if (customerNotificationRequest.getCustomerId() != null)
    	{
    		Customer customer = getEntityManager().find(Customer.class, customerNotificationRequest.getCustomerId());
    		customerNotification.setCustomer(customer);
    	}
    	if (customerNotificationRequest.getNotificationTypeId() != null)
    	{
    		NotificationType notificationType = getEntityManager().find(NotificationType.class, customerNotificationRequest.getNotificationTypeId());
    		customerNotification.setNotificationType(notificationType);
    	}
		return customerNotification;
	}
	
	@Override
	public CustomerNotificationResponse convertModelToResponse(CustomerNotification model) throws ApplicationException {
		if (model == null) return null;
		CustomerNotificationResponse customerNotificationResponse = new CustomerNotificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerNotificationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomer() != null)
			customerNotificationResponse.setCustomerId(model.getCustomer().getId());
			customerNotificationResponse.setCustomerText(model.getCustomer().getName());
		if(model.getNotificationType() != null)
			customerNotificationResponse.setNotificationTypeId(model.getNotificationType().getId());
			customerNotificationResponse.setNotificationTypeText(model.getNotificationType().getName());
		return customerNotificationResponse;
	}
}
