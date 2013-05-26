/**
 * Module for the Module model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'underscore',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Module model class definition
     * Used for CRUD operations against individual Module
     */
    var ModuleModel = Backbone.Model.extend({
        initialize: function(props){
            this.urlRoot = config.baseUrl + 'rest/applicationmodule';
        }
    });
    // export the Module Mode; class
    return ModuleModel;
});