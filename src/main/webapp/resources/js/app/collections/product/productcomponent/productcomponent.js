/**
 * Module for the ProductComponents collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/product/productcomponent/productcomponent',
    'configuration',
    'backbone'
], function (ProductComponent, config) {
    /**
     *  Here we define the ProductComponent collection
     *  We will use it for CRUD operations on Bookings
     */
    var ProductComponents = Backbone.Collection.extend({
        url: config.baseUrl + "rest/productcomponent", // the URL for performing CRUD operations
        model: ProductComponent,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ProductComponents;
});