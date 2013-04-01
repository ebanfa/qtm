/**
 * Module for the InvoiceItem model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The InvoiceItem model class definition
     * Used for CRUD operations against individual InvoiceItem
     */
    var InvoiceItem = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoiceitem', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.invoiceItemType) {
            	errors.push({name: 'invoiceItemType', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_invoiceitemtype + '.'});
        	}	
            if (!attrs.invoiceItemCategory) {
            	errors.push({name: 'invoiceItemCategory', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_invoiceitemcategory + '.'});
        	}	
            if (!attrs.invoice) {
            	errors.push({name: 'invoice', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_invoice + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_name + '.'});
        	}	
            if (!attrs.quantity) {
            	errors.push({name: 'quantity', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_quantity + '.'});
        	}	
            if (!attrs.amount) {
            	errors.push({name: 'amount', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_amount + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitem_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the InvoiceItem class
    return InvoiceItem;
});