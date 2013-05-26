/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.response.MessageResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageService extends BaseEntityService<Message, MessageResponse, MessageRequest>
{
	
}
