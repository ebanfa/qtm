/**
 * A module for the router of the desktop application
 */
define("router", [
    'jquery',
    'underscore',
    'configuration',
    'utilities',
    'app/views/desktop/login',
    'app/views/desktop/home',
    'app/views/desktop/home/customer-home',
    'app/views/desktop/home/advice-home',
    'app/views/desktop/home/channel-home',
    'app/views/desktop/home/product-home',
    'app/views/desktop/home/order-home',
    'app/views/desktop/home/invoice-home',
    'app/views/desktop/home/payment-home',
    'app/views/desktop/home/messaging-home',
    'app/views/desktop/home/businessdata-home',
    'app/views/desktop/home/report-home',
    'app/models/advice/advicestatus/advicestatus',
    'app/collections/advice/advicestatus/advicestatus',
    'app/views/desktop/advice/advicestatus/edit-advicestatus',
    'app/views/desktop/advice/advicestatus/list-advicestatus',
    'app/models/advice/advicetype/advicetype',
    'app/collections/advice/advicetype/advicetype',
    'app/views/desktop/advice/advicetype/edit-advicetype',
    'app/views/desktop/advice/advicetype/list-advicetype',
    'app/models/advice/advicetypetag/advicetypetag',
    'app/collections/advice/advicetypetag/advicetypetag',
    'app/views/desktop/advice/advicetypetag/edit-advicetypetag',
    'app/views/desktop/advice/advicetypetag/list-advicetypetag',
    'app/models/advice/advice/advice',
    'app/collections/advice/advice/advice',
    'app/views/desktop/advice/advice/edit-advice',
    'app/views/desktop/advice/advice/list-advice',
    'app/models/channel/service/service',
    'app/collections/channel/service/service',
    'app/views/desktop/channel/service/edit-service',
    'app/views/desktop/channel/service/list-service',
    'app/models/channel/servicetype/servicetype',
    'app/collections/channel/servicetype/servicetype',
    'app/views/desktop/channel/servicetype/edit-servicetype',
    'app/views/desktop/channel/servicetype/list-servicetype',
    'app/models/channel/servicecategory/servicecategory',
    'app/collections/channel/servicecategory/servicecategory',
    'app/views/desktop/channel/servicecategory/edit-servicecategory',
    'app/views/desktop/channel/servicecategory/list-servicecategory',
    'app/models/channel/servicemode/servicemode',
    'app/collections/channel/servicemode/servicemode',
    'app/views/desktop/channel/servicemode/edit-servicemode',
    'app/views/desktop/channel/servicemode/list-servicemode',
    'app/models/channel/servicetransactiontype/servicetransactiontype',
    'app/collections/channel/servicetransactiontype/servicetransactiontype',
    'app/views/desktop/channel/servicetransactiontype/edit-servicetransactiontype',
    'app/views/desktop/channel/servicetransactiontype/list-servicetransactiontype',
    'app/models/channel/hosttype/hosttype',
    'app/collections/channel/hosttype/hosttype',
    'app/views/desktop/channel/hosttype/edit-hosttype',
    'app/views/desktop/channel/hosttype/list-hosttype',
    'app/models/channel/servicepeer/servicepeer',
    'app/collections/channel/servicepeer/servicepeer',
    'app/views/desktop/channel/servicepeer/edit-servicepeer',
    'app/views/desktop/channel/servicepeer/list-servicepeer',
    'app/models/channel/servicetransaction/servicetransaction',
    'app/collections/channel/servicetransaction/servicetransaction',
    'app/views/desktop/channel/servicetransaction/edit-servicetransaction',
    'app/views/desktop/channel/servicetransaction/list-servicetransaction',
    'app/models/channel/serviceprotocoladapter/serviceprotocoladapter',
    'app/collections/channel/serviceprotocoladapter/serviceprotocoladapter',
    'app/views/desktop/channel/serviceprotocoladapter/edit-serviceprotocoladapter',
    'app/views/desktop/channel/serviceprotocoladapter/list-serviceprotocoladapter',
    'app/models/channel/host/host',
    'app/collections/channel/host/host',
    'app/views/desktop/channel/host/edit-host',
    'app/views/desktop/channel/host/list-host',
    'app/models/workeffort/workeffort/workeffort',
    'app/collections/workeffort/workeffort/workeffort',
    'app/views/desktop/workeffort/workeffort/edit-workeffort',
    'app/views/desktop/workeffort/workeffort/list-workeffort',
    'app/models/workeffort/workefforttype/workefforttype',
    'app/collections/workeffort/workefforttype/workefforttype',
    'app/views/desktop/workeffort/workefforttype/edit-workefforttype',
    'app/views/desktop/workeffort/workefforttype/list-workefforttype',
    'app/models/order/productorderitemtype/productorderitemtype',
    'app/collections/order/productorderitemtype/productorderitemtype',
    'app/views/desktop/order/productorderitemtype/edit-productorderitemtype',
    'app/views/desktop/order/productorderitemtype/list-productorderitemtype',
    'app/models/order/productorder/productorder',
    'app/collections/order/productorder/productorder',
    'app/views/desktop/order/productorder/edit-productorder',
    'app/views/desktop/order/productorder/list-productorder',
    'app/models/order/productorderitem/productorderitem',
    'app/collections/order/productorderitem/productorderitem',
    'app/views/desktop/order/productorderitem/edit-productorderitem',
    'app/views/desktop/order/productorderitem/list-productorderitem',
    'app/models/order/productordertype/productordertype',
    'app/collections/order/productordertype/productordertype',
    'app/views/desktop/order/productordertype/edit-productordertype',
    'app/views/desktop/order/productordertype/list-productordertype',
    'app/models/messaging/communicationeventpurposetype/communicationeventpurposetype',
    'app/collections/messaging/communicationeventpurposetype/communicationeventpurposetype',
    'app/views/desktop/messaging/communicationeventpurposetype/edit-communicationeventpurposetype',
    'app/views/desktop/messaging/communicationeventpurposetype/list-communicationeventpurposetype',
    'app/models/messaging/communicationevent/communicationevent',
    'app/collections/messaging/communicationevent/communicationevent',
    'app/views/desktop/messaging/communicationevent/edit-communicationevent',
    'app/views/desktop/messaging/communicationevent/list-communicationevent',
    'app/models/messaging/communicationeventworkeffort/communicationeventworkeffort',
    'app/collections/messaging/communicationeventworkeffort/communicationeventworkeffort',
    'app/views/desktop/messaging/communicationeventworkeffort/edit-communicationeventworkeffort',
    'app/views/desktop/messaging/communicationeventworkeffort/list-communicationeventworkeffort',
    'app/models/messaging/communicationeventtype/communicationeventtype',
    'app/collections/messaging/communicationeventtype/communicationeventtype',
    'app/views/desktop/messaging/communicationeventtype/edit-communicationeventtype',
    'app/views/desktop/messaging/communicationeventtype/list-communicationeventtype',
    'app/models/messaging/communicationeventpurpose/communicationeventpurpose',
    'app/collections/messaging/communicationeventpurpose/communicationeventpurpose',
    'app/views/desktop/messaging/communicationeventpurpose/edit-communicationeventpurpose',
    'app/views/desktop/messaging/communicationeventpurpose/list-communicationeventpurpose',
    'app/models/messaging/communicationeventstatustype/communicationeventstatustype',
    'app/collections/messaging/communicationeventstatustype/communicationeventstatustype',
    'app/views/desktop/messaging/communicationeventstatustype/edit-communicationeventstatustype',
    'app/views/desktop/messaging/communicationeventstatustype/list-communicationeventstatustype',
    'app/models/payment/paymentapplication/paymentapplication',
    'app/collections/payment/paymentapplication/paymentapplication',
    'app/views/desktop/payment/paymentapplication/edit-paymentapplication',
    'app/views/desktop/payment/paymentapplication/list-paymentapplication',
    'app/models/payment/paymentmethodtypeprovider/paymentmethodtypeprovider',
    'app/collections/payment/paymentmethodtypeprovider/paymentmethodtypeprovider',
    'app/views/desktop/payment/paymentmethodtypeprovider/edit-paymentmethodtypeprovider',
    'app/views/desktop/payment/paymentmethodtypeprovider/list-paymentmethodtypeprovider',
    'app/models/payment/paymentmethodtype/paymentmethodtype',
    'app/collections/payment/paymentmethodtype/paymentmethodtype',
    'app/views/desktop/payment/paymentmethodtype/edit-paymentmethodtype',
    'app/views/desktop/payment/paymentmethodtype/list-paymentmethodtype',
    'app/models/payment/payment/payment',
    'app/collections/payment/payment/payment',
    'app/views/desktop/payment/payment/edit-payment',
    'app/views/desktop/payment/payment/list-payment',
    'app/models/payment/paymenttype/paymenttype',
    'app/collections/payment/paymenttype/paymenttype',
    'app/views/desktop/payment/paymenttype/edit-paymenttype',
    'app/views/desktop/payment/paymenttype/list-paymenttype',
    'app/models/party/partycontactmechanismpurpose/partycontactmechanismpurpose',
    'app/collections/party/partycontactmechanismpurpose/partycontactmechanismpurpose',
    'app/views/desktop/party/partycontactmechanismpurpose/edit-partycontactmechanismpurpose',
    'app/views/desktop/party/partycontactmechanismpurpose/list-partycontactmechanismpurpose',
    'app/models/party/partyrelationship/partyrelationship',
    'app/collections/party/partyrelationship/partyrelationship',
    'app/views/desktop/party/partyrelationship/edit-partyrelationship',
    'app/views/desktop/party/partyrelationship/list-partyrelationship',
    'app/models/party/contactmechanismpurposetype/contactmechanismpurposetype',
    'app/collections/party/contactmechanismpurposetype/contactmechanismpurposetype',
    'app/views/desktop/party/contactmechanismpurposetype/edit-contactmechanismpurposetype',
    'app/views/desktop/party/contactmechanismpurposetype/list-contactmechanismpurposetype',
    'app/models/party/postaladdress/postaladdress',
    'app/collections/party/postaladdress/postaladdress',
    'app/views/desktop/party/postaladdress/edit-postaladdress',
    'app/views/desktop/party/postaladdress/list-postaladdress',
    'app/models/party/caseroletype/caseroletype',
    'app/collections/party/caseroletype/caseroletype',
    'app/views/desktop/party/caseroletype/edit-caseroletype',
    'app/views/desktop/party/caseroletype/list-caseroletype',
    'app/models/party/partytype/partytype',
    'app/collections/party/partytype/partytype',
    'app/views/desktop/party/partytype/edit-partytype',
    'app/views/desktop/party/partytype/list-partytype',
    'app/models/party/partycase/partycase',
    'app/collections/party/partycase/partycase',
    'app/views/desktop/party/partycase/edit-partycase',
    'app/views/desktop/party/partycase/list-partycase',
    'app/models/party/contactmechanismlink/contactmechanismlink',
    'app/collections/party/contactmechanismlink/contactmechanismlink',
    'app/views/desktop/party/contactmechanismlink/edit-contactmechanismlink',
    'app/views/desktop/party/contactmechanismlink/list-contactmechanismlink',
    'app/models/party/roletype/roletype',
    'app/collections/party/roletype/roletype',
    'app/views/desktop/party/roletype/edit-roletype',
    'app/views/desktop/party/roletype/list-roletype',
    'app/models/party/contactmechanism/contactmechanism',
    'app/collections/party/contactmechanism/contactmechanism',
    'app/views/desktop/party/contactmechanism/edit-contactmechanism',
    'app/views/desktop/party/contactmechanism/list-contactmechanism',
    'app/models/party/person/person',
    'app/collections/party/person/person',
    'app/views/desktop/party/person/edit-person',
    'app/views/desktop/party/person/list-person',
    'app/models/party/casestatustype/casestatustype',
    'app/collections/party/casestatustype/casestatustype',
    'app/views/desktop/party/casestatustype/edit-casestatustype',
    'app/views/desktop/party/casestatustype/list-casestatustype',
    'app/models/party/partyrelationshiptype/partyrelationshiptype',
    'app/collections/party/partyrelationshiptype/partyrelationshiptype',
    'app/views/desktop/party/partyrelationshiptype/edit-partyrelationshiptype',
    'app/views/desktop/party/partyrelationshiptype/list-partyrelationshiptype',
    'app/models/party/partyroletype/partyroletype',
    'app/collections/party/partyroletype/partyroletype',
    'app/views/desktop/party/partyroletype/edit-partyroletype',
    'app/views/desktop/party/partyroletype/list-partyroletype',
    'app/models/party/contactmechanismtype/contactmechanismtype',
    'app/collections/party/contactmechanismtype/contactmechanismtype',
    'app/views/desktop/party/contactmechanismtype/edit-contactmechanismtype',
    'app/views/desktop/party/contactmechanismtype/list-contactmechanismtype',
    'app/models/party/partyclassification/partyclassification',
    'app/collections/party/partyclassification/partyclassification',
    'app/views/desktop/party/partyclassification/edit-partyclassification',
    'app/views/desktop/party/partyclassification/list-partyclassification',
    'app/models/party/party/party',
    'app/collections/party/party/party',
    'app/views/desktop/party/party/edit-party',
    'app/views/desktop/party/party/list-party',
    'app/models/party/estimatedproductcost/estimatedproductcost',
    'app/collections/party/estimatedproductcost/estimatedproductcost',
    'app/views/desktop/party/estimatedproductcost/edit-estimatedproductcost',
    'app/views/desktop/party/estimatedproductcost/list-estimatedproductcost',
    'app/models/party/partyrole/partyrole',
    'app/collections/party/partyrole/partyrole',
    'app/views/desktop/party/partyrole/edit-partyrole',
    'app/views/desktop/party/partyrole/list-partyrole',
    'app/models/party/caserole/caserole',
    'app/collections/party/caserole/caserole',
    'app/views/desktop/party/caserole/edit-caserole',
    'app/views/desktop/party/caserole/list-caserole',
    'app/models/party/partyclassificationtype/partyclassificationtype',
    'app/collections/party/partyclassificationtype/partyclassificationtype',
    'app/views/desktop/party/partyclassificationtype/edit-partyclassificationtype',
    'app/views/desktop/party/partyclassificationtype/list-partyclassificationtype',
    'app/models/party/organization/organization',
    'app/collections/party/organization/organization',
    'app/views/desktop/party/organization/edit-organization',
    'app/views/desktop/party/organization/list-organization',
    'app/models/party/telecommunicationsnumber/telecommunicationsnumber',
    'app/collections/party/telecommunicationsnumber/telecommunicationsnumber',
    'app/views/desktop/party/telecommunicationsnumber/edit-telecommunicationsnumber',
    'app/views/desktop/party/telecommunicationsnumber/list-telecommunicationsnumber',
    'app/models/party/partycontactmechanism/partycontactmechanism',
    'app/collections/party/partycontactmechanism/partycontactmechanism',
    'app/views/desktop/party/partycontactmechanism/edit-partycontactmechanism',
    'app/views/desktop/party/partycontactmechanism/list-partycontactmechanism',
    'app/models/party/electronicaddress/electronicaddress',
    'app/collections/party/electronicaddress/electronicaddress',
    'app/views/desktop/party/electronicaddress/edit-electronicaddress',
    'app/views/desktop/party/electronicaddress/list-electronicaddress',
    'app/models/invoice/invoicestatustype/invoicestatustype',
    'app/collections/invoice/invoicestatustype/invoicestatustype',
    'app/views/desktop/invoice/invoicestatustype/edit-invoicestatustype',
    'app/views/desktop/invoice/invoicestatustype/list-invoicestatustype',
    'app/models/invoice/invoiceitemcategory/invoiceitemcategory',
    'app/collections/invoice/invoiceitemcategory/invoiceitemcategory',
    'app/views/desktop/invoice/invoiceitemcategory/edit-invoiceitemcategory',
    'app/views/desktop/invoice/invoiceitemcategory/list-invoiceitemcategory',
    'app/models/invoice/invoicestatus/invoicestatus',
    'app/collections/invoice/invoicestatus/invoicestatus',
    'app/views/desktop/invoice/invoicestatus/edit-invoicestatus',
    'app/views/desktop/invoice/invoicestatus/list-invoicestatus',
    'app/models/invoice/invoice/invoice',
    'app/collections/invoice/invoice/invoice',
    'app/views/desktop/invoice/invoice/edit-invoice',
    'app/views/desktop/invoice/invoice/list-invoice',
    'app/models/invoice/invoicetype/invoicetype',
    'app/collections/invoice/invoicetype/invoicetype',
    'app/views/desktop/invoice/invoicetype/edit-invoicetype',
    'app/views/desktop/invoice/invoicetype/list-invoicetype',
    'app/models/invoice/invoiceitem/invoiceitem',
    'app/collections/invoice/invoiceitem/invoiceitem',
    'app/views/desktop/invoice/invoiceitem/edit-invoiceitem',
    'app/views/desktop/invoice/invoiceitem/list-invoiceitem',
    'app/models/invoice/invoicerole/invoicerole',
    'app/collections/invoice/invoicerole/invoicerole',
    'app/views/desktop/invoice/invoicerole/edit-invoicerole',
    'app/views/desktop/invoice/invoicerole/list-invoicerole',
    'app/models/invoice/invoiceitemtype/invoiceitemtype',
    'app/collections/invoice/invoiceitemtype/invoiceitemtype',
    'app/views/desktop/invoice/invoiceitemtype/edit-invoiceitemtype',
    'app/views/desktop/invoice/invoiceitemtype/list-invoiceitemtype',
    'app/models/invoice/invoiceroletype/invoiceroletype',
    'app/collections/invoice/invoiceroletype/invoiceroletype',
    'app/views/desktop/invoice/invoiceroletype/edit-invoiceroletype',
    'app/views/desktop/invoice/invoiceroletype/list-invoiceroletype',
    'app/models/invoice/invoiceterm/invoiceterm',
    'app/collections/invoice/invoiceterm/invoiceterm',
    'app/views/desktop/invoice/invoiceterm/edit-invoiceterm',
    'app/views/desktop/invoice/invoiceterm/list-invoiceterm',
    'app/models/invoice/orderitembilling/orderitembilling',
    'app/collections/invoice/orderitembilling/orderitembilling',
    'app/views/desktop/invoice/orderitembilling/edit-orderitembilling',
    'app/views/desktop/invoice/orderitembilling/list-orderitembilling',
    'app/models/product/productclassification/productclassification',
    'app/collections/product/productclassification/productclassification',
    'app/views/desktop/product/productclassification/edit-productclassification',
    'app/views/desktop/product/productclassification/list-productclassification',
    'app/models/product/producttype/producttype',
    'app/collections/product/producttype/producttype',
    'app/views/desktop/product/producttype/edit-producttype',
    'app/views/desktop/product/producttype/list-producttype',
    'app/models/product/costcomponenttype/costcomponenttype',
    'app/collections/product/costcomponenttype/costcomponenttype',
    'app/views/desktop/product/costcomponenttype/edit-costcomponenttype',
    'app/views/desktop/product/costcomponenttype/list-costcomponenttype',
    'app/models/product/productcategorytype/productcategorytype',
    'app/collections/product/productcategorytype/productcategorytype',
    'app/views/desktop/product/productcategorytype/edit-productcategorytype',
    'app/views/desktop/product/productcategorytype/list-productcategorytype',
    'app/models/product/productfeature/productfeature',
    'app/collections/product/productfeature/productfeature',
    'app/views/desktop/product/productfeature/edit-productfeature',
    'app/views/desktop/product/productfeature/list-productfeature',
    'app/models/product/productfeaturecategory/productfeaturecategory',
    'app/collections/product/productfeaturecategory/productfeaturecategory',
    'app/views/desktop/product/productfeaturecategory/edit-productfeaturecategory',
    'app/views/desktop/product/productfeaturecategory/list-productfeaturecategory',
    'app/models/product/productfeatureapplicability/productfeatureapplicability',
    'app/collections/product/productfeatureapplicability/productfeatureapplicability',
    'app/views/desktop/product/productfeatureapplicability/edit-productfeatureapplicability',
    'app/views/desktop/product/productfeatureapplicability/list-productfeatureapplicability',
    'app/models/product/product/product',
    'app/collections/product/product/product',
    'app/views/desktop/product/product/edit-product',
    'app/views/desktop/product/product/list-product',
    'app/models/product/productcomponent/productcomponent',
    'app/collections/product/productcomponent/productcomponent',
    'app/views/desktop/product/productcomponent/edit-productcomponent',
    'app/views/desktop/product/productcomponent/list-productcomponent',
    'app/models/product/productfeaturetype/productfeaturetype',
    'app/collections/product/productfeaturetype/productfeaturetype',
    'app/views/desktop/product/productfeaturetype/edit-productfeaturetype',
    'app/views/desktop/product/productfeaturetype/list-productfeaturetype',
    'app/models/product/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'app/collections/product/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'app/views/desktop/product/productfeatureapplicabilitytype/edit-productfeatureapplicabilitytype',
    'app/views/desktop/product/productfeatureapplicabilitytype/list-productfeatureapplicabilitytype',
    'app/models/product/productcategory/productcategory',
    'app/collections/product/productcategory/productcategory',
    'app/views/desktop/product/productcategory/edit-productcategory',
    'app/views/desktop/product/productcategory/list-productcategory',
    'app/models/customer/billingaccountroletype/billingaccountroletype',
    'app/collections/customer/billingaccountroletype/billingaccountroletype',
    'app/views/desktop/customer/billingaccountroletype/edit-billingaccountroletype',
    'app/views/desktop/customer/billingaccountroletype/list-billingaccountroletype',
    'app/models/customer/billingaccountrole/billingaccountrole',
    'app/collections/customer/billingaccountrole/billingaccountrole',
    'app/views/desktop/customer/billingaccountrole/edit-billingaccountrole',
    'app/views/desktop/customer/billingaccountrole/list-billingaccountrole',
    'app/models/customer/billingaccount/billingaccount',
    'app/collections/customer/billingaccount/billingaccount',
    'app/views/desktop/customer/billingaccount/edit-billingaccount',
    'app/views/desktop/customer/billingaccount/list-billingaccount',
    'app/models/customer/customerblacklist/customerblacklist',
    'app/collections/customer/customerblacklist/customerblacklist',
    'app/views/desktop/customer/customerblacklist/edit-customerblacklist',
    'app/views/desktop/customer/customerblacklist/list-customerblacklist',
    'app/models/customer/customer/customer',
    'app/collections/customer/customer/customer',
    'app/views/desktop/customer/customer/edit-customer',
    'app/views/desktop/customer/customer/list-customer',
    'app/models/businessdata/uomconversion/uomconversion',
    'app/collections/businessdata/uomconversion/uomconversion',
    'app/views/desktop/businessdata/uomconversion/edit-uomconversion',
    'app/views/desktop/businessdata/uomconversion/list-uomconversion',
    'app/models/businessdata/termtype/termtype',
    'app/collections/businessdata/termtype/termtype',
    'app/views/desktop/businessdata/termtype/edit-termtype',
    'app/views/desktop/businessdata/termtype/list-termtype',
    'app/models/businessdata/uom/uom',
    'app/collections/businessdata/uom/uom',
    'app/views/desktop/businessdata/uom/edit-uom',
    'app/views/desktop/businessdata/uom/list-uom',
    'app/models/businessdata/currency/currency',
    'app/collections/businessdata/currency/currency',
    'app/views/desktop/businessdata/currency/edit-currency',
    'app/views/desktop/businessdata/currency/list-currency',
    'app/models/businessdata/geoboundryassociation/geoboundryassociation',
    'app/collections/businessdata/geoboundryassociation/geoboundryassociation',
    'app/views/desktop/businessdata/geoboundryassociation/edit-geoboundryassociation',
    'app/views/desktop/businessdata/geoboundryassociation/list-geoboundryassociation',
    'app/models/businessdata/servicechannel/servicechannel',
    'app/collections/businessdata/servicechannel/servicechannel',
    'app/views/desktop/businessdata/servicechannel/edit-servicechannel',
    'app/views/desktop/businessdata/servicechannel/list-servicechannel',
    'app/models/businessdata/statustype/statustype',
    'app/collections/businessdata/statustype/statustype',
    'app/views/desktop/businessdata/statustype/edit-statustype',
    'app/views/desktop/businessdata/statustype/list-statustype',
    'app/models/businessdata/geoboundry/geoboundry',
    'app/collections/businessdata/geoboundry/geoboundry',
    'app/views/desktop/businessdata/geoboundry/edit-geoboundry',
    'app/views/desktop/businessdata/geoboundry/list-geoboundry',
    'app/models/businessdata/postaladdressboundry/postaladdressboundry',
    'app/collections/businessdata/postaladdressboundry/postaladdressboundry',
    'app/views/desktop/businessdata/postaladdressboundry/edit-postaladdressboundry',
    'app/views/desktop/businessdata/postaladdressboundry/list-postaladdressboundry',
    'app/models/businessdata/geoboundarytype/geoboundarytype',
    'app/collections/businessdata/geoboundarytype/geoboundarytype',
    'app/views/desktop/businessdata/geoboundarytype/edit-geoboundarytype',
    'app/views/desktop/businessdata/geoboundarytype/list-geoboundarytype',
    'text!../templates/desktop/main.html',
    'text!../templates/desktop/home/navbar.html',
    'text!../templates/desktop/home/footer.html',
    'text!../templates/desktop/home/sidebar.html',
    'text!../templates/desktop/home/subnavbar.html'
],function ($,
            _,
            config,
            utilities,
            LoginView,
            HomeView,
            CustomerHomeView,
            AdviceHomeView,
            ChannelHomeView,
            ProductHomeView,
            OrderHomeView,
            InvoiceHomeView,
            PaymentHomeView,
            MessagingHomeView,
            BusinessDataHomeView,
            ReportsHomeView,
            AdviceStatus,
            AdviceStatuss,
            AdviceStatusEditView,
            AdviceStatusListView,
            AdviceType,
            AdviceTypes,
            AdviceTypeEditView,
            AdviceTypeListView,
            AdviceTypeTag,
            AdviceTypeTags,
            AdviceTypeTagEditView,
            AdviceTypeTagListView,
            Advice,
            Advices,
            AdviceEditView,
            AdviceListView,
            Service,
            Services,
            ServiceEditView,
            ServiceListView,
            ServiceType,
            ServiceTypes,
            ServiceTypeEditView,
            ServiceTypeListView,
            ServiceCategory,
            ServiceCategorys,
            ServiceCategoryEditView,
            ServiceCategoryListView,
            ServiceMode,
            ServiceModes,
            ServiceModeEditView,
            ServiceModeListView,
            ServiceTransactionType,
            ServiceTransactionTypes,
            ServiceTransactionTypeEditView,
            ServiceTransactionTypeListView,
            HostType,
            HostTypes,
            HostTypeEditView,
            HostTypeListView,
            ServicePeer,
            ServicePeers,
            ServicePeerEditView,
            ServicePeerListView,
            ServiceTransaction,
            ServiceTransactions,
            ServiceTransactionEditView,
            ServiceTransactionListView,
            ServiceProtocolAdapter,
            ServiceProtocolAdapters,
            ServiceProtocolAdapterEditView,
            ServiceProtocolAdapterListView,
            Host,
            Hosts,
            HostEditView,
            HostListView,
            WorkEffort,
            WorkEfforts,
            WorkEffortEditView,
            WorkEffortListView,
            WorkEffortType,
            WorkEffortTypes,
            WorkEffortTypeEditView,
            WorkEffortTypeListView,
            ProductOrderItemType,
            ProductOrderItemTypes,
            ProductOrderItemTypeEditView,
            ProductOrderItemTypeListView,
            ProductOrder,
            ProductOrders,
            ProductOrderEditView,
            ProductOrderListView,
            ProductOrderItem,
            ProductOrderItems,
            ProductOrderItemEditView,
            ProductOrderItemListView,
            ProductOrderType,
            ProductOrderTypes,
            ProductOrderTypeEditView,
            ProductOrderTypeListView,
            CommunicationEventPurposeType,
            CommunicationEventPurposeTypes,
            CommunicationEventPurposeTypeEditView,
            CommunicationEventPurposeTypeListView,
            CommunicationEvent,
            CommunicationEvents,
            CommunicationEventEditView,
            CommunicationEventListView,
            CommunicationEventWorkEffort,
            CommunicationEventWorkEfforts,
            CommunicationEventWorkEffortEditView,
            CommunicationEventWorkEffortListView,
            CommunicationEventType,
            CommunicationEventTypes,
            CommunicationEventTypeEditView,
            CommunicationEventTypeListView,
            CommunicationEventPurpose,
            CommunicationEventPurposes,
            CommunicationEventPurposeEditView,
            CommunicationEventPurposeListView,
            CommunicationEventStatusType,
            CommunicationEventStatusTypes,
            CommunicationEventStatusTypeEditView,
            CommunicationEventStatusTypeListView,
            PaymentApplication,
            PaymentApplications,
            PaymentApplicationEditView,
            PaymentApplicationListView,
            PaymentMethodTypeProvider,
            PaymentMethodTypeProviders,
            PaymentMethodTypeProviderEditView,
            PaymentMethodTypeProviderListView,
            PaymentMethodType,
            PaymentMethodTypes,
            PaymentMethodTypeEditView,
            PaymentMethodTypeListView,
            Payment,
            Payments,
            PaymentEditView,
            PaymentListView,
            PaymentType,
            PaymentTypes,
            PaymentTypeEditView,
            PaymentTypeListView,
            PartyContactMechanismPurpose,
            PartyContactMechanismPurposes,
            PartyContactMechanismPurposeEditView,
            PartyContactMechanismPurposeListView,
            PartyRelationship,
            PartyRelationships,
            PartyRelationshipEditView,
            PartyRelationshipListView,
            ContactMechanismPurposeType,
            ContactMechanismPurposeTypes,
            ContactMechanismPurposeTypeEditView,
            ContactMechanismPurposeTypeListView,
            PostalAddress,
            PostalAddresss,
            PostalAddressEditView,
            PostalAddressListView,
            CaseRoleType,
            CaseRoleTypes,
            CaseRoleTypeEditView,
            CaseRoleTypeListView,
            PartyType,
            PartyTypes,
            PartyTypeEditView,
            PartyTypeListView,
            PartyCase,
            PartyCases,
            PartyCaseEditView,
            PartyCaseListView,
            ContactMechanismLink,
            ContactMechanismLinks,
            ContactMechanismLinkEditView,
            ContactMechanismLinkListView,
            RoleType,
            RoleTypes,
            RoleTypeEditView,
            RoleTypeListView,
            ContactMechanism,
            ContactMechanisms,
            ContactMechanismEditView,
            ContactMechanismListView,
            Person,
            Persons,
            PersonEditView,
            PersonListView,
            CaseStatusType,
            CaseStatusTypes,
            CaseStatusTypeEditView,
            CaseStatusTypeListView,
            PartyRelationshipType,
            PartyRelationshipTypes,
            PartyRelationshipTypeEditView,
            PartyRelationshipTypeListView,
            PartyRoleType,
            PartyRoleTypes,
            PartyRoleTypeEditView,
            PartyRoleTypeListView,
            ContactMechanismType,
            ContactMechanismTypes,
            ContactMechanismTypeEditView,
            ContactMechanismTypeListView,
            PartyClassification,
            PartyClassifications,
            PartyClassificationEditView,
            PartyClassificationListView,
            Party,
            Partys,
            PartyEditView,
            PartyListView,
            EstimatedProductCost,
            EstimatedProductCosts,
            EstimatedProductCostEditView,
            EstimatedProductCostListView,
            PartyRole,
            PartyRoles,
            PartyRoleEditView,
            PartyRoleListView,
            CaseRole,
            CaseRoles,
            CaseRoleEditView,
            CaseRoleListView,
            PartyClassificationType,
            PartyClassificationTypes,
            PartyClassificationTypeEditView,
            PartyClassificationTypeListView,
            Organization,
            Organizations,
            OrganizationEditView,
            OrganizationListView,
            TelecommunicationsNumber,
            TelecommunicationsNumbers,
            TelecommunicationsNumberEditView,
            TelecommunicationsNumberListView,
            PartyContactMechanism,
            PartyContactMechanisms,
            PartyContactMechanismEditView,
            PartyContactMechanismListView,
            ElectronicAddress,
            ElectronicAddresss,
            ElectronicAddressEditView,
            ElectronicAddressListView,
            InvoiceStatusType,
            InvoiceStatusTypes,
            InvoiceStatusTypeEditView,
            InvoiceStatusTypeListView,
            InvoiceItemCategory,
            InvoiceItemCategorys,
            InvoiceItemCategoryEditView,
            InvoiceItemCategoryListView,
            InvoiceStatus,
            InvoiceStatuss,
            InvoiceStatusEditView,
            InvoiceStatusListView,
            Invoice,
            Invoices,
            InvoiceEditView,
            InvoiceListView,
            InvoiceType,
            InvoiceTypes,
            InvoiceTypeEditView,
            InvoiceTypeListView,
            InvoiceItem,
            InvoiceItems,
            InvoiceItemEditView,
            InvoiceItemListView,
            InvoiceRole,
            InvoiceRoles,
            InvoiceRoleEditView,
            InvoiceRoleListView,
            InvoiceItemType,
            InvoiceItemTypes,
            InvoiceItemTypeEditView,
            InvoiceItemTypeListView,
            InvoiceRoleType,
            InvoiceRoleTypes,
            InvoiceRoleTypeEditView,
            InvoiceRoleTypeListView,
            InvoiceTerm,
            InvoiceTerms,
            InvoiceTermEditView,
            InvoiceTermListView,
            OrderItemBilling,
            OrderItemBillings,
            OrderItemBillingEditView,
            OrderItemBillingListView,
            ProductClassification,
            ProductClassifications,
            ProductClassificationEditView,
            ProductClassificationListView,
            ProductType,
            ProductTypes,
            ProductTypeEditView,
            ProductTypeListView,
            CostComponentType,
            CostComponentTypes,
            CostComponentTypeEditView,
            CostComponentTypeListView,
            ProductCategoryType,
            ProductCategoryTypes,
            ProductCategoryTypeEditView,
            ProductCategoryTypeListView,
            ProductFeature,
            ProductFeatures,
            ProductFeatureEditView,
            ProductFeatureListView,
            ProductFeatureCategory,
            ProductFeatureCategorys,
            ProductFeatureCategoryEditView,
            ProductFeatureCategoryListView,
            ProductFeatureApplicability,
            ProductFeatureApplicabilitys,
            ProductFeatureApplicabilityEditView,
            ProductFeatureApplicabilityListView,
            Product,
            Products,
            ProductEditView,
            ProductListView,
            ProductComponent,
            ProductComponents,
            ProductComponentEditView,
            ProductComponentListView,
            ProductFeatureType,
            ProductFeatureTypes,
            ProductFeatureTypeEditView,
            ProductFeatureTypeListView,
            ProductFeatureApplicabilityType,
            ProductFeatureApplicabilityTypes,
            ProductFeatureApplicabilityTypeEditView,
            ProductFeatureApplicabilityTypeListView,
            ProductCategory,
            ProductCategorys,
            ProductCategoryEditView,
            ProductCategoryListView,
            BillingAccountRoleType,
            BillingAccountRoleTypes,
            BillingAccountRoleTypeEditView,
            BillingAccountRoleTypeListView,
            BillingAccountRole,
            BillingAccountRoles,
            BillingAccountRoleEditView,
            BillingAccountRoleListView,
            BillingAccount,
            BillingAccounts,
            BillingAccountEditView,
            BillingAccountListView,
            CustomerBlacklist,
            CustomerBlacklists,
            CustomerBlacklistEditView,
            CustomerBlacklistListView,
            Customer,
            Customers,
            CustomerEditView,
            CustomerListView,
            UomConversion,
            UomConversions,
            UomConversionEditView,
            UomConversionListView,
            TermType,
            TermTypes,
            TermTypeEditView,
            TermTypeListView,
            Uom,
            Uoms,
            UomEditView,
            UomListView,
            Currency,
            Currencys,
            CurrencyEditView,
            CurrencyListView,
            GeoBoundryAssociation,
            GeoBoundryAssociations,
            GeoBoundryAssociationEditView,
            GeoBoundryAssociationListView,
            ServiceChannel,
            ServiceChannels,
            ServiceChannelEditView,
            ServiceChannelListView,
            StatusType,
            StatusTypes,
            StatusTypeEditView,
            StatusTypeListView,
            GeoBoundry,
            GeoBoundrys,
            GeoBoundryEditView,
            GeoBoundryListView,
            PostalAddressBoundry,
            PostalAddressBoundrys,
            PostalAddressBoundryEditView,
            PostalAddressBoundryListView,
            GeoBoundaryType,
            GeoBoundaryTypes,
            GeoBoundaryTypeEditView,
            GeoBoundaryTypeListView,
            MainTemplate, navBarTemplate, footerTemplate, 
            sideBarTemplate, subNavBarTemplate, homeContentTemplate) {

    $(document).ready(new function() 
    {
        var NavBarView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                utilities.applyTemplate($('#navbar-container'), navBarTemplate,  {});
                return this;
            },
            events:
            {
                'click .logout-link':'handleLogout'
            },
            handleLogout:function(event)
            {
                //event.preventDefault();
                console.log('>>>>>>>>>>>>>>Logout called');
            }
        });

        var SubNavBarView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                console.log('SubNavBar world');
                utilities.applyTemplate($('#subnavbar-container'), subNavBarTemplate,  {});
                return this;
            }
        });

        var SideBarView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                console.log('SideBarView world');
                utilities.applyTemplate($('#sidebar-container'), sideBarTemplate,  {});
                return this;
            }
        });

        var FooterView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                console.log('FooterView world');
                utilities.applyTemplate($('#footer-container'), footerTemplate,  {});
                return this;
            }
        });

        utilities.applyTemplate($('body'), MainTemplate)
        // Render NavBar
        navbarView = new NavBarView({el:$('#navbar-container')});
        navbarView.render();
        // Render SubNavBar
        subNavBarView = new SubNavBarView({el:$('#subnavbar-container')});
        subNavBarView.render();
        // Render SideBarView
        sideView = new SideBarView({el:$('#sidebar-container')});
        sideView.render();
        // Render SideBarView
        footerView = new FooterView({el:$('#footer-container')});
        footerView.render();
    });

    /**
     * The Router class contains all the routes within the application -
     * i.e. URLs and the actions that will be taken as a result.
     *
     * @type {Router}
     */

    var Router = Backbone.Router.extend({
        routes : {
            "":"login",
            "login":"login",
            "home":"home",
            "customer-module":"customerModuleIndex",
            "advice-module":"adviceModuleIndex",
            "channel-module":"channelModuleIndex",
            "product-module":"productModuleIndex",
            "order-module":"orderModuleIndex",
            "invoice-module":"invoiceModuleIndex",
            "payment-module":"paymentModuleIndex",
            "messaging-module":"messagingModuleIndex",
            "businessdata-module":"businessDataModuleIndex",
            "reports-module":"reportsModuleIndex",
            "list-advicestatus":"listAdviceStatus",
            "edit-advicestatus":"editAdviceStatus",
            "edit-advicestatus/:id":"editAdviceStatus",
            "list-advicetype":"listAdviceType",
            "edit-advicetype":"editAdviceType",
            "edit-advicetype/:id":"editAdviceType",
            "list-advicetypetag":"listAdviceTypeTag",
            "edit-advicetypetag":"editAdviceTypeTag",
            "edit-advicetypetag/:id":"editAdviceTypeTag",
            "list-advice":"listAdvice",
            "edit-advice":"editAdvice",
            "edit-advice/:id":"editAdvice",
            "list-service":"listService",
            "edit-service":"editService",
            "edit-service/:id":"editService",
            "list-servicetype":"listServiceType",
            "edit-servicetype":"editServiceType",
            "edit-servicetype/:id":"editServiceType",
            "list-servicecategory":"listServiceCategory",
            "edit-servicecategory":"editServiceCategory",
            "edit-servicecategory/:id":"editServiceCategory",
            "list-servicemode":"listServiceMode",
            "edit-servicemode":"editServiceMode",
            "edit-servicemode/:id":"editServiceMode",
            "list-servicetransactiontype":"listServiceTransactionType",
            "edit-servicetransactiontype":"editServiceTransactionType",
            "edit-servicetransactiontype/:id":"editServiceTransactionType",
            "list-hosttype":"listHostType",
            "edit-hosttype":"editHostType",
            "edit-hosttype/:id":"editHostType",
            "list-servicepeer":"listServicePeer",
            "edit-servicepeer":"editServicePeer",
            "edit-servicepeer/:id":"editServicePeer",
            "list-servicetransaction":"listServiceTransaction",
            "edit-servicetransaction":"editServiceTransaction",
            "edit-servicetransaction/:id":"editServiceTransaction",
            "list-serviceprotocoladapter":"listServiceProtocolAdapter",
            "edit-serviceprotocoladapter":"editServiceProtocolAdapter",
            "edit-serviceprotocoladapter/:id":"editServiceProtocolAdapter",
            "list-host":"listHost",
            "edit-host":"editHost",
            "edit-host/:id":"editHost",
            "list-workeffort":"listWorkEffort",
            "edit-workeffort":"editWorkEffort",
            "edit-workeffort/:id":"editWorkEffort",
            "list-workefforttype":"listWorkEffortType",
            "edit-workefforttype":"editWorkEffortType",
            "edit-workefforttype/:id":"editWorkEffortType",
            "list-productorderitemtype":"listProductOrderItemType",
            "edit-productorderitemtype":"editProductOrderItemType",
            "edit-productorderitemtype/:id":"editProductOrderItemType",
            "list-productorder":"listProductOrder",
            "edit-productorder":"editProductOrder",
            "edit-productorder/:id":"editProductOrder",
            "list-productorderitem":"listProductOrderItem",
            "edit-productorderitem":"editProductOrderItem",
            "edit-productorderitem/:id":"editProductOrderItem",
            "list-productordertype":"listProductOrderType",
            "edit-productordertype":"editProductOrderType",
            "edit-productordertype/:id":"editProductOrderType",
            "list-communicationeventpurposetype":"listCommunicationEventPurposeType",
            "edit-communicationeventpurposetype":"editCommunicationEventPurposeType",
            "edit-communicationeventpurposetype/:id":"editCommunicationEventPurposeType",
            "list-communicationevent":"listCommunicationEvent",
            "edit-communicationevent":"editCommunicationEvent",
            "edit-communicationevent/:id":"editCommunicationEvent",
            "list-communicationeventworkeffort":"listCommunicationEventWorkEffort",
            "edit-communicationeventworkeffort":"editCommunicationEventWorkEffort",
            "edit-communicationeventworkeffort/:id":"editCommunicationEventWorkEffort",
            "list-communicationeventtype":"listCommunicationEventType",
            "edit-communicationeventtype":"editCommunicationEventType",
            "edit-communicationeventtype/:id":"editCommunicationEventType",
            "list-communicationeventpurpose":"listCommunicationEventPurpose",
            "edit-communicationeventpurpose":"editCommunicationEventPurpose",
            "edit-communicationeventpurpose/:id":"editCommunicationEventPurpose",
            "list-communicationeventstatustype":"listCommunicationEventStatusType",
            "edit-communicationeventstatustype":"editCommunicationEventStatusType",
            "edit-communicationeventstatustype/:id":"editCommunicationEventStatusType",
            "list-paymentapplication":"listPaymentApplication",
            "edit-paymentapplication":"editPaymentApplication",
            "edit-paymentapplication/:id":"editPaymentApplication",
            "list-paymentmethodtypeprovider":"listPaymentMethodTypeProvider",
            "edit-paymentmethodtypeprovider":"editPaymentMethodTypeProvider",
            "edit-paymentmethodtypeprovider/:id":"editPaymentMethodTypeProvider",
            "list-paymentmethodtype":"listPaymentMethodType",
            "edit-paymentmethodtype":"editPaymentMethodType",
            "edit-paymentmethodtype/:id":"editPaymentMethodType",
            "list-payment":"listPayment",
            "edit-payment":"editPayment",
            "edit-payment/:id":"editPayment",
            "list-paymenttype":"listPaymentType",
            "edit-paymenttype":"editPaymentType",
            "edit-paymenttype/:id":"editPaymentType",
            "list-partycontactmechanismpurpose":"listPartyContactMechanismPurpose",
            "edit-partycontactmechanismpurpose":"editPartyContactMechanismPurpose",
            "edit-partycontactmechanismpurpose/:id":"editPartyContactMechanismPurpose",
            "list-partyrelationship":"listPartyRelationship",
            "edit-partyrelationship":"editPartyRelationship",
            "edit-partyrelationship/:id":"editPartyRelationship",
            "list-contactmechanismpurposetype":"listContactMechanismPurposeType",
            "edit-contactmechanismpurposetype":"editContactMechanismPurposeType",
            "edit-contactmechanismpurposetype/:id":"editContactMechanismPurposeType",
            "list-postaladdress":"listPostalAddress",
            "edit-postaladdress":"editPostalAddress",
            "edit-postaladdress/:id":"editPostalAddress",
            "list-caseroletype":"listCaseRoleType",
            "edit-caseroletype":"editCaseRoleType",
            "edit-caseroletype/:id":"editCaseRoleType",
            "list-partytype":"listPartyType",
            "edit-partytype":"editPartyType",
            "edit-partytype/:id":"editPartyType",
            "list-partycase":"listPartyCase",
            "edit-partycase":"editPartyCase",
            "edit-partycase/:id":"editPartyCase",
            "list-contactmechanismlink":"listContactMechanismLink",
            "edit-contactmechanismlink":"editContactMechanismLink",
            "edit-contactmechanismlink/:id":"editContactMechanismLink",
            "list-roletype":"listRoleType",
            "edit-roletype":"editRoleType",
            "edit-roletype/:id":"editRoleType",
            "list-contactmechanism":"listContactMechanism",
            "edit-contactmechanism":"editContactMechanism",
            "edit-contactmechanism/:id":"editContactMechanism",
            "list-person":"listPerson",
            "edit-person":"editPerson",
            "edit-person/:id":"editPerson",
            "list-casestatustype":"listCaseStatusType",
            "edit-casestatustype":"editCaseStatusType",
            "edit-casestatustype/:id":"editCaseStatusType",
            "list-partyrelationshiptype":"listPartyRelationshipType",
            "edit-partyrelationshiptype":"editPartyRelationshipType",
            "edit-partyrelationshiptype/:id":"editPartyRelationshipType",
            "list-partyroletype":"listPartyRoleType",
            "edit-partyroletype":"editPartyRoleType",
            "edit-partyroletype/:id":"editPartyRoleType",
            "list-contactmechanismtype":"listContactMechanismType",
            "edit-contactmechanismtype":"editContactMechanismType",
            "edit-contactmechanismtype/:id":"editContactMechanismType",
            "list-partyclassification":"listPartyClassification",
            "edit-partyclassification":"editPartyClassification",
            "edit-partyclassification/:id":"editPartyClassification",
            "list-party":"listParty",
            "edit-party":"editParty",
            "edit-party/:id":"editParty",
            "list-estimatedproductcost":"listEstimatedProductCost",
            "edit-estimatedproductcost":"editEstimatedProductCost",
            "edit-estimatedproductcost/:id":"editEstimatedProductCost",
            "list-partyrole":"listPartyRole",
            "edit-partyrole":"editPartyRole",
            "edit-partyrole/:id":"editPartyRole",
            "list-caserole":"listCaseRole",
            "edit-caserole":"editCaseRole",
            "edit-caserole/:id":"editCaseRole",
            "list-partyclassificationtype":"listPartyClassificationType",
            "edit-partyclassificationtype":"editPartyClassificationType",
            "edit-partyclassificationtype/:id":"editPartyClassificationType",
            "list-organization":"listOrganization",
            "edit-organization":"editOrganization",
            "edit-organization/:id":"editOrganization",
            "list-telecommunicationsnumber":"listTelecommunicationsNumber",
            "edit-telecommunicationsnumber":"editTelecommunicationsNumber",
            "edit-telecommunicationsnumber/:id":"editTelecommunicationsNumber",
            "list-partycontactmechanism":"listPartyContactMechanism",
            "edit-partycontactmechanism":"editPartyContactMechanism",
            "edit-partycontactmechanism/:id":"editPartyContactMechanism",
            "list-electronicaddress":"listElectronicAddress",
            "edit-electronicaddress":"editElectronicAddress",
            "edit-electronicaddress/:id":"editElectronicAddress",
            "list-invoicestatustype":"listInvoiceStatusType",
            "edit-invoicestatustype":"editInvoiceStatusType",
            "edit-invoicestatustype/:id":"editInvoiceStatusType",
            "list-invoiceitemcategory":"listInvoiceItemCategory",
            "edit-invoiceitemcategory":"editInvoiceItemCategory",
            "edit-invoiceitemcategory/:id":"editInvoiceItemCategory",
            "list-invoicestatus":"listInvoiceStatus",
            "edit-invoicestatus":"editInvoiceStatus",
            "edit-invoicestatus/:id":"editInvoiceStatus",
            "list-invoice":"listInvoice",
            "edit-invoice":"editInvoice",
            "edit-invoice/:id":"editInvoice",
            "list-invoicetype":"listInvoiceType",
            "edit-invoicetype":"editInvoiceType",
            "edit-invoicetype/:id":"editInvoiceType",
            "list-invoiceitem":"listInvoiceItem",
            "edit-invoiceitem":"editInvoiceItem",
            "edit-invoiceitem/:id":"editInvoiceItem",
            "list-invoicerole":"listInvoiceRole",
            "edit-invoicerole":"editInvoiceRole",
            "edit-invoicerole/:id":"editInvoiceRole",
            "list-invoiceitemtype":"listInvoiceItemType",
            "edit-invoiceitemtype":"editInvoiceItemType",
            "edit-invoiceitemtype/:id":"editInvoiceItemType",
            "list-invoiceroletype":"listInvoiceRoleType",
            "edit-invoiceroletype":"editInvoiceRoleType",
            "edit-invoiceroletype/:id":"editInvoiceRoleType",
            "list-invoiceterm":"listInvoiceTerm",
            "edit-invoiceterm":"editInvoiceTerm",
            "edit-invoiceterm/:id":"editInvoiceTerm",
            "list-orderitembilling":"listOrderItemBilling",
            "edit-orderitembilling":"editOrderItemBilling",
            "edit-orderitembilling/:id":"editOrderItemBilling",
            "list-productclassification":"listProductClassification",
            "edit-productclassification":"editProductClassification",
            "edit-productclassification/:id":"editProductClassification",
            "list-producttype":"listProductType",
            "edit-producttype":"editProductType",
            "edit-producttype/:id":"editProductType",
            "list-costcomponenttype":"listCostComponentType",
            "edit-costcomponenttype":"editCostComponentType",
            "edit-costcomponenttype/:id":"editCostComponentType",
            "list-productcategorytype":"listProductCategoryType",
            "edit-productcategorytype":"editProductCategoryType",
            "edit-productcategorytype/:id":"editProductCategoryType",
            "list-productfeature":"listProductFeature",
            "edit-productfeature":"editProductFeature",
            "edit-productfeature/:id":"editProductFeature",
            "list-productfeaturecategory":"listProductFeatureCategory",
            "edit-productfeaturecategory":"editProductFeatureCategory",
            "edit-productfeaturecategory/:id":"editProductFeatureCategory",
            "list-productfeatureapplicability":"listProductFeatureApplicability",
            "edit-productfeatureapplicability":"editProductFeatureApplicability",
            "edit-productfeatureapplicability/:id":"editProductFeatureApplicability",
            "list-product":"listProduct",
            "edit-product":"editProduct",
            "edit-product/:id":"editProduct",
            "list-productcomponent":"listProductComponent",
            "edit-productcomponent":"editProductComponent",
            "edit-productcomponent/:id":"editProductComponent",
            "list-productfeaturetype":"listProductFeatureType",
            "edit-productfeaturetype":"editProductFeatureType",
            "edit-productfeaturetype/:id":"editProductFeatureType",
            "list-productfeatureapplicabilitytype":"listProductFeatureApplicabilityType",
            "edit-productfeatureapplicabilitytype":"editProductFeatureApplicabilityType",
            "edit-productfeatureapplicabilitytype/:id":"editProductFeatureApplicabilityType",
            "list-productcategory":"listProductCategory",
            "edit-productcategory":"editProductCategory",
            "edit-productcategory/:id":"editProductCategory",
            "list-billingaccountroletype":"listBillingAccountRoleType",
            "edit-billingaccountroletype":"editBillingAccountRoleType",
            "edit-billingaccountroletype/:id":"editBillingAccountRoleType",
            "list-billingaccountrole":"listBillingAccountRole",
            "edit-billingaccountrole":"editBillingAccountRole",
            "edit-billingaccountrole/:id":"editBillingAccountRole",
            "list-billingaccount":"listBillingAccount",
            "edit-billingaccount":"editBillingAccount",
            "edit-billingaccount/:id":"editBillingAccount",
            "list-customerblacklist":"listCustomerBlacklist",
            "edit-customerblacklist":"editCustomerBlacklist",
            "edit-customerblacklist/:id":"editCustomerBlacklist",
            "list-customer":"listCustomer",
            "edit-customer":"editCustomer",
            "edit-customer/:id":"editCustomer",
            "list-uomconversion":"listUomConversion",
            "edit-uomconversion":"editUomConversion",
            "edit-uomconversion/:id":"editUomConversion",
            "list-termtype":"listTermType",
            "edit-termtype":"editTermType",
            "edit-termtype/:id":"editTermType",
            "list-uom":"listUom",
            "edit-uom":"editUom",
            "edit-uom/:id":"editUom",
            "list-currency":"listCurrency",
            "edit-currency":"editCurrency",
            "edit-currency/:id":"editCurrency",
            "list-geoboundryassociation":"listGeoBoundryAssociation",
            "edit-geoboundryassociation":"editGeoBoundryAssociation",
            "edit-geoboundryassociation/:id":"editGeoBoundryAssociation",
            "list-servicechannel":"listServiceChannel",
            "edit-servicechannel":"editServiceChannel",
            "edit-servicechannel/:id":"editServiceChannel",
            "list-statustype":"listStatusType",
            "edit-statustype":"editStatusType",
            "edit-statustype/:id":"editStatusType",
            "list-geoboundry":"listGeoBoundry",
            "edit-geoboundry":"editGeoBoundry",
            "edit-geoboundry/:id":"editGeoBoundry",
            "list-postaladdressboundry":"listPostalAddressBoundry",
            "edit-postaladdressboundry":"editPostalAddressBoundry",
            "edit-postaladdressboundry/:id":"editPostalAddressBoundry",
            "list-geoboundarytype":"listGeoBoundaryType",
            "edit-geoboundarytype":"editGeoBoundaryType",
            "edit-geoboundarytype/:id":"editGeoBoundaryType",
            "about":"home"
        },
        home : function () {
            utilities.viewManager.showView(new HomeView({el:$("#content-container")}));
        },
        login:function()
        {
            var loginView = new LoginView({el:$("#page-container")});
            utilities.viewManager.showView(loginView);

        },
        listAdviceStatus:function()
        {
            var model = new AdviceStatuss();
            var adviceStatusListView = new AdviceStatusListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(adviceStatusListView);
            }).fetch();
        },
        editAdviceStatus:function(id)
        {
            var model = new AdviceStatus({id:id});
            var adviceStatusEditView = new AdviceStatusEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(adviceStatusEditView);
        },
        listAdviceType:function()
        {
            var model = new AdviceTypes();
            var adviceTypeListView = new AdviceTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(adviceTypeListView);
            }).fetch();
        },
        editAdviceType:function(id)
        {
            var model = new AdviceType({id:id});
            var adviceTypeEditView = new AdviceTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(adviceTypeEditView);
        },
        listAdviceTypeTag:function()
        {
            var model = new AdviceTypeTags();
            var adviceTypeTagListView = new AdviceTypeTagListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(adviceTypeTagListView);
            }).fetch();
        },
        editAdviceTypeTag:function(id)
        {
            var model = new AdviceTypeTag({id:id});
            var adviceTypeTagEditView = new AdviceTypeTagEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(adviceTypeTagEditView);
        },
        listAdvice:function()
        {
            var model = new Advices();
            var adviceListView = new AdviceListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(adviceListView);
            }).fetch();
        },
        editAdvice:function(id)
        {
            var model = new Advice({id:id});
            var adviceEditView = new AdviceEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(adviceEditView);
        },
        listService:function()
        {
            var model = new Services();
            var serviceListView = new ServiceListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceListView);
            }).fetch();
        },
        editService:function(id)
        {
            var model = new Service({id:id});
            var serviceEditView = new ServiceEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceEditView);
        },
        listServiceType:function()
        {
            var model = new ServiceTypes();
            var serviceTypeListView = new ServiceTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceTypeListView);
            }).fetch();
        },
        editServiceType:function(id)
        {
            var model = new ServiceType({id:id});
            var serviceTypeEditView = new ServiceTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceTypeEditView);
        },
        listServiceCategory:function()
        {
            var model = new ServiceCategorys();
            var serviceCategoryListView = new ServiceCategoryListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceCategoryListView);
            }).fetch();
        },
        editServiceCategory:function(id)
        {
            var model = new ServiceCategory({id:id});
            var serviceCategoryEditView = new ServiceCategoryEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceCategoryEditView);
        },
        listServiceMode:function()
        {
            var model = new ServiceModes();
            var serviceModeListView = new ServiceModeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceModeListView);
            }).fetch();
        },
        editServiceMode:function(id)
        {
            var model = new ServiceMode({id:id});
            var serviceModeEditView = new ServiceModeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceModeEditView);
        },
        listServiceTransactionType:function()
        {
            var model = new ServiceTransactionTypes();
            var serviceTransactionTypeListView = new ServiceTransactionTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceTransactionTypeListView);
            }).fetch();
        },
        editServiceTransactionType:function(id)
        {
            var model = new ServiceTransactionType({id:id});
            var serviceTransactionTypeEditView = new ServiceTransactionTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceTransactionTypeEditView);
        },
        listHostType:function()
        {
            var model = new HostTypes();
            var hostTypeListView = new HostTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(hostTypeListView);
            }).fetch();
        },
        editHostType:function(id)
        {
            var model = new HostType({id:id});
            var hostTypeEditView = new HostTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(hostTypeEditView);
        },
        listServicePeer:function()
        {
            var model = new ServicePeers();
            var servicePeerListView = new ServicePeerListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(servicePeerListView);
            }).fetch();
        },
        editServicePeer:function(id)
        {
            var model = new ServicePeer({id:id});
            var servicePeerEditView = new ServicePeerEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(servicePeerEditView);
        },
        listServiceTransaction:function()
        {
            var model = new ServiceTransactions();
            var serviceTransactionListView = new ServiceTransactionListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceTransactionListView);
            }).fetch();
        },
        editServiceTransaction:function(id)
        {
            var model = new ServiceTransaction({id:id});
            var serviceTransactionEditView = new ServiceTransactionEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceTransactionEditView);
        },
        listServiceProtocolAdapter:function()
        {
            var model = new ServiceProtocolAdapters();
            var serviceProtocolAdapterListView = new ServiceProtocolAdapterListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceProtocolAdapterListView);
            }).fetch();
        },
        editServiceProtocolAdapter:function(id)
        {
            var model = new ServiceProtocolAdapter({id:id});
            var serviceProtocolAdapterEditView = new ServiceProtocolAdapterEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceProtocolAdapterEditView);
        },
        listHost:function()
        {
            var model = new Hosts();
            var hostListView = new HostListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(hostListView);
            }).fetch();
        },
        editHost:function(id)
        {
            var model = new Host({id:id});
            var hostEditView = new HostEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(hostEditView);
        },
        listWorkEffort:function()
        {
            var model = new WorkEfforts();
            var workEffortListView = new WorkEffortListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(workEffortListView);
            }).fetch();
        },
        editWorkEffort:function(id)
        {
            var model = new WorkEffort({id:id});
            var workEffortEditView = new WorkEffortEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(workEffortEditView);
        },
        listWorkEffortType:function()
        {
            var model = new WorkEffortTypes();
            var workEffortTypeListView = new WorkEffortTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(workEffortTypeListView);
            }).fetch();
        },
        editWorkEffortType:function(id)
        {
            var model = new WorkEffortType({id:id});
            var workEffortTypeEditView = new WorkEffortTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(workEffortTypeEditView);
        },
        listProductOrderItemType:function()
        {
            var model = new ProductOrderItemTypes();
            var productOrderItemTypeListView = new ProductOrderItemTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productOrderItemTypeListView);
            }).fetch();
        },
        editProductOrderItemType:function(id)
        {
            var model = new ProductOrderItemType({id:id});
            var productOrderItemTypeEditView = new ProductOrderItemTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productOrderItemTypeEditView);
        },
        listProductOrder:function()
        {
            var model = new ProductOrders();
            var productOrderListView = new ProductOrderListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productOrderListView);
            }).fetch();
        },
        editProductOrder:function(id)
        {
            var model = new ProductOrder({id:id});
            var productOrderEditView = new ProductOrderEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productOrderEditView);
        },
        listProductOrderItem:function()
        {
            var model = new ProductOrderItems();
            var productOrderItemListView = new ProductOrderItemListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productOrderItemListView);
            }).fetch();
        },
        editProductOrderItem:function(id)
        {
            var model = new ProductOrderItem({id:id});
            var productOrderItemEditView = new ProductOrderItemEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productOrderItemEditView);
        },
        listProductOrderType:function()
        {
            var model = new ProductOrderTypes();
            var productOrderTypeListView = new ProductOrderTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productOrderTypeListView);
            }).fetch();
        },
        editProductOrderType:function(id)
        {
            var model = new ProductOrderType({id:id});
            var productOrderTypeEditView = new ProductOrderTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productOrderTypeEditView);
        },
        listCommunicationEventPurposeType:function()
        {
            var model = new CommunicationEventPurposeTypes();
            var communicationEventPurposeTypeListView = new CommunicationEventPurposeTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(communicationEventPurposeTypeListView);
            }).fetch();
        },
        editCommunicationEventPurposeType:function(id)
        {
            var model = new CommunicationEventPurposeType({id:id});
            var communicationEventPurposeTypeEditView = new CommunicationEventPurposeTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(communicationEventPurposeTypeEditView);
        },
        listCommunicationEvent:function()
        {
            var model = new CommunicationEvents();
            var communicationEventListView = new CommunicationEventListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(communicationEventListView);
            }).fetch();
        },
        editCommunicationEvent:function(id)
        {
            var model = new CommunicationEvent({id:id});
            var communicationEventEditView = new CommunicationEventEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(communicationEventEditView);
        },
        listCommunicationEventWorkEffort:function()
        {
            var model = new CommunicationEventWorkEfforts();
            var communicationEventWorkEffortListView = new CommunicationEventWorkEffortListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(communicationEventWorkEffortListView);
            }).fetch();
        },
        editCommunicationEventWorkEffort:function(id)
        {
            var model = new CommunicationEventWorkEffort({id:id});
            var communicationEventWorkEffortEditView = new CommunicationEventWorkEffortEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(communicationEventWorkEffortEditView);
        },
        listCommunicationEventType:function()
        {
            var model = new CommunicationEventTypes();
            var communicationEventTypeListView = new CommunicationEventTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(communicationEventTypeListView);
            }).fetch();
        },
        editCommunicationEventType:function(id)
        {
            var model = new CommunicationEventType({id:id});
            var communicationEventTypeEditView = new CommunicationEventTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(communicationEventTypeEditView);
        },
        listCommunicationEventPurpose:function()
        {
            var model = new CommunicationEventPurposes();
            var communicationEventPurposeListView = new CommunicationEventPurposeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(communicationEventPurposeListView);
            }).fetch();
        },
        editCommunicationEventPurpose:function(id)
        {
            var model = new CommunicationEventPurpose({id:id});
            var communicationEventPurposeEditView = new CommunicationEventPurposeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(communicationEventPurposeEditView);
        },
        listCommunicationEventStatusType:function()
        {
            var model = new CommunicationEventStatusTypes();
            var communicationEventStatusTypeListView = new CommunicationEventStatusTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(communicationEventStatusTypeListView);
            }).fetch();
        },
        editCommunicationEventStatusType:function(id)
        {
            var model = new CommunicationEventStatusType({id:id});
            var communicationEventStatusTypeEditView = new CommunicationEventStatusTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(communicationEventStatusTypeEditView);
        },
        listPaymentApplication:function()
        {
            var model = new PaymentApplications();
            var paymentApplicationListView = new PaymentApplicationListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(paymentApplicationListView);
            }).fetch();
        },
        editPaymentApplication:function(id)
        {
            var model = new PaymentApplication({id:id});
            var paymentApplicationEditView = new PaymentApplicationEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(paymentApplicationEditView);
        },
        listPaymentMethodTypeProvider:function()
        {
            var model = new PaymentMethodTypeProviders();
            var paymentMethodTypeProviderListView = new PaymentMethodTypeProviderListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(paymentMethodTypeProviderListView);
            }).fetch();
        },
        editPaymentMethodTypeProvider:function(id)
        {
            var model = new PaymentMethodTypeProvider({id:id});
            var paymentMethodTypeProviderEditView = new PaymentMethodTypeProviderEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(paymentMethodTypeProviderEditView);
        },
        listPaymentMethodType:function()
        {
            var model = new PaymentMethodTypes();
            var paymentMethodTypeListView = new PaymentMethodTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(paymentMethodTypeListView);
            }).fetch();
        },
        editPaymentMethodType:function(id)
        {
            var model = new PaymentMethodType({id:id});
            var paymentMethodTypeEditView = new PaymentMethodTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(paymentMethodTypeEditView);
        },
        listPayment:function()
        {
            var model = new Payments();
            var paymentListView = new PaymentListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(paymentListView);
            }).fetch();
        },
        editPayment:function(id)
        {
            var model = new Payment({id:id});
            var paymentEditView = new PaymentEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(paymentEditView);
        },
        listPaymentType:function()
        {
            var model = new PaymentTypes();
            var paymentTypeListView = new PaymentTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(paymentTypeListView);
            }).fetch();
        },
        editPaymentType:function(id)
        {
            var model = new PaymentType({id:id});
            var paymentTypeEditView = new PaymentTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(paymentTypeEditView);
        },
        listPartyContactMechanismPurpose:function()
        {
            var model = new PartyContactMechanismPurposes();
            var partyContactMechanismPurposeListView = new PartyContactMechanismPurposeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyContactMechanismPurposeListView);
            }).fetch();
        },
        editPartyContactMechanismPurpose:function(id)
        {
            var model = new PartyContactMechanismPurpose({id:id});
            var partyContactMechanismPurposeEditView = new PartyContactMechanismPurposeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyContactMechanismPurposeEditView);
        },
        listPartyRelationship:function()
        {
            var model = new PartyRelationships();
            var partyRelationshipListView = new PartyRelationshipListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyRelationshipListView);
            }).fetch();
        },
        editPartyRelationship:function(id)
        {
            var model = new PartyRelationship({id:id});
            var partyRelationshipEditView = new PartyRelationshipEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyRelationshipEditView);
        },
        listContactMechanismPurposeType:function()
        {
            var model = new ContactMechanismPurposeTypes();
            var contactMechanismPurposeTypeListView = new ContactMechanismPurposeTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(contactMechanismPurposeTypeListView);
            }).fetch();
        },
        editContactMechanismPurposeType:function(id)
        {
            var model = new ContactMechanismPurposeType({id:id});
            var contactMechanismPurposeTypeEditView = new ContactMechanismPurposeTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(contactMechanismPurposeTypeEditView);
        },
        listPostalAddress:function()
        {
            var model = new PostalAddresss();
            var postalAddressListView = new PostalAddressListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(postalAddressListView);
            }).fetch();
        },
        editPostalAddress:function(id)
        {
            var model = new PostalAddress({id:id});
            var postalAddressEditView = new PostalAddressEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(postalAddressEditView);
        },
        listCaseRoleType:function()
        {
            var model = new CaseRoleTypes();
            var caseRoleTypeListView = new CaseRoleTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(caseRoleTypeListView);
            }).fetch();
        },
        editCaseRoleType:function(id)
        {
            var model = new CaseRoleType({id:id});
            var caseRoleTypeEditView = new CaseRoleTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(caseRoleTypeEditView);
        },
        listPartyType:function()
        {
            var model = new PartyTypes();
            var partyTypeListView = new PartyTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyTypeListView);
            }).fetch();
        },
        editPartyType:function(id)
        {
            var model = new PartyType({id:id});
            var partyTypeEditView = new PartyTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyTypeEditView);
        },
        listPartyCase:function()
        {
            var model = new PartyCases();
            var partyCaseListView = new PartyCaseListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyCaseListView);
            }).fetch();
        },
        editPartyCase:function(id)
        {
            var model = new PartyCase({id:id});
            var partyCaseEditView = new PartyCaseEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyCaseEditView);
        },
        listContactMechanismLink:function()
        {
            var model = new ContactMechanismLinks();
            var contactMechanismLinkListView = new ContactMechanismLinkListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(contactMechanismLinkListView);
            }).fetch();
        },
        editContactMechanismLink:function(id)
        {
            var model = new ContactMechanismLink({id:id});
            var contactMechanismLinkEditView = new ContactMechanismLinkEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(contactMechanismLinkEditView);
        },
        listRoleType:function()
        {
            var model = new RoleTypes();
            var roleTypeListView = new RoleTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(roleTypeListView);
            }).fetch();
        },
        editRoleType:function(id)
        {
            var model = new RoleType({id:id});
            var roleTypeEditView = new RoleTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(roleTypeEditView);
        },
        listContactMechanism:function()
        {
            var model = new ContactMechanisms();
            var contactMechanismListView = new ContactMechanismListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(contactMechanismListView);
            }).fetch();
        },
        editContactMechanism:function(id)
        {
            var model = new ContactMechanism({id:id});
            var contactMechanismEditView = new ContactMechanismEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(contactMechanismEditView);
        },
        listPerson:function()
        {
            var model = new Persons();
            var personListView = new PersonListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(personListView);
            }).fetch();
        },
        editPerson:function(id)
        {
            var model = new Person({id:id});
            var personEditView = new PersonEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(personEditView);
        },
        listCaseStatusType:function()
        {
            var model = new CaseStatusTypes();
            var caseStatusTypeListView = new CaseStatusTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(caseStatusTypeListView);
            }).fetch();
        },
        editCaseStatusType:function(id)
        {
            var model = new CaseStatusType({id:id});
            var caseStatusTypeEditView = new CaseStatusTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(caseStatusTypeEditView);
        },
        listPartyRelationshipType:function()
        {
            var model = new PartyRelationshipTypes();
            var partyRelationshipTypeListView = new PartyRelationshipTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyRelationshipTypeListView);
            }).fetch();
        },
        editPartyRelationshipType:function(id)
        {
            var model = new PartyRelationshipType({id:id});
            var partyRelationshipTypeEditView = new PartyRelationshipTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyRelationshipTypeEditView);
        },
        listPartyRoleType:function()
        {
            var model = new PartyRoleTypes();
            var partyRoleTypeListView = new PartyRoleTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyRoleTypeListView);
            }).fetch();
        },
        editPartyRoleType:function(id)
        {
            var model = new PartyRoleType({id:id});
            var partyRoleTypeEditView = new PartyRoleTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyRoleTypeEditView);
        },
        listContactMechanismType:function()
        {
            var model = new ContactMechanismTypes();
            var contactMechanismTypeListView = new ContactMechanismTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(contactMechanismTypeListView);
            }).fetch();
        },
        editContactMechanismType:function(id)
        {
            var model = new ContactMechanismType({id:id});
            var contactMechanismTypeEditView = new ContactMechanismTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(contactMechanismTypeEditView);
        },
        listPartyClassification:function()
        {
            var model = new PartyClassifications();
            var partyClassificationListView = new PartyClassificationListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyClassificationListView);
            }).fetch();
        },
        editPartyClassification:function(id)
        {
            var model = new PartyClassification({id:id});
            var partyClassificationEditView = new PartyClassificationEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyClassificationEditView);
        },
        listParty:function()
        {
            var model = new Partys();
            var partyListView = new PartyListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyListView);
            }).fetch();
        },
        editParty:function(id)
        {
            var model = new Party({id:id});
            var partyEditView = new PartyEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyEditView);
        },
        listEstimatedProductCost:function()
        {
            var model = new EstimatedProductCosts();
            var estimatedProductCostListView = new EstimatedProductCostListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(estimatedProductCostListView);
            }).fetch();
        },
        editEstimatedProductCost:function(id)
        {
            var model = new EstimatedProductCost({id:id});
            var estimatedProductCostEditView = new EstimatedProductCostEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(estimatedProductCostEditView);
        },
        listPartyRole:function()
        {
            var model = new PartyRoles();
            var partyRoleListView = new PartyRoleListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyRoleListView);
            }).fetch();
        },
        editPartyRole:function(id)
        {
            var model = new PartyRole({id:id});
            var partyRoleEditView = new PartyRoleEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyRoleEditView);
        },
        listCaseRole:function()
        {
            var model = new CaseRoles();
            var caseRoleListView = new CaseRoleListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(caseRoleListView);
            }).fetch();
        },
        editCaseRole:function(id)
        {
            var model = new CaseRole({id:id});
            var caseRoleEditView = new CaseRoleEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(caseRoleEditView);
        },
        listPartyClassificationType:function()
        {
            var model = new PartyClassificationTypes();
            var partyClassificationTypeListView = new PartyClassificationTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyClassificationTypeListView);
            }).fetch();
        },
        editPartyClassificationType:function(id)
        {
            var model = new PartyClassificationType({id:id});
            var partyClassificationTypeEditView = new PartyClassificationTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyClassificationTypeEditView);
        },
        listOrganization:function()
        {
            var model = new Organizations();
            var organizationListView = new OrganizationListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(organizationListView);
            }).fetch();
        },
        editOrganization:function(id)
        {
            var model = new Organization({id:id});
            var organizationEditView = new OrganizationEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(organizationEditView);
        },
        listTelecommunicationsNumber:function()
        {
            var model = new TelecommunicationsNumbers();
            var telecommunicationsNumberListView = new TelecommunicationsNumberListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(telecommunicationsNumberListView);
            }).fetch();
        },
        editTelecommunicationsNumber:function(id)
        {
            var model = new TelecommunicationsNumber({id:id});
            var telecommunicationsNumberEditView = new TelecommunicationsNumberEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(telecommunicationsNumberEditView);
        },
        listPartyContactMechanism:function()
        {
            var model = new PartyContactMechanisms();
            var partyContactMechanismListView = new PartyContactMechanismListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(partyContactMechanismListView);
            }).fetch();
        },
        editPartyContactMechanism:function(id)
        {
            var model = new PartyContactMechanism({id:id});
            var partyContactMechanismEditView = new PartyContactMechanismEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(partyContactMechanismEditView);
        },
        listElectronicAddress:function()
        {
            var model = new ElectronicAddresss();
            var electronicAddressListView = new ElectronicAddressListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(electronicAddressListView);
            }).fetch();
        },
        editElectronicAddress:function(id)
        {
            var model = new ElectronicAddress({id:id});
            var electronicAddressEditView = new ElectronicAddressEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(electronicAddressEditView);
        },
        listInvoiceStatusType:function()
        {
            var model = new InvoiceStatusTypes();
            var invoiceStatusTypeListView = new InvoiceStatusTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceStatusTypeListView);
            }).fetch();
        },
        editInvoiceStatusType:function(id)
        {
            var model = new InvoiceStatusType({id:id});
            var invoiceStatusTypeEditView = new InvoiceStatusTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceStatusTypeEditView);
        },
        listInvoiceItemCategory:function()
        {
            var model = new InvoiceItemCategorys();
            var invoiceItemCategoryListView = new InvoiceItemCategoryListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceItemCategoryListView);
            }).fetch();
        },
        editInvoiceItemCategory:function(id)
        {
            var model = new InvoiceItemCategory({id:id});
            var invoiceItemCategoryEditView = new InvoiceItemCategoryEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceItemCategoryEditView);
        },
        listInvoiceStatus:function()
        {
            var model = new InvoiceStatuss();
            var invoiceStatusListView = new InvoiceStatusListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceStatusListView);
            }).fetch();
        },
        editInvoiceStatus:function(id)
        {
            var model = new InvoiceStatus({id:id});
            var invoiceStatusEditView = new InvoiceStatusEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceStatusEditView);
        },
        listInvoice:function()
        {
            var model = new Invoices();
            var invoiceListView = new InvoiceListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceListView);
            }).fetch();
        },
        editInvoice:function(id)
        {
            var model = new Invoice({id:id});
            var invoiceEditView = new InvoiceEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceEditView);
        },
        listInvoiceType:function()
        {
            var model = new InvoiceTypes();
            var invoiceTypeListView = new InvoiceTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceTypeListView);
            }).fetch();
        },
        editInvoiceType:function(id)
        {
            var model = new InvoiceType({id:id});
            var invoiceTypeEditView = new InvoiceTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceTypeEditView);
        },
        listInvoiceItem:function()
        {
            var model = new InvoiceItems();
            var invoiceItemListView = new InvoiceItemListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceItemListView);
            }).fetch();
        },
        editInvoiceItem:function(id)
        {
            var model = new InvoiceItem({id:id});
            var invoiceItemEditView = new InvoiceItemEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceItemEditView);
        },
        listInvoiceRole:function()
        {
            var model = new InvoiceRoles();
            var invoiceRoleListView = new InvoiceRoleListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceRoleListView);
            }).fetch();
        },
        editInvoiceRole:function(id)
        {
            var model = new InvoiceRole({id:id});
            var invoiceRoleEditView = new InvoiceRoleEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceRoleEditView);
        },
        listInvoiceItemType:function()
        {
            var model = new InvoiceItemTypes();
            var invoiceItemTypeListView = new InvoiceItemTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceItemTypeListView);
            }).fetch();
        },
        editInvoiceItemType:function(id)
        {
            var model = new InvoiceItemType({id:id});
            var invoiceItemTypeEditView = new InvoiceItemTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceItemTypeEditView);
        },
        listInvoiceRoleType:function()
        {
            var model = new InvoiceRoleTypes();
            var invoiceRoleTypeListView = new InvoiceRoleTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceRoleTypeListView);
            }).fetch();
        },
        editInvoiceRoleType:function(id)
        {
            var model = new InvoiceRoleType({id:id});
            var invoiceRoleTypeEditView = new InvoiceRoleTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceRoleTypeEditView);
        },
        listInvoiceTerm:function()
        {
            var model = new InvoiceTerms();
            var invoiceTermListView = new InvoiceTermListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(invoiceTermListView);
            }).fetch();
        },
        editInvoiceTerm:function(id)
        {
            var model = new InvoiceTerm({id:id});
            var invoiceTermEditView = new InvoiceTermEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(invoiceTermEditView);
        },
        listOrderItemBilling:function()
        {
            var model = new OrderItemBillings();
            var orderItemBillingListView = new OrderItemBillingListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(orderItemBillingListView);
            }).fetch();
        },
        editOrderItemBilling:function(id)
        {
            var model = new OrderItemBilling({id:id});
            var orderItemBillingEditView = new OrderItemBillingEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(orderItemBillingEditView);
        },
        listProductClassification:function()
        {
            var model = new ProductClassifications();
            var productClassificationListView = new ProductClassificationListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productClassificationListView);
            }).fetch();
        },
        editProductClassification:function(id)
        {
            var model = new ProductClassification({id:id});
            var productClassificationEditView = new ProductClassificationEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productClassificationEditView);
        },
        listProductType:function()
        {
            var model = new ProductTypes();
            var productTypeListView = new ProductTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productTypeListView);
            }).fetch();
        },
        editProductType:function(id)
        {
            var model = new ProductType({id:id});
            var productTypeEditView = new ProductTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productTypeEditView);
        },
        listCostComponentType:function()
        {
            var model = new CostComponentTypes();
            var costComponentTypeListView = new CostComponentTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(costComponentTypeListView);
            }).fetch();
        },
        editCostComponentType:function(id)
        {
            var model = new CostComponentType({id:id});
            var costComponentTypeEditView = new CostComponentTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(costComponentTypeEditView);
        },
        listProductCategoryType:function()
        {
            var model = new ProductCategoryTypes();
            var productCategoryTypeListView = new ProductCategoryTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productCategoryTypeListView);
            }).fetch();
        },
        editProductCategoryType:function(id)
        {
            var model = new ProductCategoryType({id:id});
            var productCategoryTypeEditView = new ProductCategoryTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productCategoryTypeEditView);
        },
        listProductFeature:function()
        {
            var model = new ProductFeatures();
            var productFeatureListView = new ProductFeatureListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productFeatureListView);
            }).fetch();
        },
        editProductFeature:function(id)
        {
            var model = new ProductFeature({id:id});
            var productFeatureEditView = new ProductFeatureEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productFeatureEditView);
        },
        listProductFeatureCategory:function()
        {
            var model = new ProductFeatureCategorys();
            var productFeatureCategoryListView = new ProductFeatureCategoryListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productFeatureCategoryListView);
            }).fetch();
        },
        editProductFeatureCategory:function(id)
        {
            var model = new ProductFeatureCategory({id:id});
            var productFeatureCategoryEditView = new ProductFeatureCategoryEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productFeatureCategoryEditView);
        },
        listProductFeatureApplicability:function()
        {
            var model = new ProductFeatureApplicabilitys();
            var productFeatureApplicabilityListView = new ProductFeatureApplicabilityListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productFeatureApplicabilityListView);
            }).fetch();
        },
        editProductFeatureApplicability:function(id)
        {
            var model = new ProductFeatureApplicability({id:id});
            var productFeatureApplicabilityEditView = new ProductFeatureApplicabilityEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productFeatureApplicabilityEditView);
        },
        listProduct:function()
        {
            var model = new Products();
            var productListView = new ProductListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productListView);
            }).fetch();
        },
        editProduct:function(id)
        {
            var model = new Product({id:id});
            var productEditView = new ProductEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productEditView);
        },
        listProductComponent:function()
        {
            var model = new ProductComponents();
            var productComponentListView = new ProductComponentListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productComponentListView);
            }).fetch();
        },
        editProductComponent:function(id)
        {
            var model = new ProductComponent({id:id});
            var productComponentEditView = new ProductComponentEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productComponentEditView);
        },
        listProductFeatureType:function()
        {
            var model = new ProductFeatureTypes();
            var productFeatureTypeListView = new ProductFeatureTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productFeatureTypeListView);
            }).fetch();
        },
        editProductFeatureType:function(id)
        {
            var model = new ProductFeatureType({id:id});
            var productFeatureTypeEditView = new ProductFeatureTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productFeatureTypeEditView);
        },
        listProductFeatureApplicabilityType:function()
        {
            var model = new ProductFeatureApplicabilityTypes();
            var productFeatureApplicabilityTypeListView = new ProductFeatureApplicabilityTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productFeatureApplicabilityTypeListView);
            }).fetch();
        },
        editProductFeatureApplicabilityType:function(id)
        {
            var model = new ProductFeatureApplicabilityType({id:id});
            var productFeatureApplicabilityTypeEditView = new ProductFeatureApplicabilityTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productFeatureApplicabilityTypeEditView);
        },
        listProductCategory:function()
        {
            var model = new ProductCategorys();
            var productCategoryListView = new ProductCategoryListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productCategoryListView);
            }).fetch();
        },
        editProductCategory:function(id)
        {
            var model = new ProductCategory({id:id});
            var productCategoryEditView = new ProductCategoryEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(productCategoryEditView);
        },
        listBillingAccountRoleType:function()
        {
            var model = new BillingAccountRoleTypes();
            var billingAccountRoleTypeListView = new BillingAccountRoleTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(billingAccountRoleTypeListView);
            }).fetch();
        },
        editBillingAccountRoleType:function(id)
        {
            var model = new BillingAccountRoleType({id:id});
            var billingAccountRoleTypeEditView = new BillingAccountRoleTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(billingAccountRoleTypeEditView);
        },
        listBillingAccountRole:function()
        {
            var model = new BillingAccountRoles();
            var billingAccountRoleListView = new BillingAccountRoleListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(billingAccountRoleListView);
            }).fetch();
        },
        editBillingAccountRole:function(id)
        {
            var model = new BillingAccountRole({id:id});
            var billingAccountRoleEditView = new BillingAccountRoleEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(billingAccountRoleEditView);
        },
        listBillingAccount:function()
        {
            var model = new BillingAccounts();
            var billingAccountListView = new BillingAccountListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(billingAccountListView);
            }).fetch();
        },
        editBillingAccount:function(id)
        {
            var model = new BillingAccount({id:id});
            var billingAccountEditView = new BillingAccountEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(billingAccountEditView);
        },
        listCustomerBlacklist:function()
        {
            var model = new CustomerBlacklists();
            var customerBlacklistListView = new CustomerBlacklistListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(customerBlacklistListView);
            }).fetch();
        },
        editCustomerBlacklist:function(id)
        {
            var model = new CustomerBlacklist({id:id});
            var customerBlacklistEditView = new CustomerBlacklistEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(customerBlacklistEditView);
        },
        listCustomer:function()
        {
            var model = new Customers();
            var customerListView = new CustomerListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(customerListView);
            }).fetch();
        },
        editCustomer:function(id)
        {
            var model = new Customer({id:id});
            var customerEditView = new CustomerEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(customerEditView);
        },
        listUomConversion:function()
        {
            var model = new UomConversions();
            var uomConversionListView = new UomConversionListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(uomConversionListView);
            }).fetch();
        },
        editUomConversion:function(id)
        {
            var model = new UomConversion({id:id});
            var uomConversionEditView = new UomConversionEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(uomConversionEditView);
        },
        listTermType:function()
        {
            var model = new TermTypes();
            var termTypeListView = new TermTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(termTypeListView);
            }).fetch();
        },
        editTermType:function(id)
        {
            var model = new TermType({id:id});
            var termTypeEditView = new TermTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(termTypeEditView);
        },
        listUom:function()
        {
            var model = new Uoms();
            var uomListView = new UomListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(uomListView);
            }).fetch();
        },
        editUom:function(id)
        {
            var model = new Uom({id:id});
            var uomEditView = new UomEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(uomEditView);
        },
        listCurrency:function()
        {
            var model = new Currencys();
            var currencyListView = new CurrencyListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(currencyListView);
            }).fetch();
        },
        editCurrency:function(id)
        {
            var model = new Currency({id:id});
            var currencyEditView = new CurrencyEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(currencyEditView);
        },
        listGeoBoundryAssociation:function()
        {
            var model = new GeoBoundryAssociations();
            var geoBoundryAssociationListView = new GeoBoundryAssociationListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(geoBoundryAssociationListView);
            }).fetch();
        },
        editGeoBoundryAssociation:function(id)
        {
            var model = new GeoBoundryAssociation({id:id});
            var geoBoundryAssociationEditView = new GeoBoundryAssociationEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(geoBoundryAssociationEditView);
        },
        listServiceChannel:function()
        {
            var model = new ServiceChannels();
            var serviceChannelListView = new ServiceChannelListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(serviceChannelListView);
            }).fetch();
        },
        editServiceChannel:function(id)
        {
            var model = new ServiceChannel({id:id});
            var serviceChannelEditView = new ServiceChannelEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(serviceChannelEditView);
        },
        listStatusType:function()
        {
            var model = new StatusTypes();
            var statusTypeListView = new StatusTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(statusTypeListView);
            }).fetch();
        },
        editStatusType:function(id)
        {
            var model = new StatusType({id:id});
            var statusTypeEditView = new StatusTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(statusTypeEditView);
        },
        listGeoBoundry:function()
        {
            var model = new GeoBoundrys();
            var geoBoundryListView = new GeoBoundryListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(geoBoundryListView);
            }).fetch();
        },
        editGeoBoundry:function(id)
        {
            var model = new GeoBoundry({id:id});
            var geoBoundryEditView = new GeoBoundryEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(geoBoundryEditView);
        },
        listPostalAddressBoundry:function()
        {
            var model = new PostalAddressBoundrys();
            var postalAddressBoundryListView = new PostalAddressBoundryListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(postalAddressBoundryListView);
            }).fetch();
        },
        editPostalAddressBoundry:function(id)
        {
            var model = new PostalAddressBoundry({id:id});
            var postalAddressBoundryEditView = new PostalAddressBoundryEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(postalAddressBoundryEditView);
        },
        listGeoBoundaryType:function()
        {
            var model = new GeoBoundaryTypes();
            var geoBoundaryTypeListView = new GeoBoundaryTypeListView({model:model, el:$("#content-container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(geoBoundaryTypeListView);
            }).fetch();
        },
        editGeoBoundaryType:function(id)
        {
            var model = new GeoBoundaryType({id:id});
            var geoBoundaryTypeEditView = new GeoBoundaryTypeEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(geoBoundaryTypeEditView);
        },
        customerModuleIndex:function()
        {
            utilities.viewManager.showView(new CustomerHomeView({el:$("#content-container")}));
        },
        adviceModuleIndex:function()
        {
            utilities.viewManager.showView(new AdviceHomeView({el:$("#content-container")}));
        },
        channelModuleIndex:function()
        {
            utilities.viewManager.showView(new ChannelHomeView({el:$("#content-container")}));
        },
        productModuleIndex:function()
        {
            utilities.viewManager.showView(new ProductHomeView({el:$("#content-container")}));
        },
        orderModuleIndex:function()
        {
            utilities.viewManager.showView(new OrderHomeView({el:$("#content-container")}));
        },
        invoiceModuleIndex:function()
        {
            utilities.viewManager.showView(new InvoiceHomeView({el:$("#content-container")}));
        },
        paymentModuleIndex:function()
        {
            utilities.viewManager.showView(new PaymentHomeView({el:$("#content-container")}));
        },
        messagingModuleIndex:function()
        {
            utilities.viewManager.showView(new MessagingHomeView({el:$("#content-container")}));
        },
        businessDataModuleIndex:function()
        {
            utilities.viewManager.showView(new BusinessDataHomeView({el:$("#content-container")}));
        },
        reportsModuleIndex:function()
        {
            utilities.viewManager.showView(new ReportsHomeView({el:$("#content-container")}));
        }
    });
    
    // Create a router instance
    var router = new Router();

    //Begin routing
    Backbone.history.start();

    return router;
});