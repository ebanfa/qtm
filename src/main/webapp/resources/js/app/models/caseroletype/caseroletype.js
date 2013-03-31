/**
 * Module for the CaseRoleType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CaseRoleType model class definition
     * Used for CRUD operations against individual CaseRoleType
     */
    var CaseRoleType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/caseroletype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.caseroletype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.caseroletype_name + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.caseroletype_description + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.caseroletype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.caseroletype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CaseRoleType class
    return CaseRoleType;
});