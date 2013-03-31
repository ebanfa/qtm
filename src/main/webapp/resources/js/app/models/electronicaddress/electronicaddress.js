/**
 * Module for the ElectronicAddress model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The ElectronicAddress model class definition
     * Used for CRUD operations against individual ElectronicAddress
     */
    var ElectronicAddress = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/electronicaddress', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.contactMechanism) {
            	errors.push({name: 'contactMechanism', message: entities_strings.alantra_form_field_required + entities_strings.electronicaddress_contactmechanism + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.electronicaddress_code + '.'});
        	}	
            if (!attrs.elecAddrTxt) {
            	errors.push({name: 'elecAddrTxt', message: entities_strings.alantra_form_field_required + entities_strings.electronicaddress_elecaddrtxt + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.electronicaddress_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.electronicaddress_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the ElectronicAddress class
    return ElectronicAddress;
});