/**
 * Module for the InvoiceItemCategory model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The InvoiceItemCategory model class definition
     * Used for CRUD operations against individual InvoiceItemCategory
     */
    var InvoiceItemCategory = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoiceitemcategory', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitemcategory_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitemcategory_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitemcategory_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoiceitemcategory_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the InvoiceItemCategory class
    return InvoiceItemCategory;
});