/**
 * Module for the EstimatedProductCost model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The EstimatedProductCost model class definition
     * Used for CRUD operations against individual EstimatedProductCost
     */
    var EstimatedProductCost = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/estimatedproductcost', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.costComponentType) {
            	errors.push({name: 'costComponentType', message: entities_strings.alantra_form_field_required + entities_strings.estimatedproductcost_costcomponenttype + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.estimatedproductcost_code + '.'});
        	}	
            if (!attrs.cost) {
            	errors.push({name: 'cost', message: entities_strings.alantra_form_field_required + entities_strings.estimatedproductcost_cost + '.'});
        	}	
            if (!attrs.fromDt) {
            	errors.push({name: 'fromDt', message: entities_strings.alantra_form_field_required + entities_strings.estimatedproductcost_fromdt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.estimatedproductcost_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.estimatedproductcost_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the EstimatedProductCost class
    return EstimatedProductCost;
});