/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process.operators;

import java.util.Date;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.rule.annotation.DateOperator;
import com.nathanclaire.alantra.rule.engine.ParameterTypes;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;


/**
 * This operator knows how to operate on {@link Date}'s.
 * It supports the following operators:
 *  1. > (Greater than)
 *  2. < (Less than)
 *  3. == (Equals to)
 *  
 * @author Edward Banfa
 *
 */
@Stateless
@DateOperator
public class DateOperatorService extends BaseOperatorService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#operate(java.lang.Object, java.lang.Object, com.nathanclaire.alantra.rule.engine.RuleConditionOperator)
	 */
	@Override
	protected Boolean operate(Object firstValue, Object secondValue,
			RuleConditionOperator ruleConditionOperator)
			throws ApplicationException {
		// Convert the value objects into Dates
		Date firstValueAsDate = DateUtil.toDate(firstValue);
		Date secondValueAsDate = DateUtil.toDate(secondValue);
		// Find out what kind of operation we are going to be carrying
		// out and call DateUtil to do all the hard work.
		String operatorSymbol = ruleConditionOperator.getSymbol();
		if(operatorSymbol.equals(RuleConditionOperator.LESS_THAN_OPERATOR))
			return DateUtil.isLessThan(firstValueAsDate, secondValueAsDate);
		if(operatorSymbol.equals(RuleConditionOperator.EQUALS_TO_OPERATOR))
			return DateUtil.isEqualsTo(firstValueAsDate, secondValueAsDate);
		if(operatorSymbol.equals(RuleConditionOperator.GREATER_THAN_OPERATOR))
			return DateUtil.isGreaterThan(firstValueAsDate, secondValueAsDate);
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#isValidOperator(com.nathanclaire.alantra.rule.engine.RuleConditionOperator)
	 */
	@Override
	protected Boolean isValidOperator(RuleConditionOperator ruleConditionOperator) {
		String operatorSymbol = ruleConditionOperator.getSymbol();
		if(operatorSymbol.equals(RuleConditionOperator.LESS_THAN_OPERATOR))
			return true;
		if(operatorSymbol.equals(RuleConditionOperator.EQUALS_TO_OPERATOR))
			return true;
		if(operatorSymbol.equals(RuleConditionOperator.GREATER_THAN_OPERATOR))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#isValidParameterType(java.lang.String)
	 */
	@Override
	protected Boolean isValidParameterType(String parameterType)
			throws ApplicationException {
		if(parameterType.equals(ParameterTypes.INTEGER_PARAMETER_TY))
			return true;
		return false;
	}

}
