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
    'app/views/desktop/home/product-home',
    'app/views/desktop/home/order-home',
    'app/views/desktop/home/invoice-home',
    'app/views/desktop/home/payment-home',
    'app/views/desktop/home/messaging-home',
    'app/views/desktop/home/businessdata-home',
    'app/views/desktop/home/report-home',
    'app/models/invoiceitemtype/invoiceitemtype',
    'app/collections/invoiceitemtype/invoiceitemtype',
    'app/views/desktop/invoiceitemtype/edit-invoiceitemtype',
    'app/views/desktop/invoiceitemtype/list-invoiceitemtype',
    'app/models/termtype/termtype',
    'app/collections/termtype/termtype',
    'app/views/desktop/termtype/edit-termtype',
    'app/views/desktop/termtype/list-termtype',
    'app/models/productcategorytype/productcategorytype',
    'app/collections/productcategorytype/productcategorytype',
    'app/views/desktop/productcategorytype/edit-productcategorytype',
    'app/views/desktop/productcategorytype/list-productcategorytype',
    'app/models/paymentapplication/paymentapplication',
    'app/collections/paymentapplication/paymentapplication',
    'app/views/desktop/paymentapplication/edit-paymentapplication',
    'app/views/desktop/paymentapplication/list-paymentapplication',
    'app/models/partytype/partytype',
    'app/collections/partytype/partytype',
    'app/views/desktop/partytype/edit-partytype',
    'app/views/desktop/partytype/list-partytype',
    'app/models/partyrelationshiptype/partyrelationshiptype',
    'app/collections/partyrelationshiptype/partyrelationshiptype',
    'app/views/desktop/partyrelationshiptype/edit-partyrelationshiptype',
    'app/views/desktop/partyrelationshiptype/list-partyrelationshiptype',
    'app/models/geoboundryassociation/geoboundryassociation',
    'app/collections/geoboundryassociation/geoboundryassociation',
    'app/views/desktop/geoboundryassociation/edit-geoboundryassociation',
    'app/views/desktop/geoboundryassociation/list-geoboundryassociation',
    'app/models/organization/organization',
    'app/collections/organization/organization',
    'app/views/desktop/organization/edit-organization',
    'app/views/desktop/organization/list-organization',
    'app/models/partyrole/partyrole',
    'app/collections/partyrole/partyrole',
    'app/views/desktop/partyrole/edit-partyrole',
    'app/views/desktop/partyrole/list-partyrole',
    'app/models/workeffort/workeffort',
    'app/collections/workeffort/workeffort',
    'app/views/desktop/workeffort/edit-workeffort',
    'app/views/desktop/workeffort/list-workeffort',
    'app/models/productfeaturecategory/productfeaturecategory',
    'app/collections/productfeaturecategory/productfeaturecategory',
    'app/views/desktop/productfeaturecategory/edit-productfeaturecategory',
    'app/views/desktop/productfeaturecategory/list-productfeaturecategory',
    'app/models/paymentmethodtypeprovider/paymentmethodtypeprovider',
    'app/collections/paymentmethodtypeprovider/paymentmethodtypeprovider',
    'app/views/desktop/paymentmethodtypeprovider/edit-paymentmethodtypeprovider',
    'app/views/desktop/paymentmethodtypeprovider/list-paymentmethodtypeprovider',
    'app/models/contactmechanismlink/contactmechanismlink',
    'app/collections/contactmechanismlink/contactmechanismlink',
    'app/views/desktop/contactmechanismlink/edit-contactmechanismlink',
    'app/views/desktop/contactmechanismlink/list-contactmechanismlink',
    'app/models/productorderitemtype/productorderitemtype',
    'app/collections/productorderitemtype/productorderitemtype',
    'app/views/desktop/productorderitemtype/edit-productorderitemtype',
    'app/views/desktop/productorderitemtype/list-productorderitemtype',
    'app/models/postaladdressboundry/postaladdressboundry',
    'app/collections/postaladdressboundry/postaladdressboundry',
    'app/views/desktop/postaladdressboundry/edit-postaladdressboundry',
    'app/views/desktop/postaladdressboundry/list-postaladdressboundry',
    'app/models/communicationeventworkeffort/communicationeventworkeffort',
    'app/collections/communicationeventworkeffort/communicationeventworkeffort',
    'app/views/desktop/communicationeventworkeffort/edit-communicationeventworkeffort',
    'app/views/desktop/communicationeventworkeffort/list-communicationeventworkeffort',
    'app/models/estimatedproductcost/estimatedproductcost',
    'app/collections/estimatedproductcost/estimatedproductcost',
    'app/views/desktop/estimatedproductcost/edit-estimatedproductcost',
    'app/views/desktop/estimatedproductcost/list-estimatedproductcost',
    'app/models/partycontactmechanism/partycontactmechanism',
    'app/collections/partycontactmechanism/partycontactmechanism',
    'app/views/desktop/partycontactmechanism/edit-partycontactmechanism',
    'app/views/desktop/partycontactmechanism/list-partycontactmechanism',
    'app/models/currency/currency',
    'app/collections/currency/currency',
    'app/views/desktop/currency/edit-currency',
    'app/views/desktop/currency/list-currency',
    'app/models/partyclassificationtype/partyclassificationtype',
    'app/collections/partyclassificationtype/partyclassificationtype',
    'app/views/desktop/partyclassificationtype/edit-partyclassificationtype',
    'app/views/desktop/partyclassificationtype/list-partyclassificationtype',
    'app/models/billingaccount/billingaccount',
    'app/collections/billingaccount/billingaccount',
    'app/views/desktop/billingaccount/edit-billingaccount',
    'app/views/desktop/billingaccount/list-billingaccount',
    'app/models/invoiceroletype/invoiceroletype',
    'app/collections/invoiceroletype/invoiceroletype',
    'app/views/desktop/invoiceroletype/edit-invoiceroletype',
    'app/views/desktop/invoiceroletype/list-invoiceroletype',
    'app/models/partyclassification/partyclassification',
    'app/collections/partyclassification/partyclassification',
    'app/views/desktop/partyclassification/edit-partyclassification',
    'app/views/desktop/partyclassification/list-partyclassification',
    'app/models/customer/customer',
    'app/collections/customer/customer',
    'app/views/desktop/customer/edit-customer',
    'app/views/desktop/customer/list-customer',
    'app/models/contactmechanismpurposetype/contactmechanismpurposetype',
    'app/collections/contactmechanismpurposetype/contactmechanismpurposetype',
    'app/views/desktop/contactmechanismpurposetype/edit-contactmechanismpurposetype',
    'app/views/desktop/contactmechanismpurposetype/list-contactmechanismpurposetype',
    'app/models/partycase/partycase',
    'app/collections/partycase/partycase',
    'app/views/desktop/partycase/edit-partycase',
    'app/views/desktop/partycase/list-partycase',
    'app/models/paymenttype/paymenttype',
    'app/collections/paymenttype/paymenttype',
    'app/views/desktop/paymenttype/edit-paymenttype',
    'app/views/desktop/paymenttype/list-paymenttype',
    'app/models/communicationevent/communicationevent',
    'app/collections/communicationevent/communicationevent',
    'app/views/desktop/communicationevent/edit-communicationevent',
    'app/views/desktop/communicationevent/list-communicationevent',
    'app/models/geoboundarytype/geoboundarytype',
    'app/collections/geoboundarytype/geoboundarytype',
    'app/views/desktop/geoboundarytype/edit-geoboundarytype',
    'app/views/desktop/geoboundarytype/list-geoboundarytype',
    'app/models/invoiceitemcategory/invoiceitemcategory',
    'app/collections/invoiceitemcategory/invoiceitemcategory',
    'app/views/desktop/invoiceitemcategory/edit-invoiceitemcategory',
    'app/views/desktop/invoiceitemcategory/list-invoiceitemcategory',
    'app/models/productclassification/productclassification',
    'app/collections/productclassification/productclassification',
    'app/views/desktop/productclassification/edit-productclassification',
    'app/views/desktop/productclassification/list-productclassification',
    'app/models/partyroletype/partyroletype',
    'app/collections/partyroletype/partyroletype',
    'app/views/desktop/partyroletype/edit-partyroletype',
    'app/views/desktop/partyroletype/list-partyroletype',
    'app/models/invoicerole/invoicerole',
    'app/collections/invoicerole/invoicerole',
    'app/views/desktop/invoicerole/edit-invoicerole',
    'app/views/desktop/invoicerole/list-invoicerole',
    'app/models/uom/uom',
    'app/collections/uom/uom',
    'app/views/desktop/uom/edit-uom',
    'app/views/desktop/uom/list-uom',
    'app/models/productfeature/productfeature',
    'app/collections/productfeature/productfeature',
    'app/views/desktop/productfeature/edit-productfeature',
    'app/views/desktop/productfeature/list-productfeature',
    'app/models/productcomponent/productcomponent',
    'app/collections/productcomponent/productcomponent',
    'app/views/desktop/productcomponent/edit-productcomponent',
    'app/views/desktop/productcomponent/list-productcomponent',
    'app/models/contactmechanism/contactmechanism',
    'app/collections/contactmechanism/contactmechanism',
    'app/views/desktop/contactmechanism/edit-contactmechanism',
    'app/views/desktop/contactmechanism/list-contactmechanism',
    'app/models/uomconversion/uomconversion',
    'app/collections/uomconversion/uomconversion',
    'app/views/desktop/uomconversion/edit-uomconversion',
    'app/views/desktop/uomconversion/list-uomconversion',
    'app/models/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'app/collections/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'app/views/desktop/productfeatureapplicabilitytype/edit-productfeatureapplicabilitytype',
    'app/views/desktop/productfeatureapplicabilitytype/list-productfeatureapplicabilitytype',
    'app/models/postaladdress/postaladdress',
    'app/collections/postaladdress/postaladdress',
    'app/views/desktop/postaladdress/edit-postaladdress',
    'app/views/desktop/postaladdress/list-postaladdress',
    'app/models/invoiceterm/invoiceterm',
    'app/collections/invoiceterm/invoiceterm',
    'app/views/desktop/invoiceterm/edit-invoiceterm',
    'app/views/desktop/invoiceterm/list-invoiceterm',
    'app/models/orderitembilling/orderitembilling',
    'app/collections/orderitembilling/orderitembilling',
    'app/views/desktop/orderitembilling/edit-orderitembilling',
    'app/views/desktop/orderitembilling/list-orderitembilling',
    'app/models/communicationeventpurposetype/communicationeventpurposetype',
    'app/collections/communicationeventpurposetype/communicationeventpurposetype',
    'app/views/desktop/communicationeventpurposetype/edit-communicationeventpurposetype',
    'app/views/desktop/communicationeventpurposetype/list-communicationeventpurposetype',
    'app/models/productfeaturetype/productfeaturetype',
    'app/collections/productfeaturetype/productfeaturetype',
    'app/views/desktop/productfeaturetype/edit-productfeaturetype',
    'app/views/desktop/productfeaturetype/list-productfeaturetype',
    'app/models/productorderitem/productorderitem',
    'app/collections/productorderitem/productorderitem',
    'app/views/desktop/productorderitem/edit-productorderitem',
    'app/views/desktop/productorderitem/list-productorderitem',
    'app/models/communicationeventstatustype/communicationeventstatustype',
    'app/collections/communicationeventstatustype/communicationeventstatustype',
    'app/views/desktop/communicationeventstatustype/edit-communicationeventstatustype',
    'app/views/desktop/communicationeventstatustype/list-communicationeventstatustype',
    'app/models/billingaccountrole/billingaccountrole',
    'app/collections/billingaccountrole/billingaccountrole',
    'app/views/desktop/billingaccountrole/edit-billingaccountrole',
    'app/views/desktop/billingaccountrole/list-billingaccountrole',
    'app/models/productfeatureapplicability/productfeatureapplicability',
    'app/collections/productfeatureapplicability/productfeatureapplicability',
    'app/views/desktop/productfeatureapplicability/edit-productfeatureapplicability',
    'app/views/desktop/productfeatureapplicability/list-productfeatureapplicability',
    'app/models/roletype/roletype',
    'app/collections/roletype/roletype',
    'app/views/desktop/roletype/edit-roletype',
    'app/views/desktop/roletype/list-roletype',
    'app/models/casestatustype/casestatustype',
    'app/collections/casestatustype/casestatustype',
    'app/views/desktop/casestatustype/edit-casestatustype',
    'app/views/desktop/casestatustype/list-casestatustype',
    'app/models/workefforttype/workefforttype',
    'app/collections/workefforttype/workefforttype',
    'app/views/desktop/workefforttype/edit-workefforttype',
    'app/views/desktop/workefforttype/list-workefforttype',
    'app/models/geoboundry/geoboundry',
    'app/collections/geoboundry/geoboundry',
    'app/views/desktop/geoboundry/edit-geoboundry',
    'app/views/desktop/geoboundry/list-geoboundry',
    'app/models/product/product',
    'app/collections/product/product',
    'app/views/desktop/product/edit-product',
    'app/views/desktop/product/list-product',
    'app/models/productordertype/productordertype',
    'app/collections/productordertype/productordertype',
    'app/views/desktop/productordertype/edit-productordertype',
    'app/views/desktop/productordertype/list-productordertype',
    'app/models/telecommunicationsnumber/telecommunicationsnumber',
    'app/collections/telecommunicationsnumber/telecommunicationsnumber',
    'app/views/desktop/telecommunicationsnumber/edit-telecommunicationsnumber',
    'app/views/desktop/telecommunicationsnumber/list-telecommunicationsnumber',
    'app/models/party/party',
    'app/collections/party/party',
    'app/views/desktop/party/edit-party',
    'app/views/desktop/party/list-party',
    'app/models/producttype/producttype',
    'app/collections/producttype/producttype',
    'app/views/desktop/producttype/edit-producttype',
    'app/views/desktop/producttype/list-producttype',
    'app/models/invoicetype/invoicetype',
    'app/collections/invoicetype/invoicetype',
    'app/views/desktop/invoicetype/edit-invoicetype',
    'app/views/desktop/invoicetype/list-invoicetype',
    'app/models/payment/payment',
    'app/collections/payment/payment',
    'app/views/desktop/payment/edit-payment',
    'app/views/desktop/payment/list-payment',
    'app/models/productcategory/productcategory',
    'app/collections/productcategory/productcategory',
    'app/views/desktop/productcategory/edit-productcategory',
    'app/views/desktop/productcategory/list-productcategory',
    'app/models/invoice/invoice',
    'app/collections/invoice/invoice',
    'app/views/desktop/invoice/edit-invoice',
    'app/views/desktop/invoice/list-invoice',
    'app/models/statustype/statustype',
    'app/collections/statustype/statustype',
    'app/views/desktop/statustype/edit-statustype',
    'app/views/desktop/statustype/list-statustype',
    'app/models/billingaccountroletype/billingaccountroletype',
    'app/collections/billingaccountroletype/billingaccountroletype',
    'app/views/desktop/billingaccountroletype/edit-billingaccountroletype',
    'app/views/desktop/billingaccountroletype/list-billingaccountroletype',
    'app/models/partycontactmechanismpurpose/partycontactmechanismpurpose',
    'app/collections/partycontactmechanismpurpose/partycontactmechanismpurpose',
    'app/views/desktop/partycontactmechanismpurpose/edit-partycontactmechanismpurpose',
    'app/views/desktop/partycontactmechanismpurpose/list-partycontactmechanismpurpose',
    'app/models/contactmechanismtype/contactmechanismtype',
    'app/collections/contactmechanismtype/contactmechanismtype',
    'app/views/desktop/contactmechanismtype/edit-contactmechanismtype',
    'app/views/desktop/contactmechanismtype/list-contactmechanismtype',
    'app/models/person/person',
    'app/collections/person/person',
    'app/views/desktop/person/edit-person',
    'app/views/desktop/person/list-person',
    'app/models/partyrelationship/partyrelationship',
    'app/collections/partyrelationship/partyrelationship',
    'app/views/desktop/partyrelationship/edit-partyrelationship',
    'app/views/desktop/partyrelationship/list-partyrelationship',
    'app/models/servicechannel/servicechannel',
    'app/collections/servicechannel/servicechannel',
    'app/views/desktop/servicechannel/edit-servicechannel',
    'app/views/desktop/servicechannel/list-servicechannel',
    'app/models/invoicestatustype/invoicestatustype',
    'app/collections/invoicestatustype/invoicestatustype',
    'app/views/desktop/invoicestatustype/edit-invoicestatustype',
    'app/views/desktop/invoicestatustype/list-invoicestatustype',
    'app/models/productorder/productorder',
    'app/collections/productorder/productorder',
    'app/views/desktop/productorder/edit-productorder',
    'app/views/desktop/productorder/list-productorder',
    'app/models/invoicestatus/invoicestatus',
    'app/collections/invoicestatus/invoicestatus',
    'app/views/desktop/invoicestatus/edit-invoicestatus',
    'app/views/desktop/invoicestatus/list-invoicestatus',
    'app/models/invoiceitem/invoiceitem',
    'app/collections/invoiceitem/invoiceitem',
    'app/views/desktop/invoiceitem/edit-invoiceitem',
    'app/views/desktop/invoiceitem/list-invoiceitem',
    'app/models/caserole/caserole',
    'app/collections/caserole/caserole',
    'app/views/desktop/caserole/edit-caserole',
    'app/views/desktop/caserole/list-caserole',
    'app/models/caseroletype/caseroletype',
    'app/collections/caseroletype/caseroletype',
    'app/views/desktop/caseroletype/edit-caseroletype',
    'app/views/desktop/caseroletype/list-caseroletype',
    'app/models/paymentmethodtype/paymentmethodtype',
    'app/collections/paymentmethodtype/paymentmethodtype',
    'app/views/desktop/paymentmethodtype/edit-paymentmethodtype',
    'app/views/desktop/paymentmethodtype/list-paymentmethodtype',
    'app/models/communicationeventpurpose/communicationeventpurpose',
    'app/collections/communicationeventpurpose/communicationeventpurpose',
    'app/views/desktop/communicationeventpurpose/edit-communicationeventpurpose',
    'app/views/desktop/communicationeventpurpose/list-communicationeventpurpose',
    'app/models/electronicaddress/electronicaddress',
    'app/collections/electronicaddress/electronicaddress',
    'app/views/desktop/electronicaddress/edit-electronicaddress',
    'app/views/desktop/electronicaddress/list-electronicaddress',
    'app/models/costcomponenttype/costcomponenttype',
    'app/collections/costcomponenttype/costcomponenttype',
    'app/views/desktop/costcomponenttype/edit-costcomponenttype',
    'app/views/desktop/costcomponenttype/list-costcomponenttype',
    'app/models/communicationeventtype/communicationeventtype',
    'app/collections/communicationeventtype/communicationeventtype',
    'app/views/desktop/communicationeventtype/edit-communicationeventtype',
    'app/views/desktop/communicationeventtype/list-communicationeventtype',
    'app/models/customerblacklist/customerblacklist',
    'app/collections/customerblacklist/customerblacklist',
    'app/views/desktop/customerblacklist/edit-customerblacklist',
    'app/views/desktop/customerblacklist/list-customerblacklist',
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
            ProductHomeView,
            OrderHomeView,
            InvoiceHomeView,
            PaymentHomeView,
            MessagingHomeView,
            BusinessDataHomeView,
            ReportsHomeView,
            InvoiceItemType,
            InvoiceItemTypes,
            InvoiceItemTypeEditView,
            InvoiceItemTypeListView,
            TermType,
            TermTypes,
            TermTypeEditView,
            TermTypeListView,
            ProductCategoryType,
            ProductCategoryTypes,
            ProductCategoryTypeEditView,
            ProductCategoryTypeListView,
            PaymentApplication,
            PaymentApplications,
            PaymentApplicationEditView,
            PaymentApplicationListView,
            PartyType,
            PartyTypes,
            PartyTypeEditView,
            PartyTypeListView,
            PartyRelationshipType,
            PartyRelationshipTypes,
            PartyRelationshipTypeEditView,
            PartyRelationshipTypeListView,
            GeoBoundryAssociation,
            GeoBoundryAssociations,
            GeoBoundryAssociationEditView,
            GeoBoundryAssociationListView,
            Organization,
            Organizations,
            OrganizationEditView,
            OrganizationListView,
            PartyRole,
            PartyRoles,
            PartyRoleEditView,
            PartyRoleListView,
            WorkEffort,
            WorkEfforts,
            WorkEffortEditView,
            WorkEffortListView,
            ProductFeatureCategory,
            ProductFeatureCategorys,
            ProductFeatureCategoryEditView,
            ProductFeatureCategoryListView,
            PaymentMethodTypeProvider,
            PaymentMethodTypeProviders,
            PaymentMethodTypeProviderEditView,
            PaymentMethodTypeProviderListView,
            ContactMechanismLink,
            ContactMechanismLinks,
            ContactMechanismLinkEditView,
            ContactMechanismLinkListView,
            ProductOrderItemType,
            ProductOrderItemTypes,
            ProductOrderItemTypeEditView,
            ProductOrderItemTypeListView,
            PostalAddressBoundry,
            PostalAddressBoundrys,
            PostalAddressBoundryEditView,
            PostalAddressBoundryListView,
            CommunicationEventWorkEffort,
            CommunicationEventWorkEfforts,
            CommunicationEventWorkEffortEditView,
            CommunicationEventWorkEffortListView,
            EstimatedProductCost,
            EstimatedProductCosts,
            EstimatedProductCostEditView,
            EstimatedProductCostListView,
            PartyContactMechanism,
            PartyContactMechanisms,
            PartyContactMechanismEditView,
            PartyContactMechanismListView,
            Currency,
            Currencys,
            CurrencyEditView,
            CurrencyListView,
            PartyClassificationType,
            PartyClassificationTypes,
            PartyClassificationTypeEditView,
            PartyClassificationTypeListView,
            BillingAccount,
            BillingAccounts,
            BillingAccountEditView,
            BillingAccountListView,
            InvoiceRoleType,
            InvoiceRoleTypes,
            InvoiceRoleTypeEditView,
            InvoiceRoleTypeListView,
            PartyClassification,
            PartyClassifications,
            PartyClassificationEditView,
            PartyClassificationListView,
            Customer,
            Customers,
            CustomerEditView,
            CustomerListView,
            ContactMechanismPurposeType,
            ContactMechanismPurposeTypes,
            ContactMechanismPurposeTypeEditView,
            ContactMechanismPurposeTypeListView,
            PartyCase,
            PartyCases,
            PartyCaseEditView,
            PartyCaseListView,
            PaymentType,
            PaymentTypes,
            PaymentTypeEditView,
            PaymentTypeListView,
            CommunicationEvent,
            CommunicationEvents,
            CommunicationEventEditView,
            CommunicationEventListView,
            GeoBoundaryType,
            GeoBoundaryTypes,
            GeoBoundaryTypeEditView,
            GeoBoundaryTypeListView,
            InvoiceItemCategory,
            InvoiceItemCategorys,
            InvoiceItemCategoryEditView,
            InvoiceItemCategoryListView,
            ProductClassification,
            ProductClassifications,
            ProductClassificationEditView,
            ProductClassificationListView,
            PartyRoleType,
            PartyRoleTypes,
            PartyRoleTypeEditView,
            PartyRoleTypeListView,
            InvoiceRole,
            InvoiceRoles,
            InvoiceRoleEditView,
            InvoiceRoleListView,
            Uom,
            Uoms,
            UomEditView,
            UomListView,
            ProductFeature,
            ProductFeatures,
            ProductFeatureEditView,
            ProductFeatureListView,
            ProductComponent,
            ProductComponents,
            ProductComponentEditView,
            ProductComponentListView,
            ContactMechanism,
            ContactMechanisms,
            ContactMechanismEditView,
            ContactMechanismListView,
            UomConversion,
            UomConversions,
            UomConversionEditView,
            UomConversionListView,
            ProductFeatureApplicabilityType,
            ProductFeatureApplicabilityTypes,
            ProductFeatureApplicabilityTypeEditView,
            ProductFeatureApplicabilityTypeListView,
            PostalAddress,
            PostalAddresss,
            PostalAddressEditView,
            PostalAddressListView,
            InvoiceTerm,
            InvoiceTerms,
            InvoiceTermEditView,
            InvoiceTermListView,
            OrderItemBilling,
            OrderItemBillings,
            OrderItemBillingEditView,
            OrderItemBillingListView,
            CommunicationEventPurposeType,
            CommunicationEventPurposeTypes,
            CommunicationEventPurposeTypeEditView,
            CommunicationEventPurposeTypeListView,
            ProductFeatureType,
            ProductFeatureTypes,
            ProductFeatureTypeEditView,
            ProductFeatureTypeListView,
            ProductOrderItem,
            ProductOrderItems,
            ProductOrderItemEditView,
            ProductOrderItemListView,
            CommunicationEventStatusType,
            CommunicationEventStatusTypes,
            CommunicationEventStatusTypeEditView,
            CommunicationEventStatusTypeListView,
            BillingAccountRole,
            BillingAccountRoles,
            BillingAccountRoleEditView,
            BillingAccountRoleListView,
            ProductFeatureApplicability,
            ProductFeatureApplicabilitys,
            ProductFeatureApplicabilityEditView,
            ProductFeatureApplicabilityListView,
            RoleType,
            RoleTypes,
            RoleTypeEditView,
            RoleTypeListView,
            CaseStatusType,
            CaseStatusTypes,
            CaseStatusTypeEditView,
            CaseStatusTypeListView,
            WorkEffortType,
            WorkEffortTypes,
            WorkEffortTypeEditView,
            WorkEffortTypeListView,
            GeoBoundry,
            GeoBoundrys,
            GeoBoundryEditView,
            GeoBoundryListView,
            Product,
            Products,
            ProductEditView,
            ProductListView,
            ProductOrderType,
            ProductOrderTypes,
            ProductOrderTypeEditView,
            ProductOrderTypeListView,
            TelecommunicationsNumber,
            TelecommunicationsNumbers,
            TelecommunicationsNumberEditView,
            TelecommunicationsNumberListView,
            Party,
            Partys,
            PartyEditView,
            PartyListView,
            ProductType,
            ProductTypes,
            ProductTypeEditView,
            ProductTypeListView,
            InvoiceType,
            InvoiceTypes,
            InvoiceTypeEditView,
            InvoiceTypeListView,
            Payment,
            Payments,
            PaymentEditView,
            PaymentListView,
            ProductCategory,
            ProductCategorys,
            ProductCategoryEditView,
            ProductCategoryListView,
            Invoice,
            Invoices,
            InvoiceEditView,
            InvoiceListView,
            StatusType,
            StatusTypes,
            StatusTypeEditView,
            StatusTypeListView,
            BillingAccountRoleType,
            BillingAccountRoleTypes,
            BillingAccountRoleTypeEditView,
            BillingAccountRoleTypeListView,
            PartyContactMechanismPurpose,
            PartyContactMechanismPurposes,
            PartyContactMechanismPurposeEditView,
            PartyContactMechanismPurposeListView,
            ContactMechanismType,
            ContactMechanismTypes,
            ContactMechanismTypeEditView,
            ContactMechanismTypeListView,
            Person,
            Persons,
            PersonEditView,
            PersonListView,
            PartyRelationship,
            PartyRelationships,
            PartyRelationshipEditView,
            PartyRelationshipListView,
            ServiceChannel,
            ServiceChannels,
            ServiceChannelEditView,
            ServiceChannelListView,
            InvoiceStatusType,
            InvoiceStatusTypes,
            InvoiceStatusTypeEditView,
            InvoiceStatusTypeListView,
            ProductOrder,
            ProductOrders,
            ProductOrderEditView,
            ProductOrderListView,
            InvoiceStatus,
            InvoiceStatuss,
            InvoiceStatusEditView,
            InvoiceStatusListView,
            InvoiceItem,
            InvoiceItems,
            InvoiceItemEditView,
            InvoiceItemListView,
            CaseRole,
            CaseRoles,
            CaseRoleEditView,
            CaseRoleListView,
            CaseRoleType,
            CaseRoleTypes,
            CaseRoleTypeEditView,
            CaseRoleTypeListView,
            PaymentMethodType,
            PaymentMethodTypes,
            PaymentMethodTypeEditView,
            PaymentMethodTypeListView,
            CommunicationEventPurpose,
            CommunicationEventPurposes,
            CommunicationEventPurposeEditView,
            CommunicationEventPurposeListView,
            ElectronicAddress,
            ElectronicAddresss,
            ElectronicAddressEditView,
            ElectronicAddressListView,
            CostComponentType,
            CostComponentTypes,
            CostComponentTypeEditView,
            CostComponentTypeListView,
            CommunicationEventType,
            CommunicationEventTypes,
            CommunicationEventTypeEditView,
            CommunicationEventTypeListView,
            CustomerBlacklist,
            CustomerBlacklists,
            CustomerBlacklistEditView,
            CustomerBlacklistListView,
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
            "product-module":"productModuleIndex",
            "order-module":"orderModuleIndex",
            "invoice-module":"invoiceModuleIndex",
            "payment-module":"paymentModuleIndex",
            "messaging-module":"messagingModuleIndex",
            "businessdata-module":"businessDataModuleIndex",
            "reports-module":"reportsModuleIndex",
            "list-invoiceitemtype":"listInvoiceItemType",
            "edit-invoiceitemtype":"editInvoiceItemType",
            "edit-invoiceitemtype/:id":"editInvoiceItemType",
            "list-termtype":"listTermType",
            "edit-termtype":"editTermType",
            "edit-termtype/:id":"editTermType",
            "list-productcategorytype":"listProductCategoryType",
            "edit-productcategorytype":"editProductCategoryType",
            "edit-productcategorytype/:id":"editProductCategoryType",
            "list-paymentapplication":"listPaymentApplication",
            "edit-paymentapplication":"editPaymentApplication",
            "edit-paymentapplication/:id":"editPaymentApplication",
            "list-partytype":"listPartyType",
            "edit-partytype":"editPartyType",
            "edit-partytype/:id":"editPartyType",
            "list-partyrelationshiptype":"listPartyRelationshipType",
            "edit-partyrelationshiptype":"editPartyRelationshipType",
            "edit-partyrelationshiptype/:id":"editPartyRelationshipType",
            "list-geoboundryassociation":"listGeoBoundryAssociation",
            "edit-geoboundryassociation":"editGeoBoundryAssociation",
            "edit-geoboundryassociation/:id":"editGeoBoundryAssociation",
            "list-organization":"listOrganization",
            "edit-organization":"editOrganization",
            "edit-organization/:id":"editOrganization",
            "list-partyrole":"listPartyRole",
            "edit-partyrole":"editPartyRole",
            "edit-partyrole/:id":"editPartyRole",
            "list-workeffort":"listWorkEffort",
            "edit-workeffort":"editWorkEffort",
            "edit-workeffort/:id":"editWorkEffort",
            "list-productfeaturecategory":"listProductFeatureCategory",
            "edit-productfeaturecategory":"editProductFeatureCategory",
            "edit-productfeaturecategory/:id":"editProductFeatureCategory",
            "list-paymentmethodtypeprovider":"listPaymentMethodTypeProvider",
            "edit-paymentmethodtypeprovider":"editPaymentMethodTypeProvider",
            "edit-paymentmethodtypeprovider/:id":"editPaymentMethodTypeProvider",
            "list-contactmechanismlink":"listContactMechanismLink",
            "edit-contactmechanismlink":"editContactMechanismLink",
            "edit-contactmechanismlink/:id":"editContactMechanismLink",
            "list-productorderitemtype":"listProductOrderItemType",
            "edit-productorderitemtype":"editProductOrderItemType",
            "edit-productorderitemtype/:id":"editProductOrderItemType",
            "list-postaladdressboundry":"listPostalAddressBoundry",
            "edit-postaladdressboundry":"editPostalAddressBoundry",
            "edit-postaladdressboundry/:id":"editPostalAddressBoundry",
            "list-communicationeventworkeffort":"listCommunicationEventWorkEffort",
            "edit-communicationeventworkeffort":"editCommunicationEventWorkEffort",
            "edit-communicationeventworkeffort/:id":"editCommunicationEventWorkEffort",
            "list-estimatedproductcost":"listEstimatedProductCost",
            "edit-estimatedproductcost":"editEstimatedProductCost",
            "edit-estimatedproductcost/:id":"editEstimatedProductCost",
            "list-partycontactmechanism":"listPartyContactMechanism",
            "edit-partycontactmechanism":"editPartyContactMechanism",
            "edit-partycontactmechanism/:id":"editPartyContactMechanism",
            "list-currency":"listCurrency",
            "edit-currency":"editCurrency",
            "edit-currency/:id":"editCurrency",
            "list-partyclassificationtype":"listPartyClassificationType",
            "edit-partyclassificationtype":"editPartyClassificationType",
            "edit-partyclassificationtype/:id":"editPartyClassificationType",
            "list-billingaccount":"listBillingAccount",
            "edit-billingaccount":"editBillingAccount",
            "edit-billingaccount/:id":"editBillingAccount",
            "list-invoiceroletype":"listInvoiceRoleType",
            "edit-invoiceroletype":"editInvoiceRoleType",
            "edit-invoiceroletype/:id":"editInvoiceRoleType",
            "list-partyclassification":"listPartyClassification",
            "edit-partyclassification":"editPartyClassification",
            "edit-partyclassification/:id":"editPartyClassification",
            "list-customer":"listCustomer",
            "edit-customer":"editCustomer",
            "edit-customer/:id":"editCustomer",
            "list-contactmechanismpurposetype":"listContactMechanismPurposeType",
            "edit-contactmechanismpurposetype":"editContactMechanismPurposeType",
            "edit-contactmechanismpurposetype/:id":"editContactMechanismPurposeType",
            "list-partycase":"listPartyCase",
            "edit-partycase":"editPartyCase",
            "edit-partycase/:id":"editPartyCase",
            "list-paymenttype":"listPaymentType",
            "edit-paymenttype":"editPaymentType",
            "edit-paymenttype/:id":"editPaymentType",
            "list-communicationevent":"listCommunicationEvent",
            "edit-communicationevent":"editCommunicationEvent",
            "edit-communicationevent/:id":"editCommunicationEvent",
            "list-geoboundarytype":"listGeoBoundaryType",
            "edit-geoboundarytype":"editGeoBoundaryType",
            "edit-geoboundarytype/:id":"editGeoBoundaryType",
            "list-invoiceitemcategory":"listInvoiceItemCategory",
            "edit-invoiceitemcategory":"editInvoiceItemCategory",
            "edit-invoiceitemcategory/:id":"editInvoiceItemCategory",
            "list-productclassification":"listProductClassification",
            "edit-productclassification":"editProductClassification",
            "edit-productclassification/:id":"editProductClassification",
            "list-partyroletype":"listPartyRoleType",
            "edit-partyroletype":"editPartyRoleType",
            "edit-partyroletype/:id":"editPartyRoleType",
            "list-invoicerole":"listInvoiceRole",
            "edit-invoicerole":"editInvoiceRole",
            "edit-invoicerole/:id":"editInvoiceRole",
            "list-uom":"listUom",
            "edit-uom":"editUom",
            "edit-uom/:id":"editUom",
            "list-productfeature":"listProductFeature",
            "edit-productfeature":"editProductFeature",
            "edit-productfeature/:id":"editProductFeature",
            "list-productcomponent":"listProductComponent",
            "edit-productcomponent":"editProductComponent",
            "edit-productcomponent/:id":"editProductComponent",
            "list-contactmechanism":"listContactMechanism",
            "edit-contactmechanism":"editContactMechanism",
            "edit-contactmechanism/:id":"editContactMechanism",
            "list-uomconversion":"listUomConversion",
            "edit-uomconversion":"editUomConversion",
            "edit-uomconversion/:id":"editUomConversion",
            "list-productfeatureapplicabilitytype":"listProductFeatureApplicabilityType",
            "edit-productfeatureapplicabilitytype":"editProductFeatureApplicabilityType",
            "edit-productfeatureapplicabilitytype/:id":"editProductFeatureApplicabilityType",
            "list-postaladdress":"listPostalAddress",
            "edit-postaladdress":"editPostalAddress",
            "edit-postaladdress/:id":"editPostalAddress",
            "list-invoiceterm":"listInvoiceTerm",
            "edit-invoiceterm":"editInvoiceTerm",
            "edit-invoiceterm/:id":"editInvoiceTerm",
            "list-orderitembilling":"listOrderItemBilling",
            "edit-orderitembilling":"editOrderItemBilling",
            "edit-orderitembilling/:id":"editOrderItemBilling",
            "list-communicationeventpurposetype":"listCommunicationEventPurposeType",
            "edit-communicationeventpurposetype":"editCommunicationEventPurposeType",
            "edit-communicationeventpurposetype/:id":"editCommunicationEventPurposeType",
            "list-productfeaturetype":"listProductFeatureType",
            "edit-productfeaturetype":"editProductFeatureType",
            "edit-productfeaturetype/:id":"editProductFeatureType",
            "list-productorderitem":"listProductOrderItem",
            "edit-productorderitem":"editProductOrderItem",
            "edit-productorderitem/:id":"editProductOrderItem",
            "list-communicationeventstatustype":"listCommunicationEventStatusType",
            "edit-communicationeventstatustype":"editCommunicationEventStatusType",
            "edit-communicationeventstatustype/:id":"editCommunicationEventStatusType",
            "list-billingaccountrole":"listBillingAccountRole",
            "edit-billingaccountrole":"editBillingAccountRole",
            "edit-billingaccountrole/:id":"editBillingAccountRole",
            "list-productfeatureapplicability":"listProductFeatureApplicability",
            "edit-productfeatureapplicability":"editProductFeatureApplicability",
            "edit-productfeatureapplicability/:id":"editProductFeatureApplicability",
            "list-roletype":"listRoleType",
            "edit-roletype":"editRoleType",
            "edit-roletype/:id":"editRoleType",
            "list-casestatustype":"listCaseStatusType",
            "edit-casestatustype":"editCaseStatusType",
            "edit-casestatustype/:id":"editCaseStatusType",
            "list-workefforttype":"listWorkEffortType",
            "edit-workefforttype":"editWorkEffortType",
            "edit-workefforttype/:id":"editWorkEffortType",
            "list-geoboundry":"listGeoBoundry",
            "edit-geoboundry":"editGeoBoundry",
            "edit-geoboundry/:id":"editGeoBoundry",
            "list-product":"listProduct",
            "edit-product":"editProduct",
            "edit-product/:id":"editProduct",
            "list-productordertype":"listProductOrderType",
            "edit-productordertype":"editProductOrderType",
            "edit-productordertype/:id":"editProductOrderType",
            "list-telecommunicationsnumber":"listTelecommunicationsNumber",
            "edit-telecommunicationsnumber":"editTelecommunicationsNumber",
            "edit-telecommunicationsnumber/:id":"editTelecommunicationsNumber",
            "list-party":"listParty",
            "edit-party":"editParty",
            "edit-party/:id":"editParty",
            "list-producttype":"listProductType",
            "edit-producttype":"editProductType",
            "edit-producttype/:id":"editProductType",
            "list-invoicetype":"listInvoiceType",
            "edit-invoicetype":"editInvoiceType",
            "edit-invoicetype/:id":"editInvoiceType",
            "list-payment":"listPayment",
            "edit-payment":"editPayment",
            "edit-payment/:id":"editPayment",
            "list-productcategory":"listProductCategory",
            "edit-productcategory":"editProductCategory",
            "edit-productcategory/:id":"editProductCategory",
            "list-invoice":"listInvoice",
            "edit-invoice":"editInvoice",
            "edit-invoice/:id":"editInvoice",
            "list-statustype":"listStatusType",
            "edit-statustype":"editStatusType",
            "edit-statustype/:id":"editStatusType",
            "list-billingaccountroletype":"listBillingAccountRoleType",
            "edit-billingaccountroletype":"editBillingAccountRoleType",
            "edit-billingaccountroletype/:id":"editBillingAccountRoleType",
            "list-partycontactmechanismpurpose":"listPartyContactMechanismPurpose",
            "edit-partycontactmechanismpurpose":"editPartyContactMechanismPurpose",
            "edit-partycontactmechanismpurpose/:id":"editPartyContactMechanismPurpose",
            "list-contactmechanismtype":"listContactMechanismType",
            "edit-contactmechanismtype":"editContactMechanismType",
            "edit-contactmechanismtype/:id":"editContactMechanismType",
            "list-person":"listPerson",
            "edit-person":"editPerson",
            "edit-person/:id":"editPerson",
            "list-partyrelationship":"listPartyRelationship",
            "edit-partyrelationship":"editPartyRelationship",
            "edit-partyrelationship/:id":"editPartyRelationship",
            "list-servicechannel":"listServiceChannel",
            "edit-servicechannel":"editServiceChannel",
            "edit-servicechannel/:id":"editServiceChannel",
            "list-invoicestatustype":"listInvoiceStatusType",
            "edit-invoicestatustype":"editInvoiceStatusType",
            "edit-invoicestatustype/:id":"editInvoiceStatusType",
            "list-productorder":"listProductOrder",
            "edit-productorder":"editProductOrder",
            "edit-productorder/:id":"editProductOrder",
            "list-invoicestatus":"listInvoiceStatus",
            "edit-invoicestatus":"editInvoiceStatus",
            "edit-invoicestatus/:id":"editInvoiceStatus",
            "list-invoiceitem":"listInvoiceItem",
            "edit-invoiceitem":"editInvoiceItem",
            "edit-invoiceitem/:id":"editInvoiceItem",
            "list-caserole":"listCaseRole",
            "edit-caserole":"editCaseRole",
            "edit-caserole/:id":"editCaseRole",
            "list-caseroletype":"listCaseRoleType",
            "edit-caseroletype":"editCaseRoleType",
            "edit-caseroletype/:id":"editCaseRoleType",
            "list-paymentmethodtype":"listPaymentMethodType",
            "edit-paymentmethodtype":"editPaymentMethodType",
            "edit-paymentmethodtype/:id":"editPaymentMethodType",
            "list-communicationeventpurpose":"listCommunicationEventPurpose",
            "edit-communicationeventpurpose":"editCommunicationEventPurpose",
            "edit-communicationeventpurpose/:id":"editCommunicationEventPurpose",
            "list-electronicaddress":"listElectronicAddress",
            "edit-electronicaddress":"editElectronicAddress",
            "edit-electronicaddress/:id":"editElectronicAddress",
            "list-costcomponenttype":"listCostComponentType",
            "edit-costcomponenttype":"editCostComponentType",
            "edit-costcomponenttype/:id":"editCostComponentType",
            "list-communicationeventtype":"listCommunicationEventType",
            "edit-communicationeventtype":"editCommunicationEventType",
            "edit-communicationeventtype/:id":"editCommunicationEventType",
            "list-customerblacklist":"listCustomerBlacklist",
            "edit-customerblacklist":"editCustomerBlacklist",
            "edit-customerblacklist/:id":"editCustomerBlacklist",
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
        customerModuleIndex:function()
        {
            utilities.viewManager.showView(new CustomerHomeView({el:$("#content-container")}));
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