/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import com.nathanclaire.alantra.channel.rest.request.ServiceTransactionRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface ServiceTransactionService {
	public void createServiceTransaction(ServiceTransactionRequest  request);
}