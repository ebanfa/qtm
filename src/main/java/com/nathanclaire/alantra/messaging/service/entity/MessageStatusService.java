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
	public static final String CUSTOMER_NOT_REGISTERED = "CUSTOMER_NOT_REGISTERED";
	public static final String CUSTOMER_MESSAGE_RECEIVED = "CUSTOMER_MESSAGE_RECEIVED";
	public static final String SYSTEM_USER_NOT_REGISTERED = "SYSTEM_USER_NOT_REGISTERED";
	public static final String SYSTEM_USER_MESSAGE_RECEIVED = "SYSTEM_USER_NOT_REGISTERED";
	public static final String UNCLASSIFIED_MESSAGE_RECEIVED = "UNCLASSIFIED_MESSAGE_RECEIVED";
	public static final String MESSAGE_SENT = "MESSAGE_SENT";
	public static final String MESSAGE_NOT_SENT = "MESSAGE_NOT_SENT";
}
