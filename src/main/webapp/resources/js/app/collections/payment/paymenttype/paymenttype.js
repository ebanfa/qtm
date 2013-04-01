/**
 * Module for the PaymentTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/payment/paymenttype/paymenttype',
    'configuration',
    'backbone'
], function (PaymentType, config) {
    /**
     *  Here we define the PaymentType collection
     *  We will use it for CRUD operations on Bookings
     */
    var PaymentTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/paymenttype", // the URL for performing CRUD operations
        model: PaymentType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PaymentTypes;
});