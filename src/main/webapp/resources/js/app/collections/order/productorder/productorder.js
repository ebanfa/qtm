/**
 * Module for the ProductOrders collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/order/productorder/productorder',
    'configuration',
    'backbone'
], function (ProductOrder, config) {
    /**
     *  Here we define the ProductOrder collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductOrders = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productorder", // the URL for performing CRUD operations
        model: ProductOrder,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductOrders;
});