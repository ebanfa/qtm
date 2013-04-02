/**
 * Module for the AdviceTypeTags collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/advice/advicetypetag/advicetypetag',
    'configuration',
    'backbone'
], function (AdviceTypeTag, config) {
    /**
     *  Here we define the AdviceTypeTag collection
     *  We will use it for CRUD operations on Bookings
     */
    var AdviceTypeTags = Backbone.Collection.extend({
        url: config.baseUrl + "rest/advicetypetag", // the URL for performing CRUD operations
        model: AdviceTypeTag,
        id:"id" // the 'id' property of the model is the identifier
    });
    return AdviceTypeTags;
});