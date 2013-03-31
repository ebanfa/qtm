/**
 * Module for the PartyClassifications collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/partyclassification/partyclassification',
    'configuration',
    'backbone'
], function (PartyClassification, config) {
    /**
     *  Here we define the PartyClassification collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyClassifications = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partyclassification", // the URL for performing CRUD operations
        model: PartyClassification,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyClassifications;
});