/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.process;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.businessdata.model.Currency;
import com.nathanclaire.alantra.businessdata.service.entity.CurrencyService;

/**
 * @author Edward Banfa
 *
 */
public class BusinessDataServiceImpl extends BaseProcessService implements
		BusinessDataService {
	
	@Inject CurrencyService currencyService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.businessdata.service.process.BusinessDataService#getCurrency(java.lang.String)
	 */
	@Override
	public Currency getCurrency(String currencyCode) throws ApplicationException {
		return (Currency) EntityUtil.returnOrThrowIfNull(
				currencyService.findByCode(currencyCode), ErrorCodes.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD, "Currency");
	}

}
