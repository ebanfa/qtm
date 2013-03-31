/**
 * Module for the PartyContactMechanismPurposes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/partycontactmechanismpurpose/partycontactmechanismpurpose',
    'configuration',
    'backbone'
], function (PartyContactMechanismPurpose, config) {
    /**
     *  Here we define the PartyContactMechanismPurpose collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyContactMechanismPurposes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partycontactmechanismpurpose", // the URL for performing CRUD operations
        model: PartyContactMechanismPurpose,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyContactMechanismPurposes;
});