/**
 * 
 */
package com.nathanclaire.alantra.businessobject.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectSearchInfo;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectSearchService {
	
	public List<BusinessObjectData> find(BusinessObjectSearchInfo searchInfo) throws ApplicationException;

}
