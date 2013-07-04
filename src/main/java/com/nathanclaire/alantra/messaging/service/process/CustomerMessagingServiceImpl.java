/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.util.Contact;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class CustomerMessagingServiceImpl extends BaseMessagingService implements CustomerMessagingService {

	@Inject MessagingService meassagingService;
	private Logger logger = LoggerFactory.getLogger(CustomerMessagingServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.CustomerMessagingService#sendCustomerMessage(com.nathanclaire.alantra.customer.model.Customer, com.nathanclaire.alantra.messaging.model.MessageType, java.util.List, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendMessageToCustomer(Customer customer, List<DataChannel> channels, 
			String subjectText, String messageText) throws ApplicationException 
	{
		try {
			if(customer == null)
				throw new ApplicationException(INVALID_CUST_TO_SEND_MESSAGE_TO);
			// Contact info
			Contact customerContact = new Contact(customer.getPrimaryEmail(), 
					customer.getSecondaryEmail(), customer.getPrimaryMobile(), customer.getSecondaryMobile());
			// Each channel will have a separate message lite
			Map<DataChannel, MessageLite> messagingMapping = 
					prepareMessages(channels, subjectText, messageText, customerContact);
			// Loop through the map and send each message using via its associated channel
			for(DataChannel channel : messagingMapping.keySet())
				meassagingService.sendMessage(channel, messagingMapping.get(channel));
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ApplicationException(ERROR_SENDING_MESSAGE_TO_CUST);
		}
	}
}
