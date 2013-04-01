/**
 * Module for the CommunicationEventPurposeTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/messaging/communicationeventpurposetype/communicationeventpurposetype',
    'configuration',
    'backbone'
], function (CommunicationEventPurposeType, config) {
    /**
     *  Here we define the CommunicationEventPurposeType collection
     *  We will use it for CRUD operations on Bookings
     */
    var CommunicationEventPurposeTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/communicationeventpurposetype", // the URL for performing CRUD operations
        model: CommunicationEventPurposeType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CommunicationEventPurposeTypes;
});