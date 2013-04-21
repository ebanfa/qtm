/**
 * 
 */
package com.nathanclaire.alantra.channel.rest.process;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.base.service.process.BaseBusinessService;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;
import com.nathanclaire.alantra.channel.service.entity.ServiceTransactionService;
import com.nathanclaire.alantra.channel.service.process.ServiceTransactionBusinessService;

/**
 * @author Edward Banfa 
 *
 */
@Path("/authorize")
@Stateless
public class TransactionAuthorizationRESTService extends BaseBusinessService 
{
	/**
	 * 
	 */
	@Inject
	ServiceTransactionService serviceTransactionService;
	
	/**
	 * 
	 */
	@Inject
	AdviceService adviceService;
	/**
	 * 
	 */
	@Inject
	ServiceTransactionBusinessService serviceTransactionBusinessService;
	
	/**
	 *  Logger
	 */
	private Logger logger = LoggerFactory.getLogger(TransactionAuthorizationRESTService.class);
	
    /**
     * @param transactionRequest
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public ServiceTransactionRequest authorize(ServiceTransactionRequest transactionRequest)
	{
    	logger.debug("Received incoming transaction request for: {}", transactionRequest.getCustomerName());
    	transactionRequest.setRecSt(ServiceTransactionService.TRANSACTION_STATUS_PROCESSING);
    	// Step one register the incoming transaction authorization request
    	ServiceTransaction transaction = serviceTransactionService.create(transactionRequest);
    	boolean isAuthorized = serviceTransactionBusinessService.isAuthorized(transaction);
    	if (!isAuthorized) return rejectTransaction(transaction, transactionRequest);
    	return acceptTransaction(transaction, transactionRequest);
	}
    
    /**
     * @param transaction
     * @param transactionRequest
     * @return
     */
    private ServiceTransactionRequest rejectTransaction(ServiceTransaction transaction, 
    		ServiceTransactionRequest transactionRequest)
    {
    	logger.info("Rejecting transaction for {}", transactionRequest.getCustomerName());
    	transactionRequest.setRecSt(ServiceTransactionService.TRANSACTION_STATUS_REJECTED);
    	transaction.setRecSt(ServiceTransactionService.TRANSACTION_STATUS_REJECTED);
    	transactionRequest.setId(transaction.getId());
    	serviceTransactionService.update(transactionRequest);
    	return transactionRequest;
    }
    
    /**
     * @param transaction
     * @param transactionRequest
     * @return
     */
    private ServiceTransactionRequest acceptTransaction(ServiceTransaction transaction, 
    		ServiceTransactionRequest transactionRequest)
    {
    	logger.info("Accepting transaction for {}", transactionRequest.getCustomerName());
    	transactionRequest.setRecSt(ServiceTransactionService.TRANSACTION_STATUS_ACCEPTED);
    	transaction.setRecSt(ServiceTransactionService.TRANSACTION_STATUS_ACCEPTED);
    	transactionRequest.setId(transaction.getId());
    	serviceTransactionService.update(transactionRequest);
    	return transactionRequest;
    }
}
