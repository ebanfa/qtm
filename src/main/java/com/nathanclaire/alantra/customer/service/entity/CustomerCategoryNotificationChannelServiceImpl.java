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

import com.nathanclaire.alantra.customer.model.CustomerCategoryNotificationChannel;
import com.nathanclaire.alantra.customer.model.CustomerCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.customer.request.CustomerCategoryNotificationChannelRequest;
import com.nathanclaire.alantra.customer.response.CustomerCategoryNotificationChannelResponse;
import com.nathanclaire.alantra.customer.service.entity.CustomerCategoryService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerCategoryNotificationChannelServiceImpl 
	extends BaseEntityServiceImpl<CustomerCategoryNotificationChannel, CustomerCategoryNotificationChannelResponse, CustomerCategoryNotificationChannelRequest> 
	implements CustomerCategoryNotificationChannelService
{
	private static final String LIST_ITEM_CUSTOMERCATEGORY = "customerCategory";
	private static final String LIST_ITEM_DATACHANNELCATEGORY = "dataChannelCategory";
	private static final String ENTITY_NAME = "CustomerCategoryNotificationChannel";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERCATEGORYNOTIFICATIONCHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERCATEGORYNOTIFICATIONCHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(CustomerCategoryNotificationChannelServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	CustomerCategoryService  customerCategoryService;
	@Inject
	DataChannelCategoryService  dataChannelCategoryService;
	
	/**
	 * @param entityClass
	 */
	public CustomerCategoryNotificationChannelServiceImpl() {
		super(CustomerCategoryNotificationChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#findById(java.lang.Integer)
	 */
	@Override
	public CustomerCategoryNotificationChannel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#findByCode(java.lang.String)
	 */
	@Override
	public CustomerCategoryNotificationChannel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#findByName(java.lang.String)
	 */
	@Override
	public CustomerCategoryNotificationChannel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerCategoryNotificationChannel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#createCustomerCategoryNotificationChannel(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerCategoryNotificationChannel create(CustomerCategoryNotificationChannelRequest customerCategoryNotificationChannelRequest) throws ApplicationException {
		return createInstance(customerCategoryNotificationChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#deleteCustomerCategoryNotificationChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerCategoryNotificationChannel#updateCustomerCategoryNotificationChannel(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerCategoryNotificationChannel update(CustomerCategoryNotificationChannelRequest customerCategoryNotificationChannelRequest) throws ApplicationException {
		return updateInstance(customerCategoryNotificationChannelRequest);
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
		List<ListItemResponse> customerCategorys = customerCategoryService.asListItem();
		List<ListItemResponse> dataChannelCategorys = dataChannelCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_CUSTOMERCATEGORY, customerCategorys); 
		listItems.put(LIST_ITEM_DATACHANNELCATEGORY, dataChannelCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerCategoryNotificationChannel customercategorynotificationchannel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customercategorynotificationchannel.getId(), customercategorynotificationchannel.getCode(), customercategorynotificationchannel.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerCategoryNotificationChannel convertRequestToModel(CustomerCategoryNotificationChannelRequest customerCategoryNotificationChannelRequest) 
     throws ApplicationException {
		CustomerCategoryNotificationChannel customerCategoryNotificationChannel = new CustomerCategoryNotificationChannel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerCategoryNotificationChannelRequest, customerCategoryNotificationChannel, allowedEntityFields);
    	//Process many to one relationships
    	if (customerCategoryNotificationChannelRequest.getCustomerCategoryId() != null)
    	{
    		CustomerCategory customerCategory = getEntityManager().find(CustomerCategory.class, customerCategoryNotificationChannelRequest.getCustomerCategoryId());
    		customerCategoryNotificationChannel.setCustomerCategory(customerCategory);
    	}
    	if (customerCategoryNotificationChannelRequest.getDataChannelCategoryId() != null)
    	{
    		DataChannelCategory dataChannelCategory = getEntityManager().find(DataChannelCategory.class, customerCategoryNotificationChannelRequest.getDataChannelCategoryId());
    		customerCategoryNotificationChannel.setDataChannelCategory(dataChannelCategory);
    	}
		return customerCategoryNotificationChannel;
	}
	
	@Override
	public CustomerCategoryNotificationChannelResponse convertModelToResponse(CustomerCategoryNotificationChannel model) throws ApplicationException {
		if (model == null) return null;
		CustomerCategoryNotificationChannelResponse customerCategoryNotificationChannelResponse = new CustomerCategoryNotificationChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerCategoryNotificationChannelResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getCustomerCategory() != null)
			customerCategoryNotificationChannelResponse.setCustomerCategoryId(model.getCustomerCategory().getId());
			customerCategoryNotificationChannelResponse.setCustomerCategoryText(model.getCustomerCategory().getName());
		if(model.getDataChannelCategory() != null)
			customerCategoryNotificationChannelResponse.setDataChannelCategoryId(model.getDataChannelCategory().getId());
			customerCategoryNotificationChannelResponse.setDataChannelCategoryText(model.getDataChannelCategory().getName());
		return customerCategoryNotificationChannelResponse;
	}
}
