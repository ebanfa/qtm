/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import java.util.List;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.transaction.model.TxnAwaitingNotification;
import com.nathanclaire.alantra.transaction.request.TxnAwaitingNotificationRequest;
import com.nathanclaire.alantra.transaction.response.TxnAwaitingNotificationResponse;

/**
 * @author Edward Banfa
 *
 */
public interface TxnAwaitingNotificationService extends 
	BaseEntityService<TxnAwaitingNotification, TxnAwaitingNotificationResponse, TxnAwaitingNotificationRequest>
{
	public List<TxnAwaitingNotification> getTransactionsAwaitingNotification() throws ApplicationException;
}
