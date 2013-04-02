/**
 * Module for the AdviceTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/advice/advicetype/advicetype',
    'configuration',
    'backbone'
], function (AdviceType, config) {
    /**
     *  Here we define the AdviceType collection
     *  We will use it for CRUD operations on Bookings
     */
    var AdviceTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/advicetype", // the URL for performing CRUD operations
        model: AdviceType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return AdviceTypes;
});