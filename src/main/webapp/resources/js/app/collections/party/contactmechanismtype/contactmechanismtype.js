/**
 * Module for the ContactMechanismTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/contactmechanismtype/contactmechanismtype',
    'configuration',
    'backbone'
], function (ContactMechanismType, config) {
    /**
     *  Here we define the ContactMechanismType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ContactMechanismTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/contactmechanismtype", // the URL for performing CRUD operations
        model: ContactMechanismType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ContactMechanismTypes;
});