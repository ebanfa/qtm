/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process.operators;

import java.math.BigDecimal;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.rule.annotation.StringOperator;
import com.nathanclaire.alantra.rule.engine.ParameterTypes;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;


/**
 * This operator knows how to operate on {@link BigDecimal}'s.
 * It supports the following operators:
 *  1. == (Equals to)
 *  
 * @author Edward Banfa
 *
 */
@Stateless
@StringOperator
public class StringOperatorService extends BaseOperatorService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#operate(java.lang.Object, java.lang.Object, com.nathanclaire.alantra.rule.engine.RuleConditionOperator)
	 */
	@Override
	protected Boolean operate(Object firstValue, Object secondValue, 
			RuleConditionOperator ruleConditionOperator) throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{firstValue, 
				secondValue, ruleConditionOperator}, "StringOperatorService.operate");
		if(firstValue.toString().equals(secondValue.toString()))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#isValidOperator(com.nathanclaire.alantra.rule.engine.RuleConditionOperator)
	 */
	@Override
	protected Boolean isValidOperator(RuleConditionOperator ruleConditionOperator) {
		String operatorSymbol = ruleConditionOperator.getSymbol();
		if(operatorSymbol.equals(RuleConditionOperator.EQUALS_TO_OPERATOR))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#isValidParameterType(java.lang.String)
	 */
	@Override
	protected Boolean isValidParameterType(String parameterType) throws ApplicationException {
		if(parameterType.equals(ParameterTypes.STRING_PARAMETER_TY))
			return true;
		return false;
	}
}
