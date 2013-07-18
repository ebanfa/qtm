/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.request.CurrencyRequest;
import com.nathanclaire.alantra.businessdata.response.CurrencyResponse;

/**
 * @author Edward Banfa
 *
 */
public interface CurrencyService extends BaseEntityService<Currency, CurrencyResponse, CurrencyRequest>
{
	public final static String DEFAULT_CURRENCY_CODE= "13";
}
