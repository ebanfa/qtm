/* -------------------------------------------------------------------- MODULES -------------------------------------------------------------------------------------------- */

INSERT INTO APPLICATION_MODULE values (1, 'BASE', 'Base', 'Base', 2, 'Base', 'application_double.png', 'N','2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (2, 'PARTY', 'Party', 'Party', 3, 'Party', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (3, 'CUSTOMER', 'Customer', 'Customer', 1, 'Customer', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (4, 'BUSINESSDATA', 'Business Data', 'Business Data', 1, 'Services', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (5, 'APPLICATION', 'Application', 'Application', 4, 'Application', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (6, 'CHANNEL', 'Channel', 'Channel', 4, 'Channel', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (7, 'DATASOURCE', 'Datasource', 'Datasource', 1, 'Datasource', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (8, 'MESSAGING', 'Messaging', 'Messaging', 4, 'Messaging', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (9, 'ADVICE', 'Advice', 'Advice', 1, 'Advice', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (10, 'PAYMENT', 'Payment', 'Payment', 1, 'Payment', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (11, 'PRODUCT', 'Product', 'Product', 4, 'Product', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (12, 'WORKEFFORT', 'WorkEffort', 'WorkEffort', 1, 'WorkEffort', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (13, 'ORDER', 'Order', 'Order', 4, 'Order', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
 
/* -------------------------------------------------------------------- Activity Group Types -------------------------------------------------------------------------------------------- */

INSERT INTO APPLICATION_ACTIVITY_GROUP_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ENTITY_GROUP', 'Entity Group', 'Entity Group', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ACTIVITY_GROUP_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESS_GROUP', 'Process Group', 'Process Group', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Activity Types -------------------------------------------------------------------------------------------- */

INSERT INTO APPLICATION_ACTIVITY_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('LIST_ACTIVITY', 'List Activity', 'List Activity', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ACTIVITY_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EDIT_ACTIVITY', 'Edit Activity', 'Edit Activity', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Entity Field Types -------------------------------------------------------------------------------------------- */

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ID', 'Id', 'Id', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CODE', 'Code', 'Code', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NAME', 'Name', 'Name', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TEXT', 'Text', 'Text', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('LARGE_TEXT', 'Large Text', 'Large Text', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NUMBER', 'Number', 'Number', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DECIMAL', 'Decimal', 'Decimal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STATUS', 'Status', 'Status', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('BOOLEAN', 'Boolean', 'Boolean', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CURRENCY', 'Currency', 'Currency', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHAR', 'Char', 'Char', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATE', 'Date', 'Date', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATETIME', 'Date Time', 'Date Time', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO APPLICATION_ENTITY_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RELATIONSHIP', 'Relationship', 'Relationship', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------CURRENCY-------------------------------------------------------------------------------------------- */

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('KES', 'Kenyan Shilling', 'KES', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('USD', 'United States Dollar', '$', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('EURO', 'Euro', 'Euro', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('GBP', 'Pound Sterling', 'GBP', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Country -------------------------------------------------------------------------------------------- */

INSERT INTO COUNTRY(CODE, NAME, CNTRY_CD_ISO2, CNTRY_CD_ISO3, LOCAL_CRNCY_ID, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('KENYA', 'Kenya', 'KY', 'KYN', 'Kenya', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Business Units -------------------------------------------------------------------------------------------- */

INSERT INTO BUSINESS_UNIT(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('HEAD_OFFICE', 'Head Office', 'Head Office', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Types -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_TRANSACTION_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ATM_WITHDRAWAL', 'ATM Withdrawal', 'ATM Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CC_TXN', 'Credit Card Transaction', 'Credit Card Transaction', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHQ_WITHDRAWAL', 'Cheque Withdrawal', 'Cheque Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FX_WITHDRAWAL', 'FX Withdrawal', 'FX Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FUNDS_TRANSFER', 'Funds Transfers', 'Funds Transfers', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Service Status-------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_TRANSACTION_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MATCHED', 'Matched', 'Matched', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NOT_MATCHED', 'Not Matched', 'Did not matched', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MATCH_PENDING', 'Match pending', 'Has not passed through the matching process', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Messages Types-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SMS', 'SMS', 'SMS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL', 'Email', 'Email', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Messages Status-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RECEIVED', 'Recieved', 'Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NOT_REGISTERED', 'Not Registered', 'Not Registered', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('REGISTRED', 'Registered', 'Registered', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUST_HAS_NO_ACCOUNTS', 'Customer has not accounts', 'Customer has not accounts', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUST_ACCOUNT_NOT_SPECIFIED', 'Customer account not specified', 'Customer account not specified', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUST_ADVICETAG_NOT_SPECIFIED', 'Advice tag not specified', 'Advice tag not specified', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Messages Classification-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INBOUND', 'Inbound', 'Inbound', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE (MSG_TY_ID, MSG_CLASS_ID, MSG_STATUS_ID, MESSAGE_FROM, MESSAGE_TO, MESSAGE_SUBJECT, MESSAGE_TXT, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'customer@testserver.com', 'ek@ecobank.com', 'Advice', 'Advice', 'RECEIVED', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Advice Channel Category -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_CHANNEL_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('HTTP', 'HTTP User Interface', 'HTTP User Interface', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CHANNEL_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('USSD', 'USSD', 'USSD', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CHANNEL_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('IVR', 'IVR', 'IVR', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Advice Channel Type -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_CHANNEL_TYPE(ADVICE_CHL_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'USSD', 'HTTP', 'HTTP', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CHANNEL_TYPE(ADVICE_CHL_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'IVR', 'SMPP', 'SMPP', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CHANNEL_TYPE(ADVICE_CHL_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'HTTP_ADMIN_CONSOLE', 'HTTP Admin User Interface', 'HTTP Admin User Interface', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CHANNEL_TYPE(ADVICE_CHL_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 'HTTP_USER_CONSOLE', 'HTTP User Interface', 'HTTP User Interface', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CHANNEL_TYPE(ADVICE_CHL_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 'HTTP_MOBILE_USER_CONSOLE', 'HTTP Mobile User Interface', 'HTTP Mobile User Interface', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Classification -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_CLASSIFICATION(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NON_CYCLIC', 'Non Cyclic', 'Non Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CLASSIFICATION(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CYCLIC', 'Cyclic', 'Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Types -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ATM_WITHDRAWAL_ADVICE', 'ATM Withdrawal Notice', 'ATM Withdrawal Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'CC_TXN_ADVICE', 'Credit Card Transaction Notice', 'Credit Card Transaction Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'CHQ_WITHDRAWAL', 'Cheque Withdrawal Notice', 'Cheque Withdrawal Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 'FX_WITHDRAWAL', 'FX Withdrawal Notice', 'FX Withdrawal Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 'FUNDS_TRANSFER', 'Funds Transfer Notice', 'Funds Transfer Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Status -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSED', 'Processed', 'Processed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSING_CYCLES', 'Partially processed', 'Partially processed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('UNPROCESSED', 'Unprocessed', 'Unprocessed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE(ADVICE_TY_ID, ADVICE_CLASS_ID, ADVICE_ST_ID, CUSTOMER_ID, CURRENCY_ID, MESSAGE_ID, CODE, NAME, AMOUNT, DESCRIPTION, MAX_MATCHES, MATCH_COUNT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 3, 1, 1, 1, 'TEST', 'Test Cheque Advice', 1000.00, 'Test Cheque Advice', -1, 0, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Types -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INDIVIDUAL', 'Individual', 'Individual Customer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ORGANIZATION', 'Corporate', 'Corporate Customer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Classification -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_CLASSIFICATION(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('GOLD', 'Gold', 'Gold', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer-------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER(CUST_TY_ID, CUST_CLASS_ID, CODE, NAME, EMAIL, MOBILE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('2', '1', '0183737262', 'Bristol Text', 'bristole@testserver.com', '+2549056543333', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Account -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_ACCOUNT(CUST_ID, CODE, NAME, ACCOUNT_NO, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, '0082820109', 'Bristol Text', 'Bristol Text Account', '0082820109', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Service Category -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NETWORK_SERVICE', 'Network Service', 'Network Service', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATA_IMPORT_SERVICE', 'Data import service', 'Data import service', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Service Type -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ATM', 'ATM', 'ATM Channel', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TXN_DATA_IMPORT', 'Transaction data import service', 'Transaction data import service', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Service Modes -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_MODE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROXY', 'Proxy', 'Proxy', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_MODE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('BRIDGE', 'Bridge', 'Bridge', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_MODE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MASTER', 'Master', 'Master', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Protocol Adapters -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_PROTOCOL_ADAPTER(CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('HTTPISO8583', 'HTTP ISO 8583 Adapter', 'HTTP ISO 8583 Adapter', 'com.nathanclaire.alantra.channel.server.HTTPISO8583PipelineBuilder', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_PROTOCOL_ADAPTER(CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TXNOBJECT_SERIALIZER', 'Transaction Object Serializer', 'Transaction Object Serializer', 'com.nathanclaire.alantra.channel.server.TransactionAuthorizationPipelineBuilder', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_PROTOCOL_ADAPTER(CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TXN_DATAIMPORT_ADAPTER', 'Transaction Data Import Adapter', 'Transaction Data Import Adapter', 'com.nathanclaire.alantra.datasource.service.process.CTSFileReaderScheduledService', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Service-------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE(SERVICE_TY_ID, MODE_ID, SERVICE_CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, PORT_NO, IP_ADDRESS, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 1, 'ISO8583_HTTP', 'ISO 8583 Over HTTP', 'ISO 8583 Over HTTP', '19001', '127.0.0.1', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE(SERVICE_TY_ID, MODE_ID, SERVICE_CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, PORT_NO, IP_ADDRESS, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 3, 2, 3, 'TXN_DATA_IMPORT_SERVICE', 'Transaction data import service', 'Transaction data import service', '19001', '127.0.0.1', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_SOURCE_CATEGORY-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_SOURCE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FILE', 'File', 'File', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL', 'Email', 'Email', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RDBMS', 'Relational Database Management Systems', 'Relational Database Management Systems', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_SOURCE_TYPE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_SOURCE_TYPE (DS_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'CSV', 'Comma Separated Values', 'Comma Separated Values', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_TYPE (DS_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'POP', 'POP Mail Server', 'POP Mail Server', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_TYPE (DS_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'MYSQL_RDBMS_DS', 'MySQL Datasource', 'MySQL Datasource', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_TYPE (DS_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'POSTGRESQL_RDBMS_DS', 'PostgreSQL Datasource', 'PostgreSQL Datasource', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_SOURCE_STRUCTURE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_SOURCE_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ACCOUNT_TXN_STRUCTURE', 'Account Transaction Structure', 'Account Transaction Structure', 'EXTERNALTRANSACTION', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_INFO_STRUCTURE', 'Customer Information Structure', 'Customer Information Structure', 'EXTERNALSERVICEUSER', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHEQUE_TXN_DATA_STRUCTURE', 'Cheque Transaction Structure', 'Cheque Transaction Structure', 'EXTERNALCHQTRANSACTION', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_SOURCE_FIELD_TYPE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_SOURCE_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STRING', 'String', 'String', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INTEGER', 'Integer', 'Integer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DECIMAL', 'Decimal', 'Decimal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATE', 'Date', 'Date', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_SOURCE_FIELD-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_SOURCE_FIELD (DS_STRUCT_ID, DS_FIELD_TY_ID, CODE, NAME, DESCRIPTION, DS_FIELD_TARGET, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'CURRENCY', 'crncy', 'Currency', 'EXTERNALCHQTRANSACTION_CRNCY', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD (DS_STRUCT_ID, DS_FIELD_TY_ID, CODE, NAME, DESCRIPTION, DS_FIELD_TARGET, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'ACCOUNT_NO', 'acct_no', 'Account Number', 'EXTERNALCHQTRANSACTION_ACCT_NO', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD (DS_STRUCT_ID, DS_FIELD_TY_ID, CODE, NAME, DESCRIPTION, DS_FIELD_TARGET, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 3, 'AMOUNT', 'amount', 'Transaction Amount', 'EXTERNALCHQTRANSACTION_AMOUNT', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD (DS_STRUCT_ID, DS_FIELD_TY_ID, CODE, NAME, DESCRIPTION, DS_FIELD_TARGET, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 4, 'TXN_DATE', 'txn_dt', 'Transaction Date', 'EXTERNALCHQTRANSACTION_TXN_DT', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_SOURCE_FIELD (DS_STRUCT_ID, DS_FIELD_TY_ID, CODE, NAME, DESCRIPTION, DS_FIELD_TARGET, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'CHQ_NO', 'Cheque Number', 'Cheque Number', 'EXTERNALCHQTRANSACTION_CHQ_NO', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_SOURCE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_SOURCE (DS_TY_ID, DS_STRUCT_ID, CODE, NAME, DESCRIPTION, DS_URL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,3, 'CHQ_TXN_DATASOURCE', 'Cheque Transactions Data Source', 'Cheque Transactions Data Source', '/home/administrator/Projects/alantra/cheque_txn.csv', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_JOB_CATEGORY-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_INPUT_JOB_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION_DATA', 'com.nathanclaire.alantra.datasource.etl.TransactionDataProcessor', 'Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_DATA', 'Transaction data', 'Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_JOB_TYPE-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CSV_FILE', 'com.nathanclaire.alantra.datasource.etl.CSVDataLoader', 'CSV import job', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EXCEL_FILE', 'Excel (xls) import job', 'Excel (xls) import job', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATABASE', 'Database import job', 'Database import job', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Data input job status-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('COMPLETED', 'Completed', 'Completed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RUNNING', 'Runnig', 'Running', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STOPPED', 'Stopped', 'Stopped', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FAILED', 'Failed', 'Failed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_JOB-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_INPUT_JOB (DS_ID, JOB_CAT_ID, JOB_TY_ID, JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,1,1, 2, 'CTS_INPUT_JOB', 'CTS Input Job', 'CTS Input Job', 1, 'MINUTE', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

