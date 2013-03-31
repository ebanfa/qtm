/**
 * Module for the ProductOrderItems collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/productorderitem/productorderitem',
    'configuration',
    'backbone'
], function (ProductOrderItem, config) {
    /**
     *  Here we define the ProductOrderItem collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductOrderItems = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productorderitem", // the URL for performing CRUD operations
        model: ProductOrderItem,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductOrderItems;
});