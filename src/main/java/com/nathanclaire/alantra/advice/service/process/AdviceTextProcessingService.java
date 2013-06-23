/**
 * 
 */
package com.nathanclaire.alantra.advice.service.process;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.nathanclaire.alantra.advice.model.AdviceType;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Account;
import com.nathanclaire.alantra.customer.model.CustomerAccount;

/**
 * @author Edward Banfa 
 *
 */
public interface AdviceTextProcessingService {
	

	public static final String CUSTOMER_HAS_NO_ACCOUNTS = "AdviceTextProcessingService.CUSTOMER_HAS_NO_ACCOUNTS";
	public static final String ADVICE_AMOUNT_NOT_SPECIFIED = "AdviceTextProcessingService.ADVICE_AMOUNT_NOT_SPECIFIED";
	public static final String ADVICE_TY_NOT_FOUND_ADVICE_TEXT = "AdviceTextProcessingService.ADVICE_TY_NOT_FOUND_ADVICE_TEXT";
	public static final String INVALID_AMOUNT_STRING_SPECIFIED = "AdviceTextProcessingService.INVALID_AMOUNT_STRING_SPECIFIED";
	public static final String CONFIG_ERROR_ACCOUNT_TYPE_NOT_FOUND = "AdviceTextProcessingService.CONFIG_ERROR_ACCOUNT_TYPE_NOT_FOUND";
	public static final String ADVICE_TY_TEXT_NOT_FOUND_ADVICE_TEXT = "AdviceTextProcessingService.ADVICE_TY_TEXT_NOT_FOUND_ADVICE_TEXT";
	public static final String CONFIG_ERROR_INVALID_ACCOUNT_MAPPING = "AdviceTextProcessingService.CONFIG_ERROR_INVALID_ACCOUNT_MAPPING";
	
	public static final String ACCOUNTS_NOT_SPECIFIED_FOR_ADVICE_REQUEST_CREATION = 
			"AdviceTextProcessingService.ACCOUNTS_NOT_SPECIFIED_FOR_ADVICE_REQUEST_CREATION";
	
	public static final String INCORRECT_CUSTOMER_SOURCE_ADDRESS = 
			"AdviceRequestMessageProcessingService.INCORRECT_CUSTOMER_SOURCE_ADDRESS";
	
	public static final String AMOUNT_GRP_REGEX_PATTERN = "";
	public static final String ADVICE_TEXT_REGEX_PATTERN = "";

	public BigDecimal getAmountInAdviceText(String adviceText)	throws ApplicationException;

	public AdviceType getAdviceTypeInAdviceText(String adviceText)	throws ApplicationException;

	public List<Currency> findCurrencyInAdviceText(String adviceText) throws ApplicationException;

	public String findCardNoInAdviceText(List<Account> accounts, String adviceText) throws ApplicationException;

	public String findChequeNoInAdviceText(List<Account> accounts, String adviceText) throws ApplicationException;

	public List<Account> findAccountsInAdviceText(Set<CustomerAccount> accountMappings, String adviceText) throws ApplicationException;

	public void processAdviceText(Integer customerId, String sourceAddress, String adviceText, Integer dataChannelId) 
			throws ApplicationException;

}
