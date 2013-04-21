/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.channel.model.ServiceTransaction;
import com.nathanclaire.alantra.channel.request.ServiceTransactionRequest;

/**
 * @author Edward Banfa
 *
 */
public interface ServiceTransactionService extends BaseEntityService<ServiceTransaction, ServiceTransactionRequest>
{
	public static final Character TRANSACTION_STATUS_REJECTED = 'R';
	public static final Character TRANSACTION_STATUS_ACCEPTED = 'A';
	public static final Character TRANSACTION_STATUS_PROCESSING = 'P';
	
}
