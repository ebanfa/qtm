/**
 * Module for the WorkEffort model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The WorkEffort model class definition
     * Used for CRUD operations against individual WorkEffort
     */
    var WorkEffort = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/workeffort', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.workEffortType) {
            	errors.push({name: 'workEffortType', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_workefforttype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_name + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_description + '.'});
        	}	
            if (!attrs.schedStartDt) {
            	errors.push({name: 'schedStartDt', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_schedstartdt + '.'});
        	}	
            if (!attrs.schedEndDt) {
            	errors.push({name: 'schedEndDt', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_schedenddt + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.workeffort_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the WorkEffort class
    return WorkEffort;
});