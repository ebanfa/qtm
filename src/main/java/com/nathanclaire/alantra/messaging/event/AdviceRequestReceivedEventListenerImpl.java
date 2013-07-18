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
import com.nathanclaire.alantra.customer.model.Customer;
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
			logger.debug("Processing advice request received event");
			// Process as advice text or else ...
			if(!event.isContainsAttachment()) 
				processAdviceRequestText(event);
			// process attachments
			else processAdviceRequestAttachment(event);	
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * TODO: The response notification should contain the advice reference no.
	 * @param event
	 * @throws ApplicationException
	 */
	private void processAdviceRequestText(MessageEvent event) throws ApplicationException {
		try {
			logger.debug("Processing advice request text {}", event.getMessageText());
			// Process the advice text
			adviceProcessingService.processAdviceText(event.getCustomerId(), 
					event.getSourceAddress(), event.getMessageText(), event.getDataChannelId());
			// Notify customer
			notificationService.notifyCustomer(getCustomer(event.getCustomerId()), 
					NotificationTypeService.ADVICE_REQUEST_TEXT_RESPONSE, initializeTemplateTagValues(event));
			// Fire advice created event
		} catch (ApplicationException e) {
			logger.error(e.getMessage());
			event.setStatusInformation(e.getMessage());
			Customer customer = getCustomer(event.getCustomerId());
			event.setCustomerName(customer.getName());
			notificationService.notifyCustomer(customer, 
					NotificationTypeService.ADVICE_REQUEST_TEXT_ERROR_RESPONSE, initializeTemplateTagValues(event));
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			notificationService.notifyCustomer(getCustomer(event.getCustomerId()), 
					NotificationTypeService.ADVICE_REQUEST_TEXT_ERROR_RESPONSE, initializeTemplateTagValues(event));
		}
	}

	/**
	 * @param event
	 * @throws ApplicationException
	 */
	private void processAdviceRequestAttachment(MessageEvent event)
			throws ApplicationException {
		/*1. Notify the customer that we have received the request and will confirm the outcome later
	      Note if the request contained an a valid data input attachment, the application will process the
	      contents of the attachment as the advice requests. All other instructions in the message text will 
	      be ignored*/
		try {
			logger.debug("Processing advice request input job with attachment id {}", event.getAttachmentId());
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

}
