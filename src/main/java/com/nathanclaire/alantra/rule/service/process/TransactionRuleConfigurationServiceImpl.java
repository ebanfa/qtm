/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleAction;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleCondition;
import com.nathanclaire.alantra.rule.engine.RuleConditionAttribute;
import com.nathanclaire.alantra.rule.engine.RuleConditionOperator;
import com.nathanclaire.alantra.rule.engine.RuleConditionParameter;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;
import com.nathanclaire.alantra.rule.engine.qtm.QTMCondition;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRule;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRuleChain;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRuleConditionAttribute;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRuleConditionOperator;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRuleConditionParameter;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRuleSpace;
import com.nathanclaire.alantra.rule.engine.qtm.QTMRuleTable;
import com.nathanclaire.alantra.rule.model.Operator;
import com.nathanclaire.alantra.rule.model.ParameterType;
import com.nathanclaire.alantra.rule.model.ParameterTypeOperator;
import com.nathanclaire.alantra.rule.model.TransactionRule;
import com.nathanclaire.alantra.rule.model.TransactionRuleAction;
import com.nathanclaire.alantra.rule.model.TransactionRuleCategory;
import com.nathanclaire.alantra.rule.model.TransactionRuleCondition;
import com.nathanclaire.alantra.rule.model.TransactionRuleConditionAttribute;
import com.nathanclaire.alantra.rule.model.TransactionRuleConditionParameter;
import com.nathanclaire.alantra.rule.model.TransactionRuleSpace;
import com.nathanclaire.alantra.rule.model.TransactionRuleType;
import com.nathanclaire.alantra.rule.service.entity.TransactionRuleSpaceService;

