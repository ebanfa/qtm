/**
 * Module for the Advice model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Advice model class definition
     * Used for CRUD operations against individual Advice
     */
    var Advice = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/advice', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.advice_party + '.'});
        	}	
            if (!attrs.adviceStatus) {
            	errors.push({name: 'adviceStatus', message: entities_strings.alantra_form_field_required + entities_strings.advice_advicestatus + '.'});
        	}	
            if (!attrs.communicationEvent) {
            	errors.push({name: 'communicationEvent', message: entities_strings.alantra_form_field_required + entities_strings.advice_communicationevent + '.'});
        	}	
            if (!attrs.adviceType) {
            	errors.push({name: 'adviceType', message: entities_strings.alantra_form_field_required + entities_strings.advice_advicetype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.advice_name + '.'});
        	}	
            if (!attrs.accountNo) {
            	errors.push({name: 'accountNo', message: entities_strings.alantra_form_field_required + entities_strings.advice_accountno + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.advice_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.advice_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.advice_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Advice class
    return Advice;
});