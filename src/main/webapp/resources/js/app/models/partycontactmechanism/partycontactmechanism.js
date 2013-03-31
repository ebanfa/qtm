/**
 * Module for the PartyContactMechanism model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyContactMechanism model class definition
     * Used for CRUD operations against individual PartyContactMechanism
     */
    var PartyContactMechanism = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partycontactmechanism', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanism) {
            	errors.push({name: 'contactMechanism', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_contactmechanism + '.'});
        	}	
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_party + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_code + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_fromdt + '.'});
        	}	
            if (!attrs.toDt) {
            	errors.push({name: 'toDt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_todt + '.'});
        	}	
            if (!attrs.noSolicitationFg) {
            	errors.push({name: 'noSolicitationFg', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_nosolicitationfg + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanism_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyContactMechanism class
    return PartyContactMechanism;
});