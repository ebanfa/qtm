/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

import java.util.List;


/**
 * @author Edward Banfa
 *
 */
public interface RuleCondition {

	public String getName();
	
	public String getCode();
	
	public RuleConditionAttribute getRuleConditionAttribute();

	public RuleConditionParameter getRuleConditionParameter();
	
	public RuleConditionOperator getRuleConditionOperator();
	
	public List<RuleConditionOperator> getApplicableOperators();
	
}
