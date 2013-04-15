/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionTypeRequest;


/**
 * @author administrator
 *
 */
@Stateless
public class ServiceTransactionTypeServiceImpl extends BaseEntityServiceImpl<ServiceTransactionType, ServiceTransactionTypeRequest> implements ServiceTransactionTypeService
{
	/**
	 * @param entityClass
	 */
	public ServiceTransactionTypeServiceImpl() {
		super(ServiceTransactionType.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransactionType findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransactionType findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransactionType> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#createServiceTransactionType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionType createInstance(ServiceTransactionTypeRequest serviceTransactionTypeRequest) {
		return createInsance(serviceTransactionTypeRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#deleteServiceTransactionType(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransactionType#updateServiceTransactionType(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransactionType updateInstance(ServiceTransactionTypeRequest serviceTransactionTypeRequest) {
		return updateInstance(serviceTransactionTypeRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ServiceTransactionType loadModelFromRequest(ServiceTransactionTypeRequest serviceTransactionTypeRequest) 
    {
		ServiceTransactionType serviceTransactionType = new ServiceTransactionType();
    	Integer serviceTransactionTypeId = serviceTransactionTypeRequest.getId();
    	// Are we editing a ServiceTransactionType
    	if(serviceTransactionTypeId != null) 
    	{
    		serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, serviceTransactionTypeRequest.getId());
    		serviceTransactionType.setLastModifiedDt(serviceTransactionTypeRequest.getLastModifiedDt());
        	serviceTransactionType.setLastModifiedUsr(getCurrentUserName(serviceTransactionTypeRequest));
    	}
    	else
    	{
        	serviceTransactionType.setCreatedDt(getCurrentSystemDate());
        	serviceTransactionType.setCreatedByUsr(getCurrentUserName(serviceTransactionTypeRequest));
    	}
    	serviceTransactionType.setCode(serviceTransactionTypeRequest.getCode());
    	serviceTransactionType.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	serviceTransactionType.setName(serviceTransactionTypeRequest.getName()); 
    	serviceTransactionType.setDescription(serviceTransactionTypeRequest.getDescription()); 
    	serviceTransactionType.setCode(serviceTransactionTypeRequest.getCode()); 
    	serviceTransactionType.setEffectiveDt(serviceTransactionTypeRequest.getEffectiveDt()); 
    	serviceTransactionType.setRecSt(serviceTransactionTypeRequest.getRecSt()); 
		return serviceTransactionType;
	}
}
