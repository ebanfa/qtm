/**
 * Module for the BillingAccountRole model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The BillingAccountRole model class definition
     * Used for CRUD operations against individual BillingAccountRole
     */
    var BillingAccountRole = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/billingaccountrole', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.billingAccountRoleType) {
            	errors.push({name: 'billingAccountRoleType', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_billingaccountroletype + '.'});
        	}	
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_party + '.'});
        	}	
            if (!attrs.billingAccount) {
            	errors.push({name: 'billingAccount', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_billingaccount + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_name + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_fromdt + '.'});
        	}	
            if (!attrs.thruDt) {
            	errors.push({name: 'thruDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_thrudt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountrole_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the BillingAccountRole class
    return BillingAccountRole;
});