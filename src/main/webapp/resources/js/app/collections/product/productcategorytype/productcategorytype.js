/**
 * Module for the ProductCategoryTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productcategorytype/productcategorytype',
    'configuration',
    'backbone'
], function (ProductCategoryType, config) {
    /**
     *  Here we define the ProductCategoryType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductCategoryTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productcategorytype", // the URL for performing CRUD operations
        model: ProductCategoryType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductCategoryTypes;
});