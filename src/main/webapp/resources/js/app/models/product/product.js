/**
 * Module for the Product model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Product model class definition
     * Used for CRUD operations against individual Product
     */
    var Product = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/product', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productType) {
            	errors.push({name: 'productType', message: entities_strings.alantra_form_field_required + entities_strings.product_producttype + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.product_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.product_name + '.'});
        	}	
            if (!attrs.introductionDt) {
            	errors.push({name: 'introductionDt', message: entities_strings.alantra_form_field_required + entities_strings.product_introductiondt + '.'});
        	}	
            if (!attrs.discountinuedDt) {
            	errors.push({name: 'discountinuedDt', message: entities_strings.alantra_form_field_required + entities_strings.product_discountinueddt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.product_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.product_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Product class
    return Product;
});