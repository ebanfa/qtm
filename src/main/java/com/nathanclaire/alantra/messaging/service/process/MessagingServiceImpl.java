/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;


import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.entity.CustomerMessageService;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.MessageCreatedEvent;
import com.nathanclaire.alantra.messaging.event.MessagingEvent;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.util.MessagingUtil;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.entity.SystemUserMessageService;

/**
 * @author Edward Banfa
 *
 */
public class MessagingServiceImpl extends BaseProcessService implements	MessagingService {
	
	@Inject MessageService messageService;
	@Inject MessageStatusService messageStatusService;
	@Inject SystemUserMessageService userMessageService;
	@Inject CustomerMessageService customerMessageService;
	@Inject MessageClassificationService messageClassificationService;	
	@Inject @MessageCreatedEvent Event<MessagingEvent> messageCreatedEvent;
	private Logger logger = LoggerFactory.getLogger(getClass());

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageService#createOutboundCustMsg(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.model.MessageType, java.lang.String, java.lang.String)
	 */
	@Override
	public Message createOutboundCustMsg(Customer customer,	DataChannel channel, 
			MessageType msgType, String msgHeader, String msgBody) throws ApplicationException {
		try {
			MessageStatus status = this.getMessageStatus(MessageStatusService.MSG_SENT);
			MessageClassification classification = this.getMessageClassification(MessageClassificationService.DEF_CUST_MSG);
			Message message = createCustMsg(customer, 
					MessagingUtil.getMessageRequest(channel, msgType, classification, status, MSG_OUT_FG, msgHeader, msgBody));
			messageCreatedEvent.fire(
					new MessagingEvent(message.getId(), customer.getId(), null, MSG_OUT_FG, true, false));
			return message;
		} catch (Exception e) {
			logger.error("Error creating outbound customer message. {}", e.getMessage());
			throw new ApplicationException("");
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageService#createInboundCustMsg(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.model.MessageType, java.lang.String, java.lang.String)
	 */
	@Override
	public Message createInboundCustMsg(Customer customer, DataChannel channel,
			MessageType msgType, String msgHeader, String msgBody) throws ApplicationException {
		try {
			MessageStatus status = this.getMessageStatus(MessageStatusService.MSG_RECIEVED);
			MessageClassification classification = this.getMessageClassification(MessageClassificationService.DEF_CUST_MSG);
			Message message = createCustMsg(customer, 
					MessagingUtil.getMessageRequest(channel, msgType, classification, status, MSG_IN_FG, msgHeader, msgBody));
			messageCreatedEvent.fire(
					new MessagingEvent(message.getId(), customer.getId(), null, MSG_IN_FG, true, false));
			return message;
		} catch (Exception e) {
			logger.error("Error creating inbound customer message. {}", e.getMessage());
			throw new ApplicationException("");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageService#createOutboundUserMsg(com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.model.MessageType, java.lang.String, java.lang.String)
	 */
	@Override
	public Message createOutboundUserMsg(SystemUser user, DataChannel channel,
			MessageType msgType, String msgHeader, String msgBody) throws ApplicationException {
		try {
			MessageStatus status = this.getMessageStatus(MessageStatusService.MSG_SENT);
			MessageClassification classification = this.getMessageClassification(MessageClassificationService.DEF_USER_MSG);
			Message message = createUserMsg(user, 
					MessagingUtil.getMessageRequest(channel, msgType, classification, status, MSG_OUT_FG, msgHeader, msgBody));
			messageCreatedEvent.fire(
					new MessagingEvent(message.getId(), null, user.getId(), MSG_OUT_FG, true, false));
			return message;
		} catch (Exception e) {
			logger.error("Error creating outbound user message. {}", e.getMessage());
			throw new ApplicationException("");
		}
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessageService#createInboundUserMsg(com.nathanclaire.alantra.security.model.SystemUser, com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.model.MessageType, java.lang.String, java.lang.String)
	 */
	@Override
	public Message createInboundUserMsg(SystemUser user, DataChannel channel,
			MessageType msgType, String msgHeader, String msgBody) throws ApplicationException {
		try {
			MessageStatus status = this.getMessageStatus(MessageStatusService.MSG_RECIEVED);
			MessageClassification classification = this.getMessageClassification(MessageClassificationService.DEF_USER_MSG);
			Message message = createUserMsg(user, 
					MessagingUtil.getMessageRequest(channel, msgType, classification, status, MSG_IN_FG, msgHeader, msgBody));
			messageCreatedEvent.fire(
					new MessagingEvent(message.getId(), null, user.getId(), MSG_IN_FG, true, false));
			return message;
		} catch (Exception e) {
			logger.error("Error creating inbound user message. {}", e.getMessage());
			throw new ApplicationException("");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingService#getMessageStatus(java.lang.String)
	 */
	@Override  
	public MessageStatus getMessageStatus(String statusCode) throws ApplicationException {
		MessageStatus status = messageStatusService.findByCode(statusCode);
		if(status == null)
			throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "MessageStatus");
		return status;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingService#getMessageClassification(java.lang.String)
	 */
	@Override
	public  MessageClassification getMessageClassification(String classificationCode) throws ApplicationException {
		MessageClassification classification = messageClassificationService.findByCode(classificationCode);
		if(classification == null)
			throw new ApplicationException(BaseEntityService.ENTITY_INSTANCE_NOT_FOUND, "MessageClassification");
		return classification;
	}

	private Message createCustMsg(Customer customer, MessageRequest messageRequest) throws ApplicationException {
		Message message = messageService.create(messageRequest);
		customerMessageService.create(MessagingUtil.getCustomerMessageRequest(customer, message));
		return message;
	}

	private Message createUserMsg(SystemUser user, MessageRequest messageRequest) throws ApplicationException {
		Message message = messageService.create(messageRequest);
		userMessageService.create(MessagingUtil.getUserMessageRequest(user, message));
		return message;
	} 
}
