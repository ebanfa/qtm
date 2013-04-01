/**
 * Module for the InvoiceItems collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoice/invoiceitem/invoiceitem',
    'configuration',
    'backbone'
], function (InvoiceItem, config) {
    /**
     *  Here we define the InvoiceItem collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceItems = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoiceitem", // the URL for performing CRUD operations
        model: InvoiceItem,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceItems;
});