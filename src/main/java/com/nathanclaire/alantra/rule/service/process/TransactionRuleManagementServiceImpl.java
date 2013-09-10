/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.EntityUtil;
import com.nathanclaire.alantra.base.util.ErrorCodes;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * Default implementation of the {@link TransactionRuleManagementService}
 * interface.
 * 
 * @author Edward Banfa
 *
 */
@Singleton
public class TransactionRuleManagementServiceImpl extends BaseProcessService
		implements TransactionRuleManagementService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<RuleSpace> ruleSpaces = new ArrayList<RuleSpace>();

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getDefaultRuleSpace()
	 */
	@Override
	public RuleSpace getDefaultRuleSpace() throws ApplicationException {
		logger.debug("Loading default rules space from set of {} managed rule spaces", ruleSpaces.size());
		for(RuleSpace ruleSpace : ruleSpaces)
			if(ruleSpace.isDefaultRuleSpace())
				return ruleSpace;
		throw new ApplicationException(ErrorCodes.TRMS_RULE_ENGINE_ERROR_CD, ErrorCodes.TRMS_RULE_SPACE_NOT_FOUND_MSG);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#setDefaultRuleSpace(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public void setDefaultRuleSpace(RuleSpace ruleSpace) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{ruleSpace}, 
				"TransactionRuleManagementServiceImpl.setDefaultRuleSpace");
		// If the list of rules spaces does not contain
		if(!ruleSpaces.contains(ruleSpace)){
			// Loop through and flip the marker on the
			// current default rule space
			for(RuleSpace ruleSpaceEntry : ruleSpaces)
				if(ruleSpaceEntry.isDefaultRuleSpace())
					ruleSpaceEntry.setDefaultRuleSpace(false);
			// then set the passed in rule space as the default
			// rule space and add to the list of managed rule spaces
			ruleSpace.setDefaultRuleSpace(true);
			ruleSpaces.add(ruleSpace);
		}
		else
		{
			// Check if the rule space is currently the
			// default rule space, and make sure no other
			// rule space in the list is marked as default
			if(ruleSpace.isDefaultRuleSpace()){
				for(RuleSpace ruleSpaceEntry : ruleSpaces)
					if(!ruleSpaceEntry.equals(ruleSpace) && ruleSpaceEntry.isDefaultRuleSpace())
						ruleSpaceEntry.setDefaultRuleSpace(false);
				return;
			}
			else{
				// Else set the rule space as default and flip the
				// marker on the rest of the rule spaces
				ruleSpace.setDefaultRuleSpace(true);
				for(RuleSpace ruleSpaceEntry : ruleSpaces)
					if(ruleSpaceEntry.isDefaultRuleSpace())
						ruleSpaceEntry.setDefaultRuleSpace(false);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getDefaultRoutingRuleChain(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public RuleChain getDefaultRoutingRuleChain(RuleSpace ruleSpace)
			throws ApplicationException {
		RuleTable routingRulesTable = getRoutingRulesTable(ruleSpace);
		List<RuleChain> ruleChains = routingRulesTable.getRuleChains();
		RuleChain defaultRuleChain = null;
		for(RuleChain chain : ruleChains)
			if(chain.getCode().equals(TransactionRuleManagementService.DEFAULT_ROUTING_RULE_CHAIN_CD))
				defaultRuleChain = chain;
		return defaultRuleChain;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getDefaultProcessingRuleChain(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public RuleChain getDefaultProcessingRuleChain(RuleSpace ruleSpace)
			throws ApplicationException {
		RuleTable processingRulesTable = getProcessingRulesTable(ruleSpace);
		List<RuleChain> ruleChains = processingRulesTable.getRuleChains();
		RuleChain defaultRuleChain = null;
		for(RuleChain chain : ruleChains)
			if(chain.getCode().equals(TransactionRuleManagementService.DEFAULT_PROCESSING_RULE_CHAIN_CD))
				defaultRuleChain = chain;
		return defaultRuleChain;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getDefaultValidationRuleChain(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public RuleChain getDefaultValidationRuleChain(RuleSpace ruleSpace)
			throws ApplicationException {
		RuleTable validationRulesTable = getValidationRulesTable(ruleSpace);
		List<RuleChain> ruleChains = validationRulesTable.getRuleChains();
		RuleChain defaultRuleChain = null;
		for(RuleChain chain : ruleChains)
			if(chain.getCode().equals(TransactionRuleManagementService.DEFAULT_VALIDATION_RULE_CHAIN_CD))
				defaultRuleChain = chain;
		return defaultRuleChain;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getRoutingRulesTable(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public RuleTable getRoutingRulesTable(RuleSpace ruleSpace)
			throws ApplicationException {
		List<RuleTable> ruleTables = ruleSpace.getRuleTables();
		RuleTable routingRulesTable = null;
		for(RuleTable table : ruleTables)
			if(table.getCode().equals(TransactionRuleManagementService.ROUTING_RULE_TABLE_CD))
				routingRulesTable = table;
		return routingRulesTable;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getProcessingRulesTable(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public RuleTable getProcessingRulesTable(RuleSpace ruleSpace)
			throws ApplicationException {
		List<RuleTable> ruleTables = ruleSpace.getRuleTables();
		RuleTable processingRulesTable = null;
		for(RuleTable table : ruleTables)
			if(table.getCode().equals(TransactionRuleManagementService.PROCESSING_RULE_TABLE_CD))
				processingRulesTable = table;
		return processingRulesTable;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#getValidationRulesTable(com.nathanclaire.alantra.rule.engine.RuleSpace)
	 */
	@Override
	public RuleTable getValidationRulesTable(RuleSpace ruleSpace)
			throws ApplicationException {
		List<RuleTable> ruleTables = ruleSpace.getRuleTables();
		RuleTable validationRulesTable = null;
		for(RuleTable table : ruleTables)
			if(table.getCode().equals(TransactionRuleManagementService.VALIDATION_RULE_TABLE_CD))
				validationRulesTable = table;
		return validationRulesTable;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#start(java.util.List)
	 */
	@Override
	public void start(List<RuleSpace> ruleSpaces) throws ApplicationException {
		EntityUtil.returnOrThrowIfParamsArrayContainsNull(new Object[]{ruleSpaces}, 
				ErrorCodes.TRMS_RULE_ENGINE_START_ERROR_CD);
		this.ruleSpaces = ruleSpaces;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.rule.service.process.TransactionRuleManagementService#stop()
	 */
	@Override
	public void stop() throws ApplicationException {
		ruleSpaces = null;
	}
}
