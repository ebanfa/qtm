/**
 * Module for the ContactMechanismType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ContactMechanismType model class definition
     * Used for CRUD operations against individual ContactMechanismType
     */
    var ContactMechanismType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/contactmechanismtype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismtype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismtype_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismtype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismtype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ContactMechanismType class
    return ContactMechanismType;
});