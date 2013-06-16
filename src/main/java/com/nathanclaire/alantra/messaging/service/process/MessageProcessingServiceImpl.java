/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.model.MessageTypeTag;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeTagService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageProcessingServiceImpl extends BaseProcessService implements MessageProcessingService {

	@Inject MessageTypeService messageTypeService;
	@Inject MessageStatusService messageStatusService;
	@Inject MessageTypeTagService messageTypeTagService;
	@Inject MessageClassificationService messageClassificationService;
	
	private Logger logger = LoggerFactory.getLogger(MessageProcessingServiceImpl.class);

	private final String PARSE_REGEX_PATTERN = "([a-zA-Z0-9]*)([\\s]*)([a-zA-Z0-9]*)([.]*)";

	@Override
	public MessageStatus getReceivedMessageStatus() throws ApplicationException {
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
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageProcessingService#getMessageType(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	public MessageType getMessageType(MessageRequest messageRequest) throws ApplicationException
	{
		logger.info("Processing get getMessageType for message: {}", messageRequest.getCode());
		// 1. All messages have a body so by default we search the message body
		// 2. If the message contains a subject the apply the pattern to the subject
		String text = messageRequest.getMessageTxt();
		if(StringUtil.isValidString(messageRequest.getMessageSubject()))
			text = messageRequest.getMessageSubject();
		// Extract the message type tag from the text
		return findMessageType(text, extractMessageTypeTagfromMessage(text));
	}

	/**
	 * @param text
	 * @param messageTypeTag
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageType findMessageType(String text, String messageTypeTag) throws ApplicationException {
		logger.info("Finding tag: {} in message text: {}", messageTypeTag, text);
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
	 * @param text
	 * @return
	 */
	private String extractMessageTypeTagfromMessage(String text) {
		logger.info("Extracting tag from message text: {}", text);
		Pattern r = Pattern.compile(PARSE_REGEX_PATTERN);
		// Now create matcher object.
		Matcher m = r.matcher(text);
		String messageTypeTag = "";
		// 3. The first group is the application, the second is just space
		// 4. The third group is the message type tag		
		// 5. The fourth group is the rest of the stuff that will be used to process the advice
		if (m.find( )) {
			messageTypeTag =  m.group(3);
			logger.info("Found message type tag: {}", messageTypeTag);
		} else {
			logger.info("Did not find any nessage tag in the message text: {}", messageTypeTag);
		}
		return messageTypeTag;
	}

	/**
	 * @param messageTypeTag
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageTypeTag resolveMessageTypeTagFromText(String messageTypeTag) throws ApplicationException 
	{
		logger.info("Resolving message tag from message text: {}", messageTypeTag);
		MessageTypeTag firstMatchingTag = null;
		List<MessageTypeTag> tags = messageTypeTagService.findAll(null);
		for(MessageTypeTag tag : tags)
		{
			firstMatchingTag = matchMessageTypeTag(messageTypeTag, tag);
			// Return the first match
			if(firstMatchingTag !=null)
			{
				logger.info("Found message type tag {} with value: {} in message text: {}", 
						firstMatchingTag.getCode(), firstMatchingTag.getTagVal(), messageTypeTag);
				return firstMatchingTag;
			}
		}
		return firstMatchingTag;
	}
	
	/**
	 * @param messageTypeTag
	 * @param firstMatchingTag
	 * @param tag
	 * @return
	 */
	private MessageTypeTag matchMessageTypeTag(String messageTypeTag, MessageTypeTag tag) 
	{
		logger.info("Matching tag: {} in message text: {}", tag.getCode(), messageTypeTag);
		// - If the message type tag is a regex the apply the regex
		if(StringUtil.flagToBoolean(tag.getIsRegexFg()))
		{
			// Create a Pattern object
			Pattern r = Pattern.compile(tag.getTagVal());
			Matcher m = r.matcher(messageTypeTag);
			if (m.find( )) return tag;
			// Else return null
			else return null;
		}
		// - Else we just do a java.lang.String.contains()
		else 
		{
			if(messageTypeTag.equalsIgnoreCase(tag.getTagVal()) | messageTypeTag.contains(tag.getTagVal())) 
				return tag;
			// Else return null
			else return null;
		}
	}

	/**
	 * @return
	 * @throws ApplicationException
	 */
	private MessageType getUnclassifiedMessageType() throws ApplicationException {
		return messageTypeService.findByCode(MessageTypeService.UNCLASSIFIED_INBOUND_MSG);
	}

}
