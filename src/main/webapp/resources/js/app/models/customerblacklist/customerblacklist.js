/**
 * Module for the CustomerBlacklist model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CustomerBlacklist model class definition
     * Used for CRUD operations against individual CustomerBlacklist
     */
    var CustomerBlacklist = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/customerblacklist', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.customerblacklist_party + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.customerblacklist_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.customerblacklist_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.customerblacklist_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CustomerBlacklist class
    return CustomerBlacklist;
});