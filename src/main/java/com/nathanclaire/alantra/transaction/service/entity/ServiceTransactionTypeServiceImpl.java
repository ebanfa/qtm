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

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.transaction.model.ServiceTransactionType;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionCategory;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionTypeResponse;
import com.nathanclaire.alantra.transaction.service.entity.ServiceTransactionCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceTransactionTypeServiceImpl 
	extends BaseEntityServiceImpl<ServiceTransactionType, ServiceTransactionTypeResponse, ServiceTransactionTypeRequest> 
	implements ServiceTransactionTypeService
{
	private static final String LIST_ITEM_SERVICETRANSACTIONCATEGORY = "serviceTransactionCategory";
	private static final String ENTITY_NAME = "ServiceTransactionType";
	private static final String LIST_ACTIVITY_CODE = "LIST_TRANSACTION_SERVICETRANSACTIONTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_TRANSACTION_SERVICETRANSACTIONTYPE";
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTransactionCategoryService  serviceTransactionCategoryService;
	
	/**
	 * @param entityClass
	 */
	public ServiceTransactionTypeServiceImpl() {
		super(ServiceTransactionType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransactionType findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransactionType> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#createServiceTransactionType(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionType create(ServiceTransactionTypeRequest serviceTransactionTypeRequest) throws ApplicationException {
		return createInstance(serviceTransactionTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#deleteServiceTransactionType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.transaction.service.ServiceTransactionType#updateServiceTransactionType(com.nathanclaire.alantra.transaction.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionType update(ServiceTransactionTypeRequest serviceTransactionTypeRequest) throws ApplicationException {
		return updateInstance(serviceTransactionTypeRequest);
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
		List<ListItemResponse> serviceTransactionCategorys = serviceTransactionCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETRANSACTIONCATEGORY, serviceTransactionCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(ServiceTransactionType servicetransactiontype: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicetransactiontype.getId(), servicetransactiontype.getCode(), servicetransactiontype.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceTransactionType convertRequestToModel(ServiceTransactionTypeRequest serviceTransactionTypeRequest) 
     throws ApplicationException {
		ServiceTransactionType serviceTransactionType = new ServiceTransactionType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(serviceTransactionTypeRequest, serviceTransactionType, allowedEntityFields);
    	//Process many to one relationships
    	if (serviceTransactionTypeRequest.getServiceTransactionCategoryId() != null)
    	{
    		ServiceTransactionCategory serviceTransactionCategory = getEntityManager().find(ServiceTransactionCategory.class, serviceTransactionTypeRequest.getServiceTransactionCategoryId());
    		serviceTransactionType.setServiceTransactionCategory(serviceTransactionCategory);
    	}
		return serviceTransactionType;
	}
	
	@Override
	public ServiceTransactionTypeResponse convertModelToResponse(ServiceTransactionType model) throws ApplicationException {
		if (model == null) return null;
		ServiceTransactionTypeResponse serviceTransactionTypeResponse = new ServiceTransactionTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceTransactionTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceTransactionCategory() != null)
			serviceTransactionTypeResponse.setServiceTransactionCategoryId(model.getServiceTransactionCategory().getId());
			serviceTransactionTypeResponse.setServiceTransactionCategoryText(model.getServiceTransactionCategory().getName());
		return serviceTransactionTypeResponse;
	}
}
