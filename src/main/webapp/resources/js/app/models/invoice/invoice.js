/**
 * Module for the Invoice model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Invoice model class definition
     * Used for CRUD operations against individual Invoice
     */
    var Invoice = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoice', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.invoiceType) {
            	errors.push({name: 'invoiceType', message: entities_strings.alantra_form_field_required + entities_strings.invoice_invoicetype + '.'});
        	}	
            if (!attrs.partyByToPartyId) {
            	errors.push({name: 'partyByToPartyId', message: entities_strings.alantra_form_field_required + entities_strings.invoice_partybytopartyid + '.'});
        	}	
            if (!attrs.contactMechanism) {
            	errors.push({name: 'contactMechanism', message: entities_strings.alantra_form_field_required + entities_strings.invoice_contactmechanism + '.'});
        	}	
            if (!attrs.partyByFromPartyId) {
            	errors.push({name: 'partyByFromPartyId', message: entities_strings.alantra_form_field_required + entities_strings.invoice_partybyfrompartyid + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoice_code + '.'});
        	}	
            if (!attrs.message) {
            	errors.push({name: 'message', message: entities_strings.alantra_form_field_required + entities_strings.invoice_message + '.'});
        	}	
            if (!attrs.invoiceDt) {
            	errors.push({name: 'invoiceDt', message: entities_strings.alantra_form_field_required + entities_strings.invoice_invoicedt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoice_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoice_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Invoice class
    return Invoice;
});