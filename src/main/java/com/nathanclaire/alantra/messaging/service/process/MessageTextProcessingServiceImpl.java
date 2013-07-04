/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.messaging.model.MessageApplication;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageApplicationActionTag;
import com.nathanclaire.alantra.messaging.model.MessageApplicationTag;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionTagService;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationTagService;

/**
 * @author Edward Banfa 
 *
 */
public class MessageTextProcessingServiceImpl extends BaseProcessService	implements MessageTextProcessingService {
	
	@Inject MessageApplicationTagService applTagService;
	@Inject MessageApplicationActionTagService tagService;
	@Inject MessageApplicationService messageApplicationService;
	@Inject MessageApplicationActionService messageApplicationActionService;
	private Logger logger = LoggerFactory.getLogger(MessageTextProcessingServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageTextProcessingService#getMessageText(com.nathanclaire.alantra.messaging.request.MessageRequest)
	 */
	@Override
	public String getMessageText(MessageRequest messageRequest) throws ApplicationException {
		// 1. All messages have a body so by default we search the message body
		// 2. If the message contains a subject the apply the pattern to the subject
		String text = messageRequest.getMessageTxt();
		if(StringUtil.isValidString(messageRequest.getMessageSubject()))
			text = messageRequest.getMessageSubject();
		if(!StringUtil.isValidString(text))
			throw new ApplicationException(GET_MESSSAGE_TEXT_FAILED);
		return text;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageTextProcessingService#getMessageApplication(java.lang.String)
	 */
	@Override
	public MessageApplication getMessageApplication(String messageText) throws ApplicationException {
		logger.debug("Searching for message application in message text: {}", messageText);
		// Extract the message type tag from the text
		MessageApplication messageApplication = findMessageApplication(extractMessageApplicationString(messageText));
		if(messageApplication == null)
			throw new ApplicationException(READ_MSG_APPL_FAILED);
		return messageApplication;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageTextProcessingService#getMessageApplicationAction(java.lang.String)
	 */
	@Override
	public MessageApplicationAction getMessageApplicationAction(String messageText) throws ApplicationException {
		logger.debug("Searching for message application action in message text: {}", messageText);
		// Extract the message application action from the text
		MessageApplicationAction messageApplicationAction = 
				findMessageApplicationAction(extractMessageApplicationActionString(messageText));
		if(messageApplicationAction == null)
			throw new ApplicationException(READ_MSG_APPL_ACTION_FAILED);
		return messageApplicationAction;	
	}

	/**
	 * @param text
	 * @return
	 */
	@Override
	public String extractMessageApplicationString(String messageText) {
		logger.debug("Extracting message application tag from message text: {}", messageText);
		// Group 3 is the message type tag
		String applTag = StringUtil.extractRegexGroupFromText(messageText, PARSE_REGEX_PATTERN, 1);
		logger.debug("Extracted message application tag {} from message text: {}", applTag, messageText);
		return applTag;
	}
	/**
	 * @param text
	 * @return
	 */
	@Override
	public String extractMessageApplicationActionString(String messageText) {
		logger.debug("Extracting message application action from message text: {}", messageText);
		// Group 3 is the message type tag
		String actionTag = StringUtil.extractRegexGroupFromText(messageText, PARSE_REGEX_PATTERN, 3);
		logger.debug("Extracted message application action tag {} from message text: {}", actionTag, messageText);
		return actionTag;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#extractAdviceReference(java.lang.String)
	 */
	@Override
	public String extractAdviceReferenceString(String messageText) throws ApplicationException {
		logger.debug("Extracting advice reference from message text: {}", messageText);
		return StringUtil.extractRegexGroupFromText(messageText, PARSE_REGEX_PATTERN, 5);
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageTextProcessingService#getMessageApplicationActionOptions(java.lang.String)
	 */
	@Override
	public String[] getMessageApplicationActionOptions(String messageText)	throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param messageApplicationTagText
	 * @return
	 * @throws ApplicationException
	 */
	private MessageApplication findMessageApplication(String messageApplicationTagText) throws ApplicationException {
		MessageApplicationTag firstMatchingTag = null;
		if(StringUtil.isValidString(messageApplicationTagText))
			// - Load all the message type tags and for each
			firstMatchingTag = getMessageApplicationTag(messageApplicationTagText);
		if(firstMatchingTag != null) return firstMatchingTag.getMessageApplication();
		// - If we still don't have a match we use the general message application
		else
			return getUnknownMessageApplication();
	}
	/**
	 * @param text
	 * @param messageTypeTag
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageApplicationAction findMessageApplicationAction(String messageTypeTag) throws ApplicationException {
		MessageApplicationActionTag firstMatchingTag = null;
		if(StringUtil.isValidString(messageTypeTag))
			// - Load all the message type tags and for each
			firstMatchingTag = getMessageApplicationActionTag(messageTypeTag);
		if(firstMatchingTag != null) return firstMatchingTag.getMessageApplicationAction();
		// - If we still don't have a match we use the general message application action
		else
			return getUnknownMessageApplicationAction();
	}
	
	/**
	 * @return
	 * @throws ApplicationException
	 */
	private MessageApplication getUnknownMessageApplication() throws ApplicationException {
		MessageApplication application = messageApplicationService.findByCode(MessageApplicationService.UNKNOWN_MSG_APPLICATION);
		if(application == null)
			throw new ApplicationException(MESSAGE_APPL_NOT_FOUND);
		return application;
	}
	
	/**
	 * @return
	 * @throws ApplicationException
	 */
	private MessageApplicationAction getUnknownMessageApplicationAction() throws ApplicationException {
		MessageApplicationAction applicationAction = 
				messageApplicationActionService.findByCode(MessageApplicationActionService.UNKNOWN_MSG_APPLICATION_ACTION);
		if(applicationAction == null)
			throw new ApplicationException(MESSAGE_APPL_ACTION_NOT_FOUND);
		return applicationAction;
	}


	/**
	 * @param text
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageApplicationTag getMessageApplicationTag(String text) throws ApplicationException 
	{
		logger.debug("Resolving message application tag from text: {}", text);
		List<MessageApplicationTag> tags = applTagService.findAll(null);
		for(MessageApplicationTag tag : tags)
		{
			String result = StringUtil.match(text, 
					tag.getTagVal(), StringUtil.flagToBoolean(tag.getIsRegexFg()));
			// Return the first match
			if(StringUtil.isValidString(result))
			{
				logger.debug("Found matching application tag {} in application text {}", tag.getTagVal(), text);
				return tag;
			}
		}
		logger.debug("Application not found in application text {}", text);
		return null;
	}
	
	/**
	 * @param text
	 * @return
	 * @throws ApplicationException 
	 */
	private MessageApplicationActionTag getMessageApplicationActionTag(String text) throws ApplicationException 
	{
		logger.debug("Resolving message application action tag from text: {}", text);
		List<MessageApplicationActionTag> tags = tagService.findAll(null);
		for(MessageApplicationActionTag tag : tags)
		{
			String result = StringUtil.match(text, 
					tag.getTagVal(), StringUtil.flagToBoolean(tag.getIsRegexFg()));
			// Return the first match
			if(StringUtil.isValidString(result))
				return tag;
		}
		return null;
	}
}
