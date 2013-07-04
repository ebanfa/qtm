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

import com.nathanclaire.alantra.customer.model.CustomerTypeNotificationChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerTypeNotificationChannelRequest;
import com.nathanclaire.alantra.customer.response.CustomerTypeNotificationChannelResponse;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
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
public class CustomerTypeNotificationChannelServiceImpl 
	extends BaseEntityServiceImpl<CustomerTypeNotificationChannel, CustomerTypeNotificationChannelResponse, CustomerTypeNotificationChannelRequest> 
	implements CustomerTypeNotificationChannelService
{
	private static final String LIST_ITEM_DATACHANNELCATEGORY = "dataChannelCategory";
	private static final String LIST_ITEM_CUSTOMERTYPE = "customerType";
	private static final String ENTITY_NAME = "CustomerTypeNotificationChannel";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERTYPENOTIFICATIONCHANNEL";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERTYPENOTIFICATIONCHANNEL";
	
	private Logger logger = LoggerFactory.getLogger(CustomerTypeNotificationChannelServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	DataChannelCategoryService  dataChannelCategoryService;
	@Inject
	CustomerTypeService  customerTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerTypeNotificationChannelServiceImpl() {
		super(CustomerTypeNotificationChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#findById(java.lang.Integer)
	 */
	@Override
	public CustomerTypeNotificationChannel findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#findByCode(java.lang.String)
	 */
	@Override
	public CustomerTypeNotificationChannel findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#findByName(java.lang.String)
	 */
	@Override
	public CustomerTypeNotificationChannel findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerTypeNotificationChannel> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#createCustomerTypeNotificationChannel(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerTypeNotificationChannel create(CustomerTypeNotificationChannelRequest customerTypeNotificationChannelRequest) throws ApplicationException {
		return createInstance(customerTypeNotificationChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#deleteCustomerTypeNotificationChannel(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeNotificationChannel#updateCustomerTypeNotificationChannel(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerTypeNotificationChannel update(CustomerTypeNotificationChannelRequest customerTypeNotificationChannelRequest) throws ApplicationException {
		return updateInstance(customerTypeNotificationChannelRequest);
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
		List<ListItemResponse> customerTypes = customerTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_DATACHANNELCATEGORY, dataChannelCategorys); 
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
		for(CustomerTypeNotificationChannel customertypenotificationchannel: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customertypenotificationchannel.getId(), customertypenotificationchannel.getCode(), customertypenotificationchannel.getCode());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerTypeNotificationChannel convertRequestToModel(CustomerTypeNotificationChannelRequest customerTypeNotificationChannelRequest) 
     throws ApplicationException {
		CustomerTypeNotificationChannel customerTypeNotificationChannel = new CustomerTypeNotificationChannel();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerTypeNotificationChannelRequest, customerTypeNotificationChannel, allowedEntityFields);
    	//Process many to one relationships
    	if (customerTypeNotificationChannelRequest.getDataChannelCategoryId() != null)
    	{
    		DataChannelCategory dataChannelCategory = getEntityManager().find(DataChannelCategory.class, customerTypeNotificationChannelRequest.getDataChannelCategoryId());
    		customerTypeNotificationChannel.setDataChannelCategory(dataChannelCategory);
    	}
    	if (customerTypeNotificationChannelRequest.getCustomerTypeId() != null)
    	{
    		CustomerType customerType = getEntityManager().find(CustomerType.class, customerTypeNotificationChannelRequest.getCustomerTypeId());
    		customerTypeNotificationChannel.setCustomerType(customerType);
    	}
		return customerTypeNotificationChannel;
	}
	
	@Override
	public CustomerTypeNotificationChannelResponse convertModelToResponse(CustomerTypeNotificationChannel model) throws ApplicationException {
		if (model == null) return null;
		CustomerTypeNotificationChannelResponse customerTypeNotificationChannelResponse = new CustomerTypeNotificationChannelResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerTypeNotificationChannelResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getDataChannelCategory() != null)
			customerTypeNotificationChannelResponse.setDataChannelCategoryId(model.getDataChannelCategory().getId());
			customerTypeNotificationChannelResponse.setDataChannelCategoryText(model.getDataChannelCategory().getName());
		if(model.getCustomerType() != null)
			customerTypeNotificationChannelResponse.setCustomerTypeId(model.getCustomerType().getId());
			customerTypeNotificationChannelResponse.setCustomerTypeText(model.getCustomerType().getName());
		return customerTypeNotificationChannelResponse;
	}
}
