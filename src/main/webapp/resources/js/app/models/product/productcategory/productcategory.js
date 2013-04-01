/**
 * Module for the ProductCategory model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductCategory model class definition
     * Used for CRUD operations against individual ProductCategory
     */
    var ProductCategory = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productcategory', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productCategoryType) {
            	errors.push({name: 'productCategoryType', message: entities_strings.alantra_form_field_required + entities_strings.productcategory_productcategorytype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.productcategory_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productcategory_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productcategory_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productcategory_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductCategory class
    return ProductCategory;
});