/**
 * Loads rule information from an EJB "rule store". 
 * 	The {@link RuleSpace} is represented by a {@link TransactionRuleSpace} entity. 
 * 	The {@link RuleTable} is represented by a {@link TransactionRuleCategory} entity. 
 * 	The {@link RuleChain} is represented by a {@link TransactionRuleType} entity.
 * 	The {@link Rule} is represented by a {@link TransactionRule} entity. 
 * 	The {@link RuleAction} is represented by a {@link TransactionRuleAction} entity. 
 * 	The {@link RuleCondition}s are represented by a {@link TransactionRuleCondition} entity
 *  The {@link RuleConditionOperator} is represented by a {@link TransactionRuleConditionOperator} entity.
 *  The {@link RuleConditionAttribute} is represented by a {@link TransactionRuleConditionAttribute} entity.
 *  The {@link RuleConditionParameter} is represented by a {@link TransactionRuleConditionParameterValue} entity.
 * 
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleConfigurationServiceImpl extends BaseProcessService
		implements TransactionRuleConfigurationService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject TransactionRuleSpaceService ruleSpaceService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleConfigurationService#loadAll()
	 */
	@Override
	public List<RuleSpace> loadAll() throws ApplicationException {
		logger.debug("Loading all rule space definitions.");
		List<RuleSpace> ruleSpaces = new ArrayList<RuleSpace>();
		try {
			List<TransactionRuleSpace> ruleSpaceEntities = ruleSpaceService.findAll(null);
			if(ruleSpaceEntities.isEmpty())
				throw new ApplicationException(ErrorCodes.RULE_SPACE_CONFIG_ERROR_CD, ErrorCodes.RULE_SPACE_NOT_FOUND__ERROR_MSG);
			logger.debug("Loaded {} rule space definitions.", ruleSpaceEntities.size());
			for(TransactionRuleSpace ruleSpace : ruleSpaceEntities)
				ruleSpaces.add(load(ruleSpace));
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.RULE_SPACE_CONFIG_ERROR_CD, e.getMessage());
		}
		return ruleSpaces;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleConfigurationService#load(java.lang.String)
	 */
	@Override
	public RuleSpace load(String ruleSpaceCode) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{}, "TransactionRuleConfigurationService.load");
		logger.debug("Loading single rule space definition with code", ruleSpaceCode);
		RuleSpace ruleSpace = null;
		try {
			TransactionRuleSpace ruleSpaceEntity = ruleSpaceService.findByCode(ruleSpaceCode);
			if(ruleSpaceEntity == null)
				throw new ApplicationException(ErrorCodes.RULE_SPACE_CONFIG_ERROR_CD,ErrorCodes.RULE_SPACE_NOT_FOUND__ERROR_MSG);
			logger.debug("Loaded rule space definition: {}.", ruleSpaceEntity);
			ruleSpace = load(ruleSpaceEntity);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.RULE_SPACE_CONFIG_ERROR_CD);
		}
		return ruleSpace;
	}

	/**
	 * Converts a {@link TransactionRuleSpace} entity into an object that
	 * implements the {@link RuleSpace} interface.
	 * 
	 * @param ruleSpaceEntity the source rule space entity
	 * @return the converted rule space object
	 * @throws ApplicationException if an exception occurred
	 */
	private RuleSpace load(TransactionRuleSpace ruleSpaceEntity) throws ApplicationException {
		RuleSpace ruleSpace = null;
		logger.debug("Processing entity rule space definition {}", ruleSpaceEntity);
		try {
			Set<TransactionRuleCategory> transactionRuleCategories = ruleSpaceEntity.getTransactionRuleCategorys();
			List<RuleTable> ruleTables = load(transactionRuleCategories);
			 ruleSpace = new QTMRuleSpace(ruleSpaceEntity.getCode(), ruleSpaceEntity.getName(), 
					 StringUtil.flagToBoolean(ruleSpaceEntity.getDefaultRuleSpaceFg()), ruleTables);
				logger.debug("Successfully loaded rule space: {}", ruleSpace);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.RULE_SPACE_CONFIG_ERROR_CD);
		}
		return ruleSpace;
	}

	/**
	 * @param transactionRuleCategories
	 * @return
	 * @throws ApplicationException
	 */
	private List<RuleTable> load(Set<TransactionRuleCategory> transactionRuleCategories) throws ApplicationException {
		List<RuleTable> ruleTables = new ArrayList<RuleTable>();
		logger.debug("Processing {} entity rule category definitions", transactionRuleCategories.size());
		try {
			// Validate that we have rule category entities defined
			if(transactionRuleCategories.isEmpty())
				throw new ApplicationException(
						ErrorCodes.TRCS_RULE_CATEGORIES_CONFIG_ERROR_CD, 
						ErrorCodes.TRCS_NO_RULE_CATEGORIES_SPECIFIED_ERROR_MSG);
			// For each rule category, build a RuleTable object representing it.
			for(TransactionRuleCategory transactionRuleCategory : transactionRuleCategories)
				ruleTables.add(loadRuleTable(transactionRuleCategory));
			logger.debug("Successfully processed {} entity " +
					"rule category definitions into {} rule tables", transactionRuleCategories.size(), ruleTables.size());
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_CATEGORIES_CONFIG_ERROR_CD);
		}
		return ruleTables;
	}

	/**
	 * Converts a {@link TransactionRuleCategory} into a {@link RuleTable}.
	 * 
	 * @param transactionRuleCategory
	 * @return
	 * @throws ApplicationException
	 */
	private RuleTable loadRuleTable(TransactionRuleCategory transactionRuleCategory) throws ApplicationException {
		RuleTable ruleTable = null;
		logger.debug("Processing entity rule category definition {}", transactionRuleCategory);
		try {
			Set<TransactionRuleType> transactionRuleTypes = transactionRuleCategory.getTransactionRuleTypes();
			// Then load
			List<RuleChain> ruleChains = loadRuleChains(transactionRuleTypes);
			 ruleTable = new QTMRuleTable(transactionRuleCategory.getCode(), transactionRuleCategory.getName(), 
					 StringUtil.flagToBoolean('Y'), ruleChains);
				logger.debug("Successfully processed entity rule category definition {}", transactionRuleCategory);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.RULE_SPACE_CONFIG_ERROR_CD);
		}
		return ruleTable;
	}

	/**
	 * Converts a list {@link TransactionRuleType}s into a 
	 * list of {@link RuleChain}s.
	 *  
	 * @param transactionRuleTypes
	 * @return
	 * @throws ApplicationException
	 */
	private List<RuleChain> loadRuleChains(Set<TransactionRuleType> transactionRuleTypes) throws ApplicationException {
		List<RuleChain> ruleChains = new ArrayList<RuleChain>();
		logger.debug("Processing {} entity rule type definitions", transactionRuleTypes.size());
		try {
			// Validate that we have rule type entities defined
			if(transactionRuleTypes.isEmpty())
				throw new ApplicationException(
						ErrorCodes.TRCS_RULE_TYP_CONFIG_ERROR_CD, 
						ErrorCodes.TRCS_NO_RULE_TYPES_SPECIFIED_ERROR_MSG);
			for(TransactionRuleType transactionRuleType : transactionRuleTypes)
				ruleChains.add(loadRuleChain(transactionRuleType));
			logger.debug("Successfully processed {} entity " +
					"rule type definitions into {} rule chains", transactionRuleTypes.size(), ruleChains.size());
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_TYP_CONFIG_ERROR_CD);
		}
		return ruleChains;
	}

	/**
	 * @param transactionRuleType
	 * @return
	 * @throws ApplicationException
	 */
	private RuleChain loadRuleChain(TransactionRuleType transactionRuleType) throws ApplicationException {
		RuleChain ruleChain = null;
		logger.debug("Processing entity rule type definition {}", transactionRuleType);
		try {
			Set<TransactionRule> transactionRules = transactionRuleType.getTransactionRules();
			List<Rule> rules = loadRules(transactionRules);
			 ruleChain = new QTMRuleChain(transactionRuleType.getCode(), transactionRuleType.getName(), 
					 StringUtil.flagToBoolean('Y'), rules);
				logger.debug("Successfully processed entity rule type definition {}", transactionRuleType);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_TYP_CONFIG_ERROR_CD);
		}
		return ruleChain;
	}

	/**
	 * @param transactionRules
	 * @return
	 * @throws ApplicationException
	 */
	private List<Rule> loadRules(Set<TransactionRule> transactionRules) throws ApplicationException {
		List<Rule> rules = new ArrayList<Rule>();
		logger.debug("Processing {} entity rule definitions", transactionRules.size());
		try {
			// If we don't have any rules configured,
			// then we return an empty list to the caller
			if(!transactionRules.isEmpty()) {
				for(TransactionRule transactionRule : transactionRules)
					rules.add(loadRule(transactionRule));
				logger.debug("Successfully processed {} entity " +
						"rule definitions into {} rules", transactionRules.size(), rules.size());
			}
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_CONFIG_ERROR_CD);
		}
		return rules;
	}

	/**
	 * @param transactionRule
	 * @return
	 * @throws ApplicationException
	 */
	private Rule loadRule(TransactionRule transactionRule) throws ApplicationException {
		Rule rule = null;
		logger.debug("Processing entity rule definition {}", transactionRule);
		try {
			Set<TransactionRuleCondition> transactionRuleConditions = transactionRule.getTransactionRuleConditions();
			RuleAction ruleAction = loadRuleAction(transactionRule.getTransactionRuleAction());
			List<RuleCondition> conditions = loadRuleConditions(transactionRuleConditions);
			 rule = new QTMRule(transactionRule.getCode(), transactionRule.getName(), 
					 transactionRule.getOperatorModeFg().toString(), ruleAction, conditions);
				logger.debug("Successfully loaded rule {}", rule);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_CONFIG_ERROR_CD);
		}
		return rule;
	}

	/**
	 * @param transactionRuleAction
	 * @return
	 * @throws ApplicationException
	 */
	private RuleAction loadRuleAction(TransactionRuleAction transactionRuleAction) throws ApplicationException {
		RuleAction ruleAction = null;
		logger.debug("Processing entity rule action definition {}", transactionRuleAction);
		try {
			String actionClassName = transactionRuleAction.getClassName();
			logger.debug("Instantiating action class {}", actionClassName);
			ruleAction = (RuleAction) EntityUtil.newInstance(actionClassName);
			logger.debug("Successfully processed entity rule action definition {}", transactionRuleAction);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.TRCS_RULE_ACTION_CONFIG_ERROR_CD);
		}
		return ruleAction;
	}

	/**
	 * @param transactionRuleConditions
	 * @return
	 * @throws ApplicationException
	 */
	private List<RuleCondition> loadRuleConditions(Set<TransactionRuleCondition> transactionRuleConditions)  throws ApplicationException {
		List<RuleCondition> ruleConditions = new ArrayList<RuleCondition>();
		logger.debug("Processing {} entity rule condition definitions", transactionRuleConditions.size());
		try {
			// Validate that we have rules entities defined
			if(transactionRuleConditions.isEmpty())
				throw new ApplicationException(
						ErrorCodes.TRCS_RULE_CONDITION_CONFIG_ERROR_CD, 
						ErrorCodes.TRCS_NO_RULE_CONDITIONS_SPECIFIED_ERROR_MSG);
			for(TransactionRuleCondition transactionRuleCondition : transactionRuleConditions)
				ruleConditions.add(loadRuleCondition(transactionRuleCondition));
			logger.debug("Successfully processed {} entity " +
					"rule condition definitions into {} condition", transactionRuleConditions.size(), ruleConditions.size());
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_CONDITION_CONFIG_ERROR_CD);
		}
		return ruleConditions;
	}

	/**
	 * @param transactionRuleCondition
	 * @return
	 * @throws ApplicationException
	 */
	private RuleCondition loadRuleCondition(TransactionRuleCondition transactionRuleCondition) throws ApplicationException {
		RuleCondition condition = null;
		logger.debug("Processing entity rule condition definition {}", transactionRuleCondition);
		try {
			// Get the operator of this condition
			RuleConditionOperator operator = 
					loadRuleConditionOperator(transactionRuleCondition.getOperator());
			// Get the attribute defined for this condition
			RuleConditionAttribute attribute = 
					loadRuleConditionAttribute(transactionRuleCondition.getTransactionRuleConditionAttribute());
			// Load the operators that are applicable to the attribute
			List<RuleConditionOperator> applicableOperators = 
					loadApplicableOperators(transactionRuleCondition.getTransactionRuleConditionAttribute());
			// Get the parameters defined for the condition
			RuleConditionParameter parameterValue = 
					loadRuleConditionParameterValue(transactionRuleCondition.getTransactionRuleConditionParameter());
			
			// Construct
			 condition = new QTMCondition(transactionRuleCondition.getCode(), transactionRuleCondition.getName(), 
					 operator, attribute, parameterValue, applicableOperators);
				logger.debug("Successfully loaded rule condition definition {}", condition);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRCS_RULE_CONDITION_CONFIG_ERROR_CD);
		}
		return condition;
	}

	
	/**
	 * @param transactionRuleConditionOperatorEntity
	 * @return
	 * @throws ApplicationException
	 */
	private RuleConditionOperator loadRuleConditionOperator(Operator transactionRuleConditionOperatorEntity) 
			throws ApplicationException {
		RuleConditionOperator ruleConditionOperator = null;
		logger.debug("Processing entity rule condition operator definition {}", transactionRuleConditionOperatorEntity);
		try {
			ruleConditionOperator = new QTMRuleConditionOperator(transactionRuleConditionOperatorEntity.getCode(), 
					transactionRuleConditionOperatorEntity.getName(), transactionRuleConditionOperatorEntity.getOperatorSymbol());
			logger.debug("Successfully loaded entity rule condition operator {}", ruleConditionOperator);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.TRCS_RULE_CONDITION_OPERATOR_CONFIG_ERROR_CD);
		}
		return ruleConditionOperator;
	}

	/**
	 * @param transactionRuleConditionAttribute
	 * @return
	 * @throws ApplicationException
	 */
	private RuleConditionAttribute loadRuleConditionAttribute(TransactionRuleConditionAttribute transactionRuleConditionAttribute)
			throws ApplicationException {
		RuleConditionAttribute ruleAttribute = null;
		logger.debug("Processing entity rule condition attribute definition {}", transactionRuleConditionAttribute);
		try {
			ruleAttribute = new QTMRuleConditionAttribute(transactionRuleConditionAttribute.getCode(), 
					transactionRuleConditionAttribute.getName(), transactionRuleConditionAttribute.getParameterType().getCode());
			ruleAttribute.getParameterType();
			logger.debug("Successfully loaded rule condition attribute {}", ruleAttribute);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.TRCS_RULE_CONDITION_ATTRIBUTE_CONFIG_ERROR_CD);
		}
		return ruleAttribute;
	}

	/**
	 * @param transactionRuleConditionParameter
	 * @return
	 * @throws ApplicationException
	 */
	private RuleConditionParameter loadRuleConditionParameterValue(
			TransactionRuleConditionParameter transactionRuleConditionParameter)
					throws ApplicationException {
		RuleConditionParameter ruleParameter = null;
		logger.debug("Processing entity rule condition parameter definition {}", transactionRuleConditionParameter);
		try {
			ruleParameter = new QTMRuleConditionParameter(transactionRuleConditionParameter.getCode(), 
					transactionRuleConditionParameter.getName(), 
					transactionRuleConditionParameter.getParameterValue(),
					transactionRuleConditionParameter.getParameter().getParameterType().getCode());
			logger.debug("Successfully loaded rule condition parameter {}", ruleParameter);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.TRCS_RULE_CONDITION_PARAMETER_CONFIG_ERROR_CD);
		}
		return ruleParameter;
	}
	
	private List<RuleConditionOperator> loadApplicableOperators(TransactionRuleConditionAttribute transactionRuleConditionAttribute) 
			throws ApplicationException 
	{
		logger.debug("Loading applicable operators for rule " +
				"condition attribute {}", transactionRuleConditionAttribute);
		List<RuleConditionOperator> applicableOperators = new ArrayList<RuleConditionOperator>();
		try {
			// Load the parameter type that of the attribute
			ParameterType parameterType = (ParameterType) 
					EntityUtil.returnOrThrowIfNull(transactionRuleConditionAttribute.getParameterType(), 
							ErrorCodes.TRCS_RULE_CONDITION_APPLICABLE_OPERATOR_CONFIG_ERROR_CD, 
							ErrorCodes.TRCS_RULE_NO_PARAMETER_TY_SPECIFIED_ERROR_CD);
			// Get all the operator mappings for the parameter type
			Set<ParameterTypeOperator> operatorsForParamterType = parameterType.getParameterTypeOperators();
			// Validate that we have rules entities defined
			if(operatorsForParamterType.isEmpty())
				throw new ApplicationException(
						ErrorCodes.TRCS_RULE_CONDITION_APPLICABLE_OPERATOR_CONFIG_ERROR_CD, 
						ErrorCodes.TRCS_RULE_NO_OPERATOR_SPECIFIED_ERROR_CD);
			// For each operator mapping, get the operator referenced and convert it to
			// RuleConditionOperator and add to list of applicable operators
			for(ParameterTypeOperator parameterTypeOperator : operatorsForParamterType){
				Operator operatorEntity = parameterTypeOperator.getOperator();
				RuleConditionOperator operator = 
						new QTMRuleConditionOperator(operatorEntity.getCode(), 
								operatorEntity.getName(), operatorEntity.getOperatorSymbol());
				applicableOperators.add(operator);
			}
			logger.debug("Successfully loaded {} applicable operators for " +
					"rule condition attribute {} ", applicableOperators.size(), transactionRuleConditionAttribute);
		} catch (Exception e) {
			ExceptionUtil.logAndProcessException(e, ErrorCodes.TRCS_RULE_CONDITION_APPLICABLE_OPERATOR_CONFIG_ERROR_CD);
		}
		return applicableOperators;
	}
}
