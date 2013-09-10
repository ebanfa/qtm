/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;

/**
 * Base class for operators. Implements the operator functionality
 * by delegating data type specific parts of the operation to the
 * data type specific subclasses, eg: {@link StringOperatorService}
 * 
 * @author Edward Banfa
 *
 */
public abstract class BaseOperatorService implements OperatorService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#
	 * operate(java.lang.String, java.lang.Object, java.lang.Object, com.nathanclaire.alantra.rule.engine.RuleConditionOperator)
	 */
	@Override
	public Boolean operate(String parameterType, Object firstValue,	
			Object secondValue, RuleConditionOperator ruleConditionOperator) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{parameterType, 
				firstValue, secondValue, ruleConditionOperator}, "BaseOperatorService.operate");
		logger.debug("Processing operation: {} request for " +
				"values {} and {}", ruleConditionOperator.getSymbol(), firstValue, secondValue);
		// Check if he parameter type is the right one for us
		if(!isValidParameterType(parameterType))
			throw new ApplicationException(ErrorCodes.OPS_OPERATIONS_ERROR_CD, ErrorCodes.OPS_INVALID_PARAM_TY_ERROR_MSG);
		// Check if we support the operator, ie I may be a string operator and
		// only support the == (logical equals) operator
		if(!isValidOperator(ruleConditionOperator))
			throw new ApplicationException(ErrorCodes.OPS_OPERATIONS_ERROR_CD, ErrorCodes.OPS_INVALID_OPERATOR_TY_ERROR_MSG);
		// All seems good now we can operate.
		return operate(firstValue, secondValue, ruleConditionOperator);
	}

	/**
	 * @param firstValue
	 * @param secondValue
	 * @param ruleConditionOperator
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract Boolean operate(Object firstValue, Object secondValue,
			RuleConditionOperator ruleConditionOperator)
			throws ApplicationException;
	
	/**
	 * @param ruleConditionOperator
	 * @return
	 */
	protected abstract Boolean isValidOperator(
			RuleConditionOperator ruleConditionOperator);

	/**
	 * @param parameterType
	 * @return
	 * @throws ApplicationException
	 */
	protected abstract Boolean isValidParameterType(String parameterType)
			throws ApplicationException;
	
}
