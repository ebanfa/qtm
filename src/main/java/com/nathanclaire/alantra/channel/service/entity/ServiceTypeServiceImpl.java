/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.ServiceType;
import com.nathanclaire.alantra.channel.request.ServiceTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ServiceTypeServiceImpl extends BaseEntityServiceImpl<ServiceType, ServiceTypeRequest> implements ServiceTypeService
{
	/**
	 * @param entityClass
	 */
	public ServiceTypeServiceImpl() {
		super(ServiceType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#findById(java.lang.Integer)
	 */
	@Override
	public ServiceType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#findByCode(java.lang.String)
	 */
	@Override
	public ServiceType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#findByName(java.lang.String)
	 */
	@Override
	public ServiceType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#createServiceType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceType create(ServiceTypeRequest serviceTypeRequest) {
		return createInstance(serviceTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#deleteServiceType(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceType#updateServiceType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceType update(ServiceTypeRequest serviceTypeRequest) {
		return updateInstance(serviceTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ServiceType loadModelFromRequest(ServiceTypeRequest serviceTypeRequest) 
    {
		ServiceType serviceType = new ServiceType();
    	Integer serviceTypeId = serviceTypeRequest.getId();
    	// Are we editing a ServiceType
    	if(serviceTypeId != null) 
    	{
    		serviceType = getEntityManager().find(ServiceType.class, serviceTypeRequest.getId());
    		serviceType.setLastModifiedDt(serviceTypeRequest.getLastModifiedDt());
        	serviceType.setLastModifiedUsr(getCurrentUserName(serviceTypeRequest));
    	}
    	else
    	{
        	serviceType.setCreatedDt(getCurrentSystemDate());
        	serviceType.setCreatedByUsr(getCurrentUserName(serviceTypeRequest));
    	}
    	serviceType.setCode(serviceTypeRequest.getCode());
    	serviceType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	serviceType.setName(serviceTypeRequest.getName()); 
    	serviceType.setDescription(serviceTypeRequest.getDescription()); 
    	serviceType.setCode(serviceTypeRequest.getCode()); 
    	serviceType.setEffectiveDt(serviceTypeRequest.getEffectiveDt()); 
    	serviceType.setRecSt(serviceTypeRequest.getRecSt()); 
		return serviceType;
	}
}
