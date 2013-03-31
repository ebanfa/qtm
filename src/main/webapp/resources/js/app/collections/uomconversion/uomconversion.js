/**
 * Module for the UomConversions collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/uomconversion/uomconversion',
    'configuration',
    'backbone'
], function (UomConversion, config) {
    /**
     *  Here we define the UomConversion collection
     *  We will use it for CRUD operations on Bookings
     */
    var UomConversions = Backbone.Collection.extend({
        url: config.baseUrl + "rest/uomconversion", // the URL for performing CRUD operations
        model: UomConversion,
        id:"id" // the 'id' property of the model is the identifier
    });
    return UomConversions;
});