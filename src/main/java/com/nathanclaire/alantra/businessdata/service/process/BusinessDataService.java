/**
 * 
 */
package com.nathanclaire.alantra.businessdata.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessdata.model.Currency;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessDataService {

	public Currency getCurrency(String currencyCode) throws ApplicationException;
}
