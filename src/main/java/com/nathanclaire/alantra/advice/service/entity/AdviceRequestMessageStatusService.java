/**
 * 
 */
package com.nathanclaire.alantra.advice.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.advice.model.AdviceRequestMessageStatus;
import com.nathanclaire.alantra.advice.request.AdviceRequestMessageStatusRequest;
import com.nathanclaire.alantra.advice.response.AdviceRequestMessageStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface AdviceRequestMessageStatusService extends BaseEntityService<AdviceRequestMessageStatus, AdviceRequestMessageStatusResponse, AdviceRequestMessageStatusRequest>
{
	public final String REJECTED_ACCOUNT_NOT_FOUND = "REJECTED_ACCOUNT_NOT_FOUND";
	public final String UNPROCESSED_ADVICE_CODE = "ACCEPTED";
	
}
