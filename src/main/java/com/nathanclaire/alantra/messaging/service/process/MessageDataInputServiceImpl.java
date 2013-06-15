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
import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.PropertyUtils;
import com.nathanclaire.alantra.datasource.etl.TableData;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.request.MessageRequest;
import com.nathanclaire.alantra.messaging.service.entity.MessageAttachementsService;
import com.nathanclaire.alantra.messaging.service.entity.MessageClassificationService;
import com.nathanclaire.alantra.messaging.service.entity.MessageService;
import com.nathanclaire.alantra.messaging.service.entity.MessageStatusService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;

/**
 * @author Edward Banfa 
 *
 */
@Stateless
public class MessageDataInputServiceImpl extends BaseProcessService implements MessageDataInputService {

	@Inject MessageService messageService;
	@Inject MessageTypeService messageTypeService;
	@Inject MessageStatusService messageStatusService;
	@Inject MessageAttachementsService attachementsService;
	@Inject MessageProcessingService messageProcessingService;
	@Inject MessageClassificationService messageClassificationService;
	
	private Logger logger = LoggerFactory.getLogger(MessageDataInputServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.nathanclaire.alantra.base.service.process.EntityDataInputService#processDataInput(com.nathanclaire.alantra.base.request.BaseRequest, com.nathanclaire.alantra.base.request.BaseRequest)
	 */
	@Override
	public BaseEntity processDataInput(BaseRequest primaryEntityRequest, BaseRequest secEntityRequest, TableData tableData) throws ApplicationException {
		// 1. Initialize base properties, case instance and set data channel.
		PropertyUtils.initializeBaseFields(primaryEntityRequest);
		MessageRequest messageRequest = (MessageRequest) primaryEntityRequest;
		DataChannel dataChannel = getDataImportService(tableData.getSourceServiceCode());
		messageRequest.setDataChannelId(dataChannel.getId());
		// 3. Get message type, message class and message status;
		messageRequest.setMessageStatusId(messageProcessingService.getReceivedMessageStatus().getId());
		messageRequest.setMessageTypeId(messageProcessingService.getMessageType(messageRequest).getId());
		messageRequest.setMessageClassificationId(messageProcessingService.getMessageClassification(dataChannel).getId());
		// 6. Process other fields and save
		Message message = messageService.findByCode(messageRequest.getCode());
		if(message == null) message = messageService.create(messageRequest);
		// 10. Get message attachment request from the second parameter
		// 11. Set the URL to location of save file
		// 12. Set the parent message id to id of message object from above
		// 11. Process other fields.
		// 13. Save
		// 14. Fire message created event
		// 14. Return the message object from above
		return message;
	}

}
