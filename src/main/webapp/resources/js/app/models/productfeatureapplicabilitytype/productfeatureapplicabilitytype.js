/**
 * Module for the ProductFeatureApplicabilityType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductFeatureApplicabilityType model class definition
     * Used for CRUD operations against individual ProductFeatureApplicabilityType
     */
    var ProductFeatureApplicabilityType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productfeatureapplicabilitytype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicabilitytype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicabilitytype_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicabilitytype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productfeatureapplicabilitytype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductFeatureApplicabilityType class
    return ProductFeatureApplicabilityType;
});