/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageApplicationAction;
import com.nathanclaire.alantra.messaging.request.MessageApplicationActionRequest;
import com.nathanclaire.alantra.messaging.response.MessageApplicationActionResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageApplicationActionService extends BaseEntityService<MessageApplicationAction, MessageApplicationActionResponse, MessageApplicationActionRequest>
{

	public static final String ADVICE_REQUEST = "ADVICE_REQUEST";
	public static final String ADVICE_INQUIRY = "ADVICE_INQUIRY";
	public static final String ADVICE_REQUEST_REPLY = "ADVICE_REQUEST_REPLY";
	public static final String ADVICE_INQUIRY_REPLY = "ADVICE_INQUIRY_REPLY";
	public static final String TXN_DATA_INPUT_REQUEST = "TXN_DATA_INPUT_REQUEST";
	public static final String UNKNOWN_MSG_APPLICATION = "UNKNOWN_MSG_APPLICATION";
	public static final String UNKNOWN_MSG_APPLICATION_ACTION = "UNKNOWN_MSG_ACTION";
	
}
