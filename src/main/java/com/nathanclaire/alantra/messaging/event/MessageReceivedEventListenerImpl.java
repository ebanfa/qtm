/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.FileUtil;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.CustomerMessage;
import com.nathanclaire.alantra.customer.request.CustomerMessageRequest;
import com.nathanclaire.alantra.customer.service.entity.CustomerMessageService;
import com.nathanclaire.alantra.messaging.annotation.AdviceInquiryReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.AdviceRequestReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.CustomerMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.ExistingMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.TransactionDataReceievdEvent;
import com.nathanclaire.alantra.messaging.annotation.UnclassifiedMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnregisteredCustomerMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UnregisteredUserMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.annotation.UserMessageReceivedEvent;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.notification.service.process.NotificationService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageReceivedEventListenerImpl extends BaseMessageListener implements	 MessageReceivedEventListener {
	
	@Inject NotificationService notificationService;
	@Inject @AdviceRequestReceivedEvent Event<MessageEvent> adviceRequestReceivedEvent;
	@Inject @AdviceInquiryReceivedEvent Event<MessageEvent> adviceInquiryReceivedEvent;
	@Inject @TransactionDataReceievdEvent Event<MessageEvent> transactionDataReceievdEventEvent;
	@Inject CustomerMessageService messageService;
	private Logger logger = LoggerFactory.getLogger(MessageReceivedEventListenerImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageReceivedEventListener#processMessageAlreadyExistEvent(com.nathanclaire.alantra.messaging.event.MessageCreationEvent)
	 */
	@Override
	public void processExistingMessageReceivedEven(@Observes @ExistingMessageReceivedEvent MessageEvent event) 
			throws ApplicationException 
	{
		try {
			logger.info("Processing existing message received");
			// If the message had an attachment
			if(StringUtil.isValidString(event.getAttachmentFileName())) FileUtil.deleteFile(event.getAttachmentFileName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageReceivedEventListener#processCreatedMsgWithAttachmentEvent(com.nathanclaire.alantra.messaging.event.MessageCreationEvent)
	 */
	@Override
	public void processCustomerMessageReceivedEvent(@Observes @CustomerMessageReceivedEvent MessageEvent event) 
			throws ApplicationException {
		try {
			logger.info("Processing customer message received event with application action {}", event.getMessageApplicationActionCode());
			// First create the customer message to link customer to message
			createCustomerMessage(event.getCustomerId(), event.getMessageId(), event.getMessageCode());
			if(!StringUtil.isValidString(event.getMessageApplicationActionCode()))
			{
				logger.debug("Invalid message application specified in customer received message event");
				return;
			}
			MessageEvent eventToFire = new MessageEvent(event.getJobId(), 
					event.getMessageId(), event.getMessageTypeCode(), event.getCustomerId(), 
					event.getSystemUserId(), event.getAttachmentId(), event.getAttachmentJobId(), event.isContainsAttachment());
			// Set the message text
			eventToFire.setMessageText(event.getMessageText());
			eventToFire.setSourceAddress(event.getSourceAddress());
			eventToFire.setDataChannelId(event.getDataChannelId());
			// Fire advice inquiry received event
			if(event.getMessageApplicationActionCode().equals(MessageApplicationActionService.ADVICE_INQUIRY))
				adviceInquiryReceivedEvent.fire(eventToFire);
			// Fire advice request received event
			if(event.getMessageApplicationActionCode().equals(MessageApplicationActionService.ADVICE_REQUEST))
				adviceRequestReceivedEvent.fire(eventToFire);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @param customerId
	 * @param messageId
	 * @throws ApplicationException 
	 */
	private CustomerMessage createCustomerMessage(Integer customerId, Integer messageId, String messageCode)
			throws ApplicationException {
		logger.debug("Creating customer message for customer with id {} and using message with code {}", customerId, messageCode);
		CustomerMessageRequest messageRequest = new CustomerMessageRequest();
		PropertyUtils.initializeBaseFields(messageRequest);
		messageRequest.setCode(messageCode);
		messageRequest.setCustomerId(customerId);
		messageRequest.setMessageId(messageId);
		CustomerMessage message = messageService.create(messageRequest);
		if(message == null)
			throw new ApplicationException(COULD_NOT_CREATE_CUSTOMER_MESSAGE);
		return message;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageReceivedEventListener#processUserMessageReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageEvent)
	 */
	@Override
	public void processUserMessageReceivedEvent(@Observes @UserMessageReceivedEvent MessageEvent event)
			throws ApplicationException {
		try {
			logger.info("Processing user message received");
			// Fire transaction data input request received event
			if(event.getMessageTypeCode().equals(MessageApplicationActionService.TXN_DATA_INPUT_REQUEST))
				transactionDataReceievdEventEvent.fire(new MessageEvent(event.getJobId(), 
						event.getMessageId(), event.getMessageTypeCode(), event.getCustomerId(), 
						event.getSystemUserId(), event.getAttachmentId(), event.getAttachmentJobId(), event.isContainsAttachment()));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageReceivedEventListener#processCreatedMsgWithoutAttachmentEvent(com.nathanclaire.alantra.messaging.event.MessageCreationEvent)
	 */
	@Override
	public void processUnregisteredCustomerMessageReceivedEvent(@Observes @UnregisteredCustomerMessageReceivedEvent MessageEvent event) 
			throws ApplicationException {
		try {
			logger.info("Processing unregistered customer message received");
			notificationService.notifyAdmin(NotificationTypeService.UNREGISTERED_CUSTOMER_MESSAGE_RECEIVED, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageReceivedEventListener#processUnregisteredUserMessageReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageEvent)
	 */
	@Override
	public void processUnregisteredUserMessageReceivedEvent(@Observes @UnregisteredUserMessageReceivedEvent MessageEvent event)
			throws ApplicationException {
		try {
			logger.info("Processing unregistered user message received");
			notificationService.notifyAdmin(NotificationTypeService.UNREGISTERED_USER_MESSAGE_RECEIVED, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.MessageReceivedEventListener#processUnclassifiedMessageReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageEvent)
	 */
	@Override
	public void processUnclassifiedMessageReceivedEvent(@Observes @UnclassifiedMessageReceivedEvent MessageEvent event)
			throws ApplicationException {
		try {
			logger.info("Processing unclassified message received");
			notificationService.notifyAdmin(NotificationTypeService.UNCLASSIFIED_MESSAGE_RECEIVED, null);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
	}
}
