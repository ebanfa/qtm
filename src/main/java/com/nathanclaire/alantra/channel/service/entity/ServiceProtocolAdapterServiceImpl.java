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

import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.response.ServiceProtocolAdapterResponse;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceProtocolAdapterServiceImpl 
	extends BaseEntityServiceImpl<ServiceProtocolAdapter, ServiceProtocolAdapterResponse, ServiceProtocolAdapterRequest> 
	implements ServiceProtocolAdapterService
{
	private static final String ENTITY_NAME = "ServiceProtocolAdapter";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICEPROTOCOLADAPTER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICEPROTOCOLADAPTER";
	
	private Logger logger = LoggerFactory.getLogger(ServiceProtocolAdapterServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	
	/**
	 * @param entityClass
	 */
	public ServiceProtocolAdapterServiceImpl() {
		super(ServiceProtocolAdapter.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#findById(java.lang.Integer)
	 */
	@Override
	public ServiceProtocolAdapter findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#findByCode(java.lang.String)
	 */
	@Override
	public ServiceProtocolAdapter findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#findByName(java.lang.String)
	 */
	@Override
	public ServiceProtocolAdapter findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceProtocolAdapter> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#createServiceProtocolAdapter(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceProtocolAdapter create(ServiceProtocolAdapterRequest serviceProtocolAdapterRequest) {
		return createInstance(serviceProtocolAdapterRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#deleteServiceProtocolAdapter(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceProtocolAdapter#updateServiceProtocolAdapter(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceProtocolAdapter update(ServiceProtocolAdapterRequest serviceProtocolAdapterRequest) {
		return updateInstance(serviceProtocolAdapterRequest);
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
		for(ServiceProtocolAdapter serviceprotocoladapter: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(serviceprotocoladapter.getId(), serviceprotocoladapter.getCode(), serviceprotocoladapter.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServiceProtocolAdapter convertRequestToModel(ServiceProtocolAdapterRequest serviceProtocolAdapterRequest) 
    {
		ServiceProtocolAdapter serviceProtocolAdapter = new ServiceProtocolAdapter();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(serviceProtocolAdapterRequest, serviceProtocolAdapter, allowedEntityFields);
    	//Process many to one relationships
		return serviceProtocolAdapter;
	}
	
	@Override
	public ServiceProtocolAdapterResponse convertModelToResponse(ServiceProtocolAdapter model) {
		if (model == null) return null;
		ServiceProtocolAdapterResponse serviceProtocolAdapterResponse = new ServiceProtocolAdapterResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceProtocolAdapterResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		return serviceProtocolAdapterResponse;
	}
}
