/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

import com.nathanclaire.alantra.base.service.entity.BaseEntityServiceImpl;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;

/**
 * @author administrator
 *
 */
@Stateless
public class ServiceTransactionServiceImpl extends BaseEntityServiceImpl<ServiceTransaction, ServiceTransactionRequest> implements ServiceTransactionService
{
	@Inject
	ServiceService channelService;
	
	@Inject
	ServiceTransactionTypeService transactionTypeService;
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
	public ServiceTransaction create(ServiceTransactionRequest serviceTransactionRequest) {
		return createInstance(serviceTransactionRequest);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#deleteServiceTransaction(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		deleteInstance(id);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.ServiceTransaction#updateServiceTransaction(com.nathanclaire.alantra.channel.rest.request.ServiceRequest)
	 */
	@Override
	public ServiceTransaction update(ServiceTransactionRequest serviceTransactionRequest) {
		return updateInstance(serviceTransactionRequest);
	}
	
	/**
     * @param request
     * @return
     */
	@Override
    protected ServiceTransaction loadModelFromRequest(ServiceTransactionRequest serviceTransactionRequest) 
    {
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
    	// Process the service and transaction type
		serviceTransaction.setService(getService(serviceTransactionRequest.getTransactionChannel()));
		serviceTransaction.setServiceTransactionType(getTransactionType(serviceTransactionRequest.getTransactionType()));;
    	// Set the code to the current date. Should be improved
    	if(serviceTransactionRequest.getCode() == null) 
    		serviceTransaction.setCode(getCurrentSystemDate().toString());
    	if(serviceTransactionRequest.getName() == null) {
    		serviceTransaction.setName(serviceTransactionRequest.getCustomerName());
    	}else {
    		serviceTransaction.setName(serviceTransactionRequest.getName());
    	}
    	serviceTransaction.setAmount(new BigDecimal(serviceTransactionRequest.getTransactionAmount())); 
    	serviceTransaction.setTxnDate(stringToDate(serviceTransactionRequest.getTransactionDate())); 
    	
    	serviceTransaction.setAccountNo(serviceTransactionRequest.getAccountNo()); 
    	serviceTransaction.setAccountNm(serviceTransactionRequest.getCustomerName()); 
    	serviceTransaction.setRecSt(serviceTransactionRequest.getRecSt()); 
		return serviceTransaction;
	}
	
	/**
	 * @param serviceCode
	 * @return
	 */
	private Service getService(String serviceCode)
    {
		System.out.println("This cant be real");
		return channelService.findByCode(serviceCode);
    }
    
    /**
     * @param transactionTypeCode
     * @return
     */
    private ServiceTransactionType getTransactionType(String transactionTypeCode)
    {
    	return transactionTypeService.findByCode(transactionTypeCode);
    }
}
