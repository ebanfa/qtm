/**
 * Module for the PostalAddresss collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/postaladdress/postaladdress',
    'configuration',
    'backbone'
], function (PostalAddress, config) {
    /**
     *  Here we define the PostalAddress collection
     *  We will use it for CRUD operations on Bookings
     */
    var PostalAddresss = Backbone.Collection.extend({
        url: config.baseUrl + "rest/postaladdress", // the URL for performing CRUD operations
        model: PostalAddress,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PostalAddresss;
});