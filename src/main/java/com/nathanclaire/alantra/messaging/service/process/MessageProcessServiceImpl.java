/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageProcessServiceImpl implements MessageProcessService {
	
	@Inject MessageService messageService;

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessService#findMessage(java.lang.String)
	 */
	@Override
	public Message findMessage(String messageCode) throws ApplicationException {
		return messageService.findByCode(messageCode);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessService#getMessage(java.lang.Integer)
	 */
	@Override
	public Message getMessage(Integer messageId) throws ApplicationException {
		Message message = messageService.findById(messageId);
		if(message == null) throw new ApplicationException(MESSAGE_NOT_FOUND);
		return message;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessService#createMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public Message createMessage(MessageRequest messageRequest) throws ApplicationException {
		Message message = messageService.create(messageRequest);
		if(message == null) throw new ApplicationException(COULD_NOT_CREATED_MESSAGE);
		return message;
	}

}
