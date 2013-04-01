/**
 * Module for the Party model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Party model class definition
     * Used for CRUD operations against individual Party
     */
    var Party = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/party', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.partyType) {
            	errors.push({name: 'partyType', message: entities_strings.alantra_form_field_required + entities_strings.party_partytype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.party_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.party_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.party_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.party_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Party class
    return Party;
});