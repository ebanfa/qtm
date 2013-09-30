/**
 *
 */
package com.nathanclaire.alantra.businessobject.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.SearchData;

/**
 * Builds a query string for finding {@link BusinessObjectData}s.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectQueryBuilderService {
	
	public String buildQuery(SearchData data) throws ApplicationException;

}
