/**
 * Module for the EstimatedProductCosts collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/estimatedproductcost/estimatedproductcost',
    'configuration',
    'backbone'
], function (EstimatedProductCost, config) {
    /**
     *  Here we define the EstimatedProductCost collection
     *  We will use it for CRUD operations on Bookings
     */
    var EstimatedProductCosts = Backbone.Collection.extend({
        url: config.baseUrl + "rest/estimatedproductcost", // the URL for performing CRUD operations
        model: EstimatedProductCost,
        id:"id" // the 'id' property of the model is the identifier
    });
    return EstimatedProductCosts;
});