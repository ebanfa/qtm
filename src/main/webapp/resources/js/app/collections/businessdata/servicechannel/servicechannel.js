/**
 * Module for the ServiceChannels collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/businessdata/servicechannel/servicechannel',
    'configuration',
    'backbone'
], function (ServiceChannel, config) {
    /**
     *  Here we define the ServiceChannel collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceChannels = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicechannel", // the URL for performing CRUD operations
        model: ServiceChannel,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceChannels;
});