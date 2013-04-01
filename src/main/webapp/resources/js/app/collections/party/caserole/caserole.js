/**
 * Module for the CaseRoles collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/caserole/caserole',
    'configuration',
    'backbone'
], function (CaseRole, config) {
    /**
     *  Here we define the CaseRole collection
     *  We will use it for CRUD operations on Bookings
     */
    var CaseRoles = Backbone.Collection.extend({
        url: config.baseUrl + "rest/caserole", // the URL for performing CRUD operations
        model: CaseRole,
        id:"id" // the 'id' property of the model is the identifier
    });
    return CaseRoles;
});