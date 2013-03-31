/**
 * Module for the WorkEfforts collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/workeffort/workeffort',
    'configuration',
    'backbone'
], function (WorkEffort, config) {
    /**
     *  Here we define the WorkEffort collection
     *  We will use it for CRUD operations on Bookings
     */
    var WorkEfforts = Backbone.Collection.extend({
        url: config.baseUrl + "rest/workeffort", // the URL for performing CRUD operations
        model: WorkEffort,
        id:"id" // the 'id' property of the model is the identifier
    });
    return WorkEfforts;
});