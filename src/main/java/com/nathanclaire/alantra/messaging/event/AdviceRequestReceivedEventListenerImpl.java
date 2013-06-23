/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.messaging.annotation.AdviceRequestReceivedEvent;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeTagService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceRequestReceivedEventListenerImpl extends BaseMessageListener implements
		AdviceRequestReceivedEventListener {
	private Logger logger = LoggerFactory.getLogger(AdviceRequestReceivedEventListenerImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.AdviceRequestReceivedEventListener#processAdviceRequestReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageReceivedEvent)
	 */
	@Override
	public void processAdviceRequestReceivedEvent(@Observes @AdviceRequestReceivedEvent MessageEvent event)
			throws ApplicationException {
		try {
			 /*1. Notify the customer that we have received the request and will confirm the outcome later
			      Note if the request contained an a valid data input attachment, the application will process the
			      contents of the attachment as the advice requests. All other instructions in the message text will 
			      be ignored*/
			if(!event.isContainsAttachment())
			{
				adviceProcessingService.processAdviceText(event.getCustomerId(), 
						event.getMessageText(), event.getMessageText(), event.getDataChannelId());
			}
			else{
				try {
					DataInputJob inputJob = processMessageAttachment(event.getAttachmentId(), event.getMessageTypeCode());
					// Include the job id into the tag values
					Map<String, String> tagValues = addToTemplateTagValues(
							TemplateTypeTagService.MESSAGE_JOB_CODE, inputJob.getCode(), initializeTemplateTagValues(event));
					// Notify the customer
					notificationService.notifyCustomer(getCustomer(event.getCustomerId()), 
							NotificationTypeService.ADVICE_REQUEST_WITH_ATTACHMENT_RESPONSE, tagValues);
				} catch (Exception e) 
				{
					// Notify the customer
					notificationService.notifyCustomer(getCustomer(event.getCustomerId()), 
							NotificationTypeService.ADVICE_REQUEST_WITH_ATTACHMENT_ERROR_RESPONSE, initializeTemplateTagValues(event));
				}	
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
