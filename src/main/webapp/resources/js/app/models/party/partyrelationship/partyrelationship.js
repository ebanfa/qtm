/**
 * Module for the PartyRelationship model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyRelationship model class definition
     * Used for CRUD operations against individual PartyRelationship
     */
    var PartyRelationship = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partyrelationship', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.fromPartyRole) {
            	errors.push({name: 'fromPartyRole', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_frompartyrole + '.'});
        	}	
            if (!attrs.toPartyRole) {
            	errors.push({name: 'toPartyRole', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_topartyrole + '.'});
        	}	
            if (!attrs.partyRelationshipType) {
            	errors.push({name: 'partyRelationshipType', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_partyrelationshiptype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_name + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_fromdt + '.'});
        	}	
            if (!attrs.thruDt) {
            	errors.push({name: 'thruDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_thrudt + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partyrelationship_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyRelationship class
    return PartyRelationship;
});