/**
 * Module for the CommunicationEventStatusTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/communicationeventstatustype/communicationeventstatustype',
    'configuration',
    'backbone'
], function (CommunicationEventStatusType, config) {
    /**
     *  Here we define the CommunicationEventStatusType collection
     *  We will use it for CRUD operations on Bookings
     */
    var CommunicationEventStatusTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/communicationeventstatustype", // the URL for performing CRUD operations
        model: CommunicationEventStatusType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CommunicationEventStatusTypes;
});