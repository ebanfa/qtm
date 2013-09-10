/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import java.util.List;

/**
 * @author Edward Banfa
 *
 */
public interface RuleChain {
	
	public String getName();
	
	public String getCode();
	
	public Boolean isDefaultRuleChain();
	
	public List<Rule> getRules();
	
	public void addRule(Rule rule);

	public void removeRule(String ruleCode);
	

}
