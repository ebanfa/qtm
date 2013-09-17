/**
 * 
 */
package com.nathanclaire.alantra.base.util;



/**
 * @author Edward Banfa
 *
 */
public class ErrorCodes {
	
	// Base
	public static final String INVALID_DATE_STRING = "DateUtil.INVALID_DATE_STRING_ERROR_CD";
	public static final String BPS_ENTITY_CREATION_ERROR_CD = "BaseProcessService.ENTITY_CREATION_ERROR_CD";
	public static final String BPS_ENTITY_CREATION_ERROR_MSG = "BaseProcessService.ENTITY_CREATION_ERROR_MSG";
	public static final String BPS_SERVICE_EXECUTION_ERROR_CD = "BaseProcessService.BPS_SERVICE_EXECUTION_ERROR_CD";
	public static final String BPS_NULL_PARAMETER_ERROR_MSG = "BaseProcessService.BPS_NULL_PARAMETER_ERROR_MSG";
	public static final String BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD = "BaseProcessService.BPS_ENTITY_INSTANCE_NOT_FOUND_ERROR_CD";
	public static final String BPS_ENTITY_PROPERTY_VALUE_NOT_FOUND_ERROR_CD = "BaseProcessService.BPS_ENTITY_PROPERTY_VALUE_NOT_FOUND_ERROR_CD";
	// Advice module
	public static final String ADIS_DATA_INPUT_ERROR = "AdviceDataInputServiceImpl.ADIS_DATA_INPUT_ERROR";
	//Customer module
	public static final String CAS_ACCOUNT_ERROR_CD = "CustomerAccountService.CAS_ACCOUNT_ERROR_CD";
	public static final String CAS_CUST_HAS_NO_DEFUALT_ACCT_ERROR_MSG = "CustomerAccountService.CAS_CUST_HAS_NO_DEFUALT_ACCT_ERROR_MSG";
	public static final String CAS_CUST_HAS_NO_ACCT_ERROR_MSG = "CustomerAccountService.CAS_CUST_HAS_NO_ACCT_ERROR_MSG";
	public static final String CCS_CUSTOMER_CONTACT_ERROR_CD = "CustomerContactService.CCS_CUSTOMER_CONTACT_ERROR_CD";
	public static final String CCS_CUST_HAS_NO_CONTACT_ERROR_MSG = "CustomerContactService.CCS_CUST_HAS_NO_CONTACT_ERROR_MSG";
	// Data source 
	public static final String JS_DATA_INPUT_JOB_STOP_ERROR_CD = "JobService.DATA_INPUT_JOB_STOP_ERROR_CD";
	public static final String JS_DATA_INPUT_JOB_START_ERROR_CD = "JobService.DATA_INPUT_JOB_START_ERROR_CD";
	public static final String ETLS_DATA_EXTRACTION_ERROR_CD = "ETLService.ETLS_DATA_EXTRACTION_ERROR_CD";
	public static final String ETLS_DATA_TRANSFORMATION_ERROR_CD = "ETLService.ETLS_DATA_TRANSFORMATION_ERROR_CD";
	public static final String ETLS_DATA_LOADING_ERROR_CD = "ETLService.ETLS_DATA_LOADING_ERROR_CD";
	public static final String DEEL_EVENT_PROCESSING_ERROR_CD = "DataExtractedEventListener.DEEL_EVENT_PROCESSING_ERROR_CD";
	public static final String DTEL_EVENT_PROCESSING_ERROR_CD = "DataTransformedEventListener.DTEL_EVENT_PROCESSING_ERROR_CD";
	public static final String DIS_LOAD_RUNNABLE_JOBS_ERROR_CD = "DataInputService.DIS_LOAD_RUNNABLE_JOBS_ERROR_CD";
	public static final String DIS_RESOLVE_RUNNABLE_JOB_ERROR_CD = "DataInputService.DIS_RESOLVE_RUNNABLE_JOB_ERROR_CD";
	public static final String BDE_DATA_EXTRACTOR_ERROR_CD = "BaseDataExtractor.BDE_DATA_EXTRACTOR_ERROR_CD";
	public static final String UNKNOWN_CELL_DATA_TYPE_ERROR_MSG = "BaseDataExtractor.BDE_DATA_EXTRACTOR_ERROR_CD";
	public static final String CSV_DATA_EXTRACTOR_ERROR_CD = "CSVDataExtractor.CSV_DATA_EXTRACTOR_ERROR_CD";
	public static final String EMAIL_DATA_EXTRACTOR_ERROR_CD = "EmailDataExtractor.EMAIL_DATA_EXTRACTOR_ERROR_CD";
	public static final String DP_FIELD_TRANSFORMATION_ERROR_CD = "DataProcessor.DP_FIELD_TRANSFORMATION_ERROR_CD";
	public static final String DP_DATA_TRANSFORMER_NOT_FOUND_ERROR_CD = "DataProcessor.DP_DATA_TRANSFORMER_NOT_FOUND_ERROR_CD";
	public static final String BOIS_LOAD_BO_ERROR_CD = "BusinessObjectInputService.BOIS_LOAD_BO_ERROR_CD";
	public static final String RELATED_ENTITY_NOT_FOUND_ERROR_CD = "AbstractDataTransformer.RELATED_ENTITY_NOT_FOUND_ERROR_MSG";
	public static final String RELATED_ENTITY_SEARCH_ERROR_CD = "AbstractDataTransformer.RELATED_ENTITY_SEARCH_ERROR_CD";
	public static final String MBT_UNSUPPORTED_INT_OPCODE_ERROR_MSG = "MapBasedTransformer.MBT_UNSUPPORTED_INT_OPCODE_ERROR_MSG";
	public static final String MBT_UNSUPPORTED_BIGDECIMAL_OPCODE_ERROR_MSG = "MapBasedTransformer.MBT_UNSUPPORTED_BIGDECIMAL_OPCODE_ERROR_MSG";
	public static final String MBT_UNSUPPORTED_DATE_OPCODE_ERROR_MSG = "MapBasedTransformer.MBT_UNSUPPORTED_DATE_OPCODE_ERROR_MSG";
	// Channels
	public static final String CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_CD = "ChannelConfigurationService.CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_CD";
	public static final String CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_MSG = "ChannelConfigurationService.CCS_CHANNEL_CONFIGURATION_CREATION_ERROR_MSG";
	public static final String CCS_PIPELINE_CONFIGURATION_CREATION_ERROR_MSG = "ChannelConfigurationService.CCS_PIPELINE_CONFIGURATION_CREATION_ERROR_MSG";
	public static final String CCS_HANDLER_CONFIGURATION_CREATION_ERROR_MSG = "ChannelConfigurationService.CCS_HANDLER_CONFIGURATION_CREATION_ERROR_MSG";
	public static final String QTMH_GET_HANDLER_ERROR_CD = "QTMHandler.QTMH_GET_HANDLER_ERROR_CD";
	public static final String CMS_START_CHANNEL_ERROR_CD = "ChannelManagerService.CMS_START_CHANNEL_ERROR_CD";
	public static final String QPF_PIPELINE_FACTORY_INITIALIZATION_ERROR_CD = "QTMPipelineFactory.QPF_PIPELINE_FACTORY_INITIALIZATION_ERROR_CD";
	public static final String CHF_CHANNEL_CREATION_ERROR_CD = "ChannelHandlerFactory.CHF_CHANNEL_CREATION_ERROR_CD";
	// Rules
	public static final String RULE_SPACE_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_SPACE_CONFIG_ERROR_CD";
	public static final String RULE_SPACE_NOT_FOUND__ERROR_MSG = "TransactionRuleConfigurationService.TRCS_RULE_SPACE_NOT_FOUND__ERROR_MSG";

