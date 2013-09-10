/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import java.util.List;

/**
 * @author Edward Banfa
 *
 */
public interface RuleTable {
	
	public String getName();
	
	public String getCode();
	
	public Boolean isDefaultRuleTable();
	
	public List<RuleChain> getRuleChains();

}
