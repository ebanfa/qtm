/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleCondition;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * Default implementation of {@link TransactionRuleMatchingService}.
 * 
 * @author Edward Banfa
 */
@Stateless
public class TransactionRuleMatchingServiceImpl extends BaseProcessService
		implements TransactionRuleMatchingService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject TransactionRuleConditionEvaluationService evaluationService;
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleMatchingService#match(com.nathanclaire.alantra.rule.engine.RuleSpace, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public List<Rule> match(RuleSpace ruleSpace, BusinessObjectData businessObjectData) throws ApplicationException {
		List<Rule> allMatchingRulesInRuleSpace = new ArrayList<Rule>();
		logger.debug("Matching all rules in rule space {} with transaction:{}", ruleSpace, businessObjectData);
		try {
			for(RuleTable ruleTable : ruleSpace.getRuleTables()){
				List<Rule> allMatchingRulesInTable = match(ruleTable, businessObjectData);
				allMatchingRulesInRuleSpace.addAll(allMatchingRulesInTable);
			}
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRMS2_RULE_MATCHING_ERROR_CD);
		}
		logger.debug("Matched {} rules in rule space {} against transaction: {}", 
				allMatchingRulesInRuleSpace.size(), ruleSpace.getCode(), businessObjectData);
		return allMatchingRulesInRuleSpace;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleMatchingService#match(com.nathanclaire.alantra.rule.engine.RuleTable, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public List<Rule> match(RuleTable ruleTable, BusinessObjectData businessObjectData) throws ApplicationException {
		List<Rule> allMatchingRulesInRuleTable = new ArrayList<Rule>();
		logger.debug("Matching all rules in rule table {} with transaction:{}", ruleTable, businessObjectData);
		try {
			List<RuleChain> ruleChains = ruleTable.getRuleChains();
			for(RuleChain ruleChain : ruleChains){
				List<Rule> allMatchingRulesInChain = match(ruleChain, businessObjectData);
				allMatchingRulesInRuleTable.addAll(allMatchingRulesInChain);
			}
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRMS2_RULE_MATCHING_ERROR_CD);
		}
		logger.debug("Matched {} rules in rule table {} against transaction: {}", 
				allMatchingRulesInRuleTable.size(), ruleTable, businessObjectData);
		return allMatchingRulesInRuleTable;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleMatchingService#match(com.nathanclaire.alantra.rule.engine.RuleChain, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public List<Rule> match(RuleChain ruleChain, BusinessObjectData businessObjectData) throws ApplicationException {
		List<Rule> allMatchingRulesInRuleChain = new ArrayList<Rule>();
		logger.debug("Matching all rules in rule chain {} with transaction:{}", ruleChain, businessObjectData);
		try {
			List<Rule> rules = ruleChain.getRules();
			allMatchingRulesInRuleChain.addAll(match(rules, businessObjectData));
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRMS2_RULE_MATCHING_ERROR_CD);
		}
		logger.debug("Matched {} rules in rule chain {} against transaction: {}", 
				allMatchingRulesInRuleChain.size(), ruleChain, businessObjectData);
		return allMatchingRulesInRuleChain;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleMatchingService#match(java.util.List, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public List<Rule> match(List<Rule> rules, BusinessObjectData businessObjectData)
			throws ApplicationException {
		List<Rule> allMatchingRules = new ArrayList<Rule>();
		logger.debug("Matching {} rules with transaction:{}", rules.size(), businessObjectData);
		try {
			for(Rule rule : rules)
			{
				// Only process match against rules in the same process category 
				// (module) as the business object
				if(rule.getProcessCategoryCode().equals(businessObjectData.getProcessCategoryCode()))
					if(match(rule, businessObjectData))
						allMatchingRules.add(rule);
			}
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRMS2_RULE_MATCHING_ERROR_CD);
		}
		logger.debug("Matched {} rules against transaction: {}", allMatchingRules.size(), businessObjectData);
		return allMatchingRules;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleMatchingService#match(com.nathanclaire.alantra.rule.engine.Rule, com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean match(Rule rule, BusinessObjectData businessObjectData)
			throws ApplicationException {
		logger.debug("Matching rule {} against transaction: {}", rule, businessObjectData);
		List<RuleCondition> ruleConditions = rule.getRuleConditions();
		for(RuleCondition ruleCondition : ruleConditions) {
			if(evaluationService.evaluate(ruleCondition, businessObjectData)){
				logger.debug("Rule successfully matched with transaction: {}", rule, businessObjectData);
				return true;
			}
		}
		logger.debug("Rule {} not matched with transaction: {}", rule, businessObjectData);
		return false;
	}
}
