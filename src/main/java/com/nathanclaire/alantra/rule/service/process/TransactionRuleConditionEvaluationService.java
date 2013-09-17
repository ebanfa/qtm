/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.RuleCondition;

/**
 * Used to evaluate a rule condition against a business object.
 * 
 * @author Edward Banfa
 *
 */
public interface TransactionRuleConditionEvaluationService {
	
	/**
	 * Evaluate a {@link RuleCondition} against a {@link BusinessObjectData}.
	 * Under normal circumstances, when this method returns true, the 
	 * rule condition is considered as matching the given business object.
	 * 
	 * @param ruleCondition the rule condition
	 * @param businessObjectData the business object data
	 * @return true if the rule condition applies to this business object and 
	 * 	       false if otherwise
	 * @throws ApplicationException if an exception was encountered
	 */
	public Boolean evaluate (RuleCondition ruleCondition, BusinessObjectData businessObjectData) throws ApplicationException;

}
