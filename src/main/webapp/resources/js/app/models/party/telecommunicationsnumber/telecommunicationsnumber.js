/**
 * Module for the TelecommunicationsNumber model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The TelecommunicationsNumber model class definition
     * Used for CRUD operations against individual TelecommunicationsNumber
     */
    var TelecommunicationsNumber = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/telecommunicationsnumber', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanism) {
            	errors.push({name: 'contactMechanism', message: entities_strings.alantra_form_field_required + entities_strings.telecommunicationsnumber_contactmechanism + '.'});
        	}	
            if (!attrs.contactNo) {
            	errors.push({name: 'contactNo', message: entities_strings.alantra_form_field_required + entities_strings.telecommunicationsnumber_contactno + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.telecommunicationsnumber_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.telecommunicationsnumber_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.telecommunicationsnumber_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the TelecommunicationsNumber class
    return TelecommunicationsNumber;
});