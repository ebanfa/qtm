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
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
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
public class TransactionRuleRoutingServiceImpl extends BaseProcessService
		implements TransactionRuleRoutingService {
	
	@Inject TransactionRuleMatchingService matchingService;
	@Inject TransactionRuleManagementService managementService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService#route(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public BusinessObjectData route(BusinessObjectData businessObjectData) throws ApplicationException {
		try {
			logger.debug("Routing {} ", businessObjectData);
			// Currently only uses the default rule space
			RuleSpace ruleSpace = managementService.getDefaultRuleSpace();
			// Use the default rule chain in the routing rules table
			RuleChain ruleChain = managementService.getDefaultRoutingRuleChain(ruleSpace);
			if(ruleChain == null)
					throw new ApplicationException(
							ErrorCodes.TRRS_RULE_CONFIG_ERROR_CD,
							ErrorCodes.TRRS_NO_ROUTING_RULES_CHAIN_FOUND_ERROR_MSG);
			// Match the business object with the rules defined in the rules chain
			List<Rule> rules = matchingService.match(ruleChain, businessObjectData);
			logger.debug("Routing business object: {} against {} rules", businessObjectData, rules.size());
			for(Rule rule : rules){
				RuleAction ruleAction = rule.getRuleAction();
				ruleAction.execute(businessObjectData);
			}
			logger.debug("Routing execution complete for: {} ", businessObjectData);
		} catch (Exception e) {
			ExceptionUtil.processException(e, ErrorCodes.TRRS_TRANSACTION_RULE_VALIDATION_ERROR_CD);
		}
		return businessObjectData;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService#isInboundTransaction(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean isInboundBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException {
		return businessObjectData.isInboundBusinessObject();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService#isForwardTransaction(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean isForwardBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException {
		return businessObjectData.isForwardBusinessObject();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService#isOutboundTransaction(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean isOutboundBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException {
		return businessObjectData.isOutboundBusinessObject();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService#getPrimaryRoute(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public ChannelConfiguration getPrimaryRoute(BusinessObjectData businessObjectData) throws ApplicationException {
		return businessObjectData.getPrimaryOutboundRoute();
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleRoutingService#getSecondaryRoutes(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public List<ChannelConfiguration> getSecondaryRoutes(BusinessObjectData businessObjectData) throws ApplicationException {
		return businessObjectData.getSecondaryRoutes();
	}

}
