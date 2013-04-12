/**
 * Module for the ServiceModes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/servicemode/servicemode',
    'configuration',
    'backbone'
], function (ServiceMode, config) {
    /**
     *  Here we define the ServiceMode collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceModes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicemode", // the URL for performing CRUD operations
        model: ServiceMode,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceModes;
});