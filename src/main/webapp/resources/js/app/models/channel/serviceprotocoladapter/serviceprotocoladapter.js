/**
 * Module for the ServiceProtocolAdapter model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ServiceProtocolAdapter model class definition
     * Used for CRUD operations against individual ServiceProtocolAdapter
     */
    var ServiceProtocolAdapter = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/serviceprotocoladapter', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.serviceprotocoladapter_name + '.'});
        	}	
            if (!attrs.className) {
            	errors.push({name: 'className', message: entities_strings.alantra_form_field_required + entities_strings.serviceprotocoladapter_classname + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.serviceprotocoladapter_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.serviceprotocoladapter_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.serviceprotocoladapter_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ServiceProtocolAdapter class
    return ServiceProtocolAdapter;
});