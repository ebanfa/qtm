/**
 * Module for the ProductOrderTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/productordertype/productordertype',
    'configuration',
    'backbone'
], function (ProductOrderType, config) {
    /**
     *  Here we define the ProductOrderType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductOrderTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productordertype", // the URL for performing CRUD operations
        model: ProductOrderType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductOrderTypes;
});