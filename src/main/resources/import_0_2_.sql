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
 ) values ('13', 'Kenyan Shilling', 'KES', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('60', 'United States Dollar', '$', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('62', 'Euro', 'Euro', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CURRENCY (CODE, NAME, CRNCY_SYM, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
 ) values ('61', 'Pound Sterling', 'GBP', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

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

/* --------------------------------------------------------------------Message Application-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_APPLICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ADVICE', 'Application For Advices', 'Application for advices', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION', 'Application For Transactions', 'Application for transactions', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('UNKNOWN_MSG_APPLICATION', 'Application For Unknown Messages', 'Application for unknown messages', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('OUT_BOUND_MSG_APPLICATION', 'Application For Outbound Messages', 'Application for outbound messages', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Application Tag-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_APPLICATION_TAG (MSG_APPL_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ADVICE', 'Advice', 'Advice', 'Advice', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_TAG (MSG_APPL_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'TRANSACTION', 'Transaction', 'Transaction', 'Transaction', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Application Action-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_APPLICATION_ACTION (MSG_APPL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ADVICE_REQUEST', 'Advice Request', 'Advice Request', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION (MSG_APPL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ADVICE_INQUIRY', 'Advice Inquiry', 'Advice Inquiry', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION (MSG_APPL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ADVICE_REQUEST_REPLY', 'Advice Request Reply', 'Advice Request Reply', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION (MSG_APPL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ADVICE_INQUIRY_REPLY', 'Advice Inquiry Reply', 'Advice Inquiry Reply', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION (MSG_APPL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'TXN_DATA_INPUT_REQUEST', 'Transaction Data Input Request', 'Transaction Data Input Request', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION (MSG_APPL_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 'UNCLASSIFIED_INBOUND_MSG', 'Unclassified Inbound Message', 'Unclassified Inbound Message', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Application Action Tag-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE_APPLICATION_ACTION_TAG (ACTION_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'PAY', 'Pay', 'Pay', 'Pay', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION_TAG (ACTION_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'CHEQUE', 'Cheque', 'Cheque', 'Cheque', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION_TAG (ACTION_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'STATUS', 'Status', 'Status', 'Status', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION_TAG (ACTION_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'ADVICE', 'Advice', 'Advice', 'Advice', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO MESSAGE_APPLICATION_ACTION_TAG (ACTION_ID, CODE, NAME, DESCRIPTION, TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 'TXN_DATA', 'Transaction Data', 'Transaction data', 'Data', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

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

/* --------------------------------------------------------------------Message Gateway Status-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ACTIVE', 'Active', 'Active', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INACTIVE', 'Inactive', 'Inactive', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message Gateway-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_CHANNEL (CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'mail.nathanclaire.com', '110', 'advicepro@nathanclaire.com', 'netfilter', 'I', 'CONN_METH_STARTTLS', 'AUTH_STARTTLS', 'NATHAN_CLAIRE_POP3_MAIL_SERVER', 'Nathan Claire POP3 Mail Server', 'Nathan Claire Mail Server', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'smtp.nathanclaire.com', '587', 'advicepro@nathanclaire.com', 'netfilter', 'O', 'CONN_METH_STARTTLS', 'AUTH_STARTTLS', 'NATHAN_CLAIRE_SMTP_MAIL_SERVER', 'Nathan Claire SMTP Mail Server', 'Nathan Claire Mail Server', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, '127.0.0.1', NULL, '/home/administrator/Projects/alantra/data/cts/cheque_txn.csv', NULL, NULL,  'I', '', '', 'CTS_CHQ_TXN_DATA_CSV_DATASOURCE', 'CTS Cheque Transactions CSV Data Source', 'CTS Cheque Transactions CSV Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, '127.0.0.1', NULL, '/home/administrator/Projects/alantra/data/customer/customer_data.csv', NULL, NULL, 'I', '', '', 'CUST_ACCT_DATA_CSV_DATASOURCE', 'Customer And Account Information CSV Data Sourcece', 'Customer And Account Information CSV Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, '127.0.0.1', NULL, '/home/administrator/Projects/alantra/data/advice/advice_data.csv', NULL, NULL, 'I', '', '', 'ADVICE_REQUEST_DATA_CSV_DATASOURCE', 'Advice Request CSV Data Source', 'Advice Request CSV Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_CHANNEL (CHANNEL_TY_ID, CHANNEL_STATUS_ID, IP_ADDR, PORT_NO, URL, USERNAME, PASSWORD, INBOUND_OUTBOUND_CD, CONN_SECURITY_CD, AUTH_METHOD_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 1, '127.0.0.1', NULL, '/home/administrator/Projects/alantra/cheque_txn.xls', NULL, NULL, 'I', '', '', 'CTS_CHQ_TXN_DATA_EXCEL_DATASOURCE', 'CTS Cheque Transactions Excel Data Source', 'CTS Cheque Transactions Excel Data Source', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Message-------------------------------------------------------------------------------------------- */

INSERT INTO MESSAGE (MSG_APPL_ID, MSG_TY_ID, MSG_CLASS_ID, MSG_STATUS_ID, CHANNEL_ID, MESSAGE_FROM, MESSAGE_TO, MESSAGE_SUBJECT, MESSAGE_TXT, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 1, 1, 'customer@testserver.com', 'ek@ecobank.com', 'Advice', 'Advice', 'RECEIVED', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Customer Category Notification Channel -------------------------------------------------------------------------------------------- */

INSERT INTO CUSTOMER_CATEGORY_NOTIFICATION_CHANNEL (CUST_CAT_ID, CHANNEL_CAT_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'PERSONAL_EMAIL', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY_NOTIFICATION_CHANNEL (CUST_CAT_ID, CHANNEL_CAT_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 2, 'PERSONAL_SMS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY_NOTIFICATION_CHANNEL (CUST_CAT_ID, CHANNEL_CAT_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 'ORG_EMAIL', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO CUSTOMER_CATEGORY_NOTIFICATION_CHANNEL (CUST_CAT_ID, CHANNEL_CAT_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 2, 'ORG_SMS', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


INSERT INTO SYSTEM_USER_NOTIFICATION_CHANNEL (SYSTEM_USER_ID, CHANNEL_CAT_ID, CODE, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'PERSONAL_EMAIL', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Request Message Status -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_REQUEST_MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('REJECTED', 'Rejected', 'Rejected', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_REQUEST_MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('REJECTED_ACCOUNT_NOT_FOUND', 'Rejected Account Not Found', 'Rejected Account Not Found', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_REQUEST_MESSAGE_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ACCEPTED', 'Accepted', 'Accepted', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Request Message -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_REQUEST_MESSAGE (CHANNEL_ID, CUSTOMER_ID, STATUS_ID, SOURCE_ADDRESS, AMOUNT, ACCOUNT_NO, CURRENCY_CD, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, '07867542', 23400.00, '080886752', 'KES', 'TEST_MESSAGE', 'Test', 'Test', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

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

/* -------------------------------------------------------------------- Advice Types Tag-------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_TYPE_TAG(ADVICE_TY_ID, CODE, NAME, DESCRIPTION, ADVICE_TY_TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'CHEQUE', 'Cheque', 'Cheque', 'Cheque', 'N',  '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE_TAG(ADVICE_TY_ID, CODE, NAME, DESCRIPTION, ADVICE_TY_TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'CHECK', 'Check', 'Check', 'Check', 'N','2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE_TAG(ADVICE_TY_ID, CODE, NAME, DESCRIPTION, ADVICE_TY_TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 'PAY', 'Pay', 'Pay', 'Pay', 'N','2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_TYPE_TAG(ADVICE_TY_ID, CODE, NAME, DESCRIPTION, ADVICE_TY_TAG_VAL, IS_REGEX_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 'ATM', 'ATM', 'ATM', 'ATM', 'N','2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice Status -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSED', 'Processed', 'Processed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('PROCESSING_CYCLES', 'Partially processed', 'Partially processed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO ADVICE_STATUS(CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('UNPROCESSED', 'Unprocessed', 'Unprocessed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* -------------------------------------------------------------------- Advice -------------------------------------------------------------------------------------------- */

INSERT INTO ADVICE(ADVICE_TY_ID, ADVICE_CLASS_ID, ADVICE_ST_ID,  CUSTOMER_ID, ACCOUNT_ID, CURRENCY_ID, ADVICE_REQUEST_ID, CODE, NAME, AMOUNT, DESCRIPTION, MAX_MATCHES, MATCH_COUNT, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 3, 1, 1, 1, 1, 'TEST', 'Test Cheque Advice', 1000.00, 'Test Cheque Advice', -1, 0, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


/* --------------------------------------------------------------------DATA_EXTRACTOR-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_EXTRACTOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CSV_DATA_EXTRACTOR', 'CSV Data Extractor', 'CSV Data Extractor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_EXTRACTOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EXCEL_DATA_EXTRACTOR', 'Excel Data Extractor', 'Excel Data Extractor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_EXTRACTOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL_DATA_EXTRACTOR', 'Email Data Extractor', 'Email Data Extractor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_PROCESSOR-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_PROCESSOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION_DATA_PROCESSOR', 'Transaction Data Processor', 'Transaction Data Processor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_PROCESSOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUST_ACCT_DATA_PROCESSOR', 'Customer And Account Data Processor', 'Customer And Account Data Processor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_PROCESSOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ADVICE_REQUEST_DATA_PROCESSOR', 'Advice Request Data Processor', 'Advice Request Data Processor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_PROCESSOR (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MESSAGE_DATA_PROCESSOR', 'Message Request Data Processor', 'Message Request Data Processor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_PROCESSOR-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_LOADER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION_DATA_LOADER', 'Transaction Data Loader', 'Transaction Data Loader', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_LOADER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUST_ACCT_DATA_LOADER', 'Customer And Account Data Loader', 'Customer And Account Data Loader', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_LOADER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ADVICE_REQUEST_DATA_LOADER', 'Advice Request Data Loader', 'Advice Request Data Loader', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_LOADER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('MESSAGE_DATA_LOADER', 'Message Request Data Loader', 'Message Request Data Loader', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_TRANSFORMER-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_TRANSFORMER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NO_OPERATION_DATA_TRANSFORMER', 'No Operation Data Transformer', 'No Operation Data Transformer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_TRANSFORMER (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('REL_ENTITY_CODE_TO_ID_DATA_TRANSFORMER', 'Related Entity Code To ID Data Transformer', 'Related Entity Code To ID Data Transformer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_STRUCTURE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_PRI_ENTITY_CD,  TARGET_SEC_ENTITY_CD, SKIP_FIRST_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL_MESG_DATA_STRUCTURE', 'Email Message Request Data Structure', 'Email Message  Request Data Structure', 'Message', 'MessageAttachements', 'N', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_PRI_ENTITY_CD, SKIP_FIRST_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CTS_TXN_DATA_STRUCTURE', 'CTS Cheque Transaction Data Structure', 'CTS Cheque Transaction Data Structure', 'ServiceTransaction', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_PRI_ENTITY_CD, TARGET_SEC_ENTITY_CD, SKIP_FIRST_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUST_ACCT_DATA_STRUCTURE', 'Customer And Account Data Structure', 'Customer And Account Data Structure', 'Customer', 'Account', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_STRUCTURE (CODE, NAME, DESCRIPTION, TARGET_PRI_ENTITY_CD, SKIP_FIRST_FG, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ADVICE_REQUEST_DATA_STRUCTURE', 'Advice Request Data Structure', 'Advice Request Data Structure', 'AdviceRequestMessage', 'Y', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_FIELD_TYPE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('STRING', 'String', 'String', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('INTEGER', 'Integer', 'Integer', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DECIMAL', 'Decimal', 'Decimal', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('DATE', 'Date', 'Date', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RELATIONSHIP', 'Relationship', 'Relationship', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_FIELD-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, REL_TARGET_ENTITY_CD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_MSG_ID', 'Message Id', 'Message Id', 'Message', 'code', '', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_FROM', 'Message From', 'Message From', 'Message', 'messageFrom', 2, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD,  REL_TARGET_ENTITY_CD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_TO', 'Message To', 'Message To', 'Message', 'messageTo', '', 3, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_SUBJECT', 'Message Subject', 'Message Subject', 'Message', 'messageSubject', 4, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_BODY', 'Message Body', 'Message Body', 'Message', 'messageTxt', 5, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_ATTACHMENT_DATA_TYPE', 'Message Attachment Data Type', 'Message Attachment Data Type', 'MessageAttachements', 'dataTy', 6, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_ATTACHMENT_DATA_URL', 'Message Attachment Location', 'Message Attachment Location', 'MessageAttachements', 'dataUrl', 7, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, REL_TARGET_ENTITY_CD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 5, 1, 'ACCOUNT_NO', 'acct_no', 'Account Number', 'ServiceTransaction', 'customerAccount', 'CustomerAccount', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 3, 1, 'AMOUNT', 'amount', 'Transaction Amount', 'ServiceTransaction', 'amount',  2, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, REL_TARGET_ENTITY_CD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 5, 1, 'CURRENCY', 'crncy', 'Currency', 'ServiceTransaction', 'currency', 'Currency', 3, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 1, 'CHEQUE_NO', 'Cheque Number', 'Cheque Number', 'ServiceTransaction', 'chequeNo',  4, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 4, 1, 'TXN_DATE', 'txn_dt', 'Transaction Date', 'ServiceTransaction', 'txnDate',  5, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'BRANCH_CODE', 'Branch', 'Branch', '', '', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'CLEARING_NO', 'Clearing No', 'Clearing No', 'Customer', 'code', 2, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'CUST_ACCOUNT_NO', 'Account No', 'Account No', 'Account', 'accountNo', 3, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'ALT_ACCOUNT_NO', 'Alternate Account Number', 'Alternate Account Number', '', '', 4, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'CUST_CURRENCY', 'Customer Currency', 'Currency', '', '', 5, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'CUST_NAME', 'Name', 'Name', 'Customer', 'Name', 6, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'ACCOUNT_STATUS', 'Status', 'Status', '', '', 7, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'ACCOUNT_STATE', 'State', 'State', '', '', 8, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'ADDRESS', 'Address', 'Address', '', '', 9, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'PHONE', 'Phone', 'Phone', 'Customer', 'primaryMobile', 10, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 1, 1, 'EMAIL', 'Email', 'Email', 'Customer', 'primaryEmail', 11, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, REL_TARGET_ENTITY_CD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 1, 'ADVICE_CURRENCY', 'Advice currency', 'Advice currency', 'AdviceRequestMessage', 'currencyCd', 'Currency', 1, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 3, 1, 'ADVICE_AMOUNT', 'Advice amount', 'Advice amount', 'AdviceRequestMessage', 'amount', 2, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD,  REL_TARGET_ENTITY_CD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 1, 'ADVICE_ACCOUNT_NO', 'Advice account number', 'Advice account number', 'AdviceRequestMessage', 'accountNo', '', 3, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 1, 'ADVICE_CHEQUE_NO', 'Advice cheque number', 'Advice cheque number', 'AdviceRequestMessage', 'chequeNo', 4, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 1, 'ADVICE_CARD_NO', 'Advice card number', 'Advice card number', 'AdviceRequestMessage', 'cardNo', 5, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_FIELD (DATA_STRUCT_ID, DATA_FIELD_TY_ID, DATA_TRANSFORMER_ID, CODE, NAME, DESCRIPTION, TARGET_ENTITY_CD, TARGET_ENTITY_FIELD, SEQ_NO, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 1, 1, 'ADVICE_TEXT', 'Advice text', 'Advice text', 'AdviceRequestMessage', 'name', 6, '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_TYPE-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('EMAIL_MESSAGE_DATA', 'Email Message Data', 'Email Message Data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('TRANSACTION_DATA', 'Transactional Data', 'Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CUSTOMER_ACCT_DATA', 'Customer And Account Information', 'Customer data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('ADVICE_REQUEST_DATA', 'Advice Request Data', 'Advice Request Data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA-------------------------------------------------------------------------------------------- */

INSERT INTO DATA (CHANNEL_ID, DATA_TY_ID, DATA_STRUCT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 1, 'EMAIL_MESSAGE_DATA', 'Email Message Data', 'Email Message Data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA (CHANNEL_ID, DATA_TY_ID, DATA_STRUCT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 2, 2, 'CTS_TRANSACTION_DATA', 'CTS Transactional Data', 'CTS Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA (CHANNEL_ID, DATA_TY_ID, DATA_STRUCT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 3, 3, 'CBS_CUSTOMER_DATA', 'CBS Customer And Account Data', 'CBS Customer And Account Data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA (CHANNEL_ID, DATA_TY_ID, DATA_STRUCT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 4, 4, 'ADVICE_REQUEST_DATA', 'Advice Request Data', 'Advice Request Data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA (CHANNEL_ID, DATA_TY_ID, DATA_STRUCT_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 2, 2, 'CTS_EXCEL_TRANSACTION_DATA', 'CTS Excel Transactional Data', 'CTS Excel Transaction data', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_INPUT (DATA_ID, DATA_LOADER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 4, 'EMAIL_MASSAGE_DATA_INPUT_CONFIG', 'Email Message Data Input Configuration', 'Email Message Data Input Configuration', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT (DATA_ID, DATA_LOADER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 1, 'CTS_TRANSACTION_DATA_INPUT_CONFIG', 'CTS Transactional Data Input Configuration', 'CTS Transactional Data Input Configuration', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT (DATA_ID, DATA_LOADER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 2, 'CBS_CUSTOMER_DATA_INPUT_CONFIG', 'CBS Customer Data Input Configuration', 'CBS Customer Transactional Data Input Configuration', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT (DATA_ID, DATA_LOADER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 3, 'ADVICE_REQUEST_DATA_INPUT_CONFIG', 'Advice Request Data Input Configuration', 'Advice Request Data Input Configuration', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT (DATA_ID, DATA_LOADER_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 1, 'CTS_EXCEL_TRANSACTION_DATA_INPUT_CONFIG', 'CTS Excel Transactional Data Input Configuration', 'CTS Excel Transactional Data Input Configuration', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_PROCESSSORS-------------------------------------------------------------------------------------------- */

INSERT INTO DATA_INPUT_PROCESSORS (DATA_INPUT_ID, DATA_PROCESSOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1, 1, 'CTS_DATA_INPUT_PROCESSORS', 'CTS Data Input Processor', 'CTS Data Input Processors', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_PROCESSORS (DATA_INPUT_ID, DATA_PROCESSOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2, 2, 'CBS_CUST_DATA_INPUT_PROCESSORS', 'CBS Customer Data Input Processor', 'CTS Data Input Processors', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_PROCESSORS (DATA_INPUT_ID, DATA_PROCESSOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3, 3, 'ADVICE_REQUEST_DATA_INPUT_PROCESSORS', 'Advice Request Data Input Processor', 'Advice Request Data Input Processor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_PROCESSORS (DATA_INPUT_ID, DATA_PROCESSOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4, 4, 'EMAIL_MESSAGE_DATA_INPUT_PROCESSORS', 'Email Message Data Input Processor', 'Email Message Data Input Processor', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_PROCESSORS (DATA_INPUT_ID, DATA_PROCESSOR_ID, CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5, 1, 'CTS_EXCEL_DATA_INPUT_PROCESSORS', 'CTS Excel Data Input Processor', 'CTS Excel Data Input Processors', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------DATA_INPUT_JOB_TYPE-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('CYCLIC', 'Cyclic', 'Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_TYPE (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NON_CYCLIC', 'Non Cyclic', 'Non Cyclic', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

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

INSERT INTO DATA_INPUT_JOB (DATA_INPUT_ID, DATA_INPUT_JOB_TY_ID, DATA_INPUT_JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (1,1,2, 'EMAIL_MESSAGE_DATA_INPUT_JOB', 'Email Message Data Input Job', 'Email Message Data Input Job', 1, 'MINUTE', '2012-04-04', 'I', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB (DATA_INPUT_ID, DATA_INPUT_JOB_TY_ID, DATA_INPUT_JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (2,1,2, 'CTS_INPUT_JOB', 'CTS Input Job', 'CTS Input Job', 1, 'MINUTE', '2012-04-04', 'I', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB (DATA_INPUT_ID, DATA_INPUT_JOB_TY_ID, DATA_INPUT_JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (3,1,2, 'CBS_CUST_DATA_INPUT_JOB', 'CBS Customer Data Input Job', 'CBS Customer Data Input Job', 1, 'MINUTE', '2012-04-04', 'I', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB (DATA_INPUT_ID, DATA_INPUT_JOB_TY_ID, DATA_INPUT_JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (4,1,2, 'ADVICE_REQUEST_DATA_INPUT_JOB', 'Advice Request Data Input Job', 'Advice Request Data Input Job', 1, 'MINUTE', '2012-04-04', 'I', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB (DATA_INPUT_ID, DATA_INPUT_JOB_TY_ID, DATA_INPUT_JOB_STATUS_ID, CODE, NAME, DESCRIPTION, DI_FREQ_VAL, DI_FREQ_CD, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values (5,1,2, 'CTS_EXCEL_INPUT_JOB', 'CTS Excel Input Job', 'CTS Excel Input Job', 1, 'MINUTE', '2012-04-04', 'I', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );


/* --------------------------------------------------------------------Data input job  summary status-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_SUMMARY_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('SUCCESSFULLY_COMPLETED', 'Successfully Completed', 'Completed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_SUMMARY_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('NOT_SUCCESSFULLY_COMPLETED', 'Not Successfully Completed', 'Completed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

INSERT INTO DATA_INPUT_JOB_SUMMARY_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('RUNNING', 'Running', 'Running', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

/* --------------------------------------------------------------------Data input job record summary status-------------------------------------------------------------------------------------------- */
INSERT INTO DATA_INPUT_JOB_RECORDS_SUMMARY_STATUS (CODE, NAME, DESCRIPTION, EFFECTIVE_DT, REC_ST , VERSION_NO, ROW_TS, CREATED_DT, CREATED_BY_USR, LAST_MODIFIED_DT, LAST_MODIFIED_USR
) values ('COMPLETED', 'Completed', 'Completed', '2012-04-04', 'A', 1, '2012-04-04', '2012-04-04', 'Admin', '2012-04-04', 'Admin' );

