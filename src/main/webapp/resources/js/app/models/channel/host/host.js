/**
 * Module for the Host model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Host model class definition
     * Used for CRUD operations against individual Host
     */
    var Host = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/host', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.serviceProtocolAdapter) {
            	errors.push({name: 'serviceProtocolAdapter', message: entities_strings.alantra_form_field_required + entities_strings.host_serviceprotocoladapter + '.'});
        	}	
            if (!attrs.hostType) {
            	errors.push({name: 'hostType', message: entities_strings.alantra_form_field_required + entities_strings.host_hosttype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.host_name + '.'});
        	}	
            if (!attrs.portNo) {
            	errors.push({name: 'portNo', message: entities_strings.alantra_form_field_required + entities_strings.host_portno + '.'});
        	}	
            if (!attrs.ipAddress) {
            	errors.push({name: 'ipAddress', message: entities_strings.alantra_form_field_required + entities_strings.host_ipaddress + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.host_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.host_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.host_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Host class
    return Host;
});