/**
 * Module for the GeoBoundryAssociation model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The GeoBoundryAssociation model class definition
     * Used for CRUD operations against individual GeoBoundryAssociation
     */
    var GeoBoundryAssociation = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/geoboundryassociation', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.geoBoundryByToGeoId) {
            	errors.push({name: 'geoBoundryByToGeoId', message: entities_strings.alantra_form_field_required + entities_strings.geoboundryassociation_geoboundrybytogeoid + '.'});
        	}	
            if (!attrs.geoBoundryByFromGeoId) {
            	errors.push({name: 'geoBoundryByFromGeoId', message: entities_strings.alantra_form_field_required + entities_strings.geoboundryassociation_geoboundrybyfromgeoid + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.geoboundryassociation_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.geoboundryassociation_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.geoboundryassociation_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the GeoBoundryAssociation class
    return GeoBoundryAssociation;
});