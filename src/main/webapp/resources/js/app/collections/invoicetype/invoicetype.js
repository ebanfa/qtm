/**
 * Module for the InvoiceTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoicetype/invoicetype',
    'configuration',
    'backbone'
], function (InvoiceType, config) {
    /**
     *  Here we define the InvoiceType collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoicetype", // the URL for performing CRUD operations
        model: InvoiceType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceTypes;
});