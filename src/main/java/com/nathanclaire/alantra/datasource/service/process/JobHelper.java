/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataStructure;
import com.nathanclaire.alantra.datasource.model.DataType;

/**
 * @author Edward Banfa 
 *
 */
public interface JobHelper {

	public static final String MINUTE = "MINUTE";
	public static final String COULD_NOT_FIND_DATA_CHANNEL = "JobHelper.COULD_NOT_FIND_DATA_CHANNEL";
	public static final String CONFIG_ERROR_JOB_TY_NOT_FOUND = "JobHelper.CONFIG_ERROR_JOB_TY_NOT_FOUND";
	public static final String CONFIG_ERROR_DATA_LOADER_NOT_FOUND = "JobHelper.CONFIG_ERROR_DATA_LOADER_NOT_FOUND";
	public static final String COULD_NOT_FIND_DATA_CHANNEL_TYPE = "JobHelper.COULD_NOT_FIND_DATA_CHANNEL_TYPE";
	public static final String COULD_NOT_FIND_DATA_CHANNEL_STATUS = "JobHelper.COULD_NOT_FIND_DATA_CHANNEL_STATUS";
	public static final String COULD_NOT_CREATE_DATA_CHANNEL = "JobHelper.COULD_NOT_CREATE_DATA_CHANNEL";
	
	public DataInputJob createNonCyclicLocalFileDataInputJob(String fileName, String fileMimeType, 
			DataStructure dataStructure, DataType dataType, String channelType) throws ApplicationException;
}
