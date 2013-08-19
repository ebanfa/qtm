/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import java.util.List;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.model.TxnAwaitingConfirmation;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingConfirmationRequest;
import com.nathanclaire.alantra.transaction.response.TxnAwaitingConfirmationResponse;

/**
 * @author Edward Banfa
 *
 */
public interface TxnAwaitingConfirmationService extends 
	BaseEntityService<TxnAwaitingConfirmation, TxnAwaitingConfirmationResponse, TxnAwaitingConfirmationRequest>
{
	public List<TxnAwaitingConfirmation> getTransactionsAwaitingConfirmation() throws ApplicationException;
}
