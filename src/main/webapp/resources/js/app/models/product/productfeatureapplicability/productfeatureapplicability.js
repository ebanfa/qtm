/**
 * Module for the ProductFeatureApplicability model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductFeatureApplicability model class definition
     * Used for CRUD operations against individual ProductFeatureApplicability
     */
    var ProductFeatureApplicability = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productfeatureapplicability', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.product) {
            	errors.push({name: 'product', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_product + '.'});
        	}	
            if (!attrs.productFeature) {
            	errors.push({name: 'productFeature', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_productfeature + '.'});
        	}	
            if (!attrs.productFeatureApplicabilityType) {
            	errors.push({name: 'productFeatureApplicabilityType', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_productfeatureapplicabilitytype + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_fromdt + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicability_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductFeatureApplicability class
    return ProductFeatureApplicability;
});