/**
 * Module for the ContactMechanismLinks collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/contactmechanismlink/contactmechanismlink',
    'configuration',
    'backbone'
], function (ContactMechanismLink, config) {
    /**
     *  Here we define the ContactMechanismLink collection
     *  We will use it for CRUD operations on Bookings
     */
    var ContactMechanismLinks = Backbone.Collection.extend({
        url: config.baseUrl + "rest/contactmechanismlink", // the URL for performing CRUD operations
        model: ContactMechanismLink,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ContactMechanismLinks;
});