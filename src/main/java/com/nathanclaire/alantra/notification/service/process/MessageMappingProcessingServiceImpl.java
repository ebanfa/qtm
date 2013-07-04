/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import java.util.Set;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.model.MessageApplicationNotificationMap;
import com.nathanclaire.alantra.notification.model.NotificationType;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageMappingProcessingServiceImpl extends BaseProcessService
		implements MessageMappingProcessingService {
	
	private Logger logger = LoggerFactory.getLogger(MessageMappingProcessingServiceImpl.class);

	@Override
	public MessageApplicationAction getMessageType(NotificationType notificationType) throws ApplicationException {
		if(notificationType == null) 
			throw new ApplicationException(INVALID_NOTIFICATION_TYPE_SPECIFIED);
		logger.debug("Loading message type mapping for notification type {}.", notificationType.getCode());
		Set<MessageApplicationNotificationMap> messageNotificationMapping = notificationType.getMessageApplicationNotificationMaps();
		if(messageNotificationMapping.isEmpty())
			throw new ApplicationException(CONFIG_ERROR_NO_MAPPING_FOR_NOTIFICATION_TYPE);
		MessageApplicationNotificationMap mapping = null;
		for(MessageApplicationNotificationMap entry : messageNotificationMapping)
		{
			mapping = entry;
			break;
		}
		MessageApplicationAction messageApplicationAction = mapping.getMessageApplicationAction();
		
		if(messageApplicationAction == null)
			throw new ApplicationException(CONFIG_ERROR_INVALID_NOTIFICATION_TYPE_MAPPING);
		return messageApplicationAction;
	}

}
