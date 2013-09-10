/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.application.model.ApplicationEntityField;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionCategory;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionCategoryRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionCategoryResponse;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceTransactionCategoryServiceImpl 
	extends BaseEntityServiceImpl<ServiceTransactionCategory, ServiceTransactionCategoryResponse, ServiceTransactionCategoryRequest> 
	implements ServiceTransactionCategoryService
{
	private static final String ENTITY_NAME = "ServiceTransactionCategory";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_SERVICETRANSACTIONCATEGORY";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_SERVICETRANSACTIONCATEGORY";
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionCategoryServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ServiceTransactionCategoryServiceImpl() {
		super(ServiceTransactionCategory.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransactionCategory findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransactionCategory findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransactionCategory findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransactionCategory> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#createServiceTransactionCategory(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionCategory create(ServiceTransactionCategoryRequest serviceTransactionCategoryRequest) throws ApplicationException {
		return createInstance(serviceTransactionCategoryRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#deleteServiceTransactionCategory(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionCategory#updateServiceTransactionCategory(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionCategory update(ServiceTransactionCategoryRequest serviceTransactionCategoryRequest) throws ApplicationException {
		return updateInstance(serviceTransactionCategoryRequest);
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
		for(ServiceTransactionCategory servicetransactioncategory: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicetransactioncategory.getId(), servicetransactioncategory.getCode(), servicetransactioncategory.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceTransactionCategory convertRequestToModel(ServiceTransactionCategoryRequest serviceTransactionCategoryRequest) 
     throws ApplicationException {
		ServiceTransactionCategory serviceTransactionCategory = new ServiceTransactionCategory();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(serviceTransactionCategoryRequest, serviceTransactionCategory, allowedEntityFields);
    	//Process many to one relationships
		return serviceTransactionCategory;
	}
	
	@Override
	public ServiceTransactionCategoryResponse convertModelToResponse(ServiceTransactionCategory model) throws ApplicationException {
		if (model == null) return null;
		ServiceTransactionCategoryResponse serviceTransactionCategoryResponse = new ServiceTransactionCategoryResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtil.copyProperties(model, serviceTransactionCategoryResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return serviceTransactionCategoryResponse;
	}
}
