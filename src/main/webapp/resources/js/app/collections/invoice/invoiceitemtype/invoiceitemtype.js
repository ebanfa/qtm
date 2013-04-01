/**
 * Module for the InvoiceItemTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoice/invoiceitemtype/invoiceitemtype',
    'configuration',
    'backbone'
], function (InvoiceItemType, config) {
    /**
     *  Here we define the InvoiceItemType collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceItemTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoiceitemtype", // the URL for performing CRUD operations
        model: InvoiceItemType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceItemTypes;
});