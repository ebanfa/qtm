/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.RuleCondition;
import com.nathanclaire.alantra.rule.engine.RuleConditionAttribute;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;
import com.nathanclaire.alantra.rule.engine.RuleConditionParameter;

/**
 * An implementation class the uses {@link RuleConditionOperatorService} 
 * to evaluate a rule condition {@link RuleCondition}. 
 * The business object attribute's {@link RuleConditionAttribute} value and 
 * the business object parameter's {@link RuleConditionParameter} value are 
 * used as the operands and the rule condition's {@link RuleConditionOperator} 
 * code is used as the operator.
 * . 
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleConditionEvaluationServiceImpl extends
		BaseProcessService implements TransactionRuleConditionEvaluationService {
	
	@Inject RuleConditionOperatorService comparatorService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject TransactionAttributeValueService attributeValueService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleConditionEvaluationService#evaluate(com.nathanclaire.alantra.rule.engine.RuleCondition, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean evaluate(RuleCondition ruleCondition, BusinessObjectData businessObjectData) 
			throws ApplicationException 
	{
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{}, 
				"TransactionRuleConditionEvaluationService.evaluate");
		// Get the required information for evaluation
		RuleConditionOperator ruleConditionOperator = ruleCondition.getRuleConditionOperator();
		RuleConditionAttribute attribute = ruleCondition.getRuleConditionAttribute();
		RuleConditionParameter parameter = ruleCondition.getRuleConditionParameter();
		
		logger.debug("Evaluating rule condition {}. Using operator {} on attribute {} and parameter {}. " +
				"Operand type is {}", ruleCondition, ruleConditionOperator, attribute, parameter, parameter.getParameterType());
		
		Boolean evaluationResult = false;
		try {
			// Do preliminary validations on parameter type and
			// operator type
			if(!parameter.getParameterType().equals(attribute.getParameterType()))
				throw new ApplicationException(
						ErrorCodes.TRCES_CONDITION_EVALUATION_ERROR_CD, 
						ErrorCodes.TRCES_PARAM_TY_DONT_MATCH_ERROR_MSG);
			if(!validateOperator(ruleCondition))
				throw new ApplicationException(
						ErrorCodes.TRCES_CONDITION_EVALUATION_ERROR_CD, 
						ErrorCodes.TRCES_INVALID_OPERATOR_ERROR_MSG);
			// Get the value of the attribute referenced by rule condition
			Object attributeValue = attributeValueService.getAttributeValue(attribute.getName(), businessObjectData);
			// Do comparison
			evaluationResult = comparatorService.operate(
					parameter.getParameterType(), parameter.getValue(), attributeValue, ruleConditionOperator);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.TRCES_CONDITION_EVALUATION_ERROR_CD);
		}
		logger.debug("Evaluation result for rule condition {} is {}", ruleCondition, evaluationResult);
		return evaluationResult;
	}
	
	/**
	 * Check if operator configured for a rule condition is valid.
	 * Basically just checks if the operator is among the list of 
	 * operators that are applicable for the rule condition.
	 * 
	 * @param ruleCondition the rule condition to validate.
	 * @return true if the operator is valid for the condition
	 *         or false otherwise.
	 */
	private Boolean validateOperator(RuleCondition ruleCondition)
	{
		// Get the applicable operators for the rule condition
		// loop through and compare the code of each with the
		// code of the operator that has been configured for the
		// condition.
		List<RuleConditionOperator> ruleConditionOperators = ruleCondition.getApplicableOperators();
		for(RuleConditionOperator ruleConditionOperator : ruleConditionOperators)
			if(ruleConditionOperator.getCode().equals(ruleCondition.getRuleConditionOperator().getCode()))
				return true;
		return false;
	}
}
