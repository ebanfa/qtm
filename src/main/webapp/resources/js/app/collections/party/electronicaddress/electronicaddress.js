/**
 * Module for the ElectronicAddresss collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/electronicaddress/electronicaddress',
    'configuration',
    'backbone'
], function (ElectronicAddress, config) {
    /**
     *  Here we define the ElectronicAddress collection
     *  We will use it for CRUD operations on Bookings
     */
    var ElectronicAddresss = Backbone.Collection.extend({
        url: config.baseUrl + "rest/electronicaddress", // the URL for performing CRUD operations
        model: ElectronicAddress,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ElectronicAddresss;
});