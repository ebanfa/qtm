/**
 * Module for the OrderItemBillings collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/orderitembilling/orderitembilling',
    'configuration',
    'backbone'
], function (OrderItemBilling, config) {
    /**
     *  Here we define the OrderItemBilling collection
     *  We will use it for CRUD operations on Bookings
     */
    var OrderItemBillings = Backbone.Collection.extend({
        url: config.baseUrl + "rest/orderitembilling", // the URL for performing CRUD operations
        model: OrderItemBilling,
        id:"id" // the 'id' property of the model is the identifier
    });
    return OrderItemBillings;
});