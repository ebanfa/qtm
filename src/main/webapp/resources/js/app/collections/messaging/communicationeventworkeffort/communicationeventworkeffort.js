/**
 * Module for the CommunicationEventWorkEfforts collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/messaging/communicationeventworkeffort/communicationeventworkeffort',
    'configuration',
    'backbone'
], function (CommunicationEventWorkEffort, config) {
    /**
     *  Here we define the CommunicationEventWorkEffort collection
     *  We will use it for CRUD operations on Bookings
     */
    var CommunicationEventWorkEfforts = Backbone.Collection.extend({
        url: config.baseUrl + "rest/communicationeventworkeffort", // the URL for performing CRUD operations
        model: CommunicationEventWorkEffort,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CommunicationEventWorkEfforts;
});