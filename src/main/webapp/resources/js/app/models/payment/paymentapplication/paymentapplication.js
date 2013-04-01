/**
 * Module for the PaymentApplication model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PaymentApplication model class definition
     * Used for CRUD operations against individual PaymentApplication
     */
    var PaymentApplication = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/paymentapplication', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.payment) {
            	errors.push({name: 'payment', message: entities_strings.alantra_form_field_required + entities_strings.paymentapplication_payment + '.'});
        	}	
            if (!attrs.amountAppl) {
            	errors.push({name: 'amountAppl', message: entities_strings.alantra_form_field_required + entities_strings.paymentapplication_amountappl + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.paymentapplication_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.paymentapplication_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.paymentapplication_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PaymentApplication class
    return PaymentApplication;
});