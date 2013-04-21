/**
 * 
 */
package com.nathanclaire.alantra.channel.service.process;

import com.nathanclaire.alantra.channel.model.ServiceTransaction;


/**
 * @author Edward Banfa 
 *
 */
public interface ServiceTransactionBusinessService {
	public boolean isAuthorized(ServiceTransaction transaction);
}
