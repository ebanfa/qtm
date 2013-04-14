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
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ServiceTransactionServiceImpl extends BaseEntityServiceImpl<ServiceTransaction> implements ServiceTransactionService
{
	/**
	 * @param entityClass
	 */
	public ServiceTransactionServiceImpl() {
		super(ServiceTransaction.class);
	}

    /* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findById(java.lang.Integer)
	 */
	@Override
	public ServiceTransaction findById(Integer id) {
		return getSingleInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findByCode(java.lang.String)
	 */
	@Override
	public ServiceTransaction findByCode(String code) {
		return findInstanceByCode(code);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findByName(java.lang.String)
	 */
	@Override
	public ServiceTransaction findByName(String name) {
		return findInstanceByName(name);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#findAll(java.util.Map)
	 */
	@Override
	public List<ServiceTransaction> findAll(MultivaluedMap<String, String> queryParameters) {
		return findAllInstances(queryParameters);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#createServiceTransaction(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransaction createInstance(BaseRequest serviceTransactionRequest) {
		return createInsance(serviceTransactionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#deleteServiceTransaction(java.lang.Integer)
	 */
	@Override
	public void deleteInstance(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#updateServiceTransaction(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransaction updateInstance(BaseRequest serviceTransactionRequest) {
		return updateInstance(serviceTransactionRequest);
	}
	
	/**
     * @param request
     * @return
     */
    protected ServiceTransaction loadModelFromRequest(BaseRequest request) 
    {
    	ServiceTransactionRequest serviceTransactionRequest = (ServiceTransactionRequest) request;
		ServiceTransaction serviceTransaction = new ServiceTransaction();
    	Integer serviceTransactionId = serviceTransactionRequest.getId();
    	// Are we editing a ServiceTransaction
    	if(serviceTransactionId != null) 
    	{
    		serviceTransaction = getEntityManager().find(ServiceTransaction.class, serviceTransactionRequest.getId());
    		serviceTransaction.setLastModifiedDt(serviceTransactionRequest.getLastModifiedDt());
        	serviceTransaction.setLastModifiedUsr(getCurrentUserName(serviceTransactionRequest));
    	}
    	else
    	{
        	serviceTransaction.setCreatedDt(getCurrentSystemDate());
        	serviceTransaction.setCreatedByUsr(getCurrentUserName(serviceTransactionRequest));
    	}
    	serviceTransaction.setCode(serviceTransactionRequest.getCode());
    	serviceTransaction.setEffectiveDt(getCurrentSystemDate());
    	//Process many to one relationships
    	/*if (serviceTransactionRequest.getService() != null)
    	{
    		Service service = getEntityManager().find(Service.class, serviceTransactionRequest.getService());
    		serviceTransaction.setService(service);
    	}
    	if (serviceTransactionRequest.getServiceTransactionType() != null)
    	{
    		ServiceTransactionType serviceTransactionType = getEntityManager().find(ServiceTransactionType.class, serviceTransactionRequest.getServiceTransactionType());
    		serviceTransaction.setServiceTransactionType(serviceTransactionType);
    	}
    	serviceTransaction.setName(serviceTransactionRequest.getName()); 
    	serviceTransaction.setAmount(serviceTransactionRequest.getTransactionAmount()); 
    	serviceTransaction.setTxnDate(serviceTransactionRequest.getTxnDate()); 
    	serviceTransaction.setAccountNo(serviceTransactionRequest.getAccountNo()); 
    	serviceTransaction.setAccountNm(serviceTransactionRequest.getAccountNm()); 
    	serviceTransaction.setDescription(serviceTransactionRequest.getDescription()); 
    	serviceTransaction.setCode(serviceTransactionRequest.getCode()); 
    	serviceTransaction.setEffectiveDt(serviceTransactionRequest.getEffectiveDt()); 
    	serviceTransaction.setRecSt(serviceTransactionRequest.getRecSt()); */
		return serviceTransaction;
	}
}
