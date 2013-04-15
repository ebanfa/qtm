/**
 * Module for the ServiceCategorys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/channel/servicecategory/servicecategory',
    'configuration',
    'backbone'
], function (ServiceCategory, config) {
    /**
     *  Here we define the ServiceCategory collection
     *  We will use it for CRUD operations on Bookings
     */
    var ServiceCategorys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/servicecategory", // the URL for performing CRUD operations
        model: ServiceCategory,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ServiceCategorys;
});