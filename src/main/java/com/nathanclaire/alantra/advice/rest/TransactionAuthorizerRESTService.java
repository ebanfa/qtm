/**
 * 
 */
package com.nathanclaire.alantra.advice.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.service.TransactionAuthorizerService;
import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;

/**
 * @author Edward Banfa 
 *
 */
@Path("/authorize")
@Stateless
public class TransactionAuthorizerRESTService {
	
	@Inject
	TransactionAuthorizerService transactionAuthorizerService;
	private Logger logger = LoggerFactory.getLogger(TransactionAuthorizerRESTService.class);

	/**
	 * authorized
	 */
	public TransactionAuthorizerRESTService() {
	}
	
	/**
	 * @param transactionRequest
	 * @return
	 */
	@POST
	@Consumes("application/json")
	public ServiceTransactionRequest authorize(ServiceTransactionRequest transactionRequest) {
		logger.debug("Processing HTTP POST transaction authorization request for account {}", transactionRequest.getAccountNo());
		boolean isAuthorized = transactionAuthorizerService.isAuthorized(transactionRequest);
		if(isAuthorized)
		{
			transactionRequest.setReturnCode(0);
		}else{
			transactionRequest.setReturnCode(1);
		}
		logger.debug("Transaction for account {} processed", transactionRequest.getAccountNo());
		return transactionRequest;
	}

}
