/**
 * Module for the InvoiceTerms collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoice/invoiceterm/invoiceterm',
    'configuration',
    'backbone'
], function (InvoiceTerm, config) {
    /**
     *  Here we define the InvoiceTerm collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceTerms = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoiceterm", // the URL for performing CRUD operations
        model: InvoiceTerm,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceTerms;
});