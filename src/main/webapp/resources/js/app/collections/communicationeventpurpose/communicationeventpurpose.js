/**
 * Module for the CommunicationEventPurposes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/communicationeventpurpose/communicationeventpurpose',
    'configuration',
    'backbone'
], function (CommunicationEventPurpose, config) {
    /**
     *  Here we define the CommunicationEventPurpose collection
     *  We will use it for CRUD operations on Bookings
     */
    var CommunicationEventPurposes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/communicationeventpurpose", // the URL for performing CRUD operations
        model: CommunicationEventPurpose,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CommunicationEventPurposes;
});