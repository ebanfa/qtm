/**
 * 
 */
package com.nathanclaire.alantra.notification.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.notification.model.NotificationType;
import com.nathanclaire.alantra.notification.request.NotificationTypeRequest;
import com.nathanclaire.alantra.notification.response.NotificationTypeResponse;

/**
 * @author Edward Banfa
 *
 */
public interface NotificationTypeService extends BaseEntityService<NotificationType, NotificationTypeResponse, NotificationTypeRequest>
{

	public static final String ADVICE_INQUIRY_RESPONSE = "ADVICE_INQUIRY_RESPONSE";
	public static final String ADVICE_REQUEST_RESPONSE = "ADVICE_REQUEST_RESPONSE";
	public static final String UNCLASSIFIED_MESSAGE_RECEIVED = "UNCLASSIFIED_MESSAGE_RECEIVED";
	public static final String TXN_DATA_INPUT_REQUEST_RESPONSE = "TXN_DATA_INPUT_REQUEST_RESPONSE";
	public static final String UNREGISTERED_USER_MESSAGE_RECEIVED = "UNREGISTERED_USER_MESSAGE_RECEIVED";
	public static final String UNREGISTERED_CUSTOMER_MESSAGE_RECEIVED = "UNREGISTERED_CUSTOMER_MESSAGE_RECEIVED";
	public static final String ADVICE_REQUEST_WITH_ATTACHMENT_RESPONSE = "ADVICE_REQUEST_WITH_ATTACHMENT_RESPONSE";
	public static final String ADVICE_INQUIRY_ADVICE_NOT_FOUND_RESPONSE = "ADVICE_INQUIRY_ADVICE_NOT_FOUND_RESPONSE";
	public static final String ADVICE_REQUEST_WITH_ATTACHMENT_ERROR_RESPONSE = "ADVICE_REQUEST_WITH_ATTACHMENT_ERROR_RESPONSE";
	public static final String TXN_DATA_INPUT_REQUEST_WITH_ATTACHMENT_RESPONSE = "TXN_DATA_INPUT_REQUEST_WITH_ATTACHMENT_RESPONSE";
	public static final String ADVICE_INQUIRY_INVALID_ADVICE_REFERENCE_RESPONSE = "ADVICE_INQUIRY_INVALID_ADVICE_REFERENCE_RESPONSE";
	
}
