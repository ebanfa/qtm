/**
 * Module for the ServicePeer model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ServicePeer model class definition
     * Used for CRUD operations against individual ServicePeer
     */
    var ServicePeer = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/servicepeer', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.service) {
            	errors.push({name: 'service', message: entities_strings.alantra_form_field_required + entities_strings.servicepeer_service + '.'});
        	}	
            if (!attrs.host) {
            	errors.push({name: 'host', message: entities_strings.alantra_form_field_required + entities_strings.servicepeer_host + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.servicepeer_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.servicepeer_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.servicepeer_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.servicepeer_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ServicePeer class
    return ServicePeer;
});