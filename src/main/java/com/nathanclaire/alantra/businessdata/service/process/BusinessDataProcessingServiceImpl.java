/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;
import com.nathanclaire.alantra.customer.model.Account;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class BusinessDataProcessingServiceImpl extends BaseProcessService implements BusinessDataProcessingService {

	@Inject CurrencyService currencyService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.process.BusinessDataProcessingService#getCurrency(java.lang.String)
	 */
	@Override
	public Currency getCurrency(String currencyCode	) throws ApplicationException
	{
		Currency currency = currencyService.findByCode(currencyCode);
		if(currency == null) throw new ApplicationException(CURRENCY_CODE_DOES_NOT_EXIST);
		return currency;
	}


	/**
	 * @param accounts
	 * @param currencysInAdviceText
	 * @return
	 * @throws ApplicationException
	 */
	@Override
	public Currency getDefaultCurrency(List<Account> accounts, List<Currency> currenciesAvailable) throws ApplicationException {
		Currency currency = null;
		if(currenciesAvailable.isEmpty())
		{
			currency = accounts.get(0).getCurrency();
			if(currency == null)
				throw new ApplicationException(CONFIG_ERROR_ACCOUNT_HAS_NO_CURRENCY_DEFINED);
		}
		else
			currency = currenciesAvailable.get(0);
		if(currency == null)
			throw new ApplicationException(CURRENCY_FOR_ADVICE_REQUEST_NOT_FOUND);
		return currency;
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.process.BusinessDataProcessingService#getAllCurrencies()
	 */
	@Override
	public List<Currency> getAllCurrencies() throws ApplicationException 
	{
		List<Currency> currencies = currencyService.findAll(null);
		if(currencies.isEmpty())throw new ApplicationException(CONFIG_ERROR_NO_CURRENCIES_DEFINED);
		return currencies;
	}
}
