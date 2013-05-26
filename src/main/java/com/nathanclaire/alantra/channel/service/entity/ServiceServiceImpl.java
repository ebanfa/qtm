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

import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceType;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.model.ServiceMode;
import com.nathanclaire.alantra.channel.model.ServiceCategory;
import com.nathanclaire.alantra.channel.request.ServiceRequest;
import com.nathanclaire.alantra.channel.response.ServiceResponse;
import com.nathanclaire.alantra.channel.service.entity.ServiceTypeService;
import com.nathanclaire.alantra.channel.service.entity.ServiceProtocolAdapterService;
import com.nathanclaire.alantra.channel.service.entity.ServiceModeService;
import com.nathanclaire.alantra.channel.service.entity.ServiceCategoryService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServiceServiceImpl 
	extends BaseEntityServiceImpl<Service, ServiceResponse, ServiceRequest> 
	implements ServiceService
{
	private static final String LIST_ITEM_SERVICETYPE = "serviceType";
	private static final String LIST_ITEM_SERVICEPROTOCOLADAPTER = "serviceProtocolAdapter";
	private static final String LIST_ITEM_SERVICEMODE = "serviceMode";
	private static final String LIST_ITEM_SERVICECATEGORY = "serviceCategory";
	private static final String ENTITY_NAME = "Service";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICE";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICE";
	
	private Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceTypeService  serviceTypeService;
	@Inject
	ServiceProtocolAdapterService  serviceProtocolAdapterService;
	@Inject
	ServiceModeService  serviceModeService;
	@Inject
	ServiceCategoryService  serviceCategoryService;
	
	/**
	 * @param entityClass
	 */
	public ServiceServiceImpl() {
		super(Service.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#findById(java.lang.Integer)
	 */
	@Override
	public Service findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#findByCode(java.lang.String)
	 */
	@Override
	public Service findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#findByName(java.lang.String)
	 */
	@Override
	public Service findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#findAll(java.util.Map)
	 */
	@Override
	public List<Service> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#createService(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Service create(ServiceRequest serviceRequest) {
		return createInstance(serviceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#deleteService(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#updateService(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Service update(ServiceRequest serviceRequest) {
		return updateInstance(serviceRequest);
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
		List<ListItemResponse> serviceTypes = serviceTypeService.asListItem();
		List<ListItemResponse> serviceProtocolAdapters = serviceProtocolAdapterService.asListItem();
		List<ListItemResponse> serviceModes = serviceModeService.asListItem();
		List<ListItemResponse> serviceCategorys = serviceCategoryService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICETYPE, serviceTypes); 
		listItems.put(LIST_ITEM_SERVICEPROTOCOLADAPTER, serviceProtocolAdapters); 
		listItems.put(LIST_ITEM_SERVICEMODE, serviceModes); 
		listItems.put(LIST_ITEM_SERVICECATEGORY, serviceCategorys); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Service service: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(service.getId(), service.getCode(), service.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Service convertRequestToModel(ServiceRequest serviceRequest) 
    {
		Service service = new Service();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(serviceRequest, service, allowedEntityFields);
    	//Process many to one relationships
    	if (serviceRequest.getServiceTypeId() != null)
    	{
    		ServiceType serviceType = getEntityManager().find(ServiceType.class, serviceRequest.getServiceTypeId());
    		service.setServiceType(serviceType);
    	}
    	if (serviceRequest.getServiceProtocolAdapterId() != null)
    	{
    		ServiceProtocolAdapter serviceProtocolAdapter = getEntityManager().find(ServiceProtocolAdapter.class, serviceRequest.getServiceProtocolAdapterId());
    		service.setServiceProtocolAdapter(serviceProtocolAdapter);
    	}
    	if (serviceRequest.getServiceModeId() != null)
    	{
    		ServiceMode serviceMode = getEntityManager().find(ServiceMode.class, serviceRequest.getServiceModeId());
    		service.setServiceMode(serviceMode);
    	}
    	if (serviceRequest.getServiceCategoryId() != null)
    	{
    		ServiceCategory serviceCategory = getEntityManager().find(ServiceCategory.class, serviceRequest.getServiceCategoryId());
    		service.setServiceCategory(serviceCategory);
    	}
		return service;
	}
	
	@Override
	public ServiceResponse convertModelToResponse(Service model) {
		if (model == null) return null;
		ServiceResponse serviceResponse = new ServiceResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, serviceResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceType() != null)
			serviceResponse.setServiceTypeId(model.getServiceType().getId());
		if(model.getServiceProtocolAdapter() != null)
			serviceResponse.setServiceProtocolAdapterId(model.getServiceProtocolAdapter().getId());
		if(model.getServiceMode() != null)
			serviceResponse.setServiceModeId(model.getServiceMode().getId());
		if(model.getServiceCategory() != null)
			serviceResponse.setServiceCategoryId(model.getServiceCategory().getId());
		return serviceResponse;
	}
}
