/**
 * Module for the GeoBoundaryType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The GeoBoundaryType model class definition
     * Used for CRUD operations against individual GeoBoundaryType
     */
    var GeoBoundaryType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/geoboundarytype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.geoboundarytype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.geoboundarytype_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.geoboundarytype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.geoboundarytype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the GeoBoundaryType class
    return GeoBoundaryType;
});