/**
 * Module for the Payment model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Payment model class definition
     * Used for CRUD operations against individual Payment
     */
    var Payment = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/payment', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.paymentType) {
            	errors.push({name: 'paymentType', message: entities_strings.alantra_form_field_required + entities_strings.payment_paymenttype + '.'});
        	}	
            if (!attrs.partyByToPartyId) {
            	errors.push({name: 'partyByToPartyId', message: entities_strings.alantra_form_field_required + entities_strings.payment_partybytopartyid + '.'});
        	}	
            if (!attrs.partyByFromPartyId) {
            	errors.push({name: 'partyByFromPartyId', message: entities_strings.alantra_form_field_required + entities_strings.payment_partybyfrompartyid + '.'});
        	}	
            if (!attrs.paymentMethodType) {
            	errors.push({name: 'paymentMethodType', message: entities_strings.alantra_form_field_required + entities_strings.payment_paymentmethodtype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.payment_name + '.'});
        	}	
            if (!attrs.amount) {
            	errors.push({name: 'amount', message: entities_strings.alantra_form_field_required + entities_strings.payment_amount + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.payment_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.payment_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.payment_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Payment class
    return Payment;
});