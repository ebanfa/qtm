/**
 * Module for the Partys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/party',
    'configuration',
    'backbone'
], function (Party, config) {
    /**
     *  Here we define the Party collection
     *  We will use it for CRUD operations on Bookings
     */
    var Partys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/party", // the URL for performing CRUD operations
        model: Party,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Partys;
});