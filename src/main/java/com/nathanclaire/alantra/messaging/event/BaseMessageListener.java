/**
 * 
 */
package com.nathanclaire.alantra.messaging.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nathanclaire.alantra.advice.model.Advice;
import com.nathanclaire.alantra.advice.model.AdviceStatus;
import com.nathanclaire.alantra.advice.service.entity.AdviceService;
import com.nathanclaire.alantra.advice.service.process.AdviceProcessingService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.customer.model.Customer;
import com.nathanclaire.alantra.customer.service.process.CustomerProcessingService;
import com.nathanclaire.alantra.datasource.model.Data;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.model.DataInputJob;
import com.nathanclaire.alantra.datasource.model.DataType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelTypeService;
import com.nathanclaire.alantra.datasource.service.entity.DataTypeService;
import com.nathanclaire.alantra.datasource.service.process.JobHelper;
import com.nathanclaire.alantra.messaging.model.Message;
import com.nathanclaire.alantra.messaging.model.MessageAttachment;
import com.nathanclaire.alantra.messaging.service.entity.MessageApplicationActionService;
import com.nathanclaire.alantra.messaging.service.entity.MessageAttachmentService;
import com.nathanclaire.alantra.messaging.service.entity.MessageTypeService;
import com.nathanclaire.alantra.messaging.service.process.MessagingModuleService;
import com.nathanclaire.alantra.notification.service.entity.TemplateTypeTagService;
import com.nathanclaire.alantra.notification.service.process.NotificationService;
import com.nathanclaire.alantra.security.model.SystemUser;
import com.nathanclaire.alantra.security.service.process.SecurityService;

/**
 * @author Edward Banfa 
 *
 */
public class BaseMessageListener {

	@Inject JobHelper helper;
	@Inject AdviceService adviceService;
	@Inject DataTypeService dataTypeService;
	@Inject SecurityService securityService;
	@Inject NotificationService notificationService;
	@Inject AdviceProcessingService adviceProcessingService;
	@Inject MessagingModuleService messagingModuleService;
	@Inject CustomerProcessingService customerProcessingService;

	public static final String CONFIG_ERROR_DATA_WITHOUT_DATA_CHANNEL = 	"BaseMessageListener.CONFIG_ERROR_DATA_WITHOUT_DATA_CHANNEL";
	public static final String CONFIG_ERROR_DATA_CHANNEL_WITHOUT_CHANNEL_TY = 
			"BaseMessageListener.CONFIG_ERROR_DATA_CHANNEL_WITHOUT_CHANNEL_TY";
	public static final String CONFIG_ERROR_ADVICE_STATUS_NOT_FOUND = "BaseMessageListener.CONFIG_ERROR_ADVICE_STATUS_NOT_FOUND";
	public static final String DATA_TYPE_NOT_FOUND = 	"MessageReceivedEventListener.DATA_TYPE_NOT_FOUND";
	private Logger logger = LoggerFactory.getLogger(BaseMessageListener.class);
	
	/**
	 * @param event
	 * @return
	 * @throws ApplicationException
	 */
	protected Message getMessage(MessageEvent event) throws ApplicationException {
		return messagingModuleService.getMessage(event.getMessageId());
	}

	/**
	 * @param advice
	 * @throws ApplicationException
	 */
	protected String getAdviceStatusInfo(String adviceReferenceNo) throws ApplicationException {

		Advice advice = adviceService.findByCode(adviceReferenceNo);
		if(advice != null)
		{
			AdviceStatus adviceStatus = advice.getAdviceStatus();
			if(adviceStatus == null) throw new ApplicationException(CONFIG_ERROR_ADVICE_STATUS_NOT_FOUND);
			return adviceStatus.getName();
		}
		return null;
	}

	/**
	 * Message text is the message body when the message has not subject.
	 * Otherwise message text is the message subject.
	 * @param message
	 * @param messageClassification
	 * @return
	 * @throws ApplicationException 
	 */
	protected String getMessageText(Message message) throws ApplicationException 
	{
		String messageText = message.getMessageTxt();
		if(StringUtil.isValidString(message.getMessageSubject()))
			messageText = message.getMessageSubject();
		return messageText;
	}
	
	/**
	 * @param advice
	 * @return
	 * @throws ApplicationException
	 */
	protected Map<String, String> initializeTemplateTagValues(MessageEvent event) 	
			throws ApplicationException 
	{
		Map<String,String> templateTagValues = new HashMap<String, String>();
		templateTagValues.put(TemplateTypeTagService.MESSAGE_CODE, event.getMessageCode());
		templateTagValues.put(TemplateTypeTagService.MESSAGE_JOB_CODE, event.getJobCode());
		templateTagValues.put(TemplateTypeTagService.MESSAGE_TY_CODE, event.getMessageTypeCode());
		templateTagValues.put(TemplateTypeTagService.MESSAGE_SOURCE_ADDR, event.getSourceAddress());
		templateTagValues.put(TemplateTypeTagService.MESSAGE_DESTINATION_ADDR, event.getDestinationAddress());
		return templateTagValues;
	}

