/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * This class should be in charge triggering the firing of advice events.
 * 
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceProcessingServiceImpl extends BaseProcessService implements AdviceProcessingService 
{	
	@Inject AdviceService adviceService;
	@Inject AdviceRequestMessageProcessingService requestMessageService;
	@Inject AdviceTextProcessingService adviceTextService;
	
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceProcessingService#findAdvice(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.math.BigDecimal)
	 */
	@Override
	public Advice findAdvice(Integer customerId, Integer accountId, String chequeNo, String cardNo, 
			Integer currencyId, Integer adviceTypeId, Integer adviceStatus, BigDecimal amount) throws ApplicationException 
			{
		return adviceService.findAdvice(customerId, accountId, chequeNo, cardNo, currencyId, adviceTypeId, adviceStatus, amount);
	}

	@Override
	public void processAdviceText(Integer customerId, String sourceAddress, String messageText, Integer dataChannelId)
			throws ApplicationException {
		adviceTextService.processAdviceText(customerId, sourceAddress, messageText, dataChannelId);
	}

	@Override
	public AdviceRequestMessageStatus getAdviceStatus(String statusCode) throws ApplicationException {
		return requestMessageService.getAdviceRequestMessageStatus(statusCode);
	}

	@Override
	public AdviceType getAdviceTypeInAdviceText(String adviceText)	throws ApplicationException {
		return adviceTextService.getAdviceTypeInAdviceText(adviceText);
	}

	@Override
	public AdviceRequestMessage findAdviceRequestMessage(String code) throws ApplicationException {
		return requestMessageService.findAdviceRequestMessage(code);
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceProcessingService#createAdviceRequest(com.nathanclaire.alantra.customer.model.Customer, java.lang.String, java.util.List, java.util.List, java.math.BigDecimal, java.lang.String, java.lang.String, com.nathanclaire.alantra.advice.model.AdviceType, java.lang.Integer)
	 */
	@Override
	public AdviceRequestMessage createAdviceRequest(Customer customer, String sourceAddress, Account account, 
			Currency currencysInAdviceText, BigDecimal amount, String chequeNo, String cardNo, AdviceType adviceType, 
			Integer dataChannelId, String adviceText) throws ApplicationException 
	{
		return requestMessageService.createAdviceRequest(customer, sourceAddress, account, 
				currencysInAdviceText, amount, chequeNo, cardNo, adviceType, dataChannelId, adviceText);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.advice.service.process.AdviceProcessingService#createAdvice(com.nathanclaire.alantra.advice.model.AdviceRequestMessage)
	 */
	@Override
	public Advice createAdvice(AdviceRequestMessage requestMessage) throws ApplicationException {
		return requestMessageService.createAdvice(requestMessage);
	}

}
