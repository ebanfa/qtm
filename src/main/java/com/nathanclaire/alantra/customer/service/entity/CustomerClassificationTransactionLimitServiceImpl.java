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

import com.nathanclaire.alantra.customer.model.CustomerClassificationTransactionLimit;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.model.LimitType;
import com.nathanclaire.alantra.customer.request.CustomerClassificationTransactionLimitRequest;
import com.nathanclaire.alantra.customer.response.CustomerClassificationTransactionLimitResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionTypeService;
import com.nathanclaire.alantra.customer.service.entity.CustomerClassificationService;
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
public class CustomerClassificationTransactionLimitServiceImpl 
	extends BaseEntityServiceImpl<CustomerClassificationTransactionLimit, CustomerClassificationTransactionLimitResponse, CustomerClassificationTransactionLimitRequest> 
	implements CustomerClassificationTransactionLimitService
{
	private static final String LIST_ITEM_SERVICETRANSACTIONTYPE = "serviceTransactionType";
	private static final String LIST_ITEM_CUSTOMERCLASSIFICATION = "customerClassification";
	private static final String LIST_ITEM_LIMITTYPE = "limitType";
	private static final String ENTITY_NAME = "CustomerClassificationTransactionLimit";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERCLASSIFICATIONTRANSACTIONLIMIT";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERCLASSIFICATIONTRANSACTIONLIMIT";
	
	private Logger logger = LoggerFactory.getLogger(CustomerClassificationTransactionLimitServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTransactionTypeService  serviceTransactionTypeService;
	@Inject
	CustomerClassificationService  customerClassificationService;
	@Inject
	LimitTypeService  limitTypeService;
	
	/**
	 * @param entityClass
	 */
	public CustomerClassificationTransactionLimitServiceImpl() {
		super(CustomerClassificationTransactionLimit.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#findById(java.lang.Integer)
	 */
	@Override
	public CustomerClassificationTransactionLimit findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#findByCode(java.lang.String)
	 */
	@Override
	public CustomerClassificationTransactionLimit findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#findByName(java.lang.String)
	 */
	@Override
	public CustomerClassificationTransactionLimit findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerClassificationTransactionLimit> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#createCustomerClassificationTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerClassificationTransactionLimit create(CustomerClassificationTransactionLimitRequest customerClassificationTransactionLimitRequest) throws ApplicationException {
		return createInstance(customerClassificationTransactionLimitRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#deleteCustomerClassificationTransactionLimit(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassificationTransactionLimit#updateCustomerClassificationTransactionLimit(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerClassificationTransactionLimit update(CustomerClassificationTransactionLimitRequest customerClassificationTransactionLimitRequest) throws ApplicationException {
		return updateInstance(customerClassificationTransactionLimitRequest);
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
		List<ListItemResponse> customerClassifications = customerClassificationService.asListItem();
		List<ListItemResponse> limitTypes = limitTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETRANSACTIONTYPE, serviceTransactionTypes); 
		listItems.put(LIST_ITEM_CUSTOMERCLASSIFICATION, customerClassifications); 
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
		for(CustomerClassificationTransactionLimit customerclassificationtransactionlimit: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customerclassificationtransactionlimit.getId(), customerclassificationtransactionlimit.getCode(), customerclassificationtransactionlimit.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerClassificationTransactionLimit convertRequestToModel(CustomerClassificationTransactionLimitRequest customerClassificationTransactionLimitRequest) 
     throws ApplicationException {
		CustomerClassificationTransactionLimit customerClassificationTransactionLimit = new CustomerClassificationTransactionLimit();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(customerClassificationTransactionLimitRequest, customerClassificationTransactionLimit, allowedEntityFields);
    	//Process many to one relationships
    	if (customerClassificationTransactionLimitRequest.getServiceTransactionTypeId() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, customerClassificationTransactionLimitRequest.getServiceTransactionTypeId());
    		customerClassificationTransactionLimit.setServiceTransactionType(serviceTransactionType);
    	}
    	if (customerClassificationTransactionLimitRequest.getCustomerClassificationId() != null)
    	{
    		CustomerClassification customerClassification = getEntityManager().find(CustomerClassification.class, customerClassificationTransactionLimitRequest.getCustomerClassificationId());
    		customerClassificationTransactionLimit.setCustomerClassification(customerClassification);
    	}
    	if (customerClassificationTransactionLimitRequest.getLimitTypeId() != null)
    	{
    		LimitType limitType = getEntityManager().find(LimitType.class, customerClassificationTransactionLimitRequest.getLimitTypeId());
    		customerClassificationTransactionLimit.setLimitType(limitType);
    	}
		return customerClassificationTransactionLimit;
	}
	
	@Override
	public CustomerClassificationTransactionLimitResponse convertModelToResponse(CustomerClassificationTransactionLimit model) throws ApplicationException {
		if (model == null) return null;
		CustomerClassificationTransactionLimitResponse customerClassificationTransactionLimitResponse = new CustomerClassificationTransactionLimitResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, customerClassificationTransactionLimitResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceTransactionType() != null)
			customerClassificationTransactionLimitResponse.setServiceTransactionTypeId(model.getServiceTransactionType().getId());
			customerClassificationTransactionLimitResponse.setServiceTransactionTypeText(model.getServiceTransactionType().getName());
		if(model.getCustomerClassification() != null)
			customerClassificationTransactionLimitResponse.setCustomerClassificationId(model.getCustomerClassification().getId());
			customerClassificationTransactionLimitResponse.setCustomerClassificationText(model.getCustomerClassification().getName());
		if(model.getLimitType() != null)
			customerClassificationTransactionLimitResponse.setLimitTypeId(model.getLimitType().getId());
			customerClassificationTransactionLimitResponse.setLimitTypeText(model.getLimitType().getName());
		return customerClassificationTransactionLimitResponse;
	}
}
