/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process.operators;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;

/**
 * Represents objects the know how to carry out comparison 
 * operations (>, < ==) on two operands of the same type.
 * 
 * @author Edward Banfa
 *
 */
public interface OperatorService {
	
	/**
	 * Carry out an operation specified by the provided 
	 * {@link RuleConditionOperator} on two operands of a 
	 * a specific type.
	 * 
	 * @param parameterType the data type of the operands
	 * @param firstValue the first operand
	 * @param secondValue the second operand
	 * @param ruleConditionOperator the operator to use
	 * @return true of the operation evaluates to true false
	 *         otherwise
	 * @throws ApplicationException if an exception was encountered
	 */
	public Boolean operate(String parameterType, Object firstValue, 
			Object secondValue, RuleConditionOperator ruleConditionOperator) throws ApplicationException;


}
