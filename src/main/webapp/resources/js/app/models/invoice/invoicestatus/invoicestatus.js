/**
 * Module for the InvoiceStatus model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The InvoiceStatus model class definition
     * Used for CRUD operations against individual InvoiceStatus
     */
    var InvoiceStatus = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoicestatus', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.invoiceStatusType) {
            	errors.push({name: 'invoiceStatusType', message: entities_strings.alantra_form_field_required + entities_strings.invoicestatus_invoicestatustype + '.'});
        	}	
            if (!attrs.invoice) {
            	errors.push({name: 'invoice', message: entities_strings.alantra_form_field_required + entities_strings.invoicestatus_invoice + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.invoicestatus_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoicestatus_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoicestatus_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoicestatus_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the InvoiceStatus class
    return InvoiceStatus;
});