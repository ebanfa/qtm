/**
 * Module for the PartyContactMechanismPurpose model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyContactMechanismPurpose model class definition
     * Used for CRUD operations against individual PartyContactMechanismPurpose
     */
    var PartyContactMechanismPurpose = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partycontactmechanismpurpose', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanismPurposeType) {
            	errors.push({name: 'contactMechanismPurposeType', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_contactmechanismpurposetype + '.'});
        	}	
            if (!attrs.contactMechanism) {
            	errors.push({name: 'contactMechanism', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_contactmechanism + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_fromdt + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partycontactmechanismpurpose_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyContactMechanismPurpose class
    return PartyContactMechanismPurpose;
});