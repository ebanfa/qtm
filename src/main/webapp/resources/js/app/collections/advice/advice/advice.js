/**
 * Module for the Advices collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/advice/advice/advice',
    'configuration',
    'backbone'
], function (Advice, config) {
    /**
     *  Here we define the Advice collection
     *  We will use it for CRUD operations on Bookings
     */
    var Advices = Backbone.Collection.extend({
        url: config.baseUrl + "rest/advice", // the URL for performing CRUD operations
        model: Advice,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Advices;
});