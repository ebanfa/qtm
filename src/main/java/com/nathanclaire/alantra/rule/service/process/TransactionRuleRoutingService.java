/**
 * 
 */
package com.nathanclaire.alantra.rule.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.channel.config.ChannelConfiguration;
import com.nathanclaire.alantra.channel.handler.BusinessObjectData;
import com.nathanclaire.alantra.rule.engine.Rule;
import com.nathanclaire.alantra.rule.engine.RuleChain;
import com.nathanclaire.alantra.rule.engine.RuleSpace;

/**
 * @author Edward Banfa
 *
 */
public interface TransactionRuleRoutingService {
	
	/**
	 * Processes a {@link BusinessObjectData} against all the {@link Rule}s
	 * defined within the ROUTE table of the default {@link RuleSpace}. The 
	 * actual {@link RuleChain}s that will be consulted will depend on whether
	 * the business object data is an INBOUND, OUTBOUND, or a FORWARD business object data
	 * 
	 * @param businessObjectData the business object data
	 * @return the processed business object data
	 * @throws ApplicationException  if an exception was encountered 
	 */
	public BusinessObjectData route(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Returns true if the {@link BusinessObjectData}'s final destination is
	 * the current system
	 * 
	 * @param businessObjectData the business object data
	 * @return true if this is an inbound business object packet
	 * @throws ApplicationException  if an exception was encountered
	 */
	public Boolean isInboundBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Returns true if the {@link BusinessObjectData}'s is being routed through the current system
	 * 
	 * @param businessObjectData the business object data
	 * @return true if this is a routed business object packet
	 * @throws ApplicationException  if an exception was encountered
	 */
	public Boolean isForwardBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * @param businessObjectData the business object data
	 * @return true if this is an outbound packet
	 * @throws ApplicationException  if an exception was encountered
	 */
	public Boolean isOutboundBusinessObject(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Get the primary route for a {@link BusinessObjectData}. The primary route
	 * will depend on the out come of processing the routing rules for this
	 * business object.
	 * 
	 * @param businessObjectData the business object data
	 * @return the {@link ChannelConfiguration} that represents the primary
	 *         output channel/interface for this business object
	 * @throws ApplicationException  if an exception was encountered
	 */
	public ChannelConfiguration getPrimaryRoute(BusinessObjectData businessObjectData) throws ApplicationException;
	 
	/**
	 * Get all the secondary routes for a {@link BusinessObjectData}.The secondary routes
	 * will depend on the out come of processing the routing rules for this business object.
	 * @param businessObjectData the business object data.
	 * 
	 * @return the list of {@link ChannelConfiguration}s that represents the secondary
	 *         output channels/interfaces for this business object
	 * @throws ApplicationException  if an exception was encountered
	 */
	public List<ChannelConfiguration> getSecondaryRoutes(BusinessObjectData businessObjectData) throws ApplicationException;

}
