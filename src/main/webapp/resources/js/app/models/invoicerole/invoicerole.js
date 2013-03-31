/**
 * Module for the InvoiceRole model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The InvoiceRole model class definition
     * Used for CRUD operations against individual InvoiceRole
     */
    var InvoiceRole = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoicerole', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.invoiceRoleType) {
            	errors.push({name: 'invoiceRoleType', message: entities_strings.alantra_form_field_required + entities_strings.invoicerole_invoiceroletype + '.'});
        	}	
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.invoicerole_party + '.'});
        	}	
            if (!attrs.invoice) {
            	errors.push({name: 'invoice', message: entities_strings.alantra_form_field_required + entities_strings.invoicerole_invoice + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoicerole_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoicerole_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoicerole_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the InvoiceRole class
    return InvoiceRole;
});