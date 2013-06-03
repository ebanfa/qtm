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
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.application.model.ApplicationEntityField;

import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.model.HostType;
import com.nathanclaire.alantra.channel.request.HostRequest;
import com.nathanclaire.alantra.channel.response.HostResponse;
import com.nathanclaire.alantra.channel.service.entity.ServiceProtocolAdapterService;
import com.nathanclaire.alantra.channel.service.entity.HostTypeService;
import com.nathanclaire.alantra.application.service.entity.ApplicationEntityService;
import com.nathanclaire.alantra.base.response.ListItemResponse;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class HostServiceImpl 
	extends BaseEntityServiceImpl<Host, HostResponse, HostRequest> 
	implements HostService
{
	private static final String LIST_ITEM_SERVICEPROTOCOLADAPTER = "serviceProtocolAdapter";
	private static final String LIST_ITEM_HOSTTYPE = "hostType";
	private static final String ENTITY_NAME = "Host";
	private static final String LIST_ACTIVITY_CODE = "LIST_CHANNEL_HOST";
	private static final String EDIT_ACTIVITY_CODE = "EDIT_CHANNEL_HOST";
	
	private Logger logger = LoggerFactory.getLogger(HostServiceImpl.class);
	
	@Inject
	ApplicationEntityService  applicationEntityService;
	@Inject
	ServiceProtocolAdapterService  serviceProtocolAdapterService;
	@Inject
	HostTypeService  hostTypeService;
	
	/**
	 * @param entityClass
	 */
	public HostServiceImpl() {
		super(Host.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findById(java.lang.Integer)
	 */
	@Override
	public Host findById(Integer id) throws ApplicationException {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findByCode(java.lang.String)
	 */
	@Override
	public Host findByCode(String code) throws ApplicationException {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findByName(java.lang.String)
	 */
	@Override
	public Host findByName(String name) throws ApplicationException {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findAll(java.util.Map)
	 */
	@Override
	public List<Host> findAll(MultivaluedMap<String, String> queryParameters) throws ApplicationException {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#createHost(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Host create(HostRequest hostRequest) throws ApplicationException {
		return createInstance(hostRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#deleteHost(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ApplicationException {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#updateHost(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Host update(HostRequest hostRequest) throws ApplicationException {
		return updateInstance(hostRequest);
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
		List<ListItemResponse> serviceProtocolAdapters = serviceProtocolAdapterService.asListItem();
		List<ListItemResponse> hostTypes = hostTypeService.asListItem();
    	
		listItems.put(LIST_ITEM_SERVICEPROTOCOLADAPTER, serviceProtocolAdapters); 
		listItems.put(LIST_ITEM_HOSTTYPE, hostTypes); 
		return listItems;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.entity.BaseEntityService#asListItem()
	 */
	@Override
	public List<ListItemResponse> asListItem() throws ApplicationException {
		List<ListItemResponse> listItems = new ArrayList<ListItemResponse>();
		queryParameters.clear();
		for(Host host: findAll(queryParameters))
		{
			ListItemResponse item = new ListItemResponse(host.getId(), host.getCode(), host.getName());
			listItems.add(item);
		}
		return listItems;
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    public Host convertRequestToModel(HostRequest hostRequest) 
     throws ApplicationException {
		Host host = new Host();
		// Copy properties
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(hostRequest, host, allowedEntityFields);
    	//Process many to one relationships
    	if (hostRequest.getServiceProtocolAdapterId() != null)
    	{
    		ServiceProtocolAdapter serviceProtocolAdapter = getEntityManager().find(ServiceProtocolAdapter.class, hostRequest.getServiceProtocolAdapterId());
    		host.setServiceProtocolAdapter(serviceProtocolAdapter);
    	}
    	if (hostRequest.getHostTypeId() != null)
    	{
    		HostType hostType = getEntityManager().find(HostType.class, hostRequest.getHostTypeId());
    		host.setHostType(hostType);
    	}
		return host;
	}
	
	@Override
	public HostResponse convertModelToResponse(Host model) throws ApplicationException {
		if (model == null) return null;
		HostResponse hostResponse = new HostResponse();
		List<ApplicationEntityField> allowedEntityFields = this.getEntityFields();
		PropertyUtils.copyProperties(model, hostResponse, allowedEntityFields);
		// Set the value of the response to the value of the id of the related Entity
		if(model.getServiceProtocolAdapter() != null)
			hostResponse.setServiceProtocolAdapterId(model.getServiceProtocolAdapter().getId());
			hostResponse.setServiceProtocolAdapterText(model.getServiceProtocolAdapter().getName());
		if(model.getHostType() != null)
			hostResponse.setHostTypeId(model.getHostType().getId());
			hostResponse.setHostTypeText(model.getHostType().getName());
		return hostResponse;
	}
}
