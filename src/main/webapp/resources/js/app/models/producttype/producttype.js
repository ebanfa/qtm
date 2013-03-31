/**
 * Module for the ProductType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductType model class definition
     * Used for CRUD operations against individual ProductType
     */
    var ProductType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/producttype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.producttype_code + '.'});
        	}	
            if (!attrs.names) {
            	errors.push({name: 'names', message: entities_strings.alantra_form_field_required + entities_strings.producttype_names + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.producttype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.producttype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductType class
    return ProductType;
});