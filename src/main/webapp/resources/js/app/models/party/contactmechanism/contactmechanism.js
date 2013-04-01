/**
 * Module for the ContactMechanism model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ContactMechanism model class definition
     * Used for CRUD operations against individual ContactMechanism
     */
    var ContactMechanism = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/contactmechanism', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanismType) {
            	errors.push({name: 'contactMechanismType', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanism_contactmechanismtype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanism_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanism_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanism_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanism_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ContactMechanism class
    return ContactMechanism;
});