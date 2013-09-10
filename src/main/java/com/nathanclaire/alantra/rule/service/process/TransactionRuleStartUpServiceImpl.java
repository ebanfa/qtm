/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseTimerService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.ExceptionUtil;
import com.nathanclaire.alantra.rule.engine.RuleSpace;


/**
 * @author Edward Banfa
 *
 */
@Startup
@Singleton
public class TransactionRuleStartUpServiceImpl extends BaseTimerService
		implements TransactionRuleStartUpService {
	
	@Inject TransactionRuleManagementService managementService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Inject TransactionRuleConfigurationService configurationService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleStartUpService#start()
	 */
	@Override
	@PostConstruct
	public void start() {
		try {
			logger.debug("Staring QTM transaction rules processing engine.");
			List<RuleSpace> ruleSpaces = configurationService.loadAll();
			managementService.start(ruleSpaces);
			logger.debug("QTM transaction rules processing engine started.");
		} catch (ApplicationException e) {
			ExceptionUtil.logException(e);
		}

	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleStartUpService#shutdown()
	 */
	@Override
	@PreDestroy
	public void shutdown() {
		try {
			logger.debug("Shutting down QTM transaction rules processing engine.");
			managementService.stop();
			logger.debug("QTM transaction rules processing engine shutdown complete.");
		} catch (ApplicationException e) {
			ExceptionUtil.logException(e);
		}
	}

}
