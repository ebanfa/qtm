/**
 * 
 */
package com.nathanclaire.alantra.channel.handler;

import java.util.List;
import java.util.Map;

import com.nathanclaire.alantra.channel.config.ChannelConfiguration;

/**
 * Defines an abstract business object.
 * 
 * @author Edward Banfa
 *
 */
public interface BusinessObjectData {
	
	
	public String getBusinessObjectName() ;
	
	public void setBusinessObjectName(String businessObjectName);
	
	public Object getDataValue(String dataValueName);
	
	public void setDataValue(String dataValueName, Object dataValue);
	
	public Map<String, Object> getDataValues();
	
	public void setDataValues(Map<String, Object> dataValues);
	
	public Boolean isValid();
	
	public void setValid(Boolean valid);
	
	public Boolean isProcessed();
	
	public void setProcessed(Boolean valid);
	
	public Boolean isRouted();
	
	public void setRouted(Boolean valid);

	public Boolean isInboundBusinessObject();

	public Boolean isForwardBusinessObject();

	public Boolean isOutboundBusinessObject();

	public ChannelConfiguration getPrimaryOutboundRoute();

	public List<ChannelConfiguration> getSecondaryRoutes();
	

}
