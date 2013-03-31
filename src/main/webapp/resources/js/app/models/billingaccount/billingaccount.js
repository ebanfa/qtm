/**
 * Module for the BillingAccount model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The BillingAccount model class definition
     * Used for CRUD operations against individual BillingAccount
     */
    var BillingAccount = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/billingaccount', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contMechId) {
            	errors.push({name: 'contMechId', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_contmechid + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_name + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_fromdt + '.'});
        	}	
            if (!attrs.thruDt) {
            	errors.push({name: 'thruDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_thrudt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccount_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the BillingAccount class
    return BillingAccount;
});