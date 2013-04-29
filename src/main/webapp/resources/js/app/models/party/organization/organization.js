/**
 * Module for the Organization model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Organization model class definition
     * Used for CRUD operations against individual Organization
     */
    var Organization = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/organization', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            /*if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.organization_party + '.'});
        	}*/	
            if (!attrs.name) {
                errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.party_name + '.'});
            }   
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.organization_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.organization_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.organization_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Organization class
    return Organization;
});