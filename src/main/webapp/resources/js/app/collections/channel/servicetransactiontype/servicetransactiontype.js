/**
 * Module for the ServiceTransactionTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/servicetransactiontype/servicetransactiontype',
    'configuration',
    'backbone'
], function (ServiceTransactionType, config) {
    /**
     *  Here we define the ServiceTransactionType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceTransactionTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicetransactiontype", // the URL for performing CRUD operations
        model: ServiceTransactionType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceTransactionTypes;
});