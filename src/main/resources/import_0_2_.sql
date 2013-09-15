/* -------------------------------------------------------------------- System Group -------------------------------------------------------------------------------------------- */

INSERT INTO SYSTEM_GROUP(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST, VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) VALUES ('ADMIN', 'Administrator', 'Administrator', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- System User -------------------------------------------------------------------------------------------- */

INSERT INTO SYSTEM_USER (GROUP_ID, CODE, NAME, USERNAME, PASSWORD, EMAIL, MOBILE, LOCKED_FG, MULTI_LOGIN_FG, EFFECTIVE_DT, REC_ST, VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) VALUES (1, 'ADMIN', 'Administrator',  'admin', 'D1MfrL5wmeCRhzTzMLwbT3xNQ9O+E1n0', 'ebanfa@gmail.com', '', 'N', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- MODULES -------------------------------------------------------------------------------------------- */

INSERT INTO APPLICATION_MODULE values (1, 'BASE', 'Base', 'Base', 2, 'Base', 'application_double.png', 'N','2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (2, 'PARTY', 'Party', 'Party', 3, 'Party', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (3, 'CUSTOMER', 'Customer', 'Customer', 1, 'Customer', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (4, 'BUSINESSDATA', 'Business Data', 'Business Data', 1, 'Services', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (5, 'APPLICATION', 'Application', 'Application', 4, 'Application', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (6, 'TRANSACTION', 'Transaction', 'Transaction', 4, 'Transaction', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (7, 'DATASOURCE', 'Datasource', 'Datasource', 1, 'Datasource', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (8, 'MESSAGING', 'Messaging', 'Messaging', 4, 'Messaging', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (9, 'ADVICE', 'Advice', 'Advice', 1, 'Advice', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (10, 'PAYMENT', 'Payment', 'Payment', 1, 'Payment', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (11, 'PRODUCT', 'Product', 'Product', 4, 'Product', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (12, 'NOTIFICATION', 'Notification', 'Notification', 1, 'Notification', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (13, 'ORDER', 'Order', 'Order', 4, 'Order', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );
INSERT INTO APPLICATION_MODULE values (14, 'SECURITY', 'Security', 'Security', 4, 'Security', 'application_double.png', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

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
 ) values ('KSH', 'Kenyan Shilling', 'KES', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('USD', 'United States Dollar', '$', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('EURO', 'Euro', 'Euro', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('GBP', 'Pound Sterling', 'GBP', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Country -------------------------------------------------------------------------------------------- */

INSERT INTO COUNTRY(CODE, NAME, CNTRY_CD_ISO2, CNTRY_CD_ISO3, LOCAL_CRNCY_ID, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('KENYA', 'Kenya', 'KY', 'KYN', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Business Units -------------------------------------------------------------------------------------------- */

INSERT INTO BUSINESS_UNIT(COUNTRY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,'HEAD_OFFICE', 'Head Office', 'Head Office', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Category -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PERSONAL', 'Personal', 'Personal Customer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NON_PERSONAL', 'Non Personal', 'Non Personal Customer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Types -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_TYPE(CUST_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'STUDENT', 'Student', 'Student', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_TYPE(CUST_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'SELF_EMPLOYED', 'Self Employed', 'Self Employed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_TYPE(CUST_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'PROFESSIONAL', 'Professional', 'Professional', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_TYPE(CUST_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'CORPORATION', 'Corporation', 'Corporation', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_TYPE(CUST_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'GOVERNMENT_AGENCY', 'Government Agency', 'Government Agency', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Classification -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_CLASSIFICATION(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('GOLD', 'Gold', 'Gold', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer-------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER(CUST_TY_ID, CUST_CLASS_ID, CODE, NAME, PRIMARY_EMAIL, PRIMARY_MOBILE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('2', '1', '0183737262', 'Bristol Text', 'ebanfa@gmail.com', '+2549056543333', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER(CUST_TY_ID, CUST_CLASS_ID, CODE, NAME, PRIMARY_EMAIL, PRIMARY_MOBILE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('2', '1', '0183737345', 'Adrian Haldermann', 'haldermanne@testserver.com', '+2549056543444r', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Account Types -------------------------------------------------------------------------------------------- */

INSERT INTO ACCOUNT_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHEQUE_CURRENT_ACCOUNT', 'Cheque current account', 'Cheque current account', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACCOUNT_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SAVINGS_ACCOUNT', 'Savings account', 'Savings account', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACCOUNT_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STAFF_CURRENT_ACCOUNT', 'Staff current account', 'Staff current account', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACCOUNT_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('OD_CURRENT_ACCOUNT', 'Current account with overdraft', 'Current account with overdraft', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ACCOUNT_TYPE(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FX_CURRENCY_ACCOUNT', 'Foreign currency account', 'Foreign currency account', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Account -------------------------------------------------------------------------------------------- */

INSERT INTO ACCOUNT(CURRENCY_ID, ACCOUNT_TY_ID, CODE, NAME, ACCOUNT_NO, DESCRIPTION, IS_JOINT_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, '0082820109', 'Bristol Text',  '0082820109', 'Bristol Text', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Account -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_ACCOUNT(CUST_ID, ACCOUNT_ID, CODE, NAME, DESCRIPTION, IS_DEFAULT_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, '0082820109', 'Bristol Text', 'Bristol Text Account', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Category -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_TRANSACTION_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CASH_DEP', 'Cash Deposit', 'Cash Deposit', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CASH_WITHDRAWAL', 'Cash Withdrawal', 'Cash Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHEQUE_WITHDRAWAL', 'Cheque Withdrawal', 'Cheque Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_CATEGORY(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FUNDS_TRANSFER', 'Funds Transfers', 'Funds Transfers', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Types -------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_TRANSACTION_TYPE(TRANSACTION_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'ATM_CASH_WITHDRAWAL', 'ATM Withdrawal', 'ATM Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(TRANSACTION_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'CASH_WITHDRAWAL', 'Cash Withdrawal', 'Cash Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(TRANSACTION_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'CHQ_WITHDRAWAL', 'Cheque Withdrawal', 'Cheque Withdrawal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_TYPE(TRANSACTION_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 'FUNDS_TRANSFER', 'Funds Transfers', 'Funds Transfers', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Service Status-------------------------------------------------------------------------------------------- */

INSERT INTO SERVICE_TRANSACTION_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MATCHED', 'Matched', 'Matched', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NOT_MATCHED', 'Not Matched', 'Did not matched', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO SERVICE_TRANSACTION_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MATCH_PENDING', 'Match pending', 'Has not passed through the matching process', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Template Classification-------------------------------------------------------------------------------------------- */

INSERT INTO TEMPLATE_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SUCCESS_TEMPLATE', 'Success Template', 'Success Template', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ERROR_TEMPLATE', 'Error Template', 'Error Template', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Template Category-------------------------------------------------------------------------------------------- */

INSERT INTO TEMPLATE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_TEMPLATE', 'Customer Template', 'Customer Template', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('USER_TEMPLATE', 'User Template', 'User Template', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Template Type-------------------------------------------------------------------------------------------- */

INSERT INTO TEMPLATE_TYPE (TEMPLATE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ADVICE_NOTIFICATION_TEMPLATES', 'Advice Notification Templates', 'Advice Notification Templates', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_TYPE (TEMPLATE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'TRANSACTION_NOTIFICATION_TEMPLATES', 'Transaction Notification Templates', 'Transaction Notification Templates', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_TYPE (TEMPLATE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'MSG_NOTIFICATION_TEMPLATES', 'Message Notification Templates', 'Message Notification Templates', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Template TypeTAG -------------------------------------------------------------------------------------------- */

INSERT INTO TEMPLATE_TYPE_TAG (TEMPLATE_TY_ID, CODE, NAME, DESCRIPTION, TAG_VAL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'STATUS_INFORMATION', 'Status Information', 'Status Information', 'STATUS_INFORMATION', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_TYPE_TAG (TEMPLATE_TY_ID, CODE, NAME, DESCRIPTION, TAG_VAL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'CUSTOMER', 'Customer', 'Customer', 'CUSTOMER', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_TYPE_TAG (TEMPLATE_TY_ID, CODE, NAME, DESCRIPTION, TAG_VAL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'MESSAGE_SOURCE_ADDR', 'Source Address', 'Source Address', 'MESSAGE_SOURCE_ADDR', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_TYPE_TAG (TEMPLATE_TY_ID, CODE, NAME, DESCRIPTION, TAG_VAL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'USER_NAME', 'User Name', 'Source Name', 'USER_NAME', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE_TYPE_TAG (TEMPLATE_TY_ID, CODE, NAME, DESCRIPTION, TAG_VAL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'MESSAGE_CATEGORY', 'Message Category', 'Message Category', 'MESSAGE_CATEGORY', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Templates-------------------------------------------------------------------------------------------- */

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_REQUEST_TEXT_RESPONSE_TEMPLATE', 'Advice Request Text Response Template', 'Advice Request Text Response Notification Template', 'Advice Response Notification', 'Your request has been received and is being processed.\n A response will be sent to you shortly.\n', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_REQUEST_TEXT_ERROR_TEMPLATE', 'Advice Request Text Error Template', 'Advice Request Text Error Notification Template', 'Advice Error Notification', 'Hi CUSTOMR_NAME.\nThere was an error processing your advice request.\nKindly note that the format for valid advice requests is:\n Advice pay cheque 101910111.\nRegards,\nMobile Advicer.\n', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_REQUEST_RESPONSE_TEMPLATE', 'Advice Request Response Template', 'Advice Request Response Notification Template', 'Advice Response Notification', 'Your advice request has been received and is being processed.\n A response will be sent to you shortly.\n', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_REQUEST_ERROR_TEMPLATE', 'Advice Request Error Template', 'Advice Request Error Notification Template', 'Advice Request Eror Notification', 'There was an error processing your advice inquiry request', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_INQUIRY_RESPONSE_TEMPLATE', 'Advice Inquiry Response Template', 'Advice Inquiry Response Notification Template', 'Advice Inquiry Response Notification', 'The status of advice: {} is {}.\n ', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_INQUIRY_ERROR_TEMPLATE', 'Advice Inquiry Error Template', 'Advice Inquiry Error Notification Template', 'Advice Inquiry Error Notification', 'There was an error processing your advice inquiry request.\n A response will be sent to you shortly.\n', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 'TXN_DATA_INPUT_REQUEST_RESPONSE_TEMPLATE', 'Transaction Data Response Notification Template', 'Transaction Data Response Notification Template', 'Transaction Data Input Notification', 'Transaction input request received and is currently being processed.\n A response will be generated shortly.', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 'TXN_DATA_INPUT_REQUEST_ERROR_TEMPLATE', 'Transaction Data Error Notification Template', 'Transaction Data Error Notification Template', 'Transaction Data Input Error Notification', 'There was an error processing your transaction upload request.\n A response will be generated shortly.', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'UNCLASSIFIED_INBOUND_MSG_TEMPLATE', 'Unclassified Inbound Message Received Notification Template', 'Unclassified Inbound Message Received Notification Template', 'Unclassified Message Received Notification', 'Hi USER_NAME, This is to notify on the arrival of an unclassified (MESSAGE_CATEGORY) message received from source address MESSAGE_SOURCE_ADDR.', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'UNREGISTERED_CUSTOMER_MSG_TEMPLATE', 'Unregistered Customer Inbound Message Received Notification Template', 'Unregistered Customer Inbound Message Received Notification Template', 'Unregistered Customer Message Received Notification', 'Hi USER_NAME, This is to notify on the arrival of an unregistered customer (MESSAGE_CATEGORY) message received from source address MESSAGE_SOURCE_ADDR.', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TEMPLATE (TEMPLATE_TY_ID, TEMPLATE_CLASS_ID, CODE, NAME, DESCRIPTION, SUBJECT_TXT, MESSAGE_TXT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'UNREGISTERED_USER_MSG_TEMPLATE', 'Unregistered User Inbound Message Received Notification Template', 'Unregistered User Inbound Message Received Notification Template', 'Unregistered User Message Received Notification', 'Hi USER_NAME, This is to notify on the arrival of an unregistered user (MESSAGE_CATEGORY) message received from source address MESSAGE_SOURCE_ADDR.', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Notification Category-------------------------------------------------------------------------------------------- */

INSERT INTO NOTIFICATION_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_TRANSACTION_NOTIFICATIONS', 'Customer Notifications', 'Customer Notifications', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('	USER_NOTIFICATIONS', 'User Notifications', 'User Notifications', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Notification Type-------------------------------------------------------------------------------------------- */
	
INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'ADVICE_REQUEST_TEXT_RESPONSE', 'Advice Request Text Response', 'Advice Request Text Response', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 2, 'ADVICE_REQUEST_TEXT_ERROR_RESPONSE', 'Advice Request Text Error', 'Advice Request Text Error', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 3, 'ADVICE_REQUEST_RESPONSE', 'Advice Request Response', 'Advice Resquest Response', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 4, 'ADVICE_REQUEST_ERROR_RESPONSE', 'Advice Request Error', 'Advice Resquest Error', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 5, 'ADVICE_INQUIRY_RESPONSE', 'Advice Inquiry Response', 'Advice Inquiry Response', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 6, 'ADVICE_INQUIRY_ERROR_RESPONSE', 'Advice Inquiry Error', 'Advice Inquiry Error', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 7, 'TXN_DATA_INPUT_REQUEST_RESPONSE', 'Transaction Data Response Notification', 'Transaction Data Response Notification', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 8, 'TXN_DATA_INPUT_REQUEST_ERROR_RESPONSE', 'Transaction Data Error Notification', 'Transaction Data Error Notification', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 9, 'UNCLASSIFIED_CUSTOMER_MESSAGE_RECEIVED', 'Unclassified Message Received', 'Unclassified Message Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 10, 'UNREGISTERED_CUSTOMER_MESSAGE_RECEIVED', 'Unregistered Customer Message Received', 'Unregistered Customer Message Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO NOTIFICATION_TYPE (NOTIFICATION_CAT_ID, TEMPLATE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 11, 'UNREGISTERED_USER_MESSAGE_RECEIVED', 'Unclassified User Message Received', 'Unclassified User Message Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Category-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INBOUND', 'Inbound Messages', 'Inbound Messages', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('OUTBOUND', 'Outbound Messages', 'Outbound Messages', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Classification-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL', 'Email', 'Email', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SMS', 'SMS', 'SMS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Type-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_TYPE (MSG_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'SMTP_POP3', 'SMTP POP3 Email Messages', 'SMTP POP3 email messages', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_TYPE (MSG_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'SMS_HTTP', 'SMS HTTP Messages', 'SMS HTTP messages', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Messages Status-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_MESSAGE_RECEIVED', 'Customer Message Recieved', 'Customer Message Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SYSTEM_USER_MESSAGE_RECEIVED', 'System User Message Received', 'System User Message Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SYSTEM_USER_NOT_REGISTERED', 'System User Not Registered', 'System User Not Registered', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_NOT_REGISTERED', 'Customer Not Registered', 'Customer not Registered', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_HAS_NO_ACCOUNTS', 'Customer Has No Accounts', 'Customer has no accounts', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_ACCOUNT_NOT_SPECIFIED', 'Customer Account Not Specified', 'Customer account not specified', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_MSGTAG_NOT_SPECIFIED', 'Message Tag Not Specified', 'Message tag not specified', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('UNCLASSIFIED_MESSAGE_RECEIVED', 'Unknown Message Recieved', 'Unknown Message Received', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MESSAGE_NOT_SENT', 'Message Not Sent', 'Message not sent', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MESSAGE_SENT', 'Message Sent', 'Message sent', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Gateway Category-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL_CHANNEL', 'Email Gateways', 'Email Gateways', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SMS_CHANNEL', 'SMS Gateways', 'SMS Gateways', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_CATEGORY (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('FILE_CHANNEL', 'File Gateways', 'File Gateways', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Gateway Adapters-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL_ADAPTER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SMTP_POP_ADAPTER', 'Email SMTP & POP Adapter', 'Email SMTP & POP Adapter', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_ADAPTER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SMS_SMPP_ADAPTER', 'SMS SMPP Adapter', 'SMS SMPP Adapter', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_ADAPTER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SMS_HTTP_ADAPTER', 'SMS HTTP Adapter', 'SMS HTTP Adapter', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_ADAPTER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CSV_FILE_ADAPTER', 'CSV File Adapter', 'CSV File Adapter', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_ADAPTER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EXCEL_FILE_ADAPTER', 'Excel File Adapter', 'Excel File Adapter', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Gateway Types-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL_TYPE (CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'SMTP_POP3', 'Email SMTP & POP Gateway', 'Email SMTP & POP Gateway', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_TYPE (CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 2, 'SMS_SMPP', 'SMS (SMPP) Gateway', 'SMS (SMPP) Gateway', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_TYPE (CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 3, 'SMS_HTTP', 'SMS (HTTP) Gateway', 'SMS (HTTP) Gateway', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_TYPE (CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 4, 'FILE_CSV', 'File (CSV) Channel', 'File (CSV) Gateway', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_TYPE (CATEGORY_ID, ADAPTER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 5, 'FILE_EXCEL', 'File (Excel) Channel', 'File (Excel) Gateway', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Data Channel Classification-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('COMMUNICATIONS_CHANNELS', 'Communications Channels', 'Communications Channels', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_CLASSIFICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION_CHANNELS', 'Transaction Channels', 'Transaction Channels', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Gateway Status-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ACTIVE', 'Active', 'Active', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INACTIVE', 'Inactive', 'Inactive', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


/* --------------------------------------------------------------------Message Gateway-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'mail.nathanclaire.com', '110', 'advicepro@nathanclaire.com', 'netfilter', 'I', 'CONN_METH_STARTTLS', 'AUTH_STARTTLS', 'N', 'NATHAN_CLAIRE_POP3_MAIL_SERVER', 'Nathan Claire POP3 Mail Server', 'Nathan Claire Mail Server', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'smtp.nathanclaire.com', '587', 'advicepro@nathanclaire.com', 'netfilter', 'O', 'CONN_METH_STARTTLS', 'AUTH_STARTTLS', 'N', 'NATHAN_CLAIRE_SMTP_MAIL_SERVER', 'Nathan Claire SMTP Mail Server', 'Nathan Claire Mail Server', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 4, 1, '127.0.0.1', NULL, '/home/edward/Projects/alantra/data/cts/cheque_txn.csv', NULL, NULL,  'I', '', '', 'N', 'CTS_CHQ_TXN_DATA_CSV_DATASOURCE', 'CTS Cheque Transactions CSV Data Source', 'CTS Cheque Transactions CSV Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 4, 1, '127.0.0.1', NULL, '/home/edward/Projects/alantra/data/customer/customer_data.csv', NULL, NULL, 'I', '', '', 'N', 'CUST_ACCT_DATA_CSV_DATASOURCE', 'Customer And Account Information CSV Data Sourcece', 'Customer And Account Information CSV Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 4, 1, '127.0.0.1', NULL, '/home/edward/Projects/alantra/data/advice/advice_data.csv', NULL, NULL, 'I', '', '', 'N', 'ADVICE_REQUEST_DATA_CSV_DATASOURCE', 'Advice Request CSV Data Source', 'Advice Request CSV Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 5, 1, '127.0.0.1', NULL, '/home/edward/Projects/alantra/data/cts/cheque_txn.xls', NULL, NULL, 'I', '', '', 'N', 'CTS_CHQ_TXN_DATA_EXCEL_DATASOURCE', 'CTS Cheque Transactions Excel Data Source', 'CTS Cheque Transactions Excel Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_CLASS_ID, CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, LOCAL_SERVICE_FG, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 3, 1, '127.0.0.1', 8083, '/home/edward/Projects/alantra/data/cts/cheque_txn.xls', NULL, NULL, 'I', '', '', 'Y', 'SERVER_TEST', 'QTM Service Test', 'QTM Service Test', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


/* --------------------------------------------------------------------Message-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE (MSG_TY_ID, MSG_CLASS_ID, MSG_STATUS_ID, CHANNEL_ID, MESSAGE_FROM, MESSAGE_TO, MESSAGE_SUBJECT, MESSAGE_TXT, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 1, 'customer@testserver.com', 'ek@ecobank.com', 'Advice', 'Advice', 'RECEIVED', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Category Notification Channel -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_CATEGORY_COMMUNICATIONS_CHANNEL (CUST_CAT_ID, CHANNEL_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'PERSONAL_EMAIL', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY_COMMUNICATIONS_CHANNEL (CUST_CAT_ID, CHANNEL_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 2, 'PERSONAL_SMS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY_COMMUNICATIONS_CHANNEL (CUST_CAT_ID, CHANNEL_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 'ORG_EMAIL', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY_COMMUNICATIONS_CHANNEL (CUST_CAT_ID, CHANNEL_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 2, 'ORG_SMS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


INSERT INTO SYSTEM_USER_COMMUNICATIONS_CHANNEL (SYSTEM_USER_ID, CHANNEL_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'PERSONAL_EMAIL', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel Pipeline-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL_PIPELINE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DEFAULT', 'Default', 'Default', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel Handler Type-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL_HANDLER_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DEFAULT', 'Default', 'Default', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel Handler-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'HTTP_DECODER', 'Http Decoder', 'Http Decoder', 'org.jboss.netty.handler.codec.http.HttpRequestDecoder', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'HTTP_ENCODER', 'Http Encoder', 'Http Encoder', 'org.jboss.netty.handler.codec.http.HttpResponseEncoder', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'HTTP_TXN_DATA_DECODER', 'Http Transaction Data Handler', 'Decodes Http based transaction data', 'HTTP_TXN_DATA_DECODER', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'HTTP_TXN_DATA_ENCODER', 'Http Transaction Data Encoder', 'Encodes Http based transaction data', 'HTTP_TXN_DATA_ENCODER', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'BO_VALIDATOR', 'Business Object Validator', 'Business Object Validator', 'BO_VALIDATOR', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'BO_PROCESSOR', 'Business Object Processor', 'Business Object Processor', 'BO_PROCESSOR', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_HANDLER (HANDLER_TY_ID, CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'BO_ROUTER', 'Business Object Router', 'Business Object Router', 'BO_ROUTER', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel Pipeline Handler-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'HTTP_DECODER', 'Http Decoder', 'Http Decoder', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 'HTTP_ENCODER', 'Http Encoder', 'Http Encoder', 2, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 'HTTP_TXN_DATA_DECODER', 'Http Transaction Data Decoder', 'Http Transaction Data Decoder', 3, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 'HTTP_TXN_DATA_ENCODE', 'Http Transaction Data Encoder', 'Http Transaction Data Encoder', 4, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 1, 'BO_VALIDATOR', 'Business Object Validator', 'Business Object Validator', 5, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (6, 1, 'BO_PROCESSOR', 'Business Object Processor', 'Business Object Processor', 6, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CHANNEL_PIPELINE_HANDLER (HANDLER_ID, PIPELINE_ID, CODE, NAME, DESCRIPTION, SEQUENCE_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (7, 1, 'BO_ROUTER', 'Business Object Router', 'Business Object Router', 7, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel Type-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MASTER', 'Master', 'Master', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel Status-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ACTIVE', 'Active', 'Active', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Channel-------------------------------------------------------------------------------------------- */

INSERT INTO CHANNEL (STATUS_ID, PIPELINE_ID, CHANNEL_TY_ID, DATA_CHANNEL_ID, CODE, NAME, DESCRIPTION, AUTO_START_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 7, 'SERVER', 'Server', 'Server', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Parameter Type-------------------------------------------------------------------------------------------- */

INSERT INTO PARAMETER_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STRING', 'String', 'String', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Parameter -------------------------------------------------------------------------------------------- */

INSERT INTO PARAMETER (PARAMETER_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ACCOUNT_NO', 'Account Number', 'Account Number', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Operator Type-------------------------------------------------------------------------------------------- */

INSERT INTO OPERATOR (CODE, NAME, DESCRIPTION, OPERATOR_SYMBOL, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EQUALS_TO', 'Equals To', 'Equals To', '=', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Parameter Operator-------------------------------------------------------------------------------------------- */

INSERT INTO PARAMETER_TYPE_OPERATOR (PARAMETER_TY_ID, OPERATOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'EQUALS_TO', 'Equals To', 'Equals To', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Space-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_SPACE (CODE, NAME, DESCRIPTION, DEFAULT_RULE_SPACE_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('OPERATIONS', 'Operations Rule Space', 'A rules space used during normal operation', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Category-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_CATEGORY (RULE_SPACE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'VALIDATION', 'Validation Rules', 'A category for validation rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_CATEGORY (RULE_SPACE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'PROCESSING', 'Processing Rules', 'A category for processing rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_CATEGORY (RULE_SPACE_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ROUTING', 'Routing Rules', 'A category for routing rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Type-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'INPUT', 'Input Validation Rules', 'Input validation rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ROUTE', 'Routing Validation Rules', 'Routing validation rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'OUTPUT', 'Output Validation Rules', 'Output validation rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'PROCESS', 'Processing Rules', 'Default type for processing rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'INBOUND', 'Inbound rules', 'Inbound routing rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'FORWARD', 'Forward rules', 'Forward routing rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO TRANSACTION_RULE_TYPE (RULE_CAT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'OUTBOUND', 'Outbound rules', 'Outbound routing rules', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Action-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_ACTION (CODE, NAME, DESCRIPTION, CLASS_NAME, EFFECTIVE_DT, REC_ST, VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ACCEPT_ACTION', 'Accept Action', 'Accept Action', 'com.nathanclaire.alantra.rule.action.validation.AcceptAction', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------Transaction Rule-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE (PROCESS_CAT_ID, RULE_TY_ID, RULE_ACTION_ID, CODE, NAME, DESCRIPTION, OPERATOR_MODE_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,1,1, 'VALIDATE_TXN_ATRTIBUTES_RULE', 'Transaction Attributes Validation Rule', "A rule to validate a transaction's attributes", 'A', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Condition Attribute-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_CONDITION_ATTRIBUTE (PARAMETER_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'TXN_ACCOUNT_NO', 'accountNo', 'Transaction Account No', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Condition Parameter-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_CONDITION_PARAMETER (PARAMETER_ID, PARAMETER_VALUE, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, '0001012', 'TXN_ACCOUNT_NO', 'accountNo', 'Transaction Account No', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transaction Rule Condition -------------------------------------------------------------------------------------------- */

INSERT INTO TRANSACTION_RULE_CONDITION (RULE_ID, ATTRIBUTE_ID, PARAMETER_ID, OPERATOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,1,1,1, 'RULE_CONDITION', 'Condition', 'Condition', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Classification -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_CLASSIFICATION(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NON_CYCLIC', 'Non Cyclic', 'Non Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_CLASSIFICATION(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CYCLIC', 'Cyclic', 'Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Types -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ATM_WITHDRAWAL_ADVICE', 'ATM Withdrawal Notice', 'ATM Withdrawal Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'CHQ_WITHDRAWAL', 'Cheque Withdrawal Notice', 'Cheque Withdrawal Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE(TXN_TY_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 'FUNDS_TRANSFER', 'Funds Transfer Notice', 'Funds Transfer Notice', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Status -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSED_CYCLES', 'Processed Cycles', 'Processed Cycles', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSED', 'Processed', 'Processed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSING_CYCLES', 'Processing Cycles', 'Processing Cycles', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('UNPROCESSED', 'Unprocessed', 'Unprocessed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INACTIVE', 'Inactive', 'Inactive', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE(ADVICE_TY_ID, ADVICE_CLASS_ID, ADVICE_ST_ID,  CUSTOMER_ID, ACCOUNT_ID, CURRENCY_ID, CODE, NAME, AMOUNT, DESCRIPTION, MAX_MATCHES, MATCH_COUNT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 3, 1, 1, 1, 'TEST', 'Test Cheque Advice', 1000.00, 'Test Cheque Advice', -1, 0, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_TRANSFORMER-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_TRANSFORMER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NO_OPERATION_DATA_TRANSFORMER', 'No Operation Data Transformer', 'No Operation Data Transformer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_TRANSFORMER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MAP_BASED_DATA_TRANSFORMER', 'Map Based Data Transformer', 'Map Based Data Transformer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_TRANSFORMER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('REL_ENTITY_CODE_TO_ID_DATA_TRANSFORMER', 'Related Entity Code To ID Data Transformer', 'Related Entity Code To ID Data Transformer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_STRUCTURE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_STRUCTURE (CODE, NAME, DESCRIPTION, BUSINESS_OBJECT_CD, SKIP_FIRST_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CTS_TXN_DATA_STRUCTURE', 'CTS Cheque Transaction Data Structure', 'CTS Cheque Transaction Data Structure', 'ServiceTransaction', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_FIELD_TYPE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STRING', 'String', 'String', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INTEGER', 'Integer', 'Integer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CHARACTER', 'Character', 'Single Character', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DECIMAL', 'Decimal', 'Decimal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATE', 'Date', 'Date', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RELATIONSHIP', 'Relationship', 'Relationship', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_FIELD-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REL_BUSINESS_OBJ_CD, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 6, 'ACCOUNT_NO', 'acct_no', 'Account Number', 'CustomerAccount', 'Y', 'N', '', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 4, 'AMOUNT', 'amount', 'Transaction Amount', 'Y', 'N', '', 2, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REL_BUSINESS_OBJ_CD, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 6, 'CURRENCY', 'crncy', 'Currency', 'Currency', 'Y', 'N', '', 3, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'CHEQUE_NO', 'Cheque Number', 'Cheque Number', 'Y', 'N', '', 4, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 5, 'TXN_DATE', 'Transaction Date', 'Transaction Date', 'Y', 'N', '', 5, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'CREATED_BY', 'Created By', 'Created By', 'Y', 'Y', 'Adrian', 6, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REL_BUSINESS_OBJ_CD, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 6, 'TXN_TY', 'Transaction Type', 'Transaction Type', 'ServiceTransactionType', 'Y', 'Y', 'ATM_CASH_WITHDRAWAL', 7, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REL_BUSINESS_OBJ_CD, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 6, 'TXN_STATUS', 'Status', 'Status', 'ServiceTransactionStatus', 'Y', 'Y', 'NOT_MATCHED', 8, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 3, 'REC_ST', 'Record Status', 'Record Status', 'Y', 'Y', 'A', 9, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REL_BUSINESS_OBJ_CD, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 6, 'CHANNEL', 'Channel', 'Channel', 'DataChannel', 'Y', 'Y', 'CTS_CHQ_TXN_DATA_EXCEL_DATASOURCE', 10, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, CODE, NAME, DESCRIPTION, REQUIRED_FG, VIRTUAL_FIELD, DEFAULT_VALUE, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'NAME', 'Name', 'Name', 'Y', 'Y', '', 10, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


/* --------------------------------------------------------------------Transformation Map-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSFORMATION_MAP (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CURRENCY_MAP', 'Currency Transformation Map', 'Currency Transformation Map', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Transformation Map Item-------------------------------------------------------------------------------------------- */

INSERT INTO TRANSFORMATION_MAP_ITEM (TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, SRC, DST, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'KENYA_SH', 'Kenyan Shilling', 'Kenyan Shilling', '13', 'KSH', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_FIELD_MAP-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'ACCOUNT_NO', 'Account Field No Mapping', 'Account Field No Mapping', 'customerAccount', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 1, 'AMOUNT', 'Transaction Amount Mapping', 'Transaction Amount Mapping', 'amount', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 2, 1, 'CURRENCY', 'Currency Mapping', 'Currency Mapping', 'currency', 'EQUALS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 1, 'CHEQUE_NO', 'Cheque Number Mapping', 'Cheque Number Mapping', 'chequeNo', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 1, 1, 'TXN_DATE', 'Transaction Date Field Mapping', 'Transaction Date Field Mapping', 'txnDate', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (6, 1, 1, 'CREATED_BY', 'Transaction Created By Field Mapping', 'Transaction Created By Field Mapping', 'createdByUsr', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (7, 1, 1, 'TXN_TY', 'Transaction Type Field Mapping', 'Transaction Type Field Mapping', 'serviceTransactionType', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (8, 1, 1, 'TXN_STATUS', 'Transaction Status Field Mapping', 'Transaction Status Field Mapping', 'serviceTransactionStatus', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (9, 1, 1, 'REC_ST', 'Transaction Record Status Field Mapping', 'Transaction Record Status Field Mapping', 'recSt', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (10, 1, 1, 'CHANNEL', 'Transaction Data Channel Field Mapping', 'Transaction Data Channel Field Mapping', 'dataChannel', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_MAP (FIELD_ID, TRANSFORMER_ID, TRANSFORMATION_MAP_ID, CODE, NAME, DESCRIPTION, BUSINESS_OBJ_FIELD_CD, OP_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (11, 1, 1, 'NAME', 'Transaction Name Field Mapping', 'Transaction Name Field Mapping', 'name', '', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_TYPE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION_DATA', 'Transactional Data', 'Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA-------------------------------------------------------------------------------------------- */

INSERT INTO DATA (DATA_TY_ID, DATA_STRUCT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'CTS_TRANSACTION_DATA', 'CTS Transactional Data', 'CTS Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_INPUT (DATA_ID, CHANNEL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 3, 'CTS_TRANSACTION_DATA_INPUT_CONFIG', 'CTS Transactional Data Input Configuration', 'CTS Transactional Data Input Configuration', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_JOB_TYPE-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CYCLIC', 'Cyclic', 'Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NON_CYCLIC', 'Non Cyclic', 'Non Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Data input job status-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NOT_RUNNING', 'Not Running', 'Not Running', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RUNNING', 'Runnig', 'Running', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSING_CYCLES', 'Processing Cycles', 'Processing Cycles', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('COMPLETED', 'Completed', 'Completed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_JOB-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_INPUT_JOB (DATA_INPUT_ID, DATA_INPUT_JOB_TY_ID, DATA_INPUT_JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,1,2, 'CTS_INPUT_JOB', 'CTS Input Job', 'CTS Input Job', 1, 'MINUTE', '2012-04-04', 'I', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

