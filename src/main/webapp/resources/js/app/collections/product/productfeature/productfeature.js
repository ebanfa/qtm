/**
 * Module for the ProductFeatures collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productfeature/productfeature',
    'configuration',
    'backbone'
], function (ProductFeature, config) {
    /**
     *  Here we define the ProductFeature collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductFeatures = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productfeature", // the URL for performing CRUD operations
        model: ProductFeature,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductFeatures;
});