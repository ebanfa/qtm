/**
 * Module for the PartyRoles collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partyrole/partyrole',
    'configuration',
    'backbone'
], function (PartyRole, config) {
    /**
     *  Here we define the PartyRole collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyRoles = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partyrole", // the URL for performing CRUD operations
        model: PartyRole,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyRoles;
});