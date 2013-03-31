/**
 * Module for the PaymentMethodTypeProviders collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/paymentmethodtypeprovider/paymentmethodtypeprovider',
    'configuration',
    'backbone'
], function (PaymentMethodTypeProvider, config) {
    /**
     *  Here we define the PaymentMethodTypeProvider collection
     *  We will use it for CRUD operations on Bookings
     */
    var PaymentMethodTypeProviders = Backbone.Collection.extend({
        url: config.baseUrl + "rest/paymentmethodtypeprovider", // the URL for performing CRUD operations
        model: PaymentMethodTypeProvider,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PaymentMethodTypeProviders;
});