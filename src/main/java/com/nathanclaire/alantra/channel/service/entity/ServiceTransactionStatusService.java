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

	String MATCH_PENDING = "MATCH_PENDING";
	
}
