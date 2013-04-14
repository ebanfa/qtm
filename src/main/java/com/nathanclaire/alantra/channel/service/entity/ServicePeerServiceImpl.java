/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.ServicePeer;
import com.nathanclaire.alantra.channel.rest.request.ServicePeerRequest;

import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;
import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.rest.request.HostRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ServicePeerServiceImpl extends BaseEntityServiceImpl<ServicePeer> implements ServicePeerService
{
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
	public ServicePeer createInstance(BaseRequest servicePeerRequest) {
		return createInsance(servicePeerRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#deleteServicePeer(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServicePeer#updateServicePeer(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServicePeer updateInstance(BaseRequest servicePeerRequest) {
		return updateInstance(servicePeerRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ServicePeer loadModelFromRequest(BaseRequest request) 
    {
    	ServicePeerRequest servicePeerRequest = (ServicePeerRequest) request;
		ServicePeer servicePeer = new ServicePeer();
    	Integer servicePeerId = servicePeerRequest.getId();
    	// Are we editing a ServicePeer
    	if(servicePeerId != null) 
    	{
    		servicePeer = getEntityManager().find(ServicePeer.class, servicePeerRequest.getId());
    		servicePeer.setLastModifiedDt(servicePeerRequest.getLastModifiedDt());
        	servicePeer.setLastModifiedUsr(getCurrentUserName(servicePeerRequest));
    	}
    	else
    	{
        	servicePeer.setCreatedDt(getCurrentSystemDate());
        	servicePeer.setCreatedByUsr(getCurrentUserName(servicePeerRequest));
    	}
    	servicePeer.setCode(servicePeerRequest.getCode());
    	servicePeer.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (servicePeerRequest.getService() != null)
    	{
    		Service service = getEntityManager().find(Service.class, servicePeerRequest.getService());
    		servicePeer.setService(service);
    	}
    	if (servicePeerRequest.getHost() != null)
    	{
    		Host host = getEntityManager().find(Host.class, servicePeerRequest.getHost());
    		servicePeer.setHost(host);
    	}
    	servicePeer.setName(servicePeerRequest.getName()); 
    	servicePeer.setDescription(servicePeerRequest.getDescription()); 
    	servicePeer.setCode(servicePeerRequest.getCode()); 
    	servicePeer.setEffectiveDt(servicePeerRequest.getEffectiveDt()); 
    	servicePeer.setRecSt(servicePeerRequest.getRecSt()); 
		return servicePeer;
	}
}
