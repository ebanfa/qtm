/**
 * Module for the BillingAccounts collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/customer/billingaccount/billingaccount',
    'configuration',
    'backbone'
], function (BillingAccount, config) {
    /**
     *  Here we define the BillingAccount collection
     *  We will use it for CRUD operations on Bookings
     */
    var BillingAccounts = Backbone.Collection.extend({
        url: config.baseUrl + "rest/billingaccount", // the URL for performing CRUD operations
        model: BillingAccount,
        id:"id" // the 'id' property of the model is the identifier
    });
    return BillingAccounts;
});