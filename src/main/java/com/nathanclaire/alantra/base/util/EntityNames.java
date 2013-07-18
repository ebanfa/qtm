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
		entityNames.put("AdviceRequestMessage", "Advice Request Message");
		entityNames.put("AdviceRequestMessageStatus", "Advice Request Message Status");
		entityNames.put("AdviceStatus", "Advice Status");
		entityNames.put("AdviceType", "Advice Type");
		entityNames.put("AdviceTypeTag", "Advice Type Tag");
		entityNames.put("Advice", "Advice");
		entityNames.put("AdvicedTransaction", "Adviced Transaction");
		entityNames.put("DataChannelCategory", "Data Channel Category");
		entityNames.put("DataChannel", "Data Channel");
		entityNames.put("DataChannelStatus", "Data Channel Status");
		entityNames.put("DataChannel", "Data Channel");
		entityNames.put("DataChannelAdapter", "Data Channel Adapter");
		entityNames.put("DataChannelType", "Data Channel Type");
		entityNames.put("DataInputJobRecordsSummaryStatus", "DataInput Job Records Summary Status");
		entityNames.put("Data", "Data");
		entityNames.put("DataFieldType", "Data Field Type");
		entityNames.put("DataInputJobSummary", "Data Input Job Summary");
		entityNames.put("DataInputProcessors", "Data Input Processors");
		entityNames.put("DataType", "Data Type");
		entityNames.put("DataInputJobSummaryStatus", "Data Input Job Summary Status");
		entityNames.put("DataInput", "Data Input");
		entityNames.put("DataInputJobRecordsSummary", "Data Input Job Records Summary");
		entityNames.put("DataInputJob", "Data Input Job");
		entityNames.put("DataInputJobType", "Data Input Job Type");
		entityNames.put("DataInputJobStatus", "Data Input Job Status");
		entityNames.put("DataExtractor", "Data Extractor");
		entityNames.put("DataStructure", "Data Structure");
		entityNames.put("DataField", "Data Field");
		entityNames.put("DataLoader", "Data Loader");
		entityNames.put("DataProcessor", "Data Processor");
		entityNames.put("DataTransformer", "DataTransformer");
		entityNames.put("ServiceTransactionCategory", "Transaction Category");
		entityNames.put("ServiceTransactionType", "Transaction Type");
		entityNames.put("ServiceTransactionStatus", "Transaction Status");
		entityNames.put("ServiceTransaction", "Transaction");
		entityNames.put("ApplicationActivityGroup", "Application Activity Group");
		entityNames.put("ApplicationActivityType", "Application Activity Type");
		entityNames.put("ApplicationFormFieldType", "Application Form Field Type");
		entityNames.put("ApplicationModule", "Application Module");
		entityNames.put("ApplicationFormType", "Application Form Type");
		entityNames.put("ApplicationEntityField", "Application Entity Field");
		entityNames.put("ApplicationRelatedActivity", "Application Related Activity");
		entityNames.put("ApplicationForm", "Application Form");
		entityNames.put("ApplicationActivity", "Application Activity");
		entityNames.put("ApplicationEntityFieldType", "Application Entity Field Type");
		entityNames.put("ApplicationActivityGroupType", "Application Activity Group Type");
		entityNames.put("ApplicationEntity", "Application Entity");
		entityNames.put("ApplicationFormField", "Application Form Field");
		entityNames.put("NotificationCategory", "Notification Category");
		entityNames.put("NotificationType", "Notification Type");
		entityNames.put("CustomerNotification", "Customer Notification");
		entityNames.put("SystemUserNotification", "System User Notification");
		entityNames.put("TemplateClassification", "Template Classification");
		entityNames.put("TemplateCategory", "Template Category");
		entityNames.put("TemplateTypeTag", "Template Type Tag");
		entityNames.put("Template", "Template");
		entityNames.put("TemplateType", "Template Type");
		entityNames.put("MessageNotificationMapping", "MessageNotificationMapping");
		entityNames.put("MessageApplicationNotificationMap", "Message Application Notification Map");
		entityNames.put("MessageStatus", "Message Status");
		entityNames.put("MessageCategory", "Message Category");
		entityNames.put("MessageType", "Message Type");
		entityNames.put("Message", "Message");
		entityNames.put("MessageClassification", "Message Classification");
		entityNames.put("MessageGateway", "Message Gateway");
		entityNames.put("MessageAttachment", "Message Attachement");
		entityNames.put("MessageApplication", "Message Application");
		entityNames.put("MessageApplicationTag", "Message Application Tag");
		entityNames.put("MessageApplicationAction", "Message Application Action");
		entityNames.put("MessageApplicationActionTag", "Message Application Action Tag");
		entityNames.put("MessageAction", "Message Action");
		entityNames.put("CustomerMessage", "CustomerMessage");
		entityNames.put("AccountType", "AccountType");
		entityNames.put("LimitType", "Limit Type");
		entityNames.put("CustomerClassificationNotificationChannel", "Customer Classification Notification Channel");
		entityNames.put("CustomerTypeTransactionLimit", "Customer Type Transaction Limit");
		entityNames.put("CustomerCategoryTransactionLimit", "Customer Category Transaction Limit");
		entityNames.put("Account", "Account");
		entityNames.put("CustomerClassificationTransactionLimit", "Customer ClassificationTransactionLimit");
		entityNames.put("CustomerTransactionLimit", "CustomerTransactionLimit");
		entityNames.put("CustomerCategoryNotificationChannel", "CustomerCategoryNotificationChannel");
		entityNames.put("CustomerAccount", "CustomerAccount");
		entityNames.put("CustomerTypeNotificationChannel", "Customer Type Notification Channel");
		entityNames.put("CustomerType", "CustomerType");
		entityNames.put("CustomerClassification", "Customer Classification");
		entityNames.put("CustomerBlacklist", "Customer Blacklist");
		entityNames.put("CustomerNotificationChannel", "Customer Notification Channel");
		entityNames.put("Customer", "Customer");
		entityNames.put("CustomerCategory", "CustomerCategory");
		entityNames.put("BusinessUnit", "BusinessUnit");
		entityNames.put("Currency", "Currency");
		entityNames.put("Country", "Country");
		entityNames.put("SystemUserMessage", "System User Message");
		entityNames.put("SystemGroup", "System Group");
		entityNames.put("SystemUserGroup", "System User Group");
		entityNames.put("SystemUser", "System User");
		entityNames.put("SystemUserNotificationChannel", "SystemUserNotificationChannel");
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