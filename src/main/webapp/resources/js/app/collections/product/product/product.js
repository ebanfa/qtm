/**
 * Module for the Products collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/product/product',
    'configuration',
    'backbone'
], function (Product, config) {
    /**
     *  Here we define the Product collection
     *  We will use it for CRUD operations on Bookings
     */
    var Products = Backbone.Collection.extend({
        url: config.baseUrl + "rest/product", // the URL for performing CRUD operations
        model: Product,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Products;
});