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

import com.nathanclaire.alantra.customer.model.CustomerTypeTransactionLimit;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.customer.model.LimitType;
import com.nathanclaire.alantra.customer.model.CustomerType;
import com.nathanclaire.alantra.customer.request.CustomerTypeTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerTypeTransactionLimitResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.customer.service.entity.LimitTypeService;
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
public class CustomerTypeTransactionLimitServiceImpl 
	extends BaseEntityServiceImpl<CustomerTypeTransactionLimit, CustomerTypeTransactionLimitResponse, CustomerTypeTransactionLimitRequest> 
	implements CustomerTypeTransactionLimitService
{
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String LIST_ITEM_LIMITTYPE = "limitType";
	private static final String LIST_ITEM_CUSTOMERTYPE = "customerType";
	private static final String ENTITY_NAME = "CustomerTypeTransactionLimit";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERTYPETRANSACTIONLIMIT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERTYPETRANSACTIONLIMIT";
	
	private Logger logger = LoggerFactory.getLogger(CustomerTypeTransactionLimitServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	@Inject
	LimitTypeService  limitTypeService;
	@Inject
	CustomerTypeService  customerTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerTypeTransactionLimitServiceImpl() {
		super(CustomerTypeTransactionLimit.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#findById(java.lang.Integer)
	 */
	@Override
	public CustomerTypeTransactionLimit findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#findByCode(java.lang.String)
	 */
	@Override
	public CustomerTypeTransactionLimit findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#findByName(java.lang.String)
	 */
	@Override
	public CustomerTypeTransactionLimit findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerTypeTransactionLimit> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#createCustomerTypeTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerTypeTransactionLimit create(CustomerTypeTransactionLimitRequest customerTypeTransactionLimitRequest) throws ApplicationException {
		return createInstance(customerTypeTransactionLimitRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#deleteCustomerTypeTransactionLimit(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerTypeTransactionLimit#updateCustomerTypeTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerTypeTransactionLimit update(CustomerTypeTransactionLimitRequest customerTypeTransactionLimitRequest) throws ApplicationException {
		return updateInstance(customerTypeTransactionLimitRequest);
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
		List<ListItemResponse> limitTypes = limitTypeService.asListItem();
		List<ListItemResponse> customerTypes = customerTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
		listItems.put(LIST_ITEM_LIMITTYPE, limitTypes); 
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
		for(CustomerTypeTransactionLimit customertypetransactionlimit: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customertypetransactionlimit.getId(), customertypetransactionlimit.getCode(), customertypetransactionlimit.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerTypeTransactionLimit convertRequestToModel(CustomerTypeTransactionLimitRequest customerTypeTransactionLimitRequest) 
     throws ApplicationException {
		CustomerTypeTransactionLimit customerTypeTransactionLimit = new CustomerTypeTransactionLimit();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerTypeTransactionLimitRequest, customerTypeTransactionLimit, allowedEntityFields);
    	//Process many to one relationships
    	if (customerTypeTransactionLimitRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, customerTypeTransactionLimitRequest.getServiceTransactionTypeId());
    		customerTypeTransactionLimit.setServiceTransactionType(serviceTransactionType);
    	}
    	if (customerTypeTransactionLimitRequest.getLimitTypeId() != null)
    	{
    		LimitType limitType = getEntityManager().find(LimitType.class, customerTypeTransactionLimitRequest.getLimitTypeId());
    		customerTypeTransactionLimit.setLimitType(limitType);
    	}
    	if (customerTypeTransactionLimitRequest.getCustomerTypeId() != null)
    	{
    		CustomerType customerType = getEntityManager().find(CustomerType.class, customerTypeTransactionLimitRequest.getCustomerTypeId());
    		customerTypeTransactionLimit.setCustomerType(customerType);
    	}
		return customerTypeTransactionLimit;
	}
	
	@Override
	public CustomerTypeTransactionLimitResponse convertModelToResponse(CustomerTypeTransactionLimit model) throws ApplicationException {
		if (model == null) return null;
		CustomerTypeTransactionLimitResponse customerTypeTransactionLimitResponse = new CustomerTypeTransactionLimitResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerTypeTransactionLimitResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceTransactionType() != null)
			customerTypeTransactionLimitResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			customerTypeTransactionLimitResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		if(model.getLimitType() != null)
			customerTypeTransactionLimitResponse.setLimitTypeId(model.getLimitType().getId());
			customerTypeTransactionLimitResponse.setLimitTypeText(model.getLimitType().getName());
		if(model.getCustomerType() != null)
			customerTypeTransactionLimitResponse.setCustomerTypeId(model.getCustomerType().getId());
			customerTypeTransactionLimitResponse.setCustomerTypeText(model.getCustomerType().getName());
		return customerTypeTransactionLimitResponse;
	}
}
