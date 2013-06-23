/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.transaction.model.ServiceTransactionStatus;
import com.nathanclaire.alantra.transaction.request.ServiceTransactionStatusRequest;
import com.nathanclaire.alantra.transaction.response.ServiceTransactionStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface ServiceTransactionStatusService extends BaseEntityService<ServiceTransactionStatus, ServiceTransactionStatusResponse, ServiceTransactionStatusRequest>
{
	public static final String MATCHED = "MATCHED";
	public static final String NOT_MATCHED = "NOT_MATCHED";
	public static final String MATCH_PENDING = "MATCH_PENDING";
}
