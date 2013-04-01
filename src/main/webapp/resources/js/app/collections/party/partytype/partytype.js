/**
 * Module for the PartyTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partytype/partytype',
    'configuration',
    'backbone'
], function (PartyType, config) {
    /**
     *  Here we define the PartyType collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partytype", // the URL for performing CRUD operations
        model: PartyType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyTypes;
});