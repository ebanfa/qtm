/**
 * Module for the GeoBoundry model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The GeoBoundry model class definition
     * Used for CRUD operations against individual GeoBoundry
     */
    var GeoBoundry = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/geoboundry', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.geoBoundaryType) {
            	errors.push({name: 'geoBoundaryType', message: entities_strings.alantra_form_field_required + entities_strings.geoboundry_geoboundarytype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.geoboundry_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.geoboundry_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.geoboundry_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.geoboundry_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the GeoBoundry class
    return GeoBoundry;
});