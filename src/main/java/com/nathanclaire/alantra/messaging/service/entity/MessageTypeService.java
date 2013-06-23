/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageType;
import com.nathanclaire.alantra.messaging.request.MessageTypeRequest;
import com.nathanclaire.alantra.messaging.response.MessageTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageTypeService extends BaseEntityService<MessageType, MessageTypeResponse, MessageTypeRequest>
{
	public static final String ADVICE_REQUEST = "ADVICE_REQUEST";
	public static final String ADVICE_INQUIRY = "ADVICE_INQUIRY";
	public static final String ADVICE_REQUEST_REPLY = "ADVICE_REQUEST_REPLY";
	public static final String ADVICE_INQUIRY_REPLY = "ADVICE_INQUIRY_REPLY";
	public static final String TXN_DATA_INPUT_REQUEST = "TXN_DATA_INPUT_REQUEST";
	public static final String UNCLASSIFIED_INBOUND_MSG = "UNCLASSIFIED_INBOUND_MSG";
}
