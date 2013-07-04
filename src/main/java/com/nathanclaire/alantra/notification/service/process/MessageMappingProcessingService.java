/**
 * 
 */
package com.nathanclaire.alantra.notification.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.notification.model.NotificationType;

/**
 * @author Edward Banfa 
 *
 */
public interface MessageMappingProcessingService {

	public static final String INVALID_NOTIFICATION_TYPE_SPECIFIED = 
			"MessageMappingProcessingService.INVALID_NOTIFICATION_TYPE_SPECIFIED";
	public static final String CONFIG_ERROR_NO_MAPPING_FOR_NOTIFICATION_TYPE = 
			"MessageMappingProcessingService.CONFIG_ERROR_NO_MAPPING_FOR_NOTIFICATION_TYPE";
	public static final String CONFIG_ERROR_INVALID_NOTIFICATION_TYPE_MAPPING = 
			"MessageMappingProcessingService.CONFIG_ERROR_INVALID_NOTIFICATION_TYPE_MAPPING";
	
	public MessageApplicationAction getMessageType(NotificationType notificationType) throws ApplicationException;

}
