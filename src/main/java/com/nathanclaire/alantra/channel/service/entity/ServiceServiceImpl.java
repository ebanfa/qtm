/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.rest.request.BaseRequest;
import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.rest.request.ServiceRequest;

import com.nathanclaire.alantra.channel.model.ServiceType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTypeRequest;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.rest.request.ServiceProtocolAdapterRequest;
import com.nathanclaire.alantra.channel.model.ServiceMode;
import com.nathanclaire.alantra.channel.rest.request.ServiceModeRequest;
import com.nathanclaire.alantra.channel.model.ServiceCategory;
import com.nathanclaire.alantra.channel.rest.request.ServiceCategoryRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ServiceServiceImpl extends BaseEntityServiceImpl<Service> implements ServiceService
{
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
	public Service createInstance(BaseRequest serviceRequest) {
		return createInsance(serviceRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#deleteService(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.Service#updateService(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public Service updateInstance(BaseRequest serviceRequest) {
		return updateInstance(serviceRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected Service loadModelFromRequest(BaseRequest request) 
    {
    	ServiceRequest serviceRequest = (ServiceRequest) request;
		Service service = new Service();
    	Integer serviceId = serviceRequest.getId();
    	// Are we editing a Service
    	if(serviceId != null) 
    	{
    		service = getEntityManager().find(Service.class, serviceRequest.getId());
    		service.setLastModifiedDt(serviceRequest.getLastModifiedDt());
        	service.setLastModifiedUsr(getCurrentUserName(serviceRequest));
    	}
    	else
    	{
        	service.setCreatedDt(getCurrentSystemDate());
        	service.setCreatedByUsr(getCurrentUserName(serviceRequest));
    	}
    	service.setCode(serviceRequest.getCode());
    	service.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	if (serviceRequest.getServiceType() != null)
    	{
    		ServiceType serviceType = getEntityManager().find(ServiceType.class, serviceRequest.getServiceType());
    		service.setServiceType(serviceType);
    	}
    	if (serviceRequest.getServiceProtocolAdapter() != null)
    	{
    		ServiceProtocolAdapter serviceProtocolAdapter = getEntityManager().find(ServiceProtocolAdapter.class, serviceRequest.getServiceProtocolAdapter());
    		service.setServiceProtocolAdapter(serviceProtocolAdapter);
    	}
    	if (serviceRequest.getServiceMode() != null)
    	{
    		ServiceMode serviceMode = getEntityManager().find(ServiceMode.class, serviceRequest.getServiceMode());
    		service.setServiceMode(serviceMode);
    	}
    	if (serviceRequest.getServiceCategory() != null)
    	{
    		ServiceCategory serviceCategory = getEntityManager().find(ServiceCategory.class, serviceRequest.getServiceCategory());
    		service.setServiceCategory(serviceCategory);
    	}
    	service.setName(serviceRequest.getName()); 
    	service.setDescription(serviceRequest.getDescription()); 
    	service.setPortNo(serviceRequest.getPortNo()); 
    	service.setIpAddress(serviceRequest.getIpAddress()); 
    	service.setCode(serviceRequest.getCode()); 
    	service.setEffectiveDt(serviceRequest.getEffectiveDt()); 
    	service.setRecSt(serviceRequest.getRecSt()); 
		return service;
	}
}
