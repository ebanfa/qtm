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
public class EmailReader {
	
	public static final String POP3 = "pop3";
	public static final String CSV_MIME = "csv";
	public static final String INBOX_FOLDER = "INBOX";
	public static final Integer MAX_MESSAGE_BODY_SIZE = 250;
	public static final String POP3_HOST_PROPERTY = "mail.pop3.host";
	public static final String DEFAULT_LOCAL_MSG_SENDER = "ADVICEPRO";
	public static final String DEFAULT_ATTACHMENTS_FOLDER = "/home/administrator/Projects/alantra/attachements/";
	
	public static List<EmailLite> getMessages(String mailServer, 
			String username, String password) throws ApplicationException 
	{
		List<EmailLite> liteMessages = new ArrayList<EmailLite>();
		try {
			Properties properties = new Properties();
			properties.put(POP3_HOST_PROPERTY, mailServer);
			Session emailSession = Session.getDefaultInstance(properties);

			POP3Store emailStore = (POP3Store) emailSession.getStore(POP3);
			emailStore.connect(username, password);

			Folder emailFolder = emailStore.getFolder(INBOX_FOLDER);
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				EmailLite messageLite = new EmailLite();
				
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
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return liteMessages;
	}
	
	public static void handleMultipart(Multipart multipart, EmailLite messageLite) throws MessagingException, IOException 
	{
		for (int i=0, n=multipart.getCount(); i<n; i++) {
			handlePart(multipart.getBodyPart(i), messageLite);
		}
	}
	public static void handlePart(Part part, EmailLite messageLite) throws MessagingException, IOException 
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
	
	private static void handleMesgBody(EmailLite messageLite, Part part) throws IOException, MessagingException
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
	private static void saveFile(EmailLite messageLite, String filename, InputStream input) throws IOException 
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
