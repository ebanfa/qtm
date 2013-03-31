/**
 * Module for the StatusTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/statustype/statustype',
    'configuration',
    'backbone'
], function (StatusType, config) {
    /**
     *  Here we define the StatusType collection
     *  We will use it for CRUD operations on Bookings
     */
    var StatusTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/statustype", // the URL for performing CRUD operations
        model: StatusType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return StatusTypes;
});