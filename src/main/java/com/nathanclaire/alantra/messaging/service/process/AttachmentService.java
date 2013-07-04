/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;

/**
 * @author Edward Banfa 
 *
 */
public interface AttachmentService {

	public static final String INVALID_MESSAGE_PROVIDED = "AttachmentService.INVALID_MESSAGE_PROVIDED";
	public static final String MESSAGE_STATUS_NOT_FOUND = "AttachmentService.MESSAGE_STATUS_NOT_FOUND";
	public static final String COULD_NOT_FIND_MESSAGE_ATTACHMENT = "AttachmentService.COULD_NOT_FIND_MESSAGE_ATTACHMENT";
	public static final String INVALID_ATTACHMENT_REQUEST_PROVIDED = "AttachmentService.INVALID_ATTACHMENT_REQUEST_PROVIDED";
	public static final String COULD_NOT_CREATED_MESSAGE_ATTACHMENT = "AttachmentService.COULD_NOT_CREATED_MESSAGE_ATTACHMENT";
	
	public MessageAttachment getAttachment(Integer attachmentId) throws ApplicationException;
	
	public MessageAttachment createAttachment(Message message , MessageAttachmentRequest attachmentRequest) 
			throws ApplicationException;

}
