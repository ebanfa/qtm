/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.businessdata.model.ServiceChannel;
import com.nathanclaire.alantra.businessdata.rest.request.ServiceChannelRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ServiceChannelServiceImpl extends BaseEntityServiceImpl<ServiceChannel> implements ServiceChannelService
{
	/**
	 * @param entityClass
	 */
	public ServiceChannelServiceImpl() {
		super(ServiceChannel.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#findById(java.lang.Integer)
	 */
	@Override
	public ServiceChannel findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#findByCode(java.lang.String)
	 */
	@Override
	public ServiceChannel findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#findByName(java.lang.String)
	 */
	@Override
	public ServiceChannel findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceChannel> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#createServiceChannel(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceChannel createInstance(BaseRequest serviceChannelRequest) {
		return createInsance(serviceChannelRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#deleteServiceChannel(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.ServiceChannel#updateServiceChannel(com.nathanclaire.alantra.businessdata.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceChannel updateInstance(BaseRequest serviceChannelRequest) {
		return updateInstance(serviceChannelRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ServiceChannel loadModelFromRequest(BaseRequest request) 
    {
    	ServiceChannelRequest serviceChannelRequest = (ServiceChannelRequest) request;
		ServiceChannel serviceChannel = new ServiceChannel();
    	Integer serviceChannelId = serviceChannelRequest.getId();
    	// Are we editing a ServiceChannel
    	if(serviceChannelId != null) 
    	{
    		serviceChannel = getEntityManager().find(ServiceChannel.class, serviceChannelRequest.getId());
    		serviceChannel.setLastModifiedDt(serviceChannelRequest.getLastModifiedDt());
        	serviceChannel.setLastModifiedUsr(getCurrentUserName(serviceChannelRequest));
    	}
    	else
    	{
        	serviceChannel.setCreatedDt(getCurrentSystemDate());
        	serviceChannel.setCreatedByUsr(getCurrentUserName(serviceChannelRequest));
    	}
    	serviceChannel.setCode(serviceChannelRequest.getCode());
    	serviceChannel.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	serviceChannel.setName(serviceChannelRequest.getName()); 
    	serviceChannel.setDescription(serviceChannelRequest.getDescription()); 
    	serviceChannel.setCode(serviceChannelRequest.getCode()); 
    	serviceChannel.setEffectiveDt(serviceChannelRequest.getEffectiveDt()); 
    	serviceChannel.setRecSt(serviceChannelRequest.getRecSt()); 
		return serviceChannel;
	}
}
