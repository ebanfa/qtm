/**
 * Module for the PaymentApplications collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/payment/paymentapplication/paymentapplication',
    'configuration',
    'backbone'
], function (PaymentApplication, config) {
    /**
     *  Here we define the PaymentApplication collection
     *  We will use it for CRUD operations on Bookings
     */
    var PaymentApplications = Backbone.Collection.extend({
        url: config.baseUrl + "rest/paymentapplication", // the URL for performing CRUD operations
        model: PaymentApplication,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PaymentApplications;
});