	public static final String RULE_CONFIG_ERROR_CD = "TransactionRuleProcessingService.NO_PROCESSING_RULES_TABLE_FOUND_ERROR_MSG";
	public static final String NO_PROCESSING_RULES_TABLE_FOUND_ERROR_MSG = "TransactionRuleProcessingService.NO_PROCESSING_RULES_TABLE_FOUND_ERROR_MSG";
	public static final String NO_PROCESSING_RULES_CHAIN_FOUND_ERROR_MSG = "TransactionRuleProcessingService.NO_PROCESSING_RULES_CHAIN_FOUND_ERROR_MSG";
	public static final String TRRS_RULE_CONFIG_ERROR_CD = "TransactionRuleRoutingService.TRRS_RULE_CONFIG_ERROR_CD";
	public static final String TRRS_NO_ROUTING_RULES_CHAIN_FOUND_ERROR_MSG = "TransactionRuleRoutingService.TRRS_NO_PROCESSING_RULES_CHAIN_FOUND_ERROR_MSG";
	public static final String TRRS_TRANSACTION_RULE_VALIDATION_ERROR_CD = "TransactionRuleRoutingService.TRRS_TRANSACTION_RULE_VALIDATION_ERROR_CD";
	public static final String TRPS_TRANSACTION_RULE_PROCESSING_ERROR_CD = "TransactionRuleProcessingService.TRPS_TRANSACTION_RULE_PROCESSING_ERROR_CD";
	public static final String TRVS_RULE_CONFIG_ERROR_CD = "TransactionRuleValidationService.TRVS_RULE_CONFIG_ERROR_CD";
	public static final String TRVS_NO_VALIDATION_RULES_CHAIN_FOUND_ERROR_MSG = "TransactionRuleValidationService.TRVS_NO_VALIDATION_RULES_CHAIN_FOUND_ERROR_MSG";
	public static final String TRVS_TRANSACTION_RULE_VALIDATION_ERROR_CD = "TransactionRuleValidationService.TRVS_TRANSACTION_RULE_VALIDATION_ERROR_CD";
	public static final String TRMS2_RULE_MATCHING_ERROR_CD = "TransactionRuleMatchingService.TRMS2_RULE_MATCHING_ERROR_CD";
	public static final String TRMS_RULE_ENGINE_START_ERROR_CD = "TransactionRuleManagementService.TRMS_RULE_ENGINE_START_ERROR_CD";
	public static final String TRMS_RULE_ENGINE_ERROR_CD = "TransactionRuleManagementService.TRMS_RULE_ENGINE_ERROR_CD";
	public static final String TRMS_RULE_TABLE_NOT_FOUND_MSG = "TransactionRuleManagementService.TRMS_RULE_TABLE_NOT_FOUND_MSG";
	public static final String TRMS_RULE_SPACE_NOT_FOUND_MSG = "TransactionRuleManagementService.TRMS_RULE_SPACE_NOT_FOUND_MSG";
	public static final String TRMS_RULE_CHAIN_NOT_FOUND_MSG = "TransactionRuleManagementService.TRMS_RULE_CHAIN_NOT_FOUND_MSG";
	public static final String TRMS_RULE_NOT_FOUND_MSG = "TransactionRuleManagementService.TRMS_RULE_NOT_FOUND_MSG";
	public static final String RCOS_OPERATION_ERROR_CD = "RuleConditionOperatorService.PCS_PARAMETER_COMPARISON_ERROR_CD";
	public static final String RCOS_INVALID_PARAMETER_TY_ERROR_MSG = "RuleConditionOperatorService.PCS_INVALID_PARAMETER_TY_ERROR_MSG";
	public static final String TRCES_CONDITION_EVALUATION_ERROR_CD = "TransactionRuleConditionEvaluationService.TRCES_CONDITION_EVALUATION_ERROR_CD";
	public static final String TRCES_PARAM_TY_DONT_MATCH_ERROR_MSG = "TransactionRuleConditionEvaluationService.TRCES_PARAM_TY_DONT_MATCH_ERROR_MSG";
	public static final String TRCES_INVALID_OPERATOR_ERROR_MSG = "TransactionRuleConditionEvaluationService.TRCES_INVALID_OPERATOR_ERROR_MSG";
	public static final String OPS_OPERATIONS_ERROR_CD = "OperatorService.OPS_OPERATIONS_ERROR_CD";
	public static final String OPS_INVALID_PARAM_TY_ERROR_MSG = "OperatorService.OPS_INVALID_PARAM_TY_ERROR_MSG";
	public static final String OPS_INVALID_OPERATOR_TY_ERROR_MSG = "OperatorService.OPS_INVALID_OPERATOR_TY_ERROR_MSG";
	public static final String IU_INTEGER_CONVERSION_ERROR = "IntegerUtil.IU_INTEGER_CONVERSION_ERROR";
	public static final String IU_INVALID_SOURCE_DATA_TY_ERROR_MSG = "IntegerUtil.IU_INVALID_SOURCE_DATA_TY_ERROR_MSG";
	public static final String BDU_INVALID_SOURCE_DATA_TY_ERROR_MSG = "BigDecimalUtil.BDU_INVALID_SOURCE_DATA_TY_ERROR_MSG";
	public static final String BDU_BIG_DECIMAL_CONVERSION_ERROR = "BigDecimalUtil.BDU_BIG_DECIMAL_CONVERSION_ERROR";
	public static final String DU_DATE_CONVERSION_ERROR = "DateUtil.DU_DATE_CONVERSION_ERROR";
	public static final String DU_INVALID_SOURCE_DATA_TY_ERROR_MSG = "DateUtil.DU_INVALID_SOURCE_DATA_TY_ERROR_MSG";
	public static final String TAVS_ATTR_RESOLUTION_ERROR_CD = "TAVS_ATTR_RESOLUTION_ERROR_CD";
	public static final String TAVS_ATTR_NOT_FOUND_ERROR_MSG = "TransactionAttributeValueService.TAVS_ATTR_NOT_FOUND_ERROR_MSG";
	public static final String TRCS_NO_RULE_CATEGORIES_SPECIFIED_ERROR_MSG = "TransactionRuleConfigurationService.TRCS_NO_RULE_CATEGORIES_SPECIFIED_ERROR_MSG";
	public static final String TRCS_RULE_CATEGORIES_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CATEGORIES_CONFIG_ERROR_MSG";
	public static final String TRCS_NO_RULE_TYPES_SPECIFIED_ERROR_MSG = "TransactionRuleConfigurationService.TRCS_NO_RULE_TYPES_SPECIFIED_ERROR_MSG";
	public static final String TRCS_RULE_TYP_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_NO_RULE_TYPES_SPECIFIED_ERROR_MSG";
	public static final String TRCS_NO_RULES_SPECIFIED_ERROR_MSG = "TransactionRuleConfigurationService.TRCS_NO_RULES_SPECIFIED_ERROR_MSG";
	public static final String TRCS_RULE_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CONFIG_ERROR_MSG";
	public static final String TRCS_RULE_ACTION_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_ACTION_CONFIG_ERROR_MSG";
	public static final String TRCS_RULE_CONDITION_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CONDITION_CONFIG_ERROR_MSG";
	public static final String TRCS_NO_RULE_CONDITIONS_SPECIFIED_ERROR_MSG = "TransactionRuleConfigurationService.TRCS_NO_RULE_CONDITIONS_SPECIFIED_ERROR_MSG";
	public static final String TRCS_RULE_CONDITION_APPLICABLE_OPERATOR_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CONDITION_APPLICABLE_OPERATOR_CONFIG_ERROR_CD";
	public static final String TRCS_RULE_CONDITION_PARAMETER_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CONDITION_ATTRIBUTE_CONFIG_ERROR_CD";
	public static final String TRCS_RULE_CONDITION_ATTRIBUTE_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CONDITION_ATTRIBUTE_CONFIG_ERROR_CD";
	public static final String TRCS_RULE_CONDITION_OPERATOR_CONFIG_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_CONDITION_OPERATOR_CONFIG_ERROR_CD";
	public static final String TRCS_RULE_NO_OPERATOR_SPECIFIED_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_NO_OPERATOR_SPECIFIED_ERROR_CD";
	public static final String TRCS_RULE_NO_PARAMETER_TY_SPECIFIED_ERROR_CD = "TransactionRuleConfigurationService.TRCS_RULE_NO_PARAMETER_TY_SPECIFIED_ERROR_CD";
	public static final String HTDH_PARSE_DATA_ERROR_CD = "HttpTransactionDataHandler.HTDH_PARSE_DATA_ERROR_CD";
	public static final String HTDH_INVALID_KEY_VALUE_PAIR_ERROR_MSG = "HttpTransactionDataHandler.HTDH_INVALID_KEY_VALUE_PAIR_ERROR_MSG";
	public static final String HTDH_INVALID_POST_DATA_ERROR_MSG = "HttpTransactionDataHandler.HTDH_INVALID_POST_DATA_ERROR_MSG";
	// Business Object
	public static final String BOCS_BUSINESS_OBJECT_CREATION_ERROR_CD = "BusinessObjectCreationService.BOCs_BUSINESS_OBJECT_CREATION_ERROR_CD";
	public static final String BORU_SEARCH_FIELD_ERROR_CD = "BusinessObjectRESTService.BORU_SEARCH_FIELD_ERROR_CD";
	public static final String BORU_SEARCH_FIELD_ERROR_MSG = "BusinessObjectRESTService.BORU_SEARCH_FIELD_ERROR_MSG";
	public static final String BOSS_BUSINESS_OBJ_QUERY_ERROR_CD = "BusinessObjectSearchService.BOSS_BUSINESS_OBJ_QUERY_ERROR_CD";
	public static final String BOU_DATA_COPY_TO_BO_ERROR_CD = "BusinessObjectUtil.BOU_DATA_COPY_TO_BO_ERROR_CD";

	
	

}
