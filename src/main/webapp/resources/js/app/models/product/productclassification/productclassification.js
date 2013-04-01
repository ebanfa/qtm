/**
 * Module for the ProductClassification model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductClassification model class definition
     * Used for CRUD operations against individual ProductClassification
     */
    var ProductClassification = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productclassification', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productCategory) {
            	errors.push({name: 'productCategory', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_productcategory + '.'});
        	}	
            if (!attrs.product) {
            	errors.push({name: 'product', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_product + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_name + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_fromdt + '.'});
        	}	
            if (!attrs.toDt) {
            	errors.push({name: 'toDt', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_todt + '.'});
        	}	
            if (!attrs.primaryFg) {
            	errors.push({name: 'primaryFg', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_primaryfg + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productclassification_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductClassification class
    return ProductClassification;
});