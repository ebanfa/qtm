/**
 * 
 */
package com.nathanclaire.alantra.rule.engine;

/**
 * Represents the attribute side of a rule condition
 * {@link RuleCondition}. The actual value of this attribute,
 * is normally resolved at runtime. Example is an attribute that
 * points to a property of a transaction object (eg amount), the actual value of 
 * the attribute will be the value of amount property of the transaction
 * object.
 * 
 * @author Edward Banfa
 *
 */
public interface RuleConditionAttribute {

	public String getName();
	
	public String getCode();
	
	public String getParameterType();
	

}
