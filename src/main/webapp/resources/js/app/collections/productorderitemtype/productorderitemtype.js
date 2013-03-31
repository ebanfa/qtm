/**
 * Module for the ProductOrderItemTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/productorderitemtype/productorderitemtype',
    'configuration',
    'backbone'
], function (ProductOrderItemType, config) {
    /**
     *  Here we define the ProductOrderItemType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductOrderItemTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productorderitemtype", // the URL for performing CRUD operations
        model: ProductOrderItemType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductOrderItemTypes;
});