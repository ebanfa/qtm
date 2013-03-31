/**
 * Module for the CommunicationEventWorkEffort model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CommunicationEventWorkEffort model class definition
     * Used for CRUD operations against individual CommunicationEventWorkEffort
     */
    var CommunicationEventWorkEffort = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/communicationeventworkeffort', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.workEffort) {
            	errors.push({name: 'workEffort', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventworkeffort_workeffort + '.'});
        	}	
            if (!attrs.communicationEvent) {
            	errors.push({name: 'communicationEvent', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventworkeffort_communicationevent + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventworkeffort_code + '.'});
        	}	
            if (!attrs.description) {
            	errors.push({name: 'description', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventworkeffort_description + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventworkeffort_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventworkeffort_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CommunicationEventWorkEffort class
    return CommunicationEventWorkEffort;
});