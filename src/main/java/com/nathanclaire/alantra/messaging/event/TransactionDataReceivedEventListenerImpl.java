/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.annotation.TransactionDataReceievdEvent;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class TransactionDataReceivedEventListenerImpl extends BaseMessageListener implements
		TransactionDataReceievdEventListener {
	private Logger logger = LoggerFactory.getLogger(TransactionDataReceivedEventListenerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.TransactionDataReceievdEventListener#processTransactionDataReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageReceivedEvent)
	 */
	@Override
	public void processTransactionDataReceivedEvent(@Observes @TransactionDataReceievdEvent MessageEvent event)
			throws ApplicationException {
		try {
			 /*1. Notify the user that we have received the request and will confirm the outcome later
			      Note if the request contained an a valid data input attachment, the application will process the
			      contents of the attachment as the transaction data. All other instructions in the message text will 
			      be ignored */
			if(!event.isContainsAttachment())
				notificationService.notifyUser(getUser(event.getSystemUserId()), 
						NotificationTypeService.TXN_DATA_INPUT_REQUEST_RESPONSE, initializeTemplateTagValues(event));
			else
			notificationService.notifyUser(getUser(event.getSystemUserId()), 
					NotificationTypeService.TXN_DATA_INPUT_REQUEST_WITH_ATTACHMENT_RESPONSE, initializeTemplateTagValues(event));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
