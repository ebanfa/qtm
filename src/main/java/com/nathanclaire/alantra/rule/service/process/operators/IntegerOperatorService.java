/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process.operators;

import java.math.BigDecimal;

import javax.ejb.Stateless;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.IntegerUtil;
import com.nathanclaire.alantra.rule.annotation.IntegerOperator;
import com.nathanclaire.alantra.rule.engine.ParameterTypes;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;


/**
 * This operator knows how to operate on {@link Integer}'s.
 * It supports the following operators:
 *  1. > (Greater than)
 *  2. < (Less than)
 *  3. == (Equals to)
 *  
 * @author Edward Banfa
 *
 */
@Stateless
@IntegerOperator
public class IntegerOperatorService extends BaseOperatorService {

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.operators.BaseOperatorService#operate(java.lang.Object, java.lang.Object, com.nathanclaire.alantra.rule.engine.RuleConditionOperator)
	 */
	@Override
	protected Boolean operate(Object firstValue, Object secondValue, RuleConditionOperator ruleConditionOperator)
			throws ApplicationException {
		// Convert the value objects into Integers
		Integer firstValueAsInteger = IntegerUtil.toInteger(firstValue);
		Integer secondValueAsInteger = IntegerUtil.toInteger(secondValue);
		// Find out what kind of operation we are going to be carrying
		// out and call InterUtil to do all the hard work.
		String operatorSymbol = ruleConditionOperator.getSymbol();
		if(operatorSymbol.equals(RuleConditionOperator.LESS_THAN_OPERATOR))
			return IntegerUtil.isLessThan(firstValueAsInteger, secondValueAsInteger);
		if(operatorSymbol.equals(RuleConditionOperator.EQUALS_TO_OPERATOR))
			return IntegerUtil.isEqualsTo(firstValueAsInteger, secondValueAsInteger);
		if(operatorSymbol.equals(RuleConditionOperator.GREATER_THAN_OPERATOR))
			return IntegerUtil.isGreaterThan(firstValueAsInteger, secondValueAsInteger);
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
