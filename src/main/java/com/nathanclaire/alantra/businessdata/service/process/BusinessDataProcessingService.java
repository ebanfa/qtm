/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.customer.model.Account;

/**
 * @author Edward Banfa 
 *
 */
public interface BusinessDataProcessingService {

	public static final String CURRENCY_CODE_DOES_NOT_EXIST =
			"BusinessDataProcessingService.CURRENCY_CODE_DOES_NOT_EXIST";
	
	public static final String CURRENCY_FOR_ADVICE_REQUEST_NOT_FOUND = 
			"BusinessDataProcessingService.CURRENCY_FOR_ADVICE_REQUEST_NOT_FOUND";
	
	public static final String CONFIG_ERROR_ACCOUNT_HAS_NO_CURRENCY_DEFINED = 
			"BusinessDataProcessingService.CONFIG_ERROR_ACCOUNT_HAS_NO_CURRENCY_DEFINED";

	public static final String CONFIG_ERROR_NO_CURRENCIES_DEFINED = "BusinessDataProcessingService.CONFIG_ERROR_NO_CURRENCIES_DEFINED";
	
	/**
	 * @param currencyCode
	 * @return
	 * @throws ApplicationException
	 */
	public Currency getCurrency(String currencyCode) throws ApplicationException;

	public Currency getDefaultCurrency(List<Account> accounts, List<Currency> currenciesAvailable) throws ApplicationException;
	
	public List<Currency> getAllCurrencies() throws ApplicationException;

}
