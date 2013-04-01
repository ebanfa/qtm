/**
 * Module for the BillingAccountRoles collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/customer/billingaccountrole/billingaccountrole',
    'configuration',
    'backbone'
], function (BillingAccountRole, config) {
    /**
     *  Here we define the BillingAccountRole collection
     *  We will use it for CRUD operations on Bookings
     */
    var BillingAccountRoles = Backbone.Collection.extend({
        url: config.baseUrl + "rest/billingaccountrole", // the URL for performing CRUD operations
        model: BillingAccountRole,
        id:"id" // the 'id' property of the model is the identifier
    });
    return BillingAccountRoles;
});