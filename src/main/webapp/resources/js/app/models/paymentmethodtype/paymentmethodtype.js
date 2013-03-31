/**
 * Module for the PaymentMethodType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PaymentMethodType model class definition
     * Used for CRUD operations against individual PaymentMethodType
     */
    var PaymentMethodType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/paymentmethodtype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtype_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.paymentmethodtype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PaymentMethodType class
    return PaymentMethodType;
});