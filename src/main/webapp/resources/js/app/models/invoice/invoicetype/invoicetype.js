/**
 * Module for the InvoiceType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The InvoiceType model class definition
     * Used for CRUD operations against individual InvoiceType
     */
    var InvoiceType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/invoicetype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.invoicetype_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.invoicetype_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.invoicetype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.invoicetype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the InvoiceType class
    return InvoiceType;
});