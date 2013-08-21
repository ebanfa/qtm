/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.messenger.SMTPMessenger;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.process.ClassificationService;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.sun.mail.smtp.SMTPTransport;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@SMTPMessenger
public class SMTPMessengerImpl extends BaseProcessService implements MessengerService {

	public static String AUTH_METH_NORMAL = "AUTH_METH_NORMAL";
	public static String AUTH_METH_STARTTLS = "AUTH_METH_STARTTLS";

	public static String CONN_METH_SSL = "CONN_METH_SSL";
	public static String CONN_METH_NORMAL = "CONN_METH_NORMAL";
	public static String CONN_METH_STARTTLS = "CONN_METH_STARTTLS";
	
	public static final String SMTP_TRANSPORT_PROTOCOL = "smtp";
	public static final String SMTPS_TRANSPORT_PROTOCOL = "smtps";
	private static final String SMTPTLS_TRANSPORT_PROTOCOL = "smtp";
	public static final String ADVICE_PRO_MAILER = "Advice Pro Mailer";
	
	@Inject MessageService messageService;
	@Inject MessageTypeService messageTypeService;
	@Inject ClassificationService classificationService;
	@Inject MessageApplicationService messageApplicationService;
	private Logger logger = LoggerFactory.getLogger(SMTPPOP3MessengerImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#getMessages(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public List<MessageLite> getMessages(DataChannel channel)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#sendMessage(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message)
			throws ApplicationException {
		Session session = getSession(channel);
		Message msg = new MimeMessage(session);
		logger.debug("Sending SMTP message to {} from {} on channel {}\n : {}", message.getMessageTo(), message.getMessageFrom(), channel,
				message.getMessageBody());
	    try {
			msg.setFrom(new InternetAddress(message.getMessageFrom()));;
			msg.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(message.getMessageTo(), false));
			msg.setSubject(message.getSubjectLine());
			msg.setText(message.getMessageBody());
			msg.setHeader("X-Mailer", ADVICE_PRO_MAILER);
			msg.setSentDate(new Date());
			SMTPTransport t = getTransport(channel, session);
			t.connect(channel.getIpAddr(), channel.getUsername(), channel.getPassword());
			t.sendMessage(msg, msg.getAllRecipients());
			logger.debug("SMTP server response: {}", t.getLastServerResponse());
			t.close();
		} catch (AddressException e) {
			throw new ApplicationException(ADDRESS_ERROR, e.getMessage());
		} catch (NoSuchProviderException e) {
			throw new ApplicationException(NO_SUCH_PROVIDER_ERROR, e.getMessage());
		} catch (SendFailedException e) {
			throw new ApplicationException(SEND_FAILED_ERROR, e.getMessage());
		} catch (MessagingException e) {
			throw new ApplicationException(MESSAGING_ERROR, e.getMessage());
		}
	    this.createMessage(message, channel);
	}

	private com.nathanclaire.alantra.messaging.model.Message createMessage(MessageLite messageLite, DataChannel channel) throws ApplicationException
	{
		MessageRequest messageRequest = new MessageRequest();
		PropertyUtils.initializeBaseFields(messageRequest);
		messageRequest.setCode(this.getCurrentTimeInMilliSeconds().toString());
		messageRequest.setMessageFrom(messageLite.getMessageFrom());
		messageRequest.setMessageTo(messageLite.getMessageTo());
		messageRequest.setMessageSubject(messageLite.getSubjectLine());
		messageRequest.setMessageTxt(messageLite.getMessageBody());
		messageRequest.setMessageTypeId(messageTypeService.findByCode(MessageTypeService.SMTP_POP3_MSG).getId());
		messageRequest.setDataChannelId(channel.getId());
		messageRequest.setMessageStatusId(classificationService.getMessageStatus(MessageStatusService.MESSAGE_SENT).getId());
		messageRequest.setMessageApplicationId(messageApplicationService.findByCode(MessageApplicationService.OUT_BOUND_MSG_APPLICATION).getId());
		messageRequest.setMessageClassificationId(classificationService.getMessageClassification(channel).getId());
		return messageService.create(messageRequest);
	}
	
	/**
	 * @param session
	 * @return
	 * @throws NoSuchProviderException
	 */
	private SMTPTransport getTransport(DataChannel channel, Session session)
			throws NoSuchProviderException 
	{
		if(channel.getConnSecurityCd().equals(CONN_METH_SSL)) {
			return (SMTPTransport)session.getTransport(SMTPS_TRANSPORT_PROTOCOL);
		}
		else if(channel.getConnSecurityCd().equals(CONN_METH_STARTTLS)) {
			return (SMTPTransport)session.getTransport(SMTPTLS_TRANSPORT_PROTOCOL);
		}
		else {
			return (SMTPTransport)session.getTransport(SMTP_TRANSPORT_PROTOCOL);
		}
	}

	/**
	 * @return
	 */
	private Session getSession(DataChannel channel) 
	{
		if(channel.getConnSecurityCd().equals(CONN_METH_SSL))
			return this.getSSLSession(channel);
		else if(channel.getConnSecurityCd().equals(CONN_METH_STARTTLS))
			return this.getSTARTTLSSession(channel);
		else
			return this.getNormalSession(channel);
	}
	
	/**
	 * @param channel
	 * @return
	 */
	private Session getSSLSession(DataChannel channel) 
	{
		logger.debug("Loading SSL session for channel {}", channel);
		Properties props = System.getProperties();
        props.put("mail.debug", "true");
        props.put("mail.smtp.auth",true);
    	props.put("mail.smtp.port", channel.getPortNo()); 
        props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.socketFactory.port", channel.getPortNo());
        props.put("mail.smtp.host", channel.getIpAddr());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    	props.setProperty("mail.smtp.ssl.trust", channel.getIpAddr());
        Session session = Session.getInstance(props, null);
		return session;
	}

	/**
	 * @param channel
	 * @return
	 */
	private Session getNormalSession(DataChannel channel) 
	{
		logger.debug("Loading Normal session for channel {}", channel);
		Properties props = System.getProperties();
        props.put("mail.smtp.host",channel.getIpAddr());
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable", false);
    	props.put("mail.smtp.port", channel.getPortNo()); 
        //props.put("mail.debug", "true");
        Session session = Session.getInstance(props, null);
		return session;
	}

	/**
	 * @param channel
	 * @return
	 */
	private Session getSTARTTLSSession(DataChannel channel) {
		logger.debug("Loading STARTTLS session for channel {}", channel);
		Properties props = System.getProperties();
        //props.put("mail.debug", "true");
        props.put("mail.smtp.host", channel.getIpAddr());
        props.put("mail.smtp.auth",true);
    	props.put("mail.smtp.port", channel.getPortNo()); 
        props.put("mail.smtp.starttls.enable", true);
    	//props.put("mail.smtp.starttls.required", true);
    	props.setProperty("mail.smtp.ssl.trust", channel.getIpAddr());
        Session session = Session.getInstance(props, null);
		return session;
	}

}
