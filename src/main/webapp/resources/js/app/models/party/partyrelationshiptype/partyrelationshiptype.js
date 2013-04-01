/**
 * Module for the PartyRelationshipType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyRelationshipType model class definition
     * Used for CRUD operations against individual PartyRelationshipType
     */
    var PartyRelationshipType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partyrelationshiptype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.partyRoleTypeByFromRoleTyId) {
            	errors.push({name: 'partyRoleTypeByFromRoleTyId', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationshiptype_partyroletypebyfromroletyid + '.'});
        	}	
            if (!attrs.partyRoleTypeByToRoleTyId) {
            	errors.push({name: 'partyRoleTypeByToRoleTyId', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationshiptype_partyroletypebytoroletyid + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationshiptype_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationshiptype_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationshiptype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationshiptype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyRelationshipType class
    return PartyRelationshipType;
});