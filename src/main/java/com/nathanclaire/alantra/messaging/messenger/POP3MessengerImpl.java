/**
 * 
 */
package com.nathanclaire.alantra.messaging.messenger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.FileUtil;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.POP3Messenger;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.sun.mail.pop3.POP3Store;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
@POP3Messenger
public class POP3MessengerImpl implements Messenger {

	public static final String HANDLE_PART_ERROR = "MessageReader.HANDLE_PART_ERROR";
	public static final String HANDLE_MULTIPART_ERROR = "MessageReader.HANDLE_MULTIPART_ERROR";
	public static final String HANDLE_MSG_BODY_IO_ERROR = "MessageReader.HANDLE_MSG_BODY_IO_ERROR";
	public static final String HANDLE_MSG_BODY_MSG_ERROR = "MessageReader.HANDLE_MSG_BODY_MSG_ERROR";
	public static final String INLINE_ATTACHMENT_IO_ERROR = "MessageReader.INLINE_ATTACHMENT_IO_ERROR";
	public static final String INLINE_ATTACHMENT_MSG_ERROR = "MessageReader.INLINE_ATTACHMENT_MSG_ERROR";
	
	public final String POP3 = "pop3";
	public final String INBOX_FOLDER = "INBOX";
	public final Integer MAX_MESSAGE_BODY_SIZE = 250;
	public final String POP3_HOST_PROPERTY = "mail.pop3.host";
	public final String DEFAULT_LOCAL_MSG_SENDER = "ADVICEPRO";
	
	public final String CSV_MIME = "csv";
	public final String MESSAGING_ERROR = "MessageReader.MESSAGING_ERROR";
	public final String CONNECTION_ERROR = "MessageReader.CONNECTION_ERROR";
	public final String UNKNOWN_EMAIL_PROVIDER = "MessageReader.UNKNOWN_MESSAGING_PROVIDER";
	public final String UNKNOWN_MSG_FETCH_ERROR = "MessageReader.UNKNOWN_MSG_FETCH_ERROR";
	
