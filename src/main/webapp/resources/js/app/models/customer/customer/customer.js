/**
 * Module for the Customer model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Customer model class definition
     * Used for CRUD operations against individual Customer
     */
    var Customer = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/customer', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.customer_party + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.customer_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.customer_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.customer_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Customer class
    return Customer;
});