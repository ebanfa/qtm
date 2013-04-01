/**
 * Module for the CaseRoleTypes collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/caseroletype/caseroletype',
    'configuration',
    'backbone'
], function (CaseRoleType, config) {
    /**
     *  Here we define the CaseRoleType collection
     *  We will use it for CRUD operations on Bookings
     */
    var CaseRoleTypes = Backbone.Collection.extend({
        url: config.baseUrl + "rest/caseroletype", // the URL for performing CRUD operations
        model: CaseRoleType,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CaseRoleTypes;
});