/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.ServiceMode;
import com.nathanclaire.alantra.channel.request.ServiceModeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ServiceModeServiceImpl extends BaseEntityServiceImpl<ServiceMode, ServiceModeRequest> implements ServiceModeService
{
	/**
	 * @param entityClass
	 */
	public ServiceModeServiceImpl() {
		super(ServiceMode.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findById(java.lang.Integer)
	 */
	@Override
	public ServiceMode findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findByCode(java.lang.String)
	 */
	@Override
	public ServiceMode findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findByName(java.lang.String)
	 */
	@Override
	public ServiceMode findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceMode> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#createServiceMode(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceMode create(ServiceModeRequest serviceModeRequest) {
		return createInstance(serviceModeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#deleteServiceMode(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceMode#updateServiceMode(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceMode update(ServiceModeRequest serviceModeRequest) {
		return updateInstance(serviceModeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ServiceMode loadModelFromRequest(ServiceModeRequest serviceModeRequest) 
    {
		ServiceMode serviceMode = new ServiceMode();
    	Integer serviceModeId = serviceModeRequest.getId();
    	// Are we editing a ServiceMode
    	if(serviceModeId != null) 
    	{
    		serviceMode = getEntityManager().find(ServiceMode.class, serviceModeRequest.getId());
    		serviceMode.setLastModifiedDt(serviceModeRequest.getLastModifiedDt());
        	serviceMode.setLastModifiedUsr(getCurrentUserName(serviceModeRequest));
    	}
    	else
    	{
        	serviceMode.setCreatedDt(getCurrentSystemDate());
        	serviceMode.setCreatedByUsr(getCurrentUserName(serviceModeRequest));
    	}
    	serviceMode.setCode(serviceModeRequest.getCode());
    	serviceMode.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	serviceMode.setName(serviceModeRequest.getName()); 
    	serviceMode.setDescription(serviceModeRequest.getDescription()); 
    	serviceMode.setCode(serviceModeRequest.getCode()); 
    	serviceMode.setEffectiveDt(serviceModeRequest.getEffectiveDt()); 
    	serviceMode.setRecSt(serviceModeRequest.getRecSt()); 
		return serviceMode;
	}
}
