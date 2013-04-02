/**
 * Module for the AdviceStatuss collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/advice/advicestatus/advicestatus',
    'configuration',
    'backbone'
], function (AdviceStatus, config) {
    /**
     *  Here we define the AdviceStatus collection
     *  We will use it for CRUD operations on Bookings
     */
    var AdviceStatuss = Backbone.Collection.extend({
        url: config.baseUrl + "rest/advicestatus", // the URL for performing CRUD operations
        model: AdviceStatus,
        id:"id" // the 'id' property of the model is the identifier
    });
    return AdviceStatuss;
});