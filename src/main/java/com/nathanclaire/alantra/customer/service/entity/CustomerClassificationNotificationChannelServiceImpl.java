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

import com.nathanclaire.alantra.customer.model.CustomerClassificationCommsChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.request.CustomerClassificationNotificationChannelRequest;
import com.nathanclaire.alantra.customer.response.CustomerClassificationNotificationChannelResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerClassificationNotificationChannelServiceImpl 
	extends BaseEntityServiceImpl<CustomerClassificationCommsChannel, CustomerClassificationNotificationChannelResponse, CustomerClassificationNotificationChannelRequest> 
	implements CustomerClassificationNotificationChannelService
{
	private static final String LIST_ITEM_DATACHANNELCATEGORY = "dataChannelCategory";
	private static final String LIST_ITEM_CUSTOMERCLASSIFICATION = "customerClassification";
	private static final String ENTITY_NAME = "CustomerClassificationNotificationChannel";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERCLASSIFICATIONNOTIFICATIONCHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERCLASSIFICATIONNOTIFICATIONCHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(CustomerClassificationNotificationChannelServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataChannelCategoryService  dataChannelCategoryService;
	@Inject
	CustomerClassificationService  customerClassificationService;
	
	/**
	 * @param entityClass
	 */
	public CustomerClassificationNotificationChannelServiceImpl() {
		super(CustomerClassificationCommsChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#findById(java.lang.Integer)
	 */
	@Override
	public CustomerClassificationCommsChannel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#findByCode(java.lang.String)
	 */
	@Override
	public CustomerClassificationCommsChannel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#findByName(java.lang.String)
	 */
	@Override
	public CustomerClassificationCommsChannel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerClassificationCommsChannel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#createCustomerClassificationNotificationChannel(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerClassificationCommsChannel create(CustomerClassificationNotificationChannelRequest customerClassificationNotificationChannelRequest) throws ApplicationException {
		return createInstance(customerClassificationNotificationChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#deleteCustomerClassificationNotificationChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationNotificationChannel#updateCustomerClassificationNotificationChannel(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerClassificationCommsChannel update(CustomerClassificationNotificationChannelRequest customerClassificationNotificationChannelRequest) throws ApplicationException {
		return updateInstance(customerClassificationNotificationChannelRequest);
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
		List<ListItemResponse> dataChannelCategorys = dataChannelCategoryService.asListItem();
		List<ListItemResponse> customerClassifications = customerClassificationService.asListItem();
    	
		listItems.put(LIST_ITEM_DATACHANNELCATEGORY, dataChannelCategorys); 
		listItems.put(LIST_ITEM_CUSTOMERCLASSIFICATION, customerClassifications); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerClassificationCommsChannel customerclassificationnotificationchannel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customerclassificationnotificationchannel.getId(), customerclassificationnotificationchannel.getCode(), customerclassificationnotificationchannel.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerClassificationCommsChannel convertRequestToModel(CustomerClassificationNotificationChannelRequest customerClassificationNotificationChannelRequest) 
     throws ApplicationException {
		CustomerClassificationCommsChannel customerClassificationCommsChannel = new CustomerClassificationCommsChannel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerClassificationNotificationChannelRequest, customerClassificationCommsChannel, allowedEntityFields);
    	//Process many to one relationships
    	if (customerClassificationNotificationChannelRequest.getDataChannelCategoryId() != null)
    	{
    		DataChannelCategory dataChannelCategory = getEntityManager().find(DataChannelCategory.class, customerClassificationNotificationChannelRequest.getDataChannelCategoryId());
    		customerClassificationCommsChannel.setDataChannelCategory(dataChannelCategory);
    	}
    	if (customerClassificationNotificationChannelRequest.getCustomerClassificationId() != null)
    	{
    		CustomerClassification customerClassification = getEntityManager().find(CustomerClassification.class, customerClassificationNotificationChannelRequest.getCustomerClassificationId());
    		customerClassificationCommsChannel.setCustomerClassification(customerClassification);
    	}
		return customerClassificationCommsChannel;
	}
	
	@Override
	public CustomerClassificationNotificationChannelResponse convertModelToResponse(CustomerClassificationCommsChannel model) throws ApplicationException {
		if (model == null) return null;
		CustomerClassificationNotificationChannelResponse customerClassificationNotificationChannelResponse = new CustomerClassificationNotificationChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerClassificationNotificationChannelResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataChannelCategory() != null)
			customerClassificationNotificationChannelResponse.setDataChannelCategoryId(model.getDataChannelCategory().getId());
			customerClassificationNotificationChannelResponse.setDataChannelCategoryText(model.getDataChannelCategory().getName());
		if(model.getCustomerClassification() != null)
			customerClassificationNotificationChannelResponse.setCustomerClassificationId(model.getCustomerClassification().getId());
			customerClassificationNotificationChannelResponse.setCustomerClassificationText(model.getCustomerClassification().getName());
		return customerClassificationNotificationChannelResponse;
	}
}
