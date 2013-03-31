/**
 * Module for the Currencys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/currency/currency',
    'configuration',
    'backbone'
], function (Currency, config) {
    /**
     *  Here we define the Currency collection
     *  We will use it for CRUD operations on Bookings
     */
    var Currencys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/currency", // the URL for performing CRUD operations
        model: Currency,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Currencys;
});