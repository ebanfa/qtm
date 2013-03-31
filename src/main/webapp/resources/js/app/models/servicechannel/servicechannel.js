/**
 * Module for the ServiceChannel model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ServiceChannel model class definition
     * Used for CRUD operations against individual ServiceChannel
     */
    var ServiceChannel = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/servicechannel', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.servicechannel_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.servicechannel_name + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.servicechannel_description + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.servicechannel_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.servicechannel_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ServiceChannel class
    return ServiceChannel;
});