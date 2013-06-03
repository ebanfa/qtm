/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionMatchingService {
	
	public Advice match(ServiceTransaction transaction) throws ApplicationException ;

}
