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
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleAction;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleProcessingServiceImpl extends BaseProcessService
		implements TransactionRuleProcessingService {
	
	@Inject TransactionRuleMatchingService matchingService;
	@Inject TransactionRuleManagementService managementService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleProcessingService#process(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public void process(BusinessObjectData businessObjectData) throws ApplicationException {
		try {
			logger.debug("Processing {} ", businessObjectData);
			// Currently only uses the default rule space
			RuleSpace ruleSpace = managementService.getDefaultRuleSpace();
			// Use the default rule chain in the processing rules table
			RuleChain ruleChain = managementService.getDefaultProcessingRuleChain(ruleSpace);
			if(ruleChain == null)
					throw new ApplicationException(
							ErrorCodes.RULE_CONFIG_ERROR_CD,
							ErrorCodes.NO_PROCESSING_RULES_CHAIN_FOUND_ERROR_MSG);
			// Match the business object with the rules defined in the rules chain
			List<Rule> rules = matchingService.match(ruleChain, businessObjectData);
			logger.debug("Processing business object: {} against {} rules", businessObjectData, rules.size());
			for(Rule rule : rules){
				RuleAction ruleAction = rule.getRuleAction();
				ruleAction.execute(businessObjectData);
			}
			logger.debug("Processing rules execution complete for business object: {}", businessObjectData);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRPS_TRANSACTION_RULE_PROCESSING_ERROR_CD);
		}
	}
}