	private Logger logger = LoggerFactory.getLogger(POP3MessengerImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#getMessages(com.nathanclaire.alantra.datasource.model.DataChannel)
	 */
	@Override
	public List<MessageLite> getMessages(DataChannel channel) throws ApplicationException {
		return getMessages(channel.getIpAddr(), channel.getUsername(), channel.getPassword());
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.messenger.Messenger#sendMessage(com.nathanclaire.alantra.datasource.model.DataChannel, com.nathanclaire.alantra.messaging.util.MessageLite)
	 */
	@Override
	public void sendMessage(DataChannel channel, MessageLite message)
			throws ApplicationException {
		// TODO Auto-generated method stub

	}
	
	private List<MessageLite> getMessages(String connectionString, String username, String password) throws ApplicationException 
	{
		List<MessageLite> messageLites = null;
		try {
			logger.info("Loading email messages from server {}", connectionString);
			POP3Store emailStore = getStore(connectionString);
			Folder emailFolder = connect(username, password, emailStore);

			Message[] messages = emailFolder.getMessages();
			logger.info("Loaded {} email messages", messages.length);
			messageLites = toMessageLiteList(messages);
			emailFolder.close(false);
			emailStore.close();
		} catch (MessagingException e) {
			logger.error(e.getMessage());
			throw new ApplicationException(MESSAGING_ERROR, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(UNKNOWN_MSG_FETCH_ERROR, e.getMessage());
		}
		return messageLites;
	}

	/**
	 * @param liteMessages
	 * @param messages
	 * @throws MessagingException
	 * @throws IOException
	 * @throws ApplicationException 
	 */
	private List<MessageLite> toMessageLiteList(Message[] messages)  throws ApplicationException {
		
		List<MessageLite> liteMessages = new ArrayList<MessageLite>();
		try {
			for (int i = 0; i < messages.length; i++) {
				MessageLite messageLite = new MessageLite();
				// Process the message headers
				Message message = messages[i];
				MimeMessage m = (MimeMessage) message;
				messageLite.setMessageId(m.getMessageID());
				if(message.getFrom().length < 1)
					throw new ApplicationException(INVALID_SENDER_ADDRESS);
				// Clean the email address if necessary
				String senderAddress = cleanEmailAddress(message.getFrom()[0].toString());
				messageLite.setMessageFrom(senderAddress);
				messageLite.setMessageTo(DEFAULT_LOCAL_MSG_SENDER);
				messageLite.setSubjectLine(message.getSubject());
				// Process the message content
				Object content = message.getContent();
			    if (content instanceof Multipart) 
			    {
			    	handleMultipart((Multipart)content, messageLite);
			    } 
			    else 
			    {
			    	handlePart(message, messageLite);
			    }
				
				liteMessages.add(messageLite);
			}
			return liteMessages;
		} catch (MessagingException e) {
			throw new ApplicationException(MESSAGING_ERROR, e.getMessage());
		} catch (IOException e) {
			throw new ApplicationException(CONNECTION_ERROR, e.getMessage());
		}
	}
	
	private String cleanEmailAddress(String emailAddress) {
		//String EMAIL_REGEX = "[a-zA-Z0-9_]*[@][a-zA-Z0-9_]*\\.[a-zA-Z0-9]+{2,}";
		logger.debug("Email address before extraction {}", emailAddress);
		if(emailAddress.contains(">") | emailAddress.contains("<"))
		{
			emailAddress = StringUtil.extractEmailFromText(emailAddress);
			logger.debug("Email address after extraction {}", emailAddress);
		}
		return emailAddress;
	}
	/**
	 * @param connectionString
	 * @return
	 * @throws NoSuchProviderException
	 */
	private POP3Store getStore(String connectionString) throws ApplicationException {
		try {
			Properties properties = new Properties();
			properties.put(POP3_HOST_PROPERTY, connectionString);
			Session emailSession = Session.getDefaultInstance(properties);
			POP3Store emailStore = (POP3Store) emailSession.getStore(POP3);
			return emailStore;
		} catch (NoSuchProviderException e) {
			throw new ApplicationException(UNKNOWN_EMAIL_PROVIDER, e.getMessage());
		}
	}

	/**
	 * @param username
	 * @param password
	 * @param emailStore
	 * @return
	 * @throws MessagingException
	 */
	private Folder connect(String username, String password, POP3Store emailStore) throws ApplicationException {
		try {
			emailStore.connect(username, password);
			Folder emailFolder = emailStore.getFolder(INBOX_FOLDER);
			emailFolder.open(Folder.READ_ONLY);
			return emailFolder;
		} catch (Exception e) {
			throw new ApplicationException(MESSAGING_ERROR, e.getMessage());
		}
	}

	public void handleMultipart(Multipart multipart, MessageLite messageLite) throws ApplicationException 
	{
		try {
			for (int i=0, n=multipart.getCount(); i<n; i++) {
				handlePart(multipart.getBodyPart(i), messageLite);
			}
		} catch (MessagingException e) {
			throw new ApplicationException(HANDLE_MULTIPART_ERROR);
		}
	}
	
	public void handlePart(Part part, MessageLite messageLite) throws ApplicationException 
	{
		String disposition = null;
		String contentType = null;
		try {
			disposition = part.getDisposition();
			contentType = part.getContentType();
		} catch (MessagingException e) {
			throw new ApplicationException(HANDLE_PART_ERROR);
		}
		messageLite.setContainsAttachement(false);
		if (disposition == null) { 
			handleNullDisposition(part, messageLite, contentType);
		} else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) 
		{
			handleAttachment(part, messageLite, contentType);
		}
		else if (disposition.equalsIgnoreCase(Part.INLINE)) 
		{
			handleInlineAttachment(part, messageLite);
		} 
		else
		{ 
			// Should never happen
			System.out.println("Other: " + disposition);
		}
	}

	/**
	 * @param part
	 * @param messageLite
	 * @param contentType
	 * @throws IOException
	 * @throws MessagingException
	 */
	private void handleNullDisposition(Part part, MessageLite messageLite, String contentType) throws ApplicationException{
		// Check if plain
		if ((contentType.length() >= 10) && (contentType.toLowerCase().substring(0, 10).equals("text/plain"))) {
			handleMesgBody(messageLite, part);
		} else if((contentType.length() >= 10) && (contentType.toLowerCase().substring(0, 10).equals("text/html"))) { 
			handleMesgBody(messageLite, part);
		}
		else { 	// Don't think this will happen
		}
	}

	/**
	 * @param part
	 * @param messageLite
	 * @throws ApplicationException
	 * @throws MessagingException
	 * @throws IOException
	 */
	private void handleInlineAttachment(Part part, MessageLite messageLite) throws ApplicationException{
		try {
			String attachementFileName = FileUtil.saveFile(part.getFileName(), part.getInputStream());
			messageLite.setAttachementFileName(attachementFileName);
		} catch (MessagingException e) {
			throw new ApplicationException(INLINE_ATTACHMENT_MSG_ERROR);
		} catch (IOException e) {
			throw new ApplicationException(INLINE_ATTACHMENT_IO_ERROR);
		}
	}

	/**
	 * @param part
	 * @param messageLite
	 * @param contentType
	 * @throws ApplicationException
	 * @throws MessagingException
	 * @throws IOException
	 */
	private void handleAttachment(Part part, MessageLite messageLite, String contentType) throws ApplicationException {
		messageLite.setContainsAttachement(true);
		if(contentType.toLowerCase().contains("text/csv"))
			messageLite.setAttachementMimeType(CSV_MIME);
		handleInlineAttachment(part, messageLite);
	}
	
	private void handleMesgBody(MessageLite messageLite, Part part) throws ApplicationException 
	{
		try {
			String messageContent = part.getContent().toString();
			if(messageContent.length() > MAX_MESSAGE_BODY_SIZE)
				messageLite.setMessageBody(part.getContent().toString().substring(0, MAX_MESSAGE_BODY_SIZE));
			else
				messageLite.setMessageBody(messageContent);
		} catch (IOException e) {
			throw new ApplicationException(HANDLE_MSG_BODY_IO_ERROR);
		} catch (MessagingException e) {
			throw new ApplicationException(HANDLE_MSG_BODY_MSG_ERROR);
		}
	}

}
