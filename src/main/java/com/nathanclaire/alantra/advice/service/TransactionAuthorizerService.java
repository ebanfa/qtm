/**
 * 
 */
package com.nathanclaire.alantra.advice.service;

import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionAuthorizerService {
	
	/**
	 * @param transactionRequest
	 * @return
	 */
	public boolean isAuthorized(ServiceTransactionRequest transactionRequest);

}
