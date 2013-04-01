/**
 * Module for the TermTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/businessdata/termtype/termtype',
    'configuration',
    'backbone'
], function (TermType, config) {
    /**
     *  Here we define the TermType collection
     *  We will use it for CRUD operations on Bookings
     */
    var TermTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/termtype", // the URL for performing CRUD operations
        model: TermType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return TermTypes;
});