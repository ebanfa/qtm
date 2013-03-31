/**
 * Module for the InvoiceItemCategorys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoiceitemcategory/invoiceitemcategory',
    'configuration',
    'backbone'
], function (InvoiceItemCategory, config) {
    /**
     *  Here we define the InvoiceItemCategory collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceItemCategorys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoiceitemcategory", // the URL for performing CRUD operations
        model: InvoiceItemCategory,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceItemCategorys;
});