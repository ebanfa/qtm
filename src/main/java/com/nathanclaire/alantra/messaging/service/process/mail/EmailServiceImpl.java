/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.util.EmailLite;
import com.nathanclaire.alantra.messaging.util.EmailReader;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class EmailServiceImpl extends BaseProcessService implements EmailService {

	@Inject
	DataChannelService dataChannelService;
	
	@Inject 
	MessageService messageService;
	@Inject 
	MessageTypeService messageTypeService;

	@Inject 
	MessageStatusService messageStatusService;
	
	@Inject 
	MessageClassificationService messageClassificationService;
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.EmailService#SendEmail(com.nathanclaire.alantra.messaging.util.EmailLite)
	 */
	@Override
	public void SendEmail(EmailLite message) throws ApplicationException {
		// 1. Get the default email gateway
		DataChannel gateway = this.getDefaultEmailGateway();
		// 2. Build email from message lite
		this.constructAndSendMessage(message, gateway);
		// 4. Create a message object to represent the sent message);x
		this.createMessageLog(message, this.getOutboundMsgClassificationId(), this.getMsgStatusId(), this.getDefaultEmailGateway().getId(),
				this.getMessageTypeId());		
	}

	private Integer getMessageTypeId() throws ApplicationException {
		return messageTypeService.findByCode("EMAIL").getId();
	}

	private Integer getMsgStatusId() throws ApplicationException  {
		return messageStatusService.findByCode("RECEIVED").getId();
	}

	private Integer getOutboundMsgClassificationId() throws ApplicationException  {
		return messageClassificationService.findByCode("OUTBOUND").getId();
	}
	
	private Integer getInboundMsgClassificationId() throws ApplicationException  {
		return messageClassificationService.findByCode("OUTBOUND").getId();
	}

	/**
	 * @param message
	 * @param messageTypeId 
	 * @param gatewayId 
	 * @param msgStatusId 
	 * @param msgClassificationId 
	 * @throws ApplicationException 
	 */
	private void createMessageLog(EmailLite message, Integer msgClassificationId, 
			Integer msgStatusId, Integer gatewayId, int messageTypeId) throws ApplicationException {
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setCode(message.getMessageId());
		messageRequest.setDescription(message.getSubjectLine());
		messageRequest.setMessageTxt(message.getMessageBody());
		messageRequest.setMessageFrom(message.getMessageFrom());
		messageRequest.setMessageTo(message.getMessageTo());
		messageRequest.setMessageSubject(message.getSubjectLine());
		messageRequest.setCreatedDt(new Date());
		messageRequest.setEffectiveDt(new Date());
		messageRequest.setCreatedByUsr("SYSTEM");
		messageRequest.setName(message.getSubjectLine());
		messageRequest.setMessageClassificationId(this.getOutboundMsgClassificationId());
		messageRequest.setDataChannelId(this.getDefaultEmailGateway().getId());
		messageRequest.setMessageStatusId(this.getMsgStatusId());
		messageRequest.setMessageTypeId(this.getMessageTypeId());
		// Create the message
		if(messageService.findByCode(message.getMessageId()) == null)
			messageService.create(messageRequest);
	}

	/**
	 * @param messageLite
	 * @param gateway
	 * @return
	 */
	private EmailLite constructAndSendMessage(EmailLite messageLite, DataChannel gateway) {
		// Get system properties
	    Properties properties = System.getProperties();
	    // Setup mail server
	    properties.setProperty("mail.smtp.host", gateway.getIpAddr());
	    // Get the default Session object.
	    Session session = Session.getDefaultInstance(properties);
	    try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(messageLite.getMessageFrom()));
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(messageLite.getMessageTo()));
	         // Set Subject: header field
	         message.setSubject(messageLite.getSubjectLine());
	         message.setText(messageLite.getMessageBody());
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
		return messageLite;
	}

	private DataChannel getDefaultEmailGateway() throws ApplicationException {
		return dataChannelService.findByCode("DEFAULT_EMAIL");
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.EmailService#getMessages()
	 */
	@Override
	public List<EmailLite> getMessages() throws ApplicationException {
		List<EmailLite> liteMessages = getMessagesImpl();
		for(EmailLite message : liteMessages)
		{
			this.createMessageLog(message, this.getOutboundMsgClassificationId(), this.getMsgStatusId(), 
					this.getDefaultEmailGateway().getId(),	this.getMessageTypeId());		
		}
		return liteMessages;
	}

	/**
	 * @return
	 * @throws ApplicationException
	 */
	private List<EmailLite> getMessagesImpl() throws ApplicationException {
		DataChannel gateway = this.getDefaultEmailGateway();
		return EmailReader.getMessages(gateway.getIpAddr(), gateway.getUsername(), gateway.getPassword());
	}

}
