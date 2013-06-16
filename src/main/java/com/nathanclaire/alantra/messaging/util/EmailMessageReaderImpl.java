/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.sun.mail.pop3.POP3Store;

/**
 * @author Edward Banfa 
 *
 */
@EmailMessageReader
@Stateless
public class EmailMessageReaderImpl implements MessageReader {
	
	public final String POP3 = "pop3";
	public final String INBOX_FOLDER = "INBOX";
	public final Integer MAX_MESSAGE_BODY_SIZE = 250;
	public final String POP3_HOST_PROPERTY = "mail.pop3.host";
	public final String DEFAULT_LOCAL_MSG_SENDER = "ADVICEPRO";
	public final String DEFAULT_ATTACHMENTS_FOLDER = "/home/administrator/Projects/alantra/attachments/";
	
	public List<MessageLite> getMessages(String connectionString, String username, String password) throws ApplicationException 
	{
		List<MessageLite> liteMessages = new ArrayList<MessageLite>();
		try {
			Properties properties = new Properties();
			properties.put(POP3_HOST_PROPERTY, connectionString);
			Session emailSession = Session.getDefaultInstance(properties);

			POP3Store emailStore = (POP3Store) emailSession.getStore(POP3);
			emailStore.connect(username, password);

			Folder emailFolder = emailStore.getFolder(INBOX_FOLDER);
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				MessageLite messageLite = new MessageLite();
				
				// Process the message headers
				Message message = messages[i];
				MimeMessage m = (MimeMessage) message;
				messageLite.setMessageId(m.getMessageID());
				messageLite.setMessageFrom(message.getFrom()[0].toString());
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
			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			throw new ApplicationException(UNKNOWN_EMAIL_PROVIDER, e.getMessage());
		} catch (MessagingException e) {
			throw new ApplicationException(MESSAGING_ERROR, e.getMessage());
		} catch (IOException e) {
			throw new ApplicationException(CONNECTION_ERROR, e.getMessage());
		} catch (Exception e) {
			throw new ApplicationException(UNKNOWN_MSG_FETCH_ERROR, e.getMessage());
		}
		return liteMessages;
	}
	
	public void handleMultipart(Multipart multipart, MessageLite messageLite) throws MessagingException, IOException 
	{
		for (int i=0, n=multipart.getCount(); i<n; i++) {
			handlePart(multipart.getBodyPart(i), messageLite);
		}
	}
	public void handlePart(Part part, MessageLite messageLite) throws MessagingException, IOException 
	{
		String disposition = part.getDisposition();
		String contentType = part.getContentType();
		messageLite.setContainsAttachement(false);
		if (disposition == null) { 
			// Check if plain
			if ((contentType.length() >= 10) && (contentType.toLowerCase().substring(0, 10).equals("text/plain"))) {
				handleMesgBody(messageLite, part);
			} else if((contentType.length() >= 10) && (contentType.toLowerCase().substring(0, 10).equals("text/html"))) { 
				handleMesgBody(messageLite, part);
			}
			else { 
				// Don't think this will happen
			}
		} else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
			System.out.println("&&&&&&&&&&&&&&&&&&&&" + contentType);
			messageLite.setContainsAttachement(true);
			if(contentType.toLowerCase().contains("text/csv"))
				messageLite.setAttachementMimeType(CSV_MIME);
			saveFile(messageLite, part.getFileName(), part.getInputStream());
		} else if (disposition.equalsIgnoreCase(Part.INLINE)) {
			saveFile(messageLite, part.getFileName(), part.getInputStream());
		} else { 
			// Should never happen
			System.out.println("Other: " + disposition);
		}
	}
	
	private void handleMesgBody(MessageLite messageLite, Part part) throws IOException, MessagingException
	{
		String messageContent = part.getContent().toString();
		if(messageContent.length() > MAX_MESSAGE_BODY_SIZE)
			messageLite.setMessageBody(part.getContent().toString().substring(0, MAX_MESSAGE_BODY_SIZE));
		else
			messageLite.setMessageBody(messageContent);
	}
	
	/**
	 * @param messageLite
	 * @param filename
	 * @param input
	 * @throws IOException
	 */
	private void saveFile(MessageLite messageLite, String filename, InputStream input) throws IOException 
	{ 
		filename = DEFAULT_ATTACHMENTS_FOLDER.concat(filename);
		// Do no overwrite existing file
		File file = new File(filename);
		for (int i = 0; file.exists(); i++) {
			filename = filename + i;
			file = new File(filename);
		}
		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		BufferedInputStream bis = new BufferedInputStream(input);
		int aByte;
		while ((aByte = bis.read()) != -1) {
			bos.write(aByte);
		}
		bos.flush();
		bos.close();
		bis.close();
		messageLite.setAttachementFileName(file.getAbsolutePath());
	}
	
}
