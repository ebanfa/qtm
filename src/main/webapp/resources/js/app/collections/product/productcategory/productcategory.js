/**
 * Module for the ProductCategorys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productcategory/productcategory',
    'configuration',
    'backbone'
], function (ProductCategory, config) {
    /**
     *  Here we define the ProductCategory collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductCategorys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productcategory", // the URL for performing CRUD operations
        model: ProductCategory,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductCategorys;
});