/**
 * Module for the Hosts collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/host/host',
    'configuration',
    'backbone'
], function (Host, config) {
    /**
     *  Here we define the Host collection
     *  We will use it for CRUD operations on Bookings
     */
    var Hosts = Backbone.Collection.extend({
        url: config.baseUrl + "rest/host", // the URL for performing CRUD operations
        model: Host,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Hosts;
});