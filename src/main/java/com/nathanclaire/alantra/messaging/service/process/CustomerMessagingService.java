/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.MessageType;

/**
 * @author Edward Banfa 
 *
 */
public interface CustomerMessagingService {

	public static final String APP_PHONE_NO = "BaseMessagingService.APP_PHONE_NO";
	public static final String APP_EMAIL_ADDR = "BaseMessagingService.APP_EMAIL_ADDR";
	public static final String ERROR_SENDING_MESSAGE_TO_CUST = "CustomerMessagingService.ERROR_SENDING_MESSAGE_TO_CUST";
	public static final String INVALID_CUST_TO_SEND_MESSAGE_TO = "CustomerMessagingService.INVALID_CUST_TO_SEND_MESSAGE_TO";
	public static final String CONFIG_ERROR_NO_CHANNEL_TY_FOUND = "BaseMessagingService.CONFIG_ERROR_NO_CHANNEL_TY_FOUND";
	public static final String CONFIG_ERROR_NO_CHANNEL_CAT_FOUND = "BaseMessagingService.CONFIG_ERROR_NO_CHANNEL_CAT_FOUND";
	
	
	public void sendMessageToCustomer(Customer customer, List<DataChannel> channels, 
			String subjectText, String messageText) throws ApplicationException;
}
