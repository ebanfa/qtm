/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.transaction.model.TxnNotificationStatus;
import com.nathanclaire.alantra.transaction.request.TxnNotificationStatusRequest;
import com.nathanclaire.alantra.transaction.response.TxnNotificationStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface TxnNotificationStatusService extends BaseEntityService<TxnNotificationStatus, TxnNotificationStatusResponse, TxnNotificationStatusRequest>
{

	public static final String NOT_SENT = "NOT_SENT";

}
