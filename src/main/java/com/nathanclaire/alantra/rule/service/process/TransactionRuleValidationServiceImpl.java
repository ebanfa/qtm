/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.businessobject.data.BusinessObjectData;
import com.nathanclaire.alantra.businessobject.service.process.BusinessObjectCreationService;
import com.nathanclaire.alantra.businessobject.util.BusinessObjectUtil;
import com.nathanclaire.alantra.rule.annotation.BusinessObjectValidationEvent;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleAction;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.event.BusinessObjectEvent;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class TransactionRuleValidationServiceImpl extends BaseProcessService
		implements TransactionRuleValidationService {

	@Inject TransactionRuleMatchingService matchingService;
	@Inject TransactionRuleManagementService managementService;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Inject BusinessObjectCreationService objectCreationService;
	@Inject @BusinessObjectValidationEvent Event<BusinessObjectEvent> event;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleValidationService#validate(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean validate(BusinessObjectData businessObjectData) throws ApplicationException {
		try {
			logger.debug("Validating business object: {}", businessObjectData);
			// Currently only uses the default rule space
			RuleSpace ruleSpace = managementService.getDefaultRuleSpace();
			// Use the default rule chain in the validation rules table
			RuleChain ruleChain = managementService.getDefaultValidationRuleChain(ruleSpace);
			if(ruleChain == null)
					throw new ApplicationException(
							ErrorCodes.TRVS_RULE_CONFIG_ERROR_CD,
							ErrorCodes.TRVS_NO_VALIDATION_RULES_CHAIN_FOUND_ERROR_MSG);
			// Match the business object with the rules defined in the rules chain
			List<Rule> rules = matchingService.match(ruleChain, businessObjectData);
			logger.debug("Validating business object: {} against {} rules", businessObjectData, rules.size());
			for(Rule rule : rules){
				RuleAction ruleAction = rule.getRuleAction();
				ruleAction.execute(businessObjectData);
			}
			if(!rules.isEmpty()){
				logger.debug("Validation success for business object: {}", businessObjectData);
				businessObjectData.setValid(true);
				objectCreationService.create(businessObjectData);
				event.fire(new BusinessObjectEvent(BusinessObjectUtil.clone(businessObjectData)));
				return true;
			}
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRVS_TRANSACTION_RULE_VALIDATION_ERROR_CD);
		}
		logger.debug("Validation failed for business object: {}", businessObjectData);;
		businessObjectData.setValid(false);
		event.fire(new BusinessObjectEvent(BusinessObjectUtil.clone(businessObjectData)));
		return false;
	}
}
