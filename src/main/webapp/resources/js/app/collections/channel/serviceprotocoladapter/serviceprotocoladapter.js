/**
 * Module for the ServiceProtocolAdapters collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/serviceprotocoladapter/serviceprotocoladapter',
    'configuration',
    'backbone'
], function (ServiceProtocolAdapter, config) {
    /**
     *  Here we define the ServiceProtocolAdapter collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceProtocolAdapters = Backbone.Collection.extend({
        url: config.baseUrl + "rest/serviceprotocoladapter", // the URL for performing CRUD operations
        model: ServiceProtocolAdapter,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceProtocolAdapters;
});