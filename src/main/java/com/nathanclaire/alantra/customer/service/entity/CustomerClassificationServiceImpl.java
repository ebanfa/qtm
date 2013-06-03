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

import com.nathanclaire.alantra.customer.model.CustomerClassification;
import com.nathanclaire.alantra.customer.request.CustomerClassificationRequest;
import com.nathanclaire.alantra.customer.response.CustomerClassificationResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class CustomerClassificationServiceImpl 
	extends BaseEntityServiceImpl<CustomerClassification, CustomerClassificationResponse, CustomerClassificationRequest> 
	implements CustomerClassificationService
{
	private static final String ENTITY_NAME = "CustomerClassification";
	private static final String LIST_ACTIVITY_CODE = "LIST_CUSTOMER_CUSTOMERCLASSIFICATION";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CUSTOMER_CUSTOMERCLASSIFICATION";
	
	private Logger logger = LoggerFactory.getLogger(CustomerClassificationServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public CustomerClassificationServiceImpl() {
		super(CustomerClassification.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#findById(java.lang.Integer)
	 */
	@Override
	public CustomerClassification findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#findByCode(java.lang.String)
	 */
	@Override
	public CustomerClassification findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#findByName(java.lang.String)
	 */
	@Override
	public CustomerClassification findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#findAll(java.util.Map)
	 */
	@Override
	public List<CustomerClassification> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#createCustomerClassification(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerClassification create(CustomerClassificationRequest customerClassificationRequest) throws ApplicationException {
		return createInstance(customerClassificationRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#deleteCustomerClassification(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.customer.service.CustomerClassification#updateCustomerClassification(com.nathanclaire.alantra.customer.rest.request.ServiceRequest)
	 */
	@Override
	public CustomerClassification update(CustomerClassificationRequest customerClassificationRequest) throws ApplicationException {
		return updateInstance(customerClassificationRequest);
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
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(CustomerClassification customerclassification: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(customerclassification.getId(), customerclassification.getCode(), customerclassification.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public CustomerClassification convertRequestToModel(CustomerClassificationRequest customerClassificationRequest) 
     throws ApplicationException {
		CustomerClassification customerClassification = new CustomerClassification();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(customerClassificationRequest, customerClassification, allowedEntityFields);
    	//Process many to one relationships
		return customerClassification;
	}
	
	@Override
	public CustomerClassificationResponse convertModelToResponse(CustomerClassification model) throws ApplicationException {
		if (model == null) return null;
		CustomerClassificationResponse customerClassificationResponse = new CustomerClassificationResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, customerClassificationResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return customerClassificationResponse;
	}
}
