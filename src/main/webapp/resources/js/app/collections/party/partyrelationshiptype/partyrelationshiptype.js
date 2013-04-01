/**
 * Module for the PartyRelationshipTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partyrelationshiptype/partyrelationshiptype',
    'configuration',
    'backbone'
], function (PartyRelationshipType, config) {
    /**
     *  Here we define the PartyRelationshipType collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyRelationshipTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partyrelationshiptype", // the URL for performing CRUD operations
        model: PartyRelationshipType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyRelationshipTypes;
});