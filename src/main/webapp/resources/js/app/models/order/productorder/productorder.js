/**
 * Module for the ProductOrder model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ProductOrder model class definition
     * Used for CRUD operations against individual ProductOrder
     */
    var ProductOrder = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/productorder', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.productOrderType) {
            	errors.push({name: 'productOrderType', message: entities_strings.alantra_form_field_required + entities_strings.productorder_productordertype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.productorder_name + '.'});
        	}	
            if (!attrs.orderDt) {
            	errors.push({name: 'orderDt', message: entities_strings.alantra_form_field_required + entities_strings.productorder_orderdt + '.'});
        	}	
            if (!attrs.fromParty) {
            	errors.push({name: 'fromParty', message: entities_strings.alantra_form_field_required + entities_strings.productorder_fromparty + '.'});
        	}	
            if (!attrs.toParty) {
            	errors.push({name: 'toParty', message: entities_strings.alantra_form_field_required + entities_strings.productorder_toparty + '.'});
        	}	
            if (!attrs.amount) {
            	errors.push({name: 'amount', message: entities_strings.alantra_form_field_required + entities_strings.productorder_amount + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.productorder_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.productorder_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.productorder_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ProductOrder class
    return ProductOrder;
});