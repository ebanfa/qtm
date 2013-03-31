/**
 * Module for the GeoBoundaryTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/geoboundarytype/geoboundarytype',
    'configuration',
    'backbone'
], function (GeoBoundaryType, config) {
    /**
     *  Here we define the GeoBoundaryType collection
     *  We will use it for CRUD operations on Bookings
     */
    var GeoBoundaryTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/geoboundarytype", // the URL for performing CRUD operations
        model: GeoBoundaryType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return GeoBoundaryTypes;
});