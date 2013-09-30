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
     * Used for CRUD operations against individual Activities
     */
    var ActivityModel = Backbone.Model.extend({
        initialize: function(props){
        	this.entityId = props.entityId;
            this.activityURL = props.activityURL;
            this.entityName = 'ApplicationActivity';
            this.urlRoot = config.baseUrl + 'rest/activity/?activityURL=' + this.activityURL + "&entityId=" + this.entityId;
        },
        
        validate: function (attrs) {
            var errors = [];
            /*if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.organization_party + '.'});
        	}*/	
            if (!attrs.businessObjectName) {
                errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.activity_code + '.'});
            }
            if (!attrs.activityURL) {
                errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.activity_name + '.'});
            }
            return errors.length > 0 ? errors : false;
        }/*,
        toJSON: function(options) {
            var attr = _.clone(this.attributes);
            delete attr.fields;
            //delete attr.activityURL;
            delete attr.entity;
            delete attr.relatedActivities;
            delete attr.relatedEntitiesListData;
            delete attr.errorCode;
            delete attr.errorMessage;
            // This will affect other entities 
            // that have displayNm as a field
            // seems to be causing trouble in CustomerAccount model
            //delete attr.displayNm;
            return attr;
        }*//*,
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