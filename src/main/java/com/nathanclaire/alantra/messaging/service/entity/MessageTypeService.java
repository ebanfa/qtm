/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageTypeRequest;
import com.nathanclaire.alantra.messaging.response.MessageTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageTypeService extends BaseEntityService<MessageType, MessageTypeResponse, MessageTypeRequest>
{

	public static final String SMTP_POP3_MSG = "MessageTypeService.SMTP_POP3_MSG";
	
}
