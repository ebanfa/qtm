/**
 * Module for the ProductFeatureApplicabilitys collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productfeatureapplicability/productfeatureapplicability',
    'configuration',
    'backbone'
], function (ProductFeatureApplicability, config) {
    /**
     *  Here we define the ProductFeatureApplicability collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductFeatureApplicabilitys = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productfeatureapplicability", // the URL for performing CRUD operations
        model: ProductFeatureApplicability,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductFeatureApplicabilitys;
});