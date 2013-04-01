/**
 * Module for the CustomerBlacklists collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/customer/customerblacklist/customerblacklist',
    'configuration',
    'backbone'
], function (CustomerBlacklist, config) {
    /**
     *  Here we define the CustomerBlacklist collection
     *  We will use it for CRUD operations on Bookings
     */
    var CustomerBlacklists = Backbone.Collection.extend({
        url: config.baseUrl + "rest/customerblacklist", // the URL for performing CRUD operations
        model: CustomerBlacklist,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CustomerBlacklists;
});