/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.messaging.annotation.AdviceInquiryReceivedEvent;
import com.nathanclaire.alantra.notification.service.entity.NotificationTypeService;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeTagService;
import com.nathanclaire.alantra.notification.service.process.NotificationService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AdviceInquiryReceivedEventListenerImpl extends BaseMessageListener implements
		AdviceInquiryReceivedEventListener {
	@Inject NotificationService notificationService;
	private Logger logger = LoggerFactory.getLogger(AdviceInquiryReceivedEventListenerImpl.class);
		
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.event.AdviceInquiryReceivedEventListener#processAdviceInquiryReceivedEvent(com.nathanclaire.alantra.messaging.event.MessageReceivedEvent)
	 */
	@Override
	public void processAdviceInquiryReceivedEvent(@Observes @AdviceInquiryReceivedEvent MessageEvent event)
			throws ApplicationException {
		try {
			// 1. Fetch the customer and the advice and respond with the advice status
			//  ( thats assuming the customer and advice referenced are valid).
			Customer customer = getCustomer(event.getCustomerId());
			String adviceReferenceNo = messageProcessingService.extractAdviceReference(getMessageText(getMessage(event)));
			if(StringUtil.isValidString(adviceReferenceNo))
			{
				String adviceStatus = getAdviceStatusInfo(adviceReferenceNo);
				Map<String, String>  templateTagValues = addToTemplateTagValues(
						TemplateTypeTagService.ADVICE_REFERENCE, adviceReferenceNo, this.initializeTemplateTagValues(event));
				// Invalid string returned is taken to mean no advice was found
				if(StringUtil.isValidString(adviceStatus))
				{
					templateTagValues = addToTemplateTagValues(TemplateTypeTagService.STATUS_INFORMATION, adviceStatus, templateTagValues);
					notificationService.notifyCustomer(customer, NotificationTypeService.ADVICE_INQUIRY_RESPONSE, templateTagValues);
				}
				else
					notificationService.notifyCustomer(customer, 
							NotificationTypeService.ADVICE_INQUIRY_ADVICE_NOT_FOUND_RESPONSE, templateTagValues);
			}
			else
				notificationService.notifyCustomer(customer, 
						NotificationTypeService.ADVICE_INQUIRY_INVALID_ADVICE_REFERENCE_RESPONSE, initializeTemplateTagValues(event));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
