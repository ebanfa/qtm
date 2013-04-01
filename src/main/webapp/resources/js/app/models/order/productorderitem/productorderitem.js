/**
 * Module for the ProductOrderItem model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductOrderItem model class definition
     * Used for CRUD operations against individual ProductOrderItem
     */
    var ProductOrderItem = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productorderitem', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productOrderItemType) {
            	errors.push({name: 'productOrderItemType', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_productorderitemtype + '.'});
        	}	
            if (!attrs.productOrder) {
            	errors.push({name: 'productOrder', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_productorder + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_name + '.'});
        	}	
            if (!attrs.quantity) {
            	errors.push({name: 'quantity', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_quantity + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productorderitem_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductOrderItem class
    return ProductOrderItem;
});