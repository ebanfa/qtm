/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
public interface RuleAction {
	
	public BusinessObjectData execute(BusinessObjectData businessObjectData) throws ApplicationException;

}
