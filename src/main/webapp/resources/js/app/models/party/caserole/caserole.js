/**
 * Module for the CaseRole model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CaseRole model class definition
     * Used for CRUD operations against individual CaseRole
     */
    var CaseRole = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/caserole', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.caserole_party + '.'});
        	}	
            if (!attrs.caseRoleType) {
            	errors.push({name: 'caseRoleType', message: entities_strings.alantra_form_field_required + entities_strings.caserole_caseroletype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.caserole_name + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.caserole_description + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.caserole_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.caserole_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.caserole_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CaseRole class
    return CaseRole;
});