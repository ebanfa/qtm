/**
 * Module for the InvoiceRoles collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoicerole/invoicerole',
    'configuration',
    'backbone'
], function (InvoiceRole, config) {
    /**
     *  Here we define the InvoiceRole collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceRoles = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoicerole", // the URL for performing CRUD operations
        model: InvoiceRole,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceRoles;
});