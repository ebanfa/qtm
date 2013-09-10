/**
 * 
 */
package com.nathanclaire.alantra.messaging.util;

import com.nathanclaire.alantra.base.util.DateUtil;
import com.nathanclaire.alantra.base.util.PropertyUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.request.CustomerMessageRequest;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.request.SystemUserMessageRequest;

/**
 * @author Edward Banfa
 *
 */
public class MessagingUtil {
	
	/**
	 * @param channel
	 * @param msgType
	 * @param msgClassification
	 * @param msgStatus
	 * @param inOutFg
	 * @param msgHeader
	 * @param msgBody
	 * @return
	 */
	public static MessageRequest getMessageRequest(DataChannel channel, MessageType msgType, 
			MessageClassification msgClassification, MessageStatus msgStatus, Character inOutFg, String msgHeader, String msgBody) {
		MessageRequest messageRequest = new MessageRequest();
		PropertyUtil.initializeBaseFields(messageRequest);
		messageRequest.setDataChannelId(channel.getId());
		messageRequest.setMessageSubject(msgHeader);
		messageRequest.setMessageTxt(msgBody);
		messageRequest.setCode(DateUtil.getCurrentTimeInMilliSeconds().toString());
		messageRequest.setMessageTypeId(msgType.getId());
		messageRequest.setMessageStatusId(msgStatus.getId());
		messageRequest.setMessageClassificationId(msgClassification.getId());
		messageRequest.setInOutFg(inOutFg);
		return messageRequest;
	}

	/**
	 * @param customer
	 * @param message
	 * @return
	 */
	public static CustomerMessageRequest getCustomerMessageRequest(Customer customer, Message message) {
		CustomerMessageRequest customerMessageRequest = new CustomerMessageRequest();
		PropertyUtil.initializeBaseFields(customerMessageRequest);
		customerMessageRequest.setCustomerId(customer.getId());
		customerMessageRequest.setMessageId(message.getId());
		customerMessageRequest.setCode(message.getCode());
		return customerMessageRequest;
	}

	/**
	 * @param user
	 * @param message
	 * @return
	 */
	public static SystemUserMessageRequest getUserMessageRequest(SystemUser user,
			Message message) {
		SystemUserMessageRequest userMessageRequest = new SystemUserMessageRequest();
		PropertyUtil.initializeBaseFields(userMessageRequest);
		userMessageRequest.setSystemUserId(user.getId());
		userMessageRequest.setMessageId(message.getId());
		userMessageRequest.setCode(message.getCode());
		return userMessageRequest;
	}

}
