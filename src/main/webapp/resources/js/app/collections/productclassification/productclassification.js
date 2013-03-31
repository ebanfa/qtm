/**
 * Module for the ProductClassifications collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/productclassification/productclassification',
    'configuration',
    'backbone'
], function (ProductClassification, config) {
    /**
     *  Here we define the ProductClassification collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductClassifications = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productclassification", // the URL for performing CRUD operations
        model: ProductClassification,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductClassifications;
});