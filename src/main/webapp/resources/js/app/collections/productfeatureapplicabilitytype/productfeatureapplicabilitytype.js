/**
 * Module for the ProductFeatureApplicabilityTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'configuration',
    'backbone'
], function (ProductFeatureApplicabilityType, config) {
    /**
     *  Here we define the ProductFeatureApplicabilityType collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductFeatureApplicabilityTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productfeatureapplicabilitytype", // the URL for performing CRUD operations
        model: ProductFeatureApplicabilityType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductFeatureApplicabilityTypes;
});