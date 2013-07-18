/**
 * 
 */
package com.nathanclaire.alantra.messaging.service.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nathanclaire.alantra.base.service.process.BaseProcessService;
import com.nathanclaire.alantra.base.util.ApplicationException;
import com.nathanclaire.alantra.base.util.StringUtil;
import com.nathanclaire.alantra.datasource.model.DataChannel;
import com.nathanclaire.alantra.datasource.model.DataChannelCategory;
import com.nathanclaire.alantra.datasource.model.DataChannelType;
import com.nathanclaire.alantra.datasource.service.entity.DataChannelCategoryService;
import com.nathanclaire.alantra.messaging.util.Contact;
import com.nathanclaire.alantra.messaging.util.MessageLite;

/**
 * @author Edward Banfa 
 *
 */
public class BaseMessagingService extends BaseProcessService {
	
	public static final String APP_PHONE_NO = "";
	public static final String APP_EMAIL_ADDR = "advicepro@nathanclaire.com";
	public static final String CONFIG_ERROR_NO_CHANNEL_TY_FOUND = "BaseMessagingService.CONFIG_ERROR_NO_CHANNEL_TY_FOUND";
	public static final String CONFIG_ERROR_NO_CHANNEL_CAT_FOUND = "BaseMessagingService.CONFIG_ERROR_NO_CHANNEL_CAT_FOUND";


	/**
	 * @param channels
	 * @param subjectText
	 * @param messageText
	 * @param contact
	 * @return
	 * @throws ApplicationException
	 */
	protected Map<DataChannel, MessageLite> prepareMessages(
			List<DataChannel> channels, String subjectText, String messageText,
			Contact contact) throws ApplicationException {
		Map<DataChannel, MessageLite> messagingMapping = new HashMap<DataChannel, MessageLite>();
		// Get the message lite for each channel
		for(DataChannel channel: channels)
		{
			DataChannelCategory channelCategory = getChannelCategory(channel);
			// Process SMS bound messages
			if(channelCategory.getCode().equals(DataChannelCategoryService.SMS_DATA_CHANNEL))
				processSMSChannel(contact, messagingMapping, channel, subjectText, messageText);
			// Process email bound messages
			if(channelCategory.getCode().equals(DataChannelCategoryService.EMAIL_DATA_CHANNEL))
				processEmailChannel(contact, messagingMapping, channel, subjectText, messageText);
		}
		return messagingMapping;
	}

	/**
	 * @param contact
	 * @param messagingMapping
	 * @param channel
	 * @param subjectText
	 * @param messageText
	 */
	protected void processSMSChannel(Contact contact,	
			Map<DataChannel, MessageLite> messagingMapping, DataChannel channel, String subjectText, String messageText) 
	{
		if(StringUtil.isValidString(contact.getPrimaryPhone()))
			messagingMapping.put(channel, 
					initializeMessageLite(APP_PHONE_NO, contact.getPrimaryPhone(), subjectText, messageText));
		if(StringUtil.isValidString(contact.getSecondaryPhone()))
			messagingMapping.put(channel, 
					initializeMessageLite(APP_PHONE_NO, contact.getSecondaryPhone(), subjectText, messageText));
	}
	
	/**
	 * @param contact
	 * @param messagingMapping
	 * @param channel
	 * @param subjectText
	 * @param messageText
	 */
	protected void processEmailChannel(Contact contact,	
			Map<DataChannel, MessageLite> messagingMapping, DataChannel channel, String subjectText, String messageText) 
	{
		if(StringUtil.isValidString(contact.getPrimaryEmail()))
			messagingMapping.put(channel, 
					initializeMessageLite(APP_EMAIL_ADDR, contact.getPrimaryEmail(), subjectText, messageText));
		if(StringUtil.isValidString(contact.getSecondaryEmail()))
			messagingMapping.put(channel, 
					initializeMessageLite(APP_EMAIL_ADDR, contact.getSecondaryEmail(), subjectText, messageText));
	}

	/**
	 * @param channel
	 * @return
	 * @throws ApplicationException
	 */
	protected DataChannelCategory getChannelCategory(DataChannel channel) throws ApplicationException {
		DataChannelType channelType = channel.getDataChannelType();
		if(channelType == null)
			throw new ApplicationException(CONFIG_ERROR_NO_CHANNEL_TY_FOUND);
		DataChannelCategory channelCategory = channelType.getDataChannelCategory();
			if(channelCategory == null)
				throw new ApplicationException(CONFIG_ERROR_NO_CHANNEL_CAT_FOUND);
		return channelCategory;
	}

	/**
	 * @param srcAddress
	 * @param dstAddress
	 * @param subjectText
	 * @param messageText
	 * @return
	 */
	protected MessageLite initializeMessageLite(String srcAddress, 
			String dstAddress, String subjectText, String messageText)
	{
		MessageLite messageLite =  new MessageLite();
		messageLite.setMessageFrom(srcAddress);
		messageLite.setMessageTo(dstAddress);
		messageLite.setSubjectLine(subjectText);
		messageLite.setMessageBody(messageText);
		return messageLite;
	}
}
