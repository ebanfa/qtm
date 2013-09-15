/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * @author Edward Banfa
 *
 */
public interface TransactionRuleValidationService {
	
	/**
	 * Validate a {@link businessObjectData} against all the
	 * {@link Rule}s defined with the VALIDATE {@link RuleTable}
	 * defined with the default {@link RuleSpace}. The {@link RuleChain}s that
	 * will be consulted will depend on whether the transaction is an INBOUND,
	 * FORWARD or an OUTBOUND business object.
	 *  
	 * @param businessObjectData the business object data
	 * @return true if the business object did not match with any of the
	 * evaluated rules and false otherwise
	 * @throws ApplicationException if an exception was encountered
	 */
	public Boolean validate(BusinessObjectData businessObjectData) throws ApplicationException;
}
