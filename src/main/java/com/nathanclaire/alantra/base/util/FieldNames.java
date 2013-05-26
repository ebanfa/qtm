/**
 *  Nathan Claire Group.
 */
package com.nathanclaire.alantra.base.util;

import java.util.HashMap;
import java.util.Map;


public class FieldNames{

	private Map<String, String> fieldNames = new HashMap<String, String>();
	
	
	public FieldNames()
	{
		
		fieldNames.put("id", "Id");
		fieldNames.put("name", "Name");
		fieldNames.put("description", "Description");
		fieldNames.put("code", "Code");
		fieldNames.put("effectiveDt", "Effective Date");
		fieldNames.put("recSt", "Status");
		fieldNames.put("encrpytedFg", "Encrypted");
		fieldNames.put("versionNo", "Version");
		fieldNames.put("rowTs", "Time Stamp");
		fieldNames.put("createdDt", "Created On");
		fieldNames.put("createdByUsr", "Created By");
		fieldNames.put("lastModifiedDt", "Last Modified On");
		fieldNames.put("lastModifiedUsr", "Last Modified By");
		//AdviceStatus
		fieldNames.put("advices", "Advices");
		//AdviceType
		fieldNames.put("serviceTransactionType", "Transaction Type");
		fieldNames.put("reqFeedback", "Feed Back");
		fieldNames.put("feedbackMsg", "Feed Back Msg");
		fieldNames.put("adviceTypeTags", "Advice Type Tags");
		fieldNames.put("advices", "Advices");
		//AdviceTypeTag
		fieldNames.put("adviceType", "Advice Type");
		fieldNames.put("adviceTyTagVal", "adviceTyTagVal");
		fieldNames.put("isRegexFg", "isRegexFg");
		//Advice
		fieldNames.put("customer", "Customer");
		fieldNames.put("adviceStatus", "Advice Status");
		fieldNames.put("adviceType", "Advice Type");
		fieldNames.put("commEventId", "Event");
		fieldNames.put("adviceTxt", "Text");
		fieldNames.put("cardNo", "Card No");
		fieldNames.put("accountNo", "Account No");
		fieldNames.put("chequeNo", "Cheque");
		fieldNames.put("accountNm", "Account Name");
		fieldNames.put("amount", "Amount");
		//DataSourceStructure
		fieldNames.put("dsStructDelimeter", "Delimeter");
		fieldNames.put("targetEntityCd", "Target Entity");
		fieldNames.put("dataSources", "Data Sources");
		fieldNames.put("dataSourceFields", "Fields");
		//DataSourceCategory
		fieldNames.put("dataSourceTypes", "Data Source Types");
		//DataSourceField
		fieldNames.put("dataSourceStructure", "Data Source Structure");
		fieldNames.put("dataSourceFieldType", "Field Type");
		fieldNames.put("dsFieldTarget", "Field Target");
		//DataInputJob
		fieldNames.put("dataSource", "Data Source");
		fieldNames.put("diFreqVal", "Frequency Value");
		fieldNames.put("diFreqCd", "Frequency Code");
		//DataSource
		fieldNames.put("dataSourceStructure", "data Source Structure");
		fieldNames.put("dataSourceType", "Data Source Type");
		fieldNames.put("dsUrl", "URL");
		fieldNames.put("dsDb", "DB");
		fieldNames.put("dsTblNm", "Table Name");
		fieldNames.put("usrNm", "Username");
		fieldNames.put("password", "Password");
		fieldNames.put("dataInputJobs", "Input Jobs");
		//DataSourceFieldType
		fieldNames.put("dataSourceFields", "Data Source Fields");
		//DataSourceType
		fieldNames.put("dataSourceCategory", "Data Source Category");
		fieldNames.put("dataSources", "Data Sources");
		//Service
		fieldNames.put("serviceType", "Service Type");
		fieldNames.put("serviceProtocolAdapter", "ProtocolAdapter");
		fieldNames.put("serviceMode", "Service Mode");
		fieldNames.put("serviceCategory", "Service Category");
		fieldNames.put("portNo", "Port No");
		fieldNames.put("ipAddress", "IP Address");
		fieldNames.put("serviceTransactions", "Service Transactions");
		fieldNames.put("servicePeers", "Peers");
		//ServiceType
		fieldNames.put("services", "Services");
		//ServiceCategory
		fieldNames.put("services", "Service");
		//ServiceMode
		fieldNames.put("services", "Services");
		//ServiceTransactionType
		fieldNames.put("serviceTransactions", "Service Transactions");
		//HostType
		fieldNames.put("hosts", "Hosts");
		//ServicePeer
		fieldNames.put("service", "Service");
		fieldNames.put("host", "Host");
		//ServiceTransaction
		fieldNames.put("service", "Service");
		fieldNames.put("serviceTransactionType", "Transaction Type");
		fieldNames.put("amount", "Amount");
		fieldNames.put("txnDate", "Txn Date");
		fieldNames.put("accountNo", "Account No");
		fieldNames.put("accountNm", "Account Name");
		//ServiceProtocolAdapter
		fieldNames.put("className", "Class Name");
		fieldNames.put("hosts", "Hosts");
		fieldNames.put("services", "Services");
		//Host
		fieldNames.put("serviceProtocolAdapter", "Protocol Adapter");
		fieldNames.put("hostType", "Host Type");
		fieldNames.put("portNo", "Port");
		fieldNames.put("ipAddress", "IP Address");
		fieldNames.put("servicePeers", "Service Peers");
		//ApplicationActivityGroup
		fieldNames.put("applicationActivityGroup", "applicationActivityGroup");
		fieldNames.put("applicationActivityGroupType", "applicationActivityGroupType");
		fieldNames.put("applicationModule", "applicationModule");
		fieldNames.put("grpUrl", "grpUrl");
		fieldNames.put("grpSeq", "grpSeq");
		fieldNames.put("displayNm", "displayNm");
		fieldNames.put("displayImg", "displayImg");
		fieldNames.put("displayFg", "displayFg");
		fieldNames.put("isParent", "isParent");
		fieldNames.put("applicationActivityGroups", "applicationActivityGroups");
		fieldNames.put("applicationActivities", "applicationActivities");
		//ApplicationActivityType
		fieldNames.put("applicationActivities", "applicationActivities");
		//ApplicationFormFieldType
		fieldNames.put("applicationFormFields", "applicationFormFields");
		//ApplicationModule
		fieldNames.put("sequenceNo", "sequenceNo");
		fieldNames.put("displayNm", "displayNm");
		fieldNames.put("displayImg", "displayImg");
		fieldNames.put("displayFg", "displayFg");
		fieldNames.put("applicationForms", "applicationForms");
		fieldNames.put("applicationActivityGroups", "applicationActivityGroups");
		fieldNames.put("applicationEntities", "applicationEntities");
		fieldNames.put("applicationActivities", "applicationActivities");
		//ApplicationFormType
		fieldNames.put("applicationForms", "applicationForms");
		//ApplicationEntityField
		fieldNames.put("applicationEntityFieldType", "applicationEntityFieldType");
		fieldNames.put("applicationRelatedEntity", "applicationRelatedEntity");
		fieldNames.put("applicationEntity", "applicationEntity");
		fieldNames.put("primarykeyFg", "primarykeyFg");
		fieldNames.put("storage", "storage");
		fieldNames.put("requiredFg", "requiredFg");
		fieldNames.put("uniqueFg", "uniqueFg");
		fieldNames.put("relatedFg", "relatedFg");
		fieldNames.put("size", "size");
		fieldNames.put("maxDigits", "maxDigits");
		fieldNames.put("decimalPrecision", "decimalPrecision");
		fieldNames.put("sequenceNo", "sequenceNo");
		fieldNames.put("applicationFormFields", "applicationFormFields");
		//ApplicationRelatedActivity
		fieldNames.put("sourceApplicationActivity", "sourceApplicationActivity");
		fieldNames.put("destinationApplicationActivity", "destinationApplicationActivity");
		fieldNames.put("relActSeq", "relActSeq");
		//ApplicationForm
		fieldNames.put("applicationFormType", "applicationFormType");
		fieldNames.put("applicationModule", "applicationModule");
		fieldNames.put("applicationEntity", "applicationEntity");
		fieldNames.put("displayNm", "displayNm");
		fieldNames.put("displayNmPlural", "displayNmPlural");
		fieldNames.put("hasTable", "hasTable");
		fieldNames.put("dbName", "dbName");
		fieldNames.put("applicationFormFields", "applicationFormFields");
		fieldNames.put("applicationActivities", "applicationActivities");
		//ApplicationActivity
		fieldNames.put("applicationActivityGroup", "applicationActivityGroup");
		fieldNames.put("applicationModule", "applicationModule");
		fieldNames.put("applicationEntity", "applicationEntity");
		fieldNames.put("applicationForm", "applicationForm");
		fieldNames.put("applicationActivityType", "applicationActivityType");
		fieldNames.put("activityUrl", "activityUrl");
		fieldNames.put("activitySeq", "activitySeq");
		fieldNames.put("displayNm", "displayNm");
		fieldNames.put("displayImg", "displayImg");
		fieldNames.put("operationCd", "operationCd");
		fieldNames.put("relatedDestinationActivities", "relatedDestinationActivities");
		fieldNames.put("relatedSourceActivities", "relatedSourceActivities");
		//ApplicationEntityFieldType
		fieldNames.put("applicationEntityFields", "applicationEntityFields");
		//ApplicationActivityGroupType
		fieldNames.put("applicationActivityGroups", "applicationActivityGroups");
		//ApplicationEntity
		fieldNames.put("applicationModule", "applicationModule");
		fieldNames.put("displayNm", "displayNm");
		fieldNames.put("displayNmPlural", "displayNmPlural");
		fieldNames.put("hasTable", "hasTable");
		fieldNames.put("dbName", "dbName");
		fieldNames.put("applicationEntityFields", "applicationEntityFields");
		fieldNames.put("applicationForms", "applicationForms");
		fieldNames.put("applicationRelatedEntityFields", "applicationRelatedEntityFields");
		fieldNames.put("applicationActivities", "applicationActivities");
		//ApplicationFormField
		fieldNames.put("applicationFormFieldType", "applicationFormFieldType");
		fieldNames.put("applicationForm", "applicationForm");
		fieldNames.put("applicationEntityField", "applicationEntityField");
		fieldNames.put("primarykeyFg", "primarykeyFg");
		fieldNames.put("requiredFg", "requiredFg");
		fieldNames.put("relatedFg", "relatedFg");
		fieldNames.put("size", "size");
		fieldNames.put("maxDigits", "maxDigits");
		fieldNames.put("decimalPrecision", "decimalPrecision");
		fieldNames.put("sequenceNo", "sequenceNo");
		//MessageStatus
		fieldNames.put("messages", "Messages");
		//MessageType
		fieldNames.put("messages", "Messages");
		//Message
		fieldNames.put("messageType", "Message Type");
		fieldNames.put("advice", "Advice");
		fieldNames.put("messageStatus", "Message Status");
		fieldNames.put("messageFrom", "Message From");
		fieldNames.put("messageTo", "Message To");
		fieldNames.put("messageSubject", "Message Subject");
		fieldNames.put("messageTxt", "Message Txt");
		//CustomerType
		fieldNames.put("customers", "Customers");
		//CustomerClassification
		fieldNames.put("customers", "Customers");
		//CustomerBlacklist
		fieldNames.put("customer", "Customers");
		//Customer
		fieldNames.put("customerClassification", "Customer Classification");
		fieldNames.put("customerType", "Customer Type");
		fieldNames.put("email", "Email");
		fieldNames.put("mobile", "Mobile");
		fieldNames.put("advices", "Advices");
		fieldNames.put("customerBlacklists", "Black List");
	}


	/**
	 * @return the fieldNames
	 */
	public Map<String, String> getFieldNames() {
		return fieldNames;
	}


	/**
	 * @param fieldNames the fieldNames to set
	 */
	public void setFieldNames(Map<String, String> fieldNames) {
		this.fieldNames = fieldNames;
	}

}
