/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageClassification;
import com.nathanclaire.alantra.messaging.model.MessageStatus;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.security.model.SystemUser;

/**
 * @author Edward Banfa
 * 
 */
public interface MessagingService {

	public static final Character MSG_IN_FG = 'I';
	public static final Character MSG_OUT_FG = 'O';

	public Message createOutboundCustMsg(Customer customer,
			DataChannel channel, MessageType msgType, String msgHeader,
			String msgBody) throws ApplicationException;

	public Message createInboundCustMsg(Customer customer, DataChannel channel,
			MessageType msgType, String msgHeader, String msgBody)
			throws ApplicationException;

	public Message createOutboundUserMsg(SystemUser user, DataChannel channel,
			MessageType msgType, String msgHeader, String msgBody)
			throws ApplicationException;

	public Message createInboundUserMsg(SystemUser user, DataChannel channel,
			MessageType msgType, String msgHeader, String msgBody)
			throws ApplicationException;

	public MessageStatus getMessageStatus(String statusCode) throws ApplicationException;

	public MessageClassification getMessageClassification(String classificationCode) throws ApplicationException;
}
