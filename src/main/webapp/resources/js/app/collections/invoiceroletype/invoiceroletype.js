/**
 * Module for the InvoiceRoleTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoiceroletype/invoiceroletype',
    'configuration',
    'backbone'
], function (InvoiceRoleType, config) {
    /**
     *  Here we define the InvoiceRoleType collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceRoleTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoiceroletype", // the URL for performing CRUD operations
        model: InvoiceRoleType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceRoleTypes;
});