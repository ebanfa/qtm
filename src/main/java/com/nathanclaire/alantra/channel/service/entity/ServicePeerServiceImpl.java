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

import com.nathanclaire.alantra.channel.model.ServicePeer;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.request.ServicePeerRequest;
import com.nathanclaire.alantra.channel.response.ServicePeerResponse;
import com.nathanclaire.alantra.channel.service.entity.ServiceService;
import com.nathanclaire.alantra.channel.service.entity.HostService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class ServicePeerServiceImpl 
	extends BaseEntityServiceImpl<ServicePeer, ServicePeerResponse, ServicePeerRequest> 
	implements ServicePeerService
{
	private static final String LIST_ITEM_SERVICE = "service";
	private static final String LIST_ITEM_HOST = "host";
	private static final String ENTITY_NAME = "ServicePeer";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_SERVICEPEER";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_SERVICEPEER";
	
	private Logger logger = LoggerFactory.getLogger(ServicePeerServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceService  serviceService;
	@Inject
	HostService  hostService;
	
	/**
	 * @param entityClass
	 */
	public ServicePeerServiceImpl() {
		super(ServicePeer.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#findById(java.lang.Integer)
	 */
	@Override
	public ServicePeer findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#findByCode(java.lang.String)
	 */
	@Override
	public ServicePeer findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#findByName(java.lang.String)
	 */
	@Override
	public ServicePeer findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#findAll(java.util.Map)
	 */
	@Override
	public List<ServicePeer> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#createServicePeer(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServicePeer create(ServicePeerRequest servicePeerRequest) {
		return createInstance(servicePeerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#deleteServicePeer(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#updateServicePeer(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServicePeer update(ServicePeerRequest servicePeerRequest) {
		return updateInstance(servicePeerRequest);
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
		List<ListItemResponse> services = serviceService.asListItem();
		List<ListItemResponse> hosts = hostService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICE, services); 
		listItems.put(LIST_ITEM_HOST, hosts); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(ServicePeer servicepeer: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(servicepeer.getId(), servicepeer.getCode(), servicepeer.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public ServicePeer convertRequestToModel(ServicePeerRequest servicePeerRequest) 
    {
		ServicePeer servicePeer = new ServicePeer();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(servicePeerRequest, servicePeer, allowedEntityFields);
    	//Process many to one relationships
    	if (servicePeerRequest.getServiceId() != null)
    	{
    		Service service = getEntityManager().find(Service.class, servicePeerRequest.getServiceId());
    		servicePeer.setService(service);
    	}
    	if (servicePeerRequest.getHostId() != null)
    	{
    		Host host = getEntityManager().find(Host.class, servicePeerRequest.getHostId());
    		servicePeer.setHost(host);
    	}
		return servicePeer;
	}
	
	@Override
	public ServicePeerResponse convertModelToResponse(ServicePeer model) {
		if (model == null) return null;
		ServicePeerResponse servicePeerResponse = new ServicePeerResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, servicePeerResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getService() != null)
			servicePeerResponse.setServiceId(model.getService().getId());
		if(model.getHost() != null)
			servicePeerResponse.setHostId(model.getHost().getId());
		return servicePeerResponse;
	}
}
