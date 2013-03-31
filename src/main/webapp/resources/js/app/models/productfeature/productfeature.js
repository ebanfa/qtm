/**
 * Module for the ProductFeature model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductFeature model class definition
     * Used for CRUD operations against individual ProductFeature
     */
    var ProductFeature = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productfeature', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productFeatureType) {
            	errors.push({name: 'productFeatureType', message: entities_strings.alantra_form_field_required + entities_strings.productfeature_productfeaturetype + '.'});
        	}	
            if (!attrs.productFeatureCategory) {
            	errors.push({name: 'productFeatureCategory', message: entities_strings.alantra_form_field_required + entities_strings.productfeature_productfeaturecategory + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productfeature_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.productfeature_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productfeature_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productfeature_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductFeature class
    return ProductFeature;
});