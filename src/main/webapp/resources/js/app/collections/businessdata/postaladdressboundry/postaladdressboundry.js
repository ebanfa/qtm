/**
 * Module for the PostalAddressBoundrys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/businessdata/postaladdressboundry/postaladdressboundry',
    'configuration',
    'backbone'
], function (PostalAddressBoundry, config) {
    /**
     *  Here we define the PostalAddressBoundry collection
     *  We will use it for CRUD operations on Bookings
     */
    var PostalAddressBoundrys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/postaladdressboundry", // the URL for performing CRUD operations
        model: PostalAddressBoundry,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PostalAddressBoundrys;
});