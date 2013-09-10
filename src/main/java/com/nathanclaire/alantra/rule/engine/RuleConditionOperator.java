/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

/**
 * 
 * Represents an operator that can be applied to a
 * rule condition. Essentially operators are applied to operands,
 * (condition attribute and the condition parameter) of the same 
 * parameter type.
 * 
 * @author Edward Banfa
 *
 */
public interface RuleConditionOperator {
	
	public static final String LESS_THAN_OPERATOR = "<";
	public static final String EQUALS_TO_OPERATOR = "=";
	public static final String GREATER_THAN_OPERATOR = ">";
	
	public String getCode();
	
	public String getName();

	public String getSymbol();

}
