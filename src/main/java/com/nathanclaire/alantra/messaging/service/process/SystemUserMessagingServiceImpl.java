/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.util.Contact;
import com.nathanclaire.alantra.messaging.util.MessageLite;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa 
 *
 */
public class SystemUserMessagingServiceImpl extends BaseMessagingService 
		implements SystemUserMessagingService {
	
	@Inject MessagingService meassagingService;
	private Logger logger = LoggerFactory.getLogger(SystemUserMessagingServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.SystemUserMessagingService#sendMessageToUser(com.nathanclaire.alantra.security.model.SystemUser, java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMessageToUser(SystemUser user, List<DataChannel> channels,
			String subjectText, String messageText) throws ApplicationException {
		logger.debug("Sending message to user {} on {} channels", user, channels);
			try {
				if(user == null)
					throw new ApplicationException(INVALID_USER_TO_SEND_MESSAGE_TO);
				// Contact info
				Contact userContact = new Contact(user.getEmail(), 	null, user.getMobile(), null);
				// Each channel will have a separate message lite
				Map<DataChannel, MessageLite> messagingMapping = 
						prepareMessages(channels, subjectText, messageText, userContact);
				// Loop through the map and send each message using via its associated channel
				for(DataChannel channel : messagingMapping.keySet())
					meassagingService.sendMessage(channel, messagingMapping.get(channel));
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new ApplicationException(ERROR_SENDING_MESSAGE_TO_USER);
			}
	}

}
