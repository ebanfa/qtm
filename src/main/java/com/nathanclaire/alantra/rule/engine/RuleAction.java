/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import com.nathanclaire.alantra.base.util.ApplicationException;

/**
 * @author Edward Banfa
 *
 */
public interface RuleAction {
	
	public BusinessObjectData execute(BusinessObjectData businessObjectData) throws ApplicationException;

}
