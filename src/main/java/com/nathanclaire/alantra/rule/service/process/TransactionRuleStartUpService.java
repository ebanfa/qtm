/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

/**
 * @author Edward Banfa
 * 
 * Start up service to initialize the transaction rules processing engine.
 *
 */
public interface TransactionRuleStartUpService {
	
	/**
	 * Initializes the in rules engine
	 */
	public void start();
	
	/**
	 * Shuts down the rule engine
	 */
	public void shutdown();

}
