/**
 * Module for the Activity model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'underscore',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Activity model class definition
     * Used for CRUD operations against individual Activity
     */
    var ActivityModel = Backbone.Model.extend({
        initialize: function(props){
            this.urlRoot = config.baseUrl + 'rest/' + props.activityUrl;
        },
        
        validate: function (attrs) {
            var errors = [];
            /*if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.organization_party + '.'});
        	}*/	
            return errors.length > 0 ? errors : false;
        },
        toJSON: function(options) {
            var attr = _.clone(this.attributes);
            delete attr.fields;
            delete attr.entity;
            delete attr.relatedActivities;
            return attr;
        }/*,
        // Overwrite save function
        save: function(attrs, options) {
            options || (options = {});
            options.contentType = 'application/json';
            // Get data
            options.data = JSON.stringify(attrs);
            // Filter the data to send to the server
            console.log(options.data);
            delete options.data.activityName;
            //delete options.data.dontSync;
            // Proxy the call to the original save function
            Backbone.Model.prototype.save.call(this, attrs, options);
        }*/
    });
    // export the Activity Mode; class
    return ActivityModel;
});