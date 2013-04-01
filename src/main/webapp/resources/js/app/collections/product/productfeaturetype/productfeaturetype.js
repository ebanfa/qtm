/**
 * Module for the ProductFeatureTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productfeaturetype/productfeaturetype',
    'configuration',
    'backbone'
], function (ProductFeatureType, config) {
    /**
     *  Here we define the ProductFeatureType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductFeatureTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productfeaturetype", // the URL for performing CRUD operations
        model: ProductFeatureType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductFeatureTypes;
});