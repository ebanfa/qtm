/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * Matches rules to a {@link BusinessObjectData}.
 * 
 * @author Edward Banfa
 * 
 */
public interface TransactionRuleMatchingService {

	/**
	 * Match a {@link BusinessObjectData} against all the
	 * {@link Rule}s defined within the {@link RuleSpace}. 
	 * 
	 * @param ruleSpace the rule space to use
	 * @param businessObjectData the business object to match against the rules
	 * @return the list of rules that match
	 * @throws ApplicationException If an exception was encountered
	 */
	public List<Rule> match(RuleSpace ruleSpace, BusinessObjectData businessObjectData) throws ApplicationException;

	/**
	 * Match a {@link BusinessObjectData} against all the
	 * {@link Rule}s defined within the given{@link RuleTable}.
	 * 
	 * @param ruleTable the table to use
	 * @param businessObjectData the business object to match against the rules
	 * @return The list of rules that match
	 * @throws ApplicationException If an exception was encountered
	 */
	public List<Rule> match(RuleTable ruleTable, BusinessObjectData businessObjectData) throws ApplicationException;

	/**
	 * Match all the {@link Rule}s within a given {@link RuleChain}.
	 * 
	 * @param ruleChain the rule chain to search
	 * @param businessObjectData the business object to match against the rules
	 * @return The list of rules that match
	 * @throws ApplicationException If an exception was encountered
	 */
	public List<Rule> match(RuleChain ruleChain, BusinessObjectData businessObjectData) throws ApplicationException;

	/**
	 * Match a {@link BusinessObjectData} against all the
	 * {@link Rule}s given.
	 * 
	 * @param rules list of rules to match against
	 * @param businessObjectData the business object to match against the rules
	 * @return The list of rules that match
	 * @throws ApplicationException If an exception was encountered
	 */
	public List<Rule> match(List<Rule> rules, BusinessObjectData businessObjectData) throws ApplicationException;

	/**
	 * Matches a single {@link Rule} against a {@link BusinessObjectData}.
	 * 
	 * @param rule the rule to match
	 * @param businessObjectData the business object to match against the rules
	 * @return The list of rules that match
	 * @throws ApplicationException If an exception was encountered
	 */
	public Boolean match(Rule rule, BusinessObjectData businessObjectData) throws ApplicationException;

}
