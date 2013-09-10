/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * Rules management service. Rules are loaded from rule definitions 
 * at application start up and passed to an instance of this interface. 
 * The management service basically provides an API to query, add, remove to
 * the rule system.
 * .
 * @author Edward Banfa
 * 
 */
public interface TransactionRuleManagementService {
	
	public static final String ROUTING_RULE_TABLE_CD = "ROUTING";
	public static final String PROCESSING_RULE_TABLE_CD = "PROCESSING";
	public static final String VALIDATION_RULE_TABLE_CD = "VALIDATION";
	
	public static final String DEFAULT_ROUTING_RULE_CHAIN_CD = "INBOUND";
	public static final String INBOUND_ROUTING_RULE_CHAIN_CD = "INBOUND";
	public static final String FORWARD_ROUTING_RULE_CHAIN_CD = "FORWARD";
	public static final String OUTBOUND_ROUTING_RULE_CHAIN_CD = "OUTBOUND";

	public static final String DEFAULT_VALIDATION_RULE_CHAIN_CD = "INPUT";
	public static final String INPUT_VALIDATION_RULE_CHAIN_CD = "INPUT";
	public static final String ROUTE_VALIDATION_RULE_CHAIN_CD = "ROUTE";
	public static final String OUTPUT_VALIDATION_RULE_CHAIN_CD = "OUTPUT";
	
	public static final String DEFAULT_PROCESSING_RULE_CHAIN_CD = "PROCESS";

	/**
	 * Returns the default {@link RuleSpace}. At any given moment, there
	 * is only one active rule space. The currently active rule space is,
	 * the rules space that will be consulted when matching/processing rules
	 * 
	 * @return the active rule space
	 * @throws ApplicationException If an exception was encountered
	 */
	public RuleSpace getDefaultRuleSpace() throws ApplicationException;

	/**
	 * Sets the given {@link RuleSpace} as the currently active rule space.
	 * At any given moment, there is only one active rule space. 
	 * The currently active rule space is, the rules space that 
	 * will be consulted when matching/processing rules
	 * 
	 * @param ruleSpace the rule space to set as the
	 *        currently active rule space
	 * @throws ApplicationException If an exception was encountered
	 */
	public void setDefaultRuleSpace(RuleSpace ruleSpace) throws ApplicationException;

	/**
	 * Get the default rule chain in the routing table.
	 * 
	 * @param ruleSpace the rule space to search
	 * @return the default rule chain in the routing table
	 * 		   of the specified rule space
	 * @throws ApplicationException if an exception was encountered
	 */
	public RuleChain getDefaultRoutingRuleChain(RuleSpace ruleSpace) 
			throws ApplicationException;
	/**
	 * Get the default rule chain in the processing table.
	 * 
	 * @param ruleSpace the rule space to search
	 * @return the default rule chain in the processing table
	 * 		   of the specified rule space
	 * @throws ApplicationException if an exception was encountered
	 */
	public RuleChain getDefaultProcessingRuleChain(RuleSpace ruleSpace) 
			throws ApplicationException;
	
	/**
	 * Get the default rule chain in the validation table.
	 * 
	 * @param ruleSpace the rule space to search
	 * @return the default rule chain in the validation table
	 * 		   of the specified rule space
	 * @throws ApplicationException if an exception was encountered
	 */
	public RuleChain getDefaultValidationRuleChain(RuleSpace ruleSpace) 
			throws ApplicationException;

	/**
	 * Get the routing {@link RuleTable} in a {@link RuleSpace}.
	 * 
	 * @param ruleSpace the rule space to search
	 * @return the validation table
	 * @throws ApplicationException if an exception was encountered
	 */
	public RuleTable getRoutingRulesTable(RuleSpace ruleSpace)
			throws ApplicationException;

	/**
	 * Get the processing {@link RuleTable} in a {@link RuleSpace}.
	 * 
	 * @param ruleSpace the rule space to search
	 * @return the validation table
	 * @throws ApplicationException if an exception was encountered
	 */
	public RuleTable getProcessingRulesTable(RuleSpace ruleSpace)
			throws ApplicationException;

	/**
	 * Get the validation {@link RuleTable} in a {@link RuleSpace}.
	 * 
	 * @param ruleSpace the rule space to search
	 * @return the validation table
	 * @throws ApplicationException if an exception was encountered
	 */
	public RuleTable getValidationRulesTable(RuleSpace ruleSpace)
			throws ApplicationException;

	/**
	 * Starts the rule management process.
	 * 
	 * @param ruleSpaces
	 * @throws ApplicationException If an exception was encountered
	 */
	public void start(List<RuleSpace> ruleSpaces) throws ApplicationException;

	/**
	 * Stops the rule management process and releases any resources held.
	 * 
	 * @throws ApplicationException If an exception was encountered
	 */
	public void stop() throws ApplicationException;

}
