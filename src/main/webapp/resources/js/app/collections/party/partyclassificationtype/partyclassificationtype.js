/**
 * Module for the PartyClassificationTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/partyclassificationtype/partyclassificationtype',
    'configuration',
    'backbone'
], function (PartyClassificationType, config) {
    /**
     *  Here we define the PartyClassificationType collection
     *  We will use it for CRUD operations on Bookings
     */
    var PartyClassificationTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/partyclassificationtype", // the URL for performing CRUD operations
        model: PartyClassificationType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return PartyClassificationTypes;
});