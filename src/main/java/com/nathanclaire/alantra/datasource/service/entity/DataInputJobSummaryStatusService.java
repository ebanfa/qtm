/**
 * 
 */
package com.nathanclaire.alantra.datasource.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.datasource.model.DataInputJobSummaryStatus;
import com.nathanclaire.alantra.datasource.request.DataInputJobSummaryStatusRequest;
import com.nathanclaire.alantra.datasource.response.DataInputJobSummaryStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface DataInputJobSummaryStatusService extends BaseEntityService<DataInputJobSummaryStatus, DataInputJobSummaryStatusResponse, DataInputJobSummaryStatusRequest>
{

	public static final String RUNNING = "RUNNING";
	
}
