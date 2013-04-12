/**
 * Module for the ServicePeers collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/servicepeer/servicepeer',
    'configuration',
    'backbone'
], function (ServicePeer, config) {
    /**
     *  Here we define the ServicePeer collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServicePeers = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicepeer", // the URL for performing CRUD operations
        model: ServicePeer,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServicePeers;
});