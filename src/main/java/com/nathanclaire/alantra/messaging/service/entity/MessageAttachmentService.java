/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.entity;

import com.nathanclaire.alantra.base.service.entity.BaseEntityService;
import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;
import com.nathanclaire.alantra.messaging.response.MessageAttachmentResponse;

/**
 * @author Edward Banfa
 *
 */
public interface MessageAttachmentService 
	extends BaseEntityService<MessageAttachment, MessageAttachmentResponse, MessageAttachmentRequest>
{

	public static final String CSV_MIME_TYPE = "CSV";
	public static final String EXCEL_MIME_TYPE = "EXCEL";
}
