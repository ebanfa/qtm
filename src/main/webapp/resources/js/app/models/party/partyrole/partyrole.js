/**
 * Module for the PartyRole model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PartyRole model class definition
     * Used for CRUD operations against individual PartyRole
     */
    var PartyRole = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/partyrole', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_party + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_name + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_fromdt + '.'});
        	}	
            if (!attrs.thruDt) {
            	errors.push({name: 'thruDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_thrudt + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.partyrole_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PartyRole class
    return PartyRole;
});