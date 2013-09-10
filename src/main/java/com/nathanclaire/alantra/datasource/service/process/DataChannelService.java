/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;

/**
 * @author Edward Banfa
 * 
 */
public interface DataChannelService {
	
	public DataChannel getDataChannel(String dataChannelCode) throws ApplicationException;
	
	public DataChannelType getDataChannelType(String typeCode) throws ApplicationException;
	
	public DataChannelCategory getDataChannelCategory(String categoryCode) throws ApplicationException;

}
