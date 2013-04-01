/**
 * Module for the PostalAddress model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PostalAddress model class definition
     * Used for CRUD operations against individual PostalAddress
     */
    var PostalAddress = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/postaladdress', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanism) {
            	errors.push({name: 'contactMechanism', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_contactmechanism + '.'});
        	}	
            if (!attrs.address1) {
            	errors.push({name: 'address1', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_address1 + '.'});
        	}	
            if (!attrs.address2) {
            	errors.push({name: 'address2', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_address2 + '.'});
        	}	
            if (!attrs.directions) {
            	errors.push({name: 'directions', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_directions + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.postaladdress_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PostalAddress class
    return PostalAddress;
});