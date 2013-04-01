/**
 * Module for the GeoBoundryAssociations collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/businessdata/geoboundryassociation/geoboundryassociation',
    'configuration',
    'backbone'
], function (GeoBoundryAssociation, config) {
    /**
     *  Here we define the GeoBoundryAssociation collection
     *  We will use it for CRUD operations on Bookings
     */
    var GeoBoundryAssociations = Backbone.Collection.extend({
        url: config.baseUrl + "rest/geoboundryassociation", // the URL for performing CRUD operations
        model: GeoBoundryAssociation,
        id:"id" // the 'id' property of the model is the identifier
    });
    return GeoBoundryAssociations;
});