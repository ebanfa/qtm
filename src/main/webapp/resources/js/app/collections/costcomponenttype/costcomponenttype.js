/**
 * Module for the CostComponentTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/costcomponenttype/costcomponenttype',
    'configuration',
    'backbone'
], function (CostComponentType, config) {
    /**
     *  Here we define the CostComponentType collection
     *  We will use it for CRUD operations on Bookings
     */
    var CostComponentTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/costcomponenttype", // the URL for performing CRUD operations
        model: CostComponentType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CostComponentTypes;
});