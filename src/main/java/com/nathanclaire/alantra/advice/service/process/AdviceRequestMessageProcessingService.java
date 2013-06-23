/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;

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
public interface AdviceRequestMessageProcessingService {
	
	public static final String COULD_NOT_CREATE_ADVICE_REQUEST = 
			"AdviceRequestMessageProcessingService.COULD_NOT_CREATE_ADVICE_REQUEST";
	public static final String CUSTOMER_NOT_FOUND = "AdviceProcessingService.CUSTOMER_NOT_FOUND";
	public static final String ADVICE_ALREADY_EXIST = "AdviceProcessingService.ADVICE_ALREADY_EXIST";
	public static final String ADVICE_TYPE_NOT_FOUND = "AdviceProcessingService.ADVICE_TYPE_NOT_FOUND";
	public static final String ADVICE_CLASS_NOT_FOUND = "AdviceProcessingService.ADVICE_CLASS_NOT_FOUND";
	public static final String ADVICE_STATUS_NOT_FOUND = "AdviceProcessingService.ADVICE_STATUS_NOT_FOUND";
	public static final String ADVICE_CURRENCY_NOT_FOUND = "AdviceProcessingService.ADVICE_CURRENCY_NOT_FOUND";
	public static final String CUSTOMER_ACCOUNT_NOT_FOUND = "AdviceProcessingService.CUSTOMER_ACCOUNT_NOT_FOUND";
	public static final String INCORRECT_CUSTOMER_SOURCE_ADDRESS = "AdviceProcessingService.INCORRECT_CUSTOMER_SOURCE_ADDRESS";

	/**
	 * @param customer
	 * @param sourceAddress
	 * @param account
	 * @param currencysInAdviceText
	 * @param amount
	 * @param chequeNo
	 * @param cardNo
	 * @param adviceType
	 * @param dataChannelId
	 * @param adviceText
	 * @return
	 * @throws ApplicationException
	 */
	AdviceRequestMessage createAdviceRequest(Customer customer,	String sourceAddress, Account account,
			Currency currencysInAdviceText, BigDecimal amount, String chequeNo, String cardNo, AdviceType adviceType, 
			Integer dataChannelId, String adviceText) throws ApplicationException;

	/**
	 * @param statusCode
	 * @return
	 * @throws ApplicationException
	 */
	AdviceRequestMessageStatus getAdviceRequestMessageStatus(String statusCode)	throws ApplicationException;

	/**
	 * @param code
	 * @return
	 * @throws ApplicationException
	 */
	AdviceRequestMessage findAdviceRequestMessage(String code) throws ApplicationException;

	Advice createAdvice(AdviceRequestMessage requestMessage)
			throws ApplicationException;

}
