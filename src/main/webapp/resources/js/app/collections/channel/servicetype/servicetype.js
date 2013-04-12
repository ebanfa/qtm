/**
 * Module for the ServiceTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/servicetype/servicetype',
    'configuration',
    'backbone'
], function (ServiceType, config) {
    /**
     *  Here we define the ServiceType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicetype", // the URL for performing CRUD operations
        model: ServiceType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceTypes;
});