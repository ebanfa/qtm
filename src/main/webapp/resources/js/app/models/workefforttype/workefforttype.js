/**
 * Module for the WorkEffortType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The WorkEffortType model class definition
     * Used for CRUD operations against individual WorkEffortType
     */
    var WorkEffortType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/workefforttype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.workefforttype_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.workefforttype_name + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.workefforttype_description + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.workefforttype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.workefforttype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the WorkEffortType class
    return WorkEffortType;
});