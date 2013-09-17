/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.data.SearchData;

/**
 * {@link BusinessObjectData} search interface.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectSearchService {
	
	public List<BusinessObjectData> find(SearchData searchInfo) throws ApplicationException;

	public BusinessObjectData findById(SearchData searchInfo) throws ApplicationException;

}
