/**
 * Module for the ContactMechanismPurposeTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/contactmechanismpurposetype/contactmechanismpurposetype',
    'configuration',
    'backbone'
], function (ContactMechanismPurposeType, config) {
    /**
     *  Here we define the ContactMechanismPurposeType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ContactMechanismPurposeTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/contactmechanismpurposetype", // the URL for performing CRUD operations
        model: ContactMechanismPurposeType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ContactMechanismPurposeTypes;
});