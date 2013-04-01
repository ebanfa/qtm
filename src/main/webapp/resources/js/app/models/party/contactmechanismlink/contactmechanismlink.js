/**
 * Module for the ContactMechanismLink model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ContactMechanismLink model class definition
     * Used for CRUD operations against individual ContactMechanismLink
     */
    var ContactMechanismLink = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/contactmechanismlink', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanismByToId) {
            	errors.push({name: 'contactMechanismByToId', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismlink_contactmechanismbytoid + '.'});
        	}	
            if (!attrs.contactMechanismByFromId) {
            	errors.push({name: 'contactMechanismByFromId', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismlink_contactmechanismbyfromid + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismlink_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismlink_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.contactmechanismlink_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ContactMechanismLink class
    return ContactMechanismLink;
});