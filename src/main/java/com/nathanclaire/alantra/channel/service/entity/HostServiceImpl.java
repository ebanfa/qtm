/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.Host;
import com.nathanclaire.alantra.channel.rest.request.HostRequest;

import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.rest.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.model.HostType;
import com.nathanclaire.alantra.channel.rest.request.HostTypeRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class HostServiceImpl extends BaseEntityServiceImpl<Host> implements HostService
{
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
	public Host findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findByCode(java.lang.String)
	 */
	@Override
	public Host findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findByName(java.lang.String)
	 */
	@Override
	public Host findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#findAll(java.util.Map)
	 */
	@Override
	public List<Host> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#createHost(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Host createInstance(BaseRequest hostRequest) {
		return createInsance(hostRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#deleteHost(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Host#updateHost(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Host updateInstance(BaseRequest hostRequest) {
		return updateInstance(hostRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected Host loadModelFromRequest(BaseRequest request) 
    {
    	HostRequest hostRequest = (HostRequest) request;
		Host host = new Host();
    	Integer hostId = hostRequest.getId();
    	// Are we editing a Host
    	if(hostId != null) 
    	{
    		host = getEntityManager().find(Host.class, hostRequest.getId());
    		host.setLastModifiedDt(hostRequest.getLastModifiedDt());
        	host.setLastModifiedUsr(getCurrentUserName(hostRequest));
    	}
    	else
    	{
        	host.setCreatedDt(getCurrentSystemDate());
        	host.setCreatedByUsr(getCurrentUserName(hostRequest));
    	}
    	host.setCode(hostRequest.getCode());
    	host.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (hostRequest.getServiceProtocolAdapter() != null)
    	{
    		ServiceProtocolAdapter serviceProtocolAdapter = getEntityManager().find(ServiceProtocolAdapter.class, hostRequest.getServiceProtocolAdapter());
    		host.setServiceProtocolAdapter(serviceProtocolAdapter);
    	}
    	if (hostRequest.getHostType() != null)
    	{
    		HostType hostType = getEntityManager().find(HostType.class, hostRequest.getHostType());
    		host.setHostType(hostType);
    	}
    	host.setName(hostRequest.getName()); 
    	host.setDescription(hostRequest.getDescription()); 
    	host.setPortNo(hostRequest.getPortNo()); 
    	host.setIpAddress(hostRequest.getIpAddress()); 
    	host.setCode(hostRequest.getCode()); 
    	host.setEffectiveDt(hostRequest.getEffectiveDt()); 
    	host.setRecSt(hostRequest.getRecSt()); 
		return host;
	}
}
