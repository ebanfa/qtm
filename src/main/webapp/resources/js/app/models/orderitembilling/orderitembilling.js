/**
 * Module for the OrderItemBilling model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The OrderItemBilling model class definition
     * Used for CRUD operations against individual OrderItemBilling
     */
    var OrderItemBilling = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/orderitembilling', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.invoiceItem) {
            	errors.push({name: 'invoiceItem', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_invoiceitem + '.'});
        	}	
            if (!attrs.productOrderItem) {
            	errors.push({name: 'productOrderItem', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_productorderitem + '.'});
        	}	
            if (!attrs.quantity) {
            	errors.push({name: 'quantity', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_quantity + '.'});
        	}	
            if (!attrs.amount) {
            	errors.push({name: 'amount', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_amount + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.orderitembilling_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the OrderItemBilling class
    return OrderItemBilling;
});