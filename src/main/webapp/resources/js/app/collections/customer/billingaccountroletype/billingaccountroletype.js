/**
 * Module for the BillingAccountRoleTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/customer/billingaccountroletype/billingaccountroletype',
    'configuration',
    'backbone'
], function (BillingAccountRoleType, config) {
    /**
     *  Here we define the BillingAccountRoleType collection
     *  We will use it for CRUD operations on Bookings
     */
    var BillingAccountRoleTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/billingaccountroletype", // the URL for performing CRUD operations
        model: BillingAccountRoleType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return BillingAccountRoleTypes;
});