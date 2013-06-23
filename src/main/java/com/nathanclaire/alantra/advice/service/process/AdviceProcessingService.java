/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;
import java.util.List;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessage;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.Customer;

/**
 * @author Edward Banfa 
 *
 */
public interface AdviceProcessingService 
{

	public static final String AMOUNT_GRP_REGEX_PATTERN = "";
	public static final String ADVICE_TEXT_REGEX_PATTERN = "";
	public static final String CARD_NO_GRP_REGEX_PATTERN = "";
	public static final String CHEQUE_NO_GRP_REGEX_PATTERN = "";
	
	
	public AdviceRequestMessage createAdviceRequest(Customer customer, String sourceAddress, Account account, 
			Currency currencysInAdviceText, BigDecimal amount, String chequeNo, String cardNo, AdviceType adviceType, 
			Integer dataChannelId, String adviceText) throws ApplicationException;
	/**
	 * @param requestMessage
	 * @return
	 */
	public Advice createAdvice(AdviceRequestMessage requestMessage) throws ApplicationException;

	/**
	 * @param customerId
	 * @param sourceAddress
	 * @param messageText
	 * @param dataChannelId
	 * @throws ApplicationException
	 */
	public void processAdviceText(Integer customerId, String sourceAddress, String messageText, Integer dataChannelId)  
			throws ApplicationException ;
	
	/**
	 * @param customerId
	 * @param accountId
	 * @param chequeNo
	 * @param currencyId
	 * @param adviceTypeId
	 * @param adviceStatus
	 * @param amount
	 * @return
	 * @throws ApplicationException
	 */
	public Advice findAdvice(Integer customerId, Integer accountId, String chequeNo, String cardNo, 
			Integer currencyId, Integer adviceTypeId, Integer adviceStatus, BigDecimal amount)  throws ApplicationException ;
	
	/**
	 * @param unprocessedAdviceCode
	 * @return
	 * @throws ApplicationException
	 */
	AdviceRequestMessageStatus getAdviceStatus(String unprocessedAdviceCode) throws ApplicationException;
	
	/**
	 * @param adviceText
	 * @return
	 * @throws ApplicationException
	 */
	AdviceType getAdviceTypeInAdviceText(String adviceText)	throws ApplicationException;
	
	/**
	 * @param code
	 * @return
	 * @throws ApplicationException
	 */
	public AdviceRequestMessage findAdviceRequestMessage(String code)	throws ApplicationException;
}
