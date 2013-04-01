/**
 * Module for the WorkEffortTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/workeffort/workefforttype/workefforttype',
    'configuration',
    'backbone'
], function (WorkEffortType, config) {
    /**
     *  Here we define the WorkEffortType collection
     *  We will use it for CRUD operations on Bookings
     */
    var WorkEffortTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/workefforttype", // the URL for performing CRUD operations
        model: WorkEffortType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return WorkEffortTypes;
});