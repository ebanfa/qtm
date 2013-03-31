/**
 * Module for the CommunicationEvents collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/communicationevent/communicationevent',
    'configuration',
    'backbone'
], function (CommunicationEvent, config) {
    /**
     *  Here we define the CommunicationEvent collection
     *  We will use it for CRUD operations on Bookings
     */
    var CommunicationEvents = Backbone.Collection.extend({
        url: config.baseUrl + "rest/communicationevent", // the URL for performing CRUD operations
        model: CommunicationEvent,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CommunicationEvents;
});