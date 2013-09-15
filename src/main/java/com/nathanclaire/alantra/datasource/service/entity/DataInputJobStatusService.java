/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataInputJobStatus;
import com.nathanclaire.alantra.datasource.request.DataInputJobStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataInputJobStatusService extends BaseEntityService<DataInputJobStatus, DataInputJobStatusResponse, DataInputJobStatusRequest>
{

	public static final String NOT_RUNNING = "NOT_RUNNING";
	public static final String RUNNING = "RUNNING";
	public static final String PROCESSING_CYCLES = "PROCESSING_CYCLES";
	public static final String COMPLETED = "COMPLETED";
	
}
