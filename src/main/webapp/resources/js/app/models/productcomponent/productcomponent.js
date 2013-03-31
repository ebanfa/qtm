/**
 * Module for the ProductComponent model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductComponent model class definition
     * Used for CRUD operations against individual ProductComponent
     */
    var ProductComponent = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productcomponent', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productByInProdId) {
            	errors.push({name: 'productByInProdId', message: entities_strings.alantra_form_field_required + entities_strings.productcomponent_productbyinprodid + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productcomponent_code + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.productcomponent_fromdt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productcomponent_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productcomponent_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductComponent class
    return ProductComponent;
});