	/**
	 * @param customerId
	 * @return
	 * @throws ApplicationException
	 */
	protected Customer getCustomer(Integer customerId) throws ApplicationException 
	{
		return customerProcessingService.getCustomerById(customerId);
	}
	
	/**
	 * @param userId
	 * @return
	 * @throws ApplicationException
	 */
	protected SystemUser getUser(Integer userId) throws ApplicationException {
		return securityService.getUserById(userId);
	}
	
	/**
	 * @param tagCode
	 * @param tagValue
	 * @param templateTagValues
	 * @return
	 */
	protected Map<String, String> addToTemplateTagValues(String tagCode, String tagValue, Map<String, String> templateTagValues)
	{
		templateTagValues.put(tagCode, tagValue);
		return templateTagValues;
	}
	
	/**
	 * @param attachmentId
	 * @return
	 * @throws ApplicationException
	 */
	protected MessageAttachment getMessageAttachment(Integer attachmentId) throws ApplicationException  {
		return messagingModuleService.getAttachment(attachmentId);
	}

	/**
	 * @param event
	 * @throws ApplicationException
	 */
	protected DataInputJob processMessageAttachment(Integer attachmentId, String messageTyCode) throws ApplicationException {
		// 1. Get the message type
		MessageAttachment attachement = getMessageAttachment(attachmentId);
		// 2. Get the data channel type
		Data attachmentDataConfig = null;
		String dataChannelTypeCode = getDataChannelTypeCode(attachement.getDataTy());
		// Ignore the attachments for advice inquiries
		if(messageTyCode.equals(MessageApplicationActionService.ADVICE_INQUIRY))
			return null;
		// Process attachments for advice request
		if(messageTyCode.equals(MessageApplicationActionService.ADVICE_REQUEST))
			attachmentDataConfig = getJobDataConfig(dataChannelTypeCode, DataTypeService.ADVICE_REQUEST_DATA);
		// Process attachments for transaction data input requests
		else if(messageTyCode.equals(MessageApplicationActionService.TXN_DATA_INPUT_REQUEST))
			attachmentDataConfig = getJobDataConfig(dataChannelTypeCode, DataTypeService.TRANSACTION_DATA);
		else return null;
		if(attachmentDataConfig == null)
			logger.error("Data input configuration not found for attachment of message of type {}, aborting auto job creation", 
					messageTyCode);
		// 5. Get the data job 
		DataInputJob inputJob = helper.createNonCyclicLocalFileDataInputJob(attachement.getDataUrl(), attachement.getDataTy(), 
				attachmentDataConfig.getDataStructure(), attachmentDataConfig.getDataType(), dataChannelTypeCode);
		return inputJob;
	}

	/**
	 * @param dataChannelTypeCode
	 * @throws ApplicationException
	 */
	private Data getJobDataConfig(String dataChannelTypeCode, String dataTypeCode) throws ApplicationException {
		Data requestData = null;
		// 1. Get the data that has advice 
		DataType dataType = dataTypeService.findByCode(dataTypeCode);
		if(dataType == null) throw new ApplicationException(DATA_TYPE_NOT_FOUND, dataTypeCode);
		Set<Data> allDataOfType = dataType.getDatas();
		for(Data data: allDataOfType)
		{
			// The channel
			DataChannel dataChannel = data.getDataChannel();
			if(dataChannel == null) 
				throw new ApplicationException(CONFIG_ERROR_DATA_WITHOUT_DATA_CHANNEL);
			// Get the channel type
			DataChannelType dataChannelType = dataChannel.getDataChannelType();
			if(dataChannelType == null) 
				throw new ApplicationException(CONFIG_ERROR_DATA_CHANNEL_WITHOUT_CHANNEL_TY);
			if(dataChannelType.getCode().equals(dataChannelTypeCode))
				requestData = data;
			break;
		}
		return requestData;
	}

	/**
	 * @param fileType
	 */
	private String getDataChannelTypeCode(String fileType) {
		String dataChannelType = null;
		if(fileType.equalsIgnoreCase(MessageAttachmentService.CSV_MIME_TYPE))
			dataChannelType = DataChannelTypeService.FILE_CSV_CHANNEL;
		if(fileType.equalsIgnoreCase(MessageAttachmentService.EXCEL_MIME_TYPE))
			dataChannelType = DataChannelTypeService.FILE_EXCEL_CHANNEL;
		return dataChannelType;
	}
}
