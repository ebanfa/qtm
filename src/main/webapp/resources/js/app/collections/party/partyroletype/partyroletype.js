/**
 * Module for the PartyRoleTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partyroletype/partyroletype',
    'configuration',
    'backbone'
], function (PartyRoleType, config) {
    /**
     *  Here we define the PartyRoleType collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyRoleTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partyroletype", // the URL for performing CRUD operations
        model: PartyRoleType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyRoleTypes;
});