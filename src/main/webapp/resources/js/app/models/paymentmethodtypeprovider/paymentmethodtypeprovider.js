/**
 * Module for the PaymentMethodTypeProvider model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PaymentMethodTypeProvider model class definition
     * Used for CRUD operations against individual PaymentMethodTypeProvider
     */
    var PaymentMethodTypeProvider = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/paymentmethodtypeprovider', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.paymentMethodType) {
            	errors.push({name: 'paymentMethodType', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_paymentmethodtype + '.'});
        	}	
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_party + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_name + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_fromdt + '.'});
        	}	
            if (!attrs.thruDt) {
            	errors.push({name: 'thruDt', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_thrudt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtypeprovider_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PaymentMethodTypeProvider class
    return PaymentMethodTypeProvider;
});