/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

/**
 * Represents the parameter side of a rule condition
 * {@link RuleCondition}. 
 * A value for this parameter is normally provided
 * when the rule is being created.
 * 
 * @author Edward Banfa
 *
 */
public interface RuleConditionParameter {
	
	public String getName();
	
	public String getCode();
	
	public String getValue();
	
	public String getParameterType();

}
