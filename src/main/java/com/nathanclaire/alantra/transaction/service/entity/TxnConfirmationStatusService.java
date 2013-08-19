/**
 * 
 */
package com.nathanclaire.alantra.transaction.service.entity;


import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.transaction.model.TxnConfirmationStatus;
import com.nathanclaire.alantra.transaction.request.TxnConfirmationStatusRequest;
import com.nathanclaire.alantra.transaction.response.TxnConfirmationStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface TxnConfirmationStatusService extends BaseEntityService<TxnConfirmationStatus, TxnConfirmationStatusResponse, TxnConfirmationStatusRequest>
{

	public static final String UNCONFIRMED = "UNCONFIRMED";

}
