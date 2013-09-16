/**
 * 
 */
package com.nathanclaire.alantra.application.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectFieldData;

/**
 * @author Edward Banfa
 *
 */
public interface ActivityService {
	
	
	public List<BusinessObjectFieldData> getEntityListFields(String entityName) throws ApplicationException;

}
