/**
 * 
 */
package com.nathanclaire.alantra.channel.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.channel.model.ServiceTransactionType;
import com.nathanclaire.alantra.channel.request.ServiceTransactionTypeRequest;
import com.nathanclaire.alantra.channel.response.ServiceTransactionTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface ServiceTransactionTypeService extends BaseEntityService<ServiceTransactionType, ServiceTransactionTypeResponse, ServiceTransactionTypeRequest>
{

	String CHEQUE_WITHDRAWAL = "CHQ_WITHDRAWAL";
	
}
