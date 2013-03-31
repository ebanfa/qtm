/**
 * Module for the CaseStatusTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/casestatustype/casestatustype',
    'configuration',
    'backbone'
], function (CaseStatusType, config) {
    /**
     *  Here we define the CaseStatusType collection
     *  We will use it for CRUD operations on Bookings
     */
    var CaseStatusTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/casestatustype", // the URL for performing CRUD operations
        model: CaseStatusType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CaseStatusTypes;
});