/**
 * Module for the Service model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Service model class definition
     * Used for CRUD operations against individual Service
     */
    var Service = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/service', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.serviceType) {
            	errors.push({name: 'serviceType', message: entities_strings.alantra_form_field_required + entities_strings.service_servicetype + '.'});
        	}	
            if (!attrs.serviceProtocolAdapter) {
            	errors.push({name: 'serviceProtocolAdapter', message: entities_strings.alantra_form_field_required + entities_strings.service_serviceprotocoladapter + '.'});
        	}	
            if (!attrs.serviceMode) {
            	errors.push({name: 'serviceMode', message: entities_strings.alantra_form_field_required + entities_strings.service_servicemode + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.service_name + '.'});
        	}	
            if (!attrs.portNo) {
            	errors.push({name: 'portNo', message: entities_strings.alantra_form_field_required + entities_strings.service_portno + '.'});
        	}	
            if (!attrs.ipAddress) {
            	errors.push({name: 'ipAddress', message: entities_strings.alantra_form_field_required + entities_strings.service_ipaddress + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.service_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.service_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.service_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Service class
    return Service;
});