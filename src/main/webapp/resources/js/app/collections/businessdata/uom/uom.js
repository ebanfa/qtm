/**
 * Module for the Uoms collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/businessdata/uom/uom',
    'configuration',
    'backbone'
], function (Uom, config) {
    /**
     *  Here we define the Uom collection
     *  We will use it for CRUD operations on Bookings
     */
    var Uoms = Backbone.Collection.extend({
        url: config.baseUrl + "rest/uom", // the URL for performing CRUD operations
        model: Uom,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Uoms;
});