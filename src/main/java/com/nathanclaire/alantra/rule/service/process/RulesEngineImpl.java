/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;

/**
 * @author Edward Banfa
 *
 */
@Stateless
public class RulesEngineImpl implements RulesEngine {

	@Inject TransactionRuleRoutingService routingService;
	@Inject TransactionRuleValidationService validationService;
	@Inject TransactionRuleProcessingService processingService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#validate(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean validate(BusinessObjectData businessData)
			throws ApplicationException {
		return validationService.validate(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#process(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public void process(BusinessObjectData businessData)
			throws ApplicationException {
		processingService.process(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#route(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public BusinessObjectData route(BusinessObjectData businessData)
			throws ApplicationException {
		return routingService.route(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#isInboundBusinessObject(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean isInboundBusinessObject(BusinessObjectData businessData)
			throws ApplicationException {
		return routingService.isInboundBusinessObject(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#isForwardBusinessObject(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean isForwardBusinessObject(BusinessObjectData businessData)
			throws ApplicationException {
		return routingService.isForwardBusinessObject(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#isOutboundBusinessObject(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public Boolean isOutboundBusinessObject(BusinessObjectData businessData)
			throws ApplicationException {
		return routingService.isOutboundBusinessObject(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#getPrimaryRoute(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public ChannelConfiguration getPrimaryRoute(BusinessObjectData businessData)
			throws ApplicationException {
		return routingService.getPrimaryRoute(businessData);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.RulesEngine#getSecondaryRoutes(com.nathanclaire.alantra.rule.engine.BusinessObjectData)
	 */
	@Override
	public List<ChannelConfiguration> getSecondaryRoutes(
			BusinessObjectData businessData) throws ApplicationException {
		return routingService.getSecondaryRoutes(businessData);
	}

}

