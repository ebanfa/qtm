/**
 * Module for the Module collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/module/module',
    'configuration',
    'backbone'
], function (ModuleModel, config) {
    /**
     *  Here we define the Module collection
     *  We will use it for CRUD operations on entities
     */
    var ModuleCollection = Backbone.Collection.extend({
        url: config.baseUrl + "rest/applicationmodule", // the URL for performing CRUD operations
        model: ModuleModel,
        id:"id" // the 'id' property of the model is the identifier
    });
    return ModuleCollection;
});