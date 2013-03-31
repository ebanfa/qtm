/**
 * Module for the UomConversion model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The UomConversion model class definition
     * Used for CRUD operations against individual UomConversion
     */
    var UomConversion = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/uomconversion', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.fromUomId) {
            	errors.push({name: 'fromUomId', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_fromuomid + '.'});
        	}	
            if (!attrs.toUomId) {
            	errors.push({name: 'toUomId', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_touomid + '.'});
        	}	
            if (!attrs.conversionFactor) {
            	errors.push({name: 'conversionFactor', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_conversionfactor + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.uomconversion_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the UomConversion class
    return UomConversion;
});