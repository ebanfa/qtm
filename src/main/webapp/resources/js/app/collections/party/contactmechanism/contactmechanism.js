/**
 * Module for the ContactMechanisms collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/contactmechanism/contactmechanism',
    'configuration',
    'backbone'
], function (ContactMechanism, config) {
    /**
     *  Here we define the ContactMechanism collection
     *  We will use it for CRUD operations on Bookings
     */
    var ContactMechanisms = Backbone.Collection.extend({
        url: config.baseUrl + "rest/contactmechanism", // the URL for performing CRUD operations
        model: ContactMechanism,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ContactMechanisms;
});