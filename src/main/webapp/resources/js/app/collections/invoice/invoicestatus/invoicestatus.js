/**
 * Module for the InvoiceStatuss collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoice/invoicestatus/invoicestatus',
    'configuration',
    'backbone'
], function (InvoiceStatus, config) {
    /**
     *  Here we define the InvoiceStatus collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceStatuss = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoicestatus", // the URL for performing CRUD operations
        model: InvoiceStatus,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceStatuss;
});