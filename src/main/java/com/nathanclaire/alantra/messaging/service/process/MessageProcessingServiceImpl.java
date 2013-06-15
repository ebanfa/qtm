/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageProcessingServiceImpl extends BaseProcessService implements MessageProcessingService {

	private static final String MESSAGE_RECEIVED_STATUS_NOT_FOUND = "MESSAGE_RECEIVED_STATUS_NOT_FOUND";
	
	@Inject MessageStatusService messageStatusService;
	@Inject MessageClassificationService messageClassificationService;

	@Override
	public MessageStatus getReceivedMessageStatus() throws ApplicationException {
		// TODO Auto-generated method stub
		MessageStatus messageStatus = messageStatusService.findByCode(MessageStatusService.RECEIVED);
		if(messageStatus == null)
			throw new ApplicationException(MESSAGE_RECEIVED_STATUS_NOT_FOUND);
		return messageStatus;
	}

	@Override
	public MessageClassification getMessageClassification(DataChannel dataChannel)  throws ApplicationException{
		// Data channel code is the classification code
		DataChannelCategory dataChannelCategory = getDataChannelCategory(dataChannel);
		MessageClassification messageClassification = null;
		// Test data channel code against the classification codes defined
		if(dataChannelCategory.getCode().equals(MessageClassificationService.SMS))
			messageClassification = messageClassificationService.findByCode(MessageClassificationService.SMS);
		if(dataChannelCategory.getCode().equals(MessageClassificationService.EMAIL))
			messageClassification = messageClassificationService.findByCode(MessageClassificationService.EMAIL);
		// If null we throw an exception
		if(messageClassification == null)
			throw new ApplicationException(MESSAGE_RECEIVED_STATUS_NOT_FOUND);
		return messageClassification;
	}

	@Override
	public MessageType getMessageType(MessageRequest messageRequest) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
