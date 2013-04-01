/**
 * Module for the PaymentMethodTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/payment/paymentmethodtype/paymentmethodtype',
    'configuration',
    'backbone'
], function (PaymentMethodType, config) {
    /**
     *  Here we define the PaymentMethodType collection
     *  We will use it for CRUD operations on Bookings
     */
    var PaymentMethodTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/paymentmethodtype", // the URL for performing CRUD operations
        model: PaymentMethodType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PaymentMethodTypes;
});