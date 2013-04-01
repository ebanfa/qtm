/**
 * Module for the PartyContactMechanisms collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partycontactmechanism/partycontactmechanism',
    'configuration',
    'backbone'
], function (PartyContactMechanism, config) {
    /**
     *  Here we define the PartyContactMechanism collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyContactMechanisms = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partycontactmechanism", // the URL for performing CRUD operations
        model: PartyContactMechanism,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyContactMechanisms;
});