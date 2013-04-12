/**
 * Module for the Services collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/service/service',
    'configuration',
    'backbone'
], function (Service, config) {
    /**
     *  Here we define the Service collection
     *  We will use it for CRUD operations on Bookings
     */
    var Services = Backbone.Collection.extend({
        url: config.baseUrl + "rest/service", // the URL for performing CRUD operations
        model: Service,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Services;
});