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
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
import com.nathanclaire.alantra.messaging.service.entity.MessageCategoryService;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class ClassificationServiceImpl extends BaseProcessService implements ClassificationService 
{
	@Inject MessageTypeService messageTypeService;
	@Inject MessageStatusService messageStatusService;
	@Inject MessageClassificationService messageClassificationService;
	@Inject MessageTextProcessingService messageTextProcessingService;
	

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#isCustomerMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public boolean isCustomerMessage(MessageRequest messageRequest) throws ApplicationException {
		String text = messageTextProcessingService.getMessageText(messageRequest);
		MessageApplicationAction messageApplicationAction = messageTextProcessingService.getMessageApplicationAction(text);
		if(messageApplicationAction.getCode().equals(MessageApplicationActionService.ADVICE_INQUIRY))
			return true;
		if(messageApplicationAction.getCode().equals(MessageApplicationActionService.ADVICE_REQUEST))
			return true;
		if(messageApplicationAction.getCode().equals(MessageApplicationActionService.ADVICE_INQUIRY_REPLY))
			return true;
		if(messageApplicationAction.getCode().equals(MessageApplicationActionService.ADVICE_REQUEST_REPLY))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#isUserMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public boolean isUserMessage(MessageRequest messageRequest) throws ApplicationException {
		String text = messageTextProcessingService.getMessageText(messageRequest);
		MessageApplicationAction messageApplicationAction = messageTextProcessingService.getMessageApplicationAction(text);
		if(messageApplicationAction.getCode().equals(MessageApplicationActionService.TXN_DATA_INPUT_REQUEST))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getMessageStatus(java.lang.String)
	 */
	@Override
	public MessageStatus getMessageStatus(String messageStatusCode) throws ApplicationException {
		MessageStatus messageStatus = messageStatusService.findByCode(messageStatusCode);
		if(messageStatus == null)
			throw new ApplicationException(MESSAGE_STATUS_NOT_FOUND);
		return messageStatus;
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.ClassificationService#getMessageClassification(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public MessageClassification getMessageClassification(DataChannel dataChannel)  throws ApplicationException{
		// Data channel code is the classification code
		DataChannelCategory dataChannelCategory = getDataChannelCategory(dataChannel);
		MessageClassification messageClassification = null;
		// Test data channel code against the classification codes defined
		if(dataChannelCategory.getCode().equals(DataChannelCategoryService.SMS_DATA_CHANNEL))
			messageClassification = messageClassificationService.findByCode(MessageClassificationService.INBOUND);
		if(dataChannelCategory.getCode().equals(DataChannelCategoryService.EMAIL_DATA_CHANNEL))
			messageClassification = messageClassificationService.findByCode(MessageClassificationService.OUTBOUND);
		// If null we throw an exception
		if(messageClassification == null)
			throw new ApplicationException(MESSAGE_CLASSIFICATION_NOT_FOUND);
		return messageClassification;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.ClassificationService#getMessageType(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public MessageType getMessageType(DataChannel channel)	throws ApplicationException {
		DataChannelType channelType = getChannelType(channel);
		MessageType messageType = messageTypeService.findByCode(channelType.getCode());
		if(messageType == null)
			throw new ApplicationException(MESSAGE_TYPE_NOT_FOUND);
		return messageType;
	}

}
