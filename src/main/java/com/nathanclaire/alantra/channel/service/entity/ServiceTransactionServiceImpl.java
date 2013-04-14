/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.WebApplicationException;

import com.nathanclaire.alantra.base.service.BaseEntityService;
import com.nathanclaire.alantra.channel.model.Service;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.channel.service.process.ServiceTransactionService;

/**
 * @author Edward Banfa 
 *
 */
public class ServiceTransactionServiceImpl extends BaseEntityService<ServiceTransaction> implements ServiceTransactionService{
	
	@Inject
	ChannelService channelService;

	@Inject
	ServiceTransactionTypeService serviceTransactionTypeService;

	/**
	 * 
	 */
	public ServiceTransactionServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param entityClass
	 */
	public ServiceTransactionServiceImpl(Class<ServiceTransaction> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	public void createServiceTransaction(ServiceTransactionRequest  request)
	{
		 try 
	        {
	        	ServiceTransaction serviceTransaction = this.loadModelFromRequest(request);
	        	getEntityManager().persist(serviceTransaction);
	        } 
	        catch (ConstraintViolationException e) 
	        {
	            // A WebApplicationException can wrap a response
	            // Throwing the exception causes an automatic rollback
	            throw new WebApplicationException(e);
	        } catch (Exception e) {
	            // Finally, handle 
	            throw new WebApplicationException(e);
	        }
	}
	
	/**
     * @param request
     * @return
     */
    private ServiceTransaction loadModelFromRequest(ServiceTransactionRequest request) 
    {
		ServiceTransaction serviceTransaction = new ServiceTransaction();
		// Find the service channel for this transaction
		Service service = channelService.findByCode(request.getTransactionChannel());
		if(service == null) throw new RuntimeException("Service channel for incoming transaction request not found");
		serviceTransaction.setService(service);
		
		// Find the service transaction type for this transaction
		ServiceTransactionType serviceTransactionType = serviceTransactionTypeService.findByCode(request.getTransactionType());
		if(serviceTransactionType == null) throw new RuntimeException("Service channel for incoming transaction type request not found");
		serviceTransaction.setServiceTransactionType(serviceTransactionType);
		
		// Set required instance properties
    	serviceTransaction.setName(request.getCustomerName()); 
    	serviceTransaction.setAmount(new BigDecimal(request.getTransactionAmount())); 
    	serviceTransaction.setTxnDate(new Date(request.getTransactionDate())); 
    	serviceTransaction.setAccountNo(request.getAccountNo()); 
    	serviceTransaction.setAccountNm(request.getCustomerName()); 
    	serviceTransaction.setCode(request.getAccountNo()); 
    	serviceTransaction.setEffectiveDt(getCurrentSystemDate());
    	serviceTransaction.setRecSt(BaseEntityService.ENTITY_STATUS_ACTIVE); 
    	serviceTransaction.setCreatedDt(getCurrentSystemDate());
    	serviceTransaction.setCreatedByUsr(getDefaultUserName());
		return serviceTransaction;
	}
}
