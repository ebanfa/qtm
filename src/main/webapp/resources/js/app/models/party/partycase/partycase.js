/**
 * Module for the PartyCase model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyCase model class definition
     * Used for CRUD operations against individual PartyCase
     */
    var PartyCase = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partycase', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.caseRole) {
            	errors.push({name: 'caseRole', message: entities_strings.alantra_form_field_required + entities_strings.partycase_caserole + '.'});
        	}	
            if (!attrs.communicationEvent) {
            	errors.push({name: 'communicationEvent', message: entities_strings.alantra_form_field_required + entities_strings.partycase_communicationevent + '.'});
        	}	
            if (!attrs.caseStatusType) {
            	errors.push({name: 'caseStatusType', message: entities_strings.alantra_form_field_required + entities_strings.partycase_casestatustype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.partycase_name + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.partycase_description + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partycase_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partycase_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partycase_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyCase class
    return PartyCase;
});