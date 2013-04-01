/**
 * Module for the PostalAddressBoundry model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The PostalAddressBoundry model class definition
     * Used for CRUD operations against individual PostalAddressBoundry
     */
    var PostalAddressBoundry = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/postaladdressboundry', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.postalAddress) {
            	errors.push({name: 'postalAddress', message: entities_strings.alantra_form_field_required + entities_strings.postaladdressboundry_postaladdress + '.'});
        	}	
            if (!attrs.geoBoundry) {
            	errors.push({name: 'geoBoundry', message: entities_strings.alantra_form_field_required + entities_strings.postaladdressboundry_geoboundry + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.postaladdressboundry_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.postaladdressboundry_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.postaladdressboundry_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the PostalAddressBoundry class
    return PostalAddressBoundry;
});