/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageAttachmentService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class AttachmentServiceImpl extends BaseProcessService implements AttachmentService {

	@Inject MessageAttachmentService attachmentsService;
	private Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#createAttachment(com.nathanclaire.alantra.messaging.model.Message, com.nathanclaire.alantra.messaging.request.MessageAttachmentRequest)
	 */
	@Override
	public MessageAttachment createAttachment(Message message, MessageAttachmentRequest attachmentRequest) throws ApplicationException 
	{
		if(message== null) 
			throw new ApplicationException(INVALID_MESSAGE_PROVIDED);
		if(attachmentRequest == null) 
			throw new ApplicationException(INVALID_ATTACHMENT_REQUEST_PROVIDED);
		PropertyUtils.initializeBaseFields(attachmentRequest);
		attachmentRequest.setMessageId(message.getId());
		attachmentRequest.setCode(message.getCode());
		MessageAttachment messageAttachments = attachmentsService.create(attachmentRequest);
		if(messageAttachments == null) 
			throw new ApplicationException(COULD_NOT_CREATED_MESSAGE_ATTACHMENT);
		return messageAttachments;
	}

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.messaging.service.process.MessagingModuleService#getAttachment(java.lang.Integer)
	 */
	@Override
	public MessageAttachment getAttachment(Integer attachmentId)	throws ApplicationException {
		MessageAttachment messageAttachments = attachmentsService.findById(attachmentId);
		if(messageAttachments == null) throw new ApplicationException(COULD_NOT_FIND_MESSAGE_ATTACHMENT);
		return messageAttachments;
	}


}
