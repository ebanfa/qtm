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
		entityNames.put("AdviceStatus", "Advice Status");
		entityNames.put("AdviceType", "Advice Type");
		entityNames.put("AdviceTypeTag", "Advice Type Tag");
		entityNames.put("Advice", "Advice");
		entityNames.put("DataSourceStructure", "Data Source Structure");
		entityNames.put("DataSourceCategory", "Data Source Category");
		entityNames.put("DataSourceField", "Data Source Field");
		entityNames.put("DataInputJob", "Data Input Job");
		entityNames.put("DataSource", "Data Source");
		entityNames.put("DataSourceFieldType", "Data Source Field Type");
		entityNames.put("DataSourceType", "Data Source Type");
		entityNames.put("Service", "Service");
		entityNames.put("ServiceType", "Service Type");
		entityNames.put("ServiceCategory", "Service Category");
		entityNames.put("ServiceMode", "Service Mode");
		entityNames.put("ServiceTransactionType", "Service Transaction Type");
		entityNames.put("HostType", "Host Type");
		entityNames.put("ServicePeer", "Service Peer");
		entityNames.put("ServiceTransaction", "Service Transaction");
		entityNames.put("ServiceProtocolAdapter", "Service Protocol Adapter");
		entityNames.put("Host", "Host");
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
		entityNames.put("MessageStatus", "Message Status");
		entityNames.put("MessageType", "Message Type");
		entityNames.put("Message", "Message");
		entityNames.put("CustomerType", "Customer Type");
		entityNames.put("CustomerClassification", "Customer Classification");
		entityNames.put("CustomerBlacklist", "Customer Blacklist");
		entityNames.put("Customer", "Customer");
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