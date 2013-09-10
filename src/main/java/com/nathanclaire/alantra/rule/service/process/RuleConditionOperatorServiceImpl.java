/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.rule.annotation.BigDecimalOperator;
import com.nathanclaire.alantra.rule.annotation.DateOperator;
import com.nathanclaire.alantra.rule.annotation.IntegerOperator;
import com.nathanclaire.alantra.rule.annotation.StringOperator;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;
import com.nathanclaire.alantra.rule.engine.ParameterTypes;
import com.nathanclaire.alantra.rule.service.process.operators.OperatorService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class RuleConditionOperatorServiceImpl extends BaseProcessService implements RuleConditionOperatorService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject @DateOperator OperatorService dateOperatorService;
	@Inject @StringOperator OperatorService stringOperatorService;
	@Inject @IntegerOperator OperatorService integerOperatorService;
	@Inject @BigDecimalOperator OperatorService bigDecimalOperatorService;
	
	@Override
	public Boolean operate(String parameterType, Object firstValue,	Object secondValue, RuleConditionOperator ruleConditionOperator) 
			throws ApplicationException 
	{
		logger.debug("Running operator {} on parameter type {} " +
				"on operands, First value: {}. Second value {}", ruleConditionOperator, parameterType, firstValue, secondValue);
		if(parameterType.equals(ParameterTypes.DATE_PARAMETER_TY))
			return dateOperatorService.operate(parameterType, firstValue, secondValue, ruleConditionOperator);
		else if(parameterType.equals(ParameterTypes.STRING_PARAMETER_TY))
			return stringOperatorService.operate(parameterType, firstValue, secondValue, ruleConditionOperator);
		else if(parameterType.equals(ParameterTypes.INTEGER_PARAMETER_TY))
			return integerOperatorService.operate(parameterType, firstValue, secondValue, ruleConditionOperator);
		else if(parameterType.equals(ParameterTypes.DECIMAL_PARAMETER_TY))
			return bigDecimalOperatorService.operate(parameterType, firstValue, secondValue, ruleConditionOperator);
		else
			throw new ApplicationException(
					ErrorCodes.RCOS_OPERATION_ERROR_CD, 
					ErrorCodes.RCOS_INVALID_PARAMETER_TY_ERROR_MSG);
	}

}
