/**
 * 
 */
package com.nathanclaire.alantra.base.service.process;

import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;

/**
 * @author Edward Banfa 
 *
 */
public class BaseProcessService {
	
	protected static final String DATA_IMPORT_SERVICE_NOT_FOUND = 
			"BaseProcessService.DATA_IMPORT_SERVICE_NOT_FOUND";

	@Inject DataChannelService dataChannelService;
	/**
	 * @return
	 * @throws ApplicationException
	 */
	protected DataChannel getDataImportService(String code) throws ApplicationException {
		// Get the service
		DataChannel service = dataChannelService.findByCode(code);
		if(service == null)
			throw new ApplicationException(DATA_IMPORT_SERVICE_NOT_FOUND);
		return service;
	}

	/**
	 * @param dataChannel
	 * @return
	 */
	protected DataChannelCategory getDataChannelCategory(DataChannel dataChannel) {
		DataChannelType dataChannelType = dataChannel.getDataChannelType();
		return dataChannelType.getDataChannelCategory();
	}
	

}
