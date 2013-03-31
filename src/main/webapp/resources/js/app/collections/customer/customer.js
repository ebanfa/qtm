/**
 * Module for the Customers collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/customer/customer',
    'configuration',
    'backbone'
], function (Customer, config) {
    /**
     *  Here we define the Customer collection
     *  We will use it for CRUD operations on Bookings
     */
    var Customers = Backbone.Collection.extend({
        url: config.baseUrl + "rest/customer", // the URL for performing CRUD operations
        model: Customer,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Customers;
});