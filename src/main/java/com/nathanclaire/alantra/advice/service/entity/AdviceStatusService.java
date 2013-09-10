/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.request.AdviceStatusRequest;
import com.nathanclaire.alantra.advice.response.AdviceStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceStatusService extends BaseEntityService<AdviceStatus, AdviceStatusResponse, AdviceStatusRequest>
{
	public static final String ADVICE_CODE = "PROCESSED";
	public static final String PROCESSING_CYCLES = "PROCESSING_CYCLES";
	public static final String UNPROCESSED_ADVICE_CODE = "UNPROCESSED";
	public static final String ACTIVE = null;
	
}
