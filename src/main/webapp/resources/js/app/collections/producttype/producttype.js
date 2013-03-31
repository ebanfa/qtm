/**
 * Module for the ProductTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/producttype/producttype',
    'configuration',
    'backbone'
], function (ProductType, config) {
    /**
     *  Here we define the ProductType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/producttype", // the URL for performing CRUD operations
        model: ProductType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductTypes;
});