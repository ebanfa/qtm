/**
 * Module for the PartyRelationships collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/partyrelationship/partyrelationship',
    'configuration',
    'backbone'
], function (PartyRelationship, config) {
    /**
     *  Here we define the PartyRelationship collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyRelationships = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partyrelationship", // the URL for performing CRUD operations
        model: PartyRelationship,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyRelationships;
});