/**
 * Module for the ServiceTransaction model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ServiceTransaction model class definition
     * Used for CRUD operations against individual ServiceTransaction
     */
    var ServiceTransaction = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/servicetransaction', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.service) {
            	errors.push({name: 'service', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_service + '.'});
        	}	
            if (!attrs.serviceTransactionType) {
            	errors.push({name: 'serviceTransactionType', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_servicetransactiontype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_name + '.'});
        	}	
            if (!attrs.amount) {
            	errors.push({name: 'amount', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_amount + '.'});
        	}	
            if (!attrs.txnDate) {
            	errors.push({name: 'txnDate', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_txndate + '.'});
        	}	
            if (!attrs.accountNo) {
            	errors.push({name: 'accountNo', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_accountno + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.servicetransaction_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ServiceTransaction class
    return ServiceTransaction;
});