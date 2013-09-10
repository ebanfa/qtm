/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;

/**
 * Represents objects that know how to carry out the operations
 * specified in rule conditions.
 * 
 * @author Edward Banfa
 *
 */
public interface RuleConditionOperatorService {
	
	/**
	 * Uses an operator {@link RuleConditionOperator} to operate of two operands {@code firstValue}
	 * and {@code secondValue}. The operands must be of the same type, which
	 * is indicated by the value of {@code parameterType}.
	 * 
	 * @param parameterType the type of the operands
	 * @param firstValue the first operand
	 * @param secondValue the second operand
	 * @param ruleConditionOperator the operator
	 * @return a boolean to indicate if the operator evaluates to true.
	 * @throws ApplicationException if an exception was encountered.
	 */
	public Boolean operate(String parameterType, Object firstValue, 
			Object secondValue, RuleConditionOperator ruleConditionOperator) throws ApplicationException;

}
