/**
 * Module for the CommunicationEventTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/messaging/communicationeventtype/communicationeventtype',
    'configuration',
    'backbone'
], function (CommunicationEventType, config) {
    /**
     *  Here we define the CommunicationEventType collection
     *  We will use it for CRUD operations on Bookings
     */
    var CommunicationEventTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/communicationeventtype", // the URL for performing CRUD operations
        model: CommunicationEventType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CommunicationEventTypes;
});