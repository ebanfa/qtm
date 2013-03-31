/**
 * Module for the PartyRoleType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyRoleType model class definition
     * Used for CRUD operations against individual PartyRoleType
     */
    var PartyRoleType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partyroletype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.roleType) {
            	errors.push({name: 'roleType', message: entities_strings.alantra_form_field_required + entities_strings.partyroletype_roletype + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partyroletype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.partyroletype_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partyroletype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partyroletype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyRoleType class
    return PartyRoleType;
});