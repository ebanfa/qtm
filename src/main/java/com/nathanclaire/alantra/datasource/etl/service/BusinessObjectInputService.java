/**
 * 
 */
package com.nathanclaire.alantra.datasource.etl.service;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
public interface BusinessObjectInputService {
	
	public void loadBusinessObject(BusinessObjectData businessObject) throws ApplicationException;

}
