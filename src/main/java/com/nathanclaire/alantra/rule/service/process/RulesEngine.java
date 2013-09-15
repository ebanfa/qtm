/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.rule.engine.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;
import com.nathanclaire.alantra.rule.engine.RuleTable;

/**
 * The primary interface of the QTM Rules Engine.
 * 
 * @author Edward Banfa
 *
 */
public interface RulesEngine {

	/**
	 * Validate a {@link BusinessObjectData} against all the
	 * {@link Rule}s defined with the VALIDATE {@link RuleTable}
	 * defined with the default {@link RuleSpace}. The {@link RuleChain}s that
	 * will be consulted will depend on whether the transaction is an INPUT,
	 * ROUTE or an OUTPUT transaction.
	 *  
	 * @param businessObjectData the transaction data
	 * @return true if the transaction did not match with any of the
	 * evaluated rules and false otherwise
	 * @throws ApplicationException if an exception was encountered
	 */
	public Boolean validate(BusinessObjectData businessObjectData) throws ApplicationException;
	

	/**
	 * Process a {@link BusinessObjectData} transaction against all the rules,
	 * defined in the default chain of the PROCESS table in 
	 * the default {@link RuleSpace}
	 * 
	 * @param businessObjectData the transaction
	 * @throws ApplicationException if an exception was encountered
	 */
	public void process(BusinessObjectData businessObjectData) throws ApplicationException;
	
	/**
	 * Processes a {@link BusinessObjectData} against all the {@link Rule}s
	 * defined within the ROUTE table of the default {@link RuleSpace}. The 
	 * actual {@link RuleChain}s that will be consulted will depend on whether
	 * the transaction data is an INBOUND, OUTBOUND, or a FORWARD transaction data
	 * 
	 * @param businessObjectData the transaction data
	 * @return the processed transaction data
	 * @throws ApplicationException  if an exception was encountered 
	 */
	public BusinessObjectData route(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Returns true if the {@link BusinessObjectData}'s final destination is
	 * the current system
	 * 
	 * @param businessObjectData the transaction data
	 * @return true if this is an inbound transaction packet
	 * @throws ApplicationException  if an exception was encountered
	 */
	public Boolean isInboundBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Returns true if the {@link BusinessObjectData}'s is being routed through the current system
	 * 
	 * @param businessObjectData the transaction data
	 * @return true if this is a routed transaction packet
	 * @throws ApplicationException  if an exception was encountered
	 */
	public Boolean isForwardBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * @param businessObjectData the transaction data
	 * @return true if this is an outbound packet
	 * @throws ApplicationException  if an exception was encountered
	 */
	public Boolean isOutboundBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Get the primary route for a {@link BusinessObjectData}. The primary route
	 * will depend on the out come of processing the routing rules for this
	 * transactions.
	 * 
	 * @param businessObjectData the transaction data
	 * @return the {@link ChannelConfiguration} that represents the primary
	 *         output channel/interface for this transaction
	 * @throws ApplicationException  if an exception was encountered
	 */
	public ChannelConfiguration getPrimaryRoute(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Get all the secondary routes for a {@link BusinessObjectData}.The secondary routes
	 * will depend on the out come of processing the routing rules for this transaction.
	 * @param businessObjectData the transaction data.
	 * 
	 * @return the list of {@link ChannelConfiguration}s that represents the secondary
	 *         output channels/interfaces for this transaction
	 * @throws ApplicationException  if an exception was encountered
	 */
	public List<ChannelConfiguration> getSecondaryRoutes(BusinessObjectData businessObjectData) throws ApplicationException;

}
