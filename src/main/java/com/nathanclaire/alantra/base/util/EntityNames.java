/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.util;

import java.util.HashMap;
import java.util.Map;


public class EntityNames{

	private Map<String, String> entityNames = new HashMap<String, String>();
	
	
	public EntityNames()
	{

		entityNames.put("AdviceClassification", "Advice Classification");
		entityNames.put("AdviceStatus", "Advice Status");
		entityNames.put("AdviceType", "Advice Type");
		entityNames.put("AdviceTypeTag", "Advice Type Tag");
		entityNames.put("Advice", "Advice");
		entityNames.put("AdvicedTransaction", "Adviced Transaction");
		entityNames.put("Channel", "Channel");
		entityNames.put("ChannelStatus", "Channel Status");
		entityNames.put("ChannelType", "Channel Type");
		entityNames.put("ChannelHandler", "Channel Handler");
		entityNames.put("ChannelHandlerType", "Channel Handler Type");
		entityNames.put("ChannelPipeline", "Channel Pipeline");
		entityNames.put("ChannelPipelineHandler", "Channel Pipeline Handler");
		entityNames.put("DataChannelCategory", "Data Channel Category");
		entityNames.put("DataChannel", "Data Channel");
		entityNames.put("DataChannelStatus", "Data Channel Status");
		entityNames.put("DataChannelClassification", "Data Channel Classification");
		entityNames.put("DataChannel", "Data Channel");
		entityNames.put("DataChannelAdapter", "Data Channel Adapter");
		entityNames.put("DataChannelType", "Data Channel Type");
		entityNames.put("Data", "Data");
		entityNames.put("DataFieldType", "Data Field Type");
		entityNames.put("DataType", "Data Type");
		entityNames.put("DataInput", "Data Input");
		entityNames.put("DataInputJob", "Data Input Job");
		entityNames.put("DataInputJobType", "Data Input Job Type");
		entityNames.put("DataInputJobStatus", "Data Input Job Status");
		entityNames.put("DataStructure", "Data Structure");
		entityNames.put("DataField", "Data Field");
		entityNames.put("DataTransformer", "DataTransformer");
		entityNames.put("DataTable", "Data Table");
		entityNames.put("DataRow", "Data Row");
		entityNames.put("DataCell", "Data Row");
		entityNames.put("ServiceTransactionCategory", "Transaction Category");
		entityNames.put("ServiceTransactionType", "Transaction Type");
		entityNames.put("ServiceTransactionStatus", "Transaction Status");
		entityNames.put("ServiceTransaction", "Transaction");
		entityNames.put("CustCatTxnTypeNotificationOptions", "Customer Category Notification Options");
		entityNames.put("CustTypeTxnTypeNotificationOptions", "Customer Type Notification Options");
		entityNames.put("CustTxnTypeNotificationOptions", "Customer Notification Options");
		entityNames.put("CustCatTxnTypeConfirmationOptions", "Customer Category Confirmation Options");
		entityNames.put("CustTypeTxnTypeConfirmationOptions", "Customer Type Confirmation Options");
		entityNames.put("CustTxnTypeConfirmationOptions", "Customer Confirmation Options");
		entityNames.put("TxnNotificationStatus", "TxnNotificationStatus");
		entityNames.put("TxnConfirmationStatus", "TxnConfirmationStatus");
		entityNames.put("TxnAwaitingNotification", "TxnAwaitingNotification");
		entityNames.put("TxnAwaitingConfirmation", "TxnAwaitingConfirmation");
		entityNames.put("ApplicationActivityGroup", "Application Activity Group");
		entityNames.put("ApplicationActivityType", "Application Activity Type");
		entityNames.put("ApplicationFormFieldType", "Application Form Field Type");
		entityNames.put("ApplicationModule", "Application Module");
		entityNames.put("ApplicationFormType", "Application Form Type");
		entityNames.put("ApplicationEntityField", "Application Entity Field");
		entityNames.put("ApplicationRelatedActivity", "Application Related Activity");
		entityNames.put("TempRelatedActivity", "Temp Related Activity");
		entityNames.put("ApplicationForm", "Application Form");
		entityNames.put("ApplicationActivity", "Application Activity");
		entityNames.put("ApplicationEntityFieldType", "Application Entity Field Type");
		entityNames.put("ApplicationActivityGroupType", "Application Activity Group Type");
		entityNames.put("ApplicationEntity", "Application Entity");
		entityNames.put("ApplicationFormField", "Application Form Field");
		entityNames.put("NotificationCategory", "Notification Category");
		entityNames.put("NotificationType", "Notification Type");
		entityNames.put("TemplateClassification", "Template Classification");
		entityNames.put("TemplateCategory", "Template Category");
		entityNames.put("TemplateTypeTag", "Template Type Tag");
		entityNames.put("Template", "Template");
		entityNames.put("TemplateType", "Template Type");
		entityNames.put("MessageStatus", "Message Status");
		entityNames.put("MessageCategory", "Message Category");
		entityNames.put("MessageType", "Message Type");
		entityNames.put("Message", "Message");
		entityNames.put("MessageClassification", "Message Classification");
		entityNames.put("MessageAttachment", "Message Attachment");
		entityNames.put("CustomerMessage", "CustomerMessage");
		entityNames.put("AccountType", "AccountType");
		entityNames.put("Account", "Account");
		entityNames.put("CustomerAccount", "CustomerAccount");
		entityNames.put("CustomerType", "CustomerType");
		entityNames.put("CustomerClassification", "Customer Classification");
		entityNames.put("CustomerBlacklist", "Customer Blacklist");
		entityNames.put("Customer", "Customer");
		entityNames.put("CustomerCategory", "CustomerCategory");
		entityNames.put("CustomerCategoryCommsChannel", "Customer Category Comms Channel");
		entityNames.put("CustomerClassificationCommsChannel", "Customer Classification Comms Channel");
		entityNames.put("CustomerTypeCommsChannel", "Customer Type Comms Channel");
		entityNames.put("CustomerCommsChannel", "Customer Comms Channel");
		entityNames.put("LimitType", "Limit Type");
		entityNames.put("CustomerTypeTransactionLimit", "Customer Type Transaction Limit");
		entityNames.put("CustomerCategoryTransactionLimit", "Customer Category Transaction Limit");
		entityNames.put("CustomerClassificationTransactionLimit", "Customer ClassificationTransactionLimit");
		entityNames.put("CustomerTransactionLimit", "CustomerTransactionLimit");
		entityNames.put("BusinessUnit", "BusinessUnit");
		entityNames.put("Currency", "Currency");
		entityNames.put("Country", "Country");
		entityNames.put("SystemUserMessage", "System User Message");
		entityNames.put("SystemGroup", "System Group");
		entityNames.put("SystemUserGroup", "System User Group");
		entityNames.put("SystemUser", "System User");
		entityNames.put("CurrentUserSession", "Current User Session");
	}


	/**
	 * @return the entityNames
	 */
	public Map<String, String> getEntityNames() {
		return entityNames;
	}


	/**
	 * @param entityNames the entityNames to set
	 */
	public void setEntityNames(Map<String, String> entityNames) {
		this.entityNames = entityNames;
	}

}