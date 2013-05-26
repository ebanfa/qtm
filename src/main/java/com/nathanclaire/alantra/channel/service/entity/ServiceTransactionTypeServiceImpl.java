/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.channel.response.ServiceTransactionTypeResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
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
	private static final String ENTITY_NAME = "ServiceTransactionType";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICETRANSACTIONTYPE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICETRANSACTIONTYPE";
	
	private Logger logger = LoggerFactory.getLogger(ServiceTransactionTypeServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ServiceTransactionTypeServiceImpl() {
		super(ServiceTransactionType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransactionType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransactionType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#createServiceTransactionType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionType create(ServiceTransactionTypeRequest serviceTransactionTypeRequest) {
		return createInstance(serviceTransactionTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#deleteServiceTransactionType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#updateServiceTransactionType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionType update(ServiceTransactionTypeRequest serviceTransactionTypeRequest) {
		return updateInstance(serviceTransactionTypeRequest);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getListActivityCode()
	 */
	@Override
	public String getListActivityCode() {
		return LIST_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEditActivityCode()
	 */
	@Override
	public String getEditActivityCode() {
		return EDIT_ACTIVITY_CODE;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return ENTITY_NAME;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#getEntityFields()
	 */
	@Override
	public List<ApplicationEntityField> getEntityFields() {
		return applicationEntityService.getFieldsForEntity(ENTITY_NAME);
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#relatedEntitesToListItems()
	 */
	@Override
	public Map<String, List<ListItemResponse>> relatedEntitesToListItems() 
	{
		Map<String, List<ListItemResponse>> listItems = new HashMap<String, List<ListItemResponse>>(); 
    	
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
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
    {
		ServiceTransactionType serviceTransactionType = new ServiceTransactionType();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(serviceTransactionTypeRequest, serviceTransactionType, allowedEntityFields);
    	//Process many to one relationships
		return serviceTransactionType;
	}
	
	@Override
	public ServiceTransactionTypeResponse convertModelToResponse(ServiceTransactionType model) {
		if (model == null) return null;
		ServiceTransactionTypeResponse serviceTransactionTypeResponse = new ServiceTransactionTypeResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceTransactionTypeResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return serviceTransactionTypeResponse;
	}
}
