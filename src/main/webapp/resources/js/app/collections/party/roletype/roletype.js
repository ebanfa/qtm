/**
 * Module for the RoleTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/roletype/roletype',
    'configuration',
    'backbone'
], function (RoleType, config) {
    /**
     *  Here we define the RoleType collection
     *  We will use it for CRUD operations on Bookings
     */
    var RoleTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/roletype", // the URL for performing CRUD operations
        model: RoleType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return RoleTypes;
});