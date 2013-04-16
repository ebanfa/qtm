/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.ServiceProtocolAdapter;
import com.nathanclaire.alantra.channel.request.ServiceProtocolAdapterRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ServiceProtocolAdapterServiceImpl extends BaseEntityServiceImpl<ServiceProtocolAdapter, ServiceProtocolAdapterRequest> implements ServiceProtocolAdapterService
{
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
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ServiceProtocolAdapter loadModelFromRequest(ServiceProtocolAdapterRequest serviceProtocolAdapterRequest) 
    {
		ServiceProtocolAdapter serviceProtocolAdapter = new ServiceProtocolAdapter();
    	Integer serviceProtocolAdapterId = serviceProtocolAdapterRequest.getId();
    	// Are we editing a ServiceProtocolAdapter
    	if(serviceProtocolAdapterId != null) 
    	{
    		serviceProtocolAdapter = getEntityManager().find(ServiceProtocolAdapter.class, serviceProtocolAdapterRequest.getId());
    		serviceProtocolAdapter.setLastModifiedDt(serviceProtocolAdapterRequest.getLastModifiedDt());
        	serviceProtocolAdapter.setLastModifiedUsr(getCurrentUserName(serviceProtocolAdapterRequest));
    	}
    	else
    	{
        	serviceProtocolAdapter.setCreatedDt(getCurrentSystemDate());
        	serviceProtocolAdapter.setCreatedByUsr(getCurrentUserName(serviceProtocolAdapterRequest));
    	}
    	serviceProtocolAdapter.setCode(serviceProtocolAdapterRequest.getCode());
    	serviceProtocolAdapter.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	serviceProtocolAdapter.setName(serviceProtocolAdapterRequest.getName()); 
    	serviceProtocolAdapter.setDescription(serviceProtocolAdapterRequest.getDescription()); 
    	serviceProtocolAdapter.setClassName(serviceProtocolAdapterRequest.getClassName()); 
    	serviceProtocolAdapter.setCode(serviceProtocolAdapterRequest.getCode()); 
    	serviceProtocolAdapter.setEffectiveDt(serviceProtocolAdapterRequest.getEffectiveDt()); 
    	serviceProtocolAdapter.setRecSt(serviceProtocolAdapterRequest.getRecSt()); 
		return serviceProtocolAdapter;
	}
}
