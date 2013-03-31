/**
 * 
 */
package com.nathanclaire.alantra.service;

import java.math.BigDecimal;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Edward Banfa 
 *
 */
@WebService(name="TransactionAuthorizationService")
@Remote(TransactionAuthorizationService.class)
@Stateless
public class TransactionAuthorizationServiceImpl implements 
								TransactionAuthorizationService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.service.TransactionAuthorizationService#validateTransaction(java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal)
	 */
	@WebMethod
	public boolean validateTransaction(String customerName, String accoutnNo,
			String transationType, BigDecimal amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
