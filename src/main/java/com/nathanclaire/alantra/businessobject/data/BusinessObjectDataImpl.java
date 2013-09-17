/**
 * 
 */
package com.nathanclaire.alantra.businessobject.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nathanclaire.alantra.channel.config.ChannelConfiguration;

/**
 * Default implementation of {@link BusinessObjectData}.
 * 
 * @author Edward Banfa
 *
 */
public class BusinessObjectDataImpl implements BusinessObjectData {

	private Boolean valid;
	private Boolean routed;
	private Boolean processed;
	private String businessObjectName;
	private String businessObjectClassName;
	private String processCategoryCode;
	private Map<String, Object> dataValues = new HashMap<String, Object>();
	
	/**
	 * 
	 */
	public BusinessObjectDataImpl() {
	}

	/**
	 * @param businessObjectName
	 */
	public BusinessObjectDataImpl(String businessObjectName) {
		this.businessObjectName = businessObjectName;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#getDataValue(java.lang.String)
	 */
	@Override
	public Object getDataValue(String dataValueName) {
		return dataValues.get(dataValueName);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setDataValue(String dataValueName, Object dataValue) {
		dataValues.put(dataValueName, dataValue);
	}

	/**
	 * @return the businessObjectName
	 */
	public String getBusinessObjectName() {
		return businessObjectName;
	}

	/**
	 * @param businessObjectName the businessObjectName to set
	 */
	public void setBusinessObjectName(String businessObjectName) {
		this.businessObjectName = businessObjectName;
	}

	/**
	 * @return the dataValues
	 */
	public Map<String, Object> getDataValues() {
		return dataValues;
	}

	/**
	 * @param dataValues the dataValues to set
	 */
	public void setDataValues(Map<String, Object> dataValues) {
		this.dataValues = dataValues;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#isInboundBusinessObject()
	 */
	@Override
	public Boolean isInboundBusinessObject() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#isForwardBusinessObject()
	 */
	@Override
	public Boolean isForwardBusinessObject() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#isOutboundBusinessObject()
	 */
	@Override
	public Boolean isOutboundBusinessObject() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#getPrimaryOutboundRoute()
	 */
	@Override
	public ChannelConfiguration getPrimaryOutboundRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.channel.service.process.netty.handler.BusinessObjectData#getSecondaryRoutes()
	 */
	@Override
	public List<ChannelConfiguration> getSecondaryRoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the valid
	 */
	public Boolean isValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return the routed
	 */
	public Boolean isRouted() {
		return routed;
	}

	/**
	 * @param routed the routed to set
	 */
	public void setRouted(Boolean routed) {
		this.routed = routed;
	}

	/**
	 * @return the processed
	 */
	public Boolean isProcessed() {
		return processed;
	}

	/**
	 * @param processed the processed to set
	 */
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BusinessObjectDataImpl [businessObjectName="
				+ businessObjectName + ", businessObjectClassName= " + businessObjectClassName + ", dataValues=" + dataValues + "]";
	}

	/**
	 * @return the businessObjectClassName
	 */
	public String getBusinessObjectClassName() {
		return businessObjectClassName;
	}

	/**
	 * @param businessObjectClassName the businessObjectClassName to set
	 */
	public void setBusinessObjectClassName(String businessObjectClassName) {
		this.businessObjectClassName = businessObjectClassName;
	}

	/**
	 * @return the processCategoryCode
	 */
	public String getProcessCategoryCode() {
		return processCategoryCode;
	}

	/**
	 * @param processCategoryCode the processCategoryCode to set
	 */
	public void setProcessCategoryCode(String processCategoryCode) {
		this.processCategoryCode = processCategoryCode;
	}
}
