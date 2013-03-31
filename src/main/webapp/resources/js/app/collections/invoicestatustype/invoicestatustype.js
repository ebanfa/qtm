/**
 * Module for the InvoiceStatusTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/invoicestatustype/invoicestatustype',
    'configuration',
    'backbone'
], function (InvoiceStatusType, config) {
    /**
     *  Here we define the InvoiceStatusType collection
     *  We will use it for CRUD operations on Bookings
     */
    var InvoiceStatusTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/invoicestatustype", // the URL for performing CRUD operations
        model: InvoiceStatusType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return InvoiceStatusTypes;
});