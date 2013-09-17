/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;

/**
 * Represents an object that can return the value
 * of a single attribute of a business object. An example
 * of an attributes of business objects are:
 *  1. Amount
 *  2. Date
 *  3. Transaction type
 *  
 * @author Edward Banfa
 *
 */
public interface TransactionAttributeValueService {
	
	public static final String DATE_ATTR_NM = "";
	public static final String ACCOUNT_NO_ATTR_NM = "accountNo";
	public static final String TRANSACTION_TY_ATTR_NM = "";
	
	public Object getAttributeValue(String attributeName, BusinessObjectData businessObjectData) throws ApplicationException;

}
