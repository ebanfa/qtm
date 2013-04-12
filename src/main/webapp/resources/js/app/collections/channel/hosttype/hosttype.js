/**
 * Module for the HostTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/hosttype/hosttype',
    'configuration',
    'backbone'
], function (HostType, config) {
    /**
     *  Here we define the HostType collection
     *  We will use it for CRUD operations on Bookings
     */
    var HostTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/hosttype", // the URL for performing CRUD operations
        model: HostType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return HostTypes;
});