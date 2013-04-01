/**
 * Module for the PartyCases collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partycase/partycase',
    'configuration',
    'backbone'
], function (PartyCase, config) {
    /**
     *  Here we define the PartyCase collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyCases = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partycase", // the URL for performing CRUD operations
        model: PartyCase,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyCases;
});