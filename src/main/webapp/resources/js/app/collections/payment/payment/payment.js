/**
 * Module for the Payments collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/payment/payment/payment',
    'configuration',
    'backbone'
], function (Payment, config) {
    /**
     *  Here we define the Payment collection
     *  We will use it for CRUD operations on Bookings
     */
    var Payments = Backbone.Collection.extend({
        url: config.baseUrl + "rest/payment", // the URL for performing CRUD operations
        model: Payment,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Payments;
});