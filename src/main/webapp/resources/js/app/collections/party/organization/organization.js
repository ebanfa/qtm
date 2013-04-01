/**
 * Module for the Organizations collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/organization/organization',
    'configuration',
    'backbone'
], function (Organization, config) {
    /**
     *  Here we define the Organization collection
     *  We will use it for CRUD operations on Bookings
     */
    var Organizations = Backbone.Collection.extend({
        url: config.baseUrl + "rest/organization", // the URL for performing CRUD operations
        model: Organization,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Organizations;
});