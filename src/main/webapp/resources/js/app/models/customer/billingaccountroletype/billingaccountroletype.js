/**
 * Module for the BillingAccountRoleType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The BillingAccountRoleType model class definition
     * Used for CRUD operations against individual BillingAccountRoleType
     */
    var BillingAccountRoleType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/billingaccountroletype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountroletype_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountroletype_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountroletype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.billingaccountroletype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the BillingAccountRoleType class
    return BillingAccountRoleType;
});