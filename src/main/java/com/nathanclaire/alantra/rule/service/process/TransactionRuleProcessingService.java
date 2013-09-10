/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.RuleSpace;

/**
 * @author Edward Banfa
 * 
 */
public interface TransactionRuleProcessingService {

	/**
	 * Process a {@link BusinessObjectData} object against all the rules,
	 * defined in the default chain of the PROCESS table in 
	 * the default {@link RuleSpace}
	 * 
	 * @param businessObjectData the business object
	 * @throws ApplicationException if an exception was encountered
	 */
	public void process(BusinessObjectData businessObjectData) throws ApplicationException;

}
