/**
 * Module for the GeoBoundrys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/geoboundry/geoboundry',
    'configuration',
    'backbone'
], function (GeoBoundry, config) {
    /**
     *  Here we define the GeoBoundry collection
     *  We will use it for CRUD operations on Bookings
     */
    var GeoBoundrys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/geoboundry", // the URL for performing CRUD operations
        model: GeoBoundry,
        id:"id" // the 'id' property of the model is the identifier
    });
    return GeoBoundrys;
});