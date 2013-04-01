/**
 * Module for the ProductFeatureCategorys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productfeaturecategory/productfeaturecategory',
    'configuration',
    'backbone'
], function (ProductFeatureCategory, config) {
    /**
     *  Here we define the ProductFeatureCategory collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductFeatureCategorys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productfeaturecategory", // the URL for performing CRUD operations
        model: ProductFeatureCategory,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductFeatureCategorys;
});