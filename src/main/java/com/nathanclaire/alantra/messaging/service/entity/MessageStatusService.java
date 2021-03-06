/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;


import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.request.MessageStatusRequest;
import com.nathanclaire.alantra.messaging.response.MessageStatusResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageStatusService extends BaseEntityService<MessageStatus, MessageStatusResponse, MessageStatusRequest>
{
	public static final String MSG_SENT = "MSG_SENT";
	public static final String MSG_RECIEVED = "MSG_RECIEVED";
}
