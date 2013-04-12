/**
 * Module for the ServiceTransactions collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/servicetransaction/servicetransaction',
    'configuration',
    'backbone'
], function (ServiceTransaction, config) {
    /**
     *  Here we define the ServiceTransaction collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceTransactions = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicetransaction", // the URL for performing CRUD operations
        model: ServiceTransaction,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceTransactions;
});