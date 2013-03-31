/**
 * Module for the Currency model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Currency model class definition
     * Used for CRUD operations against individual Currency
     */
    var Currency = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/currency', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.currency_code + '.'});
        	}	
            if (!attrs.crncyNm) {
            	errors.push({name: 'crncyNm', message: entities_strings.alantra_form_field_required + entities_strings.currency_crncynm + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.currency_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.currency_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Currency class
    return Currency;
});