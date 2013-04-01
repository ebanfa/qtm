/**
 * Module for the Invoices collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoice/invoice/invoice',
    'configuration',
    'backbone'
], function (Invoice, config) {
    /**
     *  Here we define the Invoice collection
     *  We will use it for CRUD operations on Bookings
     */
    var Invoices = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoice", // the URL for performing CRUD operations
        model: Invoice,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Invoices;
});