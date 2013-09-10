/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.model.BaseEntity;
import com.nathanclaire.alantra.base.request.BaseRequest;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerContactService;
import com.nathanclaire.alantra.datasource.etl.service.BaseEntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.service.EntityDataInputService;
import com.nathanclaire.alantra.datasource.etl.util.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.annotation.MessageDataInputService;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SystemUserContactService;

/**
 * @author Edward Banfa
 *
 */
@Stateless
@MessageDataInputService
public class MessageDataInputServiceImpl extends BaseEntityDataInputService implements EntityDataInputService {
	
	@Inject MessagingService messagingService;
	@Inject SystemUserContactService userContactService;
	@Inject CustomerContactService customerContactService;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String MDIS_DATA_INPUT_ERROR = "MessageDataInputServiceImpl.MDIS_DATA_INPUT_ERROR";

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.datasource.etl.service.BaseDataInputService#doDataInput(com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.datasource.etl.TableDataLite)
	 */
	@Override
	public BaseEntity doDataInput(DataChannel channel, BaseRequest primaryEntity, 
			BaseRequest secondaryEntity, TableData tableData) throws ApplicationException {
		logger.debug("Processing data input request");
		try {
			PropertyUtil.initializeBaseFields(primaryEntity);
			MessageRequest messageRequest = (MessageRequest) primaryEntity;
			if(messagingService.getMessage(messageRequest.getCode()) != null)
				throw new ApplicationException(DIS_ENTITY_INSTANCE_EXIST);
			Customer customer = customerContactService.findCustomerByContact(messageRequest.getMessageFrom());
			if(customer != null){
				logger.debug("Processing customer inbound message data input for customer: {}", customer.getName());
				return messagingService.createInboundCustMsg(customer, channel, 
							messageRequest.getMessageSubject(), messageRequest.getMessageTxt());
			}
			else {
				SystemUser user = userContactService.findUserByContact(messageRequest.getMessageFrom());
				if(user != null){
					logger.debug("Processing user inbound message data input for user: {}", user.getName());
					return messagingService.createInboundUserMsg(user, channel, 
							messageRequest.getMessageSubject(), messageRequest.getMessageTxt());
				}
				// This message is neither a customer or a user message. Process as unregistered user message
				else{
					logger.debug("Processing unregistered user inbound message data input from: {}", messageRequest.getMessageFrom());
					return messagingService.createInboundUnregisterUserMsg(channel, messageRequest.getMessageFrom(),
							messageRequest.getMessageTo(), messageRequest.getMessageSubject(), messageRequest.getMessageTxt());
				}
			}
		} catch (Exception e) {
			logger.error("Error processing message data input: {}", e.getMessage());
			throw new ApplicationException(MDIS_DATA_INPUT_ERROR, e.getMessage());
		}
	}
}
