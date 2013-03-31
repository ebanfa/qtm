/**
 * Module for the InvoiceTerm model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The InvoiceTerm model class definition
     * Used for CRUD operations against individual InvoiceTerm
     */
    var InvoiceTerm = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoiceterm', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.termType) {
            	errors.push({name: 'termType', message: entities_strings.alantra_form_field_required + entities_strings.invoiceterm_termtype + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoiceterm_code + '.'});
        	}	
            if (!attrs.termVal) {
            	errors.push({name: 'termVal', message: entities_strings.alantra_form_field_required + entities_strings.invoiceterm_termval + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoiceterm_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoiceterm_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the InvoiceTerm class
    return InvoiceTerm;
});