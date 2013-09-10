/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceClassification;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.model.CustomerAccount;
import com.nathanclaire.alantra.datasource.model.DataChannel;

/**
 * @author Edward Banfa
 * 
 */
public interface AdviceService {
	
	public Advice createAdvice(AdviceType adviceType, AdviceClassification adviceClassification, 
			AdviceStatus status, DataChannel channel, Customer customer, CustomerAccount account,
			Currency currency, BigDecimal Amount, String chequeNo, String cardNo) throws ApplicationException;
	
	public AdviceType getAdviceType(String adviceTypeCode) 
			throws ApplicationException;

	public AdviceStatus getAdviceStatus(String adviceStatusCode)
			throws ApplicationException;

	public AdviceClassification getAdviceClassification(
			String adviceClassificationCode) throws ApplicationException;

}
