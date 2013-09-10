/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.engine.RuleSpace;

/**
 * @author Edward Banfa
 *
 */
public interface TransactionRuleConfigurationService {
	
	/**
	 * Loads all the rules definition from the "rule store". The return
	 * parameter is the rule spaces defined in the store. Only one rule space can
	 * be active within the rules engine at any one time.
	 *   
	 * @return list of rules spaces
	 * @throws ApplicationException if no rule spaces have been defined or if
	 * 		   an exception was encountered.
	 */
	public List<RuleSpace> loadAll() throws ApplicationException;
	
	/**
	 * Load the rule space with the given code from the rule store. 
	 * 
	 * @param ruleSpaceCode the code of the rule space requested
	 * @return the rule space
	 * @throws ApplicationException if no rule space was found or if
	 * 		   an exception was encountered
	 */
	public RuleSpace load(String ruleSpaceCode) throws ApplicationException;

}
