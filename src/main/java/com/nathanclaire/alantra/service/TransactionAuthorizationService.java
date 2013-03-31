/**
 * 
 */
package com.nathanclaire.alantra.service;

import java.math.BigDecimal;

/**
 * @author Edward Banfa 
 *
 */
public interface TransactionAuthorizationService {
	
	
	public boolean validateTransaction(String customerName, String accoutnNo, 
			String transationType, BigDecimal amount);

}
