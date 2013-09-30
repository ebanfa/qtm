/**
 * 
 */
package com.nathanclaire.alantra.businessobject.data;

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
	 
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public String getBusinessObjectName() ;
	
	public void setBusinessObjectName(String businessObjectName);
	
	public String getBusinessObjectClassName();
	
	public void setBusinessObjectClassName(String className);
	
	public String getProcessCategoryCode();
	
	public void setProcessCategoryCode(String categoryCode);
	
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
