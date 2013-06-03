/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.channel.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.channel.request.ServiceTransactionStatusRequest;
import com.nathanclaire.alantra.channel.response.ServiceTransactionStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface ServiceTransactionStatusService extends BaseEntityService<ServiceTransactionStatus, ServiceTransactionStatusResponse, ServiceTransactionStatusRequest>
{
	public final String MATCHED = "MATCHED"; 
	public final String NOT_MATCHED = "NOT_MATCHED"; 
	public final String MATCH_PENDING = "MATCH_PENDING"; 
}
