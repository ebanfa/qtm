/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageAttachements;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.model.MessageTypeTag;
import com.nathanclaire.alantra.messaging.request.MessageAttachementsRequest;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageAttachementsService;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeTagService;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SecurityService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageProcessingServiceImpl extends BaseProcessService implements MessageProcessingService {

	@Inject MessageService messageService;
	@Inject SecurityService securityService;
	@Inject MessageTypeService messageTypeService;
	@Inject MessageStatusService messageStatusService;
	@Inject MessageTypeTagService messageTypeTagService;
	@Inject MessageAttachementsService attachmentsService;
	@Inject CustomerProcessingService customerProcessingService;
	@Inject MessageClassificationService messageClassificationService;
	private Logger logger = LoggerFactory.getLogger(MessageProcessingServiceImpl.class);
	private final String PARSE_REGEX_PATTERN = "([a-zA-Z0-9]*)([\\s]*)([a-zA-Z0-9]*)([.]*)";

	@Override
	public MessageClassification getMessageClassification(DataChannel dataChannel)  throws ApplicationException{
		// Data channel code is the classification code
		DataChannelCategory dataChannelCategory = getDataChannelCategory(dataChannel);
		MessageClassification messageClassification = null;
		// Test data channel code against the classification codes defined
		if(dataChannelCategory.getCode().equals(DataChannelCategoryService.SMS_DATA_CHANNEL))
			messageClassification = messageClassificationService.findByCode(MessageClassificationService.SMS);
		if(dataChannelCategory.getCode().equals(DataChannelCategoryService.EMAIL_DATA_CHANNEL))
			messageClassification = messageClassificationService.findByCode(MessageClassificationService.EMAIL);
		// If null we throw an exception
		if(messageClassification == null)
			throw new ApplicationException(MESSAGE_CLASSIFICATION_NOT_FOUND);
		return messageClassification;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#isCustomerMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public boolean isCustomerMessage(MessageRequest messageRequest) throws ApplicationException {
		MessageType messageType = getMessageType(messageRequest);
		if(messageType.getCode().equals(MessageTypeService.ADVICE_INQUIRY))
			return true;
		if(messageType.getCode().equals(MessageTypeService.ADVICE_REQUEST))
			return true;
		if(messageType.getCode().equals(MessageTypeService.ADVICE_INQUIRY_REPLY))
			return true;
		if(messageType.getCode().equals(MessageTypeService.ADVICE_REQUEST_REPLY))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#isUserMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public boolean isUserMessage(MessageRequest messageRequest) throws ApplicationException {
		MessageType messageType = getMessageType(messageRequest);
		if(messageType.getCode().equals(MessageTypeService.TXN_DATA_INPUT_REQUEST))
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#getCustomerFromMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public Customer findCustomerFromMessage(MessageRequest messageRequest) throws ApplicationException {
		// Customers can share source addresses
		return customerProcessingService.findSingleCustomerBySourceAddress(messageRequest.getMessageFrom());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#getSystemUserFromMessage(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public SystemUser findSystemUserFromMessage(MessageRequest messageRequest) throws ApplicationException {
		// Users cannot share source addresses
		return securityService.findUserBySourceAddress(messageRequest.getMessageFrom());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#getMessageStatus(java.lang.String)
	 */
	@Override
	public BaseEntity getMessageStatus(String messageStatusCode) throws ApplicationException {
		MessageStatus messageStatus = messageStatusService.findByCode(messageStatusCode);
		if(messageStatus == null)
			throw new ApplicationException(MESSAGE_STATUS_NOT_FOUND);
		return messageStatus;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#findMessage(java.lang.String)
	 */
	@Override
	public Message findMessage(String messageCode) throws ApplicationException {
		return messageService.findByCode(messageCode);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#findMessage(java.lang.String)
	 */
	@Override
	public Message getMessage(Integer messageId) throws ApplicationException {
		Message message = messageService.findById(messageId);
		if(message == null) throw new ApplicationException(MESSAGE_NOT_FOUND);
		return message;
		
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#create(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public Message create(MessageRequest messageRequest) throws ApplicationException 
	{
		Message message = messageService.create(messageRequest);
		if(message == null) throw new ApplicationException(COULD_NOT_CREATED_MESSAGE);
		return message;
	}

	/**
	 * @param text
	 * @return
	 */
	@Override
	public String extractMessageTypeTagfromMessage(String messageText) {
		logger.debug("Extracting tag from message text: {}", messageText);
		// Group 3 is the message type tag
		return StringUtil.extractRegexGroupFromText(messageText, PARSE_REGEX_PATTERN, 3);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#extractAdviceReference(java.lang.String)
	 */
	@Override
	public String extractAdviceReference(String messageText) throws ApplicationException {
		logger.debug("Extracting advice reference from message text: {}", messageText);
		return StringUtil.extractRegexGroupFromText(messageText, PARSE_REGEX_PATTERN, 4);
	}


	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#getMessageType(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	public MessageType getMessageType(MessageRequest messageRequest) throws ApplicationException
	{
		logger.debug("Processing get getMessageType for message: {}", messageRequest.getCode());
		// 1. All messages have a body so by default we search the message body
		// 2. If the message contains a subject the apply the pattern to the subject
		String text = messageRequest.getMessageTxt();
		if(StringUtil.isValidString(messageRequest.getMessageSubject()))
			text = messageRequest.getMessageSubject();
		// Extract the message type tag from the text
		return findMessageType(text, extractMessageTypeTagfromMessage(text));
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#createAttachment(com.nathanclaire.alantra.messaging.model.Message, com.nathanclaire.alantra.messaging.request.MessageAttachementsRequest)
	 */
	@Override
	public MessageAttachements createAttachment(Message message, MessageAttachementsRequest attachmentRequest) throws ApplicationException 
	{
		if(message== null) throw new ApplicationException(INVALID_MESSAGE_PROVIDED);
		if(attachmentRequest == null) throw new ApplicationException(INVALID_ATTACHMENT_REQUEST_PROVIDED);
		PropertyUtils.initializeBaseFields(attachmentRequest);
		attachmentRequest.setMessageId(message.getId());
		attachmentRequest.setCode(message.getCode());
		MessageAttachements messageAttachments = attachmentsService.create(attachmentRequest);
		if(messageAttachments == null) throw new ApplicationException(COULD_NOT_CREATED_MESSAGE_ATTACHMENT);
		return messageAttachments;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#getAttachment(java.lang.Integer)
	 */
	@Override
	public MessageAttachements getAttachment(Integer attachmentId)	throws ApplicationException {
		MessageAttachements messageAttachments = attachmentsService.findById(attachmentId);
		if(messageAttachments == null) throw new ApplicationException(COULD_NOT_FIND_MESSAGE_ATTACHMENT);
		return messageAttachments;
	}
	
	/**
	 * @return
	 * @throws ApplicationException
	 */
	private MessageType getUnclassifiedMessageType() throws ApplicationException {
		return messageTypeService.findByCode(MessageTypeService.UNCLASSIFIED_INBOUND_MSG);
	}

	/**
	 * @param text
	 * @param messageTypeTag
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageType findMessageType(String text, String messageTypeTag) throws ApplicationException {
		logger.debug("Finding tag: {} in message text: {}", messageTypeTag, text);
		MessageTypeTag firstMatchingTag = null;
		if(StringUtil.isValidString(messageTypeTag))
			// - Load all the message type tags and for each
			firstMatchingTag = resolveMessageTypeTagFromText(messageTypeTag);
		if(firstMatchingTag != null) return firstMatchingTag.getMessageType();
		// - If we still don't have a match we use the general message type
		else
			return getUnclassifiedMessageType();
	}
	
	/**
	 * @param messageTypeTag
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageTypeTag resolveMessageTypeTagFromText(String messageTypeTag) throws ApplicationException 
	{
		logger.debug("Resolving message tag from message text: {}", messageTypeTag);
		List<MessageTypeTag> tags = messageTypeTagService.findAll(null);
		for(MessageTypeTag tag : tags)
		{
			String result = StringUtil.match(messageTypeTag, 
					tag.getTagVal(), StringUtil.flagToBoolean(tag.getIsRegexFg()));
			// Return the first match
			if(StringUtil.isValidString(result))
				return tag;
		}
		return null;
	}